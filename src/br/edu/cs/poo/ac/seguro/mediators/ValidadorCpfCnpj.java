
package br.edu.cs.poo.ac.seguro.mediators;

public class ValidadorCpfCnpj {
	private static final int[] PESO_CPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	
	private static boolean validarDigitosCnpj(String cnpj) {
	    if (cnpj == null || cnpj.length() != 14) {
	        return false;
	    }

	    int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
	    int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

	    int sum = 0;
	    for (int i = 0; i < 12; i++) {
	        sum += (cnpj.charAt(i) - '0') * weights1[i];
	    }
	    int digit1 = (sum % 11 < 2) ? 0 : 11 - (sum % 11);

	    sum = 0;
	    for (int i = 0; i < 12; i++) {
	        sum += (cnpj.charAt(i) - '0') * weights2[i];
	    }
	    sum += digit1 * weights2[12];
	    int digit2 = (sum % 11 < 2) ? 0 : 11 - (sum % 11);

	    return (digit1 == Character.getNumericValue(cnpj.charAt(12))) &&
	           (digit2 == Character.getNumericValue(cnpj.charAt(13)));
	}
	
	public static String ehCnpjValido(String cnpj) {
		if (StringUtils.ehNuloOuBranco(cnpj)) return "CNPJ deve ser informado";
		if (cnpj.length() != 14) return "CNPJ deve ter 14 caracteres";
		if (!StringUtils.temSomenteNumeros(cnpj)) return "CNPJ deve conter apenas números";
		if (!validarDigitosCnpj(cnpj)) return "CNPJ com dígito inválido";
        
		return null; 
	}
	
	
	public static boolean ehCpfValido(String cpf) {
		
        cpf = limparNumeros(cpf);
        
        if (cpf == null || cpf.length() != 11) return false;
        if (todosDigitosIguais(cpf)) return false;

        String digitos = cpf.substring(9);
        String calculado = calcularDigito(cpf.substring(0, 9), PESO_CPF);
        calculado += calcularDigito(cpf.substring(0, 10), PESO_CPF);
        
        return digitos.equals(calculado);
    }

    private static String limparNumeros(String numero) {
        if (numero == null) return null;
        return numero.replaceAll("[^0-9]", "");
    }

    private static boolean todosDigitosIguais(String numero) {
        char primeiro = numero.charAt(0);
        return numero.chars().allMatch(c -> c == primeiro);
    }

    private static String calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            int digito = Integer.parseInt(str.substring(i, i + 1));
            soma += digito * peso[peso.length - str.length() + i];
        }
        
        soma = 11 - (soma % 11);
        return soma > 9 ? "0" : String.valueOf(soma);
    }
}
