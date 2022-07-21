package com.mastery.java.task.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mastery.java.task.validation.AgeValidator;
import com.mastery.java.task.validation.NameValidator;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    @Positive(message = "id can only be a positive numbers")
    private Long employeeId;

    @Column(name = "first_name", nullable = false)
    @NameValidator(message = "name could consist of letters only")
    @NotBlank(message = "first name can not be blank")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NameValidator(message = "last name could consist of letters only")
    @NotBlank(message = "last name can not be blank")
    private String lastName;

    @NotNull(message = "department id can not be null value")
    @Column(name = "department_id", nullable = false)
    private Integer departmentId;

    @Column(name = "job_title", nullable = false)
    @NotBlank(message = "job title can not be blank")
    private String jobTitle;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "gender can not be null value")
    private Gender gender;

    @Column(name = "birthday_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @AgeValidator(message = "employees could be 18 years old only")
    private LocalDate birthdayDate;


}
