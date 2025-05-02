package br.edu.cs.poo.ac.seguro.mediators;

import java.math.BigDecimal;
import java.time.LocalDate;
import br.edu.cs.poo.ac.seguro.entidades.Endereco;

public class SeguradoMediator {

    private static SeguradoMediator instancia = new SeguradoMediator();

    public static SeguradoMediator getInstancia() {
        return instancia;
    }

    public String validarNome(String nome) {
        if (StringUtils.ehNuloOuBranco(nome)) return "Nome deve ser informado";
        if (StringUtils.temSomenteNumeros(nome)) return "Nome não pode conter apenas números.";
        return null;
    }

    public String validarEndereco(Endereco endereco) {
        if (endereco == null) return "Endereço deve ser informado";

        if (StringUtils.ehNuloOuBranco(endereco.getCep())) return "CEP é obrigatório.";
        if (!StringUtils.temSomenteNumeros(endereco.getCep())) return "CEP deve conter apenas números.";

        if (StringUtils.ehNuloOuBranco(endereco.getCidade())) return "Cidade é obrigatória.";
        if (StringUtils.ehNuloOuBranco(endereco.getComplemento())) return "Complemento é obrigatório.";

        if (StringUtils.ehNuloOuBranco(endereco.getEstado())) return "Estado é obrigatório.";

        if (StringUtils.ehNuloOuBranco(endereco.getLogradouro())) return "Logradouro é obrigatório.";

        if (StringUtils.ehNuloOuBranco(endereco.getPais())) return "País é obrigatório.";
        return null;
    }

    public String validarDataCriacao(LocalDate dataCriacao) {
        if(dataCriacao == null) return "Data do nascimento deve ser informada";
        return null;
    }
    public BigDecimal ajustarDebitoBonus(BigDecimal bonus, BigDecimal valorDebito) {
        return null;
    }
}