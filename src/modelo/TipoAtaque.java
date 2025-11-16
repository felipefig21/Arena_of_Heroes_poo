package modelo;

public enum TipoAtaque {
    FISICO(10), MAGICO(15), ESPECIAL(20);

    private final int dano;

    TipoAtaque(int dano) {
        this.dano = dano;
    }

    public int getDano() {
        return dano;
    }
}

