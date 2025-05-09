package defpackage;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.HealthDataParser;

/* loaded from: classes3.dex */
public class cgj extends MeasureKit {
    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDataParser getHealthDataParser() {
        return null;
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDevice.HealthDeviceKind getHealthDataKind() {
        return HealthDevice.HealthDeviceKind.HDK_WEIGHT;
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public MeasureController getMeasureController() {
        return cgk.d();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public String getUuid() {
        return "24e9c24f-4f59-464e-9cf1-1ce81335907c";
    }
}
