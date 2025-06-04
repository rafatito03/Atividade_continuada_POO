package br.edu.cs.poo.ac.seguro.mediators;

import br.edu.cs.poo.ac.seguro.daos.ApoliceDAO;
import br.edu.cs.poo.ac.seguro.daos.SinistroDAO;
import br.edu.cs.poo.ac.seguro.daos.VeiculoDAO;
import br.edu.cs.poo.ac.seguro.entidades.Apolice;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;
import br.edu.cs.poo.ac.seguro.entidades.TipoSinistro;
import br.edu.cs.poo.ac.seguro.entidades.Veiculo;
import br.edu.cs.poo.ac.seguro.excecoes.ExcecaoValidacaoDados;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SinistroMediator {

    private VeiculoDAO daoVeiculo = new VeiculoDAO();
    private ApoliceDAO daoApolice = new ApoliceDAO();
    private SinistroDAO daoSinistro = new SinistroDAO();
    private static SinistroMediator instancia;

    public static SinistroMediator getInstancia() {
        if (instancia == null) {
            instancia = new SinistroMediator();
        }
        return instancia;
    }

    private SinistroMediator() {}

    public String incluirSinistro(DadosSinistro dados, LocalDateTime dataHoraAtual) throws ExcecaoValidacaoDados {
        ExcecaoValidacaoDados excecao = new ExcecaoValidacaoDados(); 
        Veiculo veiculoSinistrado = null;
        TipoSinistro tipoSinistroEnum = null;
        Apolice apoliceDeCobertura = null;
        String numeroApoliceCobertura = null;
        
        if (dados == null) {
            excecao.adicionarMensagem("Dados do sinistro devem ser informados"); 
            throw excecao; 
        }

        if (dados.getDataHoraSinistro() == null) {
            excecao.adicionarMensagem("Data/hora do sinistro deve ser informada"); 
        } else if (dados.getDataHoraSinistro().isAfter(dataHoraAtual) || dados.getDataHoraSinistro().isEqual(dataHoraAtual)) {
            excecao.adicionarMensagem("Data/hora do sinistro deve ser menor que a data/hora atual");
        }

        if (dados.getPlaca() == null || dados.getPlaca().trim().isEmpty()) {
            excecao.adicionarMensagem("Placa do Veiculo deve ser informada"); 
        } else {
            veiculoSinistrado = daoVeiculo.buscar(dados.getPlaca().trim());
            if (veiculoSinistrado == null) {
                excecao.adicionarMensagem("Veiculo não cadastrado");
            }
        }

        if (dados.getUsuarioRegistro() == null || dados.getUsuarioRegistro().trim().isEmpty()) {
            excecao.adicionarMensagem("Usuario do registro de sinistro deve ser informado"); 
        }

        if (dados.getValorSinistro() <= 0) {
            excecao.adicionarMensagem("Valor do sinistro deve ser maior que zero");
        }

        boolean tipoSinistroValido = false;
        if (TipoSinistro.values() != null) { 
            for (TipoSinistro tipo : TipoSinistro.values()) {
                if (tipo.getCodigo() == dados.getCodigoTipoSinistro()) { 
                    tipoSinistroEnum = tipo;
                    tipoSinistroValido = true;
                    break;
                }
            }
        }
        if (!tipoSinistroValido) {
            excecao.adicionarMensagem("Codigo do tipo de sinistro invalido"); 
        }
        
        if (excecao.getMensagens().isEmpty() && veiculoSinistrado != null) { 
            Apolice[] todasApolices = daoApolice.buscarTodos(); 
            boolean encontrouApolice = false;
            if (todasApolices != null) {
                for (Apolice apolice : todasApolices) {
                    if (apolice.getVeiculo() != null && apolice.getVeiculo().getPlaca().equals(veiculoSinistrado.getPlaca())) {
                    	LocalDateTime dataInicioVigencia = apolice.getDataInicioVigencia().atStartOfDay();
                        LocalDateTime dataFimVigencia = dataInicioVigencia.plusYears(1);

                        if (!dados.getDataHoraSinistro().isBefore(dataInicioVigencia) && dados.getDataHoraSinistro().isBefore(dataFimVigencia)) {
                            apoliceDeCobertura = apolice;
                            numeroApoliceCobertura = apolice.getNumero();
                            encontrouApolice = true;
                            break;
                        }
                    }
                }
            }

            if (!encontrouApolice) {
                excecao.adicionarMensagem("Nao existe apolice vigente para o veiculo");
            } else {
            	BigDecimal valorSinistroBD = BigDecimal.valueOf(dados.getValorSinistro());
            	if (valorSinistroBD.compareTo(apoliceDeCobertura.getValorMaximoSegurado()) > 0) {
                     excecao.adicionarMensagem("Valor do sinistro nao pode ultrapassar o valor maximo segurado constante na apolice");
                }
            }
        } else if (excecao.getMensagens().isEmpty() && veiculoSinistrado == null && dados.getPlaca() != null && !dados.getPlaca().trim().isEmpty()) {
        }


        String numeroSinistroGerado = null;
        int proximoSequencial = 1;

        if (excecao.getMensagens().isEmpty() && apoliceDeCobertura != null) {
            Sinistro[] sinistrosDaApolice = daoSinistro.listarPorApolice(numeroApoliceCobertura);
            
            List<Sinistro> listaSinistrosFiltrados = new ArrayList<>();
            if (sinistrosDaApolice != null) {
                for (Sinistro s : sinistrosDaApolice) {
                    listaSinistrosFiltrados.add(s);
                }
            }


            if (!listaSinistrosFiltrados.isEmpty()) {
                Collections.sort(listaSinistrosFiltrados, new ComparadorSinistroSequencial());
                proximoSequencial = listaSinistrosFiltrados.get(listaSinistrosFiltrados.size() - 1).getSequencial() + 1;
            }

            String sequencialFormatado = String.format("%03d", proximoSequencial);
            numeroSinistroGerado = "S" + numeroApoliceCobertura + sequencialFormatado;
        }


        if (excecao.getMensagens().isEmpty() && apoliceDeCobertura != null && veiculoSinistrado != null && tipoSinistroEnum != null && numeroSinistroGerado != null) {

            Sinistro novoSinistro = new Sinistro(
                veiculoSinistrado,
                dados.getDataHoraSinistro(),
                dataHoraAtual, 
                dados.getUsuarioRegistro(),
                new BigDecimal(dados.getValorSinistro()),
                tipoSinistroEnum
            );
            novoSinistro.setNumeroApolice(numeroApoliceCobertura);
            novoSinistro.setSequencial(proximoSequencial);  
            novoSinistro.setNumero(numeroSinistroGerado); 

            if (!daoSinistro.incluir(novoSinistro)) {
                excecao.adicionarMensagem("Falha ao incluir o sinistro no sistema de persistência.");
            }
        }
        
        if (!excecao.getMensagens().isEmpty()) {
            throw excecao;
        }
        
        if (numeroSinistroGerado == null && excecao.getMensagens().isEmpty()) {
            excecao.adicionarMensagem("Erro interno: O número do sinistro não pôde ser gerado apesar da ausência de erros de validação.");
            throw excecao;
        }
        
        return numeroSinistroGerado;
    }
}