package seedu.address.logic.commands;

import seedu.address.model.AddressBook;

/**
 * Clears the scheduler.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Scheduler has been cleared!";

    public ClearCommand() {}


    @Override
    public CommandResult execute() {
        assert model != null;
        model.resetData(AddressBook.getEmptyAddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
