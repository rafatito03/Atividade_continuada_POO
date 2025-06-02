package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;
import br.edu.cs.poo.ac.seguro.entidades.Segurado;

public class SeguradoPessoaDAO extends SeguradoDAO<SeguradoPessoa>  {

    public SeguradoPessoaDAO() {
        super();
    }
    @Override
    public Class<SeguradoPessoa> getClasseEntidade() {
        return SeguradoPessoa.class;
    }
    @Override
    public SeguradoPessoa buscar(String numero) {
        Segurado segurado = super.buscar(numero);
        if (segurado instanceof SeguradoPessoa) {
            return (SeguradoPessoa) segurado;
        }
        return null;
    }

    
    public boolean incluir(SeguradoPessoa segurado) {
        if (this.buscar(segurado.getIdUnico()) != null) {
            return false;
        }
        return super.incluir(segurado);
    }

    
    public boolean alterar(SeguradoPessoa segurado) {
        if (this.buscar(segurado.getIdUnico()) == null) {
            return false;
        }
        return super.alterar(segurado);
    }

    @Override
    public boolean excluir(String numero) {
        if (this.buscar(numero) == null) {
            return false;
        }
        return super.excluir(numero);
    }
}