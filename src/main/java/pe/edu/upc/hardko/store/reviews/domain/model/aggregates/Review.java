package pe.edu.upc.hardko.store.reviews.domain.model.aggregates;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.CreateReviewCommand;
import pe.edu.upc.hardko.store.shared.domain.model.entities.AuditableModel;

import java.util.List;

@Getter
@Document(collection = "reviews")
@NoArgsConstructor
@AllArgsConstructor
public class Review extends AuditableModel {

    @NotNull
    @Field("user_id")
    private String userId;

    @NotNull
    @Field("product_id")
    private String productId;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    @NotNull
    private String title;

    @NotNull
    private String comment;

    @Field("liked_by")
    private List<String> likedBy;

    public Review(String userId, String productId, Integer rating, String title, String comment) {
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
        this.title = title;
        this.comment = comment;
        this.likedBy = List.of();
    }

    public Review(CreateReviewCommand command) {
        this.userId = command.userId();
        this.productId = command.productId();
        this.rating = command.rating();
        this.title = command.title();
        this.comment = command.comment();
        this.likedBy = List.of();
    }

    public void addLike(String userId) {
        if (!likedBy.contains(userId)) {
            likedBy.add(userId);
        }
    }


}
