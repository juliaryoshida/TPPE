package app;

public class PessoaFactory {
    public static Pessoa criarPessoa(String nome, String sobrenome) {
        return new Pessoa(nome, sobrenome);
    }
}