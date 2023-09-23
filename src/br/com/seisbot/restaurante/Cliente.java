package br.com.seisbot.restaurante;

import java.util.Random;

public class Cliente implements Runnable {
	private int id;
	private Garcom filaPedidos;

	public Cliente(int id, Garcom filaPedidos) {
		this.id = id;
		this.filaPedidos = filaPedidos;
	}

	@Override
	public void run() {
		Random random = new Random();

		for (int i = 0; i < 3; i++) {
			String[] cardapio = { "Sushi", "Pizza", "Hamburguer", "Hamburguer sem Carne", "Salada", "Macarrão" };
			String nomePrato = cardapio[random.nextInt(cardapio.length)];
			long tempoPreparo = random.nextInt(3900) + 100; // Entre 100 e 4000 milissegundos
			String categoria = i == 0 ? "entrada" : (i == 1 ? "prato principal" : "sobremesa");

			Pedido pedido = new Pedido(nomePrato, tempoPreparo, categoria);
			System.out.println("Cliente #" + id + " fez um pedido: " + categoria + ": " + nomePrato);
			filaPedidos.adicionarPedido(pedido);

			synchronized (pedido) {
				try {
					pedido.wait(); // Aguardar a notificação do pedido pronto
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.err.println("Cliente #" + id + " recebeu o pedido: " + categoria + ": " + nomePrato);

			try {
				Thread.sleep(random.nextInt(4900) + 100); // Tempo para consumir entre 100 e 5000 milissegundos
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
