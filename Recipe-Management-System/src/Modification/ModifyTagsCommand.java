package src.Modification;

import src.RecipeConcrete;

import java.util.List;

public class ModifyTagsCommand implements Command{
    private RecipeConcrete recipeConcrete;
    private List<String> oldTags;
    private List<String> newTags;

    public ModifyTagsCommand(RecipeConcrete recipeConcrete, List<String> newTags) {
        this.recipeConcrete = recipeConcrete;
        this.newTags = newTags;
        this.oldTags = recipeConcrete.getTags();
    }

    @Override
    public void execute() {
        recipeConcrete.setTags(newTags);
    }

    @Override
    public void undo() {
        recipeConcrete.setTags(oldTags);
    }

}
