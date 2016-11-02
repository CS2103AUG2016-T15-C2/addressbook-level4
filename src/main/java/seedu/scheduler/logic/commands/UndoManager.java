package seedu.scheduler.logic.commands;

import java.util.LinkedList;
import java.util.Stack;

//@@author A0152962B
/**
 * Manager that undoable commands have to go through after executing a valid command.
 */
public class UndoManager {
    
    //public static final String MESSAGE_UNDO_FAIL = "Unable to undo.";
    //public static final String MESSAGE_REDO_FAIL = "Unable to redo.";
    //public static final String MESSAGE_UNDO_SUCCESS = "Undo successful.";
    //public static final String MESSAGE_REDO_SUCCESS = "Redo successful.";
    
    private LinkedList<UndoableCommand> commandUndoStack = new LinkedList<UndoableCommand>(); //Use LinkedList instead Stack to remove last element when there's more than 10 commands
    private Stack<UndoableCommand> commandRedoStack = new Stack<UndoableCommand>();

    /**
     * Push cmd into commandUndoStack (up to 10 commands).
     * 
     * @param cmd UndoableCommand given by each command after execution of a valid command.
     */
    public void stackCommand(UndoableCommand cmd) {
        //if (cmd instanceof ClearCommand) {
        //    commandUndoStack.clear();
        //    commandRedoStack.clear();
        //}
        if (commandUndoStack.size() == 10) {
            commandUndoStack.removeLast();
        }
        commandUndoStack.push(cmd);
        commandRedoStack.clear();
    }

    /**
     * Execute undo command from respective commands.
     */
    public void undo() {
        if (commandUndoStack.size() > 0) {
            UndoableCommand cmd = commandUndoStack.pop();
            commandRedoStack.push(cmd);
            cmd.undo();
        }
    }

    /**
     * Execute redo command from respective commands.
     */
    public void redo() {
        if (commandRedoStack.size() > 0) {
            UndoableCommand cmd = commandRedoStack.pop();
            commandUndoStack.push(cmd);
            cmd.redo();
        }
    }
}
