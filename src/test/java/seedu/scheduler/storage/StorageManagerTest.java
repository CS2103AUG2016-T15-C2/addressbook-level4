package seedu.scheduler.storage;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import seedu.scheduler.commons.events.model.SchedulerChangedEvent;
import seedu.scheduler.commons.events.storage.DataSavingExceptionEvent;
import seedu.scheduler.model.Scheduler;
import seedu.scheduler.model.ReadOnlyScheduler;
import seedu.scheduler.model.UserPrefs;
import seedu.scheduler.testutil.TypicalTestEntrys;
import seedu.scheduler.testutil.EventsCollector;

import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StorageManagerTest {

    private StorageManager storageManager;

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();


    @Before
    public void setup() {
        storageManager = new StorageManager(getTempFilePath("ab"), getTempFilePath("prefs"));
    }


    private String getTempFilePath(String fileName) {
        return testFolder.getRoot().getPath() + fileName;
    }


    /*
     * Note: This is an integration test that verifies the StorageManager is properly wired to the
     * {@link JsonUserPrefsStorage} class.
     * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
     */

    @Test
    public void prefsReadSave() throws Exception {
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(300, 600, 4, 6);
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void schedulerReadSave() throws Exception {
        Scheduler original = new TypicalTestEntrys().getTypicalScheduler();
        storageManager.saveScheduler(original);
        ReadOnlyScheduler retrieved = storageManager.readScheduler().get();
        assertEquals(original, new Scheduler(retrieved));
        //More extensive testing of Scheduler saving/reading is done in XmlSchedulerStorageTest
    }

    @Test
    public void getSchedulerFilePath(){
        assertNotNull(storageManager.getSchedulerFilePath());
    }
/*
    @Test
    public void handleSchedulerChangedEvent_exceptionThrown_eventRaised() throws IOException {
        //Create a StorageManager while injecting a stub that throws an exception when the save method is called
        Storage storage = new StorageManager(new XmlSchedulerStorageExceptionThrowingStub("dummy"), new JsonUserPrefsStorage("dummy"));
        EventsCollector eventCollector = new EventsCollector();
        storage.handleSchedulerChangedEvent(new SchedulerChangedEvent(new Scheduler()));
        assertTrue(eventCollector.get(0) instanceof DataSavingExceptionEvent);
    }
*/

    /**
     * A Stub class to throw an exception when the save method is called
     */
    class XmlSchedulerStorageExceptionThrowingStub extends XmlSchedulerStorage{

        public XmlSchedulerStorageExceptionThrowingStub(String filePath) {
            super(filePath);
        }

        @Override
        public void saveScheduler(ReadOnlyScheduler scheduler, String filePath) throws IOException {
            throw new IOException("dummy exception");
        }
    }


}
