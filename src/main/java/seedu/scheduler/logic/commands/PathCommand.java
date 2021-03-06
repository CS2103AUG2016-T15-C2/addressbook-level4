//@@author A0139956L
package seedu.scheduler.logic.commands;

import seedu.scheduler.commons.core.EventsCenter;
import seedu.scheduler.commons.events.storage.FilePathChangeEvent;

/**
 * Manually change a file path to a user-specified location.
 */
public class PathCommand extends Command {
	
    public static final String COMMAND_WORD = "path";
    public static final String COMMAND_WORD2 = "p";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " or "+ COMMAND_WORD2 + ": Change a specific file path to save. "
            + "Parameters: FILE_PATH\n"
            + "Example: " + COMMAND_WORD +  " or "+ COMMAND_WORD2 + " dropbox";
    
    public static final String MESSAGE_SUCCESS = "File Path to save changed to: ";
    
    String filePath;
    
    public PathCommand(String filePath) {
    	this.filePath = filePath;
    }

	@Override
	public CommandResult execute() {
		EventsCenter.getInstance().post(new FilePathChangeEvent(filePath));
		return new CommandResult(MESSAGE_SUCCESS + filePath);
	}
}
