package src.Rating;
import src.RecipeConcrete;
import src.RecipeRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class RecipeRatingManager {
    static private RecipeRatingManager instance;
    private Map<RecipeConcrete, Float> recipeRatings;
    public String filePath;

    // Private constructor to prevent instantiation from outside
    private RecipeRatingManager() {
        recipeRatings = new HashMap<>();
    }

    // Method to get the singleton instance
    public static RecipeRatingManager getInstance() {
        if (instance == null) {
            instance = new RecipeRatingManager();
        }
        return instance;
    }

    // Method to rate a recipeConcrete
    public void rateRecipe(RecipeConcrete recipeConcrete, float rating) {
        recipeRatings.put(recipeConcrete, rating);
        computeAvgRating(recipeConcrete);
    }

    // Method to compute the average rating of a recipeConcrete
    public float computeAvgRating(RecipeConcrete recipeConcrete) {
        if (!recipeRatings.containsKey(recipeConcrete)) return 0;
        float sum = 0;
        int count = 0;
        for (Map.Entry<RecipeConcrete, Float> entry : recipeRatings.entrySet()) {
            if (entry.getKey().equals(recipeConcrete)) {
                sum += entry.getValue();
                count++;
                System.out.println(count);
            }
        }
        if (count == 0) return 0; // No ratings available

        float avg = sum / count;
        recipeConcrete.setAvgRating(avg);
        System.out.println(avg);
        return avg;

    }
}