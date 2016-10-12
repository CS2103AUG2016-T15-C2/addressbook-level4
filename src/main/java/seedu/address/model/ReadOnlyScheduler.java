package seedu.address.model;


import seedu.address.model.entry.ReadOnlyEntry;
import seedu.address.model.entry.UniqueEntryList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

import java.util.List;

/**
 * Unmodifiable view of an scheduler
 */
public interface ReadOnlyScheduler {

    UniqueTagList getUniqueTagList();

    UniqueEntryList getUniqueEntryList();

    /**
     * Returns an unmodifiable view of entrys list
     */
    List<ReadOnlyEntry> getEntryList();

    /**
     * Returns an unmodifiable view of tags list
     */
    List<Tag> getTagList();

}
