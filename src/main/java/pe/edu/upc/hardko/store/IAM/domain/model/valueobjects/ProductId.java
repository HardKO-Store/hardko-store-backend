package pe.edu.upc.hardko.store.IAM.domain.model.valueobjects;

public record ProductId(String id) {
    public ProductId {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Product id cannot be null or blank");
        }
    }

    public ProductId() {
        this(null);
    }
}
