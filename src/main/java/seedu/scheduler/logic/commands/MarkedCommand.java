//@@author A0126090N
package seedu.scheduler.logic.commands;

import seedu.scheduler.commons.core.Messages;
import seedu.scheduler.commons.core.UnmodifiableObservableList;
import seedu.scheduler.commons.exceptions.IllegalValueException;
import seedu.scheduler.model.entry.*;
import seedu.scheduler.model.entry.UniqueEntryList.DuplicateEntryException;
import seedu.scheduler.model.entry.UniqueEntryList.EntryNotFoundException;
import seedu.scheduler.model.tag.Tag;
import seedu.scheduler.model.tag.UniqueTagList;
import seedu.scheduler.model.tag.UniqueTagList.DuplicateTagException;

/**
 * Marks an entry identified using it's last displayed index from the
 * scheduler as completed.
 */
public class MarkedCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "mark";
    public static final String COMMAND_WORD2 = "m";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the entry identified by the index number used in the last entry listing as completed.\n"
            + "Parameters: INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARKED_ENTRY_SUCCESS = "Marked Entry: %1$s";

    public final int targetIndex;
    public Entry entryToMark;
    public Entry prevEntry;

    public MarkedCommand(int targetIndex) {
        this.targetIndex = targetIndex;
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
        	UniqueTagList tagSet = entryToDelete.getTags();
        	tagSet.add(new Tag("Completed"));
        	entryToMark = new Entry(entryToDelete.getName(),
        							entryToDelete.getStartTime(),
        							entryToDelete.getEndTime(),
        							entryToDelete.getDate(),
        							entryToDelete.getEndDate(),
        							tagSet
        	);        			
        	prevEntry = (Entry) entryToDelete;
        	model.deleteEntry(entryToDelete);
        	model.addEntry(entryToMark);
        } catch (DuplicateTagException e) {
        	e.getMessage();
		} catch (IllegalValueException e) {
			e.getMessage();
		} catch (EntryNotFoundException e) {
			e.getMessage();
		}
        undoManager.stackCommand(this);
        return new CommandResult(String.format(MESSAGE_MARKED_ENTRY_SUCCESS, entryToMark));   
    }
    

    @Override
    public void undo() {
        try {
            model.addEntryAtIndex(targetIndex, prevEntry);
        } catch (DuplicateEntryException e) { }
    }

    @Override
    public void redo() {
        try {
            model.deleteEntry(prevEntry);
        } catch (EntryNotFoundException e) { }
    }
}
