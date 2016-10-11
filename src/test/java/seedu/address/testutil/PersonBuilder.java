package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.person.*;

/**
 *
 */
public class PersonBuilder {

    private TestPerson person;

    public PersonBuilder() {
        this.person = new TestPerson();
    }

    public PersonBuilder withName(String name) throws IllegalValueException {
        this.person.setName(new Name(name));
        return this;
    }

    public PersonBuilder withTags(String ... tags) throws IllegalValueException {
        for (String tag: tags) {
            person.getTags().add(new Tag(tag));
        }
        return this;
    }

    public PersonBuilder withAddress(String address) throws IllegalValueException {
        this.person.setAddress(new Address(address));
        return this;
    }

    public PersonBuilder withStartDate(String startDate) throws IllegalValueException {
        this.person.setStartDate(new StartDate(startDate));
        return this;
    }

    public PersonBuilder withEndDate(String endDate) throws IllegalValueException {
        this.person.setEndDate(new EndDate(endDate));
        return this;
    }

    public TestPerson build() {
        return this.person;
    }

}
