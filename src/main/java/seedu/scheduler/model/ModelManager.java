package seedu.scheduler.model;

import javafx.collections.transformation.FilteredList;
import seedu.scheduler.commons.core.LogsCenter;
import seedu.scheduler.commons.core.UnmodifiableObservableList;
import seedu.scheduler.commons.util.StringUtil;
import seedu.scheduler.commons.events.model.SchedulerChangedEvent;
import seedu.scheduler.commons.core.ComponentManager;
import seedu.scheduler.model.entry.Entry;
import seedu.scheduler.model.entry.ReadOnlyEntry;
import seedu.scheduler.model.entry.UniqueEntryList;
import seedu.scheduler.model.entry.UniqueEntryList.EntryNotFoundException;

import java.util.Set;
import java.util.logging.Logger;

/**
 * Represents the in-memory model of the scheduler data.
 * All changes to any model should be synchronized.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Scheduler scheduler;
    private final FilteredList<Entry> filteredEntrys;

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

    //=========== Filtered Entry List Accessors ===============================================================

    @Override
    public UnmodifiableObservableList<ReadOnlyEntry> getFilteredEntryList() {
        return new UnmodifiableObservableList<>(filteredEntrys);
    }

    @Override
    public void updateFilteredListToShowAll() {
        filteredEntrys.setPredicate(null);
    }

    @Override
    public void updateFilteredEntryList(Set<String> keywords){
        updateFilteredEntryList(new PredicateExpression(new NameQualifier(keywords)));
    }

    private void updateFilteredEntryList(Expression expression) {
        filteredEntrys.setPredicate(expression::satisfies);
    }

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

}