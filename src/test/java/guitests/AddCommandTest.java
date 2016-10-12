package guitests;

import guitests.guihandles.EntryCardHandle;
import org.junit.Test;
import seedu.address.logic.commands.AddCommand;
import seedu.address.commons.core.Messages;
import seedu.address.testutil.TestEntry;
import seedu.address.testutil.TestUtil;

import static org.junit.Assert.assertTrue;

public class AddCommandTest extends SchedulerGuiTest {

    @Test
    public void add() {
        //add one entry
        TestEntry[] currentList = td.getTypicalEntrys();
        TestEntry entryToAdd = td.meeting;
        assertAddSuccess(entryToAdd, currentList);
        currentList = TestUtil.addEntrysToList(currentList, entryToAdd);

        //add another entry
        entryToAdd = td.pickup;
        assertAddSuccess(entryToAdd, currentList);
        currentList = TestUtil.addEntrysToList(currentList, entryToAdd);

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
    }

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
