package jogo;

public enum ZombieSpecial {
    TANK("Tank", 20, 3),
    RUNNER("Runner", 8, 4),
    TOXICO("Tóxico", 10, 3),
    EXPLODER("Exploder", 4, 8);

    private final String nome;
    private final int vida;
    private final int dano;

    ZombieSpecial(String nome, int vida, int dano) {
        this.nome = nome;
        this.vida = vida;
        this.dano = dano;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getDano() {
        return dano;
    }
}