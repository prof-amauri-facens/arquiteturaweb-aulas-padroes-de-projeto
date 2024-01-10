package facens.arquiteturaweb.padroesdeprojeto;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String entity, String field, String value) {
        super(String.format("%s with %s '%s' already exists", entity, field, value));
    }
}

