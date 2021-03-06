package guitests;

import guitests.guihandles.EntryCardHandle;
import org.junit.Test;
import seedu.scheduler.logic.commands.AddCommand;
import seedu.scheduler.commons.core.Messages;
import seedu.scheduler.commons.exceptions.IllegalValueException;
import seedu.scheduler.testutil.TestEntry;
import seedu.scheduler.testutil.TestUtil;

import static org.junit.Assert.assertTrue;

public class AddCommandTest extends SchedulerGuiTest {

    @Test
    public void add() {
        //add one entry
        TestEntry[] currentList = td.getTypicalEntrys();
        TestEntry entryToAdd = td.meeting;
        //assertAddSuccess(entryToAdd, currentList);
        currentList = TestUtil.addEntrysToList(currentList, entryToAdd);

        //add another entry
        entryToAdd = td.pickup;
        //assertAdvancedAddSuccess(entryToAdd, currentList);
        currentList = TestUtil.addEntrysToList(currentList, entryToAdd);
        
        entryToAdd = td.hike;
/*
        //add duplicate entry
        commandBox.runCommand(td.meeting.getAddCommand());
        assertResultMessage(AddCommand.MESSAGE_DUPLICATE_ENTRY);
        assertTrue(entryListPanel.isListMatching(currentList));
        

        //add to empty list
        commandBox.runCommand("clear");
        assertAddSuccess(td.chem);

        //invalid command
        commandBox.runCommand("adds Johnny");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
        */
    }
  //@@author A0161210A
    /**
     * Test to see if the advanced add command ('a' instead of 'add') works properly
     * @param entryToAdd  the entry to be added
     * @param currentList current list of entries
     * 
     */
    private void assertAdvancedAddSuccess(TestEntry entryToAdd, TestEntry... currentList) {
        commandBox.runCommand(entryToAdd.getAdvancedAddCommand());
        EntryCardHandle addedCard = entryListPanel.navigateToEntry(entryToAdd.getName().fullName);
        assertMatching(entryToAdd, addedCard);

        //confirm the list now contains all previous entrys plus the new entry
        TestEntry[] expectedList = TestUtil.addEntrysToList(currentList, entryToAdd);
        assertTrue(entryListPanel.isListMatching(expectedList));
    }
    //@@author
    
    private void assertAddSuccess(TestEntry entryToAdd, TestEntry... currentList) {
        commandBox.runCommand(entryToAdd.getAddCommand());

        //confirm the new card contains the right data
        EntryCardHandle addedCard = entryListPanel.navigateToEntry(entryToAdd.getName().fullName);
        assertMatching(entryToAdd, addedCard);

        //confirm the list now contains all previous entrys plus the new entry
        TestEntry[] expectedList = TestUtil.addEntrysToList(currentList, entryToAdd);
        assertTrue(entryListPanel.isListMatching(expectedList));
    }

}
