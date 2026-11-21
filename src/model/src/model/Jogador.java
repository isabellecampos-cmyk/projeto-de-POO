package model;

import exception.*;
import java.util.ArrayList;
import java.util.List;

public class Jogador extends Usuario {
    private Biblioteca biblioteca;
    private Carrinho carrinho;
    private Carteira carteira;
    private List<Jogador> amigos;

    public Jogador(String nome, String email, String senha) {
        super(nome, email, senha);
        this.biblioteca = new Biblioteca();
        this.carrinho = new Carrinho();
        this.carteira = new Carteira();
        this.amigos = new ArrayList<>();
    }

    @Override
    public String getTipoUsuario() { return "Jogador"; }

    public Biblioteca getBiblioteca() { return biblioteca; }
    public Carrinho getCarrinho() { return carrinho; }
    public Carteira getCarteira() { return carteira; }
    public List<Jogador> getAmigos() { return amigos; }

    public void adicionarAmigo(Jogador amigo) {
        if (amigo != null && amigo != this && !amigos.contains(amigo)) {
            amigos.add(amigo);
            amigo.amigos.add(this);
        }
    }

    public void removerAmigo(Jogador amigo) {
        amigos.remove(amigo);
        amigo.amigos.remove(this);
    }

    public void adicionarAoCarrinho(Jogo jogo) {
        if (biblioteca.possui(jogo)) {
            throw new JogoJaPossuidoException(
                "O jogo '" + jogo.getNome() + "' já está na biblioteca.");
        }
        if (jogo.isGratuito()) {
            biblioteca.adicionarJogo(jogo);
            System.out.println("[GRÁTIS] '" + jogo.getNome()
                + "' adicionado diretamente à biblioteca.");
            return;
        }
        carrinho.adicionarJogo(jogo);
    }

    public void finalizarCompra() {
        if (carrinho.estaVazio()) {
            System.out.println("Carrinho vazio.");
            return;
        }
        double total = carrinho.calcularTotal();
        carteira.debitar(total);

        for (Jogo jogo : carrinho.getJogos()) {
            biblioteca.adicionarJogo(jogo);
            System.out.println("Comprado: " + jogo.getNome());
        }
        carrinho.limpar();
        System.out.printf("Compra concluída! Total: R$ %.2f | Saldo: R$ %.2f%n",
                total, carteira.getSaldo());
    }

    public Avaliacao avaliarJogo(Jogo jogo, boolean recomendado, String comentario) {
        if (!biblioteca.possui(jogo)) {
            throw new AvaliacaoNaoPermitidaException(
                "Você precisa possuir o jogo para avaliá-lo.");
        }
        Avaliacao avaliacao = new Avaliacao(this, jogo, recomendado, comentario);
        jogo.adicionarAvaliacao(avaliacao);
        return avaliacao;
    }
}
