package seedu.scheduler.logic.commands;

import seedu.scheduler.commons.exceptions.IllegalValueException;
import seedu.scheduler.model.entry.*;
import seedu.scheduler.model.entry.UniqueEntryList.DuplicateEntryException;
import seedu.scheduler.model.entry.UniqueEntryList.EntryNotFoundException;
import seedu.scheduler.model.tag.Tag;
import seedu.scheduler.model.tag.UniqueTagList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Adds a entry to the scheduler.
 */
public class AddCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_WORD2 = "a";
    //@@author A0161210A
    public static final String MESSAGE_USAGE = COMMAND_WORD + " or "+ COMMAND_WORD2 + ": Adds an entry to the scheduler. "
            + "Parameters: NAME st/START_TIME et/END_TIME sd/START_DATE ed/END_DATE  [t/TAG]...\n"
            + "Example: " + COMMAND_WORD

            + " John Wedding st/14:00 et/21:00 sd/12-10-2016 ed/12-11-2016 t/done or t/undone";
    //@@author
    public static final String MESSAGE_SUCCESS = "New entry added: %1$s";
    public static final String MESSAGE_DUPLICATE_ENTRY = "This entry already exists in the scheduler";

    private Entry toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException
     *             if any of the raw values are invalid
     */
    public AddCommand(String name, String startTime, String endTime, String date, String endDate, Set<String> tags)
            throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        //@@author A0126090N
        if (date == null && (startTime != null || endTime != null)) {
        	date = getCurrentDate();
        }
        //@@author
        this.toAdd = new Entry(new Name(name), new StartTime(startTime), new EndTime(endTime), new Date(date), new EndDate(endDate),
                new UniqueTagList(tagSet));
    }
    //@@author A0126090N
    public String getCurrentTime() {
    	DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    	Calendar cal = Calendar.getInstance();
    	return dateFormat.format(cal.getTime());
    }
    
    public String getCurrentDate() {
    	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	Calendar cal = Calendar.getInstance();
    	return dateFormat.format(cal.getTime());
    }
    //@@author
    @Override
    public CommandResult execute() {
        assert model != null;
        try {
            model.addEntry(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueEntryList.DuplicateEntryException e) {
            return new CommandResult(MESSAGE_DUPLICATE_ENTRY);
        }

    }

    //@@author A0152962B
    @Override
    public void undo() throws EntryNotFoundException {
        model.deleteEntry(toAdd);
    }

    @Override
    public void redo() throws DuplicateEntryException {
        model.addEntry(toAdd);
    }
    //@@author

}
