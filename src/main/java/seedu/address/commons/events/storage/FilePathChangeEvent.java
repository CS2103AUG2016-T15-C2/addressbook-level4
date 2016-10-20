package seedu.address.commons.events.storage;

import seedu.address.commons.events.BaseEvent;

/**
 * Indicates a change in file path
 */
public class FilePathChangeEvent extends BaseEvent {
	
	public String path;

    public FilePathChangeEvent(String path) {
        this.path = path;
    }

    @Override
    public String toString(){
        return this.path;
    }
}
