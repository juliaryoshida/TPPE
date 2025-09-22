package app;

import exceptions.NaoTemIrmaoException;

import java.util.ArrayList;
import java.util.List;

public class NoArvore {
    private final Pessoa pessoa;
    private final List<NoArvore> filhos;
    private NoArvore conjuge;
    private NoArvore pai;
    private NoArvore mae;

    public NoArvore(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.filhos = new ArrayList<>();
    }

    public void casarCom(NoArvore parceiro) {
        this.conjuge = parceiro;
        parceiro.conjuge = this;
    }

    public void adicionarFilho(NoArvore filho, NoArvore pai, NoArvore mae) {
        pai.filhos.add(filho);
        mae.filhos.add(filho);
        filho.pai = pai;
        filho.mae = mae;
    }

    public void removerNo() {
        for (NoArvore f : new ArrayList<>(this.filhos)) {
            f.removerNo();
        }

        if (pai != null) {
            pai.filhos.remove(this);
        }

        if (mae != null) {
            mae.filhos.remove(this);
        }

        if (conjuge != null) {
            conjuge.conjuge = null;
            this.conjuge = null;
        }

        this.pai = null;
        this.mae = null;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public NoArvore getConjuge() {
        return conjuge;
    }

    public NoArvore getMae() {
        return mae;
    }

    public NoArvore getPai() {
        return pai;
    }

    public List<NoArvore> getFilhos() {
        return filhos;
    }

    public List<Pessoa> getIrmaos() throws NaoTemIrmaoException {
        List<Pessoa> irmaos = new ArrayList<>();

        if (pai != null) {
            for (NoArvore i : pai.getFilhos()) {
                if (i != this && !irmaos.contains(i.getPessoa())){
                    irmaos.add(i.getPessoa());
                }
            }
        }

        if (mae != null) {
            for (NoArvore i: mae.getFilhos()) {
                if (i != this && !irmaos.contains(i.getPessoa())) {
                    irmaos.add(i.getPessoa());
                }
            }
        }

        if (irmaos.isEmpty()) {
            throw new NaoTemIrmaoException();
        }

        return irmaos;
    }

    public List<Pessoa> getTios() throws NaoTemIrmaoException {

        List<Pessoa> tios = new ArrayList<>();

        if (pai != null) {
            tios.addAll(pai.getIrmaos());
        }

        if (mae != null) {
            tios.addAll(mae.getIrmaos());
        }

        return tios;
    }

}
