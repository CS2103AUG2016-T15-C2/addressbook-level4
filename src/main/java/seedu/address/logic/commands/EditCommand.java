package seedu.address.logic.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entry.*;
import seedu.address.model.entry.UniqueEntryList.DuplicateEntryException;
import seedu.address.model.entry.UniqueEntryList.EntryNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Edits an entry in the scheduler
 */
public class EditCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "edit";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an entry in the scheduler. "
            + "Parameters: INDEX NAME st/START_TIME et/END_TIME d/DATE [t/TAG]...\n"
            + "Example: " + COMMAND_WORD
            + " 2 John Wedding st/15:00 et/21:00 d/12-10-2016 t/undone";
    
    public static final String MESSAGE_SUCCESS = "Entry editted: %1$s";
    public static final String MESSAGE_DUPLICATE_ENTRY = "This entry already exists in the scheduler";
    
    public final int targetIndex;
    public final Entry toAdd;
    public Entry prevEntry;
    public Entry currEntry;
    
    public EditCommand(int targetIndex, String name, String startTime, String endTime, String date, Set<String> tags)
            throws IllegalValueException {
        this.targetIndex = targetIndex;
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Entry(
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
        prevEntry = (Entry) entryToDelete;
        currEntry = toAdd;
        try {
            model.addEntry(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueEntryList.DuplicateEntryException e) {
            return new CommandResult(MESSAGE_DUPLICATE_ENTRY);
        }
        

    }

    @Override
    public void undo() throws EntryNotFoundException, DuplicateEntryException {
        model.deleteEntry(currEntry);
        model.addEntry(prevEntry);
    }

}
