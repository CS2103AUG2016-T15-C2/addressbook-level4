package guitests;

import org.junit.Test;
import seedu.address.commons.core.Messages;
import seedu.address.testutil.TestEntry;

import static org.junit.Assert.assertTrue;

public class FindCommandTest extends SchedulerGuiTest {

    @Test
    public void find_nonEmptyList() {
        assertFindResult("find Mark"); //no results
        assertAdvancedFindResult("f Test", td.chem, td.bio); //multiple results

        //find after deleting one result
        commandBox.runCommand("delete 1");
        assertFindResult("find Test",td.bio);
    }

    @Test
    public void find_emptyList(){
        commandBox.runCommand("clear");
        assertFindResult("find Jean"); //no results
    }

    @Test
    public void find_invalidCommand_fail() {
        commandBox.runCommand("findgeorge");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    private void assertFindResult(String command, TestEntry... expectedHits ) {
        commandBox.runCommand(command);
        assertListSize(expectedHits.length);
        assertResultMessage(expectedHits.length + " entrys listed!");
        assertTrue(entryListPanel.isListMatching(expectedHits));
    }
    
    private void assertAdvancedFindResult(String command, TestEntry... expectedHits ) {
        commandBox.runCommand(command);
        assertListSize(expectedHits.length);
        assertResultMessage(expectedHits.length + " entrys listed!");
        assertTrue(entryListPanel.isListMatching(expectedHits));
    }
}
