package guitests;

import guitests.guihandles.HelpWindowHandle;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HelpWindowTest extends SchedulerGuiTest {

    @Test
    public void openHelpWindow() {

        entryListPanel.clickOnListView();

        assertHelpWindowOpen(mainMenu.openHelpWindowUsingAccelerator());

        assertHelpWindowOpen(mainMenu.openHelpWindowUsingMenu());

        assertHelpWindowOpen(commandBox.runHelpCommand());

    }

    private void assertHelpWindowOpen(HelpWindowHandle helpWindowHandle) {
        assertTrue(helpWindowHandle.isWindowOpen());
        helpWindowHandle.closeWindow();
    }
}
