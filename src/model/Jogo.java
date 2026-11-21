package model;

import interfaces.Avaliavel;
import interfaces.Comercializavel;
import java.util.ArrayList;
import java.util.List;

public class Jogo implements Comercializavel, Avaliavel {
    private String nome;
    private double preco;
    private String genero;
    private boolean bloqueado;
    private Desenvolvedor desenvolvedor;
    private List<Avaliacao> avaliacoes;
    private List<DLC> dlcs;

    public Jogo(String nome, double preco, String genero, Desenvolvedor dev) {
        this.nome = nome;
        this.preco = preco;
        this.genero = genero;
        this.desenvolvedor = dev;
        this.avaliacoes = new ArrayList<>();
        this.dlcs = new ArrayList<>();
        this.bloqueado = false;
    }

    @Override public String getNome() { return nome; }
    @Override public double getPreco() { return preco; }
    @Override public boolean isGratuito() { return preco == 0.0; }

    public String getGenero() { return genero; }
    public Desenvolvedor getDesenvolvedor() { return desenvolvedor; }
    public boolean isBloqueado() { return bloqueado; }
    public void setBloqueado(boolean bloqueado) { this.bloqueado = bloqueado; }
    public List<Avaliacao> getAvaliacoes() { return avaliacoes; }
    public List<DLC> getDlcs() { return dlcs; }

    public void adicionarDlc(DLC dlc) { dlcs.add(dlc); }

    @Override
    public void adicionarAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    @Override
    public double calcularMediaAvaliacoes() {
        if (avaliacoes.isEmpty()) return 0;
        long positivas = avaliacoes.stream().filter(Avaliacao::isRecomendado).count();
        return 100.0 * positivas / avaliacoes.size();
    }

    @Override
    public String toString() {
        return String.format("%s (R$ %.2f, %s) — %d%% positivas",
                nome, preco, genero, (int) calcularMediaAvaliacoes());
    }
}
