package userinterface.web;

public enum HttpRoutes {
    CREATE_RECIPES(Verb.POST, "/create-recipe"), ADD_INGREDIENT(Verb.POST, "/recipe/{id}/add-ingredient");
    public final Verb verb;
    public final String path;

    HttpRoutes(Verb verb, String path) {
        this.verb = verb;
        this.path = path;
    }
}
