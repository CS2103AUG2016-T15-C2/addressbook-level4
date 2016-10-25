package seedu.scheduler.commons.events.storage;

import seedu.scheduler.commons.events.BaseEvent;

/**
 * Indicates a change in file path
 * @@author A0139956L
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
//@@author