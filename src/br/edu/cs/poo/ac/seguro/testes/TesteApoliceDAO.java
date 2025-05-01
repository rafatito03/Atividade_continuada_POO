package br.edu.cs.poo.ac.seguro.testes;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.daos.ApoliceDAO;
import br.edu.cs.poo.ac.seguro.entidades.Apolice;
import br.edu.cs.poo.ac.seguro.entidades.Veiculo;

public class TesteApoliceDAO extends TesteDAO {
	private ApoliceDAO dao = new ApoliceDAO();

	@Override
	protected Class<Apolice> getClasse() {
		return Apolice.class;
	}

	private Apolice criarApolice(String numero) {
		Veiculo veiculo = new Veiculo("ABC1234", 2020, null, null, null);
		Apolice apolice = new Apolice(
			numero, veiculo,
			new BigDecimal("1000.00"),
			new BigDecimal("500.00"),
			new BigDecimal("20000.00"), null, null
		);
		apolice.setNumero(numero);
		return apolice;
	}

	@Test
	public void teste01_buscarRetornaNull() {
		Apolice apolice = criarApolice("123");
		Apolice resultado = dao.buscar(apolice.getNumero());
		Assertions.assertNull(resultado);
	}

	@Test
	public void teste02_incluirRetornaFalse() {
	    Apolice apolice = criarApolice("456");
	    dao.incluir(apolice);
	    boolean resultado = dao.incluir(apolice);
	    Assertions.assertFalse(resultado);
	}


	@Test
	public void teste03_alterarRetornaFalse() {
		Apolice apolice = criarApolice("789");
		boolean resultado = dao.alterar(apolice);
		Assertions.assertFalse(resultado);
	}

	@Test
	public void teste04_excluirRetornaFalse() {
		Apolice apolice = criarApolice("999");
		boolean resultado = dao.excluir(apolice.getNumero());
		Assertions.assertFalse(resultado);
	}
}
