package defpackage;

import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.open.data.model.IHeartRate;

/* loaded from: classes3.dex */
public class der extends HealthData implements IHeartRate {
    private int c;

    @Override // com.huawei.health.ecologydevice.open.data.model.IHeartRate
    public int getHeartRate() {
        return this.c;
    }

    @Override // com.huawei.health.ecologydevice.open.data.model.IHeartRate
    public void setHeartRate(Integer num) {
        this.c = num == null ? 0 : num.intValue();
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public String toString() {
        return "HeartRate [mHeartRate=" + this.c + "]";
    }
}
