package model;

import java.util.ArrayList;

public class Professor extends Pessoa {
    private String matricula;
    private Disciplina disciplina;
    private ArrayList<Turma> turmas;

    public Professor(String nome, String cpf, String endereco, String telefone, String email, String matricula, Disciplina disciplina) {
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
    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }
    public ArrayList<Turma> getTurmas() { return turmas; }
    public void setTurmas(ArrayList<Turma> turmas) { this.turmas = turmas; }

    @Override
    public String apresentar() {
    return "Professor(a): " + getNome() + " | Disciplina: " + (disciplina != null ? disciplina.getNome() : "Nenhuma") + " | Matrícula: " + getMatricula();
    }
}