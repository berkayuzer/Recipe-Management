package src;
import src.Create.ConcreteRecipeFactory;
import src.Create.RecipeFactory;
import src.Modification.*;
import src.Rating.RecipeRatingManager;
import src.Search.CategorySearchStrategy;
import java.util.*;
import src.Search.IngredientSearchStrategy;
import src.Search.TagSearchStrategy;
import java.util.ArrayList;

public class RecipeClient {
    static Scanner scanner = new Scanner(System.in);
    static RecipeRepository repository = new RecipeRepository("RecipeDataset.txt");
    static RecipeRatingManager recipeRatingManager = RecipeRatingManager.getInstance();

    public static void main(String[] args) { displayMenu(); }

    static void displayMenu() {
        System.out.println("""
                Main Menu:
                 *select the index of the option that you wanna use*\s
                1.Create Recipe
                2.Search Recipe
                3.Modify Recipe
                4.Rate Recipe
                5.Exit""");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                createRecipe();
                break;
            case 2:
                searchRecipe();
                break;
            case 3:
                modifyRecipe();
                break;
            case 4:
                rateRecipe();
                break;
            case 5:
                System.exit(0);
        }
    }

    static void createRecipe() {
        float avgRating = 0;
        RecipeFactory factory = new ConcreteRecipeFactory();
        System.out.println("Create Recipe Menu");
        System.out.println("Enter the recipe name:");
        String recipeName = scanner.next();

        // Initialize an ArrayList to store ingredients
        ArrayList<String> ingredients = new ArrayList<>();

        // Prompt user to enter ingredients
        System.out.println("Enter the ingredients (enter 'done' when finished):");
        while (true) {
            String ingredient = scanner.nextLine();
            if (ingredient.equalsIgnoreCase("done")) {
                break;
            }
            if (!ingredient.isEmpty())
                ingredients.add(ingredient);
        }
        // Prompt user to enter cooking instructions
        System.out.println("Enter the cooking instructions:");
        String cookingInstructions = scanner.nextLine();

        System.out.println("Enter the serving size:");
        int servingSize = scanner.nextInt();

        // Display the entered recipe details
        System.out.println("Recipe Name: " + recipeName);
        System.out.println("Serving Size: " + servingSize);
        System.out.println("Ingredients:");
        for (String ingredient : ingredients) {
            System.out.println("- " + ingredient);
        }
        System.out.println("Cooking Instructions:");
        System.out.println(cookingInstructions);

        System.out.println("Choose from category you want to add (enter 'done' when finished):" + Arrays.toString(repository.categories));


        ArrayList<String> selectedCategories = new ArrayList<>();
        while (selectedCategories.size() < 3) {
            String category = scanner.next();
            if (category.equalsIgnoreCase("done"))
                break;

            boolean categoryFound = false;
            for (String element : repository.categories) {
                if (element.equals(category)) {
                    selectedCategories.add(category);
                    categoryFound = true;
                    break;
                }
            }
            if (!categoryFound) {
                System.out.println("Warning: Category '" + category + "' not found in the array. Please enter another category:");
            }
        }
        System.out.println("The categories you chose: " + selectedCategories);

        System.out.println("Choose from tags you want to add (enter 'done' when finished):" + Arrays.toString(repository.tags));
        ArrayList<String> selectedTags = new ArrayList<>();
        while (selectedTags.size() < 3) {
            String tag = scanner.next();
            if (tag.equalsIgnoreCase("done")) {
                break;
            }
            boolean tagFound = false;
            for (String element : repository.tags) {
                if (element.equals(tag)) {
                    selectedTags.add(tag);
                    tagFound = true;
                    break;
                }
            }
            if (!tagFound) {
                System.out.println("Warning: Tag '" + tag + "' not found in the array. Please enter another tag:");
            }
        }
        System.out.println("The tags you chose: " + selectedTags);
        System.out.println("Do you want to save this recipe: (Yes or No)");
        String save = scanner.next();
        if (Objects.equals(save, "Yes")) {
            repository.saveRecipe(factory.createRecipe(recipeName, ingredients, cookingInstructions, servingSize, selectedCategories, selectedTags, avgRating));
            displayMenu();
        }

    }

    static void modifyRecipe() {
        List<RecipeConcrete> recipes = repository.getAllRecipes();
        RecipeModificationInvoker recipeModificationInvoker = new RecipeModificationInvoker();
        int modifyType;
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + "-" + recipes.get(i).getName() + "\nIngredients: " + recipes.get(i).getIngredients()
                    + "\nInstructions: " + recipes.get(i).getInstructions() + "\nServing Size: " + recipes.get(i).getServingSize()
                    + "\nCategories: " + recipes.get(i).getCategories() + "\nTags: " + recipes.get(i).getTags() + "\n---------------------");
        }
        System.out.println("Please enter the ID of the recipe: ");
        int recipeID = scanner.nextInt();

        RecipeConcrete recipeConcrete = recipes.get(recipeID - 1);
        String title = recipes.get(recipeID - 1).getName();
        List<String> ingredients = recipes.get(recipeID - 1).getIngredients();
        String cookingInstructions = recipes.get(recipeID - 1).getInstructions();
        int servingSize = recipes.get(recipeID - 1).getServingSize();
        List<String> categories = recipes.get(recipeID - 1).getCategories();
        List<String> tags = recipes.get(recipeID - 1).getTags();
        // Prompt user to enter ingredients

        do {
            System.out.println("What do you want to modify?\n" +
                    "1.Ingredients\n" +
                    "2.Cooking Instructions\n" +
                    "3.Categories\n" +
                    "4.Tags\n" +
                    "5.Undo\n" +
                    "6.Back to Main Menu\n");

            modifyType = scanner.nextInt();

            switch (modifyType) {
                case 1 -> {
                    ingredients.clear();
                    System.out.println("Enter the ingredients (enter 'done' when finished):");
                    String ingredient;
                    do {
                        ingredient = scanner.nextLine();
                        if (!ingredient.isEmpty() && !ingredient.equalsIgnoreCase("done")) {
                            ingredients.add(ingredient);
                        }
                    } while (!ingredient.equalsIgnoreCase("done"));
                    Command modifyIngredientsCommand = new ModifyIngredientsCommand(recipeConcrete, ingredients);
                    recipeModificationInvoker.executeCommand(modifyIngredientsCommand);
                    System.out.println(ingredients);
                    repository.saveRecipe(recipeConcrete);
                    repository.getAllRecipes().get(recipeID-1).setIngredients(ingredients);

                }

                case 2 -> {
                    System.out.println("Enter the new cooking instructions:");
                    scanner.nextLine();
                    String newCookingInstructions = scanner.nextLine();
                    Command modifyInstructionsCommand = new ModifyInstructionsCommand(recipeConcrete, newCookingInstructions);
                    recipeModificationInvoker.executeCommand(modifyInstructionsCommand);
                }

                case 3 -> {
                    categories.clear();
                    System.out.println("Choose from tags you want to add (enter 'done' when finished):" + Arrays.toString(RecipeRepository.tags));
                    String category;
                    do {
                        category = scanner.nextLine();
                        if (!category.isEmpty() && !category.equalsIgnoreCase("done")) {
                            categories.add(category);
                        }
                    } while (!category.equalsIgnoreCase("done") && categories.size() < 3);

                    Command modifyCategoriesCommand = new ModifyCategoriesCommand(recipeConcrete, categories);
                    recipeModificationInvoker.executeCommand(modifyCategoriesCommand);
                }

                case 4 -> {
                    tags.clear();
                    System.out.println("Choose from tags you want to add (enter 'done' when finished):" + Arrays.toString(RecipeRepository.tags));
                    String tag;
                    do {
                        tag = scanner.nextLine();
                        if (!tag.isEmpty() && !tag.equalsIgnoreCase("done")) {
                            tags.add(tag);
                        }
                    } while (!tag.equalsIgnoreCase("done") && tags.size() < 3);
                    Command modifyTagsCommand = new ModifyTagsCommand(recipeConcrete, tags);
                    recipeModificationInvoker.executeCommand(modifyTagsCommand);
                }

                case 5 -> {
                    recipeModificationInvoker.undoCommand();
                    System.out.println("Undo operation performed.");

                }

                case 6 -> {
                    displayRecipes(recipes);
                    System.out.println("Exiting.");
                    displayMenu();
                }
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (modifyType != 6);


    }

    static void rateRecipe() {
        List<RecipeConcrete> recipes = repository.getAllRecipes();

        // Display available recipes for rating
        System.out.println("Select a recipe to rate:");
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + ". " + recipes.get(i).getName());
            System.out.println((i + 1) + ". " + recipes.get(i).getAvgRating());
            repository.write();
        }
        int recipeIndex = scanner.nextInt();

        // Select the recipe for rating
        RecipeConcrete selectedRecipe = recipes.get(recipeIndex - 1);

        // Prompt user for rating with validation
        float rating = 0;
        boolean validRating = false;
        while (!validRating) {
            System.out.println("Enter your rating for '" + selectedRecipe.getName() + "' (0-5):");
            try {
                rating = scanner.nextFloat();
                if (rating < 0 || rating > 5) {
                    System.out.println("Invalid rating. Please enter a rating between 0 and 5.");
                } else {
                    validRating = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.nextLine(); // Clear input buffer
            }
        }
        // Rate the recipe using RecipeRatingManager
        recipeRatingManager.rateRecipe(selectedRecipe, rating);

        // Display confirmation message
        System.out.println("Rating saved successfully for '" + selectedRecipe.getName() + "'.");
        displayMenu();
    }

    static void searchRecipe(){
        List<RecipeConcrete> recipes = repository.getAllRecipes();
        String search;
        System.out.println("""
                Main Menu:
                 select the index of the option that you wanna use\s
                1.Category Search
                2.Tag Search 
                3.Ingredient Search""");
        int searchType = scanner.nextInt();
        scanner.nextLine();
        switch (searchType) {
            case 1:
                System.out.print("Enter the Categories: ");
                search = scanner.nextLine();
                List<RecipeConcrete> categorySearchStrategy = new CategorySearchStrategy().search(recipes, search);
                displayRecipes(categorySearchStrategy);
                break;
            case 2:
                System.out.print("Enter the Tag: ");
                search = scanner.nextLine();
                List<RecipeConcrete> tagSearchStratregy = new TagSearchStrategy().search(recipes, search);
                displayRecipes(tagSearchStratregy);
                break;
            case 3:
                System.out.print("Enter the Ingredient: ");
                search = scanner.nextLine();
                List<RecipeConcrete> ingredientSearchStratregy = new IngredientSearchStrategy().search(recipes, search);
                displayRecipes(ingredientSearchStratregy);
                break;
        }
        displayMenu();
    }
    static void displayRecipes(List<RecipeConcrete> recipeConcretes) {
        if (recipeConcretes.isEmpty()) System.out.println("No recipes found.");
        else {
            System.out.println("Search Results:");
            for (RecipeConcrete recipeConcrete : recipeConcretes) {
                System.out.println(recipeConcrete.getName() + "\n" + recipeConcrete.getInstructions() + "\n" + recipeConcrete.getIngredients() + "\n" + recipeConcrete.getCategories() + "\n" + recipeConcrete.getTags() + "\n" + recipeConcrete.getAvgRating() + "\n" + recipeConcrete.getServingSize() + "\n");
            }
        }
    }
}

