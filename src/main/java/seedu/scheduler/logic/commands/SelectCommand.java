package seedu.scheduler.logic.commands;

import seedu.scheduler.commons.core.EventsCenter;
import seedu.scheduler.commons.core.Messages;
import seedu.scheduler.commons.events.ui.JumpToListRequestEvent;
import seedu.scheduler.commons.core.UnmodifiableObservableList;
import seedu.scheduler.model.entry.ReadOnlyEntry;

/**
 * Selects a entry identified using it's last displayed index from the scheduler.
 */
public class SelectCommand extends Command {

    public final int targetIndex;

    public static final String COMMAND_WORD = "select";
    public static final String COMMAND_WORD2 = "s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects the entry identified by the index number used in the last entry listing.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SELECT_ENTRY_SUCCESS = "Selected Entry: %1$s";

    public SelectCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute() {

        UnmodifiableObservableList<ReadOnlyEntry> lastShownList = model.getFilteredEntryList();

        if (lastShownList.size() < targetIndex) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }

        EventsCenter.getInstance().post(new JumpToListRequestEvent(targetIndex - 1));
        return new CommandResult(String.format(MESSAGE_SELECT_ENTRY_SUCCESS, targetIndex));

    }

}
