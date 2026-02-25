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
            int zumbisVida = 1 + (round / 5);
            System.out.println("Vida dos zumbis normais: " + zumbisVida);            

            // A cada 10 Rounds vai ter Boss
            if (round % 10 == 0) {
                Boss boss = new Boss("ABOMINAÇÃO", 30, 5);
                lutarContraBoss(boss);
                if (!player.estaVivo()) break;
            }


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

        System.out.println("Você escolheu " + escolhido.getNome());
    }

    private void lutarContraZumbis(int zumbis) {
        int danoFinal = player.getArma().getDano() + player.getBonusDano();
        int vidaZumbi = 1 + (round / 5); // +1 de vida a cada 5 rounds

        for (int i = 1; i <= zumbis; i++) {
            System.out.println("Zumbi " + i + " apareceu!");

            if (player.temMunicao()) {
                int zumbisMortos = danoFinal / vidaZumbi;
                
                zumbisMortos = Math.max(1, zumbisMortos);

                zumbisMortos = Math.min(zumbisMortos, zumbis - i + 1);
                i += zumbisMortos - 1;

                player.gastarBala();
                System.out.println("Você matou " + zumbisMortos + " zumbis! Balas: " + player.getMunicao());
            } else {
                int danoZumbi = 1 + (round / 4);
                int danoTomado = player.tomarDano(danoZumbi);

                // Mata 1 zumbi na faca
                int mortosMelee = 1;
                System.out.println("Sem munição! Você foi atacado (-" + danoTomado + ") Vida: " + player.getVida());
                System.out.println("Você matou " + mortosMelee + " zumbi na FACA");
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

    private void lutarContraBoss(Boss boss){
        int vidaBoss = boss.calcularVida(round);
        int danoBoss = boss.calcularDano(vidaBoss);
        int danoFinal = player.getArma().getDano() + player.getBonusDano();

        System.out.println("\n BOSS APARECEU: " + boss.getNome());
        System.out.println("Vida: " + vidaBoss + " | Dano: " + danoBoss);

        while (vidaBoss > 0 && player.estaVivo()) {
            if (player.temMunicao()) {
                player.gastarBala();
                vidaBoss -= danoFinal;
                vidaBoss = Math.max(0, vidaBoss);

                System.out.println("Você acertou o boss (-" + danoFinal + ")");
                System.out.println("Vida do Boss: " + vidaBoss + " | Balas: " + player.getMunicao());
            } else {
                player.tomarDano(danoBoss);
                System.out.println("Sem Munição, o Boss te atacou (-" + danoBoss + ")");
                System.out.println("Vida: " + player.getVida());
            }

            if (!player.estaVivo()) {
                if (player.tentarRenascer()) {
                    System.out.println("Você Morreu, porém ganhou outra chance");
                } else {
                    System.out.println("Você foi Derrotado pelo Boss!");
                    return;
                }
            }

            System.out.println();
        }
     
        System.out.println("BOSS DERROTADO!");

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
                player.gastarBala();
                vidaEspecial -= danoFinal;
                if (vidaEspecial < 0) vidaEspecial = 0;

                System.out.println("Você acertou o especial (-" + danoFinal + "). Vida do especial: " + vidaEspecial
                        + " | Balas: " + player.getMunicao());
            } else {
                
                // Dano do especial escala agora
                int danoEspecial = especial.getDano() + (round / 5);

                // 25% de chance de tomar +1 de dano no melee (Adicionei um risco)
                if (rng.nextInt(100) < 25) {
                    danoEspecial += 1;
                    System.out.println("Atacar de perto tem seus perigos.");
                }

                // Jogador toma dano "Normal"
                int danoTomado = player.tomarDano(danoEspecial);

                // Jogador Causa o dano de Faca
                int danoMelee = player.calcularDanoMelee();
                vidaEspecial = Math.max(0, vidaEspecial - danoMelee);

                System.out.println("Sem munição! O especial te atacou (-" + danoTomado + "). Vida: " + player.getVida());
                System.out.println("Você deu uma Faca (-" + danoMelee + "). Vida do especial: " + vidaEspecial);
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
                    System.out.println("Dano Final: " + (player.getArma().getDano() + player.getBonusDano()));
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