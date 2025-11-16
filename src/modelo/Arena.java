package modelo;

import java.util.*;

public class Arena {
    private String nome;
    private Set<Heroi> herois;
    private List<Vilao> viloes;
    private Map<String, Luta> lutas;

    public Arena(String nome) {
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome inválido");
        this.nome = nome;
        this.herois = new HashSet<>();
        this.viloes = new ArrayList<>();
        this.lutas = new HashMap<>();
    }

    public void adicionarHeroi(Heroi heroi) {
        if (heroi != null) {
            herois.add(heroi);
        }
    }

    public void adicionarVilao(Vilao vilao) {
        if (vilao != null && !viloes.contains(vilao)) {
            viloes.add(vilao);
        }
    }

    public void ordenarViloes() {
        viloes.sort((v1, v2) -> Integer.compare(v2.getNivel(), v1.getNivel()));
    }

    public Luta criarLuta(Heroi heroi, Vilao vilao) {
        Luta luta = new Luta(heroi, vilao);
        lutas.put(heroi.getNome() + "-" + vilao.getNome(), luta);
        return luta;
    }

    public List<Vilao> getViloes() {
        return new ArrayList<>(viloes);
    }

    public Set<Heroi> getHerois() {
        return new HashSet<>(herois);
    }

    public String getNome() {
        return nome;
    }
}

