package defpackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.hmsscankit.DetailRect;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.watchface.videoedit.param.CanvasConfig;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kyr {
    private static final Map<String, String> c = new HashMap<String, String>() { // from class: kyr.3
        {
            put("34fa0346-d46c-439d-9cb0-2f696618846b", "HuaweiCH18");
            put("33123f39-7fc1-420b-9882-a4b0d6c61100", "HuaweiCH100");
            put("ccd1f0f8-8c57-4bd7-a884-0ef38482f15f", "HuaweiAH100");
            put("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4", "LUP-B19");
            put("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4", "HAG-B19");
            put("b29df4e3-b1f7-4e40-960d-4cfb63ccca05", "HAG-B19");
            put("e835d102-af95-48a6-ae13-2983bc06f5c0", "HEM-B19");
        }
    };

    public static OutputStream b(Context context, String str, boolean z, kxn kxnVar) {
        LogUtil.a("convertJsonToStreamUtil", "convertVersionFilterJsonToStream JSON begin");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        kxg d = kyp.d(context, str);
        JSONObject jSONObject = new JSONObject();
        try {
            c(jSONObject, d, kxnVar);
            if (z) {
                e(jSONObject, d.g());
            } else {
                c(jSONObject, d.g());
            }
            jSONObject.put("IMSI", d.j());
            d(jSONObject, context, d);
            JSONObject jSONObject2 = new JSONObject();
            if (kxnVar != null && kxnVar.j()) {
                jSONObject2.put("commonRules", a(d, kxnVar));
                jSONObject2.putOpt("versionPackageRules", c(jSONObject));
            } else {
                jSONObject2.put("components", a(d));
                jSONObject2.putOpt("rules", jSONObject);
            }
            try {
                byteArrayOutputStream.write(jSONObject2.toString().getBytes(StandardCharsets.UTF_8));
                byteArrayOutputStream.flush();
            } catch (IOException e) {
                LogUtil.b("convertJsonToStreamUtil", "convertVersionFilterJsonToStream, IOException", e.getMessage());
            }
        } catch (JSONException unused) {
            LogUtil.b("convertJsonToStreamUtil", "convertVersionFilterJsonToStream, JSONException");
        }
        return byteArrayOutputStream;
    }

    public static OutputStream bSQ_(Context context, PackageInfo packageInfo, String str, boolean z, kxn kxnVar) {
        LogUtil.a("convertJsonToStreamUtil", "convertVersionFilterJsonToStream(bone) begin");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        kxg d = kyp.d(context, packageInfo.packageName);
        JSONObject jSONObject = new JSONObject();
        try {
            c(jSONObject, d, kxnVar);
            if (z) {
                e(jSONObject, CommonUtil.a(context, false));
            } else {
                c(jSONObject, str);
                a(jSONObject, str, kxnVar);
                String r = kxz.r(context, str);
                if (!TextUtils.isEmpty(r)) {
                    jSONObject.put("extra_info", r);
                }
                if (!Utils.o() && kxz.d(kxnVar.c())) {
                    String l = kxz.l(context, str);
                    if (!TextUtils.isEmpty(l)) {
                        jSONObject.put("extra_info", l);
                    }
                }
            }
            d(jSONObject, context, d);
            e(context, str, jSONObject, kxnVar.c());
            JSONObject jSONObject2 = new JSONObject();
            if (kxnVar != null && kxnVar.j()) {
                jSONObject2.put("commonRules", bSS_(packageInfo, kxnVar));
                jSONObject2.putOpt("versionPackageRules", c(jSONObject));
            } else {
                jSONObject2.put("components", bSR_(packageInfo));
                jSONObject2.putOpt("rules", jSONObject);
            }
            try {
                byteArrayOutputStream.write(jSONObject2.toString().getBytes(StandardCharsets.UTF_8));
                byteArrayOutputStream.flush();
            } catch (IOException e) {
                LogUtil.b("convertJsonToStreamUtil", "convertVersionFilterJsonToStream(bone) IOException ", e.getMessage());
            }
        } catch (JSONException unused) {
            LogUtil.b("convertJsonToStreamUtil", "convertVersionFilterJsonToStream(bone), JSONException");
        }
        LogUtil.a("convertJsonToStreamUtil", "convertVersionFilterJsonToStream(bone) leave");
        return byteArrayOutputStream;
    }

    private static void c(JSONObject jSONObject, String str) throws JSONException {
        if (CommonUtil.r(str)) {
            jSONObject.put("deviceId", str);
        } else {
            jSONObject.put("IMEI", str);
        }
    }

    private static void a(JSONObject jSONObject, String str, kxn kxnVar) throws JSONException {
        if (!Utils.o()) {
            LogUtil.a("convertJsonToStreamUtil", "Start addAirActiveRules");
            String a2 = CommonUtil.a(BaseApplication.getContext(), false);
            if (!TextUtils.isEmpty(a2)) {
                jSONObject.put("udid", a2);
            }
        }
        if (HwVersionManager.c(BaseApplication.getContext()).i()) {
            return;
        }
        String m = kxz.m(BaseApplication.getContext(), str);
        if (kxnVar != null && !TextUtils.isEmpty(kxnVar.c())) {
            m = kxnVar.c();
        }
        String d = kyg.d(str, m);
        if (TextUtils.isEmpty(d)) {
            return;
        }
        jSONObject.put("extra_info_new", d);
    }

    private static void e(JSONObject jSONObject, String str) throws JSONException {
        if (CommonUtil.br()) {
            jSONObject.put("udid", CommonUtil.h());
            jSONObject.put("deviceId", CommonUtil.g());
        }
        if (CommonUtil.bh()) {
            jSONObject.put("IMEI", str);
        }
        jSONObject.put("udid", CommonUtil.an());
        jSONObject.put("deviceId", CommonUtil.g());
    }

    private static void c(JSONObject jSONObject, kxg kxgVar, kxn kxnVar) throws JSONException {
        jSONObject.put("FingerPrint", kxgVar.c());
        if (kxnVar != null && !TextUtils.isEmpty(kxnVar.d())) {
            jSONObject.put("DeviceName", kxnVar.d());
        } else {
            jSONObject.put("DeviceName", kxgVar.b());
        }
        jSONObject.put("FirmWare", kxgVar.a());
        if (kxnVar != null) {
            int e = kxnVar.e();
            if (e == 0) {
                jSONObject.put("PackageType", "increment");
            } else if (e == 1) {
                jSONObject.put("PackageType", CanvasConfig.FULL_CONFIG);
            } else {
                LogUtil.a("convertJsonToStreamUtil", "packageType unknown");
            }
        }
    }

    private static void d(JSONObject jSONObject, Context context, kxg kxgVar) throws JSONException {
        jSONObject.put("Language", kxgVar.i());
        jSONObject.put("OS", kxgVar.h());
        jSONObject.put("C_version", kxgVar.e());
        jSONObject.put("D_version", kxgVar.d());
        if (kxu.d(context)) {
            jSONObject.put("HotaVersion", kxgVar.f());
        }
    }

    private static void e(Context context, String str, JSONObject jSONObject, String str2) throws JSONException {
        String e = HwVersionManager.c(BaseApplication.getContext()).e();
        if (!TextUtils.isEmpty(e)) {
            jSONObject.put("vendorcountry", e);
        }
        if (!HwVersionManager.c(BaseApplication.getContext()).i() && HwVersionManager.c(BaseApplication.getContext()).g()) {
            LogUtil.a("convertJsonToStreamUtil", "addGeneralRulesPart3(bone) is electronic card");
            JSONObject jSONObject2 = new JSONObject();
            String l = kxz.l(context, str);
            if (!TextUtils.isEmpty(l)) {
                jSONObject2 = new JSONObject(l);
            }
            jSONObject2.put("electronicCard", "Y");
            jSONObject.put("extra_info", jSONObject2.toString());
            kxz.d(str2, "hotaElectronic", "true");
            return;
        }
        LogUtil.h("convertJsonToStreamUtil", "addGeneralRulesPart3(bone) is not electronic card");
    }

    public static OutputStream a(Context context, String str, String str2, String str3, String str4) {
        LogUtil.a("convertJsonToStreamUtil", "convertVersionFilterJsonToStream JSON begin");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("DeviceName", c.get(str2));
            if (CommonUtil.r(str4)) {
                jSONObject.put("deviceId", str4);
            } else {
                jSONObject.put("IMEI", str4);
            }
            jSONObject.put("Language", kyp.b(context));
            jSONObject.put("OS", kyp.b());
            jSONObject.put("PackageType", CanvasConfig.FULL_CONFIG);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("componentID", "1");
            jSONObject2.put(DetailRect.CP_PACKAGE, str);
            jSONObject2.put("PackageVersionCode", str3);
            jSONObject2.put("PackageVersionName", str3);
            jSONObject2.put("FirmWare", d(str2));
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("components", jSONArray);
            jSONObject3.putOpt("rules", jSONObject);
            try {
                byteArrayOutputStream.write(jSONObject3.toString().getBytes(StandardCharsets.UTF_8));
                byteArrayOutputStream.flush();
            } catch (IOException e) {
                LogUtil.b("convertJsonToStreamUtil", "convertVersionFilterJsonToStream, IOException", e.getMessage());
            }
        } catch (JSONException e2) {
            LogUtil.b("convertJsonToStreamUtil", "convertVersionFilterJsonToStream, JSONException", e2.getMessage());
        } catch (Exception unused) {
            LogUtil.b("convertJsonToStreamUtil", "convertVersionFilterJsonToStream, Exception");
        }
        return byteArrayOutputStream;
    }

    private static String d(String str) {
        return "ccd1f0f8-8c57-4bd7-a884-0ef38482f15f".equals(str) ? "FW_OS" : "FW_SCALE";
    }

    private static JSONArray bSR_(PackageInfo packageInfo) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(DetailRect.CP_PACKAGE, packageInfo.packageName);
        jSONObject.put("PackageVersionCode", String.valueOf(packageInfo.versionCode));
        jSONObject.put("PackageVersionName", packageInfo.versionName);
        if (packageInfo.signatures != null && packageInfo.signatures.length > 0 && packageInfo.signatures[0] != null) {
            jSONObject.put(HwPayConstant.KEY_SIGN, kxv.a(packageInfo.signatures[0].toCharsString()));
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject);
        return jSONArray;
    }

    private static JSONArray a(kxg kxgVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(DetailRect.CP_PACKAGE, kxgVar.l());
        jSONObject.put("PackageVersionCode", kxgVar.k());
        jSONObject.put("PackageVersionName", kxgVar.o());
        jSONObject.put(HwPayConstant.KEY_SIGN, kxgVar.n());
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject);
        return jSONArray;
    }

    private static JSONArray c(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("versionPackageType", 6);
        jSONObject2.put("rules", jSONObject);
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject2);
        return jSONArray;
    }

    private static JSONObject bSS_(PackageInfo packageInfo, kxn kxnVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(DetailRect.CP_PACKAGE, packageInfo.packageName);
        jSONObject.put("PackageVersionCode", String.valueOf(packageInfo.versionCode));
        jSONObject.put("PackageVersionName", packageInfo.versionName);
        if (packageInfo.signatures != null && packageInfo.signatures.length > 0 && packageInfo.signatures[0] != null) {
            jSONObject.put(HwPayConstant.KEY_SIGN, kxv.a(packageInfo.signatures[0].toCharsString()));
        }
        jSONObject.put("Devicename", kxnVar.b());
        jSONObject.put("enterprise", kxnVar.a());
        return jSONObject;
    }

    private static JSONObject a(kxg kxgVar, kxn kxnVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(DetailRect.CP_PACKAGE, kxgVar.l());
        jSONObject.put("PackageVersionCode", kxgVar.k());
        jSONObject.put("PackageVersionName", kxgVar.o());
        jSONObject.put(HwPayConstant.KEY_SIGN, kxgVar.n());
        jSONObject.put("Devicename", kxnVar.b());
        jSONObject.put("enterprise", kxnVar.a());
        return jSONObject;
    }

    public static String c(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (a(jSONObject)) {
                return jSONObject.toString();
            }
            if (jSONObject.has("rules")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("rules");
                if (jSONObject2.has("IMEI")) {
                    jSONObject2.put("IMEI", CommonUtil.l(jSONObject2.getString("IMEI")));
                }
                if (jSONObject2.has("udid")) {
                    jSONObject2.put("udid", CommonUtil.l(jSONObject2.getString("udid")));
                }
                if (jSONObject2.has("deviceId")) {
                    jSONObject2.put("deviceId", CommonUtil.l(jSONObject2.getString("deviceId")));
                }
                if (jSONObject2.has("extra_info")) {
                    jSONObject2.put("extra_info", CommonUtil.l(jSONObject2.getString("extra_info")));
                }
                if (jSONObject2.has("extra_info_new")) {
                    jSONObject2.put("extra_info_new", CommonUtil.l(jSONObject2.getString("extra_info_new")));
                }
                jSONObject.put("rules", jSONObject2);
                return jSONObject.toString();
            }
            LogUtil.a("convertJsonToStreamUtil", "getVersionFilterLogContent not have rules");
            return null;
        } catch (JSONException e) {
            LogUtil.b("convertJsonToStreamUtil", "getVersionFilterLogContent JSONException :", ExceptionUtils.d(e));
            return null;
        }
    }

    private static boolean a(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("versionPackageRules")) {
            JSONArray jSONArray = jSONObject.getJSONArray("versionPackageRules");
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                d(jSONObject2);
                jSONArray2.put(jSONObject2);
            }
            jSONObject.put("versionPackageRules", jSONArray2);
            return true;
        }
        LogUtil.a("convertJsonToStreamUtil", "getVersionFilterLogContent not have versionPackageRules");
        return false;
    }

    private static void d(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("rules")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("rules");
            if (jSONObject2.has("IMEI")) {
                jSONObject2.put("IMEI", CommonUtil.l(jSONObject2.getString("IMEI")));
            }
            if (jSONObject2.has("udid")) {
                jSONObject2.put("udid", CommonUtil.l(jSONObject2.getString("udid")));
            }
            if (jSONObject2.has("deviceId")) {
                jSONObject2.put("deviceId", CommonUtil.l(jSONObject2.getString("deviceId")));
            }
            if (jSONObject2.has("extra_info")) {
                jSONObject2.put("extra_info", CommonUtil.l(jSONObject2.getString("extra_info")));
            }
            if (jSONObject2.has("extra_info_new")) {
                jSONObject2.put("extra_info_new", CommonUtil.l(jSONObject2.getString("extra_info_new")));
            }
            jSONObject.put("rules", jSONObject2);
        }
    }
}
