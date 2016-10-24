package seedu.scheduler.storage;

import com.google.common.eventbus.Subscribe;
import seedu.scheduler.commons.core.ComponentManager;
import seedu.scheduler.commons.core.LogsCenter;
import seedu.scheduler.commons.events.model.SchedulerChangedEvent;
import seedu.scheduler.commons.events.storage.DataSavingExceptionEvent;
import seedu.scheduler.commons.exceptions.DataConversionException;
import seedu.scheduler.commons.util.FileUtil;
import seedu.scheduler.model.ReadOnlyScheduler;
import seedu.scheduler.model.UserPrefs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Manages storage of Scheduler data in local storage.
 */
public class StorageManager extends ComponentManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private XmlSchedulerStorage schedulerStorage;
    private JsonUserPrefsStorage userPrefsStorage;

    //@@author A0139956L
    public StorageManager(String schedulerFilePath, String userPrefsFilePath) {
        super();
        this.schedulerStorage = new XmlSchedulerStorage(schedulerFilePath);
        this.userPrefsStorage = new JsonUserPrefsStorage(userPrefsFilePath);    
    }
    //@@author

    // ================ UserPrefs methods ==============================

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Scheduler methods ==============================

    @Override
    public String getSchedulerFilePath() {
        return schedulerStorage.getSchedulerFilePath();
    }

    @Override
    public Optional<ReadOnlyScheduler> readScheduler() throws DataConversionException, IOException {
        return readScheduler(schedulerStorage.getSchedulerFilePath());
    }

    @Override
    public Optional<ReadOnlyScheduler> readScheduler(String filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return schedulerStorage.readScheduler(filePath);
    }
    
    //@@author A0139956L
    @Override
    public void saveScheduler(ReadOnlyScheduler scheduler) throws IOException {
    	schedulerStorage.saveScheduler(scheduler, schedulerStorage.getSchedulerFilePath());
    }

    @Override
    public void saveScheduler(ReadOnlyScheduler scheduler, String filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        schedulerStorage.saveScheduler(scheduler, filePath);
    }
    

    @Override
    public void setFilePath(String pathFile) {
    	this.schedulerStorage = new XmlSchedulerStorage(pathFile);
    }
    //@@author

    @Override
    @Subscribe
    public void handleSchedulerChangedEvent(SchedulerChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Local data changed, saving to file"));
        try {
            saveScheduler(event.data);
        } catch (IOException e) {
            raise(new DataSavingExceptionEvent(e));
        }
    }
}
