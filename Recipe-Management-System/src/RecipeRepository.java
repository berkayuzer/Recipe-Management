package src;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {
    private List<RecipeConcrete> recipeConcretes;
    public static String[] tags = {"Vegetarian","Gluten-free","Spicy"};
    public static String[] appetizerCategories = {"Cocktail","Canepe","Chips and Dip", "Fruit"};
    public static String[] mainDishCategories = {"Meat","Chicken","Pork","Seafood","Vegetarian", "Pasta"};
    public static String[] dessertCategories = {"Cake","Pie","Cookies","Ice Cream","Candy"};
    public RecipeRepository() {
        this.recipeConcretes = new ArrayList<>();
    }

    // Add a recipeConcrete to the repository
    public void saveRecipe(RecipeConcrete recipeConcrete) {
        recipeConcretes.add(recipeConcrete);
    }

    // Get all recipeConcretes in the repository
    public List<RecipeConcrete> getAllRecipes() {
        return new ArrayList<>(recipeConcretes); // Return a copy to prevent modification of the internal list
    }
}
