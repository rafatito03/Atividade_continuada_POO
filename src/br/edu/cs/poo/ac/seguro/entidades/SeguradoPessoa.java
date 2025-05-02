package br.edu.cs.poo.ac.seguro.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SeguradoPessoa extends Segurado {
	private static final long serialVersionUID = 1L;
	private String cpf;
	private double renda;
	
	public SeguradoPessoa(String nome, Endereco endereco, LocalDate dataNascimento, BigDecimal bonus, String cpf, double renda) {
		super(nome, endereco, dataNascimento, bonus);
		this.cpf = cpf;
		this.renda = renda;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public double getRenda() {
		return renda;
	}
	
	public LocalDate getDataNascimento() {
		return getDataCriacao();
	}
	
	public void setDataNascimento(LocalDate dataAbertura) {
		setDataCriacao(dataAbertura);
	}
}
