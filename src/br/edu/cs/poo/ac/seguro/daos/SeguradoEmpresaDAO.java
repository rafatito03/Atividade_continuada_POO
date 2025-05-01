package br.edu.cs.poo.ac.seguro.daos;

import java.io.Serializable;

import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;

public class SeguradoEmpresaDAO extends DAOGenerico implements Serializable {

	public SeguradoEmpresa buscar(String cnpj) {
		return null;
	}
	public boolean incluir(SeguradoEmpresa segurado) {
		return false;
	}
	public boolean alterar(SeguradoEmpresa segurado) {
		return false;
	}
	public boolean excluir(String cnpj) {
		return false;
	}
}