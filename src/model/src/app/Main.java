package app;

import exception.*;
import model.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("========== GAMEHUB — TESTE DE FLUXO ==========\n");

        Desenvolvedor dev = new Desenvolvedor("Indie Studio", "dev@studio.com", "1234");
        Jogador alice = new Jogador("Alice", "alice@mail.com", "senha");

        Jogo skyrim = dev.cadastrarJogo("Skyrim", 59.90, "RPG");
        Jogo lol = dev.cadastrarJogo("LoL", 0.0, "MOBA");

        alice.getCarteira().adicionarSaldo(100.0);
        alice.adicionarAoCarrinho(skyrim);
        alice.adicionarAoCarrinho(lol);
        alice.finalizarCompra();

        alice.getBiblioteca().listar();
    }
}
