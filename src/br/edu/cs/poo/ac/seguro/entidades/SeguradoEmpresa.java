package br.edu.cs.poo.ac.seguro.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SeguradoEmpresa extends Segurado implements Registro {
	private static final long serialVersionUID = 1L;
	private String cnpj;
	private double faturamento;
	private boolean ehLocadoraDeVeiculos;
	
	
	public SeguradoEmpresa(String nome, Endereco endereco, LocalDate dataAbertura, BigDecimal bonus, String cnpj, double faturamento, boolean ehLocadoraDeVeiculos) {
		super(nome, endereco, dataAbertura, bonus);
		this.cnpj = cnpj;
		this.faturamento = faturamento;
		this.ehLocadoraDeVeiculos = ehLocadoraDeVeiculos;
	}
	@Override
	public String getIdUnico() {
		return cnpj;
	}
	
	public double getFaturamento() {
		return faturamento;
	}
	
	public boolean isLocadoraDeVeiculos() {
		return ehLocadoraDeVeiculos;
	}
	
	public LocalDate getDataAbertura() {
		return getDataCriacao();
	}
	
	public void setDataAbertura(LocalDate dataAbertura) {
		setDataCriacao(dataAbertura);
	}
	@Override
	public boolean isEmpresa() {
        return true;  
    }
	public String getCnpj(){//coloquei aqui por causa do teste porque faz a mesma coisa do id unico
		return cnpj;
	}
	
}
