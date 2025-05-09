package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class jst {
    public static void f(String str, String str2) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateDeviceIdentify mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateDeviceIdentify value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.e(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.e(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static void g(String str, String str2) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateDeviceModel mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateDeviceModel value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.i(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.i(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static void b(String str, String str2) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateDeviceCertModel mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateDeviceCertModel value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.c(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.c(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static void m(String str, String str2) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateManufactureInfo mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateManufactureInfo packageData: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.o(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.o(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static void k(String str, String str2) {
        if (!v(str) || str2 == null) {
            LogUtil.h("DeviceStatusPackageMgr", "updateDeviceUdid mac or deviceUdid is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateDeviceUdid packageData: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.j(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.j(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static void a(String str, String str2) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateBTVersion mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateBtVersion value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.d(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.d(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static void a(String str, int i) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateDeviceDeviceVersionType mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateDeviceVersionType value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.c(i);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.c(i);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static int o(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getDeviceVersionType mac is invalid");
            return -1;
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getDeviceVersionType value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            return -1;
        }
        return jsqVar.m();
    }

    public static String j(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getDeviceModel mac is invalid");
            return "";
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getDeviceModel value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        return jsqVar == null ? "" : jsqVar.i();
    }

    public static String a(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getDeviceModel mac is invalid");
            return "";
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getDeviceCertModel value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        return jsqVar == null ? "" : jsqVar.e();
    }

    public static String n(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getManufactureInfo mac is invalid");
            return "";
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getManufactureInfo packageData: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        return jsqVar == null ? "" : jsqVar.k();
    }

    public static String g(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getDeviceUdid mac is invalid");
            return "";
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getDeviceUdid packageData: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        return jsqVar == null ? "" : jsqVar.h();
    }

    public static String i(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getDeviceName mac is invalid");
            return "";
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getDeviceName value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        return jsqVar == null ? "" : jsqVar.f();
    }

    public static void i(String str, String str2) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateDeviceName mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateDeviceName value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.g(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.g(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static void n(String str, String str2) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateHiLinkDeviceId mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateHiLinkDeviceId value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.m(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.m(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static String m(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getHiLinkDeviceId mac is invalid");
            return "";
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getHiLinkDeviceId value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        return jsqVar == null ? "" : jsqVar.o();
    }

    public static void b(String str, int i) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateDeviceFactoryReset macAddress is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateDeviceFactoryReset value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.a(i);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.a(i);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static int h(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getDeviceFactoryReset macAddress is invalid");
            return -1;
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getDeviceFactoryReset value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            return -1;
        }
        return jsqVar.b();
    }

    public static String b(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getBTVersion mac is invalid");
            return "";
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getBtVersion value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        return jsqVar == null ? "" : jsqVar.c();
    }

    public static void d(String str, int i) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateProductType mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateProductType value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.b(i);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.b(i);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static int r(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getProductType mac is invalid");
            return -1;
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getProductType value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            return -1;
        }
        return jsqVar.t();
    }

    public static void e(String str, int i) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateDeviceConnectState mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateDeviceConnectState value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.e(i);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.e(i);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static int e(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getDeviceConnectState mac is invalid");
            return 3;
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getDeviceConnectState value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            return 3;
        }
        return jsqVar.d();
    }

    public static void a(String str, boolean z) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateHandshakeStatus mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateHandshakeStatus value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.b(z);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.b(z);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static boolean k(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getHandShakeStatus mac is invalid");
            return false;
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getHandShakeStatus value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            return false;
        }
        return jsqVar.x();
    }

    public static void d(String str, boolean z) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateOTAStatus mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateOtaStatus value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.e(z);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.e(z);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static boolean s(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getOTAStatus mac is invalid");
            return false;
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getOtaStatus value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            return false;
        }
        return jsqVar.w();
    }

    public static void a(String str, DeviceCapability deviceCapability) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateDeviceCapability mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateDeviceCapability value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.a(deviceCapability);
            deviceCapability.resetDeviceCapability();
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        }
    }

    public static DeviceCapability d(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getDeviceCapability mac is invalid");
            return new DeviceCapability();
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getDeviceCapability value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            return jte.a(str);
        }
        return jsqVar.a();
    }

    public static void l(String str, String str2) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateDeviceVersion mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateDeviceVersion value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.h(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.h(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static String l(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getDeviceVersion mac is invalid");
            return "";
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getDeviceVersion value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        return jsqVar == null ? "" : jsqVar.n();
    }

    public static void s(String str, String str2) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateUuid mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateUuid value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.p(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.p(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static String u(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getUuid mac is invalid");
            return "";
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getUuid value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        return jsqVar == null ? "" : jsqVar.p();
    }

    public static int p(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getPowerSave mac is invalid");
            return -1;
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getPowerSave value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            return -1;
        }
        return jsqVar.r();
    }

    public static void c(String str, int i) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updatePowerSave mac is invalid");
            return;
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updatePowerSave value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.d(i);
            jsz.f14059a.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.d(i);
            jsz.f14059a.put(str, jsqVar);
        }
    }

    public static void o(String str, String str2) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateUdidFromDevice mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateUdidFromDevice value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.k(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.k(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static String q(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getUdidFromDevice mac is invalid");
            return "";
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getUdidFromDevice value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        return jsqVar == null ? "" : jsqVar.q();
    }

    public static void p(String str, String str2) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateUnConvertedUdid mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateUnConvertedUdid value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.r(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.r(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static String t(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getUnConvertedUdid mac is invalid");
            return "";
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getUnConvertedUdid value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        return jsqVar == null ? "" : jsqVar.s();
    }

    public static void h(String str, String str2) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateDeviceSoftVersion mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateDeviceSoftVersion value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.f(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.f(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static String f(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getDeviceSoftVersion mac is invalid");
            return "";
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getDeviceSoftVersion value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        return jsqVar == null ? "" : jsqVar.g();
    }

    public static String c(String str) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "getDeviceExpandCapability mac is invalid");
            return "";
        }
        jsq jsqVar = jsz.f14059a.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "getDeviceExpandCapability value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        return jsqVar == null ? "" : jsqVar.l();
    }

    public static void c(String str, String str2) {
        if (!v(str)) {
            LogUtil.h("DeviceStatusPackageMgr", "updateDeviceExpandCapability mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateDeviceExpandCapability value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.n(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.n(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static List<String> a() {
        ArrayList arrayList = new ArrayList();
        for (jsq jsqVar : jsz.f14059a.values()) {
            if (jsqVar.d() == 2) {
                arrayList.add(jsqVar.j());
            }
        }
        return arrayList;
    }

    private static boolean v(String str) {
        return !TextUtils.isEmpty(str);
    }

    public static void d(String str, String str2) {
        if (!v(str)) {
            LogUtil.a("DeviceStatusPackageMgr", "updateDeviceCountryCode mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateDeviceCountryCode value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.b(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.b(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static void e(String str, String str2) {
        if (!v(str)) {
            LogUtil.a("DeviceStatusPackageMgr", "updateDeviceEmuiVersion mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        Object[] objArr = new Object[2];
        objArr[0] = "updateDeviceEmuiVersion value: ";
        objArr[1] = Boolean.valueOf(jsqVar == null);
        LogUtil.a("DeviceStatusPackageMgr", objArr);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.a(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.a(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }

    public static void j(String str, String str2) {
        if (!v(str)) {
            LogUtil.a("DeviceStatusPackageMgr", "updateDeviceEmuiVersion mac is invalid");
            return;
        }
        ConcurrentHashMap<String, jsq> concurrentHashMap = jsz.f14059a;
        jsq jsqVar = concurrentHashMap.get(str);
        if (jsqVar == null) {
            jsq jsqVar2 = new jsq();
            jsqVar2.l(str2);
            concurrentHashMap.putIfAbsent(str, jsqVar2);
        } else {
            jsqVar.l(str2);
            concurrentHashMap.put(str, jsqVar);
        }
    }
}
