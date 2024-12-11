package core.domain.food;

import java.util.UUID;

public record IdentifiantRecette(UUID id) {
    @Override
    public String toString() {
        return id.toString();
    }
}
