package seedu.address.logic.commands;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entry.*;
import seedu.address.model.entry.UniqueEntryList.DuplicateEntryException;
import seedu.address.model.entry.UniqueEntryList.EntryNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

import java.util.HashSet;
import java.util.Set;

/**
 * Adds a entry to the scheduler.
 */
public class AddCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "add";
<<<<<<< HEAD

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an entry to the scheduler. "
            + "Parameters: NAME st/START_TIME et/END_TIME d/DATE  [t/TAG]...\n" + "Example: " + COMMAND_WORD
=======
    public static final String COMMAND_WORD2 = "a";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " or "+ COMMAND_WORD2 + ": Adds an entry to the scheduler. "
            + "Parameters: NAME st/START_TIME et/END_TIME d/DATE  [t/TAG]...\n"
            + "Example: " + COMMAND_WORD
>>>>>>> 852b51e3a4a870310c499488ba6e2978bcf7a49c
            + " John Wedding st/14:00 et/21:00 d/12-10-2016 t/done or t/undone";

    public static final String MESSAGE_SUCCESS = "New entry added: %1$s";
    public static final String MESSAGE_DUPLICATE_ENTRY = "This entry already exists in the scheduler";

    private Entry toAdd;
    private Entry prevEntry;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException
     *             if any of the raw values are invalid
     */
    public AddCommand(String name, String startTime, String endTime, String date, Set<String> tags)
            throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Entry(new Name(name), new StartTime(startTime), new EndTime(endTime), new Date(date),
                new UniqueTagList(tagSet));
        prevEntry = this.toAdd;
    }

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

    @Override
    public void undo() throws EntryNotFoundException {
        model.deleteEntry(prevEntry);
    }

    @Override
    public void redo() throws DuplicateEntryException {
        model.addEntry(prevEntry);
    }

}
