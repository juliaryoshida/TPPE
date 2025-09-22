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
public class TiosTest {
    private final Pessoa pessoaAvoMA;
    private final Pessoa pessoaAvoMB;
    private final Pessoa pessoaAvoPA;
    private final Pessoa pessoaAvoPB;
    private final Pessoa pessoaMae;
    private final Pessoa pessoaPai;
    private final Pessoa pessoaTioPaterno;
    private final Pessoa pessoaTiaMaterna;
    private final Pessoa pessoaFilho;

    private NoArvore avoMA;
    private NoArvore avoMB;
    private NoArvore avoPA;
    private NoArvore avoPB;
    private NoArvore mae;
    private NoArvore pai;
    private NoArvore tioPaterno;
    private NoArvore tiaMaterna;
    private NoArvore filho;

    @Before
    public void setUp() {
        avoMA = new NoArvore(pessoaAvoMA);
        avoMB = new NoArvore(pessoaAvoMB);
        avoPA = new NoArvore(pessoaAvoPA);
        avoPB = new NoArvore(pessoaAvoPB);
        mae = new NoArvore(pessoaMae);
        pai = new NoArvore(pessoaPai);
        tioPaterno = new NoArvore(pessoaTioPaterno);
        tiaMaterna = new NoArvore(pessoaTiaMaterna);
        filho = new NoArvore(pessoaFilho);
        avoMA.casarCom(avoMB);
        avoPA.casarCom(avoPB);
        mae.casarCom(pai);
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        Pessoa joao = PessoaFactory.criarPessoa("Joao", "Silva");
        Pessoa ana = PessoaFactory.criarPessoa("Ana", "Souza");
        Pessoa gabriel = PessoaFactory.criarPessoa("Gabriel", "Oliveira");
        Pessoa karla = PessoaFactory.criarPessoa("Karla", "Oliveira");
        Pessoa maria = PessoaFactory.criarPessoa("Maria", "Oliveira");
        Pessoa mateus = PessoaFactory.criarPessoa("Mateus", "Oliveira");
        Pessoa paulo = PessoaFactory.criarPessoa("Paulo", "Souza");
        Pessoa giovana = PessoaFactory.criarPessoa("Giovana", "Souza");
        Pessoa otavio = PessoaFactory.criarPessoa("Otavio", "Oliveira");

        return Arrays.asList(new Object[][] {
                {joao, ana, maria, mateus, paulo, giovana, gabriel, karla, otavio}
        });
    }

    public TiosTest(Pessoa pessoaAvoMA, Pessoa pessoaAvoMB, Pessoa pessoaAvoPA, Pessoa pessoaAvoPB, Pessoa pessoaMae, Pessoa pessoaPai, Pessoa pessoaTioPaterno, Pessoa pessoaTiaMaterna, Pessoa pessoaFilho) {
        this.pessoaAvoMA = pessoaAvoMA;
        this.pessoaAvoMB = pessoaAvoMB;
        this.pessoaAvoPA = pessoaAvoPA;
        this.pessoaAvoPB = pessoaAvoPB;
        this.pessoaMae = pessoaMae;
        this.pessoaPai = pessoaPai;
        this.pessoaTioPaterno = pessoaTioPaterno;
        this.pessoaTiaMaterna = pessoaTiaMaterna;
        this.pessoaFilho = pessoaFilho;
    }

    @Test
    @Category(Funcional.class)
    public void tiosTest() throws NaoTemIrmaoException {
        avoMA.adicionarFilho(mae, avoMA, avoMB);
        avoMA.adicionarFilho(tiaMaterna, avoMA, avoMB);
        avoPA.adicionarFilho(pai, avoPA, avoPB);
        avoPA.adicionarFilho(tioPaterno, avoPA, avoPB);
        mae.adicionarFilho(filho, pai, mae);

        assertTrue(filho.getTios().contains(tiaMaterna.getPessoa()));
        assertTrue(filho.getTios().contains(tioPaterno.getPessoa()));
        assertEquals(2, filho.getTios().size());
    }

    @Test(expected = NaoTemIrmaoException.class)
    @Category(Funcional.class)
    public void tiosNaoExistemTest() throws NaoTemIrmaoException {
        avoMA.adicionarFilho(mae, avoMA, avoMB);
        avoPA.adicionarFilho(pai, avoPA, avoPB);
        mae.adicionarFilho(filho, pai, mae);

        filho.getTios();
    }
}
