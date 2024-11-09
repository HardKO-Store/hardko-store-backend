package pe.edu.upc.hardko.store.reviews.domain.model.commands;

public record CreateReviewCommand(
        String productId,
        String userId,
        Integer rating,
        String title,
        String comment
) {
}
