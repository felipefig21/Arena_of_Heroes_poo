# Exemplos de Uso - Arena of Heroes

## Estrutura do Código Minimalista

### Total de Linhas por Classe:
- TipoAtaque.java: 15 linhas (enum)
- Personagem.java: 8 linhas (interface)
- SerVivo.java: 61 linhas (abstrata)
- Heroi.java: 20 linhas
- Vilao.java: 20 linhas
- Item.java: 26 linhas
- Luta.java: 40 linhas
- Arena.java: 45 linhas
- Jogador.java: 26 linhas
- AppInterativo.java: 90 linhas
- AppRoteiro.java: 62 linhas
- **TOTAL: ~413 linhas (muito compacto!)**

## 1. Demonstração de Invariantes

```java
// ✅ Válido
Heroi heroi = new Heroi("Artur", 100);

// ❌ Lança exceção - vida deve ser > 0
Heroi heroi = new Heroi("Artur", 0);

// ❌ Lança exceção - nome vazio
Heroi heroi = new Heroi("", 100);

// ✅ Válido
Vilao vilao = new Vilao("Morgana", 100, 2);

// ❌ Lança exceção - nível deve ser > 0
Vilao vilao = new Vilao("Morgana", 100, 0);
```

## 2. Demonstração de Composição vs Agregação

```java
// COMPOSIÇÃO: Jogador → Heroi (vida útil acoplada)
Heroi heroi = new Heroi("Artur", 100);
Jogador jogador = new Jogador("João", heroi);
// Se jogador morrer/deletar, heroi morre junto

// AGREGAÇÃO: Arena → Viloes (vida útil independente)
Arena arena = new Arena("Arena dos Heróis");
Vilao vilao = new Vilao("Morgana", 100, 1);
arena.adicionarVilao(vilao);
// Se arena deletar, vilao continua existindo
```

## 3. Demonstração de Polimorfismo

### 3.1 Sobrescrita:
```java
SerVivo heroi = new Heroi("Artur", 100);
SerVivo vilao = new Vilao("Morgana", 80, 1);

// Ambos implementam o método abstrato de forma diferente
heroi.atacar(vilao);  // Ataque FISICO + experiência
vilao.atacar(heroi);  // Ataque MAGICO + nível
```

### 3.2 Sobrecarga:
```java
Vilao vilao = new Vilao("Morgana", 100, 2);

// Sobrecarga - duas versões do mesmo método
vilao.atacar(alvo);                    // Usa TipoAtaque.MAGICO por padrão
vilao.atacar(alvo, TipoAtaque.FISICO); // Usa tipo especificado
```

### 3.3 Chamadas Polimórficas:
```java
Personagem p1 = new Heroi("Artur", 100);    // Interface
Personagem p2 = new Vilao("Morgana", 80, 1);

// Em tempo de execução, chama a implementação correta
p1.atacar(p2);  // Executa Heroi.atacar()
p2.atacar(p1);  // Executa Vilao.atacar()

// Sem saber em tempo de compilação qual tipo real é
ArrayList<Personagem> combatentes = new ArrayList<>();
combatentes.add(p1);
combatentes.add(p2);

for (Personagem p : combatentes) {
    p.atacar(p);  // Polimorfismo em ação!
}
```

## 4. Demonstração de Cardinalidades

### 1 para 1 (Composição):
```
┌─────────┐         ┌─────────┐
│ Jogador │ 1  ───→ 1│ Heroi   │
│  João   │         │ Artur   │
└─────────┘         └─────────┘
```
```java
Jogador jogador = new Jogador("João", heroi);
// Cada jogador tem exatamente um herói
// E cada herói pertence a um jogador
```

### 1 para Muitos (Agregação):
```
        ┌────────────────────┐
        │      Arena         │
        │  Arena dos Heróis  │
        └────────────────────┘
                  │
         ┌────────┼────────┐
         ↓        ↓        ↓
      ┌──────┐ ┌──────┐ ┌──────┐
      │Vilao │ │Vilao │ │Vilao │
      │Morg  │ │Lanc  │ │Demo  │
      └──────┘ └──────┘ └──────┘
```
```java
Arena arena = new Arena("Arena dos Heróis");
arena.adicionarVilao(new Vilao("Morgana", 100, 2));
arena.adicionarVilao(new Vilao("Lancelot", 80, 1));
```

