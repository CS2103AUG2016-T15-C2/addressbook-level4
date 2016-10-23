package seedu.scheduler.storage;

import seedu.scheduler.commons.exceptions.IllegalValueException;
import seedu.scheduler.model.tag.Tag;
import seedu.scheduler.model.tag.UniqueTagList;
import seedu.scheduler.model.ReadOnlyScheduler;
import seedu.scheduler.model.entry.ReadOnlyEntry;
import seedu.scheduler.model.entry.UniqueEntryList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable Scheduler that is serializable to XML format
 */
@XmlRootElement(name = "scheduler")
public class XmlSerializableScheduler implements ReadOnlyScheduler {

    @XmlElement
    private List<XmlAdaptedEntry> entrys;
    @XmlElement
    private List<Tag> tags;

    {
        entrys = new ArrayList<>();
        tags = new ArrayList<>();
    }

    /**
     * Empty constructor required for marshalling
     */
    public XmlSerializableScheduler() {}

    /**
     * Conversion
     */
    public XmlSerializableScheduler(ReadOnlyScheduler src) {
        entrys.addAll(src.getEntryList().stream().map(XmlAdaptedEntry::new).collect(Collectors.toList()));
        tags = src.getTagList();
    }

    @Override
    public UniqueTagList getUniqueTagList() {
        try {
            return new UniqueTagList(tags);
        } catch (UniqueTagList.DuplicateTagException e) {
            //TODO: better error handling
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UniqueEntryList getUniqueEntryList() {
        UniqueEntryList lists = new UniqueEntryList();
        for (XmlAdaptedEntry p : entrys) {
            try {
                lists.add(p.toModelType());
            } catch (IllegalValueException e) {
                //TODO: better error handling
            }
        }
        return lists;
    }

    @Override
    public List<ReadOnlyEntry> getEntryList() {
        return entrys.stream().map(p -> {
            try {
                return p.toModelType();
            } catch (IllegalValueException e) {
                e.printStackTrace();
                //TODO: better error handling
                return null;
            }
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Tag> getTagList() {
        return Collections.unmodifiableList(tags);
    }

}
