package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class SerVivo implements Personagem {
    private String nome;
    private int vida;
    private int vidaMaxima;
    private List<Item> inventario;

    public SerVivo(String nome, int vida) {
        if (vida <= 0) throw new IllegalArgumentException("Vida deve ser > 0");
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome inválido");
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.inventario = new ArrayList<>();
    }

    public abstract void atacar(Personagem alvo);

    public int calcularDano(TipoAtaque tipo) {
        return tipo.getDano() + inventario.size();
    }

    @Override
    public void receberDano(int dano) {
        this.vida = Math.max(0, this.vida - dano);
    }

    public void adicionarItem(Item item) {
        if (item != null && !inventario.contains(item)) {
            inventario.add(item);
        }
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public List<Item> getInventario() {
        return new ArrayList<>(inventario);
    }

    public boolean estaVivo() {
        return vida > 0;
    }
}

