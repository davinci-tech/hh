package defpackage;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.HealthDataParser;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class dbk extends MeasureKit {
    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDevice.HealthDeviceKind getHealthDataKind() {
        return HealthDevice.HealthDeviceKind.HDK_TREADMILL;
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDataParser getHealthDataParser() {
        LogUtil.a("PDSPORT_TreadMillSportKit", "getHealthDataParser");
        return new HealthDataParser() { // from class: dbl
            @Override // com.huawei.health.device.open.data.HealthDataParser
            public final HealthData parseData(byte[] bArr) {
                return dbk.b(bArr);
            }
        };
    }

    static /* synthetic */ HealthData b(byte[] bArr) {
        return new HealthData();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public MeasureController getMeasureController() {
        LogUtil.a("PDSPORT_TreadMillSportKit", "getMeasureController");
        return new dbj();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public String getUuid() {
        return "92be717cf3db4668a661f26c28216d13";
    }
}
