package raulgarcia.tasks.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import raulgarcia.tasks.TasksApplication;

public class TasksApplicationFX extends Application {
    private ConfigurableApplicationContext applicationContext;

    // static void main(String[] args) {
    //    launch(args);
    // }
    @Override
    public void init() {
        this.applicationContext = new SpringApplicationBuilder(TasksApplication.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(TasksApplication.class.getResource("/templates/index.fxml"));
        // Integrates JavaFX with Spring
        loader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Task Management System");
        stage.show();
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
}
