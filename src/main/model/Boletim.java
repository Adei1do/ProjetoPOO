package model;

import java.util.HashMap;
import java.util.Map;

public class Boletim {
    private String ra;
    private Map<String, Nota> notas;

    public Boletim(String ra) {
        this.ra = ra;
        this.notas = new HashMap<>();
    }

    public void adicionarNota(String disciplina, double nota1, double nota2, double nota3, double media) {
        this.notas.put(disciplina, new Nota(disciplina, nota1, nota2, nota3, media));
    }

    public Nota getNota(String disciplina) {
        return notas.get(disciplina);
    }

    public String getRa() { return ra; }
    public void setRa(String ra) { this.ra = ra; }
    public Map<String, Nota> getNotas() { return notas; }
    public void setNotas(Map<String, Nota> notas) { this.notas = notas; }

    @Override
    public String toString() {
        return "Boletim [ra=" + ra + ", notas=" + notas + "]";
    }
}