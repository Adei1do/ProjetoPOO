/*Aluno: Mateus Fernandes - 202421901047
Aluno: Adeildo Lopes Mateus Júnior - 202421091016
Aluno: Pedro Lucas Costa de Almeida - 202421091065 */


package main;

import model.Aluno;
import model.Professor;
import model.Turma;

public class Main {
    public static void main(String[] args) {
        Turma turma1A = new Turma("primeiro Ano A", 1);

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

        Aluno adeiodo = new Aluno(
            "Adeiodo Lopez da Silva", 
            "222.222.222-22", 
            "Rua B", 
            "2222-2222", 
            "adeiodo@email.com", 
            "MAT02", 
            turma1A, 
            1
        );

        turma1A.adicionarAluno(mateus);
        turma1A.adicionarAluno(adeiodo);

        Professor profJose = new Professor(
            "José", 
            "333.333.333-33", 
            "Rua C", 
            "3333-3333", 
            "jose@escola.com", 
            "PROF01", 
            "Matemática"
        );

        Professor profMaria = new Professor(
            "Maria", 
            "444.444.444-44", 
            "Rua D", 
            "4444-4444", 
            "maria@escola.com", 
            "PROF02", 
            "Inglês"
        );

        Professor profAna = new Professor(
            "Ana", 
            "555.555.555-55", 
            "Rua E", 
            "5555-5555", 
            "ana@escola.com", 
            "PROF03", 
            "Português"
        );

        profJose.adicionarTurma(turma1A);
        profMaria.adicionarTurma(turma1A);
        profAna.adicionarTurma(turma1A);

        System.out.println("==============================================");
        System.out.println("        RELATÓRIO DO SISTEMA ESCOLAR        ");
        System.out.println("==============================================");
        
        System.out.println("\n--- TURMA ---");
        System.out.println("Nome: " + turma1A.getCodigo());

        System.out.println("\n--- ALUNOS MATRICULADOS ---");
        System.out.println("- " + mateus.getNome() + " (Turma: " + mateus.getTurma().getCodigo() + ")");
        System.out.println("- " + adeiodo.getNome() + " (Turma: " + adeiodo.getTurma().getCodigo() + ")");
        
        System.out.println("\nDisciplinas cursadas pela turma: Matemática, Português e Inglês.");

        System.out.println("\n--- PROFESSORES ---");
        System.out.println("- Professor(a) de " + profJose.getDisciplina() + ": " + profJose.getNome());
        System.out.println("- Professor(a) de " + profMaria.getDisciplina() + ": " + profMaria.getNome());
        System.out.println("- Professor(a) de " + profAna.getDisciplina() + ": " + profAna.getNome());
        
        System.out.println("==============================================");
    }
}
