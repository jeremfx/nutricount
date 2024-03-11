package core.domain.food;

public record Size(Integer size) {
    @Override
    public String toString() {
        return size.toString();
    }
}
