package com.huawei.health.health.utils.functionsetcard;

/* loaded from: classes3.dex */
public enum FunctionSetType {
    SPORTS_CARD(0),
    WEIGHT_CARD(1),
    SLEEP_CARD(2),
    STRESS_CARD(3),
    HEART_RATE_CARD(4),
    BLOOD_OXYGEN_CARD(5),
    BLOOD_PRESSURE_CARD(6),
    BLOOD_SUGAR_CARD(7),
    MANAGEMENT_CARD(8),
    PHYSIOLOGICAL_CYCLE_CARD(9),
    HEALTH_MODEL_CARD(10),
    TEMPERATURE_CARD(11),
    ACTIVE_HOUR_CARD(12),
    THREE_CIRCLE_CARD(13),
    HEALTH_RECORD_CARD(14);

    private int defaultIndex;

    FunctionSetType(int i) {
        this.defaultIndex = i;
    }
}
