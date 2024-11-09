package pe.edu.upc.hardko.store.reviews.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.reviews.application.internal.outboundservices.ExternalReviewProductService;
import pe.edu.upc.hardko.store.reviews.application.internal.outboundservices.ExternalReviewUserService;
import pe.edu.upc.hardko.store.reviews.domain.model.aggregates.Review;
import pe.edu.upc.hardko.store.reviews.domain.model.queries.GetReviewByIdQuery;
import pe.edu.upc.hardko.store.reviews.domain.model.queries.GetReviewsByProductIdQuery;
import pe.edu.upc.hardko.store.reviews.domain.model.queries.GetReviewsByUserIdQuery;
import pe.edu.upc.hardko.store.reviews.domain.model.valueobjects.ProductId;
import pe.edu.upc.hardko.store.reviews.domain.model.valueobjects.UserId;
import pe.edu.upc.hardko.store.reviews.domain.services.ReviewQueryService;
import pe.edu.upc.hardko.store.reviews.infrastructure.persistence.mongo.repositories.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final ExternalReviewProductService externalProductService;
    private final ExternalReviewUserService externalUserService;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository,
                                  ExternalReviewProductService externalProductService,
                                  ExternalReviewUserService externalUserService) {
        this.reviewRepository = reviewRepository;
        this.externalProductService = externalProductService;
        this.externalUserService = externalUserService;
    }

    @Override
    public List<Review> handle(GetReviewsByProductIdQuery query) {

        var productId = query.productId();

        if (!this.externalProductService.existsByProductId(productId)) {
            throw new IllegalArgumentException("Product not found");
        }

        var reviews = this.reviewRepository.findReviewsByProductId(new ProductId(productId));

        return reviews;
    }

    @Override
    public List<Review> handle(GetReviewsByUserIdQuery query) {

        var userId = query.userId();

        if(!this.externalProductService.existsByProductId(userId)){
            throw new IllegalArgumentException("User not found");
        }

        var reviews = this.reviewRepository.findReviewsByUserId(new UserId(userId));
        return reviews;
    }

    @Override
    public Optional<Review> handle(GetReviewByIdQuery query) {
        return this.reviewRepository.findById(query.reviewId());
    }
}
