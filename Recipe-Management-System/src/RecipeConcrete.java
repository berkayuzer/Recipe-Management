package src;

import java.util.List;

public class RecipeConcrete extends Recipe {
    List<String> categories, tags, ingredients;
    String name, cookingInstructions;
    int servingSize;
    float avgRating;
    public RecipeConcrete(String name, List<String> ingredients, String cookingInstructions, int servingSize, List<String> categories, List<String> tags, float avgRating) {
        this.name = name;
        this.ingredients = ingredients;
        this.cookingInstructions = cookingInstructions;
        this.servingSize = servingSize;
        this.categories = categories;
        this.avgRating = avgRating;
        this.tags = tags;
    }
    @Override
    public List<String> getIngredients(){
    return ingredients;
    }


    @Override
    public void setIngredients(List<String> ingredients){
        this.ingredients = ingredients;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getInstructions(){
        return cookingInstructions;
    }

    @Override
    public void setInstructions(String cookingInstructions){
    this.cookingInstructions = cookingInstructions;
    }

    @Override
    public int getServingSize(){
        return servingSize;
    }

    @Override
    public void setCategories(List<String> categories){
        this.categories = categories;
    }

    @Override
    public List<String> getCategories(){
        return categories;
    }

    @Override
    public void setTags(List<String> tags){
        this.tags=tags;
    }

@Override
    public List<String> getTags(){
        return tags;
    }

    @Override
    public void setAvgRating(float avgRating){ this.avgRating=avgRating; }
}