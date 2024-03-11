package core.domain.food;

import java.util.UUID;

public record RecipeId(UUID id) {
    @Override
    public String toString() {
        return id.toString();
    }
}
