package test;

import app.NoArvore;
import app.Pessoa;
import app.PessoaFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class MatrimonioTest {

    private final Pessoa pessoaA;
    private final Pessoa pessoaB;

    private NoArvore noPessoaA;
    private NoArvore noPessoaB;

    @Before
    public void setUp() {
        noPessoaA = new NoArvore(pessoaA);
        noPessoaB = new NoArvore(pessoaB);
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Pessoa joao = PessoaFactory.criarPessoa("Joao", "Silva");
        Pessoa ana = PessoaFactory.criarPessoa("Ana", "Souza");

        return Arrays.asList(new Object[][] {
                {joao, ana}
        });
    }

    public MatrimonioTest(Pessoa pessoaA, Pessoa pessoaB) {
        this.pessoaA = pessoaA;
        this.pessoaB = pessoaB;
    }

    @Test
    @Category(Funcional.class)
    public void matrimonioTest() {
        noPessoaA.casarCom(noPessoaB);
        assertEquals(noPessoaA.getConjuge(), noPessoaB);
        assertEquals(noPessoaB.getConjuge(), noPessoaA);
    }
}
