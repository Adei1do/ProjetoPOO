package test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.DadoInvalidoException;
import model.Disciplina;

public class DisciplinaTest {

    @Test
    public void deveCriarDisciplinaValida() {
        Disciplina disciplina = new Disciplina("Matemática", "MAT01", 60);
        
        assertNotNull(disciplina);
        assertEquals("Matemática", disciplina.getNome());
        assertEquals("MAT01", disciplina.getCodigo());
        assertEquals(60, disciplina.getCargaHoraria());
    }

    @Test
    public void naoDeveAceitarNomeVazio() {
        DadoInvalidoException exception = assertThrows(DadoInvalidoException.class, () -> {
            new Disciplina("", "MAT01", 60);
        });
        
        assertEquals("O nome da disciplina não pode ser nulo ou vazio.", exception.getMessage());
    }
}
