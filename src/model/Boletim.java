package model;

import java.util.ArrayList;

public class Boletim {
    private String raAluno;
    private ArrayList<Nota> notas;

    public Boletim(String raAluno) {
        this.raAluno = raAluno;
        this.notas = new ArrayList<>();
    }

    public void adicionarNota(Nota nota) {
        this.notas.add(nota);
    }

    public void removerNota(Nota nota) {
        this.notas.remove(nota);
    }
    
    public ArrayList<Nota> getNotas() { return notas; }
    public double calcularMedia() {
        if (notas.isEmpty()) return 0;
        double soma = 0;
        for (Nota n : notas) {
            soma += n.getValor();
        }
        return soma / notas.size();
    }

    public String getRaAluno() { return raAluno; }
    public void setRaAluno(String raAluno) { this.raAluno = raAluno; }
    public void setNotas(ArrayList<Nota> notas) { this.notas = notas; }

    @Override
    public String toString() {
        return "Boletim [ra=" + raAluno + ", notas=" + notas.size() + ", média=" + calcularMedia() + "]";
    }
}