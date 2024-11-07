package pe.edu.upc.hardko.store.reviews.domain.model.valueobjects;


public record UserId(String userId) {
    public UserId {
        if (userId == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }
        if (userId.isBlank()) {
            throw new IllegalArgumentException("User id cannot be empty");
        }
    }
}
