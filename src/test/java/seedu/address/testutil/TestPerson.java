package seedu.address.testutil;

import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.person.*;

/**
 * A mutable person object. For testing only.
 */
public class TestPerson implements ReadOnlyPerson {

    private Name name;
    private Address address;
    private Email email;
    private StartDate startDate;
    private UniqueTagList tags;

    public TestPerson() {
        tags = new UniqueTagList();
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setStartDate(StartDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public StartDate getStartDate() {
        return startDate;
    }

    @Override
    public Email getEmail() {
        return email;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public UniqueTagList getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return getAsText();
    }

    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add " + this.getName().fullName + " ");
        sb.append("p/" + this.getStartDate().value + " ");
        sb.append("e/" + this.getEmail().value + " ");
        sb.append("a/" + this.getAddress().value + " ");
        this.getTags().getInternalList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }
}
