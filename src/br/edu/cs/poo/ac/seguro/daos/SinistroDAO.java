package br.edu.cs.poo.ac.seguro.daos;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;

import java.io.Serializable;

public class SinistroDAO extends DAOGenerico {
	
	public SinistroDAO() {
		cadastro = new CadastroObjetos(Sinistro.class);
	}
	
	public Sinistro buscar(String numero) {
		return (Sinistro)cadastro.buscar(numero);
	}
	
	public boolean incluir(Sinistro sinistro) {
		if(buscar(sinistro.getNumero()) != null) return false;
		cadastro.incluir(sinistro, sinistro.getNumero());
		return true;
	}
	
	public boolean alterar(Sinistro sinistro) {
		if(buscar(sinistro.getNumero()) == null) return false;
		cadastro.alterar(sinistro, sinistro.getNumero());
		return true;
	}
	
	public boolean excluir(String numero) {
		if(buscar(numero) == null) return false;
		cadastro.excluir(numero);
		return true;
	}
	
	public Sinistro[] buscarTodos() {
		Serializable[] objetos = cadastro.buscarTodos();
		Sinistro[] sinistros = new Sinistro[objetos.length];
		for (int i = 0; i < objetos.length; i++) {
			sinistros[i] = (Sinistro) objetos[i];
		}
		return sinistros;
	}
}