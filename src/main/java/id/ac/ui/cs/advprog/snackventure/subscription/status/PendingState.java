package id.ac.ui.cs.advprog.snackventure.subscription.status;

import id.ac.ui.cs.advprog.snackventure.subscription.enums.*;
import id.ac.ui.cs.advprog.snackventure.subscription.model.Subscription;

public class PendingState implements SubscriptionState {
    @Override
    public void approve(Subscription subscription) {
        subscription.setApprovalStatus(ApprovalStatus.APPROVED);
        subscription.setSubscriptionStatus(SubscriptionStatus.SUBSCRIBED);
        subscription.setState(new ApprovedState());
    }

    @Override
    public void reject(Subscription subscription) {
        subscription.setApprovalStatus(ApprovalStatus.REJECTED);
        subscription.setSubscriptionStatus(SubscriptionStatus.CANCELLED);
        subscription.setState(new RejectedState());
    }

    @Override
    public void cancel(Subscription subscription) {
        subscription.setSubscriptionStatus(SubscriptionStatus.CANCELLED);
        subscription.setState(new CancelledState());
    }
}