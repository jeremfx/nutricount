package infrastructure.persistence.ciqual;

import core.application.repository.FoodRepository;
import core.domain.food.AlimentBasique;
import core.domain.food.Famille;
import core.domain.food.IdentifiantAliment;
import core.domain.food.Macros;
import infrastructure.persistence.ciqual.compositions.CompositionXml;
import infrastructure.persistence.ciqual.foodgroups.FoodGroupXml;
import infrastructure.persistence.ciqual.foods.FoodXml;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FoodRepositoryCiqual implements FoodRepository {

    CiqualReader ciqualReader = new CiqualReader();
    List<AlimentBasique> alimentBasiques = ciqualReader.foodsXml.stream().map(f -> createFood(f, findFoodGroupByCode(f.groupCode, ciqualReader.foodGroupsXml))).toList();

    private AlimentBasique createFood(FoodXml foodXml, FoodGroupXml foodGroupXml) {
        return new AlimentBasique(new IdentifiantAliment(foodXml.code), foodXml.name, createFoodGroup(foodGroupXml), createMacros(foodXml.code));
    }

    private Macros createMacros(Integer foodCode) {
        return getKcal(foodCode)
                .map(kcal -> new Macros(kcal, getProtein(foodCode), getCarbs(foodCode), getFats(foodCode)))
                .orElse(new Macros(getProtein(foodCode), getCarbs(foodCode), getFats(foodCode )));
    }

    private Optional<Double> getKcal(Integer foodCode) {
        Integer kcalCode = ciqualReader.constantsXml.stream()
                .filter(c -> c.nameFr.trim().equals("Energie, Règlement UE N° 1169/2011 (kcal/100 g)"))
                .findFirst().orElseThrow().code;

      return getCompositionsForFood(foodCode).stream()
                .filter(c -> c.constantCode.equals(kcalCode))
                .findFirst()
                .map(c -> c.content);
    }

    private Double getProtein(Integer foodCode) {
        Integer proteinCode = ciqualReader.constantsXml.stream()
                .filter(c -> c.nameFr.trim().equals("Protéines, N x 6.25 (g/100 g)"))
                .findFirst().orElseThrow().code;

        return getCompositionsForFood(foodCode).stream()
                .filter(c -> c.constantCode.equals(proteinCode))
                .findFirst()
                .map(c -> c.content)
                .orElseGet(() -> {
                    //System.out.println("Protein not found for food " + foodCode);
                    return 0d;
                });
    }

    private Double getCarbs(Integer foodCode) {
        Integer proteinCode = ciqualReader.constantsXml.stream()
                .filter(c -> c.nameFr.trim().equals("Glucides (g/100 g)"))
                .findFirst().orElseThrow().code;

        return getCompositionsForFood(foodCode).stream()
                .filter(c -> c.constantCode.equals(proteinCode))
                .findFirst()
                .map(c -> c.content)
                .orElseGet(() -> {
                    //System.out.println("Carbohydrates not found for food " + foodCode);
                    return 0d;
                });
    }

    private Double getFats(Integer foodCode) {
        Integer proteinCode = ciqualReader.constantsXml.stream()
                .filter(c -> c.nameFr.trim().equals("Lipides (g/100 g)"))
                .findFirst().orElseThrow().code;

        return getCompositionsForFood(foodCode).stream()
                .filter(c -> c.constantCode.equals(proteinCode))
                .findFirst()
                .map(c -> c.content)
                .orElseGet(() -> {
                    //System.out.println("Fats not found for food " + foodCode);
                    return 0d;
                });
    }

    @NotNull
    private List<CompositionXml> getCompositionsForFood(Integer foodCode) {
        return ciqualReader.compositionsXml.stream()
                .filter(c -> c.foodCode.equals(foodCode)).collect(Collectors.toList());
    }

/*    private Double getKcalOfFood(Integer foodCode) {
        Integer kcalCode = ciqualReader.constantsXml.stream()
                .filter(c -> c.nameFr.trim().equals("Energie, Règlement UE N° 1169/2011 (kcal/100 g)"))
                .findFirst().orElseThrow().code;

        return ciqualReader.compositionsXml.stream()
                .filter(c -> c.foodCode.equals(foodCode))
                .filter(c -> c.constantCode.equals(kcalCode))
                .findFirst()
                .map(c -> c.content)
                .orElseGet(() -> {
                    System.out.println("Kcal not found for food " + foodCode);
                    return 0d;
                });
    }

    private Double getKcalOfFood(Integer foodCode) {
        Integer kcalCode = ciqualReader.constantsXml.stream()
                .filter(c -> c.nameFr.trim().equals("Energie, Règlement UE N° 1169/2011 (kcal/100 g)"))
                .findFirst().orElseThrow().code;

        return ciqualReader.compositionsXml.stream()
                .filter(c -> c.foodCode.equals(foodCode))
                .filter(c -> c.constantCode.equals(kcalCode))
                .findFirst()
                .map(c -> c.content)
                .orElseGet(() -> {
                    System.out.println("Kcal not found for food " + foodCode);
                    return 0d;
                });
    }*/

    private FoodGroupXml findFoodGroupByCode(String groupCode, List<FoodGroupXml> foodGroupsXml) {
        FoodGroupXml foodGroupNullObject = new FoodGroupXml();
        foodGroupNullObject.code = "NOT FOUND";
        foodGroupNullObject.name = "Le groupe n'a pas été trouvé pour cet aliment";
        return foodGroupsXml.stream().filter(fg -> fg.code.equals(groupCode)).findFirst().orElse(foodGroupNullObject);
    }

    private Famille createFoodGroup(FoodGroupXml foodGroupXml) {
        return new Famille(foodGroupXml.name);
    }

    @Override
    public AlimentBasique getFood(IdentifiantAliment id) {
        return alimentBasiques.stream().filter(f -> f.id().equals(id)).findFirst().orElseThrow();
    }

    @Override
    public Optional<AlimentBasique> findFoodByName(String foodName) {
        return alimentBasiques.stream().filter(f -> f.nom().equalsIgnoreCase(foodName)).findFirst();
    }

    @Override
    public List<AlimentBasique> searchFoodByName(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return List.of();
        }
        return alimentBasiques.stream().filter(f -> f.nom().toLowerCase().contains(searchTerm.toLowerCase())).toList();
    }

    @Override
    public List<AlimentBasique> getFoods() {
        return alimentBasiques;
    }
}
