package id.ac.ui.cs.advprog.snackventure.subscription.controller;

import id.ac.ui.cs.advprog.snackventure.subscription.model.Subscription;
import id.ac.ui.cs.advprog.snackventure.subscription.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/subscription-box/{id}/subscribe")
    public ResponseEntity<Subscription> createSubscription(@PathVariable String id, @RequestBody Map<String, String> requestBody) {
        String frequency = requestBody.get("frequency");
        String customerId = requestBody.get("customerId");
        Subscription createdSubscription = subscriptionService.createSubscription(frequency, id, customerId);
        return ResponseEntity.ok(createdSubscription);
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }

    @GetMapping("subscriptions/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable String id) {
        Subscription subscription = subscriptionService.findById(id);
        return ResponseEntity.ok(subscription);
    }

    @PutMapping("subscriptions/{id}/change-status")
    public ResponseEntity<Subscription> updateApprovalStatus(@PathVariable String id, @RequestBody Map<String, String> requestBody) {
        String status = requestBody.get("status");
        Subscription updatedSubscription = subscriptionService.updateApprovalStatus(id, status);
        return ResponseEntity.ok(updatedSubscription);
    }

    @PutMapping("subscriptions/{id}/unsubscribe")
    public ResponseEntity<Subscription> cancelSubscription(@PathVariable String id) {
        Subscription cancelledSubscription = subscriptionService.cancelSubscription(id);
        return ResponseEntity.ok(cancelledSubscription);
    }
}