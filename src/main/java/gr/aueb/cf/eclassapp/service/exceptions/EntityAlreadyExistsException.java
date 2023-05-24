package gr.aueb.cf.eclassapp.service.exceptions;

public class EntityAlreadyExistsException extends Exception{
    private static final long serialVersionUID = 1L;

    public EntityAlreadyExistsException(Class<?> entityClass, Long id) {
        super("Entity " + entityClass.getSimpleName() + " with id " + id + " already exist");
    }

    public EntityAlreadyExistsException(Class<?> entityClass) {
        super("Entity " + entityClass.getSimpleName() +  " already exist");
    }
}
