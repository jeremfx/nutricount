package core.domain.food;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Recette implements Aliment {
    public final IdentifiantRecette id;
    public final String nom;
    private final List<Ingredient> ingredients;
    private final Macros macros;

    public Recette(List<Ingredient> ingredients) {
        id = new IdentifiantRecette(UUID.randomUUID());
        nom = "Recette " + ingredients.getFirst().alimentBasique().nom();
        this.ingredients = Collections.unmodifiableList(ingredients);
        this.macros = new Macros(ingredients.stream().map(Ingredient::proteines).reduce(0d, Double::sum),
                ingredients.stream().map(Ingredient::carbs).reduce(0d, Double::sum),
                ingredients.stream().map(Ingredient::fats).reduce(0d, Double::sum));
    }

    public Recette renommer(String nouveauNom) {
        return new Recette(id, nouveauNom, ingredients);
    }

    public Recette reajusterIngredient(IdentifiantAliment identifiantAliment, Poids newPoids) {
        if (!ingredients.stream().map(i -> i.alimentBasique().id()).toList().contains(identifiantAliment)) {
            throw new NoSuchElementException("Can't resize ingredient because it's missing it's not in the recipe");
        }
        List<Ingredient> updatedIngredients = ingredients.stream().map(i -> {
            if (i.alimentBasique().id().equals(identifiantAliment)) {
                return new Ingredient(i.alimentBasique(), newPoids);
            } else {
                return i;
            }
        }).toList();
        return copy(updatedIngredients);
    }

    @JsonCreator
    public Recette(
            @JsonProperty("id") IdentifiantRecette id,
            @JsonProperty("name") String nom,
            @JsonProperty("ingredients") List<Ingredient> ingredients
    ) {
        this.id = id;
        this.nom = nom;
        this.ingredients = Collections.unmodifiableList(ingredients);
        this.macros = new Macros(ingredients.stream().map(Ingredient::proteines).reduce(0d, Double::sum),
                ingredients.stream().map(Ingredient::carbs).reduce(0d, Double::sum),
                ingredients.stream().map(Ingredient::fats).reduce(0d, Double::sum));
    }

    public Recette copy(List<Ingredient> ingredients) {
        return new Recette(id, nom, ingredients);
    }

    @Override
    public Double calories() {
        return macros.calories();
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public Recette ajouterIngredient(Ingredient ingredient) {
        List<Ingredient> newIngredients = new ArrayList<>(ingredients);
        newIngredients.add(ingredient);
        return copy(newIngredients);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recette recette = (Recette) o;

        return Objects.equals(id, recette.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public Double proteines() {
        return macros.protein();
    }

    @Override
    public Double glucides() {
        return macros.carbohydrate();
    }

    @Override
    public Double lipides() {
        return macros.fat();
    }

    @Override
    public IdentifiantAliment id() {
        return new IdentifiantAliment((int) this.id.id().getLeastSignificantBits());
    }

    @Override
    public String nom() {
        return this.nom;
    }


    @Override
    public Famille famille() {
        return new Famille("Rectte maison");
    }

    @Override
    public Double apportCaloriqueEnProteines() {
        return macros.apportCaloriqueEnProteines();
    }

    @Override
    public Double apportCaloriqueEnGlucides() {
        return macros.apportCaloriqueEnGlucides();
    }

    @Override
    public Double apportCaloriqueEnLipides() {
        return macros.apportCaloriqueEnLipides();
    }

}
