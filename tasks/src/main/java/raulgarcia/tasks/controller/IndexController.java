package raulgarcia.tasks.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import raulgarcia.tasks.model.Task;
import raulgarcia.tasks.service.TaskService;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TaskService taskService;

    @FXML
    private TableView<Task> tasksTable;

    @FXML
    private TableColumn<Task, Integer> idColumn;

    @FXML
    private TableColumn<Task, String> titleColumn;

    @FXML
    private TableColumn<Task, String> assignedToColumn;

    @FXML
    private TableColumn<Task, String> statusColumn;

    @FXML
    private TableColumn<Task, LocalDateTime> createdAtColumn;

    private final ObservableList<Task> taskList = FXCollections.observableArrayList();

    @FXML
    private TextField nameField;
    @FXML
    private TextField assignedToField;
    @FXML
    private ComboBox<String> statusField;
    @FXML
    private TextField searchField;

    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;

    private Task selectedTask;
    private FilteredList<Task> filteredData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        configButtons();
        configTable();
        configTableColumns();
        configTableRows();
        configSearchField();
        configStatusField();
        loadTasks();
    }

    private void configButtons() {
        btnDelete.disableProperty().bind(tasksTable.getSelectionModel().selectedItemProperty().isNull());
    }

    private void configTable() {
        tasksTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tasksTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nameField.setText(newSelection.getTitle());
                assignedToField.setText(newSelection.getAssignedTo());
                statusField.setValue(newSelection.getStatus());
                this.selectedTask = newSelection;
            }
        });
    }

    private void configSearchField() {
        filteredData = new FilteredList<>(taskList, _ -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(task -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                return task.getTitle().toLowerCase().contains(lowerCaseFilter);
            });
        });

        tasksTable.setItems(filteredData);
    }

    private void configStatusField() {
        statusField.setItems(FXCollections.observableArrayList(
                "To Do",
                "In Progress",
                "Done",
                "Canceled"
        ));
        statusField.getSelectionModel().selectFirst();
    }

    private void configTableRows() {
        tasksTable.setRowFactory(tv -> new TableRow<Task>() {
            @Override
            protected void updateItem(Task item, boolean empty) {
                super.updateItem(item, empty);
                getStyleClass().remove("done");

                if (!empty && item != null && "Done".equals(item.getStatus())) {
                    getStyleClass().add("done");
                }
            }
        });
    }

    private void configTableColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        assignedToColumn.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        createdAtColumn.setCellFactory(column -> new TableCell<Task, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatter.format(item));
                }
            }
        });

    }

    private void loadTasks() {
        taskList.setAll(taskService.listTasks());
    }

    public void saveTask() {
        if (nameField.getText().isEmpty()) {
            showError("Error", "Field 'title' is required");
            return;
        }
        if (assignedToField.getText().isEmpty()) {
            showError("Error", "Field 'Assigned to' is required");
            return;
        }

        Task task = new Task();
        if (this.selectedTask != null) {
            task = this.selectedTask;
        }
        task.setTitle(nameField.getText());
        task.setAssignedTo(assignedToField.getText());
        task.setStatus(statusField.getValue());

        taskService.saveTask(task);

        clearFields();
        loadTasks();
    }

    public void deleteTask() {
        if (this.selectedTask == null) {
            showError("\"Error\"", "To delete a task, please select one of the table");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Task");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("You are about to delete: " + this.selectedTask.getTitle());
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    taskService.deleteTask(this.selectedTask);
                    this.selectedTask = null;
                    clearFields();
                    loadTasks();
                });
    }

    public void clearFields() {
        searchField.clear();
        nameField.clear();
        assignedToField.clear();
        statusField.getSelectionModel().selectFirst();
        tasksTable.getSelectionModel().clearSelection();
        this.selectedTask = null;
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
