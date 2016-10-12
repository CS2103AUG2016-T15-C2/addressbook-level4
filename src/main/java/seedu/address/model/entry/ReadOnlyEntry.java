package seedu.address.model.entry;

import seedu.address.model.tag.UniqueTagList;

/**
 * A read-only immutable interface for a Entry in the scheduler.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyEntry {

    Name getName();
    StartTime getStartTime();
    EndTime getEndTime();
    Date getDate();

    /**
     * The returned TagList is a deep copy of the internal TagList,
     * changes on the returned list will not affect the entry's internal tags.
     */
    UniqueTagList getTags();

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyEntry other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getStartTime().equals(this.getStartTime())
                && other.getEndTime().equals(this.getEndTime())
                && other.getDate().equals(this.getDate()));
    }

    /**
     * Formats the entry as text, showing all contact details.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Start Time: ")
                .append(getStartTime())
                .append(" End Time: ")
                .append(getEndTime())
                .append(" Date: ")
                .append(getDate())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Returns a string representation of this Entry's tags
     */
    default String tagsString() {
        final StringBuffer buffer = new StringBuffer();
        final String separator = ", ";
        getTags().forEach(tag -> buffer.append(tag).append(separator));
        if (buffer.length() == 0) {
            return "";
        } else {
            return buffer.substring(0, buffer.length() - separator.length());
        }
    }

}
