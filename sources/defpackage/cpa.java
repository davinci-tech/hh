package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.health.device.manager.ResourceFileListener;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* loaded from: classes3.dex */
public class cpa {
    public static final String b = cez.d;
    public static final Map<String, Integer> f = Collections.unmodifiableMap(new HashMap<String, Integer>() { // from class: cpa.2
        {
            put("c943c933-442e-4c34-bcd0-66597f24aaed", 672);
            put("e835d102-af95-48a6-ae13-2983bc06f5c0", 140);
            put("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4", 106);
            put("f005e900-7dcf-49f7-a1b0-a98e46bf62ec", Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_VOLLEYBALL));
            put("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4", 94);
            put("b29df4e3-b1f7-4e40-960d-4cfb63ccca05", 385);
            put("7a1063dd-0e0f-4a72-9939-461476ff0259", 84);
            put("33123f39-7fc1-420b-9882-a4b0d6c61100", 82);
            put("ccd1f0f8-8c57-4bd7-a884-0ef38482f15f", 84);
            put("34fa0346-d46c-439d-9cb0-2f696618846b", 82);
            put("a8ba095d-4123-43c4-a30a-0240011c58de", 84);
            put("e4b0b1d5-2003-4d88-8b5f-c4f64542040b", 82);
        }
    });
    public static final List<String> d = Collections.unmodifiableList(new ArrayList<String>() { // from class: cpa.1
        {
            add("c943c933-442e-4c34-bcd0-66597f24aaed");
            add("e835d102-af95-48a6-ae13-2983bc06f5c0");
            add("34fa0346-d46c-439d-9cb0-2f696618846b");
            add("33123f39-7fc1-420b-9882-a4b0d6c61100");
            add("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4");
            add("b29df4e3-b1f7-4e40-960d-4cfb63ccca05");
            add("e4b0b1d5-2003-4d88-8b5f-c4f64542040b");
            add("7a1063dd-0e0f-4a72-9939-461476ff0259");
            add("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4");
            add("ccd1f0f8-8c57-4bd7-a884-0ef38482f15f");
            add("a8ba095d-4123-43c4-a30a-0240011c58de");
        }
    });
    public static final Map<String, String> g = Collections.unmodifiableMap(new HashMap<String, String>() { // from class: cpa.7
        {
            put("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4", "HAG-B19");
            put("b29df4e3-b1f7-4e40-960d-4cfb63ccca05", "Hagrid-B29");
            put("e835d102-af95-48a6-ae13-2983bc06f5c0", "HEM-B19");
            put("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4", "LUP-B19");
            put("c943c933-442e-4c34-bcd0-66597f24aaed", "DOBBY-B19");
        }
    });
    public static final Map<String, String> h = Collections.unmodifiableMap(new HashMap<String, String>() { // from class: cpa.8
        {
            put("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4", "Hagrid-B19");
            put("b29df4e3-b1f7-4e40-960d-4cfb63ccca05", "Hagrid2021-B19");
            put("e835d102-af95-48a6-ae13-2983bc06f5c0", "Herm-B19");
            put("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4", "Lupin-B19HN");
            put("c943c933-442e-4c34-bcd0-66597f24aaed", "Dobby-B19");
        }
    });
    public static final Map<String, Integer> i = Collections.unmodifiableMap(new HashMap<String, Integer>() { // from class: cpa.10
        {
            put("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4", 54);
            put("b29df4e3-b1f7-4e40-960d-4cfb63ccca05", 599);
            put("e835d102-af95-48a6-ae13-2983bc06f5c0", 70);
            put("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4", 263);
            put("c943c933-442e-4c34-bcd0-66597f24aaed", 70);
        }
    });
    public static final List<String> e = Collections.unmodifiableList(new ArrayList<String>() { // from class: cpa.6
        {
            add("34fa0346-d46c-439d-9cb0-2f696618846b");
            add("33123f39-7fc1-420b-9882-a4b0d6c61100");
            add("ccd1f0f8-8c57-4bd7-a884-0ef38482f15f");
            add("7a1063dd-0e0f-4a72-9939-461476ff0259");
            add("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4");
            add("b29df4e3-b1f7-4e40-960d-4cfb63ccca05");
            add("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4");
            add("e835d102-af95-48a6-ae13-2983bc06f5c0");
            add("c943c933-442e-4c34-bcd0-66597f24aaed");
        }
    });
    public static final List<Integer> l = Collections.unmodifiableList(new ArrayList<Integer>() { // from class: cpa.9
        {
            add(94);
            add(95);
            add(385);
            add(106);
            add(140);
            add(168);
            add(672);
        }
    });
    public static final List<Integer> n = Collections.unmodifiableList(new ArrayList<Integer>() { // from class: cpa.13
        {
            add(94);
            add(95);
            add(385);
            add(106);
            add(140);
            add(168);
            add(672);
            add(82);
            add(85);
            add(84);
            add(86);
            add(57);
            add(48);
        }
    });
    public static final Map<String, String> c = Collections.unmodifiableMap(new HashMap<String, String>() { // from class: cpa.11
        {
            put("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4", "007B");
            put("b29df4e3-b1f7-4e40-960d-4cfb63ccca05", "M00F");
            put("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4", "N001");
            put("e835d102-af95-48a6-ae13-2983bc06f5c0", "M00D");
            put("c943c933-442e-4c34-bcd0-66597f24aaed", "M0CJ");
        }
    });
    public static final Map<String, String> j = Collections.unmodifiableMap(new HashMap<String, String>() { // from class: cpa.5
        {
            put("007B", "8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4");
            put("M00F", "b29df4e3-b1f7-4e40-960d-4cfb63ccca05");
            put("N001", "25c6df38-ca23-11e9-a32f-2a2ae2dbcce4");
            put("M00D", "e835d102-af95-48a6-ae13-2983bc06f5c0");
            put("M0CJ", "c943c933-442e-4c34-bcd0-66597f24aaed");
            put("117K", "e4b0b1d5-2003-4d88-8b5f-c4f64542040b");
            put("117V", "a8ba095d-4123-43c4-a30a-0240011c58de");
        }
    });
    private static final List<String> o = new ArrayList<String>() { // from class: cpa.3
        {
            add("007B");
            add("M00F");
            add("M00D");
        }
    };

    /* renamed from: a, reason: collision with root package name */
    public static final Set<String> f11398a = Collections.unmodifiableSet(new HashSet<String>() { // from class: cpa.4
        {
            add("M0CJ");
            add("M00D");
            add("0059");
            add("007B");
            add("M00F");
            add("117K");
            add("005A");
            add("N001");
            add("117V");
        }
    });

    public static boolean b(double d2) {
        return d2 > 0.0d;
    }

    private static boolean a(String str, String str2, int i2) {
        LogUtil.a("HonourDeviceConstants", "isSpecifiedDevice productId ", str, " deviceProductId ", str2, " deviceType ", Integer.valueOf(i2));
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        if (str2.equals(str)) {
            return true;
        }
        dcz d2 = ResourceManager.e().d(str);
        if (d2 == null) {
            LogUtil.h("HonourDeviceConstants", "isSpecifiedDevice productInfo is null");
            return false;
        }
        String c2 = d2.c("deviceId");
        LogUtil.a("HonourDeviceConstants", "isSpecifiedDevice deviceId ", c2);
        return String.valueOf(i2).equals(c2);
    }

    public static boolean r(String str) {
        return a(str, "c943c933-442e-4c34-bcd0-66597f24aaed", 672);
    }

    public static boolean w(String str) {
        return a(str, "e835d102-af95-48a6-ae13-2983bc06f5c0", 140);
    }

    public static boolean v(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HonourDeviceConstants", "isNewHagWeightDevice productId = null");
            return false;
        }
        if (!"b29df4e3-b1f7-4e40-960d-4cfb63ccca05".equals(str)) {
            return false;
        }
        LogUtil.a("HonourDeviceConstants", "isNewHagWeightDevice is HAG2021 device");
        return true;
    }

    public static boolean z(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "6d5416d9-2167-41df-ab10-c492e152b44f".equals(str);
    }

    public static boolean ad(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return d.contains(str);
    }

    public static boolean ag(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return f11398a.contains(str);
    }

    public static boolean aw(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "34fa0346-d46c-439d-9cb0-2f696618846b".equals(str) || ("33123f39-7fc1-420b-9882-a4b0d6c61100".equals(str) || "ccd1f0f8-8c57-4bd7-a884-0ef38482f15f".equals(str));
    }

    public static boolean ab(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "34fa0346-d46c-439d-9cb0-2f696618846b".equals(str) || ae(str) || ("33123f39-7fc1-420b-9882-a4b0d6c61100".equals(str) || "ccd1f0f8-8c57-4bd7-a884-0ef38482f15f".equals(str));
    }

    public static boolean ac(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "6d5416d9-2167-41df-ab10-c492e152b44f".equals(str) || ab(str);
    }

    public static boolean aa(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "7a1063dd-0e0f-4a72-9939-461476ff0259".equals(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0057 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean c(java.lang.String r4, java.lang.String r5) {
        /*
            java.lang.String r0 = defpackage.cpw.d(r4)
            java.lang.String r1 = ", uniqueId="
            java.lang.String r2 = defpackage.cpw.a(r5)
            java.lang.String r3 = "isTlvScaleProduct productId="
            java.lang.Object[] r0 = new java.lang.Object[]{r3, r0, r1, r2}
            java.lang.String r1 = "HonourDeviceConstants"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 != 0) goto L5d
            r4.hashCode()
            int r0 = r4.hashCode()
            r1 = 299435131(0x11d9047b, float:3.4239325E-28)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L4a
            r1 = 759413903(0x2d43bc8f, float:1.1126335E-11)
            if (r0 == r1) goto L3f
            r1 = 912505950(0x3663bc5e, float:3.393528E-6)
            if (r0 == r1) goto L34
            goto L52
        L34:
            java.lang.String r0 = "b29df4e3-b1f7-4e40-960d-4cfb63ccca05"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L3d
            goto L52
        L3d:
            r4 = r2
            goto L55
        L3f:
            java.lang.String r0 = "c943c933-442e-4c34-bcd0-66597f24aaed"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L48
            goto L52
        L48:
            r4 = r3
            goto L55
        L4a:
            java.lang.String r0 = "e835d102-af95-48a6-ae13-2983bc06f5c0"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L54
        L52:
            r4 = -1
            goto L55
        L54:
            r4 = 0
        L55:
            if (r4 == 0) goto L5c
            if (r4 == r3) goto L5c
            if (r4 == r2) goto L5c
            goto L5d
        L5c:
            return r3
        L5d:
            r4 = 19
            boolean r4 = defpackage.cji.a(r5, r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cpa.c(java.lang.String, java.lang.String):boolean");
    }

    public static boolean ae(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HonourDeviceConstants", "isNewScaleProduct productId = null");
            return false;
        }
        if ("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4".equals(str) || "25c6df38-ca23-11e9-a32f-2a2ae2dbcce4".equals(str)) {
            LogUtil.a("HonourDeviceConstants", "is wsp scale device (hygrid or mini or herm)");
            return true;
        }
        dcz d2 = ResourceManager.e().d(str);
        if (d2 == null) {
            LogUtil.h("HonourDeviceConstants", "isNewScaleProduct productInfo = null");
            return false;
        }
        String c2 = d2.c("is_huawei_wsp_scale");
        LogUtil.a("HonourDeviceConstants", "is wsp scale device product.xml is_huawei_wsp_scale= ", c2);
        return TextUtils.equals("true", c2);
    }

    public static boolean x(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HonourDeviceConstants", "isDualModeProduct productId = null");
            return false;
        }
        if ("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4".equals(str)) {
            LogUtil.a("HonourDeviceConstants", "isDualModeProduct is hagrid device");
            return true;
        }
        dcz d2 = ResourceManager.e().d(str);
        if (d2 == null) {
            LogUtil.h("HonourDeviceConstants", "isDualModeProduct productInfo = null");
            return false;
        }
        String c2 = d2.c("is_dual_mode");
        LogUtil.a("HonourDeviceConstants", "isDualModeProduct product.xml is_dual_mode=", c2);
        return TextUtils.equals("true", c2);
    }

    public static boolean ah(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "25c6df38-ca23-11e9-a32f-2a2ae2dbcce4".equals(str);
    }

    public static boolean ai(String str) {
        return "8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4".equals(str) || "b29df4e3-b1f7-4e40-960d-4cfb63ccca05".equals(str);
    }

    public static boolean aq(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HonourDeviceConstants", "isSupportSelectUserProduct productId = null");
            return false;
        }
        if ("34fa0346-d46c-439d-9cb0-2f696618846b".equals(str) || "8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4".equals(str) || "25c6df38-ca23-11e9-a32f-2a2ae2dbcce4".equals(str)) {
            LogUtil.a("HonourDeviceConstants", "isSupportSelectUserProduct is (huawei / hagird / mini)");
            return true;
        }
        dcz d2 = ResourceManager.e().d(str);
        if (d2 == null) {
            LogUtil.h("HonourDeviceConstants", "isSupportSelectUserProduct productInfo = null");
            return false;
        }
        String c2 = d2.c("is_support_multi_user");
        LogUtil.a("HonourDeviceConstants", "isSupportSelectUserProduct product.xml is_support_multi_user=", c2);
        return TextUtils.equals("true", c2);
    }

    public static String g(String str) {
        String str2 = h.get(str);
        return str2 == null ? b(str) : str2;
    }

    private static int ay(String str) {
        Integer num = i.get(str);
        if (num == null) {
            num = Integer.valueOf(a(str));
        }
        return num.intValue();
    }

    public static void c(Context context, String str, String str2) {
        String str3;
        LogUtil.a("HonourDeviceConstants", "enter sendWeightDetailSyncSuccessBroadcast");
        if (context == null) {
            return;
        }
        if (!ae(str)) {
            LogUtil.a("HonourDeviceConstants", "!sendWeightDetailSyncSuccessBroadcast isHyGrideScaleDevice");
            return;
        }
        if (ae(str)) {
            str3 = k(str2);
            if (TextUtils.isEmpty(str3)) {
                str3 = k(str);
            }
        } else {
            str3 = null;
        }
        coz.d("HonourDeviceConstants", str3, context, str2);
        LogUtil.a("HonourDeviceConstants", "device version = ", str3, ", productId = ", str);
        MeasurableDevice d2 = ceo.d().d(str2, true);
        if (d2 == null) {
            LogUtil.h("HonourDeviceConstants", "!sendWeightDetailSyncSuccessBroadcast device null");
            return;
        }
        Intent intent = new Intent("com.huawei.health.weight_detail_sync_success");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        Bundle bundle = new Bundle();
        bundle.putString("deviceVersion", str3);
        bundle.putString("deviceModel", g(str));
        bundle.putString("deviceMac", d2.getAddress());
        bundle.putString("deviceUniqueId", str2);
        bundle.putInt("productType", ay(str));
        intent.putExtras(bundle);
        context.sendBroadcast(intent);
    }

    public static boolean g() {
        return cjx.e().h("6d5416d9-2167-41df-ab10-c492e152b44f") ? cjx.e().d("6d5416d9-2167-41df-ab10-c492e152b44f") != null : cjx.e().a("6d5416d9-2167-41df-ab10-c492e152b44f") != null;
    }

    public static boolean y(String str) {
        if (z(str)) {
            return g();
        }
        return false;
    }

    public static boolean af(String str) {
        return (TextUtils.isEmpty(str) || cjx.e().a(str) == null || !ab(str)) ? false : true;
    }

    public static void n() {
        dcz d2 = ResourceManager.e().d("6d5416d9-2167-41df-ab10-c492e152b44f");
        if (d2 == null || !g()) {
            return;
        }
        cfl.a().e(d2.s(), ResourceManager.e().c("6d5416d9-2167-41df-ab10-c492e152b44f") + File.separator + d2.k());
        cfl.a().c("54C9739F-CA5C-4347-9F00-75B9DDF2C649");
    }

    public static Bundle JY_() {
        int i2;
        Bundle bundle = new Bundle();
        bundle.putString("AM16_PACKAGE_NAME", "6d5416d9-2167-41df-ab10-c492e152b44f");
        bundle.putBoolean("EXT_IS_MUTE", false);
        bundle.putInt("AM16_MEASURE_TYPE", 1);
        bundle.putBoolean("IS_PLAY_HEART_RATE_TOO_HIGH", false);
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi != null) {
            LogUtil.a("HonourDeviceConstants", "healthDataMgrApi is not null");
            HeartZoneConf heartZoneConf = healthDataMgrApi.getHeartZoneConf();
            if (heartZoneConf != null) {
                LogUtil.a("HonourDeviceConstants", "heartZoneConf is not null");
                i2 = heartZoneConf.getWarningLimitHR();
                LogUtil.a("HonourDeviceConstants", "getAm16Bundle rate warning ", Integer.valueOf(i2));
                bundle.putInt("HEART_RATE_WARN_UPPER", i2);
                return bundle;
            }
        }
        i2 = 170;
        LogUtil.a("HonourDeviceConstants", "getAm16Bundle rate warning ", Integer.valueOf(i2));
        bundle.putInt("HEART_RATE_WARN_UPPER", i2);
        return bundle;
    }

    public static Bundle Ka_() {
        int i2;
        Bundle bundle = new Bundle();
        bundle.putString("AM16_PACKAGE_NAME", "6d5416d9-2167-41df-ab10-c492e152b44f");
        bundle.putBoolean("EXT_IS_MUTE", false);
        boolean z = true;
        bundle.putInt("AM16_MEASURE_TYPE", 1);
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi != null) {
            LogUtil.a("HonourDeviceConstants", "getHeartSeeBundle healthDataMgrApi is not null");
            HeartZoneConf heartZoneConf = healthDataMgrApi.getHeartZoneConf();
            if (heartZoneConf != null) {
                LogUtil.a("HonourDeviceConstants", "getHeartSeeBundle heartZoneConf is not null");
                z = heartZoneConf.isWarningEnble();
                i2 = heartZoneConf.getWarningLimitHR();
                LogUtil.a("HonourDeviceConstants", "getHeartSeeBundle rate warning ", Integer.valueOf(i2));
                bundle.putBoolean("IS_PLAY_HEART_RATE_TOO_HIGH", z);
                bundle.putInt("HEART_RATE_WARN_UPPER", i2);
                return bundle;
            }
        }
        i2 = 170;
        LogUtil.a("HonourDeviceConstants", "getHeartSeeBundle rate warning ", Integer.valueOf(i2));
        bundle.putBoolean("IS_PLAY_HEART_RATE_TOO_HIGH", z);
        bundle.putInt("HEART_RATE_WARN_UPPER", i2);
        return bundle;
    }

    public static Bundle JZ_() {
        int i2;
        Bundle bundle = new Bundle();
        bundle.putString("AM16_PACKAGE_NAME", "6d5416d9-2167-41df-ab10-c492e152b44f");
        bundle.putBoolean("EXT_IS_MUTE", true);
        bundle.putInt("AM16_MEASURE_TYPE", 2);
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi != null) {
            LogUtil.a("HonourDeviceConstants", "getAm16BundleMute healthDataMgrApi is not null");
            HeartZoneConf heartZoneConf = healthDataMgrApi.getHeartZoneConf();
            if (heartZoneConf != null) {
                LogUtil.a("HonourDeviceConstants", "getAm16BundleMute heartZoneConf is not null");
                i2 = heartZoneConf.getWarningLimitHR();
                LogUtil.a("HonourDeviceConstants", "getAm16BundleMute rate warning ", Integer.valueOf(i2));
                bundle.putInt("HEART_RATE_WARN_UPPER", i2);
                return bundle;
            }
        }
        i2 = 170;
        LogUtil.a("HonourDeviceConstants", "getAm16BundleMute rate warning ", Integer.valueOf(i2));
        bundle.putInt("HEART_RATE_WARN_UPPER", i2);
        return bundle;
    }

    public static boolean i() {
        return new DeviceCloudSharePreferencesManager(cpp.a()).g("is_down_am16_source");
    }

    public static void k() {
        new DeviceCloudSharePreferencesManager(cpp.a()).a("is_down_am16_source", true);
    }

    public static boolean p(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return z(new JSONObject(str).optString("productId"));
        } catch (JSONException e2) {
            LogUtil.b("HonourDeviceConstants", e2.getMessage());
            return false;
        }
    }

    public static void s() {
        String str = b;
        if (new File(str).exists()) {
            LogUtil.a("HonourDeviceConstants", "HonourDeviceConstants updateResouce ");
            ddh.c().a(str, (InputStream) null, cos.e, (ResourceFileListener) null);
        }
    }

    public static boolean b() {
        return new DeviceCloudSharePreferencesManager(cpp.a()).g("is_show_permission_tip");
    }

    public static void l() {
        new DeviceCloudSharePreferencesManager(cpp.a()).a("is_show_permission_tip", true);
    }

    public static boolean aj(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!"e4b0b1d5-2003-4d88-8b5f-c4f64542040b".equals(str) && !"a8ba095d-4123-43c4-a30a-0240011c58de".equals(str)) {
            return true;
        }
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
            return Utils.i();
        }
        return false;
    }

    public static String k(String str) {
        return TextUtils.isEmpty(str) ? "" : new DeviceCloudSharePreferencesManager(cpp.a()).b(str);
    }

    public static void j(String str, String str2) {
        LogUtil.a("HonourDeviceConstants", "save scale version success");
        new DeviceCloudSharePreferencesManager(cpp.a()).a(str, str2);
    }

    public static boolean au(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "a8ba095d-4123-43c4-a30a-0240011c58de".equals(str) || "e4b0b1d5-2003-4d88-8b5f-c4f64542040b".equals(str);
    }

    public static boolean av(String str) {
        LogUtil.a("HonourDeviceConstants", "isWiFiScale productId:", str);
        if (au(str)) {
            return true;
        }
        if (ae(str)) {
            return s(str);
        }
        LogUtil.h("HonourDeviceConstants", "isWiFiScale default ");
        return false;
    }

    public static boolean e(String str, String str2) {
        LogUtil.a("HonourDeviceConstants", "isWiFiScale productId:", str);
        if (au(str)) {
            return true;
        }
        if (ae(str) && b(str, str2)) {
            return true;
        }
        LogUtil.a("HonourDeviceConstants", "isWiFiScale is false");
        return false;
    }

    public static boolean b(String str, String str2) {
        if (!ae(str)) {
            LogUtil.h("HonourDeviceConstants", "isHaigerWiFiDevice not haiger Scale", str);
            return false;
        }
        MeasurableDevice d2 = ceo.d().d(str2, false);
        if (d2 != null && (d2 instanceof ctk)) {
            LogUtil.a("HonourDeviceConstants", "isHaigerWiFiDevice true");
            return true;
        }
        LogUtil.a("HonourDeviceConstants", "isHaigerWiFiDevice false");
        return false;
    }

    public static boolean s(String str) {
        if (!ae(str)) {
            LogUtil.h("HonourDeviceConstants", "isHaigerWiFiDevice not haiger Scale", str);
            return false;
        }
        if (cjx.e().a(str) instanceof ctk) {
            LogUtil.a("HonourDeviceConstants", "isHaigerWiFiDevice true");
            return true;
        }
        LogUtil.a("HonourDeviceConstants", "isHaigerWiFiDevice false");
        return false;
    }

    public static boolean u(String str) {
        return o.contains(str);
    }

    public static boolean h() {
        Iterator<String> it = e.iterator();
        while (it.hasNext()) {
            if (cjx.e().a(it.next()) != null) {
                return true;
            }
        }
        return false;
    }

    public static int j(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HonourDeviceConstants", "getDeviceIdByProductId productId = null return -1");
            return -1;
        }
        dcz d2 = ResourceManager.e().d(str);
        if (d2 == null) {
            LogUtil.h("HonourDeviceConstants", "getDeviceIdByProductId productInfo = null return -1");
            return -1;
        }
        String g2 = d2.g();
        LogUtil.a("HonourDeviceConstants", "getDeviceIdByProductId deviceId： ", cpw.a(g2));
        if (TextUtils.isEmpty(g2)) {
            return -1;
        }
        try {
            return Integer.parseInt(g2);
        } catch (NumberFormatException unused) {
            LogUtil.b("HonourDeviceConstants", "getDeviceIdByProductId exception");
            return -1;
        }
    }

    public static void m() {
        LogUtil.a("HonourDeviceConstants", "save scale version success");
        new DeviceCloudSharePreferencesManager(cpp.a()).a("clear_device_user_data", "1");
    }

    public static boolean c() {
        String b2 = new DeviceCloudSharePreferencesManager(cpp.a()).b("clear_device_user_data");
        LogUtil.a("HonourDeviceConstants", "getFirstCleanUserData success", b2);
        return !"1".equalsIgnoreCase(b2);
    }

    public static void o() {
        LogUtil.a("HonourDeviceConstants", "setFirstBind success");
        new DeviceCloudSharePreferencesManager(cpp.a()).a("first_bind_hour_device", "1");
    }

    public static boolean d() {
        String b2 = new DeviceCloudSharePreferencesManager(cpp.a()).b("first_bind_hour_device");
        LogUtil.a("HonourDeviceConstants", "getFirstBind success", b2);
        return !"1".equalsIgnoreCase(b2);
    }

    public static String t(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HonourDeviceConstants", "getProductId prodId is null");
            return "";
        }
        if ("007B".equals(str)) {
            return "8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4";
        }
        if ("M00F".equals(str)) {
            return "b29df4e3-b1f7-4e40-960d-4cfb63ccca05";
        }
        if ("M00D".equals(str)) {
            return "e835d102-af95-48a6-ae13-2983bc06f5c0";
        }
        if ("117K".equals(str) || "1808".equals(str)) {
            return "e4b0b1d5-2003-4d88-8b5f-c4f64542040b";
        }
        if ("117V".equals(str)) {
            return "a8ba095d-4123-43c4-a30a-0240011c58de";
        }
        LogUtil.h("HonourDeviceConstants", "getProductId prodId error:", str);
        return "";
    }

    public static String n(String str) {
        String b2 = new DeviceCloudSharePreferencesManager(cpp.a()).b(str + "sn");
        if (!"0".equalsIgnoreCase(b2)) {
            return b2;
        }
        HealthDevice a2 = cjx.e().a(str);
        if (!(a2 instanceof ctk)) {
            return "";
        }
        ctk ctkVar = (ctk) a2;
        return ctkVar.b() != null ? ctkVar.b().m() : "";
    }

    public static String l(String str) {
        String b2 = new DeviceCloudSharePreferencesManager(cpp.a()).b(str + "sn");
        if ("0".equalsIgnoreCase(b2)) {
            MeasurableDevice d2 = ceo.d().d(str, false);
            if (d2 == null) {
                return "";
            }
            b2 = d2.getSerialNumber();
            if (TextUtils.isEmpty(b2) && (d2 instanceof ctk)) {
                ctk ctkVar = (ctk) d2;
                if (ctkVar.b() != null) {
                    return ctkVar.b().m();
                }
            }
        }
        return b2;
    }

    public static void d(String str, String str2) {
        LogUtil.a("HonourDeviceConstants", "save device sn");
        new DeviceCloudSharePreferencesManager(cpp.a()).a(str2 + "sn", str);
    }

    public static void a(String str, String str2) {
        LogUtil.a("HonourDeviceConstants", "save device mac");
        new DeviceCloudSharePreferencesManager(cpp.a()).a(str2 + "mac", str);
    }

    public static String h(String str) {
        String b2 = new DeviceCloudSharePreferencesManager(cpp.a()).b(str + "mac");
        if (!"0".equalsIgnoreCase(b2)) {
            return b2;
        }
        MeasurableDevice c2 = ceo.d().c(str, false);
        return c2 == null ? "" : c2.getAddress();
    }

    public static void at(String str) {
        if (str != null) {
            long currentTimeMillis = System.currentTimeMillis();
            new cke("deviceUsedTime").a(cpp.a(), str, currentTimeMillis);
            LogUtil.a("HonourDeviceConstants", "setDevicesUseTime sharedPreferences uniqueId = ", cpw.a(str), "; bandTime = ", Long.valueOf(currentTimeMillis));
            return;
        }
        LogUtil.h("HonourDeviceConstants", "setDevicesUseTime wifi productId is null");
    }

    public static void c(String str) {
        if (str == null) {
            LogUtil.h("HonourDeviceConstants", "deleteWifiDeviceUserTime productId is null");
        } else {
            new cke("deviceUsedTime").a(cpp.a(), str);
        }
    }

    public static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HonourDeviceConstants", "encryptMac address is null");
            return "";
        }
        String replace = knl.d(str).replace(Marker.ANY_NON_NULL_MARKER, "A").replace("=", "A").replace("/", "A");
        return replace.length() >= 24 ? replace.substring(0, 24) : replace;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String e(String str) {
        char c2;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        boolean o2 = Utils.o();
        boolean u = CommonUtil.u(BaseApplication.getContext());
        String u2 = CommonUtil.u();
        String str2 = o2 ? "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%1$s&devicetype=%2$s" : "/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%1$s&devicetype=%2$s";
        str.hashCode();
        switch (str.hashCode()) {
            case -771439772:
                if (str.equals("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -494131014:
                if (str.equals("33123f39-7fc1-420b-9882-a4b0d6c61100")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -237937675:
                if (str.equals("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -225709539:
                if (str.equals("6d5416d9-2167-41df-ab10-c492e152b44f")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case -164782370:
                if (str.equals("ccd1f0f8-8c57-4bd7-a884-0ef38482f15f")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 260957372:
                if (str.equals("34fa0346-d46c-439d-9cb0-2f696618846b")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 299435131:
                if (str.equals("e835d102-af95-48a6-ae13-2983bc06f5c0")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 759413903:
                if (str.equals("c943c933-442e-4c34-bcd0-66597f24aaed")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 912505950:
                if (str.equals("b29df4e3-b1f7-4e40-960d-4cfb63ccca05")) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            case 2092990983:
                if (str.equals("7a1063dd-0e0f-4a72-9939-461476ff0259")) {
                    c2 = '\t';
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 3:
                if (o2) {
                }
                break;
        }
        return "";
    }

    private static String e(boolean z, String str, boolean z2) {
        if (z) {
            return String.format(Locale.ENGLISH, "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%1$s&devicetype=Herm", str);
        }
        return z2 ? "/SmartWear/Herm/EMUI10.0/C001B001/zh-CN/index.html?cid=11055" : "/SmartWear/Herm/EMUI10.0/C001B001/en-US/index.html?cid=11055";
    }

    private static String d(boolean z, String str, boolean z2) {
        if (z) {
            return String.format(Locale.ENGLISH, "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%s&devicetype=M0CJ", str);
        }
        return z2 ? "/hwtips/scene/device/index.html?lang=zh-CN&prodId=M0CJ&cid=11055" : "/hwtips/scene/device/index.html?lang=en-US&prodId=M0CJ";
    }

    private static String c(boolean z, String str, boolean z2) {
        if (z) {
            return String.format(Locale.ENGLISH, "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%1$s&devicetype=Lupin", str);
        }
        return z2 ? "/SmartWear/Lupin/EMUI8.0/C001B001/zh-CN/index.html" : "/SmartWear/Lupin/EMUI8.0/C001B001/en-US/index.html";
    }

    private static String b(boolean z, String str, boolean z2) {
        if (z) {
            return String.format(Locale.ENGLISH, "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%1$s&devicetype=HAG-B19", str);
        }
        return z2 ? "/SmartWear/HAG-B19/EMUI8.0/C001B001/zh-CN/index.html" : "/SmartWear/HAG-B19/EMUI8.0/C001B001/en-US/index.html";
    }

    private static String a(boolean z, String str, boolean z2) {
        if (z) {
            return String.format(Locale.ENGLISH, "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%1$s&devicetype=HAG-B29", str);
        }
        return z2 ? "/SmartWear/HAG-B29/EMUI8.0/C001B001/zh-CN/index.html?cid=11055" : "/SmartWear/HAG-B29/EMUI8.0/C001B001/en-US/index.html?cid=11055";
    }

    public static String o(String str) {
        boolean o2 = Utils.o();
        boolean u = CommonUtil.u(BaseApplication.getContext());
        if (!o2) {
            return u ? "/SmartWear/Lupin/MagicUI4.0/C001B001/zh-CN/index.html" : "/SmartWear/Lupin/MagicUI4.0/C001B001/en-US/index.html";
        }
        if (TextUtils.isEmpty(str)) {
            return String.format(Locale.ENGLISH, "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%1$s&devicetype=Lupin", CommonUtil.u());
        }
        return u ? "/honor_SmartWear/Jumppage/MagicUI4.0/C001B001/en-US/index.html?lang=zh-CN&devicetype=Lupin&MagicUI=MagicUI4.0" : String.format(Locale.ENGLISH, "/honor_SmartWear/Jumppage/MagicUI4.0/C001B001/en-US/index.html?lang=%s&devicetype=Lupin&MagicUI=MagicUI4.0", CommonUtil.u());
    }

    public static boolean f() {
        return Utils.i();
    }

    public static boolean q(String str) {
        boolean o2 = Utils.o();
        if (!bc(str) || o2) {
            return (ba(str) && o2) ? false : true;
        }
        return false;
    }

    private static boolean bc(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "33123f39-7fc1-420b-9882-a4b0d6c61100".equals(str) || "ccd1f0f8-8c57-4bd7-a884-0ef38482f15f".equals(str);
    }

    public static String m(String str) {
        dcz d2;
        LogUtil.a("HonourDeviceConstants", "getDeviceNameByProductId productId ", str);
        if (TextUtils.isEmpty(str) || (d2 = ResourceManager.e().d(str)) == null || d2.n() == null) {
            return "";
        }
        String d3 = dcx.d(str, d2.n().b());
        LogUtil.c("HonourDeviceConstants", "getDeviceNameByProductId deviceName = ", d3);
        return d3;
    }

    public static boolean e(int i2) {
        return l.contains(Integer.valueOf(i2));
    }

    public static boolean b(int i2) {
        return n.contains(Integer.valueOf(i2));
    }

    public static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("HonourDeviceConstants", "getDeviceIdentify uniqueId is null");
            return "";
        }
        MeasurableDevice d2 = ceo.d().d(str, false);
        if (d2 == null) {
            LogUtil.a("HonourDeviceConstants", "getDeviceIdentify device is null");
            return "";
        }
        String serialNumber = d2.getSerialNumber();
        if (TextUtils.isEmpty(serialNumber)) {
            serialNumber = l(d2.getUniqueId());
        }
        if (TextUtils.isEmpty(serialNumber)) {
            serialNumber = d2.getUniqueId();
        }
        if (TextUtils.isEmpty(serialNumber)) {
            return "";
        }
        String replaceAll = serialNumber.replaceAll(":", "");
        return replaceAll.length() < 3 ? replaceAll : replaceAll.substring(replaceAll.length() - 3);
    }

    private static boolean ba(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "34fa0346-d46c-439d-9cb0-2f696618846b".equals(str) || "7a1063dd-0e0f-4a72-9939-461476ff0259".equals(str);
    }

    public static boolean ar(String str) {
        dcz d2 = ResourceManager.e().d(str);
        if (d2 != null && !TextUtils.isEmpty(d2.c("is_support_uds"))) {
            return TextUtils.equals("true", d2.c("is_support_uds"));
        }
        if (!"b29df4e3-b1f7-4e40-960d-4cfb63ccca05".equals(str)) {
            return false;
        }
        LogUtil.a("HonourDeviceConstants", "is Hagrid2021 support uds.");
        return true;
    }

    public static boolean an(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HonourDeviceConstants", "isSupportNps productId = null");
            return false;
        }
        if (w(str)) {
            return true;
        }
        dcz d2 = ResourceManager.e().d(str);
        if (d2 == null) {
            LogUtil.h("HonourDeviceConstants", "isSupportNps productInfo = null");
            return false;
        }
        String c2 = d2.c("is_support_nps");
        LogUtil.a("HonourDeviceConstants", "isSupportNps product.xml is_support_Nps=", c2);
        return TextUtils.equals("true", c2);
    }

    public static boolean al(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HonourDeviceConstants", "isShowGuestMeasure productId = null");
            return false;
        }
        if (w(str) || r(str)) {
            return true;
        }
        dcz d2 = ResourceManager.e().d(str);
        if (d2 == null) {
            LogUtil.h("HonourDeviceConstants", "isShowGuestMeasure productInfo = null");
            return false;
        }
        String c2 = d2.c("manger_support_info_invisible");
        LogUtil.a("HonourDeviceConstants", "isShowGuestMeasure product.xml is ", c2);
        return TextUtils.equals("true", c2);
    }

    public static String i(String str) {
        char c2;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 299435131) {
            if (str.equals("e835d102-af95-48a6-ae13-2983bc06f5c0")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != 759413903) {
            if (hashCode == 912505950 && str.equals("b29df4e3-b1f7-4e40-960d-4cfb63ccca05")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (str.equals("c943c933-442e-4c34-bcd0-66597f24aaed")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            return "Herm-B19";
        }
        if (c2 == 1) {
            return "Dobby-B19";
        }
        if (c2 == 2) {
            return "Hagrid-B29";
        }
        LogUtil.h("HonourDeviceConstants", "productId is unknow, productId = ", str);
        return "UNKNOW_DEVICE";
    }

    public static boolean a(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return false;
        }
        return b(hiHealthData.getDouble("lfrfHfImpedance"));
    }

    public static boolean c(cfe cfeVar) {
        return cfeVar != null && cfeVar.q() != null && cfeVar.q().length > 0 && b(cfeVar.q()[0]);
    }

    public static boolean c(ckm ckmVar) {
        return ckmVar != null && ckmVar.i() != null && ckmVar.i().length > 0 && b(ckmVar.i()[0]);
    }

    public static boolean am(String str) {
        return h(str, "is_support_other_activity_connect");
    }

    public static boolean ak(String str) {
        return h(str, "is_support_keep_connected_when_back");
    }

    public static boolean ap(String str) {
        return h(str, "is_support_sync_while_connected");
    }

    public static boolean ax(String str) {
        return h(str, "is_support_weight_data_claiming");
    }

    public static boolean as(String str) {
        return h(str, "is_support_question_and_suggestion");
    }

    public static boolean ao(String str) {
        dcz d2 = ResourceManager.e().d(str);
        if (d2 == null) {
            LogUtil.h("HonourDeviceConstants", "ProductInfo is null");
            return false;
        }
        if (!koq.b(d2.ad())) {
            return true;
        }
        LogUtil.h("HonourDeviceConstants", "useGuides is null");
        return false;
    }

    private static boolean h(String str, String str2) {
        LogUtil.a("HonourDeviceConstants", str2, ", productId = ", str);
        dcz d2 = ResourceManager.e().d(str);
        if (d2 == null) {
            LogUtil.h("HonourDeviceConstants", "productInfo = null");
            return false;
        }
        String c2 = d2.c(str2);
        LogUtil.a("HonourDeviceConstants", "isSupport = ", c2);
        return TextUtils.equals("true", c2);
    }

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        dcz d2 = ResourceManager.e().d(str);
        if (d2 == null) {
            LogUtil.h("HonourDeviceConstants", "getCloudDeviceId productInfo is null");
            return -1;
        }
        String g2 = d2.g();
        if (g2 == null) {
            LogUtil.h("HonourDeviceConstants", "getCloudDeviceId deviceIdStr is null");
            return -1;
        }
        try {
            return Integer.parseInt(g2);
        } catch (NumberFormatException unused) {
            LogUtil.b("HonourDeviceConstants", "parseInt exception, input is ", g2);
            return -1;
        }
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return i(str, PluginPayAdapter.KEY_DEVICE_INFO_NAME);
    }

    private static String i(String str, String str2) {
        dcz d2 = ResourceManager.e().d(str);
        if (d2 == null) {
            LogUtil.h("HonourDeviceConstants", "getCloudValueByKey productInfo is null");
            return null;
        }
        String c2 = d2.c(str2);
        LogUtil.a("HonourDeviceConstants", "cloud key is ", str2, ", value is ", c2);
        return c2;
    }
}
