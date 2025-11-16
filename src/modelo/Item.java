package modelo;

public class Item {
    private String nome;
    private int poder;

    public Item(String nome, int poder) {
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome inválido");
        if (poder <= 0) throw new IllegalArgumentException("Poder deve ser > 0");
        this.nome = nome;
        this.poder = poder;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Item)) return false;
        Item outro = (Item) obj;
        return this.nome.equals(outro.nome) && this.poder == outro.poder;
    }

    @Override
    public int hashCode() {
        return nome.hashCode() + poder;
    }

    public String getNome() {
        return nome;
    }

    public int getPoder() {
        return poder;
    }
}

