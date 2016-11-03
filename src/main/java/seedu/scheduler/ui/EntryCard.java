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
        //System.out.println("System today: " + df.format(today));
    	
    	//put startDate and endDate into Date
    	String startDate = entry.getDate().toString();   	
    	String endDate = entry.getEndDate().toString();
    	String endTime = entry.getEndTime().toString();
   	
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat edf = new SimpleDateFormat("dd-MM-yyyy");
        
        Date startDateObj;
        Date endDateObj;

        //if only entry startDate overdue
		try {
			startDateObj = sdf.parse(startDate);
	        if (onlyStartDateInput(entry, today, startDateObj)) {
	             cardPane.setStyle(OVERDUE_INDICATION);  
	        }
		} catch (ParseException e1) {
		}
		
		//if only entry endDate overdue
		try {
	        endDateObj = edf.parse(endDate);  
	        if (onlyEndDateInput(entry, today, endDateObj)) {
	             cardPane.setStyle(OVERDUE_INDICATION);  
	        }
		} catch (ParseException e) {
		}
		
/*		//endDate is today and endTime is overdue
		try {
			endDateObj = edf.parse(endDate);
			if (endDateObj.equals(today)){
				try {
					if (checkEndTimeOverdueOrNot(endTime)) {
						cardPane.setStyle(OVERDUE_INDICATION); 
					}
				}
				catch (ParseException e) {
				}
			}				
		} catch (ParseException e) {
		}
*/		
    	//if entry completed
        if (entry.tagsString().contains("Completed")) {
            cardPane.setStyle(COMPLETED_INDICATION);   
        }  	

    }

	private boolean onlyEndDateInput(ReadOnlyEntry entry, Date today, Date endDateObj) {
		return endDateObj.before(today)
				&& entry.getStartTime().toString().contains("empty") 
				&& entry.getEndTime().toString().contains("empty")
				&& entry.getDate().toString().contains("empty");
	}

/*	public static boolean checkEndTimeOverdueOrNot(String endTime) throws ParseException {
        boolean endTimeOverdueOrnot = false;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endTime.substring(0, 2)));
        cal.set(Calendar.MINUTE, Integer.parseInt(endTime.substring(3)));
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if (Calendar.getInstance().after(cal)) {
            System.out.println("it's overdue");
            endTimeOverdueOrnot = true;
        } else {
            System.out.println("it's not overdue");
        }
        return endTimeOverdueOrnot;
    }
*/	
	private boolean onlyStartDateInput(ReadOnlyEntry entry, Date today, Date startDateObj) {
		return startDateObj.before(today) 
				&& entry.getStartTime().toString().contains("empty") 
				&& entry.getEndTime().toString().contains("empty")
				&& entry.getEndDate().toString().contains("empty");
	}
}
