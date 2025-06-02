package br.edu.cs.poo.ac.seguro.mediators;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor


public class DadosSinistro {
	private String placa;
	private LocalDateTime dataHoraSinistro;
	private String usuarioRegistro;
	private double valorSinistro;
	private int codigoTipoSinistro;
	
	

}
