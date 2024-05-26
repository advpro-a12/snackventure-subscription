package id.ac.ui.cs.advprog.snackventure.subscription.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import id.ac.ui.cs.advprog.snackventure.subscription.enums.*;
import id.ac.ui.cs.advprog.snackventure.subscription.status.*;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionTest {
    private Subscription subscription;
    private final DeliveryFrequency frequency = DeliveryFrequency.MONTHLY;
    private final String customerId = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
    private final String subscriptionBoxId = "99963276-4e60-4e9a-96ce-8d5a9957209d";

    @BeforeEach
    public void setUp() {
        subscription = new Subscription(frequency, subscriptionBoxId, customerId);
    }

    @Test
    void testSubscriptionCreation() {
        assertNotNull(subscription);
        assertNotNull(subscription.getId());
        assertNotNull(subscription.getCreatedAt());
        assertNotNull(subscription.getSubscriptionCode());
        assertNull(subscription.getStartDate());
        assertNull(subscription.getEndDate());
        assertEquals(ApprovalStatus.PENDING, subscription.getApprovalStatus());
        assertEquals(SubscriptionStatus.PENDING, subscription.getSubscriptionStatus());
        assertEquals(frequency, subscription.getFrequency());
        assertEquals(customerId, subscription.getCustomerId());
        assertEquals(subscriptionBoxId, subscription.getSubscriptionBoxId());
    }

    @Test
    void testSubscriptionCodeFormat() {
        String subscriptionCode = subscription.getSubscriptionCode();
        assertNotNull(subscriptionCode);
        String[] parts = subscriptionCode.split("-");
        assertEquals(2, parts.length);
        assertEquals(frequency.getValue(), parts[0]);
    }

    @Test
    void testApprove() {
        subscription.approve();
        assertEquals(ApprovalStatus.APPROVED, subscription.getApprovalStatus());
        assertEquals(SubscriptionStatus.SUBSCRIBED, subscription.getSubscriptionStatus());
        assertInstanceOf(ApprovedState.class, subscription.getState());
        assertNotNull(subscription.getStartDate());
        assertNull(subscription.getEndDate());
    }

    @Test
    void testReject() {
        subscription.reject();
        assertEquals(ApprovalStatus.REJECTED, subscription.getApprovalStatus());
        assertEquals(SubscriptionStatus.CANCELLED, subscription.getSubscriptionStatus());
        assertInstanceOf(RejectedState.class, subscription.getState());
        assertNull(subscription.getStartDate());
        assertNull(subscription.getEndDate());
    }

    @Test
    void testCancel() {
        subscription.cancel();
        assertEquals(SubscriptionStatus.CANCELLED, subscription.getSubscriptionStatus());
        assertInstanceOf(CancelledState.class, subscription.getState());
        assertNull(subscription.getStartDate());
        assertNull(subscription.getEndDate());
    }

    @Test
    void testApproveRejected() {
        subscription.reject();
        subscription.approve();
        assertEquals(ApprovalStatus.REJECTED, subscription.getApprovalStatus());
        assertEquals(SubscriptionStatus.CANCELLED, subscription.getSubscriptionStatus());
        assertInstanceOf(RejectedState.class, subscription.getState());
        assertNull(subscription.getStartDate());
        assertNull(subscription.getEndDate());
    }

    @Test
    void testRejectApproved() {
        subscription.approve();
        subscription.reject();
        assertEquals(ApprovalStatus.APPROVED, subscription.getApprovalStatus());
        assertEquals(SubscriptionStatus.SUBSCRIBED, subscription.getSubscriptionStatus());
        assertInstanceOf(ApprovedState.class, subscription.getState());
        assertNotNull(subscription.getStartDate());
        assertNull(subscription.getEndDate());
    }

    @Test
    void testCancelApproved() {
        subscription.approve();
        subscription.cancel();
        assertEquals(ApprovalStatus.APPROVED, subscription.getApprovalStatus());
        assertEquals(SubscriptionStatus.CANCELLED, subscription.getSubscriptionStatus());
        assertInstanceOf(CancelledState.class, subscription.getState());
        assertNotNull(subscription.getStartDate());
        assertNotNull(subscription.getEndDate());
    }

    @Test
    void testCancelRejected() {
        subscription.reject();
        subscription.cancel();
        assertEquals(ApprovalStatus.REJECTED, subscription.getApprovalStatus());
        assertEquals(SubscriptionStatus.CANCELLED, subscription.getSubscriptionStatus());
        assertInstanceOf(RejectedState.class, subscription.getState());
        assertNull(subscription.getStartDate());
        assertNull(subscription.getEndDate());
    }
}
