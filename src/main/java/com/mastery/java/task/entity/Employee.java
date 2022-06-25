package com.mastery.java.task.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mastery.java.task.validation.AgeValidator;
import com.mastery.java.task.validation.NameValidator;
import lombok.*;

import javax.persistence.*;
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
    private Long employeeId;

    @Column(name = "first_name", nullable = false)
    @NameValidator
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NameValidator
    private String lastName;

    @Column(name = "department_id", nullable = false)
    private Integer departmentId;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birthday_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @AgeValidator
    private LocalDate birthdayDate;


}
