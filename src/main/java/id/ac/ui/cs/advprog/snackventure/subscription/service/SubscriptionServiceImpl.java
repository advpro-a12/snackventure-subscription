package id.ac.ui.cs.advprog.snackventure.subscription.service;

import id.ac.ui.cs.advprog.snackventure.subscription.enums.ApprovalStatus;
import id.ac.ui.cs.advprog.snackventure.subscription.model.Subscription;
import id.ac.ui.cs.advprog.snackventure.subscription.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
        return subscription;
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription findById(String subscriptionId) {
        UUID id = UUID.fromString(subscriptionId);
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid subscription Id:" + subscriptionId));
    }

    @Override
    public Subscription updateApprovalStatus(String subscriptionId, String status) {
        UUID id = UUID.fromString(subscriptionId);
        Subscription subscription = this.findById(String.valueOf(id));
        if (status.equalsIgnoreCase(ApprovalStatus.APPROVED.toString())) {
            subscription.approve();
        } else if (status.equalsIgnoreCase(ApprovalStatus.REJECTED.toString())) {
            subscription.reject();
        } else {
            throw new IllegalArgumentException("Invalid status");
        }
        subscriptionRepository.save(subscription);
        return subscription;
    }

    @Override
    public Subscription cancelSubscription(String subscriptionId) {
        UUID id = UUID.fromString(subscriptionId);
        Subscription subscription = this.findById(String.valueOf(id));
        subscription.cancel();
        subscriptionRepository.save(subscription);
        return subscription;
    }
}
