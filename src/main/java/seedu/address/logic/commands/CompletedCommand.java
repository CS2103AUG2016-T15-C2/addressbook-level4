package seedu.address.logic.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.EndTime;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.Name;
import seedu.address.model.entry.ReadOnlyEntry;
import seedu.address.model.entry.StartTime;
import seedu.address.model.entry.UniqueEntryList;
import seedu.address.model.entry.UniqueEntryList.EntryNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

public class CompletedCommand extends Command {
	public static final String COMMAND_WORD = "completed";

	public static final String MESSAGE_USAGE = COMMAND_WORD + ": Tags an entry as completed in the scheduler. "
			+ "Parameters: INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD
			+ " 1";

	public static final String MESSAGE_SUCCESS = "Entry marked as compeleted: %1$s";
	public static final String MESSAGE_DUPLICATE_ENTRY = "This entry already exists in the scheduler";

	public final int targetIndex;
	public final Entry toMarkCompleted;

	public CompletedCommand(int targetIndex, String name, String startTime, String endTime, String date, Set<String> tags)
            throws IllegalValueException {
        this.targetIndex = targetIndex;
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        tagSet.add(new Tag("Completed"));
        this.toMarkCompleted = new Entry(
                new Name(name),
                new StartTime(startTime),
                new EndTime(endTime),
                new Date(date),
                new UniqueTagList(tagSet)
        );
    }

	@Override
	public CommandResult execute() {
		UnmodifiableObservableList<ReadOnlyEntry> lastShownList = model.getFilteredEntryList();

		if (lastShownList.size() < targetIndex) {
			indicateAttemptToExecuteIncorrectCommand();
			return new CommandResult(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
		}

		ReadOnlyEntry entryToDelete = lastShownList.get(targetIndex - 1);

		try {
			model.deleteEntry(entryToDelete);
		} catch (EntryNotFoundException pnfe) {
			assert false : "The target entry cannot be missing";
		}

		try {
			model.addEntry(toMarkCompleted);
			return new CommandResult(String.format(MESSAGE_SUCCESS, toMarkCompleted));
		} catch (UniqueEntryList.DuplicateEntryException e) {
			return new CommandResult(MESSAGE_DUPLICATE_ENTRY);
		}

	}
}
