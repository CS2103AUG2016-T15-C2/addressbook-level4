package seedu.scheduler.ui;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import seedu.scheduler.MainApp;
import seedu.scheduler.commons.core.ComponentManager;
import seedu.scheduler.commons.core.Config;
import seedu.scheduler.commons.core.LogsCenter;
import seedu.scheduler.commons.events.storage.DataSavingExceptionEvent;
import seedu.scheduler.commons.events.ui.JumpToListRequestEvent;
import seedu.scheduler.commons.events.ui.EntryPanelSelectionChangedEvent;
import seedu.scheduler.commons.events.ui.ShowHelpRequestEvent;
import seedu.scheduler.commons.util.StringUtil;
import seedu.scheduler.logic.Logic;
import seedu.scheduler.model.UserPrefs;

import java.util.logging.Logger;

/**
 * The manager of the UI component.
 */
public class UiManager extends ComponentManager implements Ui {
    private static final Logger logger = LogsCenter.getLogger(UiManager.class);
    private static final String ICON_APPLICATION = "/images/calendar.png";

    private Logic logic;
    private Config config;
    private UserPrefs prefs;
    private MainWindow mainWindow;

    public UiManager(Logic logic, Config config, UserPrefs prefs) {
        super();
        this.logic = logic;
        this.config = config;
        this.prefs = prefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting UI...");
        primaryStage.setTitle(config.getAppTitle());

        //Set the application icon.
        primaryStage.getIcons().add(getImage(ICON_APPLICATION));

        try {
            mainWindow = MainWindow.load(primaryStage, config, prefs, logic);
            mainWindow.show(); //This should be called before creating other UI parts
            mainWindow.fillInnerParts();

        } catch (Throwable e) {
            logger.severe(StringUtil.getDetails(e));
            showFatalErrorDialogAndShutdown("Fatal error during initializing", e);
        }
    }

    @Override
    public void stop() {
        prefs.updateLastUsedGuiSetting(mainWindow.getCurrentGuiSetting());
        mainWindow.hide();
        mainWindow.releaseResources();
    }

    private void showFileOperationAlertAndWait(String description, String details, Throwable cause) {
        final String content = details + ":\n" + cause.toString();
        showAlertDialogAndWait(AlertType.ERROR, "File Op Error", description, content);
    }

    private Image getImage(String imagePath) {
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    void showAlertDialogAndWait(Alert.AlertType type, String title, String headerText, String contentText) {
        showAlertDialogAndWait(mainWindow.getPrimaryStage(), type, title, headerText, contentText);
    }

    private static void showAlertDialogAndWait(Stage owner, AlertType type, String title, String headerText,
                                               String contentText) {
        final Alert alert = new Alert(type);
        alert.getDialogPane().getStylesheets().add("view/DarkTheme.css");
        alert.initOwner(owner);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    private void showFatalErrorDialogAndShutdown(String title, Throwable e) {
        logger.severe(title + " " + e.getMessage() + StringUtil.getDetails(e));
        showAlertDialogAndWait(Alert.AlertType.ERROR, title, e.getMessage(), e.toString());
        Platform.exit();
        System.exit(1);
    }

    //==================== Event Handling Code =================================================================

    @Subscribe
    private void handleDataSavingExceptionEvent(DataSavingExceptionEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        showFileOperationAlertAndWait("Could not save data", "Could not save data to file", event.exception);
    }

    @Subscribe
    private void handleShowHelpEvent(ShowHelpRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        mainWindow.handleHelp();
    }

    @Subscribe
    private void handleJumpToListRequestEvent(JumpToListRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        mainWindow.getEntryListPanel().scrollTo(event.targetIndex);
    }

    @Subscribe
    private void handleEntryPanelSelectionChangedEvent(EntryPanelSelectionChangedEvent event){
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        mainWindow.loadEntryPage(event.getNewSelection());
    }

}
