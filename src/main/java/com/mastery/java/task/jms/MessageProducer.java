package com.mastery.java.task.jms;

import com.mastery.java.task.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
@Slf4j
public class MessageProducer {

    private final JmsTemplate jmsTemplate;

    private Queue queue;


    public MessageProducer(JmsTemplate jmsTemplate, Queue queue) {
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
    }

    public void sendTo(Employee employee) {
        jmsTemplate.convertAndSend(queue, employee);
        log.info("Producer >> Message Sent, employee - {}, queue - {}", employee, queue);
    }
}
