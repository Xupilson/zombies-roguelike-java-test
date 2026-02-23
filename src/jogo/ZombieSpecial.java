package jogo;

public enum ZombieSpecial {
    TANK("Tank", 18, 2),
    RUNNER("Runner", 10, 3),
    TOXICO("Tóxico", 14, 2),
    EXPLODER("Exploder", 12, 4);

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