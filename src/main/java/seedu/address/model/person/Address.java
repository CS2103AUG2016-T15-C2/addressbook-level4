package seedu.address.model.person;


import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Person's address in the scheduler.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {
    
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Item's date should be in the format dd-mm-yyyy in numbers separated by a '-'";
    public static final String ADDRESS_VALIDATION_REGEX = "(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((18|19|20|21)\\d\\d)"; 

    public final String value;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address) throws IllegalValueException {
        assert address != null;
        if (!isValidAddress(address)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.value = address;
    }

    /**
     * Returns true if a given string is a valid person endTime.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}