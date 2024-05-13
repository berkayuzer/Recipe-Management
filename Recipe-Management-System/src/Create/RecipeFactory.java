package src.Create;

import src.RecipeConcrete;

import java.util.List;

public interface RecipeFactory {
    RecipeConcrete createRecipe(String name, List<String> ingredients, String cookingInstructions, int servingSize, List<String> categories, List<String> tags, float avgRating);
}
