package seedu.scheduler.model;

import javafx.collections.ObservableList;
import seedu.scheduler.model.entry.*;
import seedu.scheduler.model.entry.Date;
import seedu.scheduler.model.tag.Tag;
import seedu.scheduler.model.tag.UniqueTagList;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Wraps all data at the scheduler level
 * Duplicates are not allowed (by .equals comparison)
 */
public class Scheduler implements ReadOnlyScheduler {

    private final UniqueEntryList entrys;
    private final UniqueTagList tags;

    {
        entrys = new UniqueEntryList();
        tags = new UniqueTagList();
    }

    public Scheduler() {}

    /**
     * Entrys and Tags are copied into this scheduler
     */
    public Scheduler(ReadOnlyScheduler toBeCopied) {
        this(toBeCopied.getUniqueEntryList(), toBeCopied.getUniqueTagList());
    }

    /**
     * Entrys and Tags are copied into this scheduler
     */
    public Scheduler(UniqueEntryList entrys, UniqueTagList tags) {
        resetData(entrys.getInternalList(), tags.getInternalList());
    }

    public static ReadOnlyScheduler getEmptyScheduler() {
        return new Scheduler();
    }

//// list overwrite operations

    public ObservableList<Entry> getEntrys() {
        return entrys.getInternalList();
    }

    public void setEntrys(List<Entry> entrys) {
        this.entrys.getInternalList().setAll(entrys);
    }

    public void setTags(Collection<Tag> tags) {
        this.tags.getInternalList().setAll(tags);
    }

    public void resetData(Collection<? extends ReadOnlyEntry> newEntrys, Collection<Tag> newTags) {
        setEntrys(newEntrys.stream().map(Entry::new).collect(Collectors.toList()));
        setTags(newTags);
    }

    public void resetData(ReadOnlyScheduler newData) {
        resetData(newData.getEntryList(), newData.getTagList());
    }

//// entry-level operations

    /**
     * Adds a entry to the scheduler.
     * Also checks the new entry's tags and updates {@link #tags} with any new tags found,
     * and updates the Tag objects in the entry to point to those in {@link #tags}.
     *
     * @throws UniqueEntryList.DuplicateEntryException if an equivalent entry already exists.
     */
    public void addEntry(Entry e) throws UniqueEntryList.DuplicateEntryException {
        syncTagsWithMasterList(e);
        entrys.add(e);
    }

    /**
     * Ensures that every tag in this entry:
     *  - exists in the master list {@link #tags}
     *  - points to a Tag object in the master list
     */
    private void syncTagsWithMasterList(Entry entry) {
        final UniqueTagList entryTags = entry.getTags();
        tags.mergeFrom(entryTags);

        // Create map with values = tag object references in the master list
        final Map<Tag, Tag> masterTagObjects = new HashMap<>();
        for (Tag tag : tags) {
            masterTagObjects.put(tag, tag);
        }

        // Rebuild the list of entry tags using references from the master list
        final Set<Tag> commonTagReferences = new HashSet<>();
        for (Tag tag : entryTags) {
            commonTagReferences.add(masterTagObjects.get(tag));
        }
        entry.setTags(new UniqueTagList(commonTagReferences));
    }

    public boolean removeEntry(ReadOnlyEntry key) throws UniqueEntryList.EntryNotFoundException {
        if (entrys.remove(key)) {
            return true;
        } else {
            throw new UniqueEntryList.EntryNotFoundException();
        }
    }
    
    //@@author A0152962B
    /**
     * Adds an entry at a specified index of the list.
     *
     * @throws UniqueEntryList.DuplicateEntryException if an equivalent entry already exists.
     */
    public void addEntryAtIndex(int index, Entry entry) throws UniqueEntryList.DuplicateEntryException {
        entrys.addAtIndex(index, entry);
    }
    
    /**
     * Edits(replaces) a specified entry in the list.
     * 
     * @throws UniqueEntryList.DuplicateEntryException if an equivalent entry already exists.
     * @throws UniqueEntryList.EntryNotFoundException if no such entry could be found in the list.
     */
    public void editEntry(Entry e, ReadOnlyEntry toEdit) throws UniqueEntryList.DuplicateEntryException, UniqueEntryList.EntryNotFoundException {
        entrys.edit(e, toEdit);
    }
    
    /**
     * Updates the fields of a specified entry in the list.
     * 
     * @throws UniqueEntryList.EntryNotFoundException if no such entry could be found in the list.
     */
    public void updateEntry(StartTime st, EndTime et, Date d, EndDate ed, UniqueTagList utl, ReadOnlyEntry toUpdate) throws UniqueEntryList.EntryNotFoundException{
        entrys.update(st, et, d, ed, utl, toUpdate);
    }
    //@@author

//// tag-level operations

    public void addTag(Tag t) throws UniqueTagList.DuplicateTagException {
        tags.add(t);
    }

//// util methods

    @Override
    public String toString() {
        return entrys.getInternalList().size() + " entrys, " + tags.getInternalList().size() +  " tags";
        // TODO: refine later
    }

    @Override
    public List<ReadOnlyEntry> getEntryList() {
        return Collections.unmodifiableList(entrys.getInternalList());
    }

    @Override
    public List<Tag> getTagList() {
        return Collections.unmodifiableList(tags.getInternalList());
    }

    @Override
    public UniqueEntryList getUniqueEntryList() {
        return this.entrys;
    }

    @Override
    public UniqueTagList getUniqueTagList() {
        return this.tags;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Scheduler // instanceof handles nulls
                && this.entrys.equals(((Scheduler) other).entrys)
                && this.tags.equals(((Scheduler) other).tags));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(entrys, tags);
    }

}
