package jogo;

public class Boss {

    private final String nome;
    private final int vidaBase;
    private final int danoBase;

    public Boss(String nome, int vidaBase, int danoBase) {  
        this.nome = nome;
        this.vidaBase = vidaBase;
        this.danoBase = danoBase;
    }
    
    public String getNome() {
        return nome;
    }

    public int calcularVida(int round) {
        return vidaBase + (round * 3);
    }

    public int calcularDano(int round) {
        return danoBase + (round / 5);
    }

}
