package pe.edu.upc.hardko.store.orders.domain.model.valueobjects;

public record ProductId(String value) {
    public ProductId {
        if (value == null) {
            throw new IllegalArgumentException("Product id cannot be null");
        }
        if (value.isBlank()) {
            throw new IllegalArgumentException("Product id cannot be empty");
        }
    }
}
