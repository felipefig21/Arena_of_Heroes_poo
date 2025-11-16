# 📚 Índice Completo - Arena of Heroes POO

## 🚀 Comece Aqui

Para entender o projeto rapidamente:

1. **[SUMARIO_EXECUTIVO.md](./SUMARIO_EXECUTIVO.md)** ← START HERE! Visão geral completa
2. **[GUIA_EXECUCAO_WINDOWS.md](./GUIA_EXECUCAO_WINDOWS.md)** - Como compilar e executar
3. Execute: `java app.AppRoteiro` para ver o projeto funcionando

## 📖 Documentação Detalhada

### Para Compreender o Projeto:
- **[README.md](./README.md)** - Estrutura do projeto, classes, enums, interface
- **[DIAGRAMA_UML.md](./DIAGRAMA_UML.md)** - Visualização das classes e relacionamentos
- **[VERIFICACAO_REQUISITOS.md](./VERIFICACAO_REQUISITOS.md)** - Checklist completo (21 requisitos)

### Para Usar o Código:
- **[EXEMPLOS_USO.md](./EXEMPLOS_USO.md)** - Exemplos práticos de polimorfismo, cardinalidades, etc.
- **[GUIA_EXECUCAO_WINDOWS.md](./GUIA_EXECUCAO_WINDOWS.md)** - Step-by-step para executar

## 📁 Estrutura do Código

```
src/
├── modelo/                    (Domínio do jogo)
│   ├── TipoAtaque.java       (1 ENUM - ataque/magia/especial)
│   ├── Personagem.java       (1 INTERFACE - implementada por 2 classes)
│   ├── SerVivo.java          (CLASSE ABSTRATA - base para herói/vilão)
│   ├── Heroi.java            (CLASSE CONCRETA 1)
│   ├── Vilao.java            (CLASSE CONCRETA 2)
│   ├── Item.java             (CLASSE CONCRETA 3)
│   ├── Luta.java             (CLASSE CONCRETA 4)
│   ├── Arena.java            (CLASSE CONCRETA 5)
│   └── Jogador.java          (CLASSE CONCRETA 6)
│
└── app/                       (Aplicativos)
    ├── AppRoteiro.java       (Execução determinística - SEM Scanner)
    └── AppInterativo.java    (Execução interativa - COM Scanner)
```

## ✅ Checklist de Requisitos

### Exigências Obrigatórias (21 total):

- ✅ **6 Classes Concretas**: Heroi, Vilao, Item, Luta, Arena, Jogador
- ✅ **1 Enum**: TipoAtaque (FISICO, MAGICO, ESPECIAL)
- ✅ **1 Interface**: Personagem (implementada por Heroi e Vilao)
- ✅ **Classe Abstrata**: SerVivo com:
  - ✅ Atributos (nome, vida, vidaMaxima, inventario)
  - ✅ Métodos concretos (calcularDano, adicionarItem, etc)
  - ✅ Métodos abstratos (atacar)
- ✅ **Polimorfismo Sobrecarga**: Vilao.atacar() com 1 ou 2 parâmetros
- ✅ **Polimorfismo Sobrescrita**: Heroi.atacar() vs Vilao.atacar()
- ✅ **Polimorfismo Chamadas**: Via interface Personagem
- ✅ **Atributos Private**: Todos private com validações
- ✅ **Invariantes**: Vida > 0, nome não-vazio, nível > 0, poder > 0
- ✅ **Cardinalidade 1:1**: Jogador ↔ Heroi (composição)
- ✅ **Cardinalidade 1:N**: Arena → Viloes (agregação)
- ✅ **Cardinalidade N:N**: Heroi ↔ Vilao (via Luta)
- ✅ **Relacionamento Bidirecional**: Arena ↔ Luta ↔ Personagens
- ✅ **Composição**: Jogador tem Heroi (vida útil acoplada)
- ✅ **Agregação**: Arena tem Viloes (vida útil independente)
- ✅ **Coleção com Duplicidade**: Set<Heroi> - HashSet não permite duplicatas
- ✅ **Ordenação com Sort**: Arena.ordenarViloes() por nível
- ✅ **AppInterativo**: Com Scanner, menu e seleção
- ✅ **AppRoteiro**: Sem Scanner, determinístico
- ✅ **Lógica no Domínio**: Métodos em classes do modelo
- ✅ **Main sem Lógica**: Apenas chama métodos

