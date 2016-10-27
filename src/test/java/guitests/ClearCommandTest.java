package guitests;

import org.junit.Test;

import javafx.scene.input.KeyCode;

import static org.junit.Assert.assertTrue;

public class ClearCommandTest extends SchedulerGuiTest {

    @Test
    public void clear() {

        //verify a non-empty list can be cleared using advanced command
        //NEEDFIX
        //assertTrue(entryListPanel.isListMatching(td.getTypicalEntrys()));
        assertAdvancedClearCommandSuccess();

        //verify other commands can work after a clear command
        commandBox.runCommand(td.meeting.getAddCommand());
        //NEEDFIX
        //assertTrue(entryListPanel.isListMatching(td.meeting));
        commandBox.runCommand("delete 1");
        assertListSize(0);
        //verify clear command works when the list is empty
        assertClearCommandSuccess();
    }
    
  //@@author A0161210A
    private void assertClearCommandSuccess() {
        commandBox.runCommand("clear");
        GuiRobot robot = new GuiRobot();
        robot.type(KeyCode.ENTER).sleep(500);
        assertListSize(0);
        assertResultMessage("Scheduler has been cleared!");
    }
    private void assertAdvancedClearCommandSuccess() {
        commandBox.runCommand("c");
        GuiRobot robot = new GuiRobot();
        robot.type(KeyCode.ENTER).sleep(500);
        assertListSize(0);
        assertResultMessage("Scheduler has been cleared!");
    }
    //@@author
}
