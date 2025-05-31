package br.edu.cs.poo.ac.seguro.entidades;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor

public class Veiculo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String placa;
	private int ano;
	private Segurado proprietario;
	private CategoriaVeiculo categoria;
}
