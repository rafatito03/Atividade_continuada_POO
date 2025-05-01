package br.edu.cs.poo.ac.seguro.mediators;

public class StringUtils {
	
	private StringUtils() {}
	
	public static boolean ehNuloOuBranco(String str) {
		if(str==null) return true;
		if(str.trim().isEmpty()) return true;
		return false;
	}
	
    public static boolean temSomenteNumeros(String input) {
    	if(ehNuloOuBranco(input)) return false;
        return input.matches("\\d+"); // Regex para um ou mais decimais
    }
}