package core.domain.food;

public interface Aliment {
    Double calories();

    Double proteines();

    Double glucides();

    Double lipides();

    IdentifiantAliment id();

    String nom();

    Famille famille();

    Double apportCaloriqueEnProteines();

    Double apportCaloriqueEnGlucides();

    Double apportCaloriqueEnLipides();
}
