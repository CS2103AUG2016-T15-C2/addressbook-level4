package seedu.scheduler.logic;

import javafx.collections.ObservableList;
import seedu.scheduler.commons.core.ComponentManager;
import seedu.scheduler.commons.core.LogsCenter;
import seedu.scheduler.logic.commands.Command;
import seedu.scheduler.logic.commands.CommandResult;
import seedu.scheduler.logic.commands.UndoableCommand;
import seedu.scheduler.logic.parser.Parser;
import seedu.scheduler.model.Model;
import seedu.scheduler.model.entry.ReadOnlyEntry;
import seedu.scheduler.storage.Storage;

import java.util.logging.Logger;

/**
 * The main LogicManager of the app.
 */
public class LogicManager extends ComponentManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Parser parser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.parser = new Parser();
    }

    @Override
    public CommandResult execute(String commandText) {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        Command command = parser.parseCommand(commandText);
        if(commandText == "undo") {
            UndoableCommand.undoManager.undo();
            return null;
        }
        if(commandText == "redo") {
            UndoableCommand.undoManager.redo();
            return null;
        }
        command.setData(model);
        return command.execute();
    }

    @Override
    public ObservableList<ReadOnlyEntry> getFilteredEntryList() {
        return model.getFilteredEntryList();
    }
}
