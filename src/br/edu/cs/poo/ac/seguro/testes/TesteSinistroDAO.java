package br.edu.cs.poo.ac.seguro.testes;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.cs.poo.ac.seguro.daos.SinistroDAO;
import br.edu.cs.poo.ac.seguro.entidades.CategoriaVeiculo;
import br.edu.cs.poo.ac.seguro.entidades.Sinistro;
import br.edu.cs.poo.ac.seguro.entidades.TipoSinistro;
import br.edu.cs.poo.ac.seguro.entidades.Veiculo;

public class TesteSinistroDAO extends TesteDAO {
	
	private Veiculo veiculoTeste = new Veiculo("00000000", 2000, null, null, CategoriaVeiculo.BASICO);
	private Sinistro sinistroTeste = new Sinistro("000", veiculoTeste, LocalDateTime.now(), LocalDateTime.now(), "fil", new BigDecimal(11111), TipoSinistro.getTipoSinistro(1));
	
	private SinistroDAO dao = new SinistroDAO();
	
	protected Class getClasse() {
		return Sinistro.class;
	}
	
	@Test
	public void teste01() {
		cadastro.incluir(sinistroTeste, sinistroTeste.getNumero());
		Sinistro si = dao.buscar(sinistroTeste.getNumero());
		Assertions.assertNotNull(si); 
	}
	
	@Test
	public void teste02() {
		cadastro.incluir(sinistroTeste, sinistroTeste.getNumero());
		Sinistro si = dao.buscar("10000000");
		Assertions.assertNull(si);
	}
	
	@Test
	public void teste03() {
		cadastro.incluir(sinistroTeste, sinistroTeste.getNumero());
		boolean ret = dao.excluir(sinistroTeste.getNumero());
		Assertions.assertTrue(ret);
	}
	
	@Test
	public void teste04() {
		cadastro.incluir(sinistroTeste, sinistroTeste.getNumero());
		boolean ret = dao.excluir("31000000");
		Assertions.assertFalse(ret);
	}
	
	@Test
	public void teste05() {
		boolean ret = dao.incluir(sinistroTeste);		
		Assertions.assertTrue(ret);
		
		Sinistro si = dao.buscar(sinistroTeste.getNumero());
		Assertions.assertNotNull(si);		
	}
	
	@Test
	public void teste06() {
		cadastro.incluir(sinistroTeste, sinistroTeste.getNumero());
		boolean ret = dao.incluir(sinistroTeste);
		Assertions.assertFalse(ret);
	}
	
	@Test
	public void teste07() {
		boolean ret = dao.alterar(sinistroTeste);		
		Assertions.assertFalse(ret);
		Sinistro si = dao.buscar(sinistroTeste.getNumero());
		Assertions.assertNull(si);		
	}
	
	@Test
	public void teste08() {
		cadastro.incluir(sinistroTeste, sinistroTeste.getNumero());
		Sinistro si = new Sinistro("000", veiculoTeste, LocalDateTime.now(), LocalDateTime.now(), "fil", new BigDecimal(22222), TipoSinistro.getTipoSinistro(1));
		boolean ret = dao.alterar(si);
		Assertions.assertTrue(ret);
	}
}
