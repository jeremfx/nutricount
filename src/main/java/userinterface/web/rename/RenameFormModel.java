package userinterface.web.rename;

import core.domain.food.IdentifiantRecette;

public record RenameFormModel(IdentifiantRecette identifiantRecette, String recipeName){}
