package jogo;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Random rng = new Random();

        System.out.println("Qual é o seu nome, Sobrevivente?");
        String nome = entrada.nextLine();

        Weapon arma = escolherArma(entrada);

        System.out.println("Quantas balas você tem?");
        int municao = entrada.nextInt();

        Player player = new Player(nome, 10, 15, municao, arma);

        System.out.println("\nBem-vindo ao Call Of Duty Zombies!");
        System.out.println("Você é " + player.getNome() + " e começa com " + player.getVida() + " de vida e " + player.getMunicao() + " balas.");
        System.out.println("Arma escolhida: " + player.getArma().getNome());

        DropSystem drops = new DropSystem(rng);
        GameEngine engine = new GameEngine(player, drops, entrada, rng);

        engine.run();

        entrada.close();
    }

    private static Weapon escolherArma(Scanner entrada) {
        while (true) {
            System.out.println("\nEscolha sua Arma:");
            System.out.println("1 - Pistola");
            System.out.println("2 - Shotgun");
            System.out.println("3 - Metralhadora");
            System.out.println("4 - Sniper");

            int escolha = entrada.nextInt();

            switch (escolha) {
                case 1: return Weapon.PISTOLA;
                case 2: return Weapon.SHOTGUN;
                case 3: return Weapon.METRALHADORA;
                case 4: return Weapon.SNIPER;
                default:
                    System.out.println("Opção inválida! Escolha apenas 1 a 4.");
            }
        }
    }
}