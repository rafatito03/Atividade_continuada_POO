package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cs.poo.ac.seguro.entidades.Veiculo;

public class VeiculoDAO extends DAOGenerico<Veiculo> {

    public VeiculoDAO() {
        super();
    }

    @Override
    public Class<Veiculo> getClasseEntidade() {
        return Veiculo.class;
    }

    @Override
    public Veiculo buscar(String placa) {
        return super.buscar(placa);
    }

    @Override
    public boolean incluir(Veiculo veiculo) {
        if (this.buscar(veiculo.getPlaca()) != null) {
            return false;
        }
        return super.incluir(veiculo);
    }

    @Override
    public boolean alterar(Veiculo veiculo) {
        if (this.buscar(veiculo.getPlaca()) == null) {
            return false;
        }
        return super.alterar(veiculo);
    }

    @Override
    public boolean excluir(String placa) {
        if (this.buscar(placa) == null) {
            return false;
        }
        return super.excluir(placa);
    }
}