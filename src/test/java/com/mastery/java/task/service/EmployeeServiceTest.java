package com.mastery.java.task.service;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.entity.Gender;
import com.mastery.java.task.exception.EmployeeServiceNotFoundException;
import com.mastery.java.task.repository.EmployeeRepository;
import com.mastery.java.task.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;
    Random random = new Random();

    @Test
    public void findAllEmployees() {
        Long presentId = random.nextLong();
        Employee employee = new Employee();
        employee.setEmployeeId(presentId);
        employee.setFirstName("first name");
        employee.setLastName("last name");
        employee.setDepartmentId(1);
        employee.setJobTitle("job title");
        employee.setGender(Gender.MALE);
        employee.setBirthdayDate(LocalDate.of(2000, 12, 13));

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        when(employeeRepository.findByFirstNameContainingAndLastNameContaining(employee.getFirstName(), employee.getLastName()))
                .thenReturn(employeeList);

        List<Employee> foundEmployees = employeeServiceImpl.findAllEmployees(employee.getFirstName(), employee.getLastName());
        assertEquals(foundEmployees, employeeList);
    }

    @Test
    public void findEmployeeByIdOrElseThrowNotFoundException() {
        Long absentId = random.nextLong();
        Long presentId = random.nextLong();
        Employee employee = new Employee(presentId, "first name", " last name",
                1, "job title 1", Gender.MALE, LocalDate.of(2001, 5, 3));

        when(employeeRepository.findById(presentId))
                .thenReturn(Optional.of(employee));

        Optional<Employee> foundEmployee = Optional.ofNullable(employeeServiceImpl.findEmployeeById(presentId));
        assertEquals(foundEmployee, Optional.of(employee));
        assertThrows(EmployeeServiceNotFoundException.class, () -> employeeServiceImpl.findEmployeeById(absentId));
    }

    @Test
    public void addEmployee() {
        Employee employee = new Employee(random.nextLong(), "first name", "last name",
                3, "job title", Gender.FEMALE, LocalDate.of(2000, 4, 17));

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee savedEmployee = employeeServiceImpl.addEmployee(employee);

        assertThat(savedEmployee).isEqualTo(employee);
    }

    @Test
    public void updateEmployee() {
        Long presentId = random.nextLong();
        Long absentId = random.nextLong();

        String updatedFirstName = "updated first name";
        String updatedLastName = "updated last name";
        int updatedDepartmentId = 5;
        String updatedJobTitle = "updated job title";
        Gender updatedGender = Gender.MALE;
        LocalDate updatedLocaleDate = LocalDate.of(2000, 12, 14);

        Employee employee = new Employee(presentId, "first name", "last name",
                4, "job title", Gender.FEMALE, LocalDate.of(2000, 4, 6));

        when(employeeRepository.findById(presentId)).thenReturn(Optional.of(employee));

        when(employeeRepository.findById(absentId))
                .thenThrow(new EmployeeServiceNotFoundException("there is no employee with such id " + absentId + "!"));

        Employee updatedEmployee = new Employee(presentId, updatedFirstName, updatedLastName
                , updatedDepartmentId, updatedJobTitle, updatedGender, updatedLocaleDate);

        when(employeeRepository.save(employee)).thenReturn(employee);

        employeeServiceImpl.updateEmployeeById(presentId, updatedEmployee);

        assertThrows(EmployeeServiceNotFoundException.class, () ->
                employeeServiceImpl.updateEmployeeById(absentId, updatedEmployee));

        assertThat(updatedEmployee.getFirstName()).isEqualTo(updatedFirstName);
        assertThat(updatedEmployee.getLastName()).isEqualTo(updatedLastName);
    }


    @Test
    public void deleteEmployeeOrThrowNotFoundException() {
        Long presentId = random.nextLong();

        employeeServiceImpl.deleteEmployee(presentId);

        Optional<Employee> deletedEmployee = employeeRepository.findById(presentId);

        verify(employeeRepository, times(1)).deleteById(presentId);
        assertThat(deletedEmployee).isNotPresent();
    }

}
