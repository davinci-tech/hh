package defpackage;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.HealthDataParser;
import com.huawei.health.device.open.data.model.HealthData;

/* loaded from: classes3.dex */
public class dai extends MeasureKit {
    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDevice.HealthDeviceKind getHealthDataKind() {
        return HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE;
    }

    static /* synthetic */ HealthData d(byte[] bArr) {
        return new HealthData();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDataParser getHealthDataParser() {
        return new HealthDataParser() { // from class: dan
            @Override // com.huawei.health.device.open.data.HealthDataParser
            public final HealthData parseData(byte[] bArr) {
                return dai.d(bArr);
            }
        };
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public MeasureController getMeasureController() {
        return daj.a();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public String getUuid() {
        return "cf6b65f7-ccea-4cbd-bd7f-246795c55bd7";
    }
}
