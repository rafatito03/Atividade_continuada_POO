package br.edu.cs.poo.ac.seguro.testes;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import org.junit.jupiter.api.BeforeEach;

public abstract class TesteMediator extends TesteAbstrato {

    @BeforeEach
    public void setUp() {
        FileUtils.limparDiretorio("./" + getClasse().getSimpleName());
        cadastro = new CadastroObjetos(getClasse());
    }
}
