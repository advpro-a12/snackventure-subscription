package id.ac.ui.cs.advprog.snackventure.subscription.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.ac.ui.cs.advprog.snackventure.subscription.status.*;
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

    @JsonIgnore
    @Column(name="state", nullable = false)
    private String stateString;

    @Transient
    @JsonIgnore
    private SubscriptionState state;

    public Subscription() {
        this.stateString = SubscriptionStatus.PENDING.toString();
        this.state = new PendingState();
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
        this.stateString = SubscriptionStatus.PENDING.toString();
        this.state = new PendingState();
    }

    @PostLoad
    public void postLoad() {
        switch (stateString) {
            case "APPROVED":
                this.state = new ApprovedState();
                break;
            case "REJECTED":
                this.state = new RejectedState();
                break;
            case "CANCELLED":
                this.state = new CancelledState();
                break;
            default:
                this.state = new PendingState();
                break;
        }
    }

    public void approve() {
        state.approve(this);
        this.stateString = "APPROVED";
    }

    public void reject() {
        state.reject(this);
        this.stateString = "REJECTED";
    }

    public void cancel() {
        state.cancel(this);
        this.stateString = "CANCELLED";
    }
}
