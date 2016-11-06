package seedu.scheduler.logic.commands;

import java.util.Set;
import static seedu.scheduler.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Lists all entries in the scheduler to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_WORD2 = "l";

    public static final String MESSAGE_SUCCESS = "Listed all entrys";
    public static final String MESSAGE_SUCCESS_DATE = "Listed all entries by date and time earliest endDate first";
    public static final String MESSAGE_SUCCESS_DATE_DESCREASE = "Listed all tasks by latest endDate first";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "or" + COMMAND_WORD2 
    		+ ": Lists all entries with the specified keyword and display them as a list with index numbers.\n"
    		+ "Parameters: [KEYWORD]"
    		+ "Example: " + COMMAND_WORD + "sort";
    
    private static final String LIST_ARG_SORT = "sort";
    private static final String LIST_KEYWORD_DEC = "dc";
    
    private Set<String> keyword;
    
    public ListCommand() {}
    
    public ListCommand(Set<String> arguments) {
    	this.keyword = arguments;
    }

    @Override
    public CommandResult execute() {
    	System.out.println(keyword);
    	if(!keyword.isEmpty() && keyword != null) {
    		if(keyword.contains(LIST_ARG_SORT) || keyword.contains(LIST_KEYWORD_DEC)) {
    			model.sortFilteredEntryList(keyword);
    			if(keyword.contains(LIST_KEYWORD_DEC)) {
    				if(keyword.contains(LIST_ARG_SORT))
    					return new CommandResult(MESSAGE_SUCCESS_DATE_DESCREASE);
    			}
    			else {
    				if(keyword.contains(LIST_ARG_SORT))
    					return new CommandResult(MESSAGE_SUCCESS_DATE);
    			}
    		}
    		else {
    			model.updateFilteredListToShowAll();
    			return new CommandResult(MESSAGE_INVALID_COMMAND_FORMAT + MESSAGE_USAGE);
    		}
    	}
    	else {
    		model.updateFilteredListToShowAll();
    	}
    	return new CommandResult(MESSAGE_SUCCESS);
    }
}
