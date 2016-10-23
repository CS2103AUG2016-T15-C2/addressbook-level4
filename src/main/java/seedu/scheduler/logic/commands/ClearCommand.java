package seedu.scheduler.logic.commands;

import seedu.scheduler.model.Scheduler;

/**
 * Clears the scheduler.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_WORD2 = "c";
    public static final String MESSAGE_SUCCESS = "Scheduler has been cleared!";

    public ClearCommand() {}


    @Override
    public CommandResult execute() {
        assert model != null;
        model.resetData(Scheduler.getEmptyScheduler());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
