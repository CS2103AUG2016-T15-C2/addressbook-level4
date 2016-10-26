package seedu.scheduler.testutil;

import seedu.scheduler.commons.exceptions.IllegalValueException;
import seedu.scheduler.model.tag.Tag;
import seedu.scheduler.model.entry.*;

/**
 *
 */
public class EntryBuilder {

    private TestEntry entry;

    public EntryBuilder() {
        this.entry = new TestEntry();
    }

    public EntryBuilder withName(String name) throws IllegalValueException {
        this.entry.setName(new Name(name));
        return this;
    }

    public EntryBuilder withTags(String ... tags) throws IllegalValueException {
        for (String tag: tags) {
            entry.getTags().add(new Tag(tag));
        }
        return this;
    }

    public EntryBuilder withDate(String startDate) throws IllegalValueException {
        this.entry.setDate(new StartDate(startDate));
        return this;
    }

    public EntryBuilder withStartTime(String startTime) throws IllegalValueException {
        this.entry.setStartTime(new StartTime(startTime));
        return this;
    }

    public EntryBuilder withEndTime(String endTime) throws IllegalValueException {
        this.entry.setEndTime(new EndTime(endTime));
        return this;
    }

    public TestEntry build() {
        return this.entry;
    }

}
