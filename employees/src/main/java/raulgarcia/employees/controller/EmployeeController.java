package raulgarcia.employees.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import raulgarcia.employees.model.Employee;
import raulgarcia.employees.service.IEmployeeService;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final IEmployeeService employeeService;

    @GetMapping({"", "/"})
    public String index(ModelMap model) {
        model.addAttribute("employees", this.employeeService.listEmployees());
        return "employees/index";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {
        model.addAttribute("employee", new Employee());
        return "employees/add";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("employee") Integer id, ModelMap model) {
        Employee employee = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR EDITING: Employee not found."));
        model.addAttribute("employee", employee);
        return "employees/edit";

    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return (employee.getId() != null) ? "employees/edit" : "employees/add";
        }
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("employee") Integer id) {
        Employee employee = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}
