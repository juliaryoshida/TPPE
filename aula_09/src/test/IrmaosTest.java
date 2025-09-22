package test;

import app.NoArvore;
import app.Pessoa;
import app.PessoaFactory;
import exceptions.NaoTemIrmaoException;
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
public class IrmaosTest {
    private final Pessoa pessoaMae;
    private final Pessoa pessoaPai;
    private final Pessoa pessoaFilhoA;
    private final Pessoa pessoaFilhoB;
    private final Pessoa pessoaFilhoC;

    private NoArvore mae;
    private NoArvore pai;
    private NoArvore filhoA;
    private NoArvore filhoB;
    private NoArvore filhoC;

    @Before
    public void setUp() {
        mae = new NoArvore(pessoaMae);
        pai = new NoArvore(pessoaPai);
        filhoA = new NoArvore(pessoaFilhoA);
        filhoB = new NoArvore(pessoaFilhoB);
        filhoC = new NoArvore(pessoaFilhoC);
        mae.casarCom(pai);
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Pessoa joao = PessoaFactory.criarPessoa("Joao", "Silva");
        Pessoa ana = PessoaFactory.criarPessoa("Ana", "Souza");
        Pessoa maria = PessoaFactory.criarPessoa("Maria", "Oliveira");
        Pessoa mateus = PessoaFactory.criarPessoa("Mateus", "Oliveira");
        Pessoa gabriel = PessoaFactory.criarPessoa("Gabriel", "Oliveira");

        return Arrays.asList(new Object[][] {
                {joao, ana, maria, mateus, gabriel}
        });
    }

    public IrmaosTest(Pessoa pessoaMae, Pessoa pessoaPai, Pessoa pessoaFilhoA, Pessoa pessoaFilhoB, Pessoa pessoaFilhoC) {
        this.pessoaMae = pessoaMae;
        this.pessoaPai = pessoaPai;
        this.pessoaFilhoA = pessoaFilhoA;
        this.pessoaFilhoB = pessoaFilhoB;
        this.pessoaFilhoC = pessoaFilhoC;
    }

    @Test
    @Category(Funcional.class)
    public void irmaosTest() throws NaoTemIrmaoException {
        mae.adicionarFilho(filhoA, pai, mae);
        mae.adicionarFilho(filhoB, pai, mae);
        mae.adicionarFilho(filhoC, pai, mae);

        assertEquals(2, filhoA.getIrmaos().size());
        assertTrue(filhoA.getIrmaos().contains(filhoB.getPessoa()));
        assertTrue(filhoA.getIrmaos().contains(filhoC.getPessoa()));

        assertEquals(2, filhoB.getIrmaos().size());
        assertTrue(filhoB.getIrmaos().contains(filhoA.getPessoa()));
        assertTrue(filhoB.getIrmaos().contains(filhoC.getPessoa()));

        assertEquals(2, filhoC.getIrmaos().size());
        assertTrue(filhoC.getIrmaos().contains(filhoA.getPessoa()));
        assertTrue(filhoC.getIrmaos().contains(filhoB.getPessoa()));
    }

    @Test(expected = NaoTemIrmaoException.class)
    @Category(Excecao.class)
    public void naoTemIrmaosTest() throws NaoTemIrmaoException {
        pai.adicionarFilho(filhoA, pai, mae);
        filhoA.getIrmaos();
    }
}
