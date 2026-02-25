package jogo;

public class Player {
    private final String nome;
    private int vidaMax;
    private int vida;
    private int municao;
    private int renascimentos;
    private final Weapon arma;
    private int armadura = 0;
    private int bonusDano = 0;


    // ====== NOVOS ATRIBUTOS ======
    private int bonusMelee = 0;
    private int regeneracaoPorRound = 0;
    private int bonusRecompensaBalas = 0;
    private int bonusSorteDrop = 0;
    private int chanceEconomizarBala = 0;
    private boolean furia = false;
    private boolean segundaChanceUsada = false;

    public Player(String nome, int vidaInicial, int vidaMax, int municaoInicial, Weapon arma) {
        this.nome = nome;
        this.vida = vidaInicial;
        this.vidaMax = vidaMax;
        this.municao = municaoInicial;
        this.arma = arma;
        this.renascimentos = 0;
    }

    // ====== GETTERS ======
    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getVidaMax() { return vidaMax; }
    public int getMunicao() { return municao; }
    public int getRenascimentos() { return renascimentos; }
    public Weapon getArma() { return arma; }
    public int getBonusDano() { return bonusDano; }
    public int getArmadura() { return armadura; }
    public int getBonusMelee() { return bonusMelee; }
    public int getRegeneracaoPorRound() { return regeneracaoPorRound; }
    public int getBonusRecompensaBalas() { return bonusRecompensaBalas; }
    public int getBonusSorteDrop() { return bonusSorteDrop; }
    public int getChanceEconomizarBala() { return chanceEconomizarBala; }
    public boolean isFuria() { return furia; }
    public boolean isSegundaChanceUsada() { return segundaChanceUsada; }

    // ====== SETTERS CONTROLADO ======
    public void setFuria(boolean valor) { this.furia = valor; }
    public void setSegundaChanceUsada(boolean valor) { this.segundaChanceUsada = valor; }
    public void setChanceEconomizarBala(int valor) { this.chanceEconomizarBala = valor; }
    public void setBonusRecompensaBalas(int valor) { this.bonusRecompensaBalas = valor; }
    public void setRegeneracaoPorRound(int valor) { this.regeneracaoPorRound = valor; }
    public void setBonusSorteDrop(int valor) { this.bonusSorteDrop = valor; }
    

    // ====== STATUS ======
    public boolean estaVivo() {
        return vida > 0;
    }

    public boolean temMunicao() {
        return municao > 0;
    }

    // ====== COMBATE ======
    public int calcularDanoFinal() {
        int dano = arma.getDano() + bonusDano;

        // SE Tiver furia e vida <= 30%
        if (furia && vida <= (vidaMax * 0.3)) {
            dano += 2;
        }
        
        return dano;
    }
    
    public int calcularDanoMelee() {
        return 1 + bonusMelee; 
    }

    public void gastarBala() {
        municao--;
        if (municao < 0) municao = 0;
    }

    public void adicionarMunicao(int qtd) {
        if (qtd > 0) {
            municao += qtd;
        }
    }

    public int tomarDano(int qtd) {
        if (qtd <= 0) return 0;

        int danoFinal = qtd - armadura;
        
        // Vai tomar pelo menos 1 de dano sempre que for atacado
        if (danoFinal < 1) danoFinal = 1;

        vida = Math.max(0, vida - danoFinal);
        return danoFinal;
    }
    
    public void aumentarVidaMax(int valor) {
        vidaMax += valor;
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
        if (armadura > 5) armadura = 5; // Agora tem limite máximo pra não travar
    }

    public void adicionarBonusMelee(int valor) {
        bonusMelee += valor;
    }

    // Vida extra: se morrer e tiver renascimento, revive
    public boolean tentarRenascer() {
        if (renascimentos <= 0) return false;

        renascimentos--;

        vida = Math.max(1, vidaMax / 2);
        // bônus pequeno para não travar
        adicionarMunicao(10);

        return true;
    }
}