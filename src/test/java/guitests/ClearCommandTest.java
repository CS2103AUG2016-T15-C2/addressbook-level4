package guitests;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ClearCommandTest extends SchedulerGuiTest {

    @Test
    public void clear() {

        //verify a non-empty list can be cleared using advanced command
        assertTrue(entryListPanel.isListMatching(td.getTypicalEntrys()));
        assertAdvancedClearCommandSuccess();

        //verify other commands can work after a clear command
        commandBox.runCommand(td.meeting.getAddCommand());
        assertTrue(entryListPanel.isListMatching(td.meeting));
        commandBox.runCommand("delete 1");
        assertListSize(0);

        //verify clear command works when the list is empty
        assertClearCommandSuccess();
    }

    private void assertClearCommandSuccess() {
        commandBox.runCommand("clear");
        assertListSize(0);
        assertResultMessage("Scheduler has been cleared!");
    }
    
    private void assertAdvancedClearCommandSuccess() {
        commandBox.runCommand("c");
        assertListSize(0);
        assertResultMessage("Scheduler has been cleared!");
    }
}
