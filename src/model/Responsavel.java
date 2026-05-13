package model;

public class Responsavel extends Pessoa {
    private String parentesco;
    
    public Responsavel(String nome, String cpf, String endereco, String telefone, String email, String parentesco) {
        super(nome, cpf, endereco, telefone, email);
        this.parentesco = parentesco;
    }
    public String getParentesco() {
        return parentesco;
    }
    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
    @Override
    public String toString() {
        return "Responsavel [nome=" + getNome() + ", cpf=" + getCpf() + ", endereco=" + getEndereco() + ", telefone=" + getTelefone() + ", email=" + getEmail() + ", parentesco=" + parentesco + "]";
    }   
}   