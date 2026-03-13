package raulgarcia.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raulgarcia.employees.model.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
}