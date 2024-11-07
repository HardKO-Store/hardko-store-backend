package pe.edu.upc.hardko.store.reviews.domain.model.valueobjects;

public record ProductId(String productId) {
    public ProductId {
        if (productId == null) {
            throw new IllegalArgumentException("Product id cannot be null");
        }
        if (productId.isBlank()) {
            throw new IllegalArgumentException("Product id cannot be empty");
        }
    }
}
