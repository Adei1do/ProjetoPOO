package model;

import java.util.ArrayList;

public class Direcao extends Pessoa implements IGestaoEscolar{
    private String funcao;
    
    public Direcao(String nome, String cpf, String endereco, String telefone, String email, String funcao) {
        super(nome, cpf, endereco, telefone, email);
        this.funcao = funcao;
    }
    public String getFuncao() {
        return funcao;
    }
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    private ArrayList<Aluno> alunos = new ArrayList<>();
    private ArrayList<Professor> professores = new ArrayList<>();
    private ArrayList<Turma> turmas = new ArrayList<>();

    @Override
    public void cadastrarAluno(Aluno aluno, Responsavel responsavel) {
    alunos.add(aluno);
    System.out.println("Aluno cadastrado: " + aluno.getNome() + " | Responsável: " + responsavel.getNome());
    }

    @Override
    public void cadastrarProfessor(Professor professor) {
    professores.add(professor);
    System.out.println("Professor cadastrado: " + professor.getNome());
    }

    @Override
    public void cadastrarTurma(Turma turma) {
    turmas.add(turma);
    System.out.println("Turma cadastrada: " + turma.getCodigo());
    }

    @Override
    public String toString() {
        return "Direcao [nome=" + getNome() + ", cpf=" + getCpf() + ", endereco=" + getEndereco() + ", telefone=" + getTelefone() + ", email=" + getEmail() + ", funcao=" + funcao + "]";
    }   

    @Override
    public String apresentar() {
    return "Direção: " + getNome() + " | Função: " + funcao;
    }

    public ArrayList<Aluno> getAlunos() { return alunos; }
    public ArrayList<Professor> getProfessores() { return professores; }
    public ArrayList<Turma> getTurmas() { return turmas; }
}   