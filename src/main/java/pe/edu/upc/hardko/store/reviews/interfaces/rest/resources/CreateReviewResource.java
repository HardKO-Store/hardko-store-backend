package pe.edu.upc.hardko.store.reviews.interfaces.rest.resources;

public record CreateReviewResource(
        String productId,
        String userId,
        Integer rating,
        String title,
        String comment
) {
}
