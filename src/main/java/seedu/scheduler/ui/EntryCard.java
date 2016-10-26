package seedu.scheduler.ui;

//import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.scheduler.model.entry.ReadOnlyEntry;

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
    //@@author A0139956L
    public static final String COMPLETED_INDICATION = "-fx-background-color: #ccffcc;";
    public static final String OVERDUE_INDICATION = "-fx-background-color:  #ffcce6;";
    //@@author
    public EntryCard(){

    }

    public static EntryCard load(ReadOnlyEntry entry, int displayedIndex){
        EntryCard card = new EntryCard();
        card.entry = entry;
        card.displayedIndex = displayedIndex;
        return UiPartLoader.loadUiPart(card);
    }

    //@@author A0139956L
    @FXML
    public void initialize() {
        name.setText(entry.getName().fullName);
        hideFieldsAccordingToType(entry);
        indicatingColourByCondition(entry);
        id.setText(displayedIndex + ". ");
        startTime.setText("Start Time: " + entry.getStartTime().value);
        date.setText("Date: " + entry.getDate().value);
        endTime.setText("End Time: " + entry.getEndTime().value);
        tags.setText(entry.tagsString());
    }
    //@@author
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
    
    //@@author A0139956L
    public void hideFieldsAccordingToType(ReadOnlyEntry entry) {
        //task
        if (entry.getStartTime().toString().contains("empty") 
        		&& entry.getEndTime().toString().contains("empty")) {
            startTime.setVisible(false);
            endTime.setVisible(false);
        } 
        if (entry.getStartTime().toString().contains("empty") 
        		&& entry.getEndTime().toString().contains("empty")
        		&& entry.getDate().toString().contains("empty")) {
            startTime.setVisible(false);
            endTime.setVisible(false);
            date.setVisible(false);
        } 
    }
    
    public void indicatingColourByCondition(ReadOnlyEntry entry) {
        
        if (entry.tagsString().contains("Completed")) {
            cardPane.setStyle(COMPLETED_INDICATION);   // if entry completed
        } 
        //else if (entryRead.getDate().before(new Date())) {
            // if entry overdue
            //cardPane.setStyle(OVERDUE_INDICATION);
       // }
    }
}
