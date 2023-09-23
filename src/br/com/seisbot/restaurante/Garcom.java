package br.com.seisbot.restaurante;

import java.util.LinkedList;
import java.util.Queue;

public class Garcom {
    private Queue<Pedido> fila = new LinkedList<>();

    public synchronized void adicionarPedido(Pedido pedido) {
        System.err.println("Garçom levou o pedido " + pedido.getNome() + " para cozinha");
        fila.add(pedido);
        notifyAll();
    }

    public synchronized Pedido removerPedido() throws InterruptedException {
        while (fila.isEmpty()) {
            wait();
        }
        return fila.poll();
    }

}
