# Guia de Execução no Windows

## Pré-requisitos

✅ Java JDK instalado (versão 8 ou superior)
✅ Variável de ambiente JAVA_HOME configurada
✅ Terminal PowerShell ou CMD

## Verificar Instalação Java

```powershell
java -version
javac -version
```

Ambos devem retornar a versão do Java.

## Passo 1: Abrir Terminal

### Opção A - PowerShell:
1. Pressione `Win + X`
2. Selecione "Windows PowerShell" ou "Terminal"
3. Navegue para o projeto

### Opção B - CMD:
1. Pressione `Win + R`
2. Digite `cmd`
3. Pressione Enter

## Passo 2: Navegar para o Projeto

```powershell
cd C:\Users\phill\IdeaProjects\Arena_of_Heroes_poo
```

## Passo 3: Compilar

```powershell
# PowerShell
javac -d bin src/modelo/*.java src/app/*.java

# CMD (alternativa)
javac -d bin src\modelo\*.java src\app\*.java
```

**Resultado esperado**: Sem mensagens de erro

## Passo 4: Executar

### Opção A - AppRoteiro (Determinístico, sem interação)

```powershell
cd bin
java app.AppRoteiro
```

**Saída esperada**:
```
=== Arena of Heroes - Roteiro ===

Criação de personagens:
- Artur (Vida: 150)
- Morgana (Vida: 100, Nível: 2)
- Inventário do herói: 2 itens

Jogador criado: Piloto
Herói do jogador: Artur

Vilões ordenados por nível:
- Morgana (Nível: 2)
- Lancelot (Nível: 1)

[... execução da luta ...]
```

### Opção B - AppInterativo (Com interação)

```powershell
cd bin
java app.AppInterativo
```

**Fluxo esperado**:
1. Pedir nome do jogador
2. Mostrar menu com opções
3. Você pode:
   - `1` - Iniciar Luta
   - `2` - Ver Estado
   - `3` - Sair

## Passo 5 (Opcional): Limpar Compilação

```powershell
# Para recompilar do zero
rm -r bin -Force
mkdir bin
javac -d bin src/modelo/*.java src/app/*.java
```

## Estrutura de Diretórios Esperada

```
C:\Users\phill\IdeaProjects\Arena_of_Heroes_poo\
├── src\
│   ├── modelo\
│   │   ├── TipoAtaque.java
│   │   ├── Personagem.java
│   │   ├── SerVivo.java
│   │   ├── Heroi.java
│   │   ├── Vilao.java
│   │   ├── Item.java
│   │   ├── Luta.java
│   │   ├── Arena.java
│   │   └── Jogador.java
│   └── app\
│       ├── AppInterativo.java
│       └── AppRoteiro.java
├── bin\
│   ├── modelo\
│   │   ├── TipoAtaque.class
│   │   ├── Personagem.class
│   │   ├── SerVivo.class
│   │   ├── Heroi.class
│   │   ├── Vilao.class
│   │   ├── Item.class
│   │   ├── Luta.class
│   │   ├── Arena.class
│   │   └── Jogador.class
│   └── app\
│       ├── AppInterativo.class
│       └── AppRoteiro.class
├── README.md
├── VERIFICACAO_REQUISITOS.md
├── EXEMPLOS_USO.md
├── DIAGRAMA_UML.md
└── SUMARIO_EXECUTIVO.md
```

## Resolução de Problemas

### Erro: "javac not recognized"
**Solução**: Java não está no PATH
```powershell
# Adicionar ao PATH (PowerShell como Admin)
$env:JAVA_HOME = "C:\Program Files\Java\jdk-VERSION"
$env:Path += ";$env:JAVA_HOME\bin"
```

### Erro: "No such file or directory"
**Solução**: Verifique se está no diretório correto
```powershell
# Mostrar diretório atual
pwd

# Listar arquivos
ls src/modelo/
```

### Erro: "package modelo does not exist"
**Solução**: Certifique-se de compilar corretamente
```powershell
# Use -d bin para colocar classes no diretório correto
javac -d bin src/modelo/*.java src/app/*.java

# Depois execute de dentro de bin
cd bin
java app.AppRoteiro
```

### AppInterativo: Scanner não responde
**Solução**: Tipo Enter após sua entrada

