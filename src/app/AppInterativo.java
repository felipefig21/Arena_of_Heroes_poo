package app;

import modelo.*;
import java.util.Scanner;

public class AppInterativo {
    private Arena arena;
    private Jogador jogador;
    private Luta lutaAtual;
    private Scanner scanner;

    public AppInterativo() {
        this.scanner = new Scanner(System.in);
        this.arena = new Arena("Arena dos Heróis");
    }

    public void iniciar() {
        System.out.println("=== Arena of Heroes ===");
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        Heroi heroi = new Heroi("Herói " + nome, 100);
        jogador = new Jogador(nome, heroi);
        arena.adicionarHeroi(heroi);

        Vilao v1 = new Vilao("Necromante", 80, 1);
        Vilao v2 = new Vilao("Demônio", 120, 2);
        arena.adicionarVilao(v1);
        arena.adicionarVilao(v2);

        heroi.adicionarItem(new Item("Espada", 5));
        v1.adicionarItem(new Item("Manto Negro", 3));

        menuPrincipal();
    }

    private void menuPrincipal() {
        boolean rodando = true;
        while (rodando) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Iniciar Luta");
            System.out.println("2. Ver Estado");
            System.out.println("3. Sair");
            System.out.print("Opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    iniciarLuta();
                    break;
                case "2":
                    exibirEstado();
                    break;
                case "3":
                    rodando = false;
                    break;
            }
        }
        scanner.close();
    }

    private void iniciarLuta() {
        System.out.println("\nVilões disponíveis:");
        int i = 1;
        for (Vilao v : arena.getViloes()) {
            System.out.println(i + ". " + v.getNome() + " (Vida: " + v.getVida() + ", Nível: " + v.getNivel() + ")");
            i++;
        }
        System.out.print("Escolha (1-" + arena.getViloes().size() + "): ");

        try {
            int escolha = Integer.parseInt(scanner.nextLine()) - 1;
            if (escolha >= 0 && escolha < arena.getViloes().size()) {
                Vilao vilao = arena.getViloes().get(escolha);
                lutaAtual = arena.criarLuta(jogador.getHeroi(), vilao);
                executarLuta();
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida!");
        }
    }

    private void executarLuta() {
        while (!lutaAtual.isFinalizada()) {
            System.out.println("\n" + lutaAtual.getEstado());
            System.out.println("1. Atacar | 2. Ataque Especial | 3. Sair");
            System.out.print("Ação: ");

            String acao = scanner.nextLine();

            if (acao.equals("1")) {
                lutaAtual.executarTurno();
            } else if (acao.equals("2") && jogador.getHeroi() instanceof Heroi) {
                ((Heroi) jogador.getHeroi()).ataqueEspecial(lutaAtual.getVilao());
                System.out.println(lutaAtual.getEstado());

                if (!lutaAtual.getVilao().estaVivo()) {
                    System.out.println("\nVilão derrotado!");
                    break; // Finaliza a luta
                } else {
                    // Vilão contra-ataca
                    lutaAtual.getVilao().atacar(jogador.getHeroi());
                    System.out.println("Contra-ataque!");
                    System.out.println(lutaAtual.getEstado());

                    if (!jogador.getHeroi().estaVivo()) {
                        System.out.println("\nHerói derrotado!");
                        break; // Finaliza a luta
                    }
                }
            } else if (acao.equals("3")) {
                break;
            }
        }

        // Verificar resultado independentemente de como a luta terminou
        if (!lutaAtual.getHeroi().estaVivo()) {
            System.out.println("\nLuta finalizada!");
            System.out.println("Ganhador: " + lutaAtual.getVilao().getNome());
        } else if (!lutaAtual.getVilao().estaVivo()) {
            System.out.println("\nLuta finalizada!");
            System.out.println("Ganhador: " + lutaAtual.getHeroi().getNome());
            jogador.registrarVitoria();
        } else if (lutaAtual.isFinalizada()) {
            System.out.println("\nLuta finalizada!");
            System.out.println("Ganhador: " + lutaAtual.getGanhador().getNome());
            if (lutaAtual.getGanhador() == jogador.getHeroi()) {
                jogador.registrarVitoria();
            }
        }
    }

    private void exibirEstado() {
        System.out.println("\n=== Estado do Jogo ===");
        System.out.println("Jogador: " + jogador.getNome());
        System.out.println("Herói: " + jogador.getHeroi().getNome());
        System.out.println("Vida: " + jogador.getHeroi().getVida() + "/" + jogador.getHeroi().getVidaMaxima());
        System.out.println("Vitórias: " + jogador.getVitorias());
        if (jogador.getHeroi() instanceof Heroi) {
            System.out.println("Experiência: " + ((Heroi) jogador.getHeroi()).getExperiencia());
        }
    }

    public static void main(String[] args) {
        new AppInterativo().iniciar();
    }
}