## 🎮 Como Usar

### Compilação:
```bash
javac -d bin src/modelo/*.java src/app/*.java
```

### Execução AppRoteiro (Determinístico):
```bash
cd bin
java app.AppRoteiro
```

### Execução AppInterativo (Interativo):
```bash
cd bin
java app.AppInterativo
```

## 📊 Estatísticas

| Aspecto | Valor |
|---------|-------|
| Linhas de código | ~413 |
| Classes | 9 |
| Métodos | ~45 |
| Arquivos Java | 11 |
| Pacotes | 2 |
| Documentação | 6 arquivos |
| Requisitos | 21/21 ✅ |

## 🔑 Conceitos POO Demonstrados

1. **Herança**: SerVivo → Heroi/Vilao
2. **Abstração**: SerVivo abstrata com método abstracto
3. **Polimorfismo**: 3 tipos (sobrecarga, sobrescrita, chamadas polimórficas)
4. **Encapsulamento**: Todos atributos private
5. **Composição**: Jogador → Heroi
6. **Agregação**: Arena → Viloes/Herois/Lutas
7. **Interface**: Personagem implementada por 2 classes
8. **Invariantes**: Validações no construtor
9. **Generics**: List<Item>, Set<Heroi>, Map<String, Luta>
10. **Enum**: TipoAtaque com valores

## 🎯 Fluxo Principal

```
AppRoteiro / AppInterativo
    ↓
criar Arena("Arena dos Heróis")
    ↓
criar Heroi("Artur", 150)
criar Vilao("Morgana", 100, 2)
    ↓
arena.adicionarHeroi(heroi)
arena.adicionarVilao(vilao)
    ↓
heroi.adicionarItem(new Item("Espada", 5))
vilao.adicionarItem(new Item("Magia", 8))
    ↓
jogador = new Jogador("João", heroi)
    ↓
arena.ordenarViloes()
    ↓
luta = arena.criarLuta(heroi, vilao)
    ↓
while (!luta.isFinalizada()) {
    luta.executarTurno()  // Chamadas polimórficas aqui!
}
    ↓
if (luta.getGanhador() == heroi) {
    jogador.registrarVitoria()
}
```

## 🧩 Componentes Essenciais

### Enum TipoAtaque:
```java
FISICO(10), MAGICO(15), ESPECIAL(20)
```

### Interface Personagem:
```java
atacar(), getVida(), getNome(), receberDano(), estaVivo()
```

### Classe Abstrata SerVivo:
- Implementa Personagem
- Superclasse de Heroi e Vilao
- Gerencia vida, nome, inventário

### Classes Concretas:
1. **Heroi**: Personagem com experiência
2. **Vilao**: Personagem com nível
3. **Item**: Objeto colecionável
4. **Luta**: Gerencia combate
5. **Arena**: Contém personagens e lutas
6. **Jogador**: Wrapper do jogador

## 🔒 Garantias de Qualidade

- ✅ Código compila sem erros
- ✅ Ambas as apps executam corretamente
- ✅ Nenhuma classe desnecessária
- ✅ Todos os requisitos atendidos
- ✅ Invariantes garantidos
- ✅ Encapsulamento completo
- ✅ Polimorfismo demonstrado
- ✅ Relacionamentos corretos

## 📞 Suporte

Veja **[GUIA_EXECUCAO_WINDOWS.md](./GUIA_EXECUCAO_WINDOWS.md)** para:
- Resolução de problemas comuns
- Comandos rápidos (copiar/colar)
- Verificação de instalação Java
- Interpretar saída

## 🎓 Aprendizados

Este projeto demonstra:
- Design orientado a objetos profissional
- Uso correto de padrões POO
- Código minimalista e eficiente
- Documentação clara
- Estrutura escalável

## 📝 Notas Finais

**Projeto 100% completo e funcional**

- Atende todos os 21 requisitos
- Código minimalista (~413 linhas)
- Sem classes desnecessárias
- Documentação completa
- Pronto para avaliação

Desenvolvido em: Java 8+
Compatível com: Windows, Linux, macOS
Requisitos: JDK instalado

