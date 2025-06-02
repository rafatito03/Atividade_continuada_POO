package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.Sinistro;
import br.edu.cs.poo.ac.seguro.entidades.Registro;
import java.util.Arrays;

public class SinistroDAO extends DAOGenerico<Sinistro> {

    public SinistroDAO() {
        super();
    }

    @Override
    public Class<Sinistro> getClasseEntidade() {
        return Sinistro.class;
    }

    @Override
    public Sinistro buscar(String numero) {
        return super.buscar(numero);
    }

    @Override
    public boolean incluir(Sinistro sinistro) {
        if (this.buscar(sinistro.getNumero()) != null) {
            return false;
        }
        return super.incluir(sinistro);
    }

    @Override
    public boolean alterar(Sinistro sinistro) {
        if (this.buscar(sinistro.getNumero()) == null) {
            return false;
        }
        return super.alterar(sinistro);
    }

    @Override
    public boolean excluir(String numero) {
        if (this.buscar(numero) == null) {
            return false;
        }
        return super.excluir(numero);
    }

    public Sinistro[] buscarTodos() {
        Registro[] registros = super.buscarTodos();
        Sinistro[] sinistros = new Sinistro[registros.length];
        for (int i = 0; i < registros.length; i++) {
            sinistros[i] = (Sinistro) registros[i];
        }
        return sinistros;
    }

    public Sinistro[] listarPorApolice(String numeroApoliceParaFiltrar) {
        Sinistro[] todosOsSinistros = this.buscarTodos(); 
        if (todosOsSinistros == null || numeroApoliceParaFiltrar == null) {
            return new Sinistro[0];
        }
        return Arrays.stream(todosOsSinistros)
                     .filter(sinistro -> sinistro.getNumeroApolice() != null && 
                                         sinistro.getNumeroApolice().equals(numeroApoliceParaFiltrar))
                     .toArray(Sinistro[]::new);
    }
}