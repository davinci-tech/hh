package defpackage;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.HealthDataParser;

/* loaded from: classes3.dex */
public class czw extends MeasureKit {
    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDevice.HealthDeviceKind getHealthDataKind() {
        return HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE;
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public MeasureController getMeasureController() {
        return czv.c();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public MeasureController getBackgroundController() {
        return new czv();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDataParser getHealthDataParser() {
        return new czt();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public String getUuid() {
        return "7972de7d-de18-4d1a-b043-6f3936fe8e01";
    }
}
