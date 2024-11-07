package pe.edu.upc.hardko.store.reviews.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.reviews.application.internal.outboundservices.ExternalProductService;
import pe.edu.upc.hardko.store.reviews.application.internal.outboundservices.ExternalUserService;
import pe.edu.upc.hardko.store.reviews.domain.model.aggregates.Review;
import pe.edu.upc.hardko.store.reviews.domain.model.queries.GetReviewsByProductIdQuery;
import pe.edu.upc.hardko.store.reviews.domain.model.queries.GetReviewsByUserIdQuery;
import pe.edu.upc.hardko.store.reviews.domain.services.ReviewQueryService;
import pe.edu.upc.hardko.store.reviews.infrastructure.persistence.mongo.repositories.ReviewRepository;

import java.util.List;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final ExternalProductService externalProductService;
    private final ExternalUserService externalUserService;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository,
                                  ExternalProductService externalProductService,
                                  ExternalUserService externalUserService) {
        this.reviewRepository = reviewRepository;
        this.externalProductService = externalProductService;
        this.externalUserService = externalUserService;
    }

    @Override
    public List<Review> handle(GetReviewsByProductIdQuery query) {

        var productId = query.productId().productId();

        if (!this.externalProductService.existsByProductId(productId)) {
            throw new IllegalArgumentException("Product not found");
        }

        var reviews = this.reviewRepository.findReviewsByProductId(productId);

        return reviews;
    }

    @Override
    public List<Review> handle(GetReviewsByUserIdQuery query) {

        var userId = query.userId().userId();

        if(!this.externalProductService.existsByProductId(userId)){
            throw new IllegalArgumentException("User not found");
        }

        var reviews = this.reviewRepository.findReviewsByUserId(userId);
        return reviews;
    }
}
