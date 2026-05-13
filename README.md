# Sistema de Gerenciamento Escolar - POO (Java)

Este é um projeto acadêmico desenvolvido em **Java** com o objetivo de aplicar e demonstrar os conceitos de **Programação Orientada a Objetos (POO)**. O sistema simula o gerenciamento de uma escola, permitindo o controle de alunos, professores, turmas e disciplinas.

## Funcionalidades

*   **Gerenciamento de Alunos:** Cadastro, edição, remoção e consulta de alunos.
*   **Gerenciamento de Professores:** Cadastro de professores e suas respectivas especialidades.
*   **Gerenciamento de Disciplinas e Turmas:** Criação de turmas e alocação de professores e alunos em disciplinas específicas.
*   **Controle de Notas e Faltas:** Lançamento de notas e registro de presenças para os alunos em cada disciplina.
*   **Geração de Boletins:** Emissão do boletim do aluno com médias e status de aprovação.

##  Tecnologias Utilizadas

*   **Linguagem:** Java
*   **Paradigma:** Programação Orientada a Objetos
*   **IDE:** Visual Studio Code 

## Conceitos de POO Aplicados

O projeto foi estruturado utilizando os quatro pilares fundamentais da Programação Orientada a Objetos:

1.  **Abstração:** Modelagem de entidades do mundo real (como `Aluno`, `Professor`, `Turma`) em classes Java.
2.  **Encapsulamento:** Proteção dos dados sensíveis das classes utilizando modificadores de acesso (`private`, `protected`) e métodos `getters` e `setters`.
3.  **Herança:** Criação de classes base (como uma superclasse `Pessoa`) para compartilhar atributos comuns (nome, CPF, endereço) com subclasses (`Aluno`, `Professor`).
4.  **Polimorfismo:** Implementação de métodos com o mesmo nome, mas comportamentos diferentes, seja através de sobrescrita (override) ou sobrecarga (overload).

##  Estrutura do Projeto

O projeto segue a estrutura padrão do Java:

```text
ProjetoPOO/
├── src/          # Código-fonte (.java)
│   ├── models/   # Classes de modelo (Aluno, Professor, etc.)
│   ├── views/    # Interface com o usuário (Console ou GUI)
│   └── Main.java # Classe principal de execução
├── bin/          # Arquivos compilados (.class)
└── README.md     # Documentação do projeto
```

## ⚙️ Como Executar

### Pré-requisitos
*   [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) instalado na máquina (versão 11 ou superior recomendada).

### Passos
1.  Clone o repositório ou baixe os arquivos fonte.
2.  Abra o terminal e navegue até o diretório raiz do projeto:
    ```bash
    cd ProjetoPOO
    ```
3.  Compile os arquivos `.java` localizados na pasta `src`:
    ```bash
    javac -d bin src/**/*.java
    ```
    *(Nota: Dependendo da sua IDE, a compilação pode ser automática)*
4.  Execute a classe principal (substitua `Main` pelo nome do pacote e classe principal, caso necessário):
    ```bash
    java -cp bin Main
    ```

## Autor

*   **Mateus** - *Desenvolvedor*
