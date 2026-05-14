package model;

public class Direcao extends Pessoa {
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
    @Override
    public String toString() {
        return "Direcao [nome=" + getNome() + ", cpf=" + getCpf() + ", endereco=" + getEndereco() + ", telefone=" + getTelefone() + ", email=" + getEmail() + ", funcao=" + funcao + "]";
    }   
}   