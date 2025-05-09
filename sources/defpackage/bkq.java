package defpackage;

import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.SimpleDataHead;
import com.huawei.devicesdk.strategy.BaseSendStrategyInitialD;
import health.compact.a.LogUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class bkq extends BaseSendStrategyInitialD {
    @Override // com.huawei.devicesdk.strategy.SendStrategy
    public ArrayList<bim> getSendFrames(bir birVar, DeviceInfo deviceInfo) {
        ArrayList<bim> arrayList = new ArrayList<>();
        if (deviceInfo == null) {
            LogUtil.a("SendStrategyInitialD", "SendStrategyInitialD getSendFrames deviceInfo is null.");
        }
        if (birVar == null) {
            LogUtil.a("SendStrategyInitialD", "message is null");
            return arrayList;
        }
        byte[] e = birVar.e();
        if (!isInputDataValid(e)) {
            LogUtil.a("SendStrategyInitialD", "getSendFrame error. input data is invalid");
            return arrayList;
        }
        LogUtil.c("SendStrategyInitialD", "isEncrypt: ", Boolean.valueOf(birVar.k()), " dataHead: ", Byte.valueOf(birVar.d()));
        LogUtil.c("SendStrategyInitialD", "uuid is " + birVar.b() + " command is ", blt.b(e));
        byte[] e2 = birVar.e();
        if (birVar.k() && birVar.d() == SimpleDataHead.INVALID.getDataHead() && deviceInfo != null) {
            e2 = new bgu().encrypt(e2, deviceInfo.getDeviceMac());
        }
        return constructBluetoothFrame(birVar.k(), e2, birVar);
    }
}
