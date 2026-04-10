package raulgarcia.employees.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    @NotEmpty(message = "First Name is required")
    private String firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "Last Name is required")
    private String lastName;
    @NotEmpty(message = "Department is required")
    private String department;
    @NotEmpty(message = "Position is required")
    private String position;
    @Min(value = 0, message = "Salary must be higher than 0")
    private Double salary;

    public void assignIdForUpdate(Integer id) {
        this.id = id;
    }
}
