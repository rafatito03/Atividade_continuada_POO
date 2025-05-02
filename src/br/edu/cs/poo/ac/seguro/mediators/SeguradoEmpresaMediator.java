package br.edu.cs.poo.ac.seguro.mediators;

import br.edu.cs.poo.ac.seguro.daos.SeguradoEmpresaDAO;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;

public class SeguradoEmpresaMediator {

    private SeguradoEmpresaDAO seguradoEmpresaDAO = new SeguradoEmpresaDAO();
    private static final SeguradoEmpresaMediator instancia = new SeguradoEmpresaMediator();

    private SeguradoEmpresaMediator() { }

    public static SeguradoEmpresaMediator getInstancia() {
        return instancia;
    }

    public String validarCnpj(String cnpj) {
        return ValidadorCpfCnpj.ehCnpjValido(cnpj);
    }

    public String validarFaturamento(double faturamento) {
        if (faturamento <= 0) return "Faturamento deve ser maior que zero";
        return null;
    }

    public String incluirSeguradoEmpresa(SeguradoEmpresa seg) {
        String erro = validarSeguradoEmpresa(seg);
        if (erro != null) return erro;

        if (seguradoEmpresaDAO.buscar(seg.getCnpj()) != null) {
            return "CNPJ do segurado empresa já existente";
        }

        seguradoEmpresaDAO.incluir(seg);
        return null;
    }

    public String alterarSeguradoEmpresa(SeguradoEmpresa seg) {
        String erro = validarSeguradoEmpresa(seg);
        if (erro != null) return erro;
        if (seguradoEmpresaDAO.buscar(seg.getCnpj()) == null) {
            return "CNPJ do segurado empresa não existente";
        }

        seguradoEmpresaDAO.alterar(seg);
        return null;
    }

    public String excluirSeguradoEmpresa(String cnpj) {
        String erro = validarCnpj(cnpj);
        if (erro != null) return erro;
        if (seguradoEmpresaDAO.buscar(cnpj) == null) {
            return "CNPJ do segurado empresa não existente";
        }
        seguradoEmpresaDAO.excluir(cnpj);
        return null;
    }

    public SeguradoEmpresa buscarSeguradoEmpresa(String cnpj) {
        return seguradoEmpresaDAO.buscar(cnpj);
    }

    public String validarSeguradoEmpresa(SeguradoEmpresa seg) {
        String erro;
        if ((erro = validarCnpj(seg.getCnpj())) != null) return erro;
        if ((erro = validarFaturamento(seg.getFaturamento())) != null) return erro;

        SeguradoMediator mediator = SeguradoMediator.getInstancia();

        if ((erro = mediator.validarNome(seg.getNome())) != null) return erro;
        if ((erro = mediator.validarEndereco(seg.getEndereco())) != null) return erro;
        if ((erro = mediator.validarDataCriacao(seg.getDataAbertura())) != null) return erro;

        return null;
    }
}
