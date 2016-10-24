package seedu.scheduler.model.entry;

import seedu.scheduler.commons.util.CollectionUtil;
import seedu.scheduler.model.tag.UniqueTagList;

import java.util.Objects;

/**
 * Represents an Entry in the scheduler.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Entry implements ReadOnlyEntry {

    private Name name;
    private StartTime startTime;
    private EndTime endTime;
    private Date date;

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Entry(Name name, StartTime startTime, EndTime endTime, Date date, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, startTime, endTime, date, tags);
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Entry(ReadOnlyEntry source) {
        this(source.getName(), source.getStartTime(), source.getEndTime(), source.getDate(), source.getTags());
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public StartTime getStartTime() {
        return startTime;
    }

    @Override
    public EndTime getEndTime() {
        return endTime;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this entry's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyEntry // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyEntry) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, startTime, endTime, date, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}
