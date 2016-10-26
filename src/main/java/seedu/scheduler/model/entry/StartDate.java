package seedu.scheduler.model.entry;


import seedu.scheduler.commons.exceptions.IllegalValueException;

/**
 * Represents a Entry's startDate in the scheduler.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class StartDate {
    
    public static final String MESSAGE_DATE_CONSTRAINTS = "Item's start date should be in the format dd-mm-yyyy in numbers separated by a '-'";
    public static final String DATE_VALIDATION_REGEX = "((0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((18|19|20|21)\\d\\d)|empty)"; 

    public final String value;

    /**
     * Validates given start date.
     *
     * @throws IllegalValueException if given date string is invalid.
     */
    public StartDate(String startDate) throws IllegalValueException {
        assert startDate != null;
        if (startDate == null) {
            startDate = "empty";
        }
        if (!isValidDate(startDate)) {
            throw new IllegalValueException(MESSAGE_DATE_CONSTRAINTS);
        }
        this.value = startDate;
    }

    /**
     * Returns true if a given string is a valid entry endTime.
     */
    public static boolean isValidDate(String test) {
        return test.matches(DATE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartDate // instanceof handles nulls
                && this.value.equals(((StartDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}