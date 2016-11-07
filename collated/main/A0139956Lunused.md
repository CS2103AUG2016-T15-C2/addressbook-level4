# A0139956Lunused
###### \java\seedu\scheduler\logic\commands\ListCommand.java
``` java
    //unused: Not enough time to do DateQualifier  
    //public static final String COMMAND_WORD3 = "list sort";
    		
    public static final String MESSAGE_SUCCESS = "Listed all entrys";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " or " + COMMAND_WORD2 + ": List all entries.\n"
    		+ "No Parameters. " + "Example: " + COMMAND_WORD;
/*    public static final String MESSAGE_SUCCESS_SORT = "Listed all entries sorted by date; earliest endDate first";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "or" + COMMAND_WORD2 
    		+ ": Lists all entries with the specified keyword and display them as a list with index numbers.\n"
    		+ "Parameters: [KEYWORD]"
    		+ "Example: " + COMMAND_WORD + " sort";
    
    private static final String LIST_ARG_SORT = "sort";
    
    private String keyword;
*/    
    public ListCommand() {}
    
```
###### \java\seedu\scheduler\logic\commands\ListCommand.java
``` java
    //unused: Not enough time to do DateQualifier  
/*    public ListCommand(String arguments) {
    	this.keyword = arguments;
    }
*/
    @Override
    public CommandResult execute() {
    	//if(keyword.contains(LIST_ARG_SORT)) {
    		//return listByKeyword();
    	//}
    	//else {
    		model.updateFilteredListToShowAll();
    		return new CommandResult(MESSAGE_SUCCESS);
    	//}
    }
```
###### \java\seedu\scheduler\logic\commands\ListCommand.java
``` java
	//unused: Not enough time to do DateQualifier  	
/*    private CommandResult listByKeyword() {	
    	System.out.println(keyword);
   		model.sortFilteredEntryList(keyword);
   		return new CommandResult(MESSAGE_SUCCESS_SORT);
    } */
}
```
###### \java\seedu\scheduler\logic\parser\Parser.java
``` java
        	return prepareList(arguments);
```
###### \java\seedu\scheduler\logic\parser\Parser.java
``` java
            return prepareList(arguments);

```
###### \java\seedu\scheduler\logic\parser\Parser.java
``` java
    //unused: Not enough time to do DateQualifier  
/*    private Command prepareList(String args) {
    	if(args.contains("sort") || args.equals("")) {
    		return new ListCommand(args);
    	}
    	else {
    		return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    	}
    }
*/    
    private Command prepareList(String args) {
    	if(args.equals(""))
    		return new ListCommand();
    	else {
    		return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    	}    		
    }
    
    /**
     * Parses arguments in the context of the file path command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command preparePath(String args) {
        final Matcher matcher = PATH_DATA_ARGS_FORMAT.matcher(args.trim());
        //Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PathCommand.MESSAGE_USAGE));
        }
        else {
            String filePath = matcher.group("name").trim().replaceAll("/$", "") + ".xml";                   //store input to filePath
            return new PathCommand(filePath);       //push input to PathCommand
        }
    }
```
###### \java\seedu\scheduler\model\entry\EndDate.java
``` java
    //unused: Not enough time to do DateQualifier
//    private LocalDate endDate;
```
###### \java\seedu\scheduler\model\entry\EndDate.java
``` java
    //unused: Not enough time to do DateQualifier
//    @Override
//    public int compareTo(EndDate o) {
//    	return this.endDate.compareTo(o.endDate);
//    }
```
###### \java\seedu\scheduler\model\Model.java
``` java
    //unused: Not enough time to do DateQualifier  
    /** Sorts the filtered entry list by the given keyword */
	//void sortFilteredEntryList(String keyword);
```
###### \java\seedu\scheduler\model\ModelManager.java
``` java
    //unused: Not enough time to do DateQualifier  
/*	@Override
    public void sortFilteredEntryList(String keyword) {
		if(keyword.contains(LIST_ARG_SORT)) {
				scheduler.sortByDateTime();
		}	
		indicateSchedulerChanged();
	}
*/
```
###### \java\seedu\scheduler\model\ModelManager.java
``` java
    //unused: Not enough time to do DateQualifier  
/*    public class DateQualifier implements Qualifier {

        private final LocalDateTime startDate;
        private final LocalDateTime endDate;
        private final Entry dateTimeQuery;

        public DateQualifier(Entry dateTime) {
            if (dateTime.getEndDate() != null) {
                endDate = setLocalTime(dateTime.getEndDate());
            }
            else {
                endDate = setLocalTime(dateTime.getEndDate());
            }
    } */
}
```
###### \java\seedu\scheduler\model\Scheduler.java
``` java
    //unused: Not enough time to do DateQualifier 
    /** 
	 * Sorts internal list by earliest end date first
	 */
/*	public void sortByDateTime() {
		this.entrys.getInternalList().sort(new Comparator<Entry>() {
			@Override
			public int compare(Entry o1, Entry o2) {
				return o1.getEndDate().compareTo(o2.getEndDate());
			}
		});
	} */
```
