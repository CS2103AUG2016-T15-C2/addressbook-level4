package seedu.address.logic.commands;

public abstract class UndoableCommand extends Command{

    /**
     * Undo the most recent command.
     */
    public abstract void  undo() throws Exception;
    
}
