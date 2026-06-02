package model;

public interface IGestaoEscolar {

    void cadastrarAluno(Aluno aluno, Responsavel responsavel);
    void cadastrarProfessor(Professor professor);
    void cadastrarTurma(Turma turma);
}