### Muitos para Muitos (através de Luta):
```
      ┌──────────────┐
      │    Heroi     │
      │    Artur     │
      └──────────────┘
              │
          ┌───┴───┐
          ↓       ↓
      ┌────────────────┐
      │   Luta vs      │
      │   Morgana      │
      └────────────────┘
              ↑       ↑
          ┌───┴───┐
      ┌──────────────┐
      │    Vilao     │
      │   Morgana    │
      └──────────────┘
```
```java
Luta luta1 = arena.criarLuta(heroi, vilao1);
Luta luta2 = arena.criarLuta(heroi, vilao2);
// Um herói pode lutar com múltiplos vilões
// Um vilão pode lutar com múltiplos heróis
```

## 5. Demonstração de Coleções

### Set com Verificação de Duplicidade:
```java
Arena arena = new Arena("Arena");
Heroi heroi1 = new Heroi("Artur", 100);
Heroi heroi2 = new Heroi("Lancelot", 100);

arena.adicionarHeroi(heroi1);
arena.adicionarHeroi(heroi2);
arena.adicionarHeroi(heroi1);  // Não adiciona (já existe)

Set<Heroi> herois = arena.getHerois();
System.out.println(herois.size()); // Sempre 2
```

### List com Ordenação:
```java
Arena arena = new Arena("Arena");
arena.adicionarVilao(new Vilao("Lancelot", 80, 1));
arena.adicionarVilao(new Vilao("Morgana", 100, 2));
arena.adicionarVilao(new Vilao("Demônio", 120, 3));

arena.ordenarViloes(); // Ordena por nível decrescente

for (Vilao v : arena.getViloes()) {
    System.out.println(v.getNome() + " - Nível: " + v.getNivel());
}
// Saída:
// Demônio - Nível: 3
// Morgana - Nível: 2
// Lancelot - Nível: 1
```

### Map para Armazenamento:
```java
// Arena armazena todas as lutas em um Map
Map<String, Luta> lutas = new HashMap<>();
lutas.put("Artur-Morgana", luta1);
lutas.put("Artur-Lancelot", luta2);

// Permite recuperar luta por chave
Luta luta = lutas.get("Artur-Morgana");
```

## 6. Fluxo de Execução Típico

### AppRoteiro:
```
1. Criar Arena
2. Criar Heroi e Viloes
3. Criar Jogador
4. Adicionar items aos personagens
5. Ordenar vilões por nível
6. Criar Luta
7. Executar turnos até finalizar
8. Verificar ganhador
9. Demonstrar polimorfismo
```

### AppInterativo:
```
1. Pedir nome do jogador
2. Criar Heroi
3. Criar Viloes
4. Menu principal:
   - Iniciar Luta (selecionar vilão)
   - Ver Estado (mostrar vida, experiência, vitórias)
   - Sair
5. Em uma Luta:
   - Mostrar estado
   - Opções: Atacar, Ataque Especial, Sair
   - Executar turnos até finalizar
```

## 7. Reação em Cadeia Polimórfica

```java
class Luta {
    public void executarTurno() {
        heroi.atacar(vilao);      // ← Chamada polimórfica #1
        
        if (!vilao.estaVivo()) {
            finalizada = true;
            return;
        }
        
        vilao.atacar(heroi);       // ← Chamada polimórfica #2
        
        if (!heroi.estaVivo()) {
            finalizada = true;
        }
    }
}

// Em tempo de execução:
// - Se heroi = Heroi, executa Heroi.atacar()
// - Se vilao = Vilao, executa Vilao.atacar()
// - Sistema chama automaticamente a versão correta
```

## 8. Exemplo Completo de Uso

```java
// Criar personagens
Heroi heroi = new Heroi("Artur", 150);
Vilao vilao = new Vilao("Morgana", 100, 2);

// Adicionar itens
heroi.adicionarItem(new Item("Excalibur", 10));
vilao.adicionarItem(new Item("Magia Negra", 8));

// Criar arena e registrar personagens
Arena arena = new Arena("Arena dos Heróis");
arena.adicionarHeroi(heroi);
arena.adicionarVilao(vilao);

// Criar jogador
Jogador jogador = new Jogador("João", heroi);

// Criar luta
Luta luta = arena.criarLuta(heroi, vilao);

// Executar combate
while (!luta.isFinalizada()) {
    System.out.println(luta.getEstado());
    luta.executarTurno();
}

// Registrar resultado
if (luta.getGanhador() == heroi) {
    jogador.registrarVitoria();
    System.out.println("Vitória! Total: " + jogador.getVitorias());
}
```

