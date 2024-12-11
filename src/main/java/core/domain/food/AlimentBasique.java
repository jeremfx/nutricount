package core.domain.food;

public record AlimentBasique(IdentifiantAliment id, String nom, Famille famille, Macros macros) implements Aliment {
    @Override
    public Double calories() {
        return macros().calories();
    }

    @Override
    public Double proteines() {
        return macros().protein();
    }
    @Override
    public Double glucides() {
        return macros().carbohydrate();
    }

    @Override
    public Double lipides() {
        return macros().fat();
    }

    @Override
    public Double apportCaloriqueEnProteines() {
        return macros().apportCaloriqueEnProteines();
    }

    @Override
    public Double apportCaloriqueEnGlucides() {
        return macros().apportCaloriqueEnGlucides();
    }

    @Override
    public Double apportCaloriqueEnLipides() {
        return macros.apportCaloriqueEnLipides();
    }
}
