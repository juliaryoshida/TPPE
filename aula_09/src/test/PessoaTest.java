package test;

import app.Pessoa;
import app.PessoaFactory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PessoaTest {
    private final String nome;
    private final String sobrenome;

    public PessoaTest(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    @Parameterized.Parameters(name = "{index}: Pessoa({0} {1})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"João", "Silva"},
                {"Ana", "Souza"},
                {"Maria", "Oliveira"}
        });
    }

    @Test
    @Category(Funcional.class)
    public void testCriarPessoa() {
        Pessoa p = PessoaFactory.criarPessoa(nome, sobrenome);
        assertEquals(nome + sobrenome, p.getNome() + p.getSobrenome());
    }
}