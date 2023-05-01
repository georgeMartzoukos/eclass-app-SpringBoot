package gr.aueb.cf.eclassapp.service.exceptions;

public class EntityNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(Class<?> entityClass, Long id) {
        super("Entity " + entityClass.getSimpleName() + " with id " + id + " does not exist");
    }

    public EntityNotFoundException(Class<?> entityClass) {
        super("Entity " + entityClass.getSimpleName() + " has not any courses yet");
    }
}
