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
        if (ValidadorCpfCnpj.ehCnpjValido(cnpj)) return null;
        return "CNPJ inválido.";
    }

    public String validarFaturamento(double faturamento) {
        if (faturamento <= 0) return "Faturamento deve ser maior que zero.";
        return null;
    }

    public String incluirSeguradoEmpresa(SeguradoEmpresa seg) {
        String erro = validarSeguradoEmpresa(seg);
        if (erro != null) return erro;
        seguradoEmpresaDAO.incluir(seg);
        return "Segurado empresa incluído com sucesso.";
    }

    public String alterarSeguradoEmpresa(SeguradoEmpresa seg) {
        String erro = validarSeguradoEmpresa(seg);
        if (erro != null) return erro;
        seguradoEmpresaDAO.alterar(seg);
        return "Segurado empresa alterado com sucesso.";
    }

    public String excluirSeguradoEmpresa(String cnpj) {
        String erro = validarCnpj(cnpj);
        if (erro != null) return erro;
        seguradoEmpresaDAO.excluir(cnpj);
        return "Segurado empresa excluído com sucesso.";
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

        return null;
    }
}
