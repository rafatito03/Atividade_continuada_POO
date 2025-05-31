package br.edu.cs.poo.ac.seguro.daos;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;

public class SeguradoPessoaDAO extends DAOGenerico {
	
	public SeguradoPessoaDAO() {
		cadastro = new CadastroObjetos(SeguradoPessoa.class);
	}
	
	public SeguradoPessoa buscar(String cpf) {
		return (SeguradoPessoa)cadastro.buscar(cpf);
	}
	
	public boolean incluir(SeguradoPessoa segurado) {
		if (buscar(segurado.getIdUnico()) != null) {
			return false;
		} else {
			cadastro.incluir(segurado, segurado.getIdUnico());
			return true;
		}
	}
	public boolean alterar(SeguradoPessoa segurado) {
		if (buscar(segurado.getIdUnico()) == null) {
			return false;
		} else {
			cadastro.alterar(segurado, segurado.getIdUnico());
			return true;
		}
	}
	public boolean excluir(String cpf) {
		if (buscar(cpf) == null) {
			return false;
		} else {
			cadastro.excluir(cpf);
			return true;
		}
	}
	
}