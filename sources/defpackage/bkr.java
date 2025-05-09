package defpackage;

import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.strategy.SendStrategy;
import health.compact.a.LogUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class bkr extends SendStrategy {
    @Override // com.huawei.devicesdk.strategy.SendStrategy
    public void destroy(String str) {
    }

    @Override // com.huawei.devicesdk.strategy.SendStrategy
    public ArrayList<bim> getSendFrames(bir birVar, DeviceInfo deviceInfo) {
        ArrayList<bim> arrayList = new ArrayList<>();
        if (deviceInfo == null) {
            LogUtil.a("SendStrategyInitialDirect", "SendStrategyInitialDirect getSendFrames deviceInfo is null.");
        }
        if (birVar == null) {
            LogUtil.a("SendStrategyInitialDirect", "message is null");
            return arrayList;
        }
        byte[] e = birVar.e();
        if (e == null) {
            LogUtil.e("SendStrategyInitialDirect", "getSendFrame error. input data is invalid");
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        bil bilVar = new bil();
        bilVar.b(e);
        bilVar.e(10);
        arrayList2.add(bilVar);
        arrayList.add(bkv.c(birVar, arrayList2));
        return arrayList;
    }

    @Override // com.huawei.devicesdk.strategy.SendStrategy
    public void parseReceiveFrames(DeviceInfo deviceInfo, biu biuVar, MessageReceiveCallback messageReceiveCallback) {
        if (deviceInfo == null || biuVar == null || messageReceiveCallback == null) {
            LogUtil.a("SendStrategyInitialDirect", "SendStrategyInitialDirect parseReceiveFrames device frame or callback exception.");
            return;
        }
        if (biuVar.a() == null) {
            LogUtil.e("SendStrategyInitialDirect", "response data is invalid");
            messageReceiveCallback.onDataReceived(deviceInfo, b(biuVar.b(), biuVar.a()), 0);
        }
        messageReceiveCallback.onDataReceived(deviceInfo, b(biuVar.b(), biuVar.a()), 0);
    }

    private biu b(String str, byte[] bArr) {
        biu biuVar = new biu();
        biuVar.a(str);
        biuVar.d(bArr);
        return biuVar;
    }
}
