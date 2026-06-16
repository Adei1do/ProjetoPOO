package model;

import java.util.ArrayList;

public class Turma {
    private String codigo;
    private int ano;
    private ArrayList<Aluno> alunos;
    private ArrayList<Disciplina> disciplinas;

    public Turma(String codigo, int ano) {
        this.codigo = codigo;
        this.ano = ano;
        this.alunos = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public ArrayList<Aluno> getAlunos() { return alunos; }
    public void setAlunos(ArrayList<Aluno> alunos) { this.alunos = alunos; }

    public void adicionarAluno(Aluno aluno) {
        if (this.alunos.contains(aluno)) {
            throw new exceptions.MatriculaInvalidaException("Aluno já matriculado nesta turma.");
        }
        this.alunos.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        if (!this.alunos.contains(aluno)) {
            throw new exceptions.MatriculaInvalidaException("Não é possível remover: aluno não encontrado nesta turma.");
        }
        this.alunos.remove(aluno);
    }
    
    public void adicionarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }
    
    public void removerDisciplina(Disciplina disciplina) {
        this.disciplinas.remove(disciplina);
    }
    
    public ArrayList<Disciplina> getDisciplinas() { return disciplinas; }
    public void setDisciplinas(ArrayList<Disciplina> disciplinas) { this.disciplinas = disciplinas; }

    @Override
    public String toString() {
        return "Turma [codigo=" + codigo + ", ano=" + ano + ", alunos=" + alunos.size() + ", disciplinas=" + disciplinas.size() + "]";
    }
}