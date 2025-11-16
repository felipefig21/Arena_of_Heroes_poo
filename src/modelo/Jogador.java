package modelo;

public class Jogador {
    private String nome;
    private Heroi heroi;
    private int vitoria;

    public Jogador(String nome, Heroi heroi) {
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome inválido");
        if (heroi == null) throw new IllegalArgumentException("Herói inválido");
        this.nome = nome;
        this.heroi = heroi;
        this.vitoria = 0;
    }

    public void registrarVitoria() {
        vitoria++;
    }

    public String getNome() {
        return nome;
    }

    public Heroi getHeroi() {
        return heroi;
    }

    public int getVitorias() {
        return vitoria;
    }
}

