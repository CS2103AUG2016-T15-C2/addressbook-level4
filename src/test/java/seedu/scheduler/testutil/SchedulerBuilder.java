package seedu.scheduler.testutil;

import seedu.scheduler.commons.exceptions.IllegalValueException;
import seedu.scheduler.model.tag.Tag;
import seedu.scheduler.model.Scheduler;
import seedu.scheduler.model.entry.Entry;
import seedu.scheduler.model.entry.UniqueEntryList;

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
