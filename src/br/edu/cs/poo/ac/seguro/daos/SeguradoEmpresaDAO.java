package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;

public class SeguradoEmpresaDAO extends DAOGenerico {

    public SeguradoEmpresaDAO() {
        cadastro = new CadastroObjetos(SeguradoEmpresa.class);
    }

    public SeguradoEmpresa buscar(String cnpj) {
        return (SeguradoEmpresa) cadastro.buscar(cnpj);
    }

    public boolean incluir(SeguradoEmpresa segurado) {
        if (buscar(segurado.getIdUnico()) != null) {
            return false; 
        }
        cadastro.incluir(segurado, segurado.getIdUnico());
        return true;
    }

    public boolean alterar(SeguradoEmpresa segurado) {
        if (buscar(segurado.getIdUnico()) == null) {
            return false; 
        }
        cadastro.alterar(segurado, segurado.getIdUnico());
        return true;
    }

    public boolean excluir(String cnpj) {
        if (buscar(cnpj) == null) {
            return false; 
        }
        cadastro.excluir(cnpj);
        return true;
    }
}