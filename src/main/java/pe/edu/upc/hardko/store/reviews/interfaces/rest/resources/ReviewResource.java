package pe.edu.upc.hardko.store.reviews.interfaces.rest.resources;

public record ReviewResource(
        String id,
        String productId,
        String userId,
        Integer rating,
        String title,
        String comment,
        Integer likes
) {
}
