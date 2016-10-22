package guitests;

import org.junit.Test;
import seedu.address.logic.commands.PathCommand;
import seedu.address.commons.core.Config;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.ConfigUtilTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Optional;

public class PathCommandTest extends SchedulerGuiTest {

	@Test
	public void PathCommandTest() {
				
		Config origConfig = initConfig(Config.DEFAULT_CONFIG_FILE);
		String origPath = origConfig.getSchedulerFilePath().replace(".xml", "");
		String newPath = "testscheduler";
		
		//add a file path
		commandBox.runCommand("path " + newPath);
		assertResultMessage(String.format(PathCommand.MESSAGE_SUCCESS, newPath + ".xml"));
		
		origConfig = initConfig(Config.DEFAULT_CONFIG_FILE);
		String compareString = origConfig.getSchedulerFilePath();
		assertEquals(newPath, compareString.substring(0,compareString.length()-4));
		
		//add a file path
		commandBox.runCommand("path " + origPath);
		assertResultMessage(String.format(PathCommand.MESSAGE_SUCCESS, origPath + ".xml"));
		
		origConfig = initConfig(Config.DEFAULT_CONFIG_FILE);
		String compareString2 = origConfig.getSchedulerFilePath();
		assertEquals(origPath, compareString2.substring(0,compareString2.length()-4));
	}

	protected Config initConfig(String configFilePath) {
		
		Config initializedConfig;
		String configFilePathUsed;
		
		configFilePathUsed = Config.DEFAULT_CONFIG_FILE;
		
		if(configFilePath != null) {
			configFilePathUsed = configFilePath;
		}
		
		try {
			Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);	
			initializedConfig = configOptional.orElse(new Config());
		} catch (DataConversionException e) {
			initializedConfig = new Config();
		}
		
		 //Update config file in case it was missing to begin with or there are new/unused fields
		try {
			ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
		} catch (IOException e) {
		}
		
		return initializedConfig;
	}
}
