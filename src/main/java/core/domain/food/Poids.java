package core.domain.food;

public record Poids(Integer size) {
    @Override
    public String toString() {
        return size.toString();
    }
}
