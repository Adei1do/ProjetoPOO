package test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.DadoInvalidoException;
import model.Aluno;
import model.Turma;

public class AlunoTest {

    private Turma turma;

    @BeforeEach
    public void setUp() {
        turma = new Turma("primeiro Ano A", 1);
    }

    @Test
    public void deveCriarAlunoValido() {
        Aluno aluno = new Aluno("Mateus", "111.111.111-11", "Rua A", "1111-1111", "mateus@email.com", "MAT01", turma, 1);
        
        assertNotNull(aluno);
        assertEquals("Mateus", aluno.getNome());
        assertEquals("111.111.111-11", aluno.getCpf());
        assertEquals("MAT01", aluno.getRa());
    }

    @Test
    public void deveLancarExcecaoNomeVazio() {
        DadoInvalidoException exception = assertThrows(DadoInvalidoException.class, () -> {
            new Aluno("", "111.111.111-11", "Rua A", "1111-1111", "email@email.com", "MAT01", turma, 1);
        });
        assertEquals("O nome não pode ser nulo ou vazio.", exception.getMessage());
    }

    @Test
    public void deveLancarExcecaoCpfVazio() {
        DadoInvalidoException exception = assertThrows(DadoInvalidoException.class, () -> {
            new Aluno("Mateus", "", "Rua A", "1111-1111", "email@email.com", "MAT01", turma, 1);
        });
        assertEquals("O CPF não pode ser nulo ou vazio.", exception.getMessage());
    }

    @Test
    public void deveLancarExcecaoCpfComLetras() {
        DadoInvalidoException exception = assertThrows(DadoInvalidoException.class, () -> {
            new Aluno("Mateus", "111.11A.111-11", "Rua A", "1111-1111", "email@email.com", "MAT01", turma, 1);
        });
        assertEquals("O CPF deve conter apenas números (11 dígitos) ou seguir o formato XXX.XXX.XXX-XX.", exception.getMessage());
    }

    @Test
    public void deveLancarExcecaoTelefoneVazio() {
        DadoInvalidoException exception = assertThrows(DadoInvalidoException.class, () -> {
            new Aluno("Mateus", "111.111.111-11", "Rua A", "", "email@email.com", "MAT01", turma, 1);
        });
        assertEquals("O telefone não pode ser nulo ou vazio.", exception.getMessage());
    }

    @Test
    public void deveLancarExcecaoTelefoneComLetras() {
        DadoInvalidoException exception = assertThrows(DadoInvalidoException.class, () -> {
            new Aluno("Mateus", "111.111.111-11", "Rua A", "1111-A111", "email@email.com", "MAT01", turma, 1);
        });
        assertEquals("O telefone deve conter apenas números e caracteres válidos (como parênteses e traços).", exception.getMessage());
    }
}
