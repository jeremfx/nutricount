package userinterface.web;

import userinterface.web.technical.Verb;

public enum HttpRoutes {
    LIRE_RECETTES(Verb.GET, "/recettes"),
    LIRE_RECETTE(Verb.GET, "/recettes/{id}"),
    CREER_RECETTE(Verb.POST, "/creer-recette"),
    AJOUTER_INGREDIENT(Verb.POST, "/recettes/{id}/ajouter-ingredient"),
    REAJUSTER_INGREDIENT(Verb.POST, "/recettes/{id}/reajuster-ingredient"),
    RENOMMER_RECETTE(Verb.POST, "/recettes/{id}/renommer");

    public final Verb verb;
    public final String path;

    HttpRoutes(Verb verb, String path) {
        this.verb = verb;
        this.path = path;
    }
}
