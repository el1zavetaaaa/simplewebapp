package com.mastery.java.task.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
public class Employee {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private Integer departmentId;
    private String jobTitle;
    private Gender genderId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthdayDate;

}
