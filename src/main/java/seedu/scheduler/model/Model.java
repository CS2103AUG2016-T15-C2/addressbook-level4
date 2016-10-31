package seedu.scheduler.model;

import seedu.scheduler.commons.core.UnmodifiableObservableList;
import seedu.scheduler.model.entry.Entry;
import seedu.scheduler.model.entry.ReadOnlyEntry;
import seedu.scheduler.model.entry.UniqueEntryList;

import java.util.Set;

/**
 * The API of the Model component.
 */
public interface Model {
    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyScheduler newData);

    /** Returns the Scheduler */
    ReadOnlyScheduler getScheduler();

    /** Deletes the given entry. */
    void deleteEntry(ReadOnlyEntry target) throws UniqueEntryList.EntryNotFoundException;

    /** Adds the given entry */
    void addEntry(Entry entry) throws UniqueEntryList.DuplicateEntryException;
    
    //@@author A0152962B
    /** Edits the given entry */
    void editEntry(int index, Entry replacement, ReadOnlyEntry entryToEdit) 
            throws UniqueEntryList.DuplicateEntryException, UniqueEntryList.EntryNotFoundException;
    //@@author
    
    /** Returns the filtered entry list as an {@code UnmodifiableObservableList<ReadOnlyEntry>} */
    UnmodifiableObservableList<ReadOnlyEntry> getFilteredEntryList();

    /** Updates the filter of the filtered entry list to show all entrys */
    void updateFilteredListToShowAll();

    /** Updates the filter of the filtered entry list to filter by the given keywords*/
    void updateFilteredEntryList(Set<String> keywords, boolean completeTracker, boolean isIncompleteTracker);


}
