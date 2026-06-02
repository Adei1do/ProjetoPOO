package model;
import java.util.ArrayList;

public class Aluno extends Pessoa {
    private String ra;
    private Turma turma;
    private int periodo;
    private ArrayList<Responsavel> responsaveis;
    private Boletim boletim;
    
    public Aluno(String nome, String cpf, String endereco, String telefone, String email, String ra, Turma turma, int periodo) {
        super(nome, cpf, endereco, telefone, email);
        this.ra = ra;
        this.turma = turma;
        this.periodo = periodo;
        this.responsaveis = new ArrayList<>();
        this.boletim = new Boletim(ra);
    }
    
    public String getRa() { return ra; }
    public void setRa(String ra) { this.ra = ra; }
    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }
    public int getPeriodo() { return periodo; }
    public void setPeriodo(int periodo) { this.periodo = periodo; }
    public ArrayList<Responsavel> getResponsaveis() { return responsaveis; }
    public void adicionarResponsavel(Responsavel responsavel) { 
        this.responsaveis.add(responsavel); 
    }
    public void removerResponsavel(Responsavel responsavel) { 
        this.responsaveis.remove(responsavel); 
    }
    public Boletim getBoletim() { return boletim; }
    public void setBoletim(Boletim boletim) { this.boletim = boletim; }
    
    @Override
    public String toString() {
        return "Aluno [nome=" + getNome() + ", cpf=" + getCpf() + ", endereco=" + getEndereco() + ", telefone=" + getTelefone() + ", email=" + getEmail() + ", ra=" + ra + ", turma=" + (turma != null ? turma.getCodigo() : "Nenhuma") + ", periodo=" + periodo + "]";
    }
    @Override
    public String apresentar() {
    return "Aluno: " + getNome() + " | RA: " + ra + " | Turma: " + (turma != null ? turma.getCodigo() : "Nenhuma") + " | Período: " + periodo;
    }
}           