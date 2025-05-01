
package br.edu.cs.poo.ac.seguro.mediators;

public class ValidadorCpfCnpj {
	public static boolean ehCnpjValido(String cnpj) {
		if (StringUtils.ehNuloOuBranco(cnpj)) return false;
		if (cnpj.length() != 14) return false;
		if (!StringUtils.temSomenteNumeros(cnpj)) return false;
		
		return true; 
	}
	public static boolean ehCpfValido(String cpf) {
		if (StringUtils.ehNuloOuBranco(cpf)) return false;
		if (!StringUtils.temSomenteNumeros(cpf)) return false;
		if (cpf.length() != 11) return false;
		
		return true; 
	}
}
