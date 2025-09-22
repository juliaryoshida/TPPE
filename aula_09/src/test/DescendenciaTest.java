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

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class DescendenciaTest {

    private final Pessoa pessoaMae;
    private final Pessoa pessoaPai;
    private final Pessoa pessoaFilho;

    private NoArvore mae;
    private NoArvore pai;
    private NoArvore filho;

    @Before
    public void setUp() {
        mae = new NoArvore(pessoaMae);
        pai = new NoArvore(pessoaPai);
        filho = new NoArvore(pessoaFilho);
        mae.casarCom(pai);
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Pessoa joao = PessoaFactory.criarPessoa("Joao", "Silva");
        Pessoa ana = PessoaFactory.criarPessoa("Ana", "Souza");
        Pessoa maria = PessoaFactory.criarPessoa("Maria", "Oliveira");

        Pessoa carlos = PessoaFactory.criarPessoa("Carlos", "Silva");
        Pessoa joana = PessoaFactory.criarPessoa("Joana", "Souza");
        Pessoa pedro = PessoaFactory.criarPessoa("Pedro", "Silva");

        return Arrays.asList(new Object[][] {
                {ana, joao, maria},
                {joana, carlos, pedro}
        });
    }

    public DescendenciaTest(Pessoa pessoaMae, Pessoa pessoaPai, Pessoa pessoaFilho) {
        this.pessoaMae = pessoaMae;
        this.pessoaPai = pessoaPai;
        this.pessoaFilho = pessoaFilho;
    }

    @Test
    @Category(Funcional.class)
    public void adicionarFilhoTest() {
        pai.adicionarFilho(filho, pai, mae);
        assertTrue(pai.getFilhos().contains(filho));
        assertTrue(mae.getFilhos().contains(filho));
        assertEquals(pai, filho.getPai());
        assertEquals(mae, filho.getMae());
    }
}
