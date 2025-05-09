package defpackage;

import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.datatype.DataDeviceIntelligentInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.mgr.device.ProfileAgent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.client.connect.ServiceConnectCallback;
import defpackage.joi;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class joi {
    private cwl g;
    private Map<String, Integer> i;
    private static final Object c = new Object();
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13988a = new Object();
    private static final Object e = new Object();
    private static List<int[]> f = new ArrayList(16);
    private static List<List<jov>> b = new ArrayList(16);

    private joi() {
        this.g = new cwl();
        this.i = new HashMap(16);
    }

    public static joi c() {
        return e.d;
    }

    public void d(String str, int i, byte[] bArr) {
        Object a2;
        byte b2 = bArr[1];
        LogUtil.c("DeviceSettingCommandProcess", "handleResult commandType is ", Integer.valueOf(b2), " id ", str);
        if (b2 == 2 || b2 == 3) {
            a2 = a(b2, bArr);
        } else if (b2 >= 4 && b2 <= 6) {
            a2 = e(b2, bArr);
        } else if (b2 >= 7 && b2 <= 20) {
            a2 = e(b2, str, i, bArr);
        } else if (b2 >= 23 && b2 <= 29) {
            a2 = b(b2, str, bArr);
        } else if (b2 >= 33 && b2 <= 36) {
            a2 = d(b2, bArr, i, str);
        } else {
            LogUtil.h("DeviceSettingCommandProcess", "handleResult command id is other :", Integer.valueOf(b2));
            a2 = null;
        }
        a(a2, str, bArr);
    }

    private Object a(int i, byte[] bArr) {
        LogUtil.a("DeviceSettingCommandProcess", "handlerCapabilityResult() in type ", Integer.valueOf(i));
        if (i == 2) {
            return h(bArr);
        }
        if (i == 3) {
            String d2 = cvx.d(bArr);
            return d(d2.length() >= 4 ? d2.substring(4, d2.length()) : "");
        }
        LogUtil.h("DeviceSettingCommandProcess", "handlerCapabilityResult default type:", Integer.valueOf(i));
        return null;
    }

    private Object h(byte[] bArr) {
        int[] iArr;
        LogUtil.a("DeviceSettingCommandProcess", "handleServiceAbility() in");
        int length = bArr.length - 4;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 4, bArr2, 0, length);
        HashMap hashMap = new HashMap(16);
        synchronized (c) {
            iArr = f.size() != 0 ? f.get(0) : null;
        }
        if (iArr != null) {
            for (int i = 0; i < length; i++) {
                hashMap.put(Integer.valueOf(iArr[i]), Boolean.valueOf(bArr2[i] != 0));
            }
        }
        synchronized (c) {
            if (f.size() != 0) {
                f.remove(0);
            }
        }
        return hashMap;
    }

    private Object d(String str) {
        List<jov> list;
        LogUtil.a("DeviceSettingCommandProcess", "handleCommandAbility() in");
        ArrayList arrayList = new ArrayList(16);
        Object obj = null;
        try {
            Object obj2 = d;
            synchronized (obj2) {
                list = b.size() != 0 ? b.get(0) : null;
            }
            if (list != null) {
                obj = e(list, str, arrayList);
                synchronized (obj2) {
                    if (b.size() != 0) {
                        b.remove(0);
                    }
                }
            }
        } catch (cwg e2) {
            LogUtil.b("DeviceSettingCommandProcess", "handleCommandAbility TlvException :", ExceptionUtils.d(e2));
        }
        return obj;
    }

    private Object e(List<jov> list, String str, List<jox> list2) throws cwg {
        LogUtil.a("DeviceSettingCommandProcess", "parserCommandSend() in");
        Iterator<cwe> it = this.g.a(str).a().iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            for (cwd cwdVar : it.next().e()) {
                jov jovVar = list.get(i);
                int w = CommonUtil.w(cwdVar.e());
                if (w == 2) {
                    i2 = CommonUtil.w(cwdVar.c());
                } else if (w == 4) {
                    jox joxVar = new jox();
                    joxVar.a(i2);
                    joxVar.d(a(jovVar, cwdVar.c()));
                    list2.add(joxVar);
                } else {
                    LogUtil.h("DeviceSettingCommandProcess", "parserCommandSend() tlv tag is error:", Integer.valueOf(CommonUtil.w(cwdVar.e())));
                }
            }
            i++;
        }
        return list2;
    }

    private Map<Integer, Boolean> a(jov jovVar, String str) {
        HashMap hashMap = new HashMap(16);
        if (jovVar != null) {
            int i = 0;
            int i2 = 0;
            while (i < str.length()) {
                int i3 = i + 2;
                if (CommonUtil.w(str.substring(i, i3)) == 1) {
                    hashMap.put(jovVar.c().get(i2), true);
                } else {
                    hashMap.put(jovVar.c().get(i2), false);
                }
                i2++;
                i = i3;
            }
        }
        return hashMap;
    }

    private Object e(int i, byte[] bArr) {
        LogUtil.a("DeviceSettingCommandProcess", "handleDeviceDate() in type ", Integer.valueOf(i));
        if (i == 4 || i == 5) {
            return Integer.valueOf(a(bArr));
        }
        if (i == 6) {
            String d2 = cvx.d(bArr);
            return c(d2.length() >= 4 ? d2.substring(4, d2.length()) : "");
        }
        LogUtil.h("DeviceSettingCommandProcess", "handleDeviceDate default type:", Integer.valueOf(i));
        return null;
    }

    private int a(byte[] bArr) {
        LogUtil.a("DeviceSettingCommandProcess", "getString() in");
        String d2 = cvx.d(bArr);
        if (d2 == null || d2.length() < 8) {
            LogUtil.h("DeviceSettingCommandProcess", "getString() in valid data");
            return -1;
        }
        return CommonUtil.w(d2.substring(8, d2.length()));
    }

    private Object c(String str) {
        LogUtil.a("DeviceSettingCommandProcess", "getData() in");
        try {
            joz jozVar = new joz();
            Iterator<cwd> it = this.g.a(str).e().iterator();
            while (it.hasNext()) {
                b(it.next(), jozVar);
            }
            return jozVar;
        } catch (cwg e2) {
            LogUtil.b("DeviceSettingCommandProcess", "getData COMMAND_ID_GET_DATE TlvException:", ExceptionUtils.d(e2));
            return null;
        }
    }

    private void b(cwd cwdVar, joz jozVar) {
        int w = CommonUtil.w(cwdVar.e());
        if (w == 1) {
            jozVar.e(CommonUtil.y(cwdVar.c()) * 1000);
        } else {
            if (w == 2) {
                jozVar.c((CommonUtil.w(cwdVar.c().substring(0, 2)) * 100) + CommonUtil.w(cwdVar.c().substring(2, 4)));
                return;
            }
            LogUtil.h("DeviceSettingCommandProcess", "setDataGmtTime() tlv tag is error:", Integer.valueOf(w));
        }
    }

    private Object e(int i, String str, int i2, byte[] bArr) {
        LogUtil.a("DeviceSettingCommandProcess", "handleDeviceResult() in type ", Integer.valueOf(i));
        if (i != 20) {
            switch (i) {
                case 7:
                    return jot.a().b(str, bArr);
                case 8:
                    return b(str, i2, bArr);
                case 9:
                    return Integer.valueOf(a(bArr));
                case 10:
                case 11:
                case 12:
                    return Integer.valueOf(a(bArr));
                case 13:
                    return b(bArr);
                default:
                    LogUtil.h("DeviceSettingCommandProcess", "handleDeviceResult default type:", Integer.valueOf(i));
                    return null;
            }
        }
        return c(bArr);
    }

    private jpw e(byte[] bArr) {
        jpw jpwVar = new jpw();
        String d2 = cvx.d(bArr);
        LogUtil.a("DeviceSettingCommandProcess", "getDeviceBattery info:", d2);
        if (TextUtils.isEmpty(d2) || d2.length() <= 4) {
            LogUtil.h("DeviceSettingCommandProcess", "getBattery data is error");
            return jpwVar;
        }
        c(d2, jpwVar);
        return jpwVar;
    }

    private void c(String str, jpw jpwVar) {
        try {
            for (cwd cwdVar : this.g.a(str.substring(4)).e()) {
                int w = CommonUtil.w(cwdVar.e());
                String c2 = cwdVar.c();
                if (w == 1) {
                    jpwVar.e(Math.min(CommonUtil.w(c2), 100));
                } else if (w == 2) {
                    if (c2 != null && c2.length() >= 6) {
                        jpwVar.b(e(CommonUtil.w(c2.substring(0, 2))));
                        jpwVar.i(e(CommonUtil.w(c2.substring(2, 4))));
                        jpwVar.a(e(CommonUtil.w(c2.substring(4))));
                    }
                    LogUtil.a("DeviceSettingCommandProcess", "leftEar is ", 0, "rightEar is ", 0, "chargeBox is ", 0);
                } else if (w != 3) {
                    LogUtil.h("DeviceSettingCommandProcess", "parseDeviceBattery() tlv tag is error:", Integer.valueOf(w));
                } else {
                    if (c2 != null && c2.length() >= 6) {
                        jpwVar.c(CommonUtil.w(c2.substring(0, 2)));
                        jpwVar.h(CommonUtil.w(c2.substring(2, 4)));
                        jpwVar.d(CommonUtil.w(c2.substring(4)));
                    }
                    LogUtil.a("DeviceSettingCommandProcess", "leftEar State is ", 0, "rightEar State is ", 0, "chargeBox State is ", 0);
                }
            }
        } catch (cwg e2) {
            LogUtil.b("DeviceSettingCommandProcess", "getDeviceBattery COMMAND_ID_GET_BATTERY TlvException:", ExceptionUtils.d(e2));
        }
    }

    private int e(int i) {
        if (i == 255) {
            return -1;
        }
        return Math.min(i, 100);
    }

    private Object b(String str, int i, byte[] bArr) {
        jpw jpwVar = new jpw();
        Intent intent = new Intent();
        intent.setAction("com.huawei.bone.action.BATTERY_LEVEL");
        if (bArr.length > 3 && bArr[2] == Byte.MAX_VALUE) {
            LogUtil.b("DeviceSettingCommandProcess", "getBattery() get battery level timeout.");
        } else {
            if (b() == 0) {
                if (bArr.length > 14) {
                    jpwVar.e(Math.min((int) bArr[14], 100));
                } else {
                    jpwVar.e(0);
                }
            } else {
                try {
                    jpwVar = e(bArr);
                } catch (NumberFormatException e2) {
                    LogUtil.b("DeviceSettingCommandProcess", "getBattery Exception = ", e2.getMessage());
                }
            }
            LogUtil.a("DeviceSettingCommandProcess", "getBattery() productType is ", Integer.valueOf(i), ", deviceId is ", CommonUtil.l(str), ", battery is ", Integer.valueOf(jpwVar.d()));
            if (!cvt.c(i)) {
                jpy.d(jpwVar.d());
            }
            jpy.b(str, jpwVar.d());
            jpy.c(str, jpwVar);
            intent.putExtra("BATTERY_LEVEL", jpwVar.d());
            intent.putExtra("DEVICE_MAC", str);
            BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
        }
        return jpwVar;
    }

    private int b() {
        DeviceInfo a2 = jpt.a("DeviceSettingCommandProcess");
        if (a2 != null) {
            return a2.getDeviceProtocol();
        }
        return -1;
    }

    private Object b(byte[] bArr) {
        LogUtil.a("DeviceSettingCommandProcess", "factoryReset() in");
        if (b() == 0) {
            return Integer.valueOf(g(bArr));
        }
        int a2 = a(bArr);
        if (a2 == 100000) {
            try {
                synchronized (f13988a) {
                    String e2 = jog.c().e();
                    if (TextUtils.isEmpty(e2)) {
                        LogUtil.h("DeviceSettingCommandProcess", "deleteMac is empty");
                    }
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add(e2);
                    LogUtil.h("DeviceSettingCommandProcess", "deleteDevice");
                    DeviceInfo e3 = jpt.e(e2, "DeviceSettingCommandProcess");
                    jfq.c().c((List<String>) arrayList, true);
                    if (e3 != null) {
                        e(e3.getSecurityUuid() + "#ANDROID21");
                    } else {
                        LogUtil.h("DeviceSettingCommandProcess", "deleteDevice currentDeviceInfo is null");
                    }
                }
            } catch (RemoteException e4) {
                LogUtil.b("DeviceSettingCommandProcess", "factoryReset() RemoteException:", ExceptionUtils.d(e4));
                jez.a(BaseApplication.getContext());
            }
        }
        return Integer.valueOf(a2);
    }

    private int g(byte[] bArr) {
        LogUtil.a("DeviceSettingCommandProcess", "handleLinkLayerV0FactoryReset() in");
        if (bArr.length > 3 && bArr[2] == Byte.MAX_VALUE) {
            return 100009;
        }
        if (bArr.length == 3) {
            return b(bArr[2]);
        }
        return 100001;
    }

    private int b(int i) {
        if (i != 0) {
            if (i == 1) {
                LogUtil.b("DeviceSettingCommandProcess", "V0 Device factory reset fail for time remove.");
                return i;
            }
            if (i == 2) {
                LogUtil.b("DeviceSettingCommandProcess", "V0 Device factory reset fail for flash remove.");
                return i;
            }
            LogUtil.b("DeviceSettingCommandProcess", "V0 Device factory reset fail for unknown.");
            return i;
        }
        LogUtil.a("DeviceSettingCommandProcess", "handleFactoryResetErrorCode V0 Device factory reset success.");
        synchronized (f13988a) {
            String e2 = jog.c().e();
            if (TextUtils.isEmpty(e2)) {
                LogUtil.h("DeviceSettingCommandProcess", "deleteMac is empty");
            }
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(e2);
            try {
                LogUtil.h("DeviceSettingCommandProcess", "deleteDevice");
                jfq.c().c((List<String>) arrayList, true);
            } catch (RemoteException e3) {
                LogUtil.b("DeviceSettingCommandProcess", "handleFactoryResetErrorCode() RemoteException:", ExceptionUtils.d(e3));
                jez.a(BaseApplication.getContext());
            }
        }
        return 100000;
    }

    private void e(String str) {
        LogUtil.h("DeviceSettingCommandProcess", "deleteDeviceToSmartLife sn:", CommonUtil.l(str));
        if (Utils.o() || TextUtils.isEmpty(str)) {
            return;
        }
        ProfileAgent.PROFILE_AGENT.connectProfile(new c(str));
        LogUtil.a("DeviceSettingCommandProcess", "syncDeviceInfoToSmartLife enter");
    }

    static class c implements ServiceConnectCallback {

        /* renamed from: a, reason: collision with root package name */
        private final String f13989a;

        public c(String str) {
            this.f13989a = str;
        }

        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onConnect() {
            LogUtil.a("DeviceSettingCommandProcess", "profile connected");
            ProfileAgent.PROFILE_AGENT.setConnected(true);
            jdx.b(new Runnable() { // from class: jof
                @Override // java.lang.Runnable
                public final void run() {
                    joi.c.this.d();
                }
            });
        }

        /* synthetic */ void d() {
            new jqf().a(this.f13989a);
            ProfileAgent.PROFILE_AGENT.disconnectProfile();
        }

        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onDisconnect() {
            ProfileAgent.PROFILE_AGENT.setConnected(false);
            LogUtil.a("DeviceSettingCommandProcess", "profile disconnected");
        }
    }

    private Object c(byte[] bArr) {
        LogUtil.a("DeviceSettingCommandProcess", "getGoldCard() in");
        String binaryString = Integer.toBinaryString(a(bArr));
        int length = binaryString.length();
        if (length < 8) {
            for (int i = 0; i < 8 - length; i++) {
                binaryString = "0" + binaryString;
            }
        }
        return Boolean.valueOf(binaryString.substring(4, 5).equals("1"));
    }

    private Object b(int i, String str, byte[] bArr) {
        LogUtil.a("DeviceSettingCommandProcess", "handlerSetDeviceResult() in type ", Integer.valueOf(i));
        switch (i) {
            case 24:
                String d2 = cvx.d(bArr);
                return b(d2.length() >= 4 ? d2.substring(4, d2.length()) : "");
            case 25:
                return Integer.toBinaryString(a(bArr));
            case 26:
            case 27:
                return Integer.valueOf(a(bArr));
            case 28:
            default:
                LogUtil.h("DeviceSettingCommandProcess", "handlerSetDeviceResult default type:", Integer.valueOf(i));
                return null;
            case 29:
                return b(str, bArr);
        }
    }

    private Object b(String str) {
        LogUtil.a("DeviceSettingCommandProcess", "lockScreen() in");
        Integer num = null;
        try {
            for (cwd cwdVar : this.g.a(str).e()) {
                if (CommonUtil.a(cwdVar.e(), 16) == 1) {
                    num = Integer.valueOf(CommonUtil.a(cwdVar.c(), 16));
                } else {
                    LogUtil.h("DeviceSettingCommandProcess", "lockScreen() tag is other", Integer.valueOf(CommonUtil.a(cwdVar.e(), 16)));
                }
            }
        } catch (cwg e2) {
            LogUtil.b("DeviceSettingCommandProcess", "lockScreen() COMMAND_ID_PAY_GET_CPLC TlvException:", ExceptionUtils.d(e2));
        }
        return num;
    }

    private Object b(String str, byte[] bArr) {
        LogUtil.a("DeviceSettingCommandProcess", "getAllowDisturbContentList messages :", cvx.d(bArr));
        Integer valueOf = Integer.valueOf(a(bArr));
        LogUtil.a("DeviceSettingCommandProcess", "getAllowDisturbContentList object: ", valueOf);
        this.i.put(str, Integer.valueOf(a(bArr)));
        return valueOf;
    }

    public int a(String str) {
        if (TextUtils.isEmpty(str) || this.i.get(str) == null) {
            return 0;
        }
        return this.i.get(str).intValue();
    }

    private Object d(int i, byte[] bArr, int i2, String str) {
        LogUtil.a("DeviceSettingCommandProcess", "handleOtherDeviceResult() in type ", Integer.valueOf(i));
        switch (i) {
            case 33:
                return b(bArr, str);
            case 34:
                String d2 = cvx.d(bArr);
                return c(d2.length() >= 4 ? d2.substring(4, d2.length()) : "", i2);
            case 35:
            case 36:
                return bArr;
            default:
                LogUtil.h("DeviceSettingCommandProcess", "handleOtherDeviceResult default type:", Integer.valueOf(i));
                return null;
        }
    }

    private Object b(byte[] bArr, String str) {
        LogUtil.a("DeviceSettingCommandProcess", "getDefaultSwitch in");
        String d2 = cvx.d(bArr);
        LogUtil.a("DeviceSettingCommandProcess", "getDefaultSwitch message :", d2);
        Integer valueOf = Integer.valueOf(a(bArr));
        LogUtil.a("DeviceSettingCommandProcess", "getDefaultSwitch value:", valueOf);
        if (cvs.d() != null && cvs.d().isSupportDefaultSwitch()) {
            ReleaseLogUtil.e("DEVMGR_DeviceSettingCommandProcess", "getDefaultSwitch device support default switch");
            int w = CommonUtil.w(d2.substring(4, 6));
            if (w == 1) {
                joy.d().e(d(bArr), str);
            } else {
                ReleaseLogUtil.d("DEVMGR_DeviceSettingCommandProcess", "getDefaultSwitch type is error, type:", Integer.valueOf(w));
            }
        } else {
            ReleaseLogUtil.d("DEVMGR_DeviceSettingCommandProcess", "getDefaultSwitch device not support default switch");
        }
        return valueOf;
    }

    private int d(byte[] bArr) {
        String d2 = cvx.d(bArr);
        try {
            if (d2.length() < 4) {
                LogUtil.h("DeviceSettingCommandProcess", "getString invalid data");
                return -1;
            }
            return Integer.parseInt(d2.substring(4, d2.length()), 16);
        } catch (NumberFormatException unused) {
            LogUtil.b("DeviceSettingCommandProcess", "getString NumberFormatException");
            return 0;
        }
    }

    private Object c(String str, int i) {
        DataDeviceIntelligentInfo dataDeviceIntelligentInfo;
        LogUtil.a("DeviceSettingCommandProcess", "getIntelligentHomeLinkage() in");
        DataDeviceIntelligentInfo dataDeviceIntelligentInfo2 = new DataDeviceIntelligentInfo();
        if (b() == 0) {
            LogUtil.a("DeviceSettingCommandProcess", "LinkLayerVersion.LINK_LAYER_V0 == getCurrentDeviceProtocol()");
        } else {
            try {
                for (cwd cwdVar : this.g.a(str).e()) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    if (a2 == 1) {
                        dataDeviceIntelligentInfo2.setDeviceManu(cvx.e(cwdVar.c()));
                    } else if (a2 == 2) {
                        dataDeviceIntelligentInfo2.setDeviceProductId(bmz.d(i, cvx.e(cwdVar.c())));
                    } else if (a2 != 3) {
                        LogUtil.h("DeviceSettingCommandProcess", "getIntelligentHomeLinkage tag is other：", Integer.valueOf(a2));
                    } else {
                        dataDeviceIntelligentInfo2.setDeviceHiv(cvx.e(cwdVar.c()));
                    }
                }
                dataDeviceIntelligentInfo = dataDeviceIntelligentInfo2;
            } catch (cwg e2) {
                LogUtil.b("DeviceSettingCommandProcess", "getIntelligentHomeLinkage Exception:", ExceptionUtils.d(e2));
            }
            jog.c().a(dataDeviceIntelligentInfo2);
            return dataDeviceIntelligentInfo;
        }
        dataDeviceIntelligentInfo = null;
        jog.c().a(dataDeviceIntelligentInfo2);
        return dataDeviceIntelligentInfo;
    }

    private void a(Object obj, String str, byte[] bArr) {
        LogUtil.a("DeviceSettingCommandProcess", "sendCallback in ");
        synchronized (e) {
            List<IBaseResponseCallback> a2 = jfh.a(bArr[1]);
            if (a2 == null) {
                LogUtil.h("DeviceSettingCommandProcess", "sendCallback callbacks is null.");
                return;
            }
            if (obj != null && a2.size() != 0) {
                if (bArr[1] == 8 && (obj instanceof jpw)) {
                    try {
                        jpw jpwVar = (jpw) obj;
                        jpwVar.a(str);
                        Iterator<IBaseResponseCallback> it = a2.iterator();
                        while (it.hasNext()) {
                            it.next().d(0, jpwVar);
                        }
                    } catch (ClassCastException unused) {
                        LogUtil.b("DeviceSettingCommandProcess", "sendCallback ClassCastException");
                    }
                    a2.clear();
                } else {
                    a2.get(0).d(0, obj);
                    a2.remove(0);
                }
            } else if (a2.size() != 0) {
                a2.get(0).d(100001, "UNKNOWN_ERROR");
                a2.remove(0);
            }
        }
    }

    static class e {
        private static joi d = new joi();
    }
}
