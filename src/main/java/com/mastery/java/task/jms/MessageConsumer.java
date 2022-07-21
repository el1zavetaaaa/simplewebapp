package com.mastery.java.task.jms;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    private final EmployeeRepository employeeRepository;

    public MessageConsumer(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @JmsListener(destination = "employee-service-queue")
    public void processMessage(Employee employee){
        log.info("Consumer >> Employee received {} ", employee);
        employeeRepository.save(employee);
    }
}
