package id.ac.ui.cs.advprog.snackventure.subscription.service;

import id.ac.ui.cs.advprog.snackventure.subscription.enums.ApprovalStatus;
import id.ac.ui.cs.advprog.snackventure.subscription.enums.DeliveryFrequency;
import id.ac.ui.cs.advprog.snackventure.subscription.enums.SubscriptionStatus;
import id.ac.ui.cs.advprog.snackventure.subscription.model.Subscription;
import id.ac.ui.cs.advprog.snackventure.subscription.repository.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceImplTest {

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSubscription() {
        String frequency = DeliveryFrequency.MONTHLY.getValue();
        String subscriptionBoxId = "1";
        String customerId = "1";
        Subscription subscription = new Subscription(DeliveryFrequency.fromString(frequency), subscriptionBoxId, customerId);
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        Subscription createdSubscription = subscriptionService.createSubscription(frequency, subscriptionBoxId, customerId);

        verify(subscriptionRepository, times(1)).save(any(Subscription.class));
        assertEquals(subscription, createdSubscription);
    }

    @Test
    void testGetAllSubscriptions() {
        Subscription subscription1 = new Subscription();
        Subscription subscription2 = new Subscription();
        when(subscriptionRepository.findAll()).thenReturn(Arrays.asList(subscription1, subscription2));

        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();

        verify(subscriptionRepository, times(1)).findAll();
        assertEquals(2, subscriptions.size());
    }

    @Test
    void testGetSubscriptionById() {
        Subscription subscription = new Subscription();
        UUID id = UUID.randomUUID();
        subscription.setId(id);
        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(subscription));

        Subscription foundSubscription = subscriptionService.findById(id.toString());

        verify(subscriptionRepository, times(1)).findById(id);
        assertEquals(subscription, foundSubscription);
    }

    @Test
    void testGetSubscriptionsByCustomerId() {
        Subscription subscription1 = new Subscription();
        Subscription subscription2 = new Subscription();
        String customerId = "1";
        when(subscriptionRepository.findByCustomerId(customerId)).thenReturn(Arrays.asList(subscription1, subscription2));

        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByCustomerId(customerId);

        verify(subscriptionRepository, times(1)).findByCustomerId(customerId);
        assertEquals(2, subscriptions.size());
    }

    @Test
    void testUpdateApprovalStatusToApproved() {
        Subscription subscription = new Subscription();
        UUID id = UUID.randomUUID();
        subscription.setId(id);
        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(subscription));

        Subscription updatedSubscription = subscriptionService.updateApprovalStatus(id.toString(), ApprovalStatus.APPROVED.getValue());

        verify(subscriptionRepository, times(1)).findById(id);
        verify(subscriptionRepository, times(1)).save(subscription);
        assertEquals(ApprovalStatus.APPROVED, updatedSubscription.getApprovalStatus());
    }

    @Test
    void testUpdateApprovalStatusToRejected() {
        Subscription subscription = new Subscription();
        UUID id = UUID.randomUUID();
        subscription.setId(id);
        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(subscription));

        Subscription updatedSubscription = subscriptionService.updateApprovalStatus(id.toString(), ApprovalStatus.REJECTED.getValue());

        verify(subscriptionRepository, times(1)).findById(id);
        verify(subscriptionRepository, times(1)).save(subscription);
        assertEquals(ApprovalStatus.REJECTED, updatedSubscription.getApprovalStatus());
    }

    @Test
    void testUpdateApprovalStatusInvalidStatus() {
        Subscription subscription = new Subscription();
        UUID id = UUID.randomUUID();
        subscription.setId(id);
        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(subscription));

        try {
            subscriptionService.updateApprovalStatus(id.toString(), "INVALID");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid status", e.getMessage());
        }
    }

    @Test
    void testCancelSubscription() {
        Subscription subscription = new Subscription();
        UUID id = UUID.randomUUID();
        subscription.setId(id);
        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(subscription));

        Subscription cancelledSubscription = subscriptionService.cancelSubscription(id.toString());

        verify(subscriptionRepository, times(1)).findById(id);
        verify(subscriptionRepository, times(1)).save(subscription);
        assertEquals(SubscriptionStatus.CANCELLED, cancelledSubscription.getSubscriptionStatus());
    }
}