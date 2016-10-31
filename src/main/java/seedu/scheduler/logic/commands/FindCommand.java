package seedu.scheduler.logic.commands;

import java.util.Set;

import com.sun.media.jfxmedia.logging.Logger;

/**
 * Finds and lists all entrys in scheduler whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_WORD2 = "f";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all entrys whose names contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " meeting";

    private final Set<String> keywords;
    public boolean isCompleteTracker = false;
    public boolean isIncompleteTracker = false;
    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
        this.isCompleteTracker = (keywords.contains("completed"));
        Object[] keywordArray = keywords.toArray();
        this.isIncompleteTracker = keywordArray[0].equals("incompleted") && !(keywords.contains("completed"));
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredEntryList(keywords,this.isCompleteTracker,this.isIncompleteTracker);
        return new CommandResult(getMessageForEntryListShownSummary(model.getFilteredEntryList().size()));
    }

}
