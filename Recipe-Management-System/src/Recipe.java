package src;

import java.util.List;

public abstract class Recipe {

    public String getName() {
        return null;
    }

    public void setName(String name) {
    }

    public String getInstructions() {
        return null;
    }

    public void setInstructions(String cookingInstructions) {
    }

    public int getServingSize() {
        return 0;
    }

    public void setCategories(List<String> categories) {
    }

    public List<String> getCategories() {
        return null;
    }

    public void setTags(List<String> tags) {
    }

    public List<String> getTags() {
        return null;
    }

    public void setAvgRating(float avgRating) {
    }

    public List<String> getIngredients() {
        return null;
    }

    public void setIngredients(List<String> ingredients) {
    }

    public void setServingSize(int servingSize) {
    }

    public float getAvgRating() {
        return 0;
    }

}
