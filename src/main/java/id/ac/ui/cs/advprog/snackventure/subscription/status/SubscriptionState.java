package id.ac.ui.cs.advprog.snackventure.subscription.status;

import id.ac.ui.cs.advprog.snackventure.subscription.model.Subscription;

public interface SubscriptionState {
    void approve(Subscription subscription);
    void reject(Subscription subscription);
    void cancel(Subscription subscription);
}