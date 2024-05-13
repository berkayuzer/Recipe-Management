package src.Create;

import src.RecipeConcrete;

import java.util.List;

public class ConcreteRecipeFactory implements RecipeFactory{
    @Override
    public RecipeConcrete createRecipe(String name, List<String> ingredients, String cookingInstructions, int servingSize, List<String> categories, List<String> tags, float avgRating) {
        return new RecipeConcrete(name, ingredients, cookingInstructions, servingSize, categories, tags, avgRating);
    }
}
