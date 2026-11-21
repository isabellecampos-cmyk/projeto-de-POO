package model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Jogo> jogos = new ArrayList<>();

    public void adicionarJogo(Jogo jogo) {
        if (!jogos.contains(jogo)) jogos.add(jogo);
    }

    public boolean estaVazio() { return jogos.isEmpty(); }
    public List<Jogo> getJogos() { return jogos; }

    public double calcularTotal() {
        return jogos.stream().mapToDouble(Jogo::getPreco).sum();
    }

    public void limpar() { jogos.clear(); }
}
