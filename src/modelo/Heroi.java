package modelo;

public class Heroi extends SerVivo {
    private int experiencia;

    public Heroi(String nome, int vida) {
        super(nome, vida);
        this.experiencia = 0;
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = calcularDano(TipoAtaque.FISICO);
        alvo.receberDano(dano);
        experiencia += 10;
    }

    public void ataqueEspecial(Personagem alvo) {
        int dano = calcularDano(TipoAtaque.ESPECIAL) * 2;  // Ataque especial tem 2x de dano!
        alvo.receberDano(dano);
        experiencia += 25;
    }

    public int getExperiencia() {
        return experiencia;
    }
}

