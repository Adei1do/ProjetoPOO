# 🎯 Caminho Feliz - Sistema Escolar POO

## Estrutura de Classes

```
         Pessoa (abstrata)
            ├─ Aluno
            ├─ Professor
            ├─ Responsavel
            ├─ Direcao
            └─ Secretaria

+ Disciplina, Turma, Nota, Boletim, Repositorio<T>
```

---

##  Fluxo Correto de Execução

### **1. Criar Disciplinas**
```java
Disciplina mat = new Disciplina("Matemática", "MAT101", 60);
Disciplina port = new Disciplina("Português", "PORT101", 60);
Disciplina ing = new Disciplina("Inglês", "ING101", 40);
```

### **2. Criar Turma e Adicionar Disciplinas**
```java
Turma turma1A = new Turma("1º Ano A", 1);
turma1A.adicionarDisciplina(mat);
turma1A.adicionarDisciplina(port);
turma1A.adicionarDisciplina(ing);
```

### **3. Criar Professores com Disciplina**
```java
Professor prof1 = new Professor("José", "333...", "Rua C", "3333-3333", 
                                "jose@escola.com", "PROF01", mat);
prof1.adicionarTurma(turma1A);

Professor prof2 = new Professor("Maria", "444...", "Rua D", "4444-4444", 
                                "maria@escola.com", "PROF02", ing);
prof2.adicionarTurma(turma1A);
```

### **4. Criar Alunos e Adicionar à Turma**
```java
Aluno aluno1 = new Aluno("Mateus Silva", "111...", "Rua A", "1111-1111", 
                         "mateus@email.com", "MAT01", turma1A, 1);
turma1A.adicionarAluno(aluno1);

Aluno aluno2 = new Aluno("Adeildo Silva", "222...", "Rua B", "2222-2222", 
                         "adeildo@email.com", "MAT02", turma1A, 1);
turma1A.adicionarAluno(aluno2);
```

### **5. Criar Responsáveis e Vincular aos Alunos**
```java
Responsavel pai = new Responsavel("João", "444...", "Rua A", "4444-4444", 
                                  "joao@email.com", "Pai");
aluno1.adicionarResponsavel(pai);
```

### **6. Registrar Notas no Boletim**
```java
Nota nota1 = new Nota(aluno1, mat, 8.5);
Nota nota2 = new Nota(aluno1, port, 9.0);
Nota nota3 = new Nota(aluno1, ing, 7.5);

aluno1.getBoletim().adicionarNota(nota1);
aluno1.getBoletim().adicionarNota(nota2);
aluno1.getBoletim().adicionarNota(nota3);
```

### **7. Usar Classe Genérica (Repositorio<T>)**
```java
// Funciona com qualquer tipo de objeto
Repositorio<Aluno> repoAlunos = new Repositorio<>();
repoAlunos.adicionar(aluno1);
repoAlunos.adicionar(aluno2);
System.out.println("Total de alunos: " + repoAlunos.contar()); // 2

Repositorio<Professor> repoProfessores = new Repositorio<>();
repoProfessores.adicionar(prof1);
repoProfessores.adicionar(prof2);
System.out.println("Total de professores: " + repoProfessores.contar()); // 2
```

### **8. Gerar Relatórios**
```java
// Listar alunos da turma
for (Aluno a : turma1A.getAlunos()) {
    System.out.println(a.apresentar());
}

// Ver boletim do aluno
System.out.println("Média: " + aluno1.getBoletim().calcularMedia());
for (Nota n : aluno1.getBoletim().getNotas()) {
    System.out.println(n.getDisciplina().getNome() + ": " + n.getValor());
}

// Ver responsáveis
for (Responsavel r : aluno1.getResponsaveis()) {
    System.out.println(r.apresentar());
}

// Usar polimorfismo (ArrayList de Pessoa)
ArrayList<Pessoa> pessoas = new ArrayList<>();
pessoas.add(aluno1);
pessoas.add(prof1);
pessoas.add(pai);

for (Pessoa p : pessoas) {
    System.out.println(p.apresentar()); // Cada um apresenta diferente
}
```

---

## 🔗 Relacionamentos Entre Classes

| Relacionamento | Tipo | Exemplo |
|---|---|---|
| Aluno → Turma | N-1 | 1 aluno em 1 turma |
| Aluno → Responsavel | N-N | 1 aluno com N responsáveis |
| Aluno → Boletim | 1-1 | 1 aluno com 1 boletim |
| Boletim → Nota | 1-N | 1 boletim com N notas |
| Nota → Disciplina | N-1 | N notas de 1 disciplina |
| Professor → Disciplina | N-1 | 1 professor ensina 1 disciplina |
| Professor → Turma | N-N | 1 professor ensina N turmas |
| Turma → Disciplina | N-N | 1 turma tem N disciplinas |

---

## 📚 Conceitos de POO Demonstrados

| Conceito | Implementação |
|----------|--------------|
| **Herança** | Classe `Pessoa` é superclasse de `Aluno`, `Professor`, etc |
| **Encapsulamento** | Atributos `private` com getters/setters |
| **Polimorfismo** | Método `apresentar()` sobrescrito em cada subclasse |
| **Composição** | `Aluno` contém `Boletim`, `Turma` contém `Aluno` |
| **Associação** | `Professor` associado a `Disciplina` |
| **Generics** | `Repositorio<T>` funciona com qualquer tipo `T` |

---

## 🎓 Principais Métodos

### **Pessoa (abstrata)**
- `getNome()`, `getCpf()`, `getEndereco()`, etc
- `apresentar()` (abstrato - implementado em cada subclasse)

### **Aluno**
- `adicionarResponsavel(Responsavel)`
- `getBoletim()` → retorna boletim do aluno
- `apresentar()` → "Aluno: Nome | RA: MAT01 | Turma: 1º Ano A"

### **Turma**
- `adicionarAluno(Aluno)`
- `adicionarDisciplina(Disciplina)`
- `getAlunos()`, `getDisciplinas()`

### **Boletim**
- `adicionarNota(Nota)`
- `getNotas()` → retorna ArrayList de notas
- `calcularMedia()` → média aritmética das notas

### **Repositorio<T>** (Genérica)
- `adicionar(T)`
- `remover(T)`
- `listar()` → ArrayList com todos
- `contar()` → número de itens
- `estaVazio()`

---

## ⚡ Resumo Rápido

1. **Criar dados base** (Disciplinas, Turma)
2. **Cadastrar pessoas** (Professor, Aluno, Responsavel)
3. **Fazer associações** (Professor → Turma, Aluno → Turma, Aluno → Responsavel)
4. **Registrar desempenho** (Notas → Boletim)
5. **Consultar informações** (Relatórios com polimorfismo)
6. **Usar Generics** (Repositorio<T> para reutilizar código)

---

## 📝 Arquivo Main.java Completo

Veja o arquivo `src/main/Main.java` para um exemplo prático com todos os passos implementados.
