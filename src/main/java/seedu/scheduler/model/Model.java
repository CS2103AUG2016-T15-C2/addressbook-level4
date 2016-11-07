package seedu.scheduler.model;

import seedu.scheduler.commons.core.UnmodifiableObservableList;
import seedu.scheduler.model.entry.Entry;
import seedu.scheduler.model.entry.ReadOnlyEntry;
import seedu.scheduler.model.entry.*;
import seedu.scheduler.model.entry.UniqueEntryList;
import seedu.scheduler.model.tag.UniqueTagList;

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
    /** Adds the given entry at the index */
    void addEntryAtIndex(int targetIndex, Entry Entry) throws UniqueEntryList.DuplicateEntryException;
    
    /** Edits the given entry */
    void editEntry(Entry replacement, ReadOnlyEntry entryToEdit) 
            throws UniqueEntryList.DuplicateEntryException, UniqueEntryList.EntryNotFoundException;
    
    //@@author A0152962B-unused
    /** Updates the given entry */
    //Unused: update feature removed, no longer required
    /*void updateEntry(StartTime startTime, EndTime endTime, Date date, EndDate endDate, UniqueTagList tagList, ReadOnlyEntry entryToUpdate)
            throws UniqueEntryList.EntryNotFoundException;
    */
    //@@author
    
    /** Returns the filtered entry list as an {@code UnmodifiableObservableList<ReadOnlyEntry>} */
    UnmodifiableObservableList<ReadOnlyEntry> getFilteredEntryList();

    /** Updates the filter of the filtered entry list to show all entrys */
    void updateFilteredListToShowAll();
    //@@author A0161210A
    /** Updates the filter of the filtered entry list to filter by the given keywords*/
    void updateFilteredEntryList(Set<String> keywords, boolean completeTracker, boolean isIncompleteTracker);
    //@@author A0139956L-unused
    //unused: Not enough time to do DateQualifier  
    /** Sorts the filtered entry list by the given keyword */
	//void sortFilteredEntryList(String keyword);
    //@@author
}
