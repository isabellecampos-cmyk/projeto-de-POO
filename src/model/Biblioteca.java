package model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Jogo> jogos = new ArrayList<>();

    public void adicionarJogo(Jogo jogo) {
        if (!jogos.contains(jogo)) jogos.add(jogo);
    }

    public boolean possui(Jogo jogo) { return jogos.contains(jogo); }
    public List<Jogo> getJogos() { return jogos; }

    public void listar() {
        System.out.println("=== Biblioteca (" + jogos.size() + " jogos) ===");
        jogos.forEach(j -> System.out.println(" - " + j));
    }
}
