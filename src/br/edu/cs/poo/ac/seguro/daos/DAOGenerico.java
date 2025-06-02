package br.edu.cs.poo.ac.seguro.daos;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.edu.cs.poo.ac.seguro.entidades.Registro;

public abstract class DAOGenerico<T extends Registro> {
    private CadastroObjetos cadastro;
    
    
    public DAOGenerico() {
        this.cadastro = new CadastroObjetos(getClasseEntidade());
    }
    
    public abstract Class<T> getClasseEntidade();
    
    
    public boolean incluir(T objeto) {
    	try {
            cadastro.incluir(objeto, objeto.getIdUnico());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
         
    }
    
    public boolean alterar(T objeto) {
        try {
            cadastro.alterar(objeto, objeto.getIdUnico());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @SuppressWarnings("unchecked")
    public T buscar(String id) {
        return (T) cadastro.buscar(id);
    }
    
    
    public Registro[] buscarTodos() {
        Serializable[] objetosSerializados = cadastro.buscarTodos(); 
        if (objetosSerializados == null) {
            return new Registro[0]; //
        }
        Registro[] registros = new Registro[objetosSerializados.length];//isso aqui é um casting de serializable pra registro
        for (int i = 0; i < objetosSerializados.length; i++) {
            if (objetosSerializados[i] instanceof Registro) {
                registros[i] = (Registro) objetosSerializados[i];
            } else {
                registros[i] = (Registro) objetosSerializados[i];
            }
        }
        return registros;
    }
    
    public boolean excluir(String id) {
        try {
            cadastro.excluir(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}