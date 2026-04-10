package raulgarcia.employees.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raulgarcia.employees.model.Employee;
import raulgarcia.employees.repository.IEmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);


    @Override
    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        try {
            Employee employeeSaved = employeeRepository.save(employee);
            logger.info("Employee with ID {} saved successfully", employeeSaved.getId());
            return employeeSaved;
        } catch (Exception e) {
            logger.error("Database error while saving employee: {}", e.getMessage());
            throw new RuntimeException("Error saving used in the database", e);
        }
    }

    @Override
    public void deleteEmployeeById(Integer id) {

        employeeRepository.deleteById(id);
    }


}
