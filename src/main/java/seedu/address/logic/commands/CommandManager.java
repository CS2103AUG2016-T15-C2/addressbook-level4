package seedu.address.logic.commands;

import java.util.Stack;

public class CommandManager {
    private Stack<Command> commandStack = new Stack<Command>();
    
    public Command ExecuteCommand(Command cmd)
    {
        //cmd.execute();
        if(cmd instanceof UndoableCommand)
        {
            commandStack.push(cmd);
        }
        return cmd;
    }
    
    public Command Undo() throws Exception
    {
        if(commandStack.size() > 0)
        {
            UndoableCommand cmd = (UndoableCommand)commandStack.pop();
            cmd.undo();
        }
        return null;
    }
}
