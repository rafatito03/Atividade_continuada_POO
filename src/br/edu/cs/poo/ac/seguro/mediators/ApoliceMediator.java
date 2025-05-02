package br.edu.cs.poo.ac.seguro.mediators;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.cs.poo.ac.seguro.daos.ApoliceDAO;
import br.edu.cs.poo.ac.seguro.daos.SeguradoEmpresaDAO;
import br.edu.cs.poo.ac.seguro.daos.SeguradoPessoaDAO;
import br.edu.cs.poo.ac.seguro.daos.SinistroDAO;
import br.edu.cs.poo.ac.seguro.daos.VeiculoDAO;
import br.edu.cs.poo.ac.seguro.entidades.Apolice;
import br.edu.cs.poo.ac.seguro.entidades.CategoriaVeiculo;
import br.edu.cs.poo.ac.seguro.entidades.PrecoAno;
import br.edu.cs.poo.ac.seguro.entidades.Segurado;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;
import br.edu.cs.poo.ac.seguro.entidades.Veiculo;

public class ApoliceMediator {
    private SeguradoPessoaDAO daoSegPes = new SeguradoPessoaDAO();
    private SeguradoEmpresaDAO daoSegEmp = new SeguradoEmpresaDAO();
    private VeiculoDAO daoVel = new VeiculoDAO();
    private ApoliceDAO daoApo = new ApoliceDAO();
    private SinistroDAO daoSin = new SinistroDAO();

    private ApoliceMediator() {}

    public static ApoliceMediator instancia = new ApoliceMediator();

    public static ApoliceMediator getInstancia() {
        return instancia;
    }

