package userinterface.web;

import core.application.service.Core;
import core.domain.food.Recipe;
import core.domain.food.RecipeId;
import infrastructure.persistence.ciqual.FoodRepositoryCiqual;
import infrastructure.persistence.ciqual.RecipeRepositoryInMemory;
import io.javalin.Javalin;
import io.javalin.http.ContentType;
import userinterface.web.addingredient.AddIngredientForm;
import userinterface.web.addingredient.AddIngredientFormModel;
import userinterface.web.resizeingredient.ResizeIngredientForm;
import userinterface.web.resizeingredient.ResizeIngredientFormModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class WebServer {

    private final Core core = new Core(
                    new FoodRepositoryCiqual(),
                    new RecipeRepositoryInMemory());

    public void start(int port) {
        Javalin app = Javalin.create(config -> {
            //config.staticFiles("static", Location.CLASSPATH);
            config.showJavalinBanner = false;
        }).start(port);

        app.get("/", ctx -> {
            ctx.contentType(ContentType.HTML);
            DefaultPage defaultPage = new DefaultPage(
                    new SearchBar(Optional.empty()), new SearchResult(core, "all")
            );
            ctx.result(defaultPage.render());
        });

        app.post("/create-recipe", ctx -> {
            AddIngredientFormModel addIngredientFormModel = AddIngredientForm.parseResponse(ctx.formParamMap());
            RecipeId recipeId = core.createRecipe(addIngredientFormModel.id(), addIngredientFormModel.size());
            ctx.redirect("/recipes/" + recipeId);
        });

        app.get("/recipes/{id}", ctx -> {
            ctx.contentType(ContentType.HTML);
            RecipeId id = new RecipeId(UUID.fromString(ctx.pathParam("id")));
            SearchResult searchResult = new SearchResult(core, ctx.queryParam("search-term"), id);
            Recipe recipe = core.getRecipe(id);
            RecipePage recipePage = new RecipePage(searchResult, recipe);
            ctx.result(new DefaultPage(new SearchBar(Optional.of(id)), recipePage).render());
        });

        app.post("/recipes/{id}/add-ingredient", ctx -> {
            AddIngredientFormModel addIngredientFormModel = AddIngredientForm.parseResponse(ctx.formParamMap());
            core.addIngredient(new RecipeId(UUID.fromString(ctx.pathParam("id"))), addIngredientFormModel.id(), addIngredientFormModel.size());
            ctx.redirect("/recipes/" + ctx.pathParam("id"));
        });

        app.post("/recipes/{id}/resize-ingredient", ctx -> {
            ResizeIngredientFormModel resizeIngredientFormModel = ResizeIngredientForm.parseResponse(ctx.formParamMap());
            core.resizeIngredient(new RecipeId(UUID.fromString(ctx.pathParam("id"))), resizeIngredientFormModel.foodId(), resizeIngredientFormModel.newSize());
            ctx.redirect("/recipes/" + ctx.pathParam("id"));
        });

        app.get("/recipes", ctx -> {
            ctx.contentType(ContentType.HTML);
            List<Recipe> recipes = core.getRecipes();
            ctx.result(new DefaultPage(new SearchBar(Optional.empty()), new RecipesResult(recipes)).render());
        });
    }
}