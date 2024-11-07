package pe.edu.upc.hardko.store.reviews.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.reviews.application.internal.outboundservices.ExternalProductService;
import pe.edu.upc.hardko.store.reviews.application.internal.outboundservices.ExternalUserService;
import pe.edu.upc.hardko.store.reviews.domain.model.aggregates.Review;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.AddLikeToReviewByIdCommand;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.CreateReviewCommand;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.DeleteReviewCommand;
import pe.edu.upc.hardko.store.reviews.domain.services.ReviewCommandService;
import pe.edu.upc.hardko.store.reviews.infrastructure.persistence.mongo.repositories.ReviewRepository;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final ExternalProductService externalProductService;
    private final ExternalUserService externalUserService;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository,
                                  ExternalProductService externalProductService,
                                  ExternalUserService externalUserService) {
        this.reviewRepository = reviewRepository;
        this.externalProductService = externalProductService;
        this.externalUserService = externalUserService;
    }

    @Override
    public Optional<Review> handle(CreateReviewCommand command) {
        return Optional.empty();
    }

    @Override
    public void handle(DeleteReviewCommand command) {

    }

    @Override
    public Boolean handle(AddLikeToReviewByIdCommand command) {
        return null;
    }
}
