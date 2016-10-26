package seedu.scheduler.logic.commands;

/**
 * Represents a command that is undoable.
 * @@author A0152962B
 *
 */
public abstract class UndoableCommand extends Command {

    /**
     * Undo the most recent commands (up to 10).
     */
    public abstract void undo() throws Exception;

    /**
     * Redo the recently undone command.
     */
    public abstract void redo() throws Exception;

}
