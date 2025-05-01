package br.edu.cs.poo.ac.seguro.mediators;

import br.edu.cs.poo.ac.seguro.daos.SeguradoPessoaDAO;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;

public class SeguradoPessoaMediator {

    private SeguradoPessoaDAO seguradoPessoaDAO = new SeguradoPessoaDAO();
    private static final SeguradoPessoaMediator instancia = new SeguradoPessoaMediator();

    private SeguradoPessoaMediator() { }

    public static SeguradoPessoaMediator getInstancia() {
        return instancia;
    }

    public String validarCpf(String cpf) {
        if (!ValidadorCpfCnpj.ehCpfValido(cpf))
            return "CPF inválido.";
        return null;
    }

    public String validarRenda(double renda) {
        if (renda <= 0)
            return "Renda deve ser maior que zero.";
        return null;
    }

    public String incluirSeguradoPessoa(SeguradoPessoa seg) {
        String erro = validarSeguradoPessoa(seg);
        if (erro != null) 
            return erro;
        seguradoPessoaDAO.incluir(seg);
        return "Segurado incluído com sucesso.";
    }

    public String alterarSeguradoPessoa(SeguradoPessoa seg) {
        String erro = validarSeguradoPessoa(seg);
        if (erro != null) 
            return erro;
        seguradoPessoaDAO.alterar(seg);
        return "Segurado alterado com sucesso.";
    }

    public String excluirSeguradoPessoa(String cpf) {
        String erro = validarCpf(cpf);
        if (erro != null) 
            return erro;
        seguradoPessoaDAO.excluir(cpf);
        return "Segurado excluído com sucesso.";
    }

    public SeguradoPessoa buscarSeguradoPessoa(String cpf) {
        return seguradoPessoaDAO.buscar(cpf);
    }

    public String validarSeguradoPessoa(SeguradoPessoa seg) {
        String erro;
        if ((erro = validarCpf(seg.getCpf())) != null) return erro;
        if ((erro = validarRenda(seg.getRenda())) != null) return erro;

        SeguradoMediator mediator = SeguradoMediator.getInstancia();
        if ((erro = mediator.validarNome(seg.getNome())) != null) return erro;
        if ((erro = mediator.validarEndereco(seg.getEndereco())) != null) return erro;
        if ((erro = mediator.validarDataCriacao(seg.getDataNascimento())) != null) return erro;

        return null;
    }
}
