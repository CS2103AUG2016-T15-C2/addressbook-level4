package seedu.address.logic.commands;

import java.io.IOException;

import seedu.address.commons.core.Config;
import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.storage.FilePathChangeEvent;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;

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
