package defpackage;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.HealthDataParser;

/* loaded from: classes3.dex */
public class cgr extends MeasureKit {
    @Override // com.huawei.health.device.open.MeasureKit
    public MeasureController getBackgroundController() {
        return null;
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDevice.HealthDeviceKind getHealthDataKind() {
        return HealthDevice.HealthDeviceKind.HDK_WEIGHT;
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDataParser getHealthDataParser() {
        return new cgz();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public MeasureController getMeasureController() {
        return cgt.e();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public String getUuid() {
        return "42ad7cd8-ca28-11e9-a32f-2a2ae2dbcce4";
    }
}
