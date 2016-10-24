package seedu.scheduler.logic.commands;

import java.util.LinkedList;
import java.util.Stack;

public class CommandManager {
    
    public static final String MESSAGE_UNDO_FAIL = "No commands to undo.";
    public static final String MESSAGE_REDO_FAIL = "No commands to redo.";
    
    private LinkedList<Command> commandUndoStack = new LinkedList<Command>();
    private Stack<Command> commandRedoStack = new Stack<Command>();

    /**
     * Push cmd into commandUndoStack (up to 10 commands).
     * 
     * @param Command
     *            cmd from Parser
     * @return Command cmd as needed by Parser
     */
    public Command ExecuteCommand(Command cmd) {
        if (cmd instanceof UndoableCommand) {
            if (commandUndoStack.size() == 10) {
                commandUndoStack.removeLast();
            }
            commandUndoStack.push(cmd);
            commandRedoStack.clear();
        }
        return cmd;
    }

    /**
     * Execute undo command from respective commands.
     * 
     * @throws Exception
     */
    public void Undo() throws Exception {
        if (commandUndoStack.size() > 0) {
            UndoableCommand cmd = (UndoableCommand) commandUndoStack.pop();
            commandRedoStack.push(cmd);
            cmd.undo();
        }
    }

    /**
     * Execute redo command from respective commands.
     * 
     * @throws Exception
     */
    public void Redo() throws Exception {
        if (commandRedoStack.size() > 0) {
            UndoableCommand cmd = (UndoableCommand) commandRedoStack.pop();
            commandUndoStack.push(cmd);
            cmd.redo();
        }
    }
}
