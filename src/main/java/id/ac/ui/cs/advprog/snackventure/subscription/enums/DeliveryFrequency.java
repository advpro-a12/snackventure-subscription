package id.ac.ui.cs.advprog.snackventure.subscription.enums;

import lombok.Getter;

@Getter
public enum DeliveryFrequency {
    MONTHLY("MTH"),
    QUARTERLY("QTR"),
    SEMI_ANNUALLY("SAA");

    private final String value;

    private DeliveryFrequency(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (DeliveryFrequency deliveryFrequency : DeliveryFrequency.values()) {
            if (deliveryFrequency.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}
