package model;

import exception.SaldoInsuficienteException;
import java.util.ArrayList;
import java.util.List;

public class Carteira {
    private double saldo;
    private List<Transacao> historico = new ArrayList<>();

    public double getSaldo() { return saldo; }

    public void adicionarSaldo(double valor) {
        if (valor <= 0) throw new IllegalArgumentException("Valor inválido.");
        saldo += valor;
        historico.add(new Transacao("CREDITO", valor));
    }

    public void debitar(double valor) {
        if (valor > saldo) {
            throw new SaldoInsuficienteException(
                String.format("Saldo insuficiente. Necessário R$ %.2f, disponível R$ %.2f.",
                        valor, saldo));
        }
        saldo -= valor;
        historico.add(new Transacao("DEBITO", valor));
    }

    public void exibirHistorico() {
        System.out.println("=== Histórico da Carteira ===");
        historico.forEach(System.out::println);
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
    }
}
