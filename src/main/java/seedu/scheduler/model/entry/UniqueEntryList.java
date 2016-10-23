package seedu.scheduler.model.entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.scheduler.commons.util.CollectionUtil;
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
