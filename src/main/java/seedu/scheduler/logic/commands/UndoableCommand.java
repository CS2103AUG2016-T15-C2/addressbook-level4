package seedu.scheduler.logic.commands;

//@@author A0152962B
/**
 * Represents a command that is undoable.
 */
public abstract class UndoableCommand extends Command {

    public static UndoManager undoManager = new UndoManager();
    
    /**
     * Undo the most recent commands (up to 10).
     */
    public abstract void undo();

    /**
     * Redo the recently undone command.
     */
    public abstract void redo();

}
