package seedu.scheduler.logic.commands;

import java.util.LinkedList;
import java.util.Stack;

//@@author A0152962B
/**
 * Manager that commands have to go through
 */
public class UndoManager {
    
    //public static final String MESSAGE_UNDO_FAIL = "Unable to undo.";
    //public static final String MESSAGE_REDO_FAIL = "Unable to redo.";
    //public static final String MESSAGE_UNDO_SUCCESS = "Undo successful.";
    //public static final String MESSAGE_REDO_SUCCESS = "Redo successful.";
    
    private LinkedList<Command> commandUndoStack = new LinkedList<Command>();
    private Stack<Command> commandRedoStack = new Stack<Command>();

    /**
     * Push cmd into commandUndoStack (up to 10 commands).
     * 
     * @param Command cmd from Parser
     * @return Command cmd as needed by Parser
     */
    public Command stackCommand(Command cmd) {
        if (cmd instanceof UndoableCommand) {
            if (commandUndoStack.size() == 10) {
                commandUndoStack.removeLast();
            }
            commandUndoStack.push(cmd);
            commandRedoStack.clear();
        } //else if (cmd instanceof ClearCommand) {
            //commandUndoStack.clear();
            //commandRedoStack.clear();
        //}
        return cmd;
    }

    /**
     * Execute undo command from respective commands.
     */
    public void undo() {
        if (commandUndoStack.size() > 0) {
            UndoableCommand cmd = (UndoableCommand) commandUndoStack.pop();
            commandRedoStack.push(cmd);
            cmd.undo();
        }
    }

    /**
     * Execute redo command from respective commands.
     */
    public void redo() {
        if (commandRedoStack.size() > 0) {
            UndoableCommand cmd = (UndoableCommand) commandRedoStack.pop();
            commandUndoStack.push(cmd);
            cmd.redo();
        }
    }
}
