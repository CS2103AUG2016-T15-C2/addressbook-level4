package seedu.address.model.person;


import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents an Entry's end date in the scheduler.
 * Guarantees: immutable; is valid as declared in {@link #isValidEndDate(String)}
 */
public class EndDate {

    public static final String MESSAGE_ENDDATE_CONSTRAINTS =
            "Item's end date should be in the format dd-mm-yyyy in numbers separated by a '-'";
    public static final String ENDDATE_VALIDATION_REGEX = "(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((18|19|20|21)\\d\\d)"; 

    public final String value;

    /**
     * Validates given endDate.
     *
     * @throws IllegalValueException if given endDate address string is invalid.
     */
    public EndDate(String endDate) throws IllegalValueException {
        assert endDate != null;
        endDate = endDate.trim();
        if (!isValidEndDate(endDate)) {
            throw new IllegalValueException(MESSAGE_ENDDATE_CONSTRAINTS);
        }
        this.value = endDate;
    }

    /**
     * Returns if a given string is a valid person endDate.
     */
    public static boolean isValidEndDate(String test) {
        return test.matches(ENDDATE_VALIDATION_REGEX);
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
