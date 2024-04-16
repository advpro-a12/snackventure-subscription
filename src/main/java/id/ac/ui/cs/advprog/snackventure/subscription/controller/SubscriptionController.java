package id.ac.ui.cs.advprog.snackventure.subscription.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class SubscriptionController {
    @GetMapping("/")
    public String subscription() {
        return "Hello from subscription!";
    }
}
