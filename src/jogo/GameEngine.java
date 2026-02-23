package jogo;

import java.util.Random;
import java.util.Scanner;

public class GameEngine {
    private final Player player;
    private final DropSystem drops;
    private final Scanner entrada;
    private final Random rng;
    

    private int round = 1;

    public GameEngine(Player player, DropSystem drops, Scanner entrada, Random rng) {
        this.player = player;
        this.drops = drops;
        this.entrada = entrada;
        this.rng = rng;
    }

    public void run() {
        while (player.estaVivo()) {
            System.out.println("\n===== ROUND " + round + " =====");

            int zumbis = round * 2;
            System.out.println("Zumbis nesta rodada: " + zumbis);

            // A cada 3 rounds: especial
            if (round % 3 == 0) {
                ZombieSpecial especial = sortearEspecial();
                lutarContraEspecial(especial);
                if (!player.estaVivo()) break;
            }

            lutarContraZumbis(zumbis);
            if (!player.estaVivo()) break;

            // Drops no fim do round
            drops.aplicarDrops(player);

            // Recompensa cresce com o round
            aplicarRecompensaDeRound();

            // A cada 4 rounds, oferece poderes (4, 8, 12, e por ai vai)
            if (round % 4 == 0) {
                oferecerPoderes();
            }


            // Menu entre rounds
            mostrarMenuEntreRounds();

            round++;
        }

        System.out.println("\n===== FIM DO JOGO =====");
        System.out.println("Rodada alcançada: " + round);
        System.out.println("Vida final: " + player.getVida());
        System.out.println("Munição final: " + player.getMunicao());
        System.out.println("Renascimentos restantes: " + player.getRenascimentos());
    }

    private void oferecerPoderes() {
        Power[] poderes = Power.values();

        // Sorteia duas opções diferentes
        int indice1 = rng.nextInt(poderes.length);
        int indice2;
        do {
            indice2 = rng.nextInt(poderes.length);
        } while (indice2 == indice1);

        Power opcao1 = poderes[indice1];
        Power opcao2 = poderes[indice2];

        System.out.println("\n ==== ESCOLHA UM PODER ====");
        System.out.println("1 - " + opcao1.getNome() + " | " + opcao1.getDescricao());
        System.out.println("2 - " + opcao2.getNome() + " | " + opcao2.getDescricao());

        int escolha;
        do {
            System.out.println("Digite 1 ou 2: ");
            escolha = entrada.nextInt();
            if (escolha != 1 && escolha != 2) {
                System.out.println("Opção inválida, digite 1 ou 2.");
            }
        } while (escolha != 1 && escolha != 2);     
        
        Power escolhido = (escolha == 1) ? opcao1 : opcao2;
        escolhido.aplicar(player);

        System.out.println("Você escolhou" + escolhido.getNome());
    }

    private void lutarContraZumbis(int zumbis) {
        int danoFinal = player.getArma().getDano() + player.getBonusDano();

        for (int i = 1; i <= zumbis; i++) {
            System.out.println("Zumbi " + i + " apareceu!");

            if (player.temMunicao()) {
                int zumbisMortos = Math.min(danoFinal, zumbis - i + 1);
                i += zumbisMortos - 1;

                player.gastarBala(1);
                System.out.println("Você matou " + zumbisMortos + " zumbis! Balas: " + player.getMunicao());
            } else {
                player.tomarDano(1);
                System.out.println("Sem munição! Você foi atacado! Vida: " + player.getVida());
            }

            if (!player.estaVivo()) {
                if (player.tentarRenascer()) {
                    System.out.println("💀 Você morreu... MAS RENASCEU!");
                    System.out.println("Vida: " + player.getVida() + "/" + player.getVidaMax()
                            + " | Renascimentos: " + player.getRenascimentos()
                            + " | Munição: " + player.getMunicao());
                } else {
                    System.out.println("Você morreu! FIM DE JOGO!");
                    break;
                }
            }

            System.out.println();
        }
    }

    private ZombieSpecial sortearEspecial() {
        ZombieSpecial[] tipos = ZombieSpecial.values();
        return tipos[rng.nextInt(tipos.length)];
    }

    private void lutarContraEspecial(ZombieSpecial especial) {
        System.out.println("\n👹 ZUMBI ESPECIAL: " + especial.getNome()
                + " | Vida: " + especial.getVida()
                + " | Dano: " + especial.getDano());

        int vidaEspecial = especial.getVida();
        int danoFinal = player.getArma().getDano() + player.getBonusDano();

        while (vidaEspecial > 0 && player.estaVivo()) {
            if (player.temMunicao()) {
                player.gastarBala(1);
                vidaEspecial -= danoFinal;
                if (vidaEspecial < 0) vidaEspecial = 0;

                System.out.println("Você acertou o especial (-" + danoFinal + "). Vida do especial: " + vidaEspecial
                        + " | Balas: " + player.getMunicao());
            } else {
                player.tomarDano(especial.getDano());
                System.out.println("Sem munição! O especial te atacou (-" + especial.getDano() + "). Vida: " + player.getVida());
            }

            if (!player.estaVivo()) {
                if (player.tentarRenascer()) {
                    System.out.println("💀 Você morreu... MAS RENASCEU!");
                    System.out.println("Vida: " + player.getVida() + "/" + player.getVidaMax()
                            + " | Renascimentos: " + player.getRenascimentos()
                            + " | Munição: " + player.getMunicao());
                } else {
                    System.out.println("Você morreu! FIM DE JOGO!");
                    return;
                }
            }
        }

        System.out.println("✅ Você derrotou o zumbi especial: " + especial.getNome() + "!");
    }

    private void aplicarRecompensaDeRound() {
        int base = player.getArma().getRecompensaBase();
        int bonus = (round - 1) / 5;
        int recompensa = base + bonus;

        player.adicionarMunicao(recompensa);
        System.out.println("Recompensa do round: +" + recompensa + " balas (base " + base + " + bônus " + bonus + "). Munição: " + player.getMunicao());
    }

    private void mostrarMenuEntreRounds() {
        boolean menuValido = false;

        while (!menuValido && player.estaVivo()) {
            System.out.println("\n===== MENU ENTRE ROUNDS =====");
            System.out.println("1 - Continuar");
            System.out.println("2 - Ver Status");
            System.out.println("3 - Sair");

            int opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    menuValido = true;
                    break;

                case 2:
                    System.out.println("\n=== STATUS ===");
                    System.out.println("Jogador: " + player.getNome());
                    System.out.println("Arma: " + player.getArma().getNome() + " (dano " + player.getArma().getDano() + ")");
                    System.out.println("Vida: " + player.getVida() + "/" + player.getVidaMax());
                    System.out.println("Munição: " + player.getMunicao());
                    System.out.println("Renascimentos: " + player.getRenascimentos());
                    System.out.println("Bonus de Dano: " + player.getBonusDano());
                    System.out.println("Dano FInal: " + (player.getArma().getDano() + player.getBonusDano()));
                    System.out.println("Round Atual: " + round);
                    break;

                case 3:
                    System.out.println("Você decidiu encerrar o jogo.");
                    player.tomarDano(9999); // mata e sai
                    menuValido = true;
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

}