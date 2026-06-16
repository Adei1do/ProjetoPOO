package model;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;

    public Pessoa(String nome, String cpf, String endereco, String telefone, String email) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new exceptions.DadoInvalidoException("O nome não pode ser nulo ou vazio.");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new exceptions.DadoInvalidoException("O CPF não pode ser nulo ou vazio.");
        }
        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}") && !cpf.matches("\\d{11}")) {
            throw new exceptions.DadoInvalidoException("O CPF deve conter apenas números (11 dígitos) ou seguir o formato XXX.XXX.XXX-XX.");
        }
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new exceptions.DadoInvalidoException("O telefone não pode ser nulo ou vazio.");
        }
        if (!telefone.matches("[0-9()\\-\\s]+")) {
            throw new exceptions.DadoInvalidoException("O telefone deve conter apenas números e caracteres válidos (como parênteses e traços).");
        }
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public abstract String apresentar();
}   