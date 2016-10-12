package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.Scheduler;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.UniqueEntryList;

/**
 * A utility class to help with building Scheduler objects.
 * Example usage: <br>
 *     {@code Scheduler ab = new SchedulerBuilder().withEntry("John", "Doe").withTag("Friend").build();}
 */
public class SchedulerBuilder {

    private Scheduler scheduler;

    public SchedulerBuilder(Scheduler scheduler){
        this.scheduler = scheduler;
    }

    public SchedulerBuilder withEntry(Entry entry) throws UniqueEntryList.DuplicateEntryException {
        scheduler.addEntry(entry);
        return this;
    }

    public SchedulerBuilder withTag(String tagName) throws IllegalValueException {
        scheduler.addTag(new Tag(tagName));
        return this;
    }

    public Scheduler build(){
        return scheduler;
    }
}
