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

    public PersonBuilder withDate(String address) throws IllegalValueException {
        this.person.setDate(new Date(address));
        return this;
    }

    public PersonBuilder withStartTime(String startTime) throws IllegalValueException {
        this.person.setStartTime(new StartTime(startTime));
        return this;
    }

    public PersonBuilder withEndTime(String endTime) throws IllegalValueException {
        this.person.setEndTime(new EndTime(endTime));
        return this;
    }

    public TestPerson build() {
        return this.person;
    }

}
