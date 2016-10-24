package seedu.scheduler.model.entry;


import seedu.scheduler.commons.exceptions.IllegalValueException;

/**
 * Represents an Entry's end time in the scheduler.
 * Guarantees: immutable; is valid as declared in {@link #isValidEndTime(String)}
 */
public class EndTime {

    public static final String MESSAGE_ENDTIME_CONSTRAINTS =
            "Item's end time should be in the 24-Hr format hh-mm in numbers separated by a ':'";
    public static final String ENDTIME_VALIDATION_REGEX = "(([01]?[0-9]|2[0-3]):[0-5][0-9]|empty)"; 

    public final String value;

    /**
     * Validates given endTime.
     *
     * @throws IllegalValueException if given endTime scheduler string is invalid.
     */
    public EndTime(String endTime) throws IllegalValueException {
        assert endTime != null;
        if(endTime == null) {
            endTime = "empty";
        }
        endTime = endTime.trim();
        if (!isValidEndTime(endTime)) {
            throw new IllegalValueException(MESSAGE_ENDTIME_CONSTRAINTS);
        }
        this.value = endTime;
    }

    /**
     * Returns if a given string is a valid entry endTime.
     */
    public static boolean isValidEndTime(String test) {
        return test.matches(ENDTIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndTime // instanceof handles nulls
                && this.value.equals(((EndTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
