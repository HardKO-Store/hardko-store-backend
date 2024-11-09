package pe.edu.upc.hardko.store.reviews.domain.model.aggregates;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.edu.upc.hardko.store.reviews.domain.model.commands.CreateReviewCommand;
import pe.edu.upc.hardko.store.reviews.domain.model.valueobjects.ProductId;
import pe.edu.upc.hardko.store.reviews.domain.model.valueobjects.UserId;
import pe.edu.upc.hardko.store.shared.domain.model.entities.AuditableModel;

import java.util.List;

@Getter
@Document(collection = "reviews")
@NoArgsConstructor
@AllArgsConstructor
public class Review extends AuditableModel {

    @NotNull
    private UserId userId;

    @NotNull
    private ProductId productId;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    @NotNull
    private String title;

    @NotNull
    private String comment;

    private List<UserId> likedBy;

    public Review(String userId, String productId, Integer rating, String title, String comment) {
        this.userId = new UserId(userId);
        this.productId = new ProductId(productId);
        this.rating = rating;
        this.title = title;
        this.comment = comment;
        this.likedBy = List.of();
    }

    public Review(CreateReviewCommand command) {
        this.userId = new UserId(command.userId());
        this.productId = new ProductId(command.productId());
        this.rating = command.rating();
        this.title = command.title();
        this.comment = command.comment();
        this.likedBy = List.of();
    }

    public void addLike(UserId userId) {
        if (!likedBy.contains(userId)) {
            likedBy.add(userId);
        }
    }


}
