package src.Search;

import src.RecipeConcrete;

import java.util.List;

public interface SearchStrategy {
    List<RecipeConcrete> search(List<RecipeConcrete> recipeConcretes, String keyword);
}
