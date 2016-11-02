package seedu.scheduler.ui;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        if (endDateInput(entry)) {
            startTime.setVisible(false);
            endTime.setVisible(false);
            date.setVisible(false);
        } 
        if (startDateInput(entry)) {
            startTime.setVisible(false);
            endTime.setVisible(false);
            endDate.setVisible(false);
        } 
        if(startAndEndDateInput(entry)) {
            startTime.setVisible(false);
            endTime.setVisible(false);
        }
        //floating task
        if (floatTask(entry)) {
            startTime.setVisible(false);
            endTime.setVisible(false);
            date.setVisible(false);
            endDate.setVisible(false);
        } 
        if (startTimeInput(entry)) {
        	endTime.setVisible(false);
        	endDate.setVisible(false);
        }
        if (endTimeInput(entry)) {
        	startTime.setVisible(false);
        	date.setVisible(false);
        }
    }

	private boolean startAndEndDateInput(ReadOnlyEntry entry) {
		return entry.getStartTime().toString().contains("empty") 
        		&& entry.getEndTime().toString().contains("empty");
	}

	private boolean endDateInput(ReadOnlyEntry entry) {
		return entry.getStartTime().toString().contains("empty") 
        		&& entry.getEndTime().toString().contains("empty")
        		&& entry.getDate().toString().contains("empty");
	}

	private boolean startDateInput(ReadOnlyEntry entry) {
		return entry.getStartTime().toString().contains("empty") 
        		&& entry.getEndTime().toString().contains("empty")
        		&& entry.getEndDate().toString().contains("empty");
	}

	private boolean floatTask(ReadOnlyEntry entry) {
		return entry.getStartTime().toString().contains("empty") 
        		&& entry.getEndTime().toString().contains("empty")
        		&& entry.getDate().toString().contains("empty")
        		&& entry.getEndDate().toString().contains("empty");
	}
	
	private boolean startTimeInput(ReadOnlyEntry entry) {
		return entry.getEndTime().toString().contains("empty")
         		&& entry.getEndDate().toString().contains("empty");
	}
	
	private boolean endTimeInput(ReadOnlyEntry entry) {
		return entry.getStartTime().toString().contains("empty") 
        		&& entry.getDate().toString().contains("empty");
	}
    
    public void indicatingColourByCondition(ReadOnlyEntry entry) {
    	//get today date from system
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date today = new Date();
        //System.out.println("Today: " + df.format(today));
    	
    	//put startDate and endDate into Date
    	String startDate = entry.getDate().toString();   	
    	String endDate = entry.getEndDate().toString();
    	System.out.println("String for ed: " + endDate);
    	
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat edf = new SimpleDateFormat("dd-MM-yyyy");
        Date startDateObj;
        Date endDateObj;
        
		try {
			startDateObj = sdf.parse(startDate);
			System.out.println("Start date: " + sdf.format(startDateObj));
			
	        //if entry startDate overdue
	        if (onlyStartDateInput(entry, today, startDateObj)) {
	             cardPane.setStyle(OVERDUE_INDICATION);  
	        }
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
       
		try {
	        endDateObj = edf.parse(endDate);
	        System.out.println("End date: " + edf.format(endDateObj));
	        
	        //if entry endDate overdue
	        if (endDateObj.before(today)) {
	             cardPane.setStyle(OVERDUE_INDICATION);  
	        }
		} catch (ParseException e) {
			e.printStackTrace();
		}
 	
    	//if entry completed
        if (entry.tagsString().contains("Completed")) {
            cardPane.setStyle(COMPLETED_INDICATION);   
        }  	

    }

	private boolean onlyStartDateInput(ReadOnlyEntry entry, Date today, Date startDateObj) {
		return startDateObj.before(today) 
				&& entry.getStartTime().toString().contains("empty") 
				&& entry.getEndTime().toString().contains("empty")
				&& entry.getEndDate().toString().contains("empty");
	}
}
