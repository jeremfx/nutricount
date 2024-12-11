package infrastructure.persistence.ciqual;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.application.repository.RecipeRepository;
import core.domain.food.IdentifiantRecette;
import core.domain.food.Recette;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeFileRepository implements RecipeRepository {
    private final Path storagePath;
    private final ObjectMapper objectMapper;

    public RecipeFileRepository() {
        this.storagePath = Paths.get(".");
        this.objectMapper = new ObjectMapper();

        try {
            Files.createDirectories(storagePath);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de créer le répertoire de stockage", e);
        }
    }

    @Override
    public void saveRecipe(Recette recette) {
        Path recipePath = storagePath.resolve(recette.id.toString() + ".json");
        try {
            String json = objectMapper.writeValueAsString(recette);
            Files.write(recipePath, json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde de la recette", e);
        }
    }

    @Override
    public Recette getRecipe(IdentifiantRecette id) {
        Path recipePath = storagePath.resolve(id.toString() + ".json");
        try {
            String json = Files.readString(recipePath);
            return objectMapper.readValue(json, Recette.class);
        } catch (IOException e) {
            throw new RuntimeException("Recette non trouvée: " + id, e);
        }
    }

    @Override
    public List<Recette> getRecipes() {
        List<Recette> recettes = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(storagePath, "*.json")) {
            for (Path path : stream) {
                String json = Files.readString(path);
                recettes.add(objectMapper.readValue(json, Recette.class));
            }
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture des recettes", e);
        }
        return recettes;
    }
}
