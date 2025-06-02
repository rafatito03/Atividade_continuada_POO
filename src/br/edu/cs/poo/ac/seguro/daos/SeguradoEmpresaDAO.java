package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;
import br.edu.cs.poo.ac.seguro.entidades.Segurado;

public class SeguradoEmpresaDAO extends SeguradoDAO<SeguradoEmpresa> {

    public SeguradoEmpresaDAO() {
        super();
    }
    @Override
    public Class<SeguradoEmpresa> getClasseEntidade() {
        return SeguradoEmpresa.class;
    }
    public SeguradoEmpresa buscar(String numero) {
        Segurado segurado = super.buscar(numero);
        if (segurado instanceof SeguradoEmpresa) {
            return (SeguradoEmpresa) segurado;
        }
        return null;
    }

    
    public boolean incluir(SeguradoEmpresa segurado) {
        if (this.buscar(segurado.getIdUnico()) != null) {
            return false;
        }
        return super.incluir(segurado);
    }

    
    public boolean alterar(SeguradoEmpresa segurado) {
        if (this.buscar(segurado.getIdUnico()) == null) {
            return false;
        }
        return super.alterar(segurado);
    }

    
    public boolean excluir(String numero) {
        if (this.buscar(numero) == null) {
            return false;
        }
        return super.excluir(numero);
    }
}