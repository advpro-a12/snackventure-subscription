package id.ac.ui.cs.advprog.snackventure.subscription.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import id.ac.ui.cs.advprog.snackventure.subscription.enums.*;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionTest {
    private Subscription subscription;
    private final DeliveryFrequency frequency = DeliveryFrequency.MONTHLY;
    private final String customerId = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
    private final String subscriptionBoxId = "99963276-4e60-4e9a-96ce-8d5a9957209d";

    @BeforeEach
    public void setUp() {
        subscription = new Subscription(frequency, customerId, subscriptionBoxId);
        System.out.println("Subscription code: " + subscription.getSubscriptionCode());
    }

    @Test
    public void testSubscriptionCreation() {
        assertNotNull(subscription);
        assertNotNull(subscription.getId());
        assertNotNull(subscription.getCreatedAt());
        assertNotNull(subscription.getSubscriptionCode());
        assertEquals(ApprovalStatus.PENDING, subscription.getApprovalStatus());
        assertEquals(SubscriptionStatus.PENDING, subscription.getSubscriptionStatus());
        assertEquals(frequency, subscription.getFrequency());
        assertEquals(customerId, subscription.getCustomerId());
        assertEquals(subscriptionBoxId, subscription.getSubscriptionBoxId());
    }

    @Test
    public void testSetApprovalStatusToApproved() {
        subscription.setApprovalStatus(ApprovalStatus.APPROVED);
        assertEquals(ApprovalStatus.APPROVED, subscription.getApprovalStatus());
    }

    @Test
    public void testSetApprovalStatusToRejected() {
        subscription.setApprovalStatus(ApprovalStatus.REJECTED);
        assertEquals(ApprovalStatus.REJECTED, subscription.getApprovalStatus());
    }

    @Test
    public void testSetSubscriptionStatusToSubscribed() {
        subscription.setSubscriptionStatus(SubscriptionStatus.SUBSCRIBED);
        assertEquals(SubscriptionStatus.SUBSCRIBED, subscription.getSubscriptionStatus());
    }

    @Test
    public void testSetSubscriptionStatusToCancelled() {
        subscription.setSubscriptionStatus(SubscriptionStatus.CANCELLED);
        assertEquals(SubscriptionStatus.CANCELLED, subscription.getSubscriptionStatus());
    }

    @Test
    public void testSubscriptionCodeFormat() {
        String subscriptionCode = subscription.getSubscriptionCode();
        assertNotNull(subscriptionCode);
        String[] parts = subscriptionCode.split("-");
        assertEquals(2, parts.length);
        assertEquals(frequency.getValue(), parts[0]);
    }
}
