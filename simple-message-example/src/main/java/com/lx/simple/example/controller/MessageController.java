package com.lx.simple.example.controller;

import com.lx.simple.example.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    public MessageProducer producer;

    @RequestMapping(value = "/api/mq/send_message")
    public String sendMessage(@RequestParam String message) {
        producer.sendMessage(message);
        return "send success";
    }
}
