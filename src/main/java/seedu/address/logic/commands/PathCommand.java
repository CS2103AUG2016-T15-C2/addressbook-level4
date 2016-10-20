package seedu.address.logic.commands;

import java.io.IOException;

import seedu.address.commons.core.Config;
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
            + "Example: " + COMMAND_WORD +  " or "+ COMMAND_WORD2 + " dropbox.xml";
    
    public static final String MESSAGE_SUCCESS = "File Path to save changed to: ";
    public static final String MESSAGE_FAIL = "Failed to save File Path: ";
    
    String filePath;
    
    public PathCommand(String filePath) {
    	this.filePath = filePath;
    }

	@Override
	public CommandResult execute() {
		Config config = new Config();
		String configFilePathUsed = Config.DEFAULT_CONFIG_FILE;
				
		try {
			config.setSchedulerFilePath(filePath);
			ConfigUtil.saveConfig(config, configFilePathUsed);
			return new CommandResult(String.format(MESSAGE_SUCCESS + filePath + "\nUpdated. \n"));
		} catch (IOException e) {
			return new CommandResult(String.format(MESSAGE_FAIL + StringUtil.getDetails(e)));
		}
	}

}
