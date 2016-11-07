//package seedu.scheduler.logic.commands;

//import java.util.HashSet;
import java.util.Set;

import seedu.scheduler.commons.core.Messages;
import seedu.scheduler.commons.core.UnmodifiableObservableList;
import seedu.scheduler.commons.exceptions.IllegalValueException;
import seedu.scheduler.model.entry.*;
import seedu.scheduler.model.entry.UniqueEntryList.DuplicateEntryException;
import seedu.scheduler.model.entry.UniqueEntryList.EntryNotFoundException;
import seedu.scheduler.model.tag.Tag;
import seedu.scheduler.model.tag.UniqueTagList;

//@@author A1052962B-unused
/**
 * Updates an entry in the scheduler given an index and the fields to update.
 * Unused: update feature not fully implemented.
 */
/*public class UpdateCommand extends UndoableCommand{

    public static final String COMMAND_WORD = "update";
    public static final String COMMAND_WORD2 = "u";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + " or " + COMMAND_WORD2 + ": Updates an entry in the scheduler. "
            + "Parameters: INDEX st/START_TIME et/END_TIME d/DATE [t/TAG]...\n" + "Example: " + COMMAND_WORD
            + " 2 st/15:00 et/21:00 d/12-10-2016 t/undone";
    
    public final String MESSAGE_SUCCESS = "Entry updated to: %1$s";
    public static final String MESSAGE_ENTRY_MISSING = "The target entry cannot be missing";

    private final int targetIndex;
    private final StartTime startTime;
    private final EndTime endTime;
    private final Date date;
    private final EndDate endDate;
    private final UniqueTagList tagList;
    private Entry prevEntry;
    private Entry newEntry;
    
    public UpdateCommand(int targetIndex, String startTime, String endTime, String date, String endDate, Set<String> tags) 
            throws IllegalValueException{
        this.targetIndex = targetIndex;
        this.startTime = new StartTime(startTime);
        this.endTime = new EndTime(endTime);
        this.date = new Date(date);
        this.endDate = new EndDate(endDate);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        tagList = new UniqueTagList(tagSet);
    }
    
    @Override
    public CommandResult execute() {
        
        UnmodifiableObservableList<ReadOnlyEntry> lastShownList = model.getFilteredEntryList();

        if (lastShownList.size() < targetIndex) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }
        
        ReadOnlyEntry entryToUpdate = lastShownList.get(targetIndex - 1);
        
        try {
            prevEntry = new Entry(entryToUpdate);
            model.updateEntry(startTime, endTime, date, endDate, tagList, entryToUpdate);
            newEntry = (Entry) lastShownList.get(targetIndex - 1);
            undoManager.stackCommand(this);
            return new CommandResult(String.format(MESSAGE_SUCCESS, newEntry));
        } catch (UniqueEntryList.EntryNotFoundException e) {
            return new CommandResult(MESSAGE_ENTRY_MISSING);
        }
    }
    
    @Override
    public void undo() {
        try {
            model.editEntry(prevEntry, newEntry);
        } catch (EntryNotFoundException enfe) {
        } catch (DuplicateEntryException dee) { }
    }

    @Override
    public void redo() {
        try {
            model.editEntry(newEntry, prevEntry);
        } catch (EntryNotFoundException enfe) {
        } catch (DuplicateEntryException dee) { }
    }

    

}
*/