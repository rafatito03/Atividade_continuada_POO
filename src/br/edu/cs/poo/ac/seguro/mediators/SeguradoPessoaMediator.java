package br.edu.cs.poo.ac.seguro.mediators;


import br.edu.cs.poo.ac.seguro.daos.SeguradoPessoaDAO;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;
import br.edu.cs.poo.ac.seguro.mediators.StringUtils;

public class SeguradoPessoaMediator {
	
	SeguradoMediator seguradoMediator = new SeguradoMediator();
	SeguradoPessoaDAO seguradoPessoaDAO = new SeguradoPessoaDAO();
	
	SeguradoPessoaMediator instancia;
	
	public String validarCpf(String cpf) {
		return null;
	}
	public String validarRenda(double renda) {
		return null;
	}
	public String incluirSeguradoPessoa(SeguradoPessoa seg) {
		// Validação CPF
		if(StringUtils.ehNuloOuBranco(seg.getCpf())) return null;
		if(!StringUtils.temSomenteNumeros(seg.getNome())) return null;
		
		// Validação 
		if(StringUtils.ehNuloOuBranco(seg.getNome())) return null;
		if(StringUtils.temSomenteNumeros(seg.getNome())) return null;
		if(StringUtils.ehNuloOuBranco(seg.getEndereco().getCep())) return null;
		if(StringUtils.ehNuloOuBranco(seg.getEndereco().getCidade())) return null;
		if(StringUtils.ehNuloOuBranco(seg.getEndereco().getComplemento())) return null;
		if(StringUtils.ehNuloOuBranco(seg.getEndereco().getEstado())) return null;
		if(StringUtils.ehNuloOuBranco(seg.getEndereco().getLogradouro())) return null;
		if(StringUtils.ehNuloOuBranco(seg.getEndereco().getPais())) return null;
		
		
		validarSeguradoPessoa(seg);
		
		return "";
	}
	public String alterarSeguradoPessoa(SeguradoPessoa seg) {
		validarSeguradoPessoa(seg); // fazer a atribuição correta
		return null;
	}
	public String excluirSeguradoPessoa(String cpf) {
		return null;
	}
	public SeguradoPessoa buscarSeguradoPessoa(String cpf) {
		return null;
	}
	public String validarSeguradoPessoa(SeguradoPessoa seg) {
		return null;
	}
	
	public SeguradoPessoaMediator getInstancia() {
		return instancia;
	}
}