package test;

import app.NoArvore;
import app.Pessoa;
import app.PessoaFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertNull;

@RunWith(Parameterized.class)
public class RemoverTest {
    private final Pessoa pessoaAvoA;
    private final Pessoa pessoaAvoB;
    private final Pessoa pessoaPai;
    private final Pessoa pessoaMae;
    private final Pessoa pessoaFilho;
    private final Pessoa pessoaConjuge;
    private final Pessoa pessoaNeto;

    private NoArvore avoA;
    private NoArvore avoB;
    private NoArvore mae;
    private NoArvore pai;
    private NoArvore filho;
    private NoArvore conjuge;
    private NoArvore neto;

    @Before
    public void setUp() {
        avoA = new NoArvore(pessoaAvoA);
        avoB = new NoArvore(pessoaAvoB);
        mae = new NoArvore(pessoaMae);
        pai = new NoArvore(pessoaPai);
        filho = new NoArvore(pessoaFilho);
        conjuge = new NoArvore(pessoaConjuge);
        neto = new NoArvore(pessoaNeto);

        avoA.casarCom(avoB);
        mae.casarCom(pai);
        filho.casarCom(conjuge);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        Pessoa joao = PessoaFactory.criarPessoa("Joao", "Silva");
        Pessoa ana = PessoaFactory.criarPessoa("Ana", "Souza");
        Pessoa maria = PessoaFactory.criarPessoa("Maria", "Oliveira");
        Pessoa carlos = PessoaFactory.criarPessoa("Carlos", "Silva");
        Pessoa joana = PessoaFactory.criarPessoa("Joana", "Souza");
        Pessoa pedro = PessoaFactory.criarPessoa("Pedro", "Silva");
        Pessoa lucas = PessoaFactory.criarPessoa("Lucas", "Silva");

        return Arrays.asList(new Object[][] {
                {ana, joao, maria, carlos, pedro, joana, lucas}
        });
    }

    public RemoverTest(Pessoa pessoaAvoA, Pessoa pessoaAvoB, Pessoa pessoaMae, Pessoa pessoaPai, Pessoa pessoaFilho, Pessoa pessoaConjuge, Pessoa pessoaNeto) {
        this.pessoaAvoA = pessoaAvoA;
        this.pessoaAvoB = pessoaAvoB;
        this.pessoaMae = pessoaMae;
        this.pessoaPai = pessoaPai;
        this.pessoaFilho = pessoaFilho;
        this.pessoaConjuge = pessoaConjuge;
        this.pessoaNeto = pessoaNeto;
    }

    @Test
    @Category(Funcional.class)
    public void removeNoTest() {
        avoA.adicionarFilho(pai, avoA, avoB);
        pai.adicionarFilho(filho, pai, mae);
        filho.adicionarFilho(neto, filho, conjuge);
        avoA.removerNo();

        assertNull(avoB.getConjuge());

        assertNull(pai.getMae());
        assertNull(pai.getPai());

        assertNull(filho.getMae());
        assertNull(filho.getPai());

        assertNull(neto.getMae());
        assertNull(neto.getPai());
    }
}
