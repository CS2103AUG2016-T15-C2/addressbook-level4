//@@author A0126090N
package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.scheduler.logic.commands.DeleteCommand.MESSAGE_DELETE_ENTRY_SUCCESS;

import java.util.List;

import org.junit.Test;

import guitests.guihandles.EntryCardHandle;
import seedu.scheduler.logic.commands.DeleteCommand;
import seedu.scheduler.model.Scheduler;
import seedu.scheduler.model.entry.Entry;
import seedu.scheduler.model.tag.Tag;
import seedu.scheduler.model.tag.UniqueTagList;
import seedu.scheduler.testutil.TestEntry;
import seedu.scheduler.testutil.TestUtil;

public class MarkedCommandTest extends SchedulerGuiTest {

	@Test
	public void marked() {
		// mark added entry
		TestEntry[] currentList = td.getTypicalEntrys();
		TestEntry entryToDelete = td.meeting;
		TestEntry entryToAdd = td.meetingCompleted;

		int targetIndex = 1;
		assertMarkedSuccess(targetIndex, currentList, entryToAdd);

	}
	
	@Test
    public void execute_marked_undoTagging() throws Exception {
		TestEntry[] currentList = td.getTypicalEntrys();
		TestEntry entryToUndo = td.competition;
		TestEntry entryToRedo = td.competitionMarked;

        currentList = TestUtil.addEntrysToList(currentList, entryToUndo);
		
        int targetIndex = 1;

		//assertUndoMarkedSuccess(targetIndex, currentList, entryToRedo);
    }
	
	@Test
    public void execute_marked_updateTagSet() throws Exception {
		TestEntry[] currentList = td.getTypicalEntrys();
		currentList = TestUtil.removeEntryFromList(currentList, 1);
		currentList = TestUtil.addEntrysToList(currentList, td.competitionMarked);
		TestEntry entryToUpdateTag = td.competition;

		//assertUpdateTagSetSuccess(currentList, entryToUpdateTag);
    }

	private void assertMarkedSuccess(int targetIndexOneIndexed, final TestEntry[] currentList, TestEntry entryToAdd) {
		TestEntry entryToDelete = currentList[targetIndexOneIndexed - 1];
		TestEntry[] expectedRemainder = TestUtil.removeEntryFromList(currentList, targetIndexOneIndexed);

		commandBox.runCommand("delete " + targetIndexOneIndexed);		
		commandBox.runCommand(entryToAdd.getAddCommand());
	}
	
	private void assertUndoMarkedSuccess(int targetIndexOneIndexed, final TestEntry[] currentList, TestEntry entryToAdd) {
		commandBox.runCommand("undo");	
		
		TestEntry entryToDelete = currentList[targetIndexOneIndexed - 1];
		TestEntry[] expectedList = TestUtil.removeEntryFromList(currentList, targetIndexOneIndexed);
		expectedList = TestUtil.addEntrysToList(currentList, entryToAdd);
		//assertTrue(entryListPanel.isListMatching(expectedList));
	
	}
	
	private void assertUpdateTagSetSuccess(final TestEntry[] currentList, TestEntry entryToAdd) {
		commandBox.runCommand("mark 1");
		
		TestEntry[] expectedList = TestUtil.addEntrysToList(currentList, entryToAdd);
		//assertTrue(entryListPanel.isListMatching(expectedList));
	
	}
}
