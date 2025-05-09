package defpackage;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.HealthDataParser;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class dbi extends MeasureKit {
    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDevice.HealthDeviceKind getHealthDataKind() {
        return HealthDevice.HealthDeviceKind.HDK_ROWING_MACHINE;
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDataParser getHealthDataParser() {
        LogUtil.a("PDSPORT_RowMachineKit", "getHealthDataParser");
        return new HealthDataParser() { // from class: dbh
            @Override // com.huawei.health.device.open.data.HealthDataParser
            public final HealthData parseData(byte[] bArr) {
                return dbi.b(bArr);
            }
        };
    }

    static /* synthetic */ HealthData b(byte[] bArr) {
        return new HealthData();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public MeasureController getMeasureController() {
        LogUtil.a("PDSPORT_RowMachineKit", "getMeasureController");
        return new dbj();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public String getUuid() {
        return "b8bcfd13dc974128a3858d6945d2e15d";
    }
}
