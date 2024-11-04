package pe.edu.upc.hardko.store.products.domain.model.valueobjects;

public record UserId(Long userId) {
    public UserId {
        if (userId < 0) {
            throw new IllegalArgumentException("User userId cannot be negative");
        }
    }

    public UserId() {
        this(0L);
    }
}
