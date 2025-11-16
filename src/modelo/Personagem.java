package modelo;

public interface Personagem {
    void atacar(Personagem alvo);
    int getVida();
    String getNome();
    void receberDano(int dano);
    boolean estaVivo();
}

