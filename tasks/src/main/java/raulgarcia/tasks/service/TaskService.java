package raulgarcia.tasks.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import raulgarcia.tasks.model.Task;
import raulgarcia.tasks.repository.ITaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    private final ITaskRepository taskRepository;
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Override
    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task searchTaskById(Integer taskId) {
        return taskRepository
                .findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task with id " + taskId + " not found."));
    }

    @Override
    public void saveTask(Task task) {
        try {
            taskRepository.save(task);
            logger.info("Task saved successfully");
        } catch (Exception e) {
            throw new RuntimeException("Error saving task" + e.getMessage());
        }
    }

    @Override
    public void deleteTask(Task task) {
        try {
            taskRepository.delete(task);
            logger.info("Task deleted successfully");
        } catch (Exception e) {
            throw new RuntimeException("Error deleting task" + e.getMessage());
        }
    }
}
