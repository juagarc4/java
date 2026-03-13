package raulgarcia.employees.service;

import raulgarcia.employees.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<Employee> listEmployees();

    Optional<Employee> getEmployeeById(Integer id);

    void saveEmployee(Employee employee);

    void deleteEmployeeById(Integer id);

}
