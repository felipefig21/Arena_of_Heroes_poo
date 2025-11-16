package modelo;

public class Luta {
    private Personagem heroi;
    private Personagem vilao;
    private int turno;
    private boolean finalizada;

    public Luta(Personagem heroi, Personagem vilao) {
        if (heroi == null || vilao == null) throw new IllegalArgumentException("Personagens inválidos");
        this.heroi = heroi;
        this.vilao = vilao;
        this.turno = 0;
        this.finalizada = false;
    }

    public void executarTurno() {
        if (finalizada) return;

        turno++;
        heroi.atacar(vilao);

        if (!vilao.estaVivo() || vilao.getVida() == 0) {
            finalizada = true;
            return;
        }

        vilao.atacar(heroi);

        if (!heroi.estaVivo() || heroi.getVida() == 0) {
            finalizada = true;
        }
    }

    public Personagem getGanhador() {
        if (!finalizada) return null;
        return heroi.getVida() > 0 ? heroi : vilao;
    }

    public String getEstado() {
        return heroi.getNome() + " [" + heroi.getVida() + "hp] vs " +
               vilao.getNome() + " [" + vilao.getVida() + "hp] - Turno: " + turno;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public Personagem getHeroi() {
        return heroi;
    }

    public Personagem getVilao() {
        return vilao;
    }
}

