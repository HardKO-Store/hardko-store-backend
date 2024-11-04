package pe.edu.upc.hardko.store.shared.interfaces.rest.resources;

public record MessageResource(String message) {
    public MessageResource {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
    }
}
