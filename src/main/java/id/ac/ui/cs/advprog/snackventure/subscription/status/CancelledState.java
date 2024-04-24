package id.ac.ui.cs.advprog.snackventure.subscription.status;

import id.ac.ui.cs.advprog.snackventure.subscription.enums.*;
import id.ac.ui.cs.advprog.snackventure.subscription.model.Subscription;

public class CancelledState implements SubscriptionState {
    @Override
    public void approve(Subscription subscription) {
        // Cannot approve a cancelled subscription
    }

    @Override
    public void reject(Subscription subscription) {
        // Cannot reject a cancelled subscription
    }

    @Override
    public void cancel(Subscription subscription) {
        // Already cancelled
    }
}