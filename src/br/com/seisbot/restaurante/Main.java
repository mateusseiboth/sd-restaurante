package br.com.seisbot.restaurante;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int numClientes = random.nextInt(31) + 20; // Entre 20 e 50 clientes
        int numCozinheiros = random.nextInt(6) + 5; // Entre 5 e 10 cozinheiros

        Garcom filaPedidos = new Garcom();

        ExecutorService executor = Executors.newFixedThreadPool(numCozinheiros);

        for (int i = 0; i < numCozinheiros; i++) {
            executor.execute(new Cozinheiro(i + 1, filaPedidos));
        }

        ExecutorService clienteExecutor = Executors.newFixedThreadPool(numClientes);

        for (int i = 0; i < numClientes; i++) {
            clienteExecutor.execute(new Cliente(i + 1, filaPedidos));
        }

        executor.shutdown();
        clienteExecutor.shutdown();
    }
}
