package br.edu.cs.poo.ac.seguro.mediators;

import java.time.LocalDate;

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
        
        if (cpf == null || cpf.trim().isEmpty()) {
            return "CPF deve ser informado";
        }

        
        cpf = cpf.replaceAll("[^0-9]", "");

        
        if (cpf.length() != 11) {
            return "CPF deve ter 11 caracteres";
        }

       
        if (!ValidadorCpfCnpj.ehCpfValido(cpf)) {
            return "CPF com dígito inválido"; 
        }

        return null; 
    }

    public String validarRenda(double renda) {
        if (renda < 0) { 
            return "Renda deve ser maior ou igual à zero";
        }
        return null;
    }

    public String incluirSeguradoPessoa(SeguradoPessoa seg) {
        String erro = validarSeguradoPessoa(seg);
        if (erro != null) 
            return erro;
        if (seguradoPessoaDAO.buscar(seg.getCpf()) != null) {
            return "CPF do segurado pessoa já existente";
        }

        seguradoPessoaDAO.incluir(seg);
        return null;
    }

    public String alterarSeguradoPessoa(SeguradoPessoa seg) {
        String erro = validarSeguradoPessoa(seg);
        if (erro != null) 
            return erro;
        if (seguradoPessoaDAO.buscar(seg.getCpf()) == null) {
            return "CPF do segurado pessoa não existente";
        }
        seguradoPessoaDAO.alterar(seg);
        return null;
    }

    public String excluirSeguradoPessoa(String cpf) {
        
        if (seguradoPessoaDAO.buscar(cpf) == null) {
            return "CPF do segurado pessoa não existente";
        }

        
        String erro = validarCpf(cpf);
        if (erro != null) {
            return erro;
        }

        seguradoPessoaDAO.excluir(cpf);
        return null; 
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
        erro = mediator.validarDataCriacao(seg.getDataNascimento());
        
        if(erro != null && erro.equals("Data da criação deve ser informada")) {
        	return "Data do nascimento deve ser informada";
        }

        return null;
    }
}