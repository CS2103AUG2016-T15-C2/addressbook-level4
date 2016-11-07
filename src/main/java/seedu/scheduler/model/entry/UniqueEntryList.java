package seedu.scheduler.model.entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.scheduler.commons.util.CollectionUtil;
import seedu.scheduler.model.tag.UniqueTagList;
import seedu.scheduler.commons.exceptions.DuplicateDataException;

import java.util.*;

/**
 * A list of entries that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Entry#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueEntryList implements Iterable<Entry> {

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateEntryException extends DuplicateDataException {
        protected DuplicateEntryException() {
            super("Operation would result in duplicate entrys");
        }
    }

    /**
     * Signals that an operation targeting a specified entry in the list would fail because
     * there is no such matching entry in the list.
     */
    public static class EntryNotFoundException extends Exception {}

    private final ObservableList<Entry> internalList = FXCollections.observableArrayList();

    /**
     * Constructs empty EntryList.
     */
    public UniqueEntryList() {}

    /**
     * Returns true if the list contains an equivalent entry as the given argument.
     */
    public boolean contains(ReadOnlyEntry toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }

    /**
     * Adds a entry to the list.
     *
     * @throws DuplicateEntryException if the entry to add is a duplicate of an existing entry in the list.
     */
    public void add(Entry toAdd) throws DuplicateEntryException {
        assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicateEntryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent entry from the list.
     *
     * @throws EntryNotFoundException if no such entry could be found in the list.
     */
    public boolean remove(ReadOnlyEntry toRemove) throws EntryNotFoundException {
        assert toRemove != null;
        final boolean entryFoundAndDeleted = internalList.remove(toRemove);
        if (!entryFoundAndDeleted) {
            throw new EntryNotFoundException();
        }
        return entryFoundAndDeleted;
    }
    
    //@@author A0152962B
    /**
     * Adds an entry into the list at a given index. Shifts all entrys after the index in the list down by one.
     * 
     * @param index  Index of the entry to add.
     * @param toAdd  Entry to add at the index of the list.
     * @throws DuplicateEntryException if the entry to add is a duplicate of an existing entry in the list.
     */
    public void addAtIndex(int index, Entry toAdd) throws DuplicateEntryException {
        assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicateEntryException();
        }
        internalList.add(index - 1, toAdd);
    }
    
    /**
     * Edits(replaces) the specified entry.
     * 
     * @param replacement  The entry to replace the to-be-edited entry with.
     * @param toEdit  The target entry to be replaced with.
     * @throws EntryNotFoundException if no such entry could be found in the list.
     * @throws DuplicateEntryException if the entry to replace is a duplicate of an existing entry in the list.
     */
    public void edit(Entry replacement, ReadOnlyEntry toEdit) throws EntryNotFoundException, DuplicateEntryException {
        assert toEdit != null;
        final boolean entryFound = internalList.contains(toEdit);
        int index = internalList.indexOf(toEdit);
        
        if(!entryFound) {
            throw new EntryNotFoundException();
        }
        if(contains(replacement)) {
            throw new DuplicateEntryException();
        }
        internalList.set(index, replacement);
    }

    //@@author A0152962B-unused
    /**
     * Updates the specified entry with given fields if not empty.
     * 
     * @param startTime  New start time to update to.
     * @param endTime  New end time to update to.
     * @param date  New start date to update to.
     * @param endDate  New end date to update to.
     * @param tagList  New tags to add on to.
     * @param toUpdate  The entry that is required to update.
     * @throws EntryNotFoundException if no such entry could be found in the list.
     */
    /*public void update(StartTime startTime, EndTime endTime, Date date, EndDate endDate, UniqueTagList tagList, ReadOnlyEntry toUpdate) 
            throws EntryNotFoundException {
        assert toUpdate != null;
        final boolean entryFound = internalList.contains(toUpdate);
        if(!entryFound) {
            throw new EntryNotFoundException();
        }
        int index = internalList.indexOf(toUpdate);
        //if(!update.getName().fullName.equals(null) 
        //        && !update.getName().fullName.equals(toUpdate.getName().fullName)) {
        //    internalList.get(index).setName(update.getName());
        //}
        
        if(!startTime.value.equals("empty")
                && !startTime.equals(toUpdate.getStartTime())) {
            internalList.get(index).setStartTime(startTime);
        }
        if(!endTime.value.equals("empty")
                && !endTime.equals(toUpdate.getEndTime())) {
            internalList.get(index).setEndTime(endTime);
        }
        if(!date.value.equals("empty")
                && !date.equals(toUpdate.getDate())) {
            internalList.get(index).setDate(date);
        }
        if(!endDate.value.equals("empty")
                && !endDate.equals(toUpdate.getEndDate())) {
            internalList.get(index).setEndDate(endDate);
        }
        if(!tagList.equals(null)
                && !tagList.equals(toUpdate.getTags())) {
            internalList.get(index).updateTags(tagList);
        }
    }
    */
    //@@author

    public ObservableList<Entry> getInternalList() {
        return internalList;
    }

    @Override
    public Iterator<Entry> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEntryList // instanceof handles nulls
                && this.internalList.equals(
                ((UniqueEntryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
