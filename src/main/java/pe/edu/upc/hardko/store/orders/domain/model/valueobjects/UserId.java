package pe.edu.upc.hardko.store.orders.domain.model.valueobjects;

public record UserId(String value) {
    public UserId {
        if (value == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }
        if (value.isBlank()) {
            throw new IllegalArgumentException("User id cannot be empty");
        }
    }
}
