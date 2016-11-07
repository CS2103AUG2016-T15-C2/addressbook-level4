package seedu.scheduler.logic.commands;

import java.util.Set;

import static seedu.scheduler.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Lists all entries in the scheduler to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_WORD2 = "l";
    public static final String COMMAND_WORD3 = "list sort";
    		
    public static final String MESSAGE_SUCCESS = "Listed all entrys";
    public static final String MESSAGE_SUCCESS_SORT = "Listed all entries sorted by date; earliest endDate first";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "or" + COMMAND_WORD2 
    		+ ": Lists all entries with the specified keyword and display them as a list with index numbers.\n"
    		+ "Parameters: [KEYWORD]"
    		+ "Example: " + COMMAND_WORD + " sort";
    
    private static final String LIST_ARG_SORT = "sort";
    
    private String keyword;
    
    public ListCommand() {}
    
    public ListCommand(String arguments) {
    	this.keyword = arguments;
    }

    @Override
    public CommandResult execute() {
    	if(keyword.contains(LIST_ARG_SORT)) {
    		return listByKeyword();
    	}
    	else {
    		model.updateFilteredListToShowAll();
    		return new CommandResult(MESSAGE_SUCCESS);
    	}
    }
    	
    private CommandResult listByKeyword() {	
    	System.out.println(keyword);
   		model.sortFilteredEntryList(keyword);
   		return new CommandResult(MESSAGE_SUCCESS_SORT);
    }
}
