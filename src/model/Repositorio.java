package model;

import java.util.ArrayList;

/**
 * Classe Genérica para gerenciar qualquer tipo de entidade.
 * Demonstra o conceito de Generics em Java para reutilização de código.
 * 
 * @param <T> Tipo de entidade a ser gerenciada (Aluno, Professor, Disciplina, etc)
 * 
 * Exemplo de uso:
 * Repositorio<Aluno> repoAlunos = new Repositorio<>();
 * Repositorio<Professor> repoProfessores = new Repositorio<>();
 */
public class Repositorio<T> {
    private ArrayList<T> itens;

    public Repositorio() {
        this.itens = new ArrayList<>();
    }

    /**
     * Adiciona um item ao repositório
     * @param item Item a ser adicionado
     */
    public void adicionar(T item) {
        if (item != null) {
            this.itens.add(item);
        }
    }

    /**
     * Remove um item do repositório
     * @param item Item a ser removido
     * @return true se removido, false caso contrário
     */
    public boolean remover(T item) {
        return this.itens.remove(item);
    }

    /**
     * Busca um item por índice
     * @param indice Índice do item
     * @return Item encontrado ou null
     */
    public T buscarPorIndice(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            return itens.get(indice);
        }
        return null;
    }

    /**
     * Lista todos os itens do repositório
     * @return ArrayList com todos os itens
     */
    public ArrayList<T> listar() {
        return new ArrayList<>(itens);
    }

    /**
     * Retorna a quantidade de itens no repositório
     * @return Número de itens
     */
    public int contar() {
        return itens.size();
    }

    /**
     * Verifica se o repositório está vazio
     * @return true se vazio
     */
    public boolean estaVazio() {
        return itens.isEmpty();
    }

    /**
     * Limpa todos os itens do repositório
     */
    public void limpar() {
        itens.clear();
    }

    @Override
    public String toString() {
        return "Repositorio [itens=" + itens.size() + "]";
    }
}
