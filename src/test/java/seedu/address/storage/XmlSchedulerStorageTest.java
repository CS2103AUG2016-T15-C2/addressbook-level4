package seedu.address.storage;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.Scheduler;
import seedu.address.model.ReadOnlyScheduler;
import seedu.address.model.entry.Entry;
import seedu.address.testutil.TypicalTestEntrys;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class XmlSchedulerStorageTest {
    private static String TEST_DATA_FOLDER = FileUtil.getPath("./src/test/data/XmlSchedulerStorageTest/");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void readScheduler_nullFilePath_assertionFailure() throws Exception {
        thrown.expect(AssertionError.class);
        readScheduler(null);
    }

    private java.util.Optional<ReadOnlyScheduler> readScheduler(String filePath) throws Exception {
        return new XmlSchedulerStorage(filePath).readScheduler(addToTestDataPathIfNotNull(filePath));
    }

    private String addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER + prefsFileInTestDataFolder
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readScheduler("NonExistentFile.xml").isPresent());
    }

    @Test
    public void read_notXmlFormat_exceptionThrown() throws Exception {

        thrown.expect(DataConversionException.class);
        readScheduler("NotXmlFormatScheduler.xml");

        /* IMPORTANT: Any code below an exception-throwing line (like the one above) will be ignored.
         * That means you should not have more than one exception test in one method
         */
    }

    @Test
    public void readAndSaveScheduler_allInOrder_success() throws Exception {
        String filePath = testFolder.getRoot().getPath() + "TempScheduler.xml";
        TypicalTestEntrys td = new TypicalTestEntrys();
        Scheduler original = td.getTypicalScheduler();
        XmlSchedulerStorage xmlSchedulerStorage = new XmlSchedulerStorage(filePath);

        //Save in new file and read back
        xmlSchedulerStorage.saveScheduler(original, filePath);
        ReadOnlyScheduler readBack = xmlSchedulerStorage.readScheduler(filePath).get();
        assertEquals(original, new Scheduler(readBack));

        //Modify data, overwrite exiting file, and read back
        original.addEntry(new Entry(TypicalTestEntrys.meeting));
        original.removeEntry(new Entry(TypicalTestEntrys.chem));
        xmlSchedulerStorage.saveScheduler(original, filePath);
        readBack = xmlSchedulerStorage.readScheduler(filePath).get();
        assertEquals(original, new Scheduler(readBack));

        //Save and read without specifying file path
        original.addEntry(new Entry(TypicalTestEntrys.pickup));
        xmlSchedulerStorage.saveScheduler(original); //file path not specified
        readBack = xmlSchedulerStorage.readScheduler().get(); //file path not specified
        assertEquals(original, new Scheduler(readBack));

    }

    @Test
    public void saveScheduler_nullScheduler_assertionFailure() throws IOException {
        thrown.expect(AssertionError.class);
        saveScheduler(null, "SomeFile.xml");
    }

    private void saveScheduler(ReadOnlyScheduler scheduler, String filePath) throws IOException {
        new XmlSchedulerStorage(filePath).saveScheduler(scheduler, addToTestDataPathIfNotNull(filePath));
    }

    @Test
    public void saveScheduler_nullFilePath_assertionFailure() throws IOException {
        thrown.expect(AssertionError.class);
        saveScheduler(new Scheduler(), null);
    }


}
