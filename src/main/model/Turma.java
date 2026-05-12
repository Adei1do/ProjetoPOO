package model;

import java.util.ArrayList;

public class Turma {
    private String codigo;
    private int ano;
    private ArrayList<Aluno> alunos;

    public Turma(String codigo, int ano) {
        this.codigo = codigo;
        this.ano = ano;
        this.alunos = new ArrayList<>();
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public ArrayList<Aluno> getAlunos() { return alunos; }
    public void setAlunos(ArrayList<Aluno> alunos) { this.alunos = alunos; }

    public void adicionarAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        this.alunos.remove(aluno);
    }

    @Override
    public String toString() {
        return "Turma [codigo=" + codigo + ", ano=" + ano + ", total de alunos=" + alunos.size() + "]";
    }
}