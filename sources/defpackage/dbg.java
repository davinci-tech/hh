package defpackage;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.HealthDataParser;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class dbg extends MeasureKit {
    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDevice.HealthDeviceKind getHealthDataKind() {
        return HealthDevice.HealthDeviceKind.HDK_EXERCISE_BIKE;
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDataParser getHealthDataParser() {
        LogUtil.a("PDSPORT_ExerciseBikeSportKit", "getHealthDataParser");
        return new HealthDataParser() { // from class: dbd
            @Override // com.huawei.health.device.open.data.HealthDataParser
            public final HealthData parseData(byte[] bArr) {
                return dbg.a(bArr);
            }
        };
    }

    static /* synthetic */ HealthData a(byte[] bArr) {
        return new HealthData();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public MeasureController getMeasureController() {
        LogUtil.a("PDSPORT_ExerciseBikeSportKit", "getMeasureController");
        return new dbj();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public String getUuid() {
        return "0a413e73937644de8bf8beff56a6e4e1";
    }
}
