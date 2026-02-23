package jogo;

public enum Weapon {
    PISTOLA("Pistola", 1, 5),
    SHOTGUN("Shotgun", 2, 4),
    METRALHADORA("Metralhadora", 3, 3),
    SNIPER("Sniper", 5, 1);

    private final String nome;
    private final int dano;
    private final int recompensaBase;

    Weapon(String nome, int dano, int recompensaBase) {
        this.nome = nome;
        this.dano = dano;
        this.recompensaBase = recompensaBase;
    }

    public String getNome() {
        return nome;
    }

    public int getDano() {
        return dano;
    }

    public int getRecompensaBase() {
        return recompensaBase;
    }
}