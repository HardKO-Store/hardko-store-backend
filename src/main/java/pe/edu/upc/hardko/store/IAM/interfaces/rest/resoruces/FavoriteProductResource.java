package pe.edu.upc.hardko.store.IAM.interfaces.rest.resoruces;



import java.util.List;

public record FavoriteProductResource(
        String id,
        String name,
        String description,
        List<String> categories,
        Double price,
        String brand,
        String imageurl
) {
}
