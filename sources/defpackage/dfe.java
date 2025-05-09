package defpackage;

import android.os.Bundle;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager;
import com.huawei.hwlogsmodel.LogUtil;
import java.security.SecureRandom;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dfe {

    /* renamed from: a, reason: collision with root package name */
    private static volatile dfe f11633a;
    private static final Object b = new Object();

    private dfe() {
        LogUtil.a("DeviceAutoTestTools", "DeviceAutoTestTools getInstance()");
    }

    public static dfe a() {
        if (f11633a == null) {
            synchronized (b) {
                if (f11633a == null) {
                    f11633a = new dfe();
                }
            }
        }
        return f11633a;
    }

    public boolean e() {
        return new dfm(cpp.a(), "deviceautotest").d("deviceauto");
    }

    public boolean j(String str) {
        return !"0".equals(new dfm(cpp.a(), "deviceautotest").a(str));
    }

    public HealthDevice b(String str) {
        String str2;
        JSONObject jSONObject;
        String str3 = "";
        try {
            jSONObject = new JSONObject(new dfm(cpp.a(), "deviceautotest").a(str));
            str2 = (String) jSONObject.get("product_bluetoothname");
        } catch (JSONException unused) {
            str2 = "";
        }
        try {
            str3 = (String) jSONObject.get("product_mac");
        } catch (JSONException unused2) {
            LogUtil.b("DeviceAutoTestTools", "DeviceAutoTestTools buildAutoDevice JSONException");
            return new cet(str2, str3, str3);
        }
        return new cet(str2, str3, str3);
    }

    private void e(String str, HealthData healthData) {
        try {
            String str2 = (String) new JSONObject(new dfm(cpp.a(), "deviceautotest").a(str)).get("product_random_insert");
            String a2 = new dfm(cpp.a(), "insert".equals(str2) ? "deviceautotestinterdata" : "deviceautotestdata").a(str);
            if (healthData instanceof deb) {
                e(str2, a2, (deb) healthData);
            } else if (healthData instanceof ckm) {
                a(str2, a2, (ckm) healthData);
            } else if (healthData instanceof deo) {
                b(str2, a2, (deo) healthData);
            } else if (healthData instanceof der) {
                b(str2, a2, (der) healthData);
            } else {
                LogUtil.h("DeviceAutoTestTools", "DeviceAutoTestTools HealthData Class is error ");
            }
        } catch (JSONException unused) {
            LogUtil.b("DeviceAutoTestTools", "DeviceAutoTestTools JSONException");
        }
    }

    private void e(String str, String str2, deb debVar) throws JSONException {
        float parseFloat;
        if (str.equals("random")) {
            JSONArray jSONArray = new JSONArray(str2);
            int nextInt = new SecureRandom().nextInt(100) - 1;
            if (nextInt < 0) {
                nextInt = 0;
            }
            parseFloat = jSONArray.getJSONObject(nextInt).getInt("bloodsugar");
        } else {
            if ("insert".equals(str)) {
                try {
                    parseFloat = Float.parseFloat(new JSONObject(str2).getString("bloodsugar"));
                } catch (NumberFormatException unused) {
                    LogUtil.b("DeviceAutoTestTools", "DeviceAutoTestTools setBloodSugar Exception");
                }
            }
            parseFloat = 0.0f;
        }
        LogUtil.c("DeviceAutoTestTools", "DeviceAutoTestTools setBloodSugar bloodsugar =", Float.valueOf(parseFloat));
        debVar.setBloodSugar(parseFloat);
        debVar.setStartTime(new Date().getTime());
        debVar.setEndTime(new Date().getTime());
        debVar.setSequenceNumber(0);
    }

    private void a(String str, String str2, ckm ckmVar) throws JSONException {
        float parseFloat;
        if (str.equals("random")) {
            JSONArray jSONArray = new JSONArray(str2);
            int nextInt = new SecureRandom().nextInt(100) - 1;
            if (nextInt < 0) {
                nextInt = 0;
            }
            parseFloat = jSONArray.getJSONObject(nextInt).getInt("weight");
        } else {
            if (str.equals("insert")) {
                try {
                    parseFloat = Float.parseFloat(new JSONObject(str2).getString("weight"));
                } catch (NumberFormatException unused) {
                    LogUtil.h("DeviceAutoTestTools", "DeviceAutoTestTools setWeightData Exception");
                }
            }
            parseFloat = 0.0f;
        }
        LogUtil.c("DeviceAutoTestTools", "DeviceAutoTestTools setWeightData weight =", Float.valueOf(parseFloat));
        ckmVar.setWeight(parseFloat);
        ckmVar.setBodyFatRat(20.0f);
        ckmVar.setStartTime(new Date().getTime());
        ckmVar.setEndTime(new Date().getTime());
    }

    private void b(String str, String str2, deo deoVar) throws JSONException {
        int i;
        int i2;
        int i3;
        if (str.equals("random")) {
            JSONArray jSONArray = new JSONArray(str2);
            int nextInt = new SecureRandom().nextInt(100) - 1;
            JSONObject jSONObject = jSONArray.getJSONObject(nextInt >= 0 ? nextInt : 0);
            r5 = jSONObject.getInt("low");
            int i4 = jSONObject.getInt(MediaManager.ROPE_MEDIA_HIGH);
            i3 = jSONObject.getInt("heart");
            i2 = i4;
        } else if (str.equals("insert")) {
            try {
                JSONObject jSONObject2 = new JSONObject(str2);
                i = Integer.parseInt(jSONObject2.getString("low"));
                try {
                    i2 = Integer.parseInt(jSONObject2.getString(MediaManager.ROPE_MEDIA_HIGH));
                    try {
                        r5 = Integer.parseInt(jSONObject2.getString("heart"));
                    } catch (NumberFormatException unused) {
                        LogUtil.b("DeviceAutoTestTools", "setBloodPressureData Exception");
                        i3 = r5;
                        r5 = i;
                        LogUtil.c("DeviceAutoTestTools", "low =", Integer.valueOf(r5), " high=", Integer.valueOf(i2), "heartRate=", Integer.valueOf(i3));
                        deoVar.setSystolic((short) i2);
                        deoVar.setDiastolic((short) r5);
                        deoVar.setHeartRate((short) i3);
                    }
                } catch (NumberFormatException unused2) {
                    i2 = 0;
                }
            } catch (NumberFormatException unused3) {
                i = 0;
                i2 = 0;
            }
            i3 = r5;
            r5 = i;
        } else {
            i3 = 0;
            i2 = 0;
        }
        LogUtil.c("DeviceAutoTestTools", "low =", Integer.valueOf(r5), " high=", Integer.valueOf(i2), "heartRate=", Integer.valueOf(i3));
        deoVar.setSystolic((short) i2);
        deoVar.setDiastolic((short) r5);
        deoVar.setHeartRate((short) i3);
    }

    private void b(String str, String str2, der derVar) throws JSONException {
        if (str.equals("random")) {
            JSONArray jSONArray = new JSONArray(str2);
            int nextInt = new SecureRandom().nextInt(100) - 1;
            r3 = jSONArray.getJSONObject(nextInt >= 0 ? nextInt : 0).getInt("heart");
        } else if (str.equals("insert")) {
            try {
                r3 = Integer.parseInt(new JSONObject(str2).getString("heart"));
            } catch (NumberFormatException unused) {
                LogUtil.b("DeviceAutoTestTools", "DeviceAutoTestTools setHeartRateData Exception");
            }
        }
        LogUtil.c("DeviceAutoTestTools", "DeviceAutoTestTools heartRateCount =", Integer.valueOf(r3));
        derVar.setHeartRate(Integer.valueOf(r3));
    }

    public HealthData a(String str) {
        deb debVar = new deb();
        e(str, debVar);
        return debVar;
    }

    public HealthData e(String str) {
        deo deoVar = new deo();
        e(str, deoVar);
        return deoVar;
    }

    public HealthData c(String str) {
        ckm ckmVar = new ckm();
        e(str, ckmVar);
        return ckmVar;
    }

    public HealthData d(String str) {
        der derVar = new der();
        e(str, derVar);
        return derVar;
    }

    public Bundle TR_(HealthDevice.HealthDeviceKind healthDeviceKind, String str) {
        Bundle bundle = new Bundle();
        try {
            JSONObject jSONObject = new JSONObject(new dfm(cpp.a(), "deviceautotest").a(str));
            String str2 = jSONObject.get("product_bluetoothname") instanceof String ? (String) jSONObject.get("product_bluetoothname") : null;
            String str3 = jSONObject.get("product_mac") instanceof String ? (String) jSONObject.get("product_mac") : null;
            String str4 = jSONObject.get("product_random_insert") instanceof String ? (String) jSONObject.get("product_random_insert") : null;
            bundle.putString("product_bluetoothname", str2);
            bundle.putString("product_mac", str3);
            TQ_(str4, new dfm(cpp.a(), "insert".equals(str4) ? "deviceautotestinterdata" : "deviceautotestdata").a(str), healthDeviceKind, bundle);
        } catch (JSONException unused) {
            LogUtil.b("DeviceAutoTestTools", "buildAutoTestSilentData JSONException");
        }
        return bundle;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r20v0, types: [android.os.Bundle] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.String] */
    private void TQ_(String str, String str2, HealthDevice.HealthDeviceKind healthDeviceKind, Bundle bundle) throws JSONException {
        JSONObject jSONObject;
        String str3;
        HealthDevice.HealthDeviceKind healthDeviceKind2;
        ?? r11;
        String str4;
        String str5;
        String string;
        HealthDevice.HealthDeviceKind healthDeviceKind3 = healthDeviceKind;
        if ("random".equals(str)) {
            JSONArray jSONArray = new JSONArray(str2);
            int nextInt = new SecureRandom().nextInt(100) - 1;
            if (nextInt < 0) {
                nextInt = 0;
            }
            jSONObject = jSONArray.getJSONObject(nextInt);
        } else {
            jSONObject = "insert".equals(str) ? new JSONObject(str2) : null;
        }
        String str6 = "0";
        if (jSONObject != null) {
            try {
                try {
                } catch (NumberFormatException e) {
                    e = e;
                    str3 = "0";
                    healthDeviceKind2 = healthDeviceKind3;
                }
            } catch (NumberFormatException e2) {
                e = e2;
                ?? r2 = "0";
                str3 = r2;
                healthDeviceKind2 = r2;
            }
            if (healthDeviceKind3 == HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE) {
                ?? string2 = jSONObject.getString("low");
                String string3 = jSONObject.getString(MediaManager.ROPE_MEDIA_HIGH);
                try {
                    str6 = jSONObject.getString("heart");
                    bundle.putString("low", string2);
                    bundle.putString(MediaManager.ROPE_MEDIA_HIGH, string3);
                    bundle.putString("heart", str6);
                    r11 = string2;
                    str5 = string3;
                    str4 = str6;
                } catch (NumberFormatException e3) {
                    e = e3;
                    str3 = str6;
                    str6 = string3;
                    healthDeviceKind2 = string2;
                    LogUtil.h("DeviceAutoTestTools", "e1 = ", e.getMessage());
                    r11 = healthDeviceKind2;
                    str4 = str3;
                    str5 = str6;
                    LogUtil.c("DeviceAutoTestTools", "argFirst =", r11, " argSecond=", str5, "argThird=", str4);
                }
                LogUtil.c("DeviceAutoTestTools", "argFirst =", r11, " argSecond=", str5, "argThird=", str4);
            }
            if (healthDeviceKind3 == HealthDevice.HealthDeviceKind.HDK_WEIGHT) {
                string = jSONObject.getString("weight");
                bundle.putString("weight", string);
            } else if (healthDeviceKind3 == HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR) {
                string = jSONObject.getString("bloodsugar");
                bundle.putString("bloodsugar", string);
            }
            r11 = string;
            str5 = "0";
            str4 = str5;
            LogUtil.c("DeviceAutoTestTools", "argFirst =", r11, " argSecond=", str5, "argThird=", str4);
        }
        r11 = "0";
        str5 = r11;
        str4 = str5;
        LogUtil.c("DeviceAutoTestTools", "argFirst =", r11, " argSecond=", str5, "argThird=", str4);
    }
}
