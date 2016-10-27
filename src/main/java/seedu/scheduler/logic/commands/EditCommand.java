package seedu.scheduler.logic.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.scheduler.commons.core.Messages;
import seedu.scheduler.commons.core.UnmodifiableObservableList;
import seedu.scheduler.commons.exceptions.IllegalValueException;
import seedu.scheduler.model.entry.*;
import seedu.scheduler.model.entry.UniqueEntryList.DuplicateEntryException;
import seedu.scheduler.model.entry.UniqueEntryList.EntryNotFoundException;
import seedu.scheduler.model.tag.Tag;
import seedu.scheduler.model.tag.UniqueTagList;

//@@author A0152962B
/**
 * Edits an entry in the scheduler given an index.
 */
public class EditCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "edit";
    public static final String COMMAND_WORD2 = "e";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an entry in the scheduler. "
            + "Parameters: INDEX NAME st/START_TIME et/END_TIME d/DATE [t/TAG]...\n" + "Example: " + COMMAND_WORD
            + " 2 John Wedding st/15:00 et/21:00 d/12-10-2016 t/undone";

    public static final String MESSAGE_SUCCESS = "Entry editted: %1$s";
    public static final String MESSAGE_DUPLICATE_ENTRY = "This entry already exists in the scheduler";
    public static final String MESSAGE_ENTRY_MISSING = "The target entry cannot be missing";
    
    private final int targetIndex;
    private final Entry replacement;
    private Entry prevEntry;
    
    public EditCommand(int targetIndex, String name, String startTime, String endTime, String date,String endDate, Set<String> tags)
            throws IllegalValueException {
        this.targetIndex = targetIndex;
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.replacement = new Entry(new Name(name), new StartTime(startTime), new EndTime(endTime), new Date(date), new EndDate(endDate),
                new UniqueTagList(tagSet));
    }

    @Override
    public CommandResult execute() {
        UnmodifiableObservableList<ReadOnlyEntry> lastShownList = model.getFilteredEntryList();

        if (lastShownList.size() < targetIndex) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }

        ReadOnlyEntry entryToEdit = lastShownList.get(targetIndex - 1);

        try {
            model.editEntry(targetIndex - 1, replacement, entryToEdit);
            prevEntry = (Entry) entryToEdit;
            return new CommandResult(String.format(MESSAGE_SUCCESS, replacement));
        } catch (UniqueEntryList.DuplicateEntryException dee) {
            return new CommandResult(MESSAGE_DUPLICATE_ENTRY);
        } catch (UniqueEntryList.EntryNotFoundException enfe) {
            return new CommandResult(MESSAGE_ENTRY_MISSING);
        }
    }

    @Override
    public void undo() throws EntryNotFoundException, DuplicateEntryException {
        model.editEntry(targetIndex - 1, prevEntry, replacement);
    }

    @Override
    public void redo() throws Exception {
        model.editEntry(targetIndex - 1, replacement, prevEntry);
    }

}
