package pe.edu.upc.hardko.store.IAM.domain.model.valueobjects;

public record UserAddress(String country, String city, String street, String zip) {
    public UserAddress {
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or empty");
        }
        if (zip == null || zip.isBlank()) {
            throw new IllegalArgumentException("Zip code cannot be null or empty");
        }
    }
}
