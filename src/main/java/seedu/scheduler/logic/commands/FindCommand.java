package seedu.scheduler.logic.commands;

import java.util.Set;

import com.sun.media.jfxmedia.logging.Logger;

import seedu.scheduler.commons.exceptions.IllegalValueException;

/**
 * Finds and lists all entrys in scheduler whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_WORD2 = "f";
    //@@author A0161210A
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all entrys whose names contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Can also be used to find completed and incompleted task.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " meeting\n"
            + "Example: " + COMMAND_WORD + " completed";

    private final Set<String> keywords;
    public boolean isCompleteTracker = false;
    public boolean isIncompleteTracker = false;
    /**
     * 
     * @param keywords  set of keywords to filter by
     * @throws IllegalValueException if given endDate string is invalid.
     */
    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
        this.isCompleteTracker = (keywords.contains("completed")) |(keywords.contains("c")) | (keywords.contains("complete"));
        Object[] keywordArray = keywords.toArray();
        this.isIncompleteTracker = (keywordArray[0].equals("i")|keywordArray[0].equals("incompleted") 
                |keywordArray[0].equals("incomplete")) 
                && !(keywords.contains("completed"));
    }
    //@@author
    @Override
    public CommandResult execute() {
        model.updateFilteredEntryList(keywords,this.isCompleteTracker,this.isIncompleteTracker);
        return new CommandResult(getMessageForEntryListShownSummary(model.getFilteredEntryList().size()));
    }
}
