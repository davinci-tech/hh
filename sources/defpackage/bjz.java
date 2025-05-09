package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.devicesdk.strategy.SendStrategy;
import health.compact.a.LogUtil;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class bjz {
    protected MessageReceiveCallback c;
    protected MessageReceiveCallback d;
    protected MessageReceiveCallback e;
    private static Map<String, SendStrategy> b = new ConcurrentHashMap(16);

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, big> f417a = new ConcurrentHashMap(16);

    private bjz() {
        this.e = new MessageReceiveCallback() { // from class: bjz.2
            @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
            public void onDataReceived(DeviceInfo deviceInfo, biu biuVar, int i) {
                if (deviceInfo == null || biuVar == null) {
                    LogUtil.a("SendCommandMange", "invalid input parameter");
                    return;
                }
                String b2 = biuVar.b();
                LogUtil.c("SendCommandMange", "onDataReceived uuid: ", b2, blt.a(deviceInfo), " errorCode: ", Integer.valueOf(i));
                SendStrategy d = bjz.this.d(b2);
                if (d != null) {
                    MessageReceiveCallback a2 = bjz.this.a(deviceInfo.getDeviceMac());
                    if (a2 == null) {
                        LogUtil.a("SendCommandMange", "dataReceiveCallback is empty ", blt.a(deviceInfo));
                        return;
                    } else if (i == 0) {
                        d.parseReceiveFrames(deviceInfo, biuVar, a2);
                        return;
                    } else {
                        a2.onDataReceived(deviceInfo, null, i);
                        return;
                    }
                }
                LogUtil.a("SendCommandMange", "can not get send strategy of uuid ", b2, blt.a(deviceInfo));
            }

            @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
            public void onChannelEnable(DeviceInfo deviceInfo, String str, int i) {
                LogUtil.c("SendCommandMange", "onChannelEnable characterUuid: ", str, blt.a(deviceInfo));
                if (deviceInfo != null) {
                    MessageReceiveCallback a2 = bjz.this.a(deviceInfo.getDeviceMac());
                    if (a2 != null) {
                        a2.onChannelEnable(deviceInfo, str, i);
                        return;
                    } else {
                        LogUtil.a("SendCommandMange", "can not get dataReceiveCallback.");
                        return;
                    }
                }
                LogUtil.a("SendCommandMange", "deviceInfo is null");
            }
        };
        bib.a().b(this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MessageReceiveCallback a(String str) {
        if (bjx.a().g(str)) {
            return this.d;
        }
        return this.c;
    }

    public static bjz b() {
        return e.d;
    }

    public SendStrategy e(String str, SendMode sendMode) {
        if (str == null) {
            return null;
        }
        SendStrategy d = d(str);
        if (d != null) {
            return d;
        }
        SendStrategy a2 = a(sendMode);
        LogUtil.c("SendCommandMange", "getSendStrategy sendStrategy is null uuid: ", str, ",type: ", Integer.valueOf(sendMode.value()), ",sendStrategy: ", a2);
        b.put(str, a2);
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SendStrategy d(String str) {
        return b.get(str);
    }

    private SendStrategy a(SendMode sendMode) {
        Object d = new bkp().d(sendMode);
        if (d instanceof SendStrategy) {
            return (SendStrategy) d;
        }
        return null;
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("SendCommandMange", "clearStrategyCache: identify is empty");
            return;
        }
        Iterator<SendStrategy> it = b.values().iterator();
        while (it.hasNext()) {
            it.next().destroy(str);
        }
    }

    public void a(DeviceInfo deviceInfo, bir birVar) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("SendCommandMange", "sendCommand device info is invalid.");
            return;
        }
        if (birVar == null) {
            LogUtil.a("SendCommandMange", "command message is invalid.", blt.a(deviceInfo));
            return;
        }
        String deviceMac = deviceInfo.getDeviceMac();
        String a2 = bgn.a(birVar.b());
        SendStrategy e2 = e(a2, birVar.g());
        if (e2 == null) {
            LogUtil.a("SendCommandMange", "send strategy is null.", blt.a(deviceMac), " characterId: ", a2);
            return;
        }
        big bigVar = f417a.get(deviceMac);
        if (bigVar == null) {
            LogUtil.a("SendCommandMange", "device command sender is not init ", blt.a(deviceMac));
            c(deviceInfo, birVar);
        } else {
            if (bigVar.d(birVar, e2)) {
                return;
            }
            LogUtil.a("SendCommandMange", "add command to queue failed.");
            c(deviceInfo, birVar);
        }
    }

    private void c(DeviceInfo deviceInfo, bir birVar) {
        CharacterOperationType c = birVar.c();
        String b2 = birVar.b();
        String str = "notifySendCommandError " + blt.a(deviceInfo) + " characterId: " + b2 + " optionsType: " + c;
        iyv.c("CommandMessage", str);
        LogUtil.a("SendCommandMange", str);
        if (c == CharacterOperationType.ENABLE || c == CharacterOperationType.DISABLE) {
            this.e.onChannelEnable(deviceInfo, b2, 1);
        } else {
            this.e.onDataReceived(deviceInfo, null, 1);
        }
    }

    public void b(MessageReceiveCallback messageReceiveCallback) {
        this.c = messageReceiveCallback;
    }

    public void d() {
        this.c = null;
    }

    public void d(MessageReceiveCallback messageReceiveCallback) {
        this.d = messageReceiveCallback;
    }

    public void a(DeviceInfo deviceInfo) {
        if (e(deviceInfo)) {
            String deviceMac = deviceInfo.getDeviceMac();
            LogUtil.c("SendCommandMange", "initDeviceCommandMessageSender ", blt.a(deviceInfo));
            big bigVar = new big(deviceInfo);
            f417a.put(deviceMac, bigVar);
            bigVar.start();
        }
    }

    public int e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("SendCommandMange", "device info is invalid.");
            return -1;
        }
        big bigVar = f417a.get(str);
        if (bigVar == null) {
            return -1;
        }
        int a2 = bigVar.a();
        LogUtil.c("SendCommandMange", "command queue size: ", Integer.valueOf(a2));
        return a2;
    }

    public boolean e(DeviceInfo deviceInfo) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            LogUtil.a("SendCommandMange", "device info is invalid.");
            return false;
        }
        String deviceMac = deviceInfo.getDeviceMac();
        LogUtil.c("SendCommandMange", "closeDeviceCommandMessageSender ", blt.a(deviceInfo));
        big remove = f417a.remove(deviceMac);
        if (remove == null) {
            return true;
        }
        remove.e();
        return true;
    }

    static class e {
        private static bjz d = new bjz();
    }
}
