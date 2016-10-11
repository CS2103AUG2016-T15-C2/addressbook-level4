package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.entry.ReadOnlyEntry;

/**
 * Represents a selection change in the Entry List Panel
 */
public class EntryPanelSelectionChangedEvent extends BaseEvent {


    private final ReadOnlyEntry newSelection;

    public EntryPanelSelectionChangedEvent(ReadOnlyEntry newSelection){
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public ReadOnlyEntry getNewSelection() {
        return newSelection;
    }
}
