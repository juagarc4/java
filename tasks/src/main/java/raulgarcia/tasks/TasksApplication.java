package raulgarcia.tasks;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import raulgarcia.tasks.view.TasksApplicationFX;

@SpringBootApplication
public class TasksApplication {

    static void main(String[] args) {

        //SpringApplication.run(TasksApplication.class, args);
        Application.launch(TasksApplicationFX.class, args);
    }

}
