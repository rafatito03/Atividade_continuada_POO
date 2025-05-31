package br.edu.cs.poo.ac.seguro.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Apolice implements Registro{
    private static final long serialVersionUID = 1L;

    private String numero;
    private Veiculo veiculo;
    private BigDecimal valorFranquia;
    private BigDecimal valorPremio;
    private BigDecimal valorMaximoSegurado;
    private LocalDate dataInicioVigencia;
    private LocalDate dataFimVigencia;

    public Apolice(String numero,
                   Veiculo veiculo,
                   BigDecimal valorFranquia,
                   BigDecimal valorPremio,
                   BigDecimal valorMaximoSegurado,
                   LocalDate dataInicioVigencia) {

        this(numero,
             veiculo,
             valorFranquia,
             valorPremio,
             valorMaximoSegurado,
             dataInicioVigencia,
             null);

    }
    @Override
    public String getIdUnico() {
    	return numero;
    }
}
