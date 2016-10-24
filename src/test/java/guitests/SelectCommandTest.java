package guitests;

import org.junit.Test;
import seedu.scheduler.model.entry.ReadOnlyEntry;

import static org.junit.Assert.assertEquals;

public class SelectCommandTest extends SchedulerGuiTest {


    @Test
    public void selectEntry_nonEmptyList() {

        assertSelectionInvalid(10); //invalid index
        assertNoEntrySelected();

        assertAdvancedSelectionSuccess(1); //first entry in the list
        int entryCount = td.getTypicalEntrys().length;
        assertSelectionSuccess(entryCount); //last entry in the list
        int middleIndex = entryCount / 2;
        assertSelectionSuccess(middleIndex); //a entry in the middle of the list

        assertSelectionInvalid(entryCount + 1); //invalid index
        assertEntrySelected(middleIndex); //assert previous selection remains

        /* Testing other invalid indexes such as -1 should be done when testing the SelectCommand */
    }

    @Test
    public void selectEntry_emptyList(){
        commandBox.runCommand("clear");
        assertListSize(0);
        assertSelectionInvalid(1); //invalid index
    }

    private void assertSelectionInvalid(int index) {
        commandBox.runCommand("select " + index);
        assertResultMessage("The entry index provided is invalid");
    }

    private void assertSelectionSuccess(int index) {
        commandBox.runCommand("select " + index);
        assertResultMessage("Selected Entry: "+index);
        assertEntrySelected(index);
    }
    
    private void assertAdvancedSelectionSuccess(int index) {
        commandBox.runCommand("s " + index);
        assertResultMessage("Selected Entry: "+index);
        assertEntrySelected(index);
    }

    private void assertEntrySelected(int index) {
        assertEquals(entryListPanel.getSelectedEntrys().size(), 1);
        ReadOnlyEntry selectedEntry = entryListPanel.getSelectedEntrys().get(0);
        assertEquals(entryListPanel.getEntry(index-1), selectedEntry);
        //TODO: confirm the correct page is loaded in the Browser Panel
    }

    private void assertNoEntrySelected() {
        assertEquals(entryListPanel.getSelectedEntrys().size(), 0);
    }

}
