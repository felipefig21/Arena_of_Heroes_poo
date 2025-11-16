# Diagrama UML - Arena of Heroes

## Estrutura de Classes

```
┌─────────────────────────────────────────────────────────────┐
│                     <<interface>>                            │
│                      Personagem                              │
├─────────────────────────────────────────────────────────────┤
│ + atacar(alvo: Personagem): void                             │
│ + getVida(): int                                             │
│ + getNome(): String                                          │
│ + receberDano(dano: int): void                               │
│ + estaVivo(): boolean                                        │
└─────────────────────────────────────────────────────────────┘
           ▲                                    ▲
           │ implements                        │ implements
           │                                    │
    ┌──────────────────┐              ┌────────────────┐
    │                  │              │                │
    │  <<abstract>>    │              │                │
    │  SerVivo         │              │                │
    ├──────────────────┤              │                │
    │ - nome: String   │              │                │
    │ - vida: int      │              │                │
    │ - vidaMaxima: int│              │                │
    │ - inventario[0..*]Item          │                │
    ├──────────────────┤              │                │
    │ + atacar()*      │              │                │
    │ + calcularDano()  │              │                │
    │ + adicionarItem() │              │                │
    │ + receberDano()   │              │                │
    │ + estaVivo()      │              │                │
    │ + getVida()       │              │                │
    │ + getNome()       │              │                │
    │ + getInventario() │              │                │
    │ + getVidaMaxima() │              │                │
    └──────────────────┘              │                │
           △                          │                │
           │ extends                  │                │
           │                          │                │
    ┌──────────┐              ┌───────────┐
    │  Heroi   │              │  Vilao    │
    ├──────────┤              ├───────────┤
    │ - exp: int             │ - nivel: int
    ├──────────┤              ├───────────┤
    │ + atacar()              │ + atacar()
    │ + ataque-│              │ + atacar(tipo)*
    │  Especial()             │ + getNivel()
    │ + getExp()              │
    └──────────┘              └───────────┘
         ▲                          ▲
         │ 1                        │ 1..N
         │ possui                   │ referencias
         │                          │
    ┌─────────────┐           ┌──────────┐
    │  Jogador    │           │   Luta   │
    ├─────────────┤           ├──────────┤
    │ - nome      │           │ - heroi  │
    │ - heroi     │           │ - vilao  │
    │ - vitoria   │           │ - turno  │
    ├─────────────┤           │ - final  │
    │ + registro- │           ├──────────┤
    │  Vitoria()  │           │ + executa
    │ + getVitori │           │  Turno()
    │  as()       │           │ + getGan
    │ + getHeroi()│           │  hador()
    │ + getNome() │           │ + getEst
    └─────────────┘           │  ado()
                              │ + isFina
                              │  lizada()
                              └──────────┘
                                    ▲
                                    │ 0..*
                                    │ armazena
                                    │
                              ┌──────────┐
                              │  Arena   │
                              ├──────────┤
                              │ - nome   │
                              │ - herois │
                              │  (Set)   │
                              │ - viloes │
                              │  (List)  │
                              │ - lutas  │
                              │  (Map)   │
                              ├──────────┤
                              │ + adiciona
                              │  rHeroi()
                              │ + adiciona
                              │  rVilao()
                              │ + criar
                              │  Luta()
                              │ + ordenar
                              │  Viloes()
                              │ + getHero
                              │  is()
                              │ + getVil
                              │  oes()
                              │ + getNome
                              │  ()
                              └──────────┘

    ┌──────────┐
    │  Item    │
    ├──────────┤
    │ - nome   │
    │ - poder  │
    ├──────────┤
    │ + getPod
    │  er()
    │ + getNom
    │  e()
    │ + equals()
    │ + hashCo
    │  de()
    └──────────┘
         ▲
         │ 0..*
         │ possui
         │
    ┌──────────┐
    │ SerVivo  │
    │(inventar)
    └──────────┘

┌─────────────────────────┐
│   <<enum>>              │
│   TipoAtaque            │
├─────────────────────────┤
│ FISICO(10)              │
│ MAGICO(15)              │
│ ESPECIAL(20)            │
├─────────────────────────┤
│ + getDano(): int        │
└─────────────────────────┘
```

## Relacionamentos

### Herança:
- `Heroi extends SerVivo`
- `Vilao extends SerVivo`

### Implementação:
- `SerVivo implements Personagem`

### Composição (vida útil acoplada):
- `Jogador [1] ─── [1] Heroi` - Uma morte implica na outra

### Agregação (vida útil independente):
- `Arena [1] ─── [N] Vilao` - Arena referencia vilões
- `Arena [1] ─── [N] Heroi` - Arena referencia heróis
- `Arena [1] ─── [N] Luta` - Arena armazena lutas
- `SerVivo [1] ─── [N] Item` - Personagem tem itens

### Associação Bidirecional:
- `Luta` referencia tanto `Heroi` quanto `Vilao`

## Padrões Utilizados

### 1. Template Method (implícito):
- `SerVivo.calcularDano()` fornece template
- Subclasses implementam `atacar()` diferentemente

### 2. Strategy (implícito):
- `TipoAtaque` define diferentes estratégias de ataque

### 3. Composite (implícito):
- `Arena` compõe múltiplos personagens

### 4. Wrapper (implícito):
- `Jogador` encapsula `Heroi`

## Multiplicidades

| Associação | Multiplicidade | Tipo |
|-----------|---------------|------|
| Jogador → Heroi | 1 : 1 | Composição |
| Arena → Viloes | 1 : N | Agregação |
| Arena → Herois | 1 : N | Agregação |
| SerVivo → Items | 1 : N | Agregação |
| Luta → Heroi | 1 : 1 | Associação |
| Luta → Vilao | 1 : 1 | Associação |
| Arena → Lutas | 1 : N | Agregação |

## Hierarquia de Tipos

```
Object
 │
 ├─ Personagem (interface)
 │   ├─ SerVivo (abstract)
 │   │  ├─ Heroi
 │   │  └─ Vilao
 │
 ├─ Luta
 ├─ Arena
 ├─ Jogador
 ├─ Item
 └─ TipoAtaque (enum)
```

## Fluxo de Dados

```
┌─────────────┐
│ AppInterativo│─ Scanner ─ Usuário
└────┬────────┘
     │ cria
     ▼
┌─────────────┐         ┌──────────┐
│ Jogador     │◄───────→│ Heroi    │
└─────────────┘         └────┬─────┘
                              │ implementa
                              ▼
                         Personagem
                              ▲
                              │ implementa
                         ┌────┴─────┐
                         │           │
                      SerVivo      Vilao
                         │
                    ┌────┴─────┐
                    │           │
                  Item[0..*]  (composição)

        Arena
         │
         ├─ Herois (Set)
         ├─ Viloes (List)
         └─ Lutas (Map)
              │
              ├─ Luta
              │  ├─ Heroi
              │  └─ Vilao
              │
              └─ Luta
                 ├─ Heroi
                 └─ Vilao
```

