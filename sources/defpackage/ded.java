package defpackage;

import com.huawei.health.device.open.data.model.HealthData;

/* loaded from: classes3.dex */
public class ded extends HealthData {
    private long b;
    private long c;
    private float e;

    public float e() {
        return this.e;
    }

    public void a(float f) {
        this.e = f;
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public long getStartTime() {
        return this.b;
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public void setStartTime(long j) {
        this.b = j;
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public long getEndTime() {
        return this.c;
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public void setEndTime(long j) {
        this.c = j;
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public String toString() {
        return "BodyTemperature [mStartTime=" + this.b + ", mEndTime=" + this.c + "]";
    }
}
