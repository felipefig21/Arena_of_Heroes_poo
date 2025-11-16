package modelo;

public class Vilao extends SerVivo {
    private int nivel;

    public Vilao(String nome, int vida, int nivel) {
        super(nome, vida);
        if (nivel <= 0) throw new IllegalArgumentException("Nível deve ser > 0");
        this.nivel = nivel;
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = calcularDano(TipoAtaque.MAGICO) + nivel * 2;
        alvo.receberDano(dano);
    }

    public void atacar(Personagem alvo, TipoAtaque tipo) {
        int dano = calcularDano(tipo) + nivel;
        alvo.receberDano(dano);
    }

    public int getNivel() {
        return nivel;
    }
}

