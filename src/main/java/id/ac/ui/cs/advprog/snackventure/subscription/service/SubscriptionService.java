package id.ac.ui.cs.advprog.snackventure.subscription.service;

import id.ac.ui.cs.advprog.snackventure.subscription.model.Subscription;
import java.util.List;

public interface SubscriptionService {
    public Subscription createSubscription(Subscription subscription);
    public List<Subscription> getAllSubscriptions();
    public Subscription findById(String subscriptionId);
    public Subscription updateApprovalStatus(String subscriptionId, String status);
    public Subscription cancelSubscription(String subscriptionId, String status);
}
