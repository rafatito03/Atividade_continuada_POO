package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.Apolice;
import br.edu.cs.poo.ac.seguro.entidades.Registro;

public class ApoliceDAO extends DAOGenerico<Apolice> {

    public ApoliceDAO() {
        super();
    }

    @Override
    public Class<Apolice> getClasseEntidade() {
        return Apolice.class;
    }

    @Override
    public Apolice buscar(String numero) {
        return super.buscar(numero);
    }

    @Override
    public boolean incluir(Apolice apolice) {
        if (this.buscar(apolice.getNumero()) != null) {
            return false;
        }
        return super.incluir(apolice);
    }

    @Override
    public boolean alterar(Apolice apolice) {
        if (this.buscar(apolice.getNumero()) == null) {
            return false;
        }
        return super.alterar(apolice);
    }

    @Override
    public boolean excluir(String numero) {
        if (this.buscar(numero) == null) {
            return false;
        }
        return super.excluir(numero);
    }
    public Apolice[] buscarTodos() {
        Registro[] registros = super.buscarTodos(); 
        Apolice[] apolices = new Apolice[registros.length];
        for (int i = 0; i < registros.length; i++) {
            apolices[i] = (Apolice) registros[i]; 
        }
        return apolices;
    }
}