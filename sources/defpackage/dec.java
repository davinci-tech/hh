package defpackage;

import com.huawei.health.device.open.data.model.HealthData;

/* loaded from: classes3.dex */
public class dec extends HealthData {
    private float c;
    private long d;
    private long e;

    public float e() {
        return this.c;
    }

    public void c(float f) {
        this.c = f;
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public long getStartTime() {
        return this.d;
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public void setStartTime(long j) {
        this.d = j;
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public long getEndTime() {
        return this.e;
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public void setEndTime(long j) {
        this.e = j;
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public String toString() {
        return "BloodOxygen [mStartTime=" + this.d + ", mEndTime=" + this.e + "]";
    }
}
