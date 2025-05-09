package com.huawei.hihealth.api;

/* loaded from: classes.dex */
public interface SyncApi<T> {
    public static final String FITNESS = "FitnessAdvice";
    public static final String HEALTH_LIFE = "HealthLifeModel";

    void cleanCloud(boolean z, Object obj);

    String getLabel();

    T syncCloud();
}
