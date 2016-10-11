package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.address.model.entry.ReadOnlyEntry;

public class EntryCard extends UiPart{

    private static final String FXML = "EntryListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label startTime;
    @FXML
    private Label date;
    @FXML
    private Label endTime;
    @FXML
    private Label tags;

    private ReadOnlyEntry entry;
    private int displayedIndex;

    public EntryCard(){

    }

    public static EntryCard load(ReadOnlyEntry entry, int displayedIndex){
        EntryCard card = new EntryCard();
        card.entry = entry;
        card.displayedIndex = displayedIndex;
        return UiPartLoader.loadUiPart(card);
    }

    @FXML
    public void initialize() {
        name.setText(entry.getName().fullName);
        id.setText(displayedIndex + ". ");
        startTime.setText(entry.getStartTime().value);
        date.setText(entry.getDate().value);
        endTime.setText(entry.getEndTime().value);
        tags.setText(entry.tagsString());
    }

    public HBox getLayout() {
        return cardPane;
    }

    @Override
    public void setNode(Node node) {
        cardPane = (HBox)node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }
}
