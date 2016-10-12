package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.ReadOnlyScheduler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * A class to access Scheduler data stored as an xml file on the hard disk.
 */
public class XmlSchedulerStorage implements SchedulerStorage {

    private static final Logger logger = LogsCenter.getLogger(XmlSchedulerStorage.class);

    private String filePath;

    public XmlSchedulerStorage(String filePath){
        this.filePath = filePath;
    }

    public String getSchedulerFilePath(){
        return filePath;
    }

    /**
     * Similar to {@link #readScheduler()}
     * @param filePath location of the data. Cannot be null
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyScheduler> readScheduler(String filePath) throws DataConversionException, FileNotFoundException {
        assert filePath != null;

        File schedulerFile = new File(filePath);

        if (!schedulerFile.exists()) {
            logger.info("Scheduler file "  + schedulerFile + " not found");
            return Optional.empty();
        }

        ReadOnlyScheduler schedulerOptional = XmlFileStorage.loadDataFromSaveFile(new File(filePath));

        return Optional.of(schedulerOptional);
    }

    /**
     * Similar to {@link #saveScheduler(ReadOnlyScheduler)}
     * @param filePath location of the data. Cannot be null
     */
    public void saveScheduler(ReadOnlyScheduler scheduler, String filePath) throws IOException {
        assert scheduler != null;
        assert filePath != null;

        File file = new File(filePath);
        FileUtil.createIfMissing(file);
        XmlFileStorage.saveDataToFile(file, new XmlSerializableScheduler(scheduler));
    }

    @Override
    public Optional<ReadOnlyScheduler> readScheduler() throws DataConversionException, IOException {
        return readScheduler(filePath);
    }

    @Override
    public void saveScheduler(ReadOnlyScheduler scheduler) throws IOException {
        saveScheduler(scheduler, filePath);
    }
}
