package seedu.address.logic.commands;

import seedu.address.model.Scheduler;

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
public class ClearCommand extends Command {

	public static final String COMMAND_WORD = "clear";
	public static final String COMMAND_WORD2 = "c";
	public static final String MESSAGE_SUCCESS = "Scheduler has been cleared!";
	public static final String MESSAGE_STOPPED = "";

	private static Optional<ButtonType> result;
	
	public ClearCommand() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Look, a Confirmation Dialog");
		alert.setContentText("Are you ok with this?");

		result = alert.showAndWait();
	}

	@Override
	public CommandResult execute() {
		if (result.get() == ButtonType.OK) {
			assert model != null;
			model.resetData(Scheduler.getEmptyScheduler());
			return new CommandResult(MESSAGE_SUCCESS);
		} else {
			// ... user chose CANCEL or closed the dialog
			return new CommandResult(MESSAGE_STOPPED);
		}
	}
}
