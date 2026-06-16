package test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.MatriculaInvalidaException;
import model.Aluno;
import model.Turma;

public class TurmaTest {

    private Turma turma;
    private Aluno aluno1;

    @BeforeEach
    public void setUp() {
        turma = new Turma("primeiro Ano A", 1);
        aluno1 = new Aluno("Mateus", "111.111.111-11", "Rua A", "1111-1111", "mateus@email.com", "MAT01", turma, 1);
    }

    @Test
    public void deveAdicionarAluno() {
        turma.adicionarAluno(aluno1);
        assertEquals(1, turma.getAlunos().size());
        assertTrue(turma.getAlunos().contains(aluno1));
    }

    @Test
    public void naoDeveAdicionarAlunoDuplicado() {
        turma.adicionarAluno(aluno1);
        
        MatriculaInvalidaException exception = assertThrows(MatriculaInvalidaException.class, () -> {
            turma.adicionarAluno(aluno1);
        });
        
        assertEquals("Aluno já matriculado nesta turma.", exception.getMessage());
        assertEquals(1, turma.getAlunos().size());
    }

    @Test
    public void deveRemoverAluno() {
        turma.adicionarAluno(aluno1);
        assertEquals(1, turma.getAlunos().size());
        
        turma.removerAluno(aluno1);
        assertEquals(0, turma.getAlunos().size());
    }

    @Test
    public void naoDeveRemoverAlunoInexistente() {
        MatriculaInvalidaException exception = assertThrows(MatriculaInvalidaException.class, () -> {
            turma.removerAluno(aluno1);
        });
        
        assertEquals("Não é possível remover: aluno não encontrado nesta turma.", exception.getMessage());
    }
}
