package src.Modification;

import java.util.Stack;

public class RecipeModificationInvoker {
    private Stack<Command> commands = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        commands.push(command);
    }

    public void undoCommand() {
        if (!commands.isEmpty()) {
            Command command = commands.pop();
            command.undo();
        }
        else {
            System.out.println("No commands to undo.");
        }
    }
}
