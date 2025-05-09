package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.wearengine.capability.EnumWearEngineCapabilityItem;

/* loaded from: classes7.dex */
public class tof {
    private DeviceInfo b;
    private EnumWearEngineCapabilityItem c;
    private tof e;

    public tof(DeviceInfo deviceInfo, EnumWearEngineCapabilityItem enumWearEngineCapabilityItem) {
        this.b = deviceInfo;
        this.c = enumWearEngineCapabilityItem;
    }

    public int a() {
        if (tog.a(this.b, this.c)) {
            return 0;
        }
        return c();
    }

    private int c() {
        tof tofVar = this.e;
        if (tofVar != null) {
            return tofVar.a();
        }
        return 1;
    }

    public tof d(EnumWearEngineCapabilityItem enumWearEngineCapabilityItem) {
        tof tofVar = new tof(this.b, enumWearEngineCapabilityItem);
        this.e = tofVar;
        return tofVar;
    }
}
