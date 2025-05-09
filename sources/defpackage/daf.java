package defpackage;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.HealthDataParser;

/* loaded from: classes3.dex */
public class daf extends MeasureKit {
    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDevice.HealthDeviceKind getHealthDataKind() {
        return HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE;
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDataParser getHealthDataParser() {
        return new dal();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public MeasureController getMeasureController() {
        return new dae();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public MeasureController getBackgroundController() {
        return new dae();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public String getUuid() {
        return "3ee66bc2-2306-4ce1-a697-952e85aa5e68";
    }
}
