/*Aluno: Mateus Fernandes - 202421901047
Aluno: Adeildo Lopes Mateus Júnior - 202421091016
Aluno: Pedro Lucas Costa de Almeida - 202421901065 */


package main;
import java.util.ArrayList;

import model.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("     SISTEMA DE GERENCIAMENTO ESCOLAR - POO    ");
        System.out.println("==============================================\n");
        
        // ========== CRIANDO DISCIPLINAS ==========
        Disciplina mat = new Disciplina("Matemática", "MAT101", 60);
        Disciplina port = new Disciplina("Português", "PORT101", 60);
        Disciplina ing = new Disciplina("Inglês", "ING101", 40);
        
        // ========== CRIANDO TURMA ==========
        Turma turma1A = new Turma("1º Ano A", 1);
        turma1A.adicionarDisciplina(mat);
        turma1A.adicionarDisciplina(port);
        turma1A.adicionarDisciplina(ing);
        
        // ========== CRIANDO ALUNOS ==========
        Aluno mateus = new Aluno(
            "Mateus Fernandes da Silva", 
            "111.111.111-11", 
            "Rua A", 
            "1111-1111", 
            "mateus@email.com", 
            "MAT01", 
            turma1A, 
            1
        );

        Aluno adeildo = new Aluno(
            "Adeildo Lopez da Silva", 
            "222.222.222-22", 
            "Rua B", 
            "2222-2222", 
            "adeildo@email.com", 
            "MAT02", 
            turma1A, 
            1
        );

        turma1A.adicionarAluno(mateus);
        turma1A.adicionarAluno(adeildo);
        
        // ========== CRIANDO RESPONSÁVEIS ==========
        Responsavel resp1 = new Responsavel(
            "João Silva",
            "444.444.444-44",
            "Rua A",
            "4444-4444",
            "joao@email.com",
            "Pai"
        );
        
        Responsavel resp2 = new Responsavel(
            "Maria Santos",
            "555.555.555-55",
            "Rua B",
            "5555-5555",
            "maria@email.com",
            "Mãe"
        );
        
        mateus.adicionarResponsavel(resp1);
        adeildo.adicionarResponsavel(resp2);
        
        // ========== CRIANDO PROFESSORES ==========
        Professor profJose = new Professor(
            "José", 
            "333.333.333-33", 
            "Rua C", 
            "3333-3333", 
            "jose@escola.com", 
            "PROF01", 
            mat
        );

        Professor profMaria = new Professor(
            "Maria", 
            "444.444.444-44", 
            "Rua D", 
            "4444-4444", 
            "maria@escola.com", 
            "PROF02", 
            ing
        );

        Professor profAna = new Professor(
            "Ana", 
            "555.555.555-55", 
            "Rua E", 
            "5555-5555", 
            "ana@escola.com", 
            "PROF03", 
            port
        );

        profJose.adicionarTurma(turma1A);
        profMaria.adicionarTurma(turma1A);
        profAna.adicionarTurma(turma1A);
        
        // ========== ADICIONANDO NOTAS ==========
        Nota nota1Mateus = new Nota(mateus, mat, 8.5);
        Nota nota2Mateus = new Nota(mateus, port, 9.0);
        Nota nota3Mateus = new Nota(mateus, ing, 7.5);
        
        mateus.getBoletim().adicionarNota(nota1Mateus);
        mateus.getBoletim().adicionarNota(nota2Mateus);
        mateus.getBoletim().adicionarNota(nota3Mateus);
        
        // ========== USANDO CLASSE GENÉRICA ==========
        System.out.println("--- DEMONSTRAÇÃO DE GENERICS ---");
        Repositorio<Aluno> repoAlunos = new Repositorio<>();
        Repositorio<Professor> repoProfessores = new Repositorio<>();
        Repositorio<Disciplina> repoDisciplinas = new Repositorio<>();
        
        repoAlunos.adicionar(mateus);
        repoAlunos.adicionar(adeildo);
        
        repoProfessores.adicionar(profJose);
        repoProfessores.adicionar(profMaria);
        repoProfessores.adicionar(profAna);
        
        repoDisciplinas.adicionar(mat);
        repoDisciplinas.adicionar(port);
        repoDisciplinas.adicionar(ing);
        
        System.out.println("Total de alunos no repositório: " + repoAlunos.contar());
        System.out.println("Total de professores no repositório: " + repoProfessores.contar());
        System.out.println("Total de disciplinas no repositório: " + repoDisciplinas.contar());
        
        
        System.out.println("        INFORMAÇÕES DA TURMA        ");
        System.out.println("Turma: " + turma1A.getCodigo() + " - Ano: " + turma1A.getAno());
        System.out.println("Alunos matriculados: " + turma1A.getAlunos().size());
        System.out.println("Disciplinas: " + turma1A.getDisciplinas().size());
        
        System.out.println("\n--- ALUNOS DA TURMA ---");
        for (Aluno a : turma1A.getAlunos()) {
            System.out.println("→ " + a.apresentar());
        }
        
        System.out.println("\n--- DISCIPLINAS OFERECIDAS ---");
        for (Disciplina d : turma1A.getDisciplinas()) {
            System.out.println("→ " + d.getNome() + " (" + d.getCodigo() + ") - " + d.getCargaHoraria() + "h");
        }
        
        System.out.println("\n--- PROFESSORES ---");
        for (Professor p : repoProfessores.listar()) {
            System.out.println("→ " + p.apresentar());
        }
        
        System.out.println("\n--- BOLETIM DO ALUNO: " + mateus.getNome() + " ---");
        System.out.println("RA: " + mateus.getRa());
        System.out.println("Turma: " + mateus.getTurma().getCodigo());
        for (Nota n : mateus.getBoletim().getNotas()) {
            System.out.println("  • " + n.getDisciplina().getNome() + ": " + n.getValor());
        }
        System.out.println("  Média geral: " + String.format("%.2f", mateus.getBoletim().calcularMedia()));
        
        System.out.println("\n--- RESPONSÁVEIS DE " + mateus.getNome() + " ---");
        for (Responsavel r : mateus.getResponsaveis()) {
            System.out.println("→ " + r.apresentar());
        }

        System.out.println("   LISTA DE TODAS AS PESSOAS DO SISTEMA   ");
        
        ArrayList<Pessoa> todasAsPessoas = new ArrayList<>();
        todasAsPessoas.addAll(repoAlunos.listar());
        todasAsPessoas.addAll(repoProfessores.listar());
        todasAsPessoas.add(resp1);
        todasAsPessoas.add(resp2);

        for (Pessoa p : todasAsPessoas) {
            System.out.println("→ " + p.apresentar());
        }
        
        System.out.println("\n==============================================");
        System.out.println("          FIM DO RELATÓRIO      ");
        System.out.println("==============================================");
    }
}
