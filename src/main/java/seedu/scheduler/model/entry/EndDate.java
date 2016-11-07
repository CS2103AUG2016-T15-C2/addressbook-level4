package seedu.scheduler.model.entry;


// java.time.LocalDate;

import seedu.scheduler.commons.exceptions.IllegalValueException;

//@@author A0161210A
/**
 * Represents a Entry's endDate in the scheduler.
 * Guarantees: immutable; is valid as declared in {@link #isValidEndDate(String)}
 */
public class EndDate { // implements Comparable<EndDate> {
    
    public static final String MESSAGE_DATE_CONSTRAINTS = "Item's endDate should be in the format dd-mm-yyyy in numbers separated by a '-'";
    public static final String DATE_VALIDATION_REGEX = "((0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((18|19|20|21)\\d\\d)|empty)"; 
    //@@author A0139956L-unused
    //unused: Not enough time to do DateQualifier
//    private LocalDate endDate;
    //@@author A0161210A
    public final String value;
    /**
     * 
     * @param endDate  The specified end date in string format for this entry
     * @throws IllegalValueException if given endDate string is invalid.
     */
    public EndDate(String endDate) throws IllegalValueException {
        if (endDate == null) {
            endDate = "empty";
        }
        if (!isValidEndDate(endDate)) {
            throw new IllegalValueException(MESSAGE_DATE_CONSTRAINTS);
        }
        this.value = endDate;
    }

    /**
     * Returns true if a given string is a valid entry endDate.
     * @param test  string that is to be tested to see if it is in a valid end date format
     * @return true if input string is in valid format
     */
    public static boolean isValidEndDate(String test) {
        return test.matches(DATE_VALIDATION_REGEX);
    }
  //@@author

    @Override
    public String toString() {
        return value;
    }
    //@@author A0139956L-unused
    //unused: Not enough time to do DateQualifier
//    @Override
//    public int compareTo(EndDate o) {
//    	return this.endDate.compareTo(o.endDate);
//    }
    //@@author
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