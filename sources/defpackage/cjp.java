package defpackage;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.HealthDataParser;

/* loaded from: classes3.dex */
public class cjp extends MeasureKit {
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
        return new cjk();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public MeasureController getMeasureController() {
        return cjl.c();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public String getUuid() {
        return "c26e8aaf-c375-4640-958a-7276b3b0b6cb";
    }
}
