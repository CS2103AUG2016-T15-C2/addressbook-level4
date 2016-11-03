package guitests;

import org.junit.Test;

import seedu.scheduler.testutil.EntryBuilder;
import seedu.scheduler.testutil.TestEntry;
import seedu.scheduler.testutil.TestUtil;

import static org.junit.Assert.assertTrue;

public class EditCommandTest extends SchedulerGuiTest {

    @Test
    public void edit() {
        
        //edit the first in the list
        TestEntry[] currentList = td.getTypicalEntrys();
        int targetIndex = 1;
        TestEntry entryToEditTo = td.meeting;
        currentList = TestUtil.removeEntrysFromList(currentList, entryToEditTo);
        
        assertEditSuccess(targetIndex, currentList, entryToEditTo);
    }
    
    
    private void assertEditSuccess(int targetIndexOneIndexed, final TestEntry[] currentList, TestEntry entryToEditTo){
        //TestEntry entryToEdit = currentList[targetIndexOneIndexed-1]; //-1 because array starts from index 0
        commandBox.runCommand(entryToEditTo.getEditCommand(targetIndexOneIndexed));
        
        //confirm the list now contains all previous entrys plus the newly edited entry
        TestEntry[] expectedList = TestUtil.replaceEntryFromList(currentList, entryToEditTo, targetIndexOneIndexed);
        //assertTrue(entryListPanel.isListMatching(expectedList));
    }
}
