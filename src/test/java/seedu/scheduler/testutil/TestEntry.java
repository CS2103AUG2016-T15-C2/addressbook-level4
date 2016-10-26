package seedu.scheduler.testutil;

import seedu.scheduler.model.tag.UniqueTagList;
import seedu.scheduler.model.entry.*;

/**
 * A mutable entry object. For testing only.
 */
public class TestEntry implements ReadOnlyEntry {

    private Name name;
    private StartDate startDate;
    private EndTime endTime;
    private StartTime startTime;
    private UniqueTagList tags;

    public TestEntry() {
        tags = new UniqueTagList();
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setDate(StartDate startDate) {
        this.startDate = startDate;
    }

    public void setEndTime(EndTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(StartTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public StartTime getStartTime() {
        return startTime;
    }

    @Override
    public EndTime getEndTime() {
        return endTime;
    }

    @Override
    public StartDate getStartDate() {
        return startDate;
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
        sb.append("st/" + this.getStartTime().value + " ");
        sb.append("et/" + this.getEndTime().value + " ");
        sb.append("sd/" + this.getStartDate().value + " ");
        this.getTags().getInternalList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }
    
    public String getAdvancedAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("a " + this.getName().fullName + " ");
        sb.append("st/" + this.getStartTime().value + " ");
        sb.append("et/" + this.getEndTime().value + " ");
        sb.append("sd/" + this.getStartDate().value + " ");
        this.getTags().getInternalList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }
}
