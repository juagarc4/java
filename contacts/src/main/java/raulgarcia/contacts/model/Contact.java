package raulgarcia.contacts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    @NotEmpty(message = "First Name is required")
    private String firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "Last Name is required")
    private String lastName;
    private String phone;
    private String email;

}

