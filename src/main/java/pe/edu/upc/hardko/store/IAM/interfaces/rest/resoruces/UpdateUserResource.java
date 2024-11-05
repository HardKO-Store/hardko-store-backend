package pe.edu.upc.hardko.store.IAM.interfaces.rest.resoruces;

public record UpdateUserResource(
        String firstName,
        String lastName,
        String email,
        String password,
        String country,
        String city,
        String street,
        String zip,
        String newFavoriteProductId
) {
}
