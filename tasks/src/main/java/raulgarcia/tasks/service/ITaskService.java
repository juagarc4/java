package raulgarcia.tasks.service;

import raulgarcia.tasks.model.Task;

import java.util.List;

public interface ITaskService {
    List<Task> listTasks();

    Task searchTaskById(Integer taskId);

    void saveTask(Task task);

    void deleteTask(Task task);
}
