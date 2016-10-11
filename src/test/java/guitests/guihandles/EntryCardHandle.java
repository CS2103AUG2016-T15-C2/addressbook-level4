package guitests.guihandles;

import guitests.GuiRobot;
import javafx.scene.Node;
import javafx.stage.Stage;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * Provides a handle to a person card in the person list panel.
 */
public class PersonCardHandle extends GuiHandle {
    private static final String NAME_FIELD_ID = "#name";
    private static final String DATE_FIELD_ID = "#address";
    private static final String PHONE_FIELD_ID = "#startTime";
    private static final String ENDTIME_FIELD_ID = "#endTime";

    private Node node;

    public PersonCardHandle(GuiRobot guiRobot, Stage primaryStage, Node node){
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

    public boolean isSamePerson(ReadOnlyPerson person){
        return getFullName().equals(person.getName().fullName) && getStartTime().equals(person.getStartTime().value)
                && getEndTime().equals(person.getEndTime().value) && getDate().equals(person.getDate().value);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PersonCardHandle) {
            PersonCardHandle handle = (PersonCardHandle) obj;
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
