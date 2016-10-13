package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entry.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * JAXB-friendly version of the Entry.
 */
public class XmlAdaptedEntry {

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String startTime;
    @XmlElement(required = true)
    private String endTime;
    @XmlElement(required = true)
    private String date;

    @XmlElement
    private List<XmlAdaptedTag> tagged = new ArrayList<>();

    /**
     * No-arg constructor for JAXB use.
     */
    public XmlAdaptedEntry() {}


    /**
     * Converts a given Entry into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedEntry
     */
    public XmlAdaptedEntry(ReadOnlyEntry source) {
        name = source.getName().fullName;
        startTime = source.getStartTime().value;
        endTime = source.getEndTime().value;
        date = source.getDate().value;
        tagged = new ArrayList<>();
        for (Tag tag : source.getTags()) {
            tagged.add(new XmlAdaptedTag(tag));
        }
    }

    /**
     * Converts this jaxb-friendly adapted entry object into the model's Entry object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entry
     */
    public Entry toModelType() throws IllegalValueException {
        final List<Tag> entryTags = new ArrayList<>();
        for (XmlAdaptedTag tag : tagged) {
            entryTags.add(tag.toModelType());
        }
        final Name name = new Name(this.name);
        final StartTime startTime = new StartTime(this.startTime);
        final EndTime endTime = new EndTime(this.endTime);
        final Date date = new Date(this.date);
        final UniqueTagList tags = new UniqueTagList(entryTags);
        return new Entry(name, startTime, endTime, date, tags);
    }
}