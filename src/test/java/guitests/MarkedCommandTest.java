package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.scheduler.logic.commands.DeleteCommand.MESSAGE_DELETE_ENTRY_SUCCESS;

import org.junit.Test;

import guitests.guihandles.EntryCardHandle;
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

	private void assertMarkedSuccess(int targetIndexOneIndexed, final TestEntry[] currentList, TestEntry entryToAdd) {
		TestEntry entryToDelete = currentList[targetIndexOneIndexed - 1];
		TestEntry[] expectedRemainder = TestUtil.removeEntryFromList(currentList, targetIndexOneIndexed);

		commandBox.runCommand("delete " + targetIndexOneIndexed);		
		commandBox.runCommand(entryToAdd.getAddCommand());
	}
}
