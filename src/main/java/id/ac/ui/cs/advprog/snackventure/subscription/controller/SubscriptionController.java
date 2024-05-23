package id.ac.ui.cs.advprog.snackventure.subscription.controller;

import id.ac.ui.cs.advprog.snackventure.subscription.model.Subscription;
import id.ac.ui.cs.advprog.snackventure.subscription.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@EnableAsync
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Async
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/subscription-box/{id}/subscribe")
    public CompletableFuture<ResponseEntity<Subscription>> createSubscription(@PathVariable String id, @RequestBody Map<String, String> requestBody) {
        String frequency = requestBody.get("frequency");
        String customerId = requestBody.get("customerId");
        Subscription createdSubscription = subscriptionService.createSubscription(frequency, id, customerId);
        return CompletableFuture.completedFuture(ResponseEntity.ok(createdSubscription));
    }

    @Async
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/subscriptions")
    public CompletableFuture<ResponseEntity<List<Subscription>>> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        return CompletableFuture.completedFuture(ResponseEntity.ok(subscriptions));
    }

    @Async
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("subscriptions/{id}")
    public CompletableFuture<ResponseEntity<Subscription>> getSubscriptionById(@PathVariable String id) {
        Subscription subscription = subscriptionService.findById(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok(subscription));
    }

    @Async
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("subscriptions/{id}/change-status")
    public CompletableFuture<ResponseEntity<Subscription>> updateApprovalStatus(@PathVariable String id, @RequestBody Map<String, String> requestBody) {
        String status = requestBody.get("status");
        Subscription updatedSubscription = subscriptionService.updateApprovalStatus(id, status);
        return CompletableFuture.completedFuture(ResponseEntity.ok(updatedSubscription));
    }

    @Async
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PutMapping("subscriptions/{id}/unsubscribe")
    public CompletableFuture<ResponseEntity<Subscription>> cancelSubscription(@PathVariable String id) {
        Subscription cancelledSubscription = subscriptionService.cancelSubscription(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok(cancelledSubscription));
    }
}