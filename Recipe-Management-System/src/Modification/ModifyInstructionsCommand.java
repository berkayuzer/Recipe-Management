package src.Modification;

import src.RecipeConcrete;

public class ModifyInstructionsCommand implements Command {
    private RecipeConcrete recipeConcrete;
    private String oldCookingInstructions;
    private String newCookingInstructions;

    public ModifyInstructionsCommand(RecipeConcrete recipeConcrete, String newCookingInstructions) {
        this.recipeConcrete = recipeConcrete;
        this.newCookingInstructions = newCookingInstructions;
        this.oldCookingInstructions = recipeConcrete.getInstructions();
    }

    @Override
    public void execute() {
        recipeConcrete.setInstructions(newCookingInstructions);
    }

    @Override
    public void undo() {
        recipeConcrete.setInstructions(oldCookingInstructions);
    }
}
