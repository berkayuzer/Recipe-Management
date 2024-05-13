package src.Modification;

import src.RecipeConcrete;

import java.util.List;

public class ModifyIngredientsCommand implements Command {
    private RecipeConcrete recipeConcrete;
    private List<String> oldIngredients;
    private List<String> newIngredients;

    public ModifyIngredientsCommand(RecipeConcrete recipeConcrete, List<String> newIngredients) {
        this.recipeConcrete = recipeConcrete;
        this.newIngredients = newIngredients;
        this.oldIngredients = recipeConcrete.getIngredients();
    }

    @Override
    public void execute() {
        recipeConcrete.setIngredients(newIngredients);
    }

    @Override
    public void undo() {
        recipeConcrete.setIngredients(oldIngredients);
    }
}
