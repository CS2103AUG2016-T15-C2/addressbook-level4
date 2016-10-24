package seedu.scheduler.storage;

import seedu.scheduler.commons.exceptions.DataConversionException;
import seedu.scheduler.model.ReadOnlyScheduler;

import java.io.IOException;
import java.util.Optional;

/**
 * Represents a storage for {@link seedu.scheduler.model.Scheduler}.
 */
public interface SchedulerStorage {

    /**
     * Returns the file path of the data file.
     */
    String getSchedulerFilePath();

    /**
     * Returns Scheduler data as a {@link ReadOnlyScheduler}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyScheduler> readScheduler() throws DataConversionException, IOException;

    /**
     * @see #getSchedulerFilePath()
     */
    Optional<ReadOnlyScheduler> readScheduler(String filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyScheduler} to the storage.
     * @param scheduler cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveScheduler(ReadOnlyScheduler scheduler) throws IOException;

    /**
     * @see #saveScheduler(ReadOnlyScheduler)
     */
    void saveScheduler(ReadOnlyScheduler scheduler, String filePath) throws IOException;

}
