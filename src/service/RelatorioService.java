package service;

import java.util.ArrayList;

import model.Aluno;
import model.Pessoa;
import model.Professor;
import model.Turma;

public class RelatorioService {

    public static void exibirRelatorioEscolar(Turma turma, ArrayList<Aluno> alunos, ArrayList<Professor> professores) {
        System.out.println("==============================================");
        System.out.println("        RELATÓRIO DO SISTEMA ESCOLAR        ");
        System.out.println("==============================================");
        
        System.out.println("\n--- TURMA ---");
        System.out.println("Nome: " + turma.getCodigo());

        System.out.println("\n--- ALUNOS MATRICULADOS ---");
        for (Aluno aluno : alunos) {
            System.out.println("- " + aluno.getNome() + " (Turma: " + aluno.getTurma().getCodigo() + ")");
        }
        
        System.out.println("\nDisciplinas cursadas pela turma: Matemática, Português e Inglês.");

        System.out.println("\n--- PROFESSORES ---");
        for (Professor prof : professores) {
            System.out.println("- Professor(a) de " + prof.getDisciplina() + ": " + prof.getNome());
        }
    }

    public static void exibirListaPessoas(ArrayList<Pessoa> pessoas) {
        System.out.println("==============================================");
        System.out.println("   LISTA DE PESSOAS CADASTRADAS NO SISTEMA   ");
        System.out.println("==============================================");

        for (Pessoa p : pessoas) {
            System.out.println(p.apresentar());
        }
    }
}
