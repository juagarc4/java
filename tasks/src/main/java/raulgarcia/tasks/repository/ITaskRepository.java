package raulgarcia.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raulgarcia.tasks.model.Task;

public interface ITaskRepository extends JpaRepository<Task, Integer> {
}