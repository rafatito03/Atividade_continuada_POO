package br.edu.cs.poo.ac.seguro.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class SeguradoEmpresa extends Segurado implements Serializable {
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
	
	public String getCnpj() {
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
	
}
