# 🧟 Zombies Roguelike - Java Console Game

Um jogo estilo **Roguelike em Java**, rodando no console, com sistema de rounds infinitos, zumbis especiais, boss fights e progressão por habilidades.

Projeto focado em prática de lógica, orientação a objetos e arquitetura estilo backend.

---

## 🎮 Mecânicas do Jogo

### 🔁 Sistema de Rounds Infinitos
- A cada round, a quantidade de zumbis aumenta.
- Fórmula atual: `round * 2`
- Vida dos zumbis escala progressivamente.
- Dano dos inimigos escala com o round.

---

### 👹 Zumbi Especial
- A cada **3 rounds**, surge um zumbi especial aleatório.
- Cada especial possui:
  - Vida própria
  - Dano próprio
  - Escalonamento com o round
- Tipos atuais:
  - Tank
  - Runner
  - Tóxico
  - Exploder

---

### 🔥 Sistema de Boss
- A cada **10 rounds**, surge um Boss.
- O Boss possui:
  - Vida escalável
  - Dano escalável
  - Combate separado dos zumbis normais
- Sistema preparado para expansão futura com habilidades únicas.

---

### 🧬 Sistema de Habilidades (Roguelike)

A cada **4 rounds**, o jogador escolhe entre **2 habilidades aleatórias**.

As habilidades são permanentes e acumulativas durante a partida.

Habilidades atuais:

- **Força Bruta** → +1 de dano permanente
- **Colete** → +1 de armadura (limitado)
- **Mira Afiada** → aumenta dano corpo-a-corpo
- **Fúria** → dano extra quando vida está baixa
- **Regeneração** → cura por round
- **Vida Extra** → aumenta vida máxima
- **Carregador Estendido** → bônus de munição
- **Sorte** → aumenta chance de drop
- **Economia** → chance de não gastar bala
- **Segunda Chance** → +1 renascimento (limitado)

---

### 💀 Sistema de Renascimento
- Drop raro concede **+1 Renascimento**
- Ao morrer:
  - Se tiver renascimento → revive automaticamente com metade da vida
  - Se não tiver → Fim de jogo

---

### 🎁 Sistema de Drops
Após cada round:

- 10% → Renascimento
- 20% → Kit Médico (+3 vida)
- 40% → Munição extra
- 30% → Nenhum drop

Sistema preparado para influência de habilidades futuras.

---

### ⚔ Sistema de Combate

- Cada arma possui dano base diferente.
- O dano final considera bônus de habilidades.
- Armadura reduz dano recebido (com limite máximo).
- Sistema de **melee (faca)** quando sem munição.
- Combate corpo-a-corpo possui risco adicional.
- Dano mínimo garantido (evita estado imortal).
- Vida nunca fica negativa.

---

## 🛠 Melhorias e Correções Recentes

- Corrigido bug de vida negativa.
- Corrigido cálculo incorreto de dano com armadura.
- Implementado dano mínimo garantido.
- Adicionado limite de armadura.
- Implementado sistema de melee para evitar travamentos.
- Corrigido erro de campo `final` em vidaMax.
- Refatorado método de consumo de munição.
- Escalonamento de dificuldade balanceado.
- Integração completa do sistema expandido de Player.

---

## 🧱 Arquitetura do Projeto

Estrutura organizada em estilo backend:

```
src/
 └── jogo/
      Main.java
      GameEngine.java
      Player.java
      Weapon.java
      DropSystem.java
      ZombieSpecial.java
      Power.java
      Boss.java
```

Separação de responsabilidades:

- `Main` → Inicializa o jogo
- `GameEngine` → Controla rounds e fluxo
- `Player` → Estado e atributos do jogador
- `Weapon` → Definição de armas
- `ZombieSpecial` → Tipos especiais
- `Boss` → Sistema de boss escalável
- `Power` → Sistema de habilidades
- `DropSystem` → Sorteio de drops

---

## ▶ Como Executar

Compile:
```
javac -d out src/jogo/*.java
```

Execute:
```
java -cp out jogo.Main
```

---

## 🚀 Próximas Implementações

- [ ] Sistema de Loja entre rounds
- [ ] Sistema de Eventos Aleatórios
- [ ] Modo Hardcore
- [ ] Sistema de Moeda
- [ ] Inimigos Elite
- [ ] Sistema de Crítico
- [ ] Melhorias visuais no console

---

## 🎯 Objetivo do Projeto

Este projeto foi criado para:

- Praticar lógica em Java
- Aprender orientação a objetos
- Organizar código em arquitetura limpa
- Simular estrutura de backend
- Evoluir futuramente para versão com Spring Boot

---

🧟 Projeto em evolução constante.