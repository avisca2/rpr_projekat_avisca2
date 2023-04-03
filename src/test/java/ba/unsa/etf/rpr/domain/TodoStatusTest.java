package ba.unsa.etf.rpr.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoStatusTest {

    @Test
    void getId() {
        TodoStatus todoStatus = new TodoStatus("In progress");
        todoStatus.setId(1);
        assertTrue(todoStatus.getId() == 1);
    }

    @Test
    void setId() {
        TodoStatus todoStatus = new TodoStatus("In progress");
        todoStatus.setId(1);
        assertFalse(todoStatus.getId() == 2);
    }

    @Test
    void getNaziv() {
        TodoStatus todoStatus = new TodoStatus("In progress");
        assertNotEquals("Done", todoStatus.getNaziv());
    }

    @Test
    void setNaziv() {
        TodoStatus todoStatus = new TodoStatus("In progress");
        todoStatus.setNaziv("Done");
        assertEquals("Done", todoStatus.getNaziv());
    }

    @Test
    void testToString() {
        TodoStatus todoStatus = new TodoStatus("In progress");
        assertEquals("In progress", todoStatus.toString());
    }
}