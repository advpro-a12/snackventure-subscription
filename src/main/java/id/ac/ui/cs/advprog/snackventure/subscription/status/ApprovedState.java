package id.ac.ui.cs.advprog.snackventure.subscription.status;

import id.ac.ui.cs.advprog.snackventure.subscription.enums.*;
import id.ac.ui.cs.advprog.snackventure.subscription.model.Subscription;

public class ApprovedState implements SubscriptionState {
    @Override
    public void approve(Subscription subscription) {
        // Already approved
    }

    @Override
    public void reject(Subscription subscription) {
        // Cannot reject an approved subscription
    }

    @Override
    public void cancel(Subscription subscription) {
        subscription.setSubscriptionStatus(SubscriptionStatus.CANCELLED);
        subscription.setState(new CancelledState());
    }
}