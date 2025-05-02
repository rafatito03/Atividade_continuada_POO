package br.edu.cs.poo.ac.seguro.testes;

import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public abstract class TesteAbstrato {
	protected CadastroObjetos cadastro;

	protected abstract Class<?> getClasse();

	@BeforeEach
	public void setUp() {
		String sep = File.separator;
		FileUtils.limparDiretorio("." + sep + getClasse().getSimpleName());
		cadastro = new CadastroObjetos(getClasse());
	}
}
