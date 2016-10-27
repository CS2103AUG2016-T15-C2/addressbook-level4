package seedu.scheduler.model.entry;


import seedu.scheduler.commons.exceptions.IllegalValueException;

//@@author A0161210A
/**
 * Represents a Entry's endDate in the scheduler.
 * Guarantees: immutable; is valid as declared in {@link #isValidEndDate(String)}
 */
public class EndDate {
    
    public static final String MESSAGE_DATE_CONSTRAINTS = "Item's endDate should be in the format dd-mm-yyyy in numbers separated by a '-'";
    public static final String DATE_VALIDATION_REGEX = "((0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((18|19|20|21)\\d\\d)|empty)"; 

    public final String value;

    /**
     * ValiendDates given endDate.
     *
     * @throws IllegalValueException if given endDate string is invalid.
     */
    public EndDate(String endDate) throws IllegalValueException {
        assert endDate != null;
        if (endDate == null) {
            endDate = "empty";
        }
        if (!isValidEndDate(endDate)) {
            throw new IllegalValueException(MESSAGE_DATE_CONSTRAINTS);
        }
        this.value = endDate;
    }

    /**
     * Returns true if a given string is a valid entry endTime.
     */
    public static boolean isValidEndDate(String test) {
        return test.matches(DATE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndDate // instanceof handles nulls
                && this.value.equals(((EndDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}