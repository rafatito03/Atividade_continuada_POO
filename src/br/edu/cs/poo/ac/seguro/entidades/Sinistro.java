package br.edu.cs.poo.ac.seguro.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Sinistro implements Serializable {
	private static final long serialVersionUID = 1L;
	private String numero; // Não pegar no construtor
	private Veiculo veiculo;
	private LocalDateTime dataHoraSinistro;
	private LocalDateTime dataHoraRegistro;
	private String usuarioRegistro;
	private BigDecimal valorSinistro;
	private TipoSinistro tipo;

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getNumero() {
		return numero;
	}
}
