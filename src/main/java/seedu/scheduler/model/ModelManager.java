package seedu.scheduler.model;

import javafx.collections.transformation.FilteredList;
import seedu.scheduler.commons.core.LogsCenter;
import seedu.scheduler.commons.core.UnmodifiableObservableList;
import seedu.scheduler.commons.util.StringUtil;
import seedu.scheduler.commons.events.model.SchedulerChangedEvent;
import seedu.scheduler.commons.exceptions.IllegalValueException;
import seedu.scheduler.commons.core.ComponentManager;
import seedu.scheduler.model.entry.Date;
import seedu.scheduler.model.entry.EndDate;
import seedu.scheduler.model.entry.EndTime;
import seedu.scheduler.model.entry.Entry;
import seedu.scheduler.model.entry.ReadOnlyEntry;
import seedu.scheduler.model.entry.StartTime;
import seedu.scheduler.model.entry.UniqueEntryList;
import seedu.scheduler.model.entry.UniqueEntryList.DuplicateEntryException;
import seedu.scheduler.model.entry.UniqueEntryList.EntryNotFoundException;
import seedu.scheduler.model.tag.UniqueTagList;

import java.util.Set;
import java.util.logging.Logger;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents the in-memory model of the scheduler data.
 * All changes to any model should be synchronized.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Scheduler scheduler;
    private final FilteredList<Entry> filteredEntrys;
    
    private static final String LIST_ARG_SORT = "sort";

    /**
     * Initializes a ModelManager with the given Scheduler
     * Scheduler and its variables should not be null
     */
    public ModelManager(Scheduler src, UserPrefs userPrefs) {
        super();
        assert src != null;
        assert userPrefs != null;

        logger.fine("Initializing with scheduler: " + src + " and user prefs " + userPrefs);

        scheduler = new Scheduler(src);
        filteredEntrys = new FilteredList<>(scheduler.getEntrys());
    }

    public ModelManager() {
        this(new Scheduler(), new UserPrefs());
    }

    public ModelManager(ReadOnlyScheduler initialData, UserPrefs userPrefs) {
        scheduler = new Scheduler(initialData);
        filteredEntrys = new FilteredList<>(scheduler.getEntrys());
    }

    @Override
    public void resetData(ReadOnlyScheduler newData) {
        scheduler.resetData(newData);
        indicateSchedulerChanged();
    }

    @Override
    public ReadOnlyScheduler getScheduler() {
        return scheduler;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateSchedulerChanged() {
        raise(new SchedulerChangedEvent(scheduler));
    }

    @Override
    public synchronized void deleteEntry(ReadOnlyEntry target) throws EntryNotFoundException {
        scheduler.removeEntry(target);
        indicateSchedulerChanged();
    }

    @Override
    public synchronized void addEntry(Entry entry) throws UniqueEntryList.DuplicateEntryException {
        scheduler.addEntry(entry);
        updateFilteredListToShowAll();
        indicateSchedulerChanged();
    }
    
    //@@author A0152962B
    public synchronized void addEntryAtIndex(int index, Entry entry) throws UniqueEntryList.DuplicateEntryException {
        scheduler.addEntryAtIndex(index, entry);
        //updateFilteredListToShowAll();
        indicateSchedulerChanged();
    }
    
    @Override
    public synchronized void editEntry(Entry replacement, ReadOnlyEntry toEdit) 
            throws UniqueEntryList.DuplicateEntryException, UniqueEntryList.EntryNotFoundException{
        scheduler.editEntry(replacement, toEdit);
        indicateSchedulerChanged();
    }
    
    //@@author A0152962B-unused
    /*@Override
    public synchronized void updateEntry(StartTime startTime, EndTime endTime, Date date, EndDate endDate, UniqueTagList tagList, ReadOnlyEntry toUpdate)
            throws UniqueEntryList.EntryNotFoundException{
        scheduler.updateEntry(startTime, endTime, date, endDate, tagList, toUpdate);
        indicateSchedulerChanged();
    }
    */
    //@@author

    //=========== Filtered Entry List Accessors ===============================================================

    @Override
    public UnmodifiableObservableList<ReadOnlyEntry> getFilteredEntryList() {
        return new UnmodifiableObservableList<>(filteredEntrys);
    }

    @Override
    public void updateFilteredListToShowAll() {
        filteredEntrys.setPredicate(null);
    }
    
    //@@author A0161210A
    @Override
    /**
     * Updates the entry list by the given filter
     * @param keywords  Keywords to filter by
     * @param completeTracker  Indicates whether to filter out for complete task or not
     * @param incompleteTracker Indicates whether to filter out for incomplete task or not
     * @throws IllegalValueException if given endDate string is invalid.
     */
    public void updateFilteredEntryList(Set<String> keywords, boolean completeTracker,boolean incompleteTracker){
        if (completeTracker) {
            updateFilteredEntryList(new PredicateExpression(new TagQualifier(keywords)));
        }
        else if(incompleteTracker) {
            updateFilteredEntryList(new PredicateExpression(new IncompleteTagQualifier(keywords)));
        }
        else {
            updateFilteredEntryList(new PredicateExpression(new NameQualifier(keywords)));
        }

    }
    //@@author

    private void updateFilteredEntryList(Expression expression) {
        filteredEntrys.setPredicate(expression::satisfies);
    }
    
    //@@author A0139956L-unused
    //unused: Not enough time to do DateQualifier  
/*	@Override
    public void sortFilteredEntryList(String keyword) {
		if(keyword.contains(LIST_ARG_SORT)) {
				scheduler.sortByDateTime();
		}	
		indicateSchedulerChanged();
	}
*/
    //@@author
    //========== Inner classes/interfaces used for filtering ==================================================

    interface Expression {
        boolean satisfies(ReadOnlyEntry entry);
        String toString();
    }

    private class PredicateExpression implements Expression {

        private final Qualifier qualifier;

        PredicateExpression(Qualifier qualifier) {
            this.qualifier = qualifier;
        }

        @Override
        public boolean satisfies(ReadOnlyEntry entry) {
            return qualifier.run(entry);
        }

        @Override
        public String toString() {
            return qualifier.toString();
        }
    }

    interface Qualifier {
        boolean run(ReadOnlyEntry entry);
        String toString();
    }

    private class NameQualifier implements Qualifier {
        private Set<String> nameKeyWords;

        NameQualifier(Set<String> nameKeyWords) {
            this.nameKeyWords = nameKeyWords;
        }

        @Override
        public boolean run(ReadOnlyEntry entry) {
            return nameKeyWords.stream()
                    .filter(keyword -> StringUtil.containsIgnoreCase(entry.getName().fullName, keyword))
                    .findAny()
                    .isPresent();
        }

        @Override
        public String toString() {
            return "name=" + String.join(", ", nameKeyWords);
        }
    }
    
    //@@author A0161210A
    private class TagQualifier implements Qualifier {
        private Set<String> nameKeyWords;

        TagQualifier(Set<String> nameKeyWords) {
            this.nameKeyWords = nameKeyWords;
        }

        @Override
        public boolean run(ReadOnlyEntry entry) {
            return (entry.tagsString().contains("Completed")); 
        }

        @Override
        public String toString() {
            return "name=" + String.join(", ", nameKeyWords);
        }
    }
    
    private class IncompleteTagQualifier implements Qualifier {
        private Set<String> nameKeyWords;

        IncompleteTagQualifier(Set<String> nameKeyWords) {
            this.nameKeyWords = nameKeyWords;
        }

        @Override
        public boolean run(ReadOnlyEntry entry) {
            return (!entry.tagsString().contains("Completed")); 
        }

        @Override
        public String toString() {
            return "name=" + String.join(", ", nameKeyWords);
        }
    }

    //@@author A0139956L-unused
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
