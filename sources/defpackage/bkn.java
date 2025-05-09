package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.command.CommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.strategy.SendStrategy;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class bkn extends SendStrategy {
    private MessageReceiveCallback c = new MessageReceiveCallback() { // from class: bkn.1
        @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
        public void onChannelEnable(DeviceInfo deviceInfo, String str, int i) {
        }

        @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
        public void onDataReceived(DeviceInfo deviceInfo, biu biuVar, int i) {
            if (deviceInfo != null && biuVar != null) {
                for (CommandBase commandBase : bkn.e) {
                    if (commandBase != null && !commandBase.handleReceivedData(deviceInfo.getDeviceMac(), biuVar)) {
                        return;
                    }
                }
                MessageReceiveCallback messageReceiveCallback = (MessageReceiveCallback) bkn.d.get(deviceInfo.getDeviceMac());
                if (messageReceiveCallback != null) {
                    messageReceiveCallback.onDataReceived(deviceInfo, biuVar, i);
                    return;
                } else {
                    LogUtil.a("SendStrategyInitial5A", "onDataReceived messageReceiveCallback is null");
                    return;
                }
            }
            LogUtil.a("SendStrategyInitial5A", "localCallback onDataReceived device or dataContents parameter exception.");
        }
    };
    private static List<CommandBase> e = new ArrayList(16);
    private static Map<String, bkm> b = new ConcurrentHashMap(16);
    private static Map<String, MessageReceiveCallback> d = new ConcurrentHashMap(16);

    public bkn() {
        e.add(new bgk());
    }

    @Override // com.huawei.devicesdk.strategy.SendStrategy
    public ArrayList<bim> getSendFrames(bir birVar, DeviceInfo deviceInfo) {
        ArrayList<bim> arrayList = new ArrayList<>(16);
        if (birVar == null) {
            LogUtil.a("SendStrategyInitial5A", "message is empty when getting send frames .");
            return arrayList;
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("SendStrategyInitial5A", "deviceMac is empty when getting send frames .");
            return arrayList;
        }
        byte[] e2 = birVar.e();
        e(e2);
        LogUtil.c("SendStrategyInitial5A", "command is ", blt.b(e2));
        bkm d2 = d(deviceInfo.getDeviceMac() + birVar.i());
        biw c = bjx.a().c(deviceInfo.getDeviceMac());
        d2.d(c);
        if (birVar.k() && e(deviceInfo) && c != null) {
            if (bgm.d(birVar, deviceInfo.getDeviceMac(), c.a())) {
                e2 = birVar.e();
            } else {
                LogUtil.e("SendStrategyInitial5A", "getSendFrames: encrypt failed");
                iyv.c("CommandMessage", "encrypt command failed");
                return arrayList;
            }
        }
        ArrayList<byte[]> a2 = d2.a(e2);
        if (bkz.e(a2)) {
            return arrayList;
        }
        Iterator<byte[]> it = a2.iterator();
        while (it.hasNext()) {
            byte[] next = it.next();
            bim bimVar = new bim();
            bimVar.c(birVar.b());
            bimVar.b(birVar.j());
            bimVar.b(birVar.c());
            bimVar.c(birVar.i());
            ArrayList arrayList2 = new ArrayList(16);
            arrayList2.addAll(d2.b(next));
            bimVar.a(arrayList2);
            arrayList.add(bimVar);
        }
        return arrayList;
    }

    private void e(byte[] bArr) {
        if (bky.i()) {
            byte b2 = bArr[0];
            byte b3 = bArr[1];
            if (b2 != 9 || b3 == 3 || b3 == 4 || b3 == 18) {
                return;
            }
            LogUtil.c("R_SendStrategyInitial5A", "command is ", blt.d(bArr, true));
        }
    }

    private boolean e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("SendStrategyInitial5A", "deviceInfo is null");
            return false;
        }
        String deviceMac = deviceInfo.getDeviceMac();
        return deviceInfo.getDeviceBtType() == 2 || bjn.c(deviceMac) || bjr.e(deviceMac) || bjk.a(deviceMac);
    }

    @Override // com.huawei.devicesdk.strategy.SendStrategy
    public void parseReceiveFrames(DeviceInfo deviceInfo, biu biuVar, MessageReceiveCallback messageReceiveCallback) {
        if (!e(deviceInfo, biuVar)) {
            LogUtil.a("SendStrategyInitial5A", "the parameter input is invalid");
            return;
        }
        if (messageReceiveCallback != null) {
            d.put(deviceInfo.getDeviceMac(), messageReceiveCallback);
        }
        LogUtil.c("SendStrategyInitial5A", "parseReceiveFrames, ", Integer.valueOf(biuVar.c()));
        bkm d2 = d(deviceInfo.getDeviceMac() + biuVar.c());
        biw c = bjx.a().c(deviceInfo.getDeviceMac());
        d2.d(c);
        byte[] a2 = biuVar.a();
        byte[] e2 = d2.e(a2.length, a2);
        if (e2 == null || e2.length == 0) {
            LogUtil.a("SendStrategyInitial5A", "BLE package and length less than MTU threshold.");
            return;
        }
        List<bit> d3 = d2.d(e2.length, e2);
        if (bkz.e(d3)) {
            LogUtil.a("SendStrategyInitial5A", "sliceResponses is empty");
        } else {
            d2.b(deviceInfo, biuVar.b(), c, d3, this.c);
        }
    }

    @Override // com.huawei.devicesdk.strategy.SendStrategy
    public void destroy(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("SendStrategyInitial5A", "destroy: identify is empty");
            return;
        }
        Iterator<Map.Entry<String, bkm>> it = b.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, bkm> next = it.next();
            if (next.getKey().contains(str)) {
                if (next.getValue() != null) {
                    next.getValue().b();
                }
                it.remove();
            }
        }
    }

    private bkm d(String str) {
        bkm bkmVar = b.containsKey(str) ? b.get(str) : null;
        if (bkmVar != null) {
            return bkmVar;
        }
        bkm bkmVar2 = new bkm();
        b.put(str, bkmVar2);
        return bkmVar2;
    }

    private boolean e(DeviceInfo deviceInfo, biu biuVar) {
        if (deviceInfo == null) {
            LogUtil.a("SendStrategyInitial5A", "device is empty, and return");
            return false;
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("SendStrategyInitial5A", "device mac is empty, and return");
            return false;
        }
        if (biuVar != null) {
            return true;
        }
        LogUtil.a("SendStrategyInitial5A", "frame is empty, and return");
        return false;
    }
}
