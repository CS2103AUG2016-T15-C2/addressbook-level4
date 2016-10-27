package seedu.scheduler.logic;

import javafx.collections.ObservableList;
import seedu.scheduler.logic.commands.CommandResult;
import seedu.scheduler.model.entry.ReadOnlyEntry;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws Exception 
     */
    CommandResult execute(String commandText);

    /** Returns the filtered list of entrys */
    ObservableList<ReadOnlyEntry> getFilteredEntryList();

}
