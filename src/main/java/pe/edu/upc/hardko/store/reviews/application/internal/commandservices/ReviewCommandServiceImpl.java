package pe.edu.upc.hardko.store.reviews.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.reviews.application.internal.outboundservices.ExternalReviewProductService;
import pe.edu.upc.hardko.store.reviews.application.internal.outboundservices.ExternalReviewUserService;
import pe.edu.upc.hardko.store.reviews.domain.model.aggregates.Review;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.AddLikeToReviewByReviewIdAndUserIdCommand;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.CreateReviewCommand;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.DeleteReviewCommand;
import pe.edu.upc.hardko.store.reviews.domain.services.ReviewCommandService;
import pe.edu.upc.hardko.store.reviews.infrastructure.persistence.mongo.repositories.ReviewRepository;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final ExternalReviewProductService externalProductService;
    private final ExternalReviewUserService externalUserService;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository,
                                  ExternalReviewProductService externalProductService,
                                  ExternalReviewUserService externalUserService) {
        this.reviewRepository = reviewRepository;
        this.externalProductService = externalProductService;
        this.externalUserService = externalUserService;
    }

    @Override
    public Optional<Review> handle(CreateReviewCommand command) {
        if(!this.externalUserService.existsByUserId( (command.userId()) )){
            throw new IllegalArgumentException("User not found");
        }
        if(!this.externalProductService.existsByProductId( (command.productId()) )){
            throw new IllegalArgumentException("Product not found");
        }

        var review = new Review(command);

        try {
            this.reviewRepository.save(review);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving review: " + e.getMessage());
        }

        return Optional.of(review);
    }

    @Override
    public void handle(DeleteReviewCommand command) {
        if (!this.reviewRepository.existsById(command.reviewId())) {
            throw new IllegalArgumentException("Review with id " + command.reviewId() + " does not exist");
        }

        try {
            this.reviewRepository.deleteById(command.reviewId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting review: " + e.getMessage());
        }
    }

    @Override
    public void handle(AddLikeToReviewByReviewIdAndUserIdCommand command) {
        if (!this.reviewRepository.existsById(command.reviewId())) {
            throw new IllegalArgumentException("Review with id " + command.reviewId() + " does not exist");
        }

        var review = this.reviewRepository.findById(command.reviewId()).get();
        review.addLike(command.userId());

        try {
            this.reviewRepository.save(review);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving review: " + e.getMessage());
        }

    }
}
