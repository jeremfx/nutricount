package core.domain.food;

public record FoodId(Integer id) {
    @Override
    public String toString() {
        return id.toString();
    }
}
