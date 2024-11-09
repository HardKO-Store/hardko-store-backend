package pe.edu.upc.hardko.store.orders.domain.model.valueobjects;

public record DeliveryAddress(String country, String city, String street, String zipCode) {
    public DeliveryAddress {
        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or empty");
        }
        if (zipCode == null || zipCode.isBlank()) {
            throw new IllegalArgumentException("Zip code cannot be null or empty");
        }
    }
}
