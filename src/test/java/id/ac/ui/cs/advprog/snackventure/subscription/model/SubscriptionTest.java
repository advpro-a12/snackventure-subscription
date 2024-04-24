package id.ac.ui.cs.advprog.snackventure.subscription.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import id.ac.ui.cs.advprog.snackventure.subscription.enums.*;
import id.ac.ui.cs.advprog.snackventure.subscription.status.*;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionTest {
    private Subscription subscription;
    private final DeliveryFrequency frequency = DeliveryFrequency.MONTHLY;
    private final String customerId = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
    private final String subscriptionBoxId = "99963276-4e60-4e9a-96ce-8d5a9957209d";

    @BeforeEach
    public void setUp() {
        subscription = new Subscription(frequency, customerId, subscriptionBoxId);
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
    public void testSubscriptionCodeFormat() {
        String subscriptionCode = subscription.getSubscriptionCode();
        assertNotNull(subscriptionCode);
        String[] parts = subscriptionCode.split("-");
        assertEquals(2, parts.length);
        assertEquals(frequency.getValue(), parts[0]);
    }

    @Test
    public void testApprove() {
        subscription.approve();
        assertEquals(ApprovalStatus.APPROVED, subscription.getApprovalStatus());
        assertEquals(SubscriptionStatus.SUBSCRIBED, subscription.getSubscriptionStatus());
        assertInstanceOf(ApprovedState.class, subscription.getState());
    }

    @Test
    public void testReject() {
        subscription.reject();
        assertEquals(ApprovalStatus.REJECTED, subscription.getApprovalStatus());
        assertEquals(SubscriptionStatus.CANCELLED, subscription.getSubscriptionStatus());
        assertInstanceOf(RejectedState.class, subscription.getState());
    }

    @Test
    public void testCancel() {
        subscription.cancel();
        assertEquals(SubscriptionStatus.CANCELLED, subscription.getSubscriptionStatus());
        assertInstanceOf(CancelledState.class, subscription.getState());
    }

    @Test
    public void testApproveRejected() {
        subscription.reject();
        subscription.approve();
        assertEquals(ApprovalStatus.REJECTED, subscription.getApprovalStatus());
        assertEquals(SubscriptionStatus.CANCELLED, subscription.getSubscriptionStatus());
        assertInstanceOf(RejectedState.class, subscription.getState());
    }

    @Test
    public void testRejectApproved() {
        subscription.approve();
        subscription.reject();
        assertEquals(ApprovalStatus.APPROVED, subscription.getApprovalStatus());
        assertEquals(SubscriptionStatus.SUBSCRIBED, subscription.getSubscriptionStatus());
        assertInstanceOf(ApprovedState.class, subscription.getState());
    }

    @Test
    public void testCancelApproved() {
        subscription.approve();
        subscription.cancel();
        assertEquals(ApprovalStatus.APPROVED, subscription.getApprovalStatus());
        assertEquals(SubscriptionStatus.CANCELLED, subscription.getSubscriptionStatus());
        assertInstanceOf(CancelledState.class, subscription.getState());
    }

    @Test
    public void testCancelRejected() {
        subscription.reject();
        subscription.cancel();
        assertEquals(ApprovalStatus.REJECTED, subscription.getApprovalStatus());
        assertEquals(SubscriptionStatus.CANCELLED, subscription.getSubscriptionStatus());
        assertInstanceOf(RejectedState.class, subscription.getState());
    }
}
