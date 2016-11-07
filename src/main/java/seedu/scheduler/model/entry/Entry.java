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
    private EndDate endDate;

    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Entry(Name name, StartTime startTime, EndTime endTime, Date date, EndDate endDate, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, startTime, endTime, date, tags);
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.endDate = endDate;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Entry(ReadOnlyEntry source) {
        this(source.getName(), source.getStartTime(), source.getEndTime(), source.getDate(), source.getEndDate(), source.getTags());
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
    public EndDate getEndDate() {
        return endDate;
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    //@@author A0152962B-unused
    /**
     * Replaces this entry's name with name in the argument.
     * @param name to replace
     */
    //Unused: mutator no longer needed as update feature is removed.
    /*public void setName(Name name) {
        this.name = name;
    }
    */
    
    /**
     * Replaces this entry's start time with startTime in the argument.
     * @param startTime to replace
     */
    //Unused: mutator no longer needed as update feature is removed.
    /*public void setStartTime(StartTime startTime) {
        this.startTime = startTime;
    }
    */
    
    /**
     * Replaces this entry's end time with endTime in the argument.
     * @param endTime to replace
     */
    //Unused: mutator no longer needed as update feature is removed.
    /*public void setEndTime(EndTime endTime) {
        this.endTime = endTime;
    }
    */
    
    /**
     * Replaces this entry's date/start date with date in the argument.
     * @param date to replace
     */
    //Unused: mutator no longer needed as update feature is removed.
    /*public void setDate(Date date) {
        this.date = date;
    }
    */
    
    /**
     * Replaces this entry's end date with endDate in the argument.
     * @param endDate to replace
     */
    //Unused: mutator no longer needed as update feature is removed.
    /*public void setEndDate(EndDate endDate) {
        this.endDate = endDate;
    }
    */
    
    /**
     * Adds tags to this entry's tags with the tags in the argument tag list that are do not already exist.
     * @param updates to replace
     */
    //Unused: mutator no longer needed as update feature is removed.
    /*public void updateTags(UniqueTagList updates) {
        this.tags.mergeFrom(updates);
    }
    */
    //@@author
    
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
