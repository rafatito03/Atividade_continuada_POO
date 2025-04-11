package br.edu.cs.poo.ac.seguro.entidades;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Apolice {
	private Veiculo veiculo;
	private BigDecimal valorFranquia;
	private BigDecimal valorPremio;
	private BigDecimal valorMaximoSegurado;
	
	public Apolice(Veiculo veiculo, BigDecimal valorFranquia, BigDecimal valorPremio,BigDecimal valorMaximoSegurado) {
		this.veiculo = veiculo;
		this.valorFranquia = valorPremio;
		this.valorMaximoSegurado = valorMaximoSegurado;
	}
	
}
