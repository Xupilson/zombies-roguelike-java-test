package jogo;

public enum Power {

    // ====== HABILIDADES OFENSIVAS ======
    FORCA_BRUTA("Força Bruta", "+1 de Dano") {
        @Override public void aplicar(Player player) {
            player.adicionarBonusDano(1);
        }
    },

    MIRA_AFIADA("Mira Afiada", "+2 de dano de faca (melee)") {
        @Override public void aplicar(Player player) {
            player.adicionarBonusMelee(2);
        }
    },

    FURIA("Fúria", "Se vida <= 30%, +2 de Dano") {
        @Override public void aplicar(Player player) {
            player.setFuria(true);
        }
    },


    // ====== HABILIDADES DEFENSIVAS ======
    COLETE("Colete", "+1 de Armadura") {
        @Override public void aplicar(Player player) {
            player.adicionarArmadura(1);
        }
    },


    REGENERACAO("Regeneração", "Cura +1 de Vida no fim de cada Round") {
        @Override public void aplicar(Player player) {
            player.setRegeneracaoPorRound(player.getRegeneracaoPorRound() + 1);
        }
    },

    VIDA_EXTRA("Vida Extra", "+3 Na vida maxima e cura +3") {
        @Override public void aplicar(Player player) {
            player.aumentarVidaMax(3);
            player.curar(3);
        }
    },

    // ====== HABILIDADES DE RECURSOS E ECONOMIA ======
    CARREGADOR_ESTENDIDO("Carregador Estendido", "+2 Balas na Recompensa por Round") {
        @Override public void aplicar(Player player) {
            player.setBonusRecompensaBalas(player.getBonusRecompensaBalas() + 2);
        }
    },

    SORTE("Sorte", "+10% de Chance de Drop (menos chance de 'nada')") {
        @Override public void aplicar(Player player) {
            player.setBonusSorteDrop(player.getBonusSorteDrop() + 10);
        }
    },

    ECONOMIA("Economia", "20% de Chance de não gastar bala ao atirar") {
        @Override public void aplicar(Player player) {
            player.setChanceEconomizarBala(player.getChanceEconomizarBala() + 20);
        }
    },

    // ====== RARO / ESPECIAL ====== 
    SEGUNDA_CHANCE("Segunda Chance", "+1 Renascimento (Só pode pegar 1 vez por jogo)") {
        @Override public void aplicar(Player player) {
            if (!player.isSegundaChanceUsada()) {
                player.adicionarRenascimento();
                player.setSegundaChanceUsada(true);
            }
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
