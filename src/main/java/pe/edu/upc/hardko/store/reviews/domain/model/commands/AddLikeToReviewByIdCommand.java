package pe.edu.upc.hardko.store.reviews.domain.model.commands;

public record AddLikeToReviewByIdCommand(String reviewId, String userId) {
}
