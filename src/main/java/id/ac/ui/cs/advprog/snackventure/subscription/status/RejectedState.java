package id.ac.ui.cs.advprog.snackventure.subscription.status;

import id.ac.ui.cs.advprog.snackventure.subscription.enums.*;
import id.ac.ui.cs.advprog.snackventure.subscription.model.Subscription;

public class RejectedState implements SubscriptionState {
    @Override
    public void approve(Subscription subscription) {
        // Cannot approve a rejected subscription
    }

    @Override
    public void reject(Subscription subscription) {
        // Already rejected
    }

    @Override
    public void cancel(Subscription subscription) {
        // Already cancelled
    }
}