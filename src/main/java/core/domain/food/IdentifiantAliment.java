package core.domain.food;

public record IdentifiantAliment(Integer id) {
    @Override
    public String toString() {
        return id.toString();
    }
}
