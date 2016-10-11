package guitests.guihandles;


import guitests.GuiRobot;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import seedu.address.TestApp;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.ReadOnlyEntry;
import seedu.address.testutil.TestUtil;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Provides a handle for the panel containing the entry list.
 */
public class EntryListPanelHandle extends GuiHandle {

    public static final int NOT_FOUND = -1;
    public static final String CARD_PANE_ID = "#cardPane";

    private static final String ENTRY_LIST_VIEW_ID = "#entryListView";

    public EntryListPanelHandle(GuiRobot guiRobot, Stage primaryStage) {
        super(guiRobot, primaryStage, TestApp.APP_TITLE);
    }

    public List<ReadOnlyEntry> getSelectedEntrys() {
        ListView<ReadOnlyEntry> entryList = getListView();
        return entryList.getSelectionModel().getSelectedItems();
    }

    public ListView<ReadOnlyEntry> getListView() {
        return (ListView<ReadOnlyEntry>) getNode(ENTRY_LIST_VIEW_ID);
    }

    /**
     * Returns true if the list is showing the entry details correctly and in correct order.
     * @param entrys A list of entry in the correct order.
     */
    public boolean isListMatching(ReadOnlyEntry... entrys) {
        return this.isListMatching(0, entrys);
    }
    
    /**
     * Clicks on the ListView.
     */
    public void clickOnListView() {
        Point2D point= TestUtil.getScreenMidPoint(getListView());
        guiRobot.clickOn(point.getX(), point.getY());
    }

    /**
     * Returns true if the {@code entrys} appear as the sub list (in that order) at position {@code startPosition}.
     */
    public boolean containsInOrder(int startPosition, ReadOnlyEntry... entrys) {
        List<ReadOnlyEntry> entrysInList = getListView().getItems();

        // Return false if the list in panel is too short to contain the given list
        if (startPosition + entrys.length > entrysInList.size()){
            return false;
        }

        // Return false if any of the entrys doesn't match
        for (int i = 0; i < entrys.length; i++) {
            if (!entrysInList.get(startPosition + i).getName().fullName.equals(entrys[i].getName().fullName)){
                return false;
            }
        }

        return true;
    }

    /**
     * Returns true if the list is showing the entry details correctly and in correct order.
     * @param startPosition The starting position of the sub list.
     * @param entrys A list of entry in the correct order.
     */
    public boolean isListMatching(int startPosition, ReadOnlyEntry... entrys) throws IllegalArgumentException {
        if (entrys.length + startPosition != getListView().getItems().size()) {
            throw new IllegalArgumentException("List size mismatched\n" +
                    "Expected " + (getListView().getItems().size() - 1) + " entrys");
        }
        assertTrue(this.containsInOrder(startPosition, entrys));
        for (int i = 0; i < entrys.length; i++) {
            final int scrollTo = i + startPosition;
            guiRobot.interact(() -> getListView().scrollTo(scrollTo));
            guiRobot.sleep(200);
            if (!TestUtil.compareCardAndEntry(getEntryCardHandle(startPosition + i), entrys[i])) {
                return false;
            }
        }
        return true;
    }


    public EntryCardHandle navigateToEntry(String name) {
        guiRobot.sleep(500); //Allow a bit of time for the list to be updated
        final Optional<ReadOnlyEntry> entry = getListView().getItems().stream().filter(p -> p.getName().fullName.equals(name)).findAny();
        if (!entry.isPresent()) {
            throw new IllegalStateException("Name not found: " + name);
        }

        return navigateToEntry(entry.get());
    }

    /**
     * Navigates the listview to display and select the entry.
     */
    public EntryCardHandle navigateToEntry(ReadOnlyEntry entry) {
        int index = getEntryIndex(entry);

        guiRobot.interact(() -> {
            getListView().scrollTo(index);
            guiRobot.sleep(150);
            getListView().getSelectionModel().select(index);
        });
        guiRobot.sleep(100);
        return getEntryCardHandle(entry);
    }


    /**
     * Returns the position of the entry given, {@code NOT_FOUND} if not found in the list.
     */
    public int getEntryIndex(ReadOnlyEntry targetEntry) {
        List<ReadOnlyEntry> entrysInList = getListView().getItems();
        for (int i = 0; i < entrysInList.size(); i++) {
            if(entrysInList.get(i).getName().equals(targetEntry.getName())){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Gets a entry from the list by index
     */
    public ReadOnlyEntry getEntry(int index) {
        return getListView().getItems().get(index);
    }

    public EntryCardHandle getEntryCardHandle(int index) {
        return getEntryCardHandle(new Entry(getListView().getItems().get(index)));
    }

    public EntryCardHandle getEntryCardHandle(ReadOnlyEntry entry) {
        Set<Node> nodes = getAllCardNodes();
        Optional<Node> entryCardNode = nodes.stream()
                .filter(n -> new EntryCardHandle(guiRobot, primaryStage, n).isSameEntry(entry))
                .findFirst();
        if (entryCardNode.isPresent()) {
            return new EntryCardHandle(guiRobot, primaryStage, entryCardNode.get());
        } else {
            return null;
        }
    }

    protected Set<Node> getAllCardNodes() {
        return guiRobot.lookup(CARD_PANE_ID).queryAll();
    }

    public int getNumberOfPeople() {
        return getListView().getItems().size();
    }
}
