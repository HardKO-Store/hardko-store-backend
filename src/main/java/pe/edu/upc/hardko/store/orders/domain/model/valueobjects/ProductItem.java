package pe.edu.upc.hardko.store.orders.domain.model.valueobjects;

public record ProductItem(String productId, Integer quantity) {
    public ProductItem {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("Product Id cannot be null or empty");
        }
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }
}
