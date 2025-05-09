package defpackage;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.HealthDataParser;

/* loaded from: classes3.dex */
public class cfo extends MeasureKit {
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
        return new cfj();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public String getUuid() {
        return "6d783cfa-eec8-4a62-a6fa-b686fdf02d24";
    }
}
