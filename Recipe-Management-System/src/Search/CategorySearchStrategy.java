package src.Search;

import src.RecipeConcrete;

import java.util.List;

public class CategorySearchStrategy implements SearchStrategy{
    @Override
    public List<RecipeConcrete> search(List<RecipeConcrete> recipeConcretes, String keyword) {
        return recipeConcretes.stream()
                .filter(recipe -> recipe.getCategories().stream()
                        .anyMatch(category -> category.equalsIgnoreCase(keyword)))
                .collect(java.util.stream.Collectors.toList());
    }
}
