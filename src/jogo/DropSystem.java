package jogo;

import java.util.Random;

public class DropSystem {
    private final Random rng;

    public DropSystem(Random rng) {
        this.rng = rng;
    }

    // Drops no fim do round
    public void aplicarDrops(Player player) {
        int sorteio = rng.nextInt(100) + 1; // 1..100

        // 10% - raro: renascimento
        if (sorteio <= 10) {
            player.adicionarRenascimento();
            System.out.println("DROP RARO: +1 RENASCIMENTO! Renascimentos: " + player.getRenascimentos());
            return;
        }

        // 20% - médio: kit médico
        if (sorteio <= 30) {
            int vidaAntes = player.getVida();
            player.curar(3);
            int curou = player.getVida() - vidaAntes;

            if (curou > 0) {
                System.out.println("DROP: KIT MÉDICO (+3 VIDA)! Vida: " + player.getVida() + "/" + player.getVidaMax());
            } else {
                System.out.println("DROP: KIT MÉDICO apareceu, mas você já está FULL LIFE!");
            }
            return;
        }

        // 40% - comum: munição extra
        if (sorteio <= 70) {
            int balasExtras = 2 + rng.nextInt(4); // 2..5
            player.adicionarMunicao(balasExtras);
            System.out.println("DROP: +" + balasExtras + " balas extras! Munição: " + player.getMunicao());
            return;
        }

        // 30% - nada
        System.out.println("Sem drop nessa rodada.");
    }
}