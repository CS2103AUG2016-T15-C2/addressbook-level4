package seedu.scheduler.logic.commands;

import seedu.scheduler.commons.core.EventsCenter;
import seedu.scheduler.commons.events.ui.ExitAppRequestEvent;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String COMMAND_WORD2 = "ex";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Date Book as requested ...";

    public ExitCommand() {}

    @Override
    public CommandResult execute() {
        EventsCenter.getInstance().post(new ExitAppRequestEvent());
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

}
