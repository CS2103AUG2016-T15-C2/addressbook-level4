package guitests.guihandles;

import guitests.GuiRobot;
import javafx.scene.Node;
import javafx.stage.Stage;
import seedu.scheduler.model.entry.ReadOnlyEntry;

/**
 * Provides a handle to a entry card in the entry list panel.
 */
public class EntryCardHandle extends GuiHandle {
    private static final String NAME_FIELD_ID = "#name";
    private static final String DATE_FIELD_ID = "#date";
    private static final String PHONE_FIELD_ID = "#startTime";
    private static final String ENDTIME_FIELD_ID = "#endTime";

    private Node node;

    public EntryCardHandle(GuiRobot guiRobot, Stage primaryStage, Node node){
        super(guiRobot, primaryStage, null);
        this.node = node;
    }

    protected String getTextFromLabel(String fieldId) {
        return getTextFromLabel(fieldId, node);
    }

    public String getFullName() {
        return getTextFromLabel(NAME_FIELD_ID);
    }

    public String getDate() {
        return getTextFromLabel(DATE_FIELD_ID);
    }

    public String getStartTime() {
        return getTextFromLabel(PHONE_FIELD_ID);
    }

    public String getEndTime() {
        return getTextFromLabel(ENDTIME_FIELD_ID);
    }

    public boolean isSameEntry(ReadOnlyEntry entry){
        return getFullName().equals(entry.getName().fullName) && getStartTime().equals(entry.getStartTime().value)
                && getEndTime().equals(entry.getEndTime().value) && getDate().equals(entry.getStartDate().value);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof EntryCardHandle) {
            EntryCardHandle handle = (EntryCardHandle) obj;
            return getFullName().equals(handle.getFullName())
                    && getDate().equals(handle.getDate()); //TODO: compare the rest
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return getFullName() + " " + getDate();
    }
}
