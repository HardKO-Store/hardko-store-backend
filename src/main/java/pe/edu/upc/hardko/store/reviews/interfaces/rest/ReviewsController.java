package pe.edu.upc.hardko.store.reviews.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.AddLikeToReviewByIdCommand;
import pe.edu.upc.hardko.store.reviews.domain.model.queries.GetReviewByIdQuery;
import pe.edu.upc.hardko.store.reviews.domain.model.queries.GetReviewsByProductIdQuery;
import pe.edu.upc.hardko.store.reviews.domain.model.queries.GetReviewsByUserIdQuery;
import pe.edu.upc.hardko.store.reviews.domain.services.ReviewCommandService;
import pe.edu.upc.hardko.store.reviews.domain.services.ReviewQueryService;
import pe.edu.upc.hardko.store.reviews.interfaces.rest.resources.AddLikeToReviewResource;
import pe.edu.upc.hardko.store.reviews.interfaces.rest.resources.CreateReviewResource;
import pe.edu.upc.hardko.store.reviews.interfaces.rest.resources.ReviewResource;
import pe.edu.upc.hardko.store.reviews.interfaces.rest.transform.CreateReviewCommandFromResourceAssembler;
import pe.edu.upc.hardko.store.reviews.interfaces.rest.transform.ReviewResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reviews", description = "Reviews Management Endpoints")
public class ReviewsController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;


    public ReviewsController(ReviewCommandService reviewCommandService,
                            ReviewQueryService reviewQueryService) {
        this.reviewCommandService = reviewCommandService;
        this.reviewQueryService = reviewQueryService;
    }

    @GetMapping("/{reviewId}")
    @Operation(summary = "Get review by id", description = "Get a review by id")
    @ApiResponse(responseCode = "200", description = "Review found")
    public ResponseEntity<ReviewResource> getReviewById(@PathVariable String reviewId){
        //TODO: implement personalized response for user, when a user
        // is logged in, the response should include if the user liked the review

        var getReviewByIdQuery = new GetReviewByIdQuery(reviewId);

        var review = this.reviewQueryService.handle(getReviewByIdQuery);

        if (review.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());

        return ResponseEntity.ok(reviewResource);
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Get reviews by product id", description = "Get reviews by product id")
    @ApiResponse(responseCode = "200", description = "Reviews found")
    public ResponseEntity<List<ReviewResource>> getReviewsByProductId(@PathVariable String productId){

        //TODO: implement personalized response for user, when a user
        // is logged in, the response should include if the user liked the review

        var getReviewsByProductIdQuery = new GetReviewsByProductIdQuery(productId);

        var reviews = this.reviewQueryService.handle(getReviewsByProductIdQuery);

        var reviewResources = reviews.stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(reviewResources);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get reviews by user id", description = "Get reviews by user id")
    @ApiResponse(responseCode = "200", description = "Reviews found")
    public ResponseEntity<List<ReviewResource>> getReviewsByUserId(@PathVariable String userId){
        var getReviewsByUserIdQuery = new GetReviewsByUserIdQuery(userId);

        var reviews = this.reviewQueryService.handle(getReviewsByUserIdQuery);

        var reviewResources = reviews.stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(reviewResources);
    }

    @PostMapping
    @Operation(summary = "Create review", description = "Create a new review for a product")
    @ApiResponse(responseCode = "201", description = "Review created")
    public ResponseEntity<ReviewResource> createReview(@RequestBody CreateReviewResource resource){
        var createReviewCommand = CreateReviewCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var review = this.reviewCommandService.handle(createReviewCommand);

        if (review.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());

        return ResponseEntity.ok(reviewResource);
    }

    @PutMapping("/{reviewId}/like")
    @Operation(summary = "Add like to review", description = "Add a like to a review")
    @ApiResponse(responseCode = "201", description = "Like added")
    public ResponseEntity<Void> addLikeToReview(@PathVariable String reviewId, @RequestBody AddLikeToReviewResource resource){
        var addLikeToReviewByIdCommand = new AddLikeToReviewByIdCommand(reviewId, resource.userId());

        this.reviewCommandService.handle(addLikeToReviewByIdCommand);

        return ResponseEntity.ok().build();
    }

}
