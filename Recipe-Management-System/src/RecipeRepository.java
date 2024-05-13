package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {
    private static List<RecipeConcrete> recipes;
    public static String[] tags = {"Vegetarian", "Gluten-free", "Spicy", "Cocktail", "Canepe", "Chips and Dip", "Fruit", "Meat", "Chicken", "Pork", "Seafood", "Vegetarian", "Cake", "Pie", "Cookies", "Ice Cream", "Candy"};
    public static String[] categories = {"Main-Dish", "Dessert", "Appetizer", "Soup", "Kebabs", "Pizza", "Pasta"};

    public String filePath;

    public RecipeRepository(String filePath) {
        this.filePath = filePath;
        recipes = new ArrayList<>();
        loadRecipes(); // Load recipes from file when repository is instantiated
    }

    private void loadRecipes() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            recipes = (List<RecipeConcrete>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If file doesn't exist or is corrupted, ignore and create a new empty list
            recipes = new ArrayList<>();
        }
    }

    // Method to save recipes to file
    private void saveRecipes() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveRecipe(RecipeConcrete recipe) {
        recipes.add(recipe);
        saveRecipes(); // Save recipes to file after adding a new recipe
    }

    public List<RecipeConcrete> getAllRecipes() {
        return recipes;
    }
    public void write() {
        for (RecipeConcrete element : recipes) {
            StringBuilder sb = new StringBuilder();
            sb.append("Recipe Name: ").append(element.name).append("\n");
            sb.append("Serving Size: ").append(element.servingSize).append("\n");
            sb.append("Ingredients:\n");
            for (String ingredient : element.ingredients) {
                sb.append("- ").append(ingredient).append("\n");
            }
            sb.append("Cooking Instructions:\n").append(element.cookingInstructions).append("\n");
            sb.append("Categories:\n");
            for (String category : element.categories) {
                sb.append("- ").append(category).append("\n");
            }
            sb.append("Tags:\n");
            for (String tag : element.tags) {
                sb.append("- ").append(tag).append("\n");
            }
            System.out.println(sb);

    }
        }

}


