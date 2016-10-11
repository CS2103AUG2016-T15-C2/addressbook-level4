package guitests;

import org.junit.Test;
import seedu.address.testutil.TestEntry;
import seedu.address.testutil.TestUtil;

import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS;

public class DeleteCommandTest extends SchedulerGuiTest {

    @Test
    public void delete() {

        //delete the first in the list
        TestEntry[] currentList = td.getTypicalEntrys();
        int targetIndex = 1;
        assertDeleteSuccess(targetIndex, currentList);

        //delete the last in the list
        currentList = TestUtil.removeEntryFromList(currentList, targetIndex);
        targetIndex = currentList.length;
        assertDeleteSuccess(targetIndex, currentList);

        //delete from the middle of the list
        currentList = TestUtil.removeEntryFromList(currentList, targetIndex);
        targetIndex = currentList.length/2;
        assertDeleteSuccess(targetIndex, currentList);

        //invalid index
        commandBox.runCommand("delete " + currentList.length + 1);
        assertResultMessage("The entry index provided is invalid");

    }

    /**
     * Runs the delete command to delete the entry at specified index and confirms the result is correct.
     * @param targetIndexOneIndexed e.g. to delete the first entry in the list, 1 should be given as the target index.
     * @param currentList A copy of the current list of entrys (before deletion).
     */
    private void assertDeleteSuccess(int targetIndexOneIndexed, final TestEntry[] currentList) {
        TestEntry entryToDelete = currentList[targetIndexOneIndexed-1]; //-1 because array uses zero indexing
        TestEntry[] expectedRemainder = TestUtil.removeEntryFromList(currentList, targetIndexOneIndexed);

        commandBox.runCommand("delete " + targetIndexOneIndexed);

        //confirm the list now contains all previous entrys except the deleted entry
        assertTrue(entryListPanel.isListMatching(expectedRemainder));

        //confirm the result message is correct
        assertResultMessage(String.format(MESSAGE_DELETE_PERSON_SUCCESS, entryToDelete));
    }

}