### Erro ao executar: "Could not find main class"
**Solução**: Você deve estar no diretório `bin` para executar
```powershell
cd C:\Users\phill\IdeaProjects\Arena_of_Heroes_poo\bin
java app.AppRoteiro  # Correto

# Não faça isso:
cd C:\Users\phill\IdeaProjects\Arena_of_Heroes_poo
java app.AppRoteiro  # Erro!
```

## Comandos Rápidos (Copiar e Colar)

### Compilação completa:
```powershell
cd C:\Users\phill\IdeaProjects\Arena_of_Heroes_poo; rm -r bin -Force; mkdir bin; javac -d bin src/modelo/*.java src/app/*.java
```

### Executar AppRoteiro:
```powershell
cd C:\Users\phill\IdeaProjects\Arena_of_Heroes_poo\bin; java app.AppRoteiro
```

### Executar AppInterativo:
```powershell
cd C:\Users\phill\IdeaProjects\Arena_of_Heroes_poo\bin; java app.AppInterativo
```

## Interpretando a Saída do AppRoteiro

```
=== Arena of Heroes - Roteiro ===

Criação de personagens:
- Artur (Vida: 150)           # Herói criado com 150 HP
- Morgana (Vida: 100, Nível: 2) # Vilão criado com 100 HP e nível 2
- Inventário do herói: 2 itens # 2 itens adicionados ao herói

Jogador criado: Piloto        # Jogador wrapper criado
Herói do jogador: Artur       # Vinculação herói-jogador

Vilões ordenados por nível:
- Morgana (Nível: 2)          # Vilões ordenados em ordem decrescente
- Lancelot (Nível: 1)

Iniciando luta entre Artur e Morgana

Turno Executando
Artur [150hp] vs Morgana [100hp] - Turno: 0  # Estado inicial
Turno Executando
Artur [130hp] vs Morgana [88hp] - Turno: 1   # Depois de 1 turno
# ... (turnos continuam até alguém morrer)

Luta finalizada!
Ganhador: Morgana              # Resultado final

Demonstração de polimorfismo:
Chamada polimórfica em p1 (Herói):
Morgana agora tem 0 de vida   # Herói ataca vilão

Sobrecarga em Vilão:
Artur agora tem 0 de vida     # Vilão ataca com tipo específico
```

## Interagindo com AppInterativo

```
=== Arena of Heroes ===
Digite seu nome: João           # Digite seu nome e pressione Enter

--- Menu ---
1. Iniciar Luta
2. Ver Estado
3. Sair
Opção: 1                        # Pressione 1 e Enter

Vilões disponíveis:
1. Necromante (Vida: 80, Nível: 1)
2. Demônio (Vida: 120, Nível: 2)
Escolha (1-2): 1                # Escolha 1 ou 2 e pressione Enter

Turno 1
[Herói João] [100hp] vs [Necromante] [80hp] - Turno: 1
1. Atacar | 2. Ataque Especial | 3. Sair
Ação: 1                         # Escolha 1, 2 ou 3 e pressione Enter

# ... (luta continua)
```

## Dicas

1. **Mantenha o PowerShell aberto**: Reutilize a janela para múltiplas execuções
2. **Use setas do teclado**: Reutilize comandos anteriores (seta acima)
3. **Copie o diretório exato**: Use `cd C:\Users\phill\...` do arquivo này
4. **Teste AppRoteiro primeiro**: Ele é determinístico, bom para validar
5. **Crie um script .bat**: Para compilação e execução rápida

## Script Batch (Opcional)

Crie um arquivo `run.bat` no projeto:

```batch
@echo off
cd /d C:\Users\phill\IdeaProjects\Arena_of_Heroes_poo
if exist bin (
    echo Limpando compilacao anterior...
    rmdir /s /q bin
)
echo Compilando...
mkdir bin
javac -d bin src\modelo\*.java src\app\*.java
if errorlevel 1 (
    echo Erro na compilacao!
    pause
    exit /b 1
)
echo.
echo Escolha qual aplicativo executar:
echo 1 - AppRoteiro (determinístico)
echo 2 - AppInterativo (com Scanner)
set /p choice="Opcao: "
cd bin
if "%choice%"=="1" (
    java app.AppRoteiro
) else if "%choice%"=="2" (
    java app.AppInterativo
) else (
    echo Opcao invalida!
)
pause
```

Execute depois com duplo clique ou:
```powershell
.\run.bat
```

## Suporte

Se tiver problemas:
1. Verifique se Java está instalado: `java -version`
2. Confirme o caminho do projeto
3. Certifique-se de compilar antes de executar
4. Execute de dentro do diretório `bin`

