package com.application.messageq.publisher;


import com.application.messageq.config.MsgConfiguration;
import com.application.messageq.dto.Dept;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MsgPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/post")
    public Dept Publish(@RequestBody Dept dept, @RequestParam(required = false) int priority){
        Dept dept1 = new Dept();
        dept1.setDeptname(dept.getDeptname());
        dept1.setAvailseats(dept.getAvailseats());
        dept1.setTotal(dept.getTotal());


        rabbitTemplate.convertAndSend(MsgConfiguration.Exchange,MsgConfiguration.key,dept1,message -> {
            message.getMessageProperties().setPriority(priority);
            return message;
        });
        return dept1;
    }

    @GetMapping("/receive")
    public ResponseEntity<?> receive() {
        Message message = rabbitTemplate.receive("demo");

        if(message!=null){
            MessageConverter converter =  rabbitTemplate.getMessageConverter();
            Dept dept = (Dept) converter.fromMessage(message);

            return ResponseEntity.ok(dept);
        }else {
            return ResponseEntity.internalServerError().body("error");
        }
    }
}
