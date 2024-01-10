package facens.arquiteturaweb.padroesdeprojeto;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, Long id) {
        super(String.format("%s with id %d not found", entity, id));
    }
}
