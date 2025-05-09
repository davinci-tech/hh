package defpackage;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.HealthDataParser;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class dbq extends MeasureKit {
    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDevice.HealthDeviceKind getHealthDataKind() {
        return HealthDevice.HealthDeviceKind.HDK_WALKING_MACHINE;
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public HealthDataParser getHealthDataParser() {
        LogUtil.a("PDSPORT_WalkingMachineSportKit", "getHealthDataParser");
        return null;
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public MeasureController getMeasureController() {
        LogUtil.a("PDSPORT_WalkingMachineSportKit", "getMeasureController");
        return new dbj();
    }

    @Override // com.huawei.health.device.open.MeasureKit
    public String getUuid() {
        return "eac9bc0b-9e7d-422b-b368-103993011b0f";
    }
}
