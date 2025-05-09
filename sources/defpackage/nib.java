package defpackage;

import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class nib {
    private static final Map<String, Integer> e = Collections.unmodifiableMap(new HashMap<String, Integer>() { // from class: nib.4
        private static final long serialVersionUID = -696935313487950289L;

        {
            put("fallAsleepTime", 44201);
            put("sleepEfficiency", 44207);
            put("sleepLatency", 44204);
            put("sleepScore", 44203);
            put("snoreFreq", 44208);
            put("validData", 44206);
            put("wakeupTime", 44202);
            put("deepPart", 44106);
            put("minHeartrate", 44209);
            put("maxHeartrate", 44210);
            put("minOxygenSaturation", 44211);
            put("maxOxygenSaturation", 44212);
            put("minBreathrate", 44213);
            put("maxBreathrate", 44214);
            put("avgHeartrate", 44219);
            put("minHeartrateBaseline", 44220);
            put("maxHeartrateBaseline", 44221);
            put("heartrateDayToBaseline", 44222);
            put("avgOxygenSaturation", 44223);
            put("minOxygenSaturationBaseline", 44224);
            put("maxOxygenSaturationBaseline", 44225);
            put("oxygenSaturationDayToBaseline", 44226);
            put("avgBreathrate", 44227);
            put("minBreathrateBaseline", 44228);
            put("maxBreathrateBaseline", 44229);
            put("breathrateDayToBaseline", 44230);
            put("avgHrv", 44231);
            put("minHrvBaseline", 44232);
            put("maxHrvBaseline", 44233);
            put("hrvDayToBaseline", 44234);
        }
    });

    public static List<HiHealthData> e(JSONObject jSONObject, nhv nhvVar) {
        ArrayList arrayList = new ArrayList(16);
        e(arrayList, nhvVar);
        d(arrayList, nhvVar);
        c(arrayList, nhvVar);
        if (nhvVar.b() == 1) {
            return arrayList;
        }
        v(arrayList, nhvVar, jSONObject);
        u(arrayList, nhvVar, jSONObject);
        z(arrayList, nhvVar, jSONObject);
        ab(arrayList, nhvVar, jSONObject);
        i(arrayList, nhvVar, jSONObject);
        p(arrayList, nhvVar, jSONObject);
        o(arrayList, nhvVar, jSONObject);
        x(arrayList, nhvVar, jSONObject);
        k(arrayList, nhvVar, jSONObject);
        r(arrayList, nhvVar, jSONObject);
        f(arrayList, nhvVar, jSONObject);
        d(arrayList, nhvVar, jSONObject);
        q(arrayList, nhvVar, jSONObject);
        l(arrayList, nhvVar, jSONObject);
        g(arrayList, nhvVar, jSONObject);
        c(arrayList, nhvVar, jSONObject);
        y(arrayList, nhvVar, jSONObject);
        m(arrayList, nhvVar, jSONObject);
        w(arrayList, nhvVar, jSONObject);
        b(arrayList, nhvVar, jSONObject);
        s(arrayList, nhvVar, jSONObject);
        j(arrayList, nhvVar, jSONObject);
        a(arrayList, nhvVar, jSONObject);
        e(arrayList, nhvVar, jSONObject);
        t(arrayList, nhvVar, jSONObject);
        n(arrayList, nhvVar, jSONObject);
        h(arrayList, nhvVar, jSONObject);
        return arrayList;
    }

    private static void e(List<HiHealthData> list, nhv nhvVar) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setDeviceUuid(nhvVar.a());
        hiHealthData.setStartTime(nhvVar.c());
        hiHealthData.setEndTime(nhvVar.c());
        hiHealthData.setType(e.get("fallAsleepTime").intValue());
        hiHealthData.setValue(nhvVar.e());
        list.add(hiHealthData);
    }

    private static void v(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "sleepEfficiency");
    }

    private static void u(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "sleepLatency");
    }

    private static void z(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "sleepScore");
    }

    private static void ab(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "snoreFreq");
    }

    private static void p(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "minHeartrate");
    }

    private static void o(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "maxHeartrate");
    }

    private static void x(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        a(list, nhvVar, jSONObject, "minOxygenSaturation");
    }

    private static void k(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        a(list, nhvVar, jSONObject, "maxOxygenSaturation");
    }

    private static void r(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        a(list, nhvVar, jSONObject, "minBreathrate");
    }

    private static void f(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        a(list, nhvVar, jSONObject, "maxBreathrate");
    }

    private static void d(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "avgHeartrate");
    }

    private static void q(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "minHeartrateBaseline");
    }

    private static void l(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "maxHeartrateBaseline");
    }

    private static void g(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "heartrateDayToBaseline");
    }

    private static void c(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "avgOxygenSaturation");
    }

    private static void y(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "minOxygenSaturationBaseline");
    }

    private static void m(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "maxOxygenSaturationBaseline");
    }

    private static void w(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "oxygenSaturationDayToBaseline");
    }

    private static void b(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "avgBreathrate");
    }

    private static void s(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "minBreathrateBaseline");
    }

    private static void j(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "maxBreathrateBaseline");
    }

    private static void a(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "breathrateDayToBaseline");
    }

    private static void e(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "avgHrv");
    }

    private static void t(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "minHrvBaseline");
    }

    private static void n(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "maxHrvBaseline");
    }

    private static void h(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "hrvDayToBaseline");
    }

    private static void c(List<HiHealthData> list, nhv nhvVar) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setDeviceUuid(nhvVar.a());
        hiHealthData.setStartTime(nhvVar.c());
        hiHealthData.setEndTime(nhvVar.c());
        hiHealthData.setType(e.get("validData").intValue());
        hiHealthData.setValue(nhvVar.b());
        list.add(hiHealthData);
    }

    private static void d(List<HiHealthData> list, nhv nhvVar) {
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setDeviceUuid(nhvVar.a());
        hiHealthData.setStartTime(nhvVar.c());
        hiHealthData.setEndTime(nhvVar.c());
        hiHealthData.setType(e.get("wakeupTime").intValue());
        hiHealthData.setValue(nhvVar.c());
        list.add(hiHealthData);
    }

    private static void i(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject) {
        c(list, nhvVar, jSONObject, "deepPart");
    }

    private static void c(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject, String str) {
        if (!jSONObject.has(str)) {
            ReleaseLogUtil.c("BTSYNC_CoreSleep_CoreSleepStatUtil", str, "setIntegerStatData is null");
            return;
        }
        try {
            HiHealthData hiHealthData = new HiHealthData();
            hiHealthData.setDeviceUuid(nhvVar.a());
            long b = b(nhvVar);
            hiHealthData.setStartTime(b);
            hiHealthData.setEndTime(b);
            hiHealthData.setType(e.get(str).intValue());
            hiHealthData.setValue(jSONObject.getInt(str));
            list.add(hiHealthData);
        } catch (JSONException unused) {
            ReleaseLogUtil.c("BTSYNC_CoreSleep_CoreSleepStatUtil", str, "setIntegerStatData jsonException");
        }
    }

    private static void a(List<HiHealthData> list, nhv nhvVar, JSONObject jSONObject, String str) {
        if (!jSONObject.has(str)) {
            ReleaseLogUtil.c("BTSYNC_CoreSleep_CoreSleepStatUtil", str, "setDoubleStatData is null");
            return;
        }
        try {
            HiHealthData hiHealthData = new HiHealthData();
            hiHealthData.setDeviceUuid(nhvVar.a());
            long b = b(nhvVar);
            hiHealthData.setStartTime(b);
            hiHealthData.setEndTime(b);
            hiHealthData.setType(e.get(str).intValue());
            hiHealthData.setValue(jSONObject.getDouble(str));
            list.add(hiHealthData);
        } catch (JSONException unused) {
            ReleaseLogUtil.c("BTSYNC_CoreSleep_CoreSleepStatUtil", str, "setDoubleStatData jsonException");
        }
    }

    public static boolean a() {
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepStatUtil", "enter checkDeviceBedDetectionCapability");
        DeviceInfo b = b();
        if (b == null) {
            LogUtil.h("CoreSleepStatUtil", "checkDeviceBedDetectionCapability() deviceInfo is null");
            return false;
        }
        boolean c = cwi.c(b, 199);
        ReleaseLogUtil.e("BTSYNC_CoreSleep_CoreSleepStatUtil", "isSupportBedDetection: ", Boolean.valueOf(c));
        return c;
    }

    public static DeviceInfo b() {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "CoreSleepStatUtil");
        if (deviceList == null) {
            LogUtil.h("CoreSleepStatUtil", "getActiveMainNotAw70Device() deviceInfoList is null.");
            return null;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (!cvt.c(next.getProductType())) {
                deviceInfo = next;
                break;
            }
        }
        LogUtil.a("CoreSleepStatUtil", "getActiveMainNotAw70Device() deviceInfo ", deviceInfo);
        return deviceInfo;
    }

    private static long b(nhv nhvVar) {
        long c = nhvVar.c();
        long e2 = jdl.e(c, 20, 0);
        return c <= e2 ? c : e2 + 14400000;
    }

    public static void e(String str, boolean z, int i, String str2) {
        String str3 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "_" + bgv.e(str) + "_CoreSleepProcess";
        LogUtil.a("CoreSleepStatUtil", "saveCodeToStorage storageKey:", blt.a(str), ",storageKey:", str3, ",tag:", str2);
        SharedPreferenceManager.e(String.valueOf(i), str3, z);
    }
}
