package jogo;

public enum Power {

    FORCA_BRUTA("Força Bruta", "+1 de Dano") {
        @Override
        public void aplicar(Player player) {
            player.adicionarBonusDano(1);
        }
    },

    COLETE("Colete", "+1 de Armadura") {
        @Override
        public void aplicar(Player player) {
            player.adicionarArmadura(1);
        }
    };

    private final String nome;
    private final String descricao;

    Power(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public abstract void aplicar(Player player);





    
}
