package jogo;

public class Player {
    private final String nome;
    private final int vidaMax;
    private int vida;
    private int municao;
    private int renascimentos;
    private final Weapon arma;
    private int armadura = 0;
    private int bonusDano = 0;

    public Player(String nome, int vidaInicial, int vidaMax, int municaoInicial, Weapon arma) {
        this.nome = nome;
        this.vida = vidaInicial;
        this.vidaMax = vidaMax;
        this.municao = municaoInicial;
        this.arma = arma;
        this.renascimentos = 0;
    }

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getVidaMax() { return vidaMax; }
    public int getMunicao() { return municao; }
    public int getRenascimentos() { return renascimentos; }
    public Weapon getArma() { return arma; }
    public int getBonusDano() { return bonusDano; }
    public int getArmadura() { return armadura; }

    public boolean estaVivo() {
        return vida > 0;
    }

    public boolean temMunicao() {
        return municao > 0;
    }

    public void gastarBala(int qtd) {
        municao = Math.max(0, municao - qtd);
    }

    public void adicionarMunicao(int qtd) {
        municao += qtd;
    }

    public void tomarDano(int qtd) {
        vida -= qtd;
    }

    public void curar(int qtd) {
        vida = Math.min(vida + qtd, vidaMax);
    }

    public void adicionarRenascimento() {
        renascimentos++;
    }

    public void adicionarBonusDano(int valor) {
        bonusDano += valor;
    }

    public void adicionarArmadura(int valor) {
        armadura += valor;
    }

    // Vida extra: se morrer e tiver renascimento, revive
    public boolean tentarRenascer() {
        if (renascimentos <= 0) return false;

        renascimentos--;

        int vidaNova = vidaMax / 2; // volta com metade da vida
        if (vidaNova < 1) vidaNova = 1;
        vida = vidaNova;

        // bônus pequeno para não travar
        adicionarMunicao(1);

        return true;
    }
}