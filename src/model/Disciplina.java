package model;

public class Disciplina {
    private String nome;
    private String codigo;
    private int cargaHoraria;

    public Disciplina(String nome, String codigo, int cargaHoraria) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new exceptions.DadoInvalidoException("O nome da disciplina não pode ser nulo ou vazio.");
        }
        if (cargaHoraria <= 0) {
            throw new exceptions.DadoInvalidoException("A carga horária deve ser maior que zero.");
        }
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }

    @Override
    public String toString() {
        return "Disciplina [codigo=" + codigo + ", nome=" + nome + ", cargaHoraria=" + cargaHoraria + "]";
    }
}