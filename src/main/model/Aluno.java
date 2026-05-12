package model;

public class Aluno extends Pessoa {
    private String ra;
    private Turma turma;
    private int periodo;
    
    public Aluno(String nome, String cpf, String endereco, String telefone, String email, String ra, Turma turma, int periodo) {
        super(nome, cpf, endereco, telefone, email);
        this.ra = ra;
        this.turma = turma;
        this.periodo = periodo;
    }
    
    public String getRa() { return ra; }
    public void setRa(String ra) { this.ra = ra; }
    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }
    public int getPeriodo() { return periodo; }
    public void setPeriodo(int periodo) { this.periodo = periodo; }
    
    @Override
    public String toString() {
        return "Aluno [nome=" + getNome() + ", cpf=" + getCpf() + ", endereco=" + getEndereco() + ", telefone=" + getTelefone() + ", email=" + getEmail() + ", ra=" + ra + ", turma=" + (turma != null ? turma.getCodigo() : "Nenhuma") + ", periodo=" + periodo + "]";
    }
}           