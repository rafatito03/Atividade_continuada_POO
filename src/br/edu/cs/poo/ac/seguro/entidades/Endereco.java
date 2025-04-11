package br.edu.cs.poo.ac.seguro.entidades;

public class Endereco {
	private String logradouro;
	private String cep;
	private String numero;
	private String complemento;
	private String pais;
	private String estado;
	private String cidade;
	public Endereco(String logradouro, String cep, String numero, String complemento, String pais, String estado, String cidade) {
	    setLogradouro(logradouro);
	    setCep(cep);
	    setNumero(numero);
	    setComplemento(complemento);
	    setPais(pais);
	    setEstado(estado);
	    setCidade(cidade);
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
