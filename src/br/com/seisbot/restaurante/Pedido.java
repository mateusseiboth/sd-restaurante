package br.com.seisbot.restaurante;

public class Pedido {
    private String nome;
    private long tempoPreparo;
    private String categoria;

    public Pedido(String nome, long tempoPreparo, String categoria) {
        this.nome = nome;
        this.tempoPreparo = tempoPreparo;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public long getTempoPreparo() {
        return tempoPreparo;
    }

    public String getCategoria() {
        return categoria;
    }
}
