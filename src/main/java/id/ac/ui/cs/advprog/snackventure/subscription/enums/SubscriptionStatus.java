package id.ac.ui.cs.advprog.snackventure.subscription.enums;

import lombok.Getter;

@Getter
public enum SubscriptionStatus {
    PENDING("PENDING"),
    CANCELLED("CANCELLED"),
    SUBSCRIBED("SUBSCRIBED");

    private final String value;

    private SubscriptionStatus(String value) {
        this.value = value;
    }
}