    public RetornoInclusaoApolice incluirApolice(DadosVeiculo dados) {
        if (dados == null) return new RetornoInclusaoApolice(null, "Dados do veículo devem ser informados");
        if (dados.getPlaca() == null || dados.getPlaca().isBlank()) return new RetornoInclusaoApolice(null, "Placa do veículo deve ser informada");
        if (dados.getCpfOuCnpj() == null || dados.getCpfOuCnpj().isBlank()) return new RetornoInclusaoApolice(null, "CPF ou CNPJ deve ser informado");

        if (dados.getCpfOuCnpj().length() == 14) {
            String validacaoCnpj = ValidadorCpfCnpj.ehCnpjValido(dados.getCpfOuCnpj());
            if (validacaoCnpj != null) return new RetornoInclusaoApolice(null, "CNPJ inválido");
        } else {
            if (!ValidadorCpfCnpj.ehCpfValido(dados.getCpfOuCnpj())) return new RetornoInclusaoApolice(null, "CPF inválido");
        }

        if (dados.getAno() < 2020 || dados.getAno() > 2025) return new RetornoInclusaoApolice(null, "Ano tem que estar entre 2020 e 2025, incluindo estes");
        if (dados.getValorMaximoSegurado() == null) return new RetornoInclusaoApolice(null, "Valor máximo segurado deve ser informado");

        CategoriaVeiculo categoria = null;
        for (CategoriaVeiculo cat : CategoriaVeiculo.values()) if (cat.getCodigo() == dados.getCodigoCategoria()) { categoria = cat; break; }
        if (categoria == null) return new RetornoInclusaoApolice(null, "Categoria inválida");

        PrecoAno precoAno = null;
        for (PrecoAno pa : categoria.getPrecosAnos()) if (pa.getAno() == dados.getAno()) { precoAno = pa; break; }
        if (precoAno == null) return new RetornoInclusaoApolice(null, "Preço não encontrado para o ano informado");

        BigDecimal minValor = BigDecimal.valueOf(precoAno.getPreco()).multiply(new BigDecimal("0.75"));
        BigDecimal maxValor = BigDecimal.valueOf(precoAno.getPreco());
        if (dados.getValorMaximoSegurado().compareTo(minValor) < 0 || dados.getValorMaximoSegurado().compareTo(maxValor) > 0)
            return new RetornoInclusaoApolice(null, "Valor máximo segurado deve estar entre 75% e 100% do valor do carro encontrado na categoria");

        boolean isEmpresa = dados.getCpfOuCnpj().length() != 11;
        Segurado seg = isEmpresa ? daoSegEmp.buscar(dados.getCpfOuCnpj()) : daoSegPes.buscar(dados.getCpfOuCnpj());
        if (seg == null) return new RetornoInclusaoApolice(null, isEmpresa ? "CNPJ inexistente no cadastro de empresas" : "CPF inexistente no cadastro de pessoas");

        String numeroGerado = generateNumeroApolice(dados, isEmpresa);
        Apolice apoliceExistente = daoApo.buscar(numeroGerado);
        if (apoliceExistente != null) return new RetornoInclusaoApolice(null, "Apólice já existente para ano atual e veículo");

        Veiculo veiculo = daoVel.buscar(dados.getPlaca());
        if (veiculo != null) {
            boolean validOwner = isEmpresa ?
                veiculo.getProprietarioEmpresa() != null && veiculo.getProprietarioEmpresa().getCnpj().equals(dados.getCpfOuCnpj()) :
                veiculo.getProprietarioPessoa() != null && veiculo.getProprietarioPessoa().getCpf().equals(dados.getCpfOuCnpj());
            if (!validOwner) {
                if (isEmpresa) veiculo.setProprietarioEmpresa((SeguradoEmpresa) seg);
                else veiculo.setProprietarioPessoa((SeguradoPessoa) seg);
                daoVel.alterar(veiculo);
            }
        } else {
            veiculo = new Veiculo(dados.getPlaca(), dados.getAno(), isEmpresa ? (SeguradoEmpresa) seg : null,
                                  !isEmpresa ? (SeguradoPessoa) seg : null, categoria);
            daoVel.incluir(veiculo);
        }

        BigDecimal VPA = dados.getValorMaximoSegurado().multiply(new BigDecimal("0.03")).setScale(2, RoundingMode.HALF_UP);
        BigDecimal VPB = isEmpresa && ((SeguradoEmpresa) seg).isLocadoraDeVeiculos()
                         ? VPA.multiply(new BigDecimal("1.2")).setScale(2, RoundingMode.HALF_UP)
                         : VPA;

        BigDecimal bonusDividido = seg.getBonus().divide(new BigDecimal("10"), 2, RoundingMode.HALF_UP);
        BigDecimal VPC = VPB.subtract(bonusDividido).setScale(2, RoundingMode.HALF_UP);
        if (VPC.compareTo(BigDecimal.ZERO) < 0) VPC = BigDecimal.ZERO;

        BigDecimal franquia = VPB.multiply(new BigDecimal("1.3")).setScale(2, RoundingMode.HALF_UP);

        Apolice apolice = new Apolice(numeroGerado, veiculo, franquia, VPC,
                                     dados.getValorMaximoSegurado().setScale(2, RoundingMode.HALF_UP),
                                     LocalDate.now(), null);

        daoApo.incluir(apolice);

        Sinistro[] todosSinistros = daoSin.buscarTodos();
        LocalDate corteData = LocalDate.now().minusYears(1);
        boolean temSinistroRecente = Arrays.stream(todosSinistros)
            .filter(s -> s.getVeiculo().getPlaca().equals(dados.getPlaca()))
            .map(s -> s.getDataHoraSinistro().toLocalDate())
            .anyMatch(d -> d.isAfter(corteData));

        if (temSinistroRecente) {
            BigDecimal acrescimo = VPC.multiply(new BigDecimal("0.3")).setScale(2, RoundingMode.HALF_UP);
            seg.creditarBonus(acrescimo);
            if (isEmpresa) daoSegEmp.alterar((SeguradoEmpresa) seg);
            else daoSegPes.alterar((SeguradoPessoa) seg);
        }

        return new RetornoInclusaoApolice(apolice.getNumero(), null);
    }

    private String generateNumeroApolice(DadosVeiculo dados, boolean isEmpresa) {
        int anoAtual = LocalDate.now().getYear();
        return isEmpresa
            ? anoAtual + dados.getCpfOuCnpj() + dados.getPlaca()
            : anoAtual + "000" + dados.getCpfOuCnpj() + dados.getPlaca();
    }

    public Apolice buscarApolice(String numero) {
        return null;
    }

    public String excluirApolice(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            return "Número deve ser informado";
        }
        
        Apolice apolice = daoApo.buscar(numero);
        if (apolice == null) {
            return "Apólice inexistente";
        }
        
        Sinistro[] todosSinistros = daoSin.buscarTodos();
        List<Sinistro> sinistrosVeiculo = Arrays.stream(todosSinistros)
            .filter(s -> s.getVeiculo().getPlaca().equals(apolice.getVeiculo().getPlaca()))
            .collect(Collectors.toList());
        
        LocalDate corteData = apolice.getDataInicioVigencia().minusYears(1);
        boolean temSinistroNoAno = sinistrosVeiculo.stream()
            .anyMatch(s -> s.getDataHoraSinistro().toLocalDate().isAfter(corteData));
        
        if (temSinistroNoAno) {
            return "Existe sinistro cadastrado para o veículo em questão e no mesmo ano da apólice";
        }
        
        boolean sucesso = daoApo.excluir(numero);
        return sucesso ? null : "Falha ao excluir apólice";
    }
    	
}
