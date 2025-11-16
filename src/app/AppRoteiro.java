package app;

import modelo.*;

public class AppRoteiro {

    public static void main(String[] args) {
        Arena arena = new Arena("Arena Test");

        Heroi heroi = new Heroi("Artur", 150);
        Vilao vilao = new Vilao("Morgana", 100, 2);

        arena.adicionarHeroi(heroi);
        arena.adicionarVilao(vilao);
        arena.adicionarVilao(new Vilao("Lancelot", 80, 1));

        Item espada = new Item("Excalibur", 10);
        Item escudo = new Item("Escudo Sagrado", 5);

        heroi.adicionarItem(espada);
        heroi.adicionarItem(escudo);
        vilao.adicionarItem(new Item("Magia Negra", 8));

        System.out.println("=== Arena of Heroes - Roteiro ===\n");
        System.out.println("Criação de personagens:");
        System.out.println("- " + heroi.getNome() + " (Vida: " + heroi.getVida() + ")");
        System.out.println("- " + vilao.getNome() + " (Vida: " + vilao.getVida() + ", Nível: " + vilao.getNivel() + ")");
        System.out.println("- Inventário do herói: " + heroi.getInventario().size() + " itens\n");

        Jogador jogador = new Jogador("Piloto", heroi);
        System.out.println("Jogador criado: " + jogador.getNome());
        System.out.println("Herói do jogador: " + jogador.getHeroi().getNome() + "\n");

        arena.ordenarViloes();
        System.out.println("Vilões ordenados por nível:");
        for (Vilao v : arena.getViloes()) {
            System.out.println("- " + v.getNome() + " (Nível: " + v.getNivel() + ")");
        }
        System.out.println();

        Luta luta = arena.criarLuta(heroi, vilao);
        System.out.println("Iniciando luta entre " + heroi.getNome() + " e " + vilao.getNome() + "\n");

        while (!luta.isFinalizada()) {
            System.out.println("Turno " + (luta.isFinalizada() ? "Final" : "Executando"));
            System.out.println(luta.getEstado());
            luta.executarTurno();
        }

        System.out.println("\nLuta finalizada!");
        System.out.println("Ganhador: " + luta.getGanhador().getNome());

        if (luta.getGanhador() == heroi) {
            jogador.registrarVitoria();
            System.out.println("Vitória do jogador! Total de vitórias: " + jogador.getVitorias());

            if (heroi instanceof Heroi) {
                Heroi h = (Heroi) heroi;
                System.out.println("Experiência ganha: " + h.getExperiencia());
            }
        }

        System.out.println("\nDemonstração de polimorfismo:");

        // Criar novos personagens para demonstração
        Heroi heroi2 = new Heroi("Lancelot", 100);
        Vilao vilao2 = new Vilao("Demônio", 80, 2);

        System.out.println("\n--- Demonstração de Ataque Especial ---");
        System.out.println(heroi2.getNome() + " vida: " + heroi2.getVida() + " | " + vilao2.getNome() + " vida: " + vilao2.getVida());
        heroi2.ataqueEspecial(vilao2);
        System.out.println("Após ataque especial:");
        System.out.println(heroi2.getNome() + " vida: " + heroi2.getVida() + " | " + vilao2.getNome() + " vida: " + vilao2.getVida());

        System.out.println("\n--- Demonstração de Chamadas Polimórficas ---");
        Personagem p1 = heroi2;
        Personagem p2 = vilao2;
        System.out.println("Chamada polimórfica em p1 (Herói):");
        p1.atacar(p2);
        System.out.println(p2.getNome() + " agora tem " + p2.getVida() + " de vida");

        System.out.println("\n--- Demonstração de Sobrecarga em Vilão ---");
        vilao2.atacar(p1, TipoAtaque.FISICO);
        System.out.println(p1.getNome() + " agora tem " + p1.getVida() + " de vida (após ataque FISICO)");

        vilao2.atacar(p1, TipoAtaque.MAGICO);
        System.out.println(p1.getNome() + " agora tem " + p1.getVida() + " de vida (após ataque MAGICO)");
    }
}

