package src.Search;

import src.RecipeConcrete;

import java.util.List;
import java.util.stream.Collectors;

public class TagSearchStrategy implements SearchStrategy{
    @Override
    public List<RecipeConcrete> search(List<RecipeConcrete> recipeConcretes, String keyword) {
        return recipeConcretes.stream()
                .filter(recipe -> recipe.getTags().stream()
                        .anyMatch(tag -> tag.equalsIgnoreCase(keyword)))
                .collect(Collectors.toList());
    }
}
