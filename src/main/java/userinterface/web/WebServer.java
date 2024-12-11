package userinterface.web;

import core.application.service.Core;
import core.domain.food.IdentifiantRecette;
import core.domain.food.Recette;
import infrastructure.persistence.ciqual.FoodRepositoryCiqual;
import infrastructure.persistence.ciqual.RecipeFileRepository;
import io.javalin.Javalin;
import io.javalin.http.ContentType;
import userinterface.web.addingredient.AddIngredientForm;
import userinterface.web.addingredient.AddIngredientFormModel;
import userinterface.web.rename.RenameForm;
import userinterface.web.rename.RenameFormModel;
import userinterface.web.resizeingredient.ResizeIngredientForm;
import userinterface.web.resizeingredient.ResizeIngredientFormModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class WebServer {

    private final Core core = new Core(new FoodRepositoryCiqual(), new RecipeFileRepository());

    public void start(int port) {
        Javalin app = Javalin.create(config -> {
            //config.staticFiles("static", Location.CLASSPATH);
            config.showJavalinBanner = false;
        }).start(port);

        app.get("/", ctx -> {
            ctx.contentType(ContentType.HTML);

            DefaultPage defaultPage = new DefaultPage(
                    new SearchBar(Optional.empty())
                    , new SearchResult(core, getSearchTerm(Optional.ofNullable(ctx.queryParam("search-term")))));
            ctx.result(defaultPage.render());
        });

        app.post(HttpRoutes.CREER_RECETTE.path, ctx -> {
            AddIngredientFormModel addIngredientFormModel = AddIngredientForm.parseResponse(ctx.formParamMap());
            IdentifiantRecette identifiantRecette = core.createRecipe(addIngredientFormModel.identifiantAliment(), addIngredientFormModel.poids());
            ctx.redirect("/recipes/" + identifiantRecette);
        });

        app.get(HttpRoutes.LIRE_RECETTE.path, ctx -> {
            ctx.contentType(ContentType.HTML);
            IdentifiantRecette id = new IdentifiantRecette(UUID.fromString(ctx.pathParam("id")));
            SearchResult searchResult = new SearchResult(core, ctx.queryParam("search-term"), id);
            Recette recette = core.getRecipe(id);
            RecipePage recipePage = new RecipePage(searchResult, recette);
            ctx.result(new DefaultPage(new SearchBar(Optional.of(id)), recipePage).render());
        });

        app.post(HttpRoutes.AJOUTER_INGREDIENT.path, ctx -> {
            AddIngredientFormModel addIngredientFormModel = AddIngredientForm.parseResponse(ctx.formParamMap());
            core.addIngredient(new IdentifiantRecette(UUID.fromString(ctx.pathParam("id"))), addIngredientFormModel.identifiantAliment(), addIngredientFormModel.poids());
            ctx.redirect(HttpRoutes.LIRE_RECETTE.path.replace("{id}", ctx.pathParam("id")));
        });

        app.post(HttpRoutes.REAJUSTER_INGREDIENT.path, ctx -> {
            ResizeIngredientFormModel resizeIngredientFormModel = ResizeIngredientForm.parseResponse(ctx.formParamMap());
            core.resizeIngredient(new IdentifiantRecette(UUID.fromString(ctx.pathParam("id"))), resizeIngredientFormModel.identifiantAliment(), resizeIngredientFormModel.newPoids());
            ctx.redirect(HttpRoutes.LIRE_RECETTE.path.replace("{id}", ctx.pathParam("id")));
        });

        app.post(HttpRoutes.RENOMMER_RECETTE.path, ctx -> {
            RenameFormModel formModel = RenameForm.parseResponse(ctx.formParamMap());
            core.renameRecipe(formModel.identifiantRecette(), formModel.recipeName());
            ctx.redirect(HttpRoutes.LIRE_RECETTE.path.replace("{id}", ctx.pathParam("id")));
        });

        app.get(HttpRoutes.LIRE_RECETTES.path, ctx -> {
            ctx.contentType(ContentType.HTML);
            List<Recette> recettes = core.getRecipes();
            ctx.result(new DefaultPage(new SearchBar(Optional.empty()), new RecipesResult(recettes)).render());
        });
    }

    private String getSearchTerm(Optional<String> searchTerm) {
        return searchTerm
                .filter(s -> !s.isBlank())
                .orElse("all");
    }
}