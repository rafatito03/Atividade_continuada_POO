package br.edu.cs.poo.ac.seguro.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class Sinistro implements Registro {
	private static final long serialVersionUID = 1L;
	private String numero; // Não pegar no construtor
	private Veiculo veiculo;
	private LocalDateTime dataHoraSinistro;
	private LocalDateTime dataHoraRegistro;
	private String usuarioRegistro;
	private BigDecimal valorSinistro;
	private TipoSinistro tipo;
	private int sequencial;//n construtor
	private String numeroApolice;//n construtor
	
	public Sinistro(Veiculo veiculo, LocalDateTime dataHoraSinistro,
            LocalDateTime dataHoraRegistro, String usuarioRegistro,
            BigDecimal valorSinistro, TipoSinistro tipo) {
				this.veiculo = veiculo;
				this.dataHoraSinistro = dataHoraSinistro;
				this.dataHoraRegistro = dataHoraRegistro;
				this.usuarioRegistro = usuarioRegistro;
				this.valorSinistro = valorSinistro;
				this.tipo = tipo;

}
	 public Sinistro(String numero, Veiculo veiculo, LocalDateTime dataHoraSinistro, //novo construtor para o teste de sinistro Mediator
             LocalDateTime dataHoraRegistro, String usuarioRegistro,
             BigDecimal valorSinistro, TipoSinistro tipo) {
				 this.numero = numero;
				 this.veiculo = veiculo;
				 this.dataHoraSinistro = dataHoraSinistro;
				 this.dataHoraRegistro = dataHoraRegistro;
				 this.usuarioRegistro = usuarioRegistro;
				 this.valorSinistro = valorSinistro;
				 this.tipo = tipo;
}

	public String getIdUnico() {
		return getNumero();
	}
}
