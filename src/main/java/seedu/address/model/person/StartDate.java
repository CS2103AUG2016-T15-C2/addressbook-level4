package seedu.address.model.person;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Person's startDate number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartDate(String)}
 */
public class StartDate {

    public static final String MESSAGE_START_DATE_CONSTRAINTS = "Item's date should be in the format dd-mm-yyyy in numbers separated by a '-'";
    public static final String START_DATE_VALIDATION_REGEX = "(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((18|19|20|21)\\d\\d)";

    public final String value;

    /**
     * Validates given Start Date.
     *
     * @throws IllegalValueException if given startDate string is invalid.
     */
    public StartDate(String startDate) throws IllegalValueException {
        assert startDate != null;
        startDate = startDate.trim();
        if (!isValidStartDate(startDate)) {
            throw new IllegalValueException(MESSAGE_START_DATE_CONSTRAINTS);
        }
        this.value = startDate;
    }

    /**
     * Returns true if a given string is a valid person startDate number.
     */
    public static boolean isValidStartDate(String test) {
        return test.matches(START_DATE_VALIDATION_REGEX);
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
