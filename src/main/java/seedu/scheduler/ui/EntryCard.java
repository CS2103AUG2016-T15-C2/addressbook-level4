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
    private Label endDate;
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
        date.setText("Start Date : " + entry.getDate().value);
        startTime.setText("Start Time : " + entry.getStartTime().value);
        endDate.setText("End Date  : " + entry.getEndDate().value);
        endTime.setText("End Time  : " + entry.getEndTime().value);
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
        //deadline task
        if (entry.getStartTime().toString().contains("empty") 
        		&& entry.getEndTime().toString().contains("empty")) {
            startTime.setVisible(false);
            endTime.setVisible(false);
            date.setVisible(false);
        } 
        //floating task
        if (entry.getStartTime().toString().contains("empty") 
        		&& entry.getEndTime().toString().contains("empty")
        		&& entry.getDate().toString().contains("empty")
        		&& entry.getEndDate().toString().contains("empty")) {
            startTime.setVisible(false);
            endTime.setVisible(false);
            date.setVisible(false);
            endDate.setVisible(false);
        } 
    }
    
    public void indicatingColourByCondition(ReadOnlyEntry entry) {
    	//entry completed
        if (entry.tagsString().contains("Completed")) {
            cardPane.setStyle(COMPLETED_INDICATION);   
        } 
        // if entry overdue
        //else if (entryRead.getDate().before(new Date())) {
             //cardPane.setStyle(OVERDUE_INDICATION);  
       // }
    }
}
