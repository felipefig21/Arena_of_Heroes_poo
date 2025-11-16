# Arena of Heroes - Projeto POO em Java

## Estrutura do Projeto

### Classes Concretas de Domínio (6):
1. **Heroi** - Personagem jogador com experiência
2. **Vilao** - Antagonista com nível variável
3. **Item** - Objeto que pode ser coletado
4. **Luta** - Gerencia combates entre personagens
5. **Arena** - Localidade que contém heróis e vilões
6. **Jogador** - Wrapper do jogador com suas vitórias

### Enum (1):
- **TipoAtaque** - Define tipos de ataque (FISICO, MAGICO, ESPECIAL) com valores de dano

### Interface (1):
- **Personagem** - Implementada por Heroi e Vilao (2+ classes)
  - Métodos: atacar(), getVida(), getNome(), receberDano(), estaVivo()

### Classe Abstrata (1):
- **SerVivo** - Superclasse de Heroi e Vilao
  - Atributos: nome, vida, vidaMaxima, inventario
  - Métodos concretos: calcularDano(), adicionarItem(), receberDano(), getters
  - Métodos abstratos: atacar()

## Polimorfismo Demonstrado

### 1. Sobrescrita:
- Heroi.atacar() vs Vilao.atacar() - implementações diferentes

### 2. Sobrecarga:
- Vilao.atacar(Personagem) - sem tipo
- Vilao.atacar(Personagem, TipoAtaque) - com tipo

### 3. Chamadas Polimórficas:
- Luta recebe Personagem e chama atacar() polimorficamente
- AppRoteiro usa Personagem p1 = heroi; p1.atacar(p2);

## Cardinalidades e Relacionamentos

### 1 para 1 (Composição):
- Jogador -> Heroi (um jogador tem um herói)

### 1 para Muitos (Agregação):
- Arena -> Lista de Viloes (agregação)
- Arena -> Set de Herois
- SerVivo -> Lista de Items (inventário)

### Muitos para Muitos (Agregação):
- Luta -> Armazena múltiplas lutas (Map)
- Arena -> Lutas entre heróis e vilões

### Relacionamento Bidirecional:
- Arena gerencia Heroi/Vilao e Luta (que referencia ambos)

## Coleções Utilizadas

### Set com Verificação de Duplicidade:
- Arena.herois (HashSet) - não permite heróis duplicados

### List com Ordenação:
- Arena.viloes (ArrayList) - ordenada por nível com Collections.sort
- SerVivo.inventario (ArrayList) - com verificação de duplicidade

### Map:
- Arena.lutas (HashMap) - armazena lutas por chave

## Composição e Agregação

### Composição:
- Jogador POSSUI um Heroi (vida útil ligada)
- SerVivo POSSUI um inventário (Lista de Items)

### Agregação:
- Arena CONTÉM vilões e heróis (vida independente)
- Luta REFERENCIA personagens (não os cria)

## Estrutura de Pacotes

```
src/
├── modelo/
│   ├── TipoAtaque.java (enum)
│   ├── Personagem.java (interface)
│   ├── SerVivo.java (classe abstrata)
│   ├── Heroi.java
│   ├── Vilao.java
│   ├── Item.java
│   ├── Luta.java
│   ├── Arena.java
│   └── Jogador.java
└── app/
    ├── AppInterativo.java (com Scanner)
    └── AppRoteiro.java (determinístico)
```

## Invariantes de Classe

- **SerVivo**: vida > 0, nome não-vazio
- **Vilao**: nível > 0
- **Item**: poder > 0, nome não-vazio
- **Jogador**: herói não-nulo, nome não-vazio

## Execução

### AppRoteiro (Determinístico):
```bash
cd bin
java app.AppRoteiro
```

### AppInterativo (Com Scanner):
```bash
cd bin
java app.AppInterativo
```

## Requisitos Atendidos

✓ 6 classes concretas de domínio
✓ 1 enum (TipoAtaque)
✓ 1 interface implementada por 2+ classes
✓ Hierarquia com classe abstrata, atributos, métodos concretos e abstratos
✓ Polimorfismo com sobrecarga, sobrescrita e chamadas polimórficas
✓ Atributos private com invariantes garantidos
✓ Cardinalidades 1-1, 1-N, N-N com relacionamento bidirecional
✓ Composição e agregação
✓ Coleções com duplicidade e ordenação
✓ Dois aplicativos (interativo e roteiro)
✓ Lógica de negócio no domínio, main sem lógica
✓ Organização em pacotes simples

