package seedu.address.storage;

import seedu.address.commons.util.XmlUtil;
import seedu.address.commons.exceptions.DataConversionException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Stores scheduler data in an XML file
 */
public class XmlFileStorage {
    /**
     * Saves the given scheduler data to the specified file.
     */
    public static void saveDataToFile(File file, XmlSerializableScheduler scheduler)
            throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, scheduler);
        } catch (JAXBException e) {
            assert false : "Unexpected exception " + e.getMessage();
        }
    }

    /**
     * Returns scheduler in the file or an empty scheduler
     */
    public static XmlSerializableScheduler loadDataFromSaveFile(File file) throws DataConversionException,
                                                                            FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, XmlSerializableScheduler.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }

}
