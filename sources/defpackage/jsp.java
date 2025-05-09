package defpackage;

import android.content.Intent;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.HwWatchFaceUtil;
import com.huawei.hwdevice.phoneprocess.mgr.sleepsync.SleepSyncManager;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class jsp {
    private static final Map<Integer, Set<Integer>> d;

    static {
        HashMap hashMap = new HashMap(16);
        d = hashMap;
        HashSet hashSet = new HashSet(2);
        hashSet.add(16);
        hashSet.add(59);
        hashSet.add(46);
        hashSet.add(54);
        hashSet.add(56);
        hashMap.put(1, hashSet);
        HashSet hashSet2 = new HashSet(2);
        hashSet2.add(12);
        hashMap.put(39, hashSet2);
        HashSet hashSet3 = new HashSet(2);
        hashSet3.add(7);
        hashMap.put(42, hashSet3);
        HashSet hashSet4 = new HashSet(2);
        hashSet4.add(1);
        hashMap.put(11, hashSet4);
    }

    public static boolean d(byte[] bArr, DeviceInfo deviceInfo) {
        if (bArr != null && bArr.length >= 2) {
            if (deviceInfo == null) {
                LogUtil.h("DataReceivedResponse", "responseDataReceived deviceInfo null");
                return true;
            }
            byte b = bArr[0];
            byte b2 = bArr[1];
            if (b == 27 || b == 39) {
                ReleaseLogUtil.b("DEVMGR_DataReceivedResponse", "response sid: ", Integer.valueOf(b), " cid: ", Integer.valueOf(b2));
            }
            if (!e(b, b2) && !c(b, b2, bArr)) {
                return true;
            }
            d(b, b2, bArr, deviceInfo);
            c(b, b2, bArr, deviceInfo);
            e(b, b2, bArr, deviceInfo);
            e(b, b2, deviceInfo);
            b(b, b2, bArr);
            a(b, b2, bArr);
            a(b, b2, bArr, deviceInfo);
            b(b, b2, bArr, deviceInfo);
            a(bArr, deviceInfo, b, b2);
        }
        return false;
    }

    private static boolean e(int i, int i2) {
        Set<Integer> set = d.get(Integer.valueOf(i));
        return set != null && set.contains(Integer.valueOf(i2));
    }

    private static boolean c(int i, int i2, byte[] bArr) {
        LogUtil.a("DataReceivedResponse", "isSpecialProcess enter");
        if (i != 7 || i2 != 15 || !e(bArr)) {
            return false;
        }
        LogUtil.a("DataReceivedResponse", "command is 5.7.15 sleep");
        return true;
    }

    private static void d(int i, int i2, byte[] bArr, DeviceInfo deviceInfo) {
        if (i == 1 && i2 == 16) {
            new kit().d(bArr, deviceInfo);
        }
    }

    private static void c(int i, int i2, byte[] bArr, final DeviceInfo deviceInfo) {
        if (i == 39 && i2 == 12) {
            String d2 = cvx.d(bArr);
            if (TextUtils.isEmpty(d2) || d2.length() < 4) {
                LogUtil.h("DataReceivedResponse", "responseOneHopHandInfo dataInfos is error");
                return;
            }
            if ("7F04".equalsIgnoreCase(d2.length() >= 8 ? d2.substring(4, 8) : null)) {
                LogUtil.h("DataReceivedResponse", "responseOneHopHandInfo error 7F04");
            } else {
                LogUtil.a("DataReceivedResponse", "enter send ability");
                HwWatchFaceUtil.e(new HwWatchFaceUtil.TouchSupportCallback() { // from class: jsp.1
                    @Override // com.huawei.hwdevice.outofprocess.util.HwWatchFaceUtil.TouchSupportCallback
                    public void touchSupportResult(int i3) {
                        LogUtil.a("DataReceivedResponse", "touchSupport:", Integer.valueOf(i3));
                        String str = cvx.e(1) + cvx.e(1) + cvx.e(i3);
                        DeviceCommand deviceCommand = new DeviceCommand();
                        deviceCommand.setServiceID(39);
                        deviceCommand.setCommandID(12);
                        deviceCommand.setDataContent(cvx.a(str));
                        deviceCommand.setDataLen(cvx.a(str).length);
                        deviceCommand.setmIdentify(DeviceInfo.this.getDeviceIdentify());
                        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
                    }
                });
            }
        }
    }

    private static void e(int i, int i2, byte[] bArr, final DeviceInfo deviceInfo) {
        if (i != 42 || i2 != 7) {
            LogUtil.h("DataReceivedResponse", "responseAppMarketSupportFeatures serviceId and CommandId is error");
            return;
        }
        String d2 = cvx.d(bArr);
        LogUtil.h("DataReceivedResponse", "responseAppMarketSupportFeatures dataInfo:", d2);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            return;
        }
        if ("7F04".equalsIgnoreCase(d2.length() >= 8 ? d2.substring(4, 8) : null)) {
            return;
        }
        LogUtil.a("DataReceivedResponse", "responseAppMarketSupportFeatures get and send support features");
        kjv.d().a(new IBaseResponseCallback() { // from class: jsp.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                LogUtil.a("DataReceivedResponse", "getAppMarketSupportFeatures features:", Integer.valueOf(i3));
                String str = cvx.e(1) + cvx.e(1) + cvx.e(i3);
                DeviceCommand deviceCommand = new DeviceCommand();
                deviceCommand.setServiceID(42);
                deviceCommand.setCommandID(7);
                deviceCommand.setDataContent(cvx.a(str));
                deviceCommand.setDataLen(cvx.a(str).length);
                deviceCommand.setmIdentify(DeviceInfo.this.getDeviceIdentify());
                jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
            }
        });
    }

    private static void e(int i, int i2, DeviceInfo deviceInfo) {
        if (i == 1 && i2 == 59) {
            LogUtil.a("DataReceivedResponse", "Enter 5.1.59 responseDisconnectDevice");
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(deviceInfo.getDeviceIdentify());
            jsz.b(BaseApplication.getContext()).unPair(arrayList, false);
        }
    }

    private static void b(int i, int i2, byte[] bArr) {
        if (i == 11 && i2 == 1) {
            jtv.b(BaseApplication.getContext()).a(bArr);
        }
    }

    private static void a(int i, int i2, byte[] bArr) {
        if (i == 1 && i2 == 46) {
            kio.b().a(bArr);
        }
    }

    private static void a(int i, int i2, byte[] bArr, DeviceInfo deviceInfo) {
        if (i == 1 && i2 == 54) {
            kfe.a().d(bArr, deviceInfo);
        }
    }

    private static void b(int i, int i2, byte[] bArr, DeviceInfo deviceInfo) {
        if (i == 1 && i2 == 56) {
            kki.c(bArr, deviceInfo);
        }
    }

    private static void a(byte[] bArr, DeviceInfo deviceInfo, int i, int i2) {
        LogUtil.a("DataReceivedResponse", "enter responseSleepSyncInfo");
        if (i == 7 && i2 == 15 && e(bArr)) {
            LogUtil.a("DataReceivedResponse", "responseSleepSyncInfo is sleep");
            Intent intent = new Intent("com.huawei.health.watch_in_sleep");
            intent.setPackage(BaseApplication.getContext().getPackageName());
            BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
            SleepSyncManager.e().a(deviceInfo);
        }
    }

    private static boolean e(byte[] bArr) {
        try {
            if (jro.a(bArr) == 16) {
                LogUtil.a("DataReceivedResponse", "action is sleep");
                return true;
            }
        } catch (cwg unused) {
            LogUtil.b("DataReceivedResponse", "processDeviceDataReport exception");
        }
        return false;
    }
}
