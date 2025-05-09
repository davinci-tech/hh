package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.health.device.datatype.SkippingTargetMode;
import com.huawei.health.ecologydevice.fitness.RopeFittingsClient;
import com.huawei.health.ecologydevice.fitness.datastruct.DeviceInformation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import health.compact.a.CommonUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class dds {

    /* renamed from: a, reason: collision with root package name */
    private static volatile dds f11616a;
    private static final Object c = new Object();
    private static final Map<String, RopeFittingsClient> d = new HashMap();
    private boolean b;
    private boolean e;
    private MessageOrStateCallback h;
    private cxz j;

    private dds() {
    }

    public static dds c() {
        dds ddsVar;
        if (f11616a == null) {
            synchronized (c) {
                if (f11616a == null) {
                    f11616a = new dds();
                }
                ddsVar = f11616a;
            }
            return ddsVar;
        }
        return f11616a;
    }

    public void g() {
        LogUtil.c("RopeDeviceManager", "init()");
        if (this.j == null) {
            this.j = new cxz();
            MessageOrStateCallback messageOrStateCallback = this.h;
            if (messageOrStateCallback != null) {
                b("RopeDeviceManager", messageOrStateCallback, false, null);
            }
        }
    }

    public boolean f() {
        cxz cxzVar = this.j;
        return cxzVar != null && cxzVar.d() == 6;
    }

    public String b() {
        cxz cxzVar = this.j;
        return cxzVar != null ? cxzVar.c() : "";
    }

    public void e(MessageOrStateCallback messageOrStateCallback) {
        this.h = messageOrStateCallback;
        if (messageOrStateCallback != null) {
            b("RopeDeviceManager", messageOrStateCallback, false, null);
        }
    }

    public void e(String str, MessageOrStateCallback messageOrStateCallback, boolean z) {
        b(str, messageOrStateCallback, z, null);
    }

    public void b(String str, MessageOrStateCallback messageOrStateCallback, boolean z, List<Integer> list) {
        cxz cxzVar = this.j;
        if (cxzVar != null) {
            cxzVar.c(str, messageOrStateCallback, z, list);
        } else {
            LogUtil.b("RopeDeviceManager", "ropeFitnessClient is null");
        }
    }

    public void a(boolean z, String str) {
        if (this.j != null && !TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str) && !str.equals(d())) {
                i();
            }
            this.j.connectByMac(z, str);
            return;
        }
        LogUtil.b("RopeDeviceManager", "ropeFitnessClient is null");
    }

    public void c(SkippingTargetMode skippingTargetMode) {
        cxz cxzVar = this.j;
        if (cxzVar != null) {
            cxzVar.b(skippingTargetMode);
        } else {
            LogUtil.b("RopeDeviceManager", "ropeFitnessClient is null");
        }
    }

    public void d(int i, int[] iArr) {
        cxz cxzVar = this.j;
        if (cxzVar != null) {
            cxzVar.setFitnessMachineControl(i, iArr);
        } else {
            LogUtil.b("RopeDeviceManager", "ropeFitnessClient is null");
        }
    }

    public void c(int i, int i2, int[] iArr) {
        cxz cxzVar = this.j;
        if (cxzVar != null) {
            cxzVar.setFitnessMachineControl(i, i2, iArr);
        } else {
            LogUtil.b("RopeDeviceManager", "ropeFitnessClient is null");
        }
    }

    public void d(int i, int i2, int[] iArr) {
        cxz cxzVar = this.j;
        if (cxzVar != null) {
            cxzVar.b(i, i2, iArr);
        } else {
            LogUtil.b("RopeDeviceManager", "ropeFitnessClient is null");
        }
    }

    public void c(String str, boolean z) {
        cxz cxzVar = this.j;
        if (cxzVar != null) {
            cxzVar.removeMessageOrStateCallback(str, z);
        } else {
            LogUtil.b("RopeDeviceManager", "ropeFitnessClient is null");
        }
    }

    public void h() {
        did.c().c(false);
        cxz cxzVar = this.j;
        if (cxzVar != null) {
            cxzVar.releaseResource();
            this.j = null;
        } else {
            LogUtil.b("RopeDeviceManager", "ropeFitnessClient is null");
        }
    }

    public String d() {
        cxz cxzVar = this.j;
        if (cxzVar != null) {
            return cxzVar.getCurrentMacAddress();
        }
        LogUtil.b("RopeDeviceManager", "ropeFitnessClient is null");
        return "";
    }

    public void e(String str) {
        cxz cxzVar = this.j;
        if (cxzVar != null) {
            cxzVar.d(str);
        } else {
            LogUtil.b("RopeDeviceManager", "ropeFitnessClient is null");
        }
    }

    public String j() {
        cxz cxzVar = this.j;
        if (cxzVar != null) {
            return cxzVar.e();
        }
        LogUtil.b("RopeDeviceManager", "ropeFitnessClient is null");
        return "";
    }

    public void b(String str) {
        cxz cxzVar = this.j;
        if (cxzVar != null) {
            cxzVar.e(str);
        } else {
            LogUtil.b("RopeDeviceManager", "ropeFitnessClient is null");
        }
    }

    public DeviceInformation a() {
        cxz cxzVar = this.j;
        return cxzVar != null ? cxzVar.a() : new DeviceInformation();
    }

    private void i() {
        LogUtil.a("RopeDeviceManager", "initRopeRealTimeDataCallBack");
        b("DeviceManagerDefault", new MessageOrStateCallback() { // from class: dds.1
            @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
            public void onStateChange(String str) {
            }

            @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
            public void onNewMessage(int i, Bundle bundle) {
                if (i == 1000) {
                    dds.this.b = true;
                }
                if (i == 1001) {
                    dds.this.b = false;
                }
                if (i != 902) {
                    return;
                }
                if (bundle != null) {
                    if (!dds.d.isEmpty()) {
                        if (!dds.this.b || !dds.this.e) {
                            LogUtil.h("RopeDeviceManager", "Linkage is not enabled or authorized.mIsStartSkippingRope = ", Boolean.valueOf(dds.this.b), " mIsFittingAuthentication = ", Boolean.valueOf(dds.this.e));
                            return;
                        }
                        LogUtil.a("RopeDeviceManager", "in onNewMessage, newCase is DATA_AVAILABLE");
                        if (bundle.getInt("com.huawei.health.fitness.KEY_MESSAGE_FITNESS_DATA_TYPE") != 22) {
                            LogUtil.h("RopeDeviceManager", "other type");
                            return;
                        }
                        Serializable serializable = bundle.getSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA");
                        LogUtil.c("RopeDeviceManager", "onNewMessage DATA_AVAILABLE:", serializable);
                        if (serializable instanceof HashMap) {
                            dds.this.b((HashMap<Integer, Object>) serializable);
                            return;
                        }
                        return;
                    }
                    LogUtil.h("RopeDeviceManager", "FITTINGS_MAC is empty");
                    return;
                }
                LogUtil.h("RopeDeviceManager", "bundle is null");
            }
        }, false, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HashMap<Integer, Object> hashMap) {
        Object obj = hashMap.get(40010);
        Object obj2 = hashMap.get(40001);
        Object obj3 = hashMap.get(6);
        Object obj4 = hashMap.get(40011);
        Object obj5 = hashMap.get(Integer.valueOf(SmartMsgConstant.MSG_TYPE_RIDE_USER));
        LinkedList linkedList = new LinkedList();
        linkedList.add(0, 31);
        linkedList.add(1, Integer.valueOf(obj != null ? ((Integer) obj).intValue() : 0));
        linkedList.add(2, Integer.valueOf(obj2 != null ? ((Integer) obj2).intValue() : 0));
        linkedList.add(3, Integer.valueOf(obj3 != null ? ((Integer) obj3).intValue() : 0));
        linkedList.add(4, Integer.valueOf(obj4 != null ? ((Integer) obj4).intValue() : 0));
        linkedList.add(5, Integer.valueOf(obj5 != null ? ((Integer) obj5).intValue() : 0));
        int[] iArr = new int[6];
        for (int i = 0; i < linkedList.size(); i++) {
            iArr[i] = ((Integer) linkedList.get(i)).intValue();
        }
        a(iArr);
    }

    private RopeFittingsClient d(String str) {
        Map<String, RopeFittingsClient> map = d;
        RopeFittingsClient ropeFittingsClient = map.get(str);
        if (ropeFittingsClient != null) {
            return ropeFittingsClient;
        }
        RopeFittingsClient ropeFittingsClient2 = new RopeFittingsClient(str);
        map.put(str, ropeFittingsClient2);
        return ropeFittingsClient2;
    }

    public void c(String str, RopeFittingsClient.FittingsMessageOrStateCallback fittingsMessageOrStateCallback) {
        RopeFittingsClient d2 = d(str);
        if (d(d2)) {
            return;
        }
        d2.e(fittingsMessageOrStateCallback);
        d2.c();
    }

    public void c(String str) {
        d(str).e();
        d.remove(str);
    }

    public void a(int[] iArr) {
        for (Map.Entry<String, RopeFittingsClient> entry : d.entrySet()) {
            RopeFittingsClient value = entry.getValue();
            if (!d(value)) {
                LogUtil.a("RopeDeviceManager", "device = ", CommonUtil.l(entry.getKey()));
                return;
            }
            value.c(iArr);
        }
    }

    public void a(int i, int i2) {
        for (Map.Entry<String, RopeFittingsClient> entry : d.entrySet()) {
            RopeFittingsClient value = entry.getValue();
            if (!d(value)) {
                LogUtil.a("RopeDeviceManager", "device ", CommonUtil.l(entry.getKey()));
                return;
            }
            value.d(i, i2);
        }
    }

    public void e(int i) {
        for (Map.Entry<String, RopeFittingsClient> entry : d.entrySet()) {
            RopeFittingsClient value = entry.getValue();
            if (!d(value)) {
                LogUtil.a("RopeDeviceManager", "device ", CommonUtil.l(entry.getKey()));
                return;
            }
            value.a(i);
        }
    }

    public boolean a(String str) {
        return d(d(str));
    }

    private boolean d(RopeFittingsClient ropeFittingsClient) {
        boolean b = ropeFittingsClient.b();
        Object[] objArr = new Object[1];
        objArr[0] = b ? "connect" : "unConnected";
        LogUtil.a("RopeDeviceManager", objArr);
        return b;
    }

    public void d(boolean z) {
        this.e = z;
    }
}
