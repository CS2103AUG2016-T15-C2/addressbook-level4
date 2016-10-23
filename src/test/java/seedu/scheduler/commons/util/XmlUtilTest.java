package seedu.scheduler.commons.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.scheduler.model.Scheduler;
import seedu.scheduler.storage.XmlSerializableScheduler;
import seedu.scheduler.testutil.SchedulerBuilder;
import seedu.scheduler.testutil.TestUtil;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class XmlUtilTest {

    private static final String TEST_DATA_FOLDER = FileUtil.getPath("src/test/data/XmlUtilTest/");
    private static final File EMPTY_FILE = new File(TEST_DATA_FOLDER + "empty.xml");
    private static final File MISSING_FILE = new File(TEST_DATA_FOLDER + "missing.xml");
    private static final File VALID_FILE = new File(TEST_DATA_FOLDER + "validScheduler.xml");
    private static final File TEMP_FILE = new File(TestUtil.getFilePathInSandboxFolder("tempScheduler.xml"));

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getDataFromFile_nullFile_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.getDataFromFile(null, Scheduler.class);
    }

    @Test
    public void getDataFromFile_nullClass_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.getDataFromFile(VALID_FILE, null);
    }

    @Test
    public void getDataFromFile_missingFile_FileNotFoundException() throws Exception {
        thrown.expect(FileNotFoundException.class);
        XmlUtil.getDataFromFile(MISSING_FILE, Scheduler.class);
    }

    @Test
    public void getDataFromFile_emptyFile_DataFormatMismatchException() throws Exception {
        thrown.expect(JAXBException.class);
        XmlUtil.getDataFromFile(EMPTY_FILE, Scheduler.class);
    }

    @Test
    public void getDataFromFile_validFile_validResult() throws Exception {
        XmlSerializableScheduler dataFromFile = XmlUtil.getDataFromFile(VALID_FILE, XmlSerializableScheduler.class);
        assertEquals(9, dataFromFile.getEntryList().size());
        assertEquals(0, dataFromFile.getTagList().size());
    }

    @Test
    public void saveDataToFile_nullFile_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.saveDataToFile(null, new Scheduler());
    }

    @Test
    public void saveDataToFile_nullClass_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.saveDataToFile(VALID_FILE, null);
    }

    @Test
    public void saveDataToFile_missingFile_FileNotFoundException() throws Exception {
        thrown.expect(FileNotFoundException.class);
        XmlUtil.saveDataToFile(MISSING_FILE, new Scheduler());
    }

    @Test
    public void saveDataToFile_validFile_dataSaved() throws Exception {
        TEMP_FILE.createNewFile();
        XmlSerializableScheduler dataToWrite = new XmlSerializableScheduler(new Scheduler());
        XmlUtil.saveDataToFile(TEMP_FILE, dataToWrite);
        XmlSerializableScheduler dataFromFile = XmlUtil.getDataFromFile(TEMP_FILE, XmlSerializableScheduler.class);
        assertEquals((new Scheduler(dataToWrite)).toString(),(new Scheduler(dataFromFile)).toString());
        //TODO: use equality instead of string comparisons

        SchedulerBuilder builder = new SchedulerBuilder(new Scheduler());
        dataToWrite = new XmlSerializableScheduler(builder.withEntry(TestUtil.generateSampleEntryData().get(0)).withTag("Friends").build());

        XmlUtil.saveDataToFile(TEMP_FILE, dataToWrite);
        dataFromFile = XmlUtil.getDataFromFile(TEMP_FILE, XmlSerializableScheduler.class);
        assertEquals((new Scheduler(dataToWrite)).toString(),(new Scheduler(dataFromFile)).toString());
    }
}
