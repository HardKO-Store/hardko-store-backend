package pe.edu.upc.hardko.store.IAM.interfaces.rest.resoruces;

public record UserAddressResource(
        String country,
        String city,
        String street,
        String zip
) {
}
