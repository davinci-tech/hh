package com.huawei.health.ecologydevice.open.data.model;

/* loaded from: classes3.dex */
public interface IHeartRateAndBloodPressure {
    short getDiastolic();

    short getHeartRate();

    long getStartTime();

    short getSystolic();

    void setDiastolic(short s);

    void setEndTime(long j);

    void setHeartRate(short s);

    void setStartTime(long j);

    void setSystolic(short s);
}
