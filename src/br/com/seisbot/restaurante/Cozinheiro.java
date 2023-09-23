package br.com.seisbot.restaurante;

import java.util.Random;

public class Cozinheiro implements Runnable {
	private int id;
	private Garcom filaPedidos;

	public Cozinheiro(int id, Garcom filaPedidos) {
		this.id = id;
		this.filaPedidos = filaPedidos;
	}

	@Override
	public void run() {
		Random random = new Random();

		while (true) {
			try {
				Pedido pedido = filaPedidos.removerPedido();
				System.out.println(
						"Cozinheiro #" + id + " está preparando " + pedido.getCategoria() + ": " + pedido.getNome());
				Thread.sleep(pedido.getTempoPreparo());
				System.err.println("Cozinheiro #" + id + " terminou de preparar " + pedido.getCategoria() + ": "
						+ pedido.getNome());

				// Notificar o cliente que o pedido está pronto
				synchronized (pedido) {
					System.err.println("Garçom levou o pedido " + pedido.getNome() + " para o cliente");
					pedido.notify();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
