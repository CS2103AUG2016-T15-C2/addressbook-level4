package seedu.scheduler.logic.commands;

import seedu.scheduler.model.ReadOnlyScheduler;
import seedu.scheduler.model.Scheduler;

import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Clears the scheduler.
 */
public class ClearCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_WORD2 = "c";
    public static final String MESSAGE_SUCCESS = "Scheduler has been cleared!";
    public static final String MESSAGE_STOPPED = "";

    private static Optional<ButtonType> result;
    private ReadOnlyScheduler lastScheduler;

    // @@author A0126090N
    public ClearCommand() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Clearing the list will remove all data");
        alert.setContentText("Proceed to clear the list?");

        result = alert.showAndWait();
    }

    @Override
    public CommandResult execute() {
        if (result.get() == ButtonType.OK) {
            assert model != null;
            lastScheduler = new Scheduler(model.getScheduler());
            model.resetData(Scheduler.getEmptyScheduler());
            undoManager.stackCommand(this);
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            // ... user chose CANCEL or closed the dialog
            return new CommandResult(MESSAGE_STOPPED);
        }
    }
    // @@author

    @Override
    public void undo() {
        model.resetData(lastScheduler);
    }

    @Override
    public void redo() {
        model.resetData(Scheduler.getEmptyScheduler());
    }
}
