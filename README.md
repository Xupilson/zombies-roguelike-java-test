#  Zombies Roguelike - Java Console Game

Um jogo estilo **Roguelike em Java**, rodando no console, com sistema de rounds infinitos, zumbis especiais e progressão por habilidades.

Projeto focado em prática de lógica, orientação a objetos e arquitetura estilo backend.

---

##  Mecânicas do Jogo

###  Sistema de Rounds Infinitos
- A cada round, a quantidade de zumbis aumenta.
- Fórmula atual: `round * 2`

---

###  Zumbi Especial
- A cada **3 rounds**, surge um zumbi especial aleatório.
- Cada especial possui:
  - Vida própria
  - Dano próprio
- Tipos atuais:
  - Tank
  - Runner
  - Tóxico
  - Exploder

---

###  Sistema de Habilidades (Roguelike)

A cada **4 rounds**, o jogador escolhe entre **2 habilidades aleatórias**.

As habilidades são permanentes e acumulativas durante a partida.

Habilidades atuais:

-  **Força Bruta** → +1 de dano permanente
-  **Colete** → +1 de armadura permanente

---

###  Sistema de Renascimento
- Drop raro concede **+1 Renascimento**
- Ao morrer:
  - Se tiver renascimento → revive automaticamente
  - Se não tiver → Fim de jogo

---

###  Sistema de Drops
Após cada round:

- 10% → Renascimento
- 20% → Kit Médico (+3 vida)
- 40% → Munição extra
- 30% → Nenhum drop

---

###  Sistema de Combate

- Cada arma possui dano base diferente.
- O dano final considera bônus de habilidades.
- Armadura reduz dano recebido.
- Economia de bala (em desenvolvimento).

---

##  Arquitetura do Projeto

Estrutura organizada em estilo backend:
src/
jogo/
Main.java
GameEngine.java
Player.java
Weapon.java
DropSystem.java
ZombieSpecial.java
Power.java

Separação de responsabilidades:

- `Main` → Inicializa o jogo
- `GameEngine` → Controla rounds e fluxo
- `Player` → Estado do jogador
- `Weapon` → Definição de armas
- `ZombieSpecial` → Tipos especiais
- `Power` → Sistema de habilidades
- `DropSystem` → Sorteio de drops

---

##  Como Executar

Compile: javac -d out src/jogo/*.java
Execute: java -cp out jogo.Main 

---

##  Próximas Implementações

- [ ] Completar 10 habilidades
- [ ] Regeneração por round
- [ ] Economia de munição por porcentagem
- [ ] Boss a cada 10 rounds
- [ ] Sistema de loja entre rounds
- [ ] Sistema de eventos aleatórios
- [ ] Modo Hardcore

---

##  Objetivo do Projeto

Este projeto foi criado para:

- Praticar lógica em Java
- Aprender orientação a objetos
- Organizar código em arquitetura limpa
- Evoluir para backend com Spring Boot futuramente

---
 Projeto em evolução constante.