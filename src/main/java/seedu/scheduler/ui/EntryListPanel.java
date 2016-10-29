package seedu.scheduler.ui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.scheduler.commons.events.ui.EntryPanelSelectionChangedEvent;
import seedu.scheduler.model.entry.ReadOnlyEntry;
import seedu.scheduler.commons.core.LogsCenter;

import java.util.logging.Logger;

/**
 * Panel containing the list of entrys.
 */
public class EntryListPanel extends UiPart {
    private final Logger logger = LogsCenter.getLogger(EntryListPanel.class);
    private static final String FXML = "EntryListPanel.fxml";
    private VBox panel;
    private AnchorPane placeHolderPane;

    @FXML
    private ListView<ReadOnlyEntry> entryListView;

    public EntryListPanel() {
        super();
    }

    @Override
    public void setNode(Node node) {
        panel = (VBox) node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }

    @Override
    public void setPlaceholder(AnchorPane pane) {
        this.placeHolderPane = pane;
    }

    public static EntryListPanel load(Stage primaryStage, AnchorPane entryListPlaceholder,
                                       ObservableList<ReadOnlyEntry> entryList) {
        EntryListPanel entryListPanel =
                UiPartLoader.loadUiPart(primaryStage, entryListPlaceholder, new EntryListPanel());
        entryListPanel.configure(entryList);
        return entryListPanel;
    }

    private void configure(ObservableList<ReadOnlyEntry> entryList) {
        setConnections(entryList);
        addToPlaceholder();
    }

    private void setConnections(ObservableList<ReadOnlyEntry> entryList) {
        entryListView.setItems(entryList);
        entryListView.setCellFactory(listView -> new EntryListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void addToPlaceholder() {
        SplitPane.setResizableWithParent(placeHolderPane, false);
        placeHolderPane.getChildren().add(panel);
    }

    private void setEventHandlerForSelectionChangeEvent() {
        entryListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                logger.fine("Selection in entry list panel changed to : '" + newValue + "'");
                raise(new EntryPanelSelectionChangedEvent(newValue));
            }
        });
    }

    public void scrollTo(int index) {
        Platform.runLater(() -> {
            entryListView.scrollTo(index);
            entryListView.getSelectionModel().clearAndSelect(index);
        });
    }

    class EntryListViewCell extends ListCell<ReadOnlyEntry> {

        public EntryListViewCell() {
        }

        @Override
        protected void updateItem(ReadOnlyEntry entry, boolean empty) {
            super.updateItem(entry, empty);

            if (empty || entry == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(EntryCard.load(entry, getIndex() + 1).getLayout());
            }
        }
    }

}
