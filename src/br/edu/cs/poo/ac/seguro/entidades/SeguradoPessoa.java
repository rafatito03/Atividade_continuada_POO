package br.edu.cs.poo.ac.seguro.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SeguradoPessoa extends Segurado implements Registro{
	private static final long serialVersionUID = 1L;
	private String cpf;
	private double renda;
	
	public SeguradoPessoa(String nome, Endereco endereco, LocalDate dataNascimento, BigDecimal bonus, String cpf, double renda) {
		super(nome, endereco, dataNascimento, bonus);
		this.cpf = cpf;
		this.renda = renda;
	}
	
	public String getIdUnico() {
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
	@Override
    public boolean isEmpresa() {
        return false;  
    }
	public String getCpf(){//coloquei aqui por causa do teste porque faz a mesma coisa do id unico
		return cpf;
	}
}
