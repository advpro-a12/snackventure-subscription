package id.ac.ui.cs.advprog.snackventure.subscription.model;

import jakarta.persistence.*;
import id.ac.ui.cs.advprog.snackventure.subscription.enums.*;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name="subscription_code", updatable = false, nullable = false)
    private String subscriptionCode;

    @Column(name="approval_status", nullable = false)
    private ApprovalStatus approvalStatus;

    @Column(name="subscription_status", nullable = false)
    private SubscriptionStatus subscriptionStatus;

    @Column(name="frequency", updatable = false, nullable = false)
    private DeliveryFrequency frequency;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @Column(name="customer_id", updatable = false, nullable = false)
    private String customerId;

    @Column(name="subscription_box_id", updatable = false, nullable = false)
    private String subscriptionBoxId;

    public Subscription() {
    }

    public Subscription(DeliveryFrequency frequency, String customerId, String subscriptionBoxId) {
        this.id = UUID.randomUUID();
        this.createdAt = new Date();
        this.subscriptionCode = frequency.getValue() + "-" + Long.toHexString(Double.doubleToLongBits(Math.random()));
        this.approvalStatus = ApprovalStatus.PENDING;
        this.subscriptionStatus = SubscriptionStatus.PENDING;
        this.frequency = frequency;
        this.customerId = customerId;
        this.subscriptionBoxId = subscriptionBoxId;
    }
}
