package pe.edu.upc.hardko.store.IAM.interfaces.rest.resoruces;

public record CreateUserResource(
        String firstName,
        String lastName,
        String email,
        String password,
        String country,
        String city,
        String street,
        String zip
) {
}
