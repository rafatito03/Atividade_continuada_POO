package br.edu.cs.poo.ac.seguro.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class SeguradoEmpresa extends Segurado implements Serializable {
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
	
	public boolean getEhLocadoraDeVeiculos() {
		return ehLocadoraDeVeiculos;
	}
	
	public LocalDate getDataAbertura() {
		return getDataCriacao();
	}
	
	public void SetDataAbertura(LocalDate dataAbertura) {
		setDataCriacao(dataAbertura);
	}
	
}
