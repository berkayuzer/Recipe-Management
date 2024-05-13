package src.Modification;

import src.RecipeConcrete;

import java.util.List;

public class ModifyCategoriesCommand implements Command {
    private RecipeConcrete recipeConcrete;
    private List<String> oldCategories;
    private List<String> newCategories;

    public ModifyCategoriesCommand(RecipeConcrete recipeConcrete, List<String> newCategories) {
        this.recipeConcrete = recipeConcrete;
        this.newCategories = newCategories;
        this.oldCategories = recipeConcrete.getCategories();
    }

    @Override
    public void execute() {
        recipeConcrete.setCategories(newCategories);
    }

    @Override
    public void undo() {
        recipeConcrete.setCategories(oldCategories);
    }
}
