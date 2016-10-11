package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.Scheduler;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * A utility class to help with building Scheduler objects.
 * Example usage: <br>
 *     {@code Scheduler ab = new SchedulerBuilder().withPerson("John", "Doe").withTag("Friend").build();}
 */
public class SchedulerBuilder {

    private Scheduler scheduler;

    public SchedulerBuilder(Scheduler scheduler){
        this.scheduler = scheduler;
    }

    public SchedulerBuilder withPerson(Person person) throws UniquePersonList.DuplicatePersonException {
        scheduler.addPerson(person);
        return this;
    }

    public SchedulerBuilder withTag(String tagName) throws IllegalValueException {
        scheduler.addTag(new Tag(tagName));
        return this;
    }

    public Scheduler build(){
        return scheduler;
    }
}
