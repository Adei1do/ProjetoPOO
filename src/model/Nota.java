package model;

public class Nota { 
    private Aluno aluno;
    private Disciplina disciplina;
    private double valor;

    public Nota(Aluno aluno, Disciplina disciplina, double valor) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.valor = valor;
    }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    @Override
    public String toString() {
        return "Nota [aluno=" + aluno.getNome() + ", disciplina=" + disciplina.getNome() + ", valor=" + valor + "]";
    } 
    
}
