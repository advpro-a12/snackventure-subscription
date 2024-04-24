package id.ac.ui.cs.advprog.snackventure.subscription.service;

import id.ac.ui.cs.advprog.snackventure.subscription.model.Subscription;
import id.ac.ui.cs.advprog.snackventure.subscription.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public Subscription updateApprovalStatus(String subscriptionId, String status) {
        return null;
    }

    @Override
    public Subscription cancelSubscription(String subscriptionId, String status) {
        return null;
    }
}
