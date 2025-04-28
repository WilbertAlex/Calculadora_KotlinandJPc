package pe.edu.upeu.turismospringboot.exception;

public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(String message) {
        super(message); // Pasa el mensaje al constructor de RuntimeException
    }
}