package model;

public class Secretaria extends Pessoa {
    private String matricula;
    
    public Secretaria(String nome, String cpf, String endereco, String telefone, String email, String matricula) {
        super(nome, cpf, endereco, telefone, email);
        this.matricula = matricula;
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    
    @Override
    public String toString() {
        return "Secretaria [nome=" + getNome() + ", cpf=" + getCpf() + ", endereco=" + getEndereco() + ", telefone=" + getTelefone() + ", email=" + getEmail() + ", matricula=" + matricula + "]";
    }
}