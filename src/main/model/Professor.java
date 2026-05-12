package model;

import java.util.ArrayList;

public class Professor extends Pessoa {
    private String matricula;
    private String disciplina;
    private ArrayList<Turma> turmas;

    public Professor(String nome, String cpf, String endereco, String telefone, String email, String matricula, String disciplina) {
        super(nome, cpf, endereco, telefone, email);
        this.matricula = matricula;
        this.disciplina = disciplina;
        this.turmas = new ArrayList<>();
    }

    public void adicionarTurma(Turma turma) {
        this.turmas.add(turma);
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getDisciplina() { return disciplina; }
    public void setDisciplina(String disciplina) { this.disciplina = disciplina; }
    public ArrayList<Turma> getTurmas() { return tu rmas; }
    public void setTurmas(ArrayList<Turma> turmas) { this.turmas = turmas; }

    @Override
    public String toString() {
        return "Professor [matricula=" + matricula + ", disciplina=" + disciplina + ", turmas=" + turmas + "]";
    }
}