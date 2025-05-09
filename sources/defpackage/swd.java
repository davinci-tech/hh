package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.agconnect.apms.collect.model.HeaderType;
import com.huawei.hwidauth.datatype.DeviceInfo;
import com.huawei.wear.oversea.overseamanger.OverSeaMangerUtil;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public final class swd {

    /* renamed from: a, reason: collision with root package name */
    private static final Pattern f17259a = Pattern.compile("(\\\\+/)");
    private static final String d = "JSONHelperTwo";

    public static JSONObject b(String str) {
        String str2 = d;
        stq.c(str2, "JSONHelper createServiceTokenAuthStr  begin", false);
        LinkedHashMap<String, String> b = b(str, "AccessTokenAuth");
        std e = OverSeaMangerUtil.c(ssz.e()).e();
        if (e == null) {
            return new JSONObject();
        }
        String m = e.m();
        if (TextUtils.isEmpty(m)) {
            stq.c(str2, "createServiceTokenAuthStr, accountInfo is null.", false);
            b.put("error_desc", "createAccessTokenAuthStr, accountInfo is null.");
            sun.b(0, "Wallet_create_st", b);
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("userid", m);
            jSONObject.put("appID", e.c());
            jSONObject.put(DeviceInfo.TAG_DEVICE_ID, e.e());
            jSONObject.put("deviceType", e.j());
            jSONObject.put("serviceToken", e.o());
            jSONObject.put("terminalType", e.a());
            if (!e.d().equals("CN")) {
                stq.c(str2, "createServiceTokenAuthStr, stSite = " + e.l(), false);
                jSONObject.put("stSite", e.l() + "");
                jSONObject.put("countryCode", e.d());
            }
            return jSONObject;
        } catch (JSONException unused) {
            b.put("error_desc", "createAccessTokenAuthStr, accountInfo invalid.");
            sun.b(0, "Wallet_create_st", b);
            stq.c(d, "createServiceTokenAuthStr, accountInfo invalid.", false);
            return null;
        }
    }

    public static JSONObject a(String str) {
        String str2 = d;
        stq.c(str2, "JSONHelper createAccessTokenAuthStr  begin", false);
        LinkedHashMap<String, String> b = b(str, "AccessTokenAuth");
        std e = OverSeaMangerUtil.c(ssz.e()).e();
        if (e == null) {
            return new JSONObject();
        }
        if (TextUtils.isEmpty(e.m())) {
            stq.c(str2, "createAccessTokenAuthStr, accountInfo is null.", false);
            b.put("error_desc", "createAccessTokenAuthStr, accountInfo is null.");
            sun.b(0, "Wallet_create_st", b);
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("userid", e.m());
            jSONObject.put("appID", e.c());
            jSONObject.put(DeviceInfo.TAG_DEVICE_ID, e.e());
            jSONObject.put("deviceType", e.j());
            jSONObject.put("accessToken", e.o());
            jSONObject.put("terminalType", e.a());
            if (!e.d().equals("CN")) {
                stq.c(str2, "createAccessTokenAuthStr, stSite = " + e.l(), false);
                jSONObject.put("stSite", e.l() + "");
                jSONObject.put("countryCode", e.d());
            }
            return jSONObject;
        } catch (JSONException unused) {
            stq.c(d, "createAccessTokenAuthStr, accountInfo invalid.", false);
            b.put("error_desc", "createAccessTokenAuthStr, accountInfo invalid.");
            sun.b(0, "Wallet_create_st", b);
            return null;
        }
    }

    private static LinkedHashMap<String, String> b(String str, String str2) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(HeaderType.EVENT_ID, "Wallet_create_st");
        linkedHashMap.put("log_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Long.valueOf(System.currentTimeMillis())));
        linkedHashMap.put("command_str", str);
        if (TextUtils.isEmpty(OverSeaMangerUtil.c(ssz.e()).e().m())) {
            String str3 = d;
            stq.c(str3, "JSONHelper " + str2 + ", getAccountInfo begin", false);
            linkedHashMap.put("error_desc", "JSONHelper " + str2 + ", uesrid isEmpty .");
            sun.b(0, "Wallet_create_st", linkedHashMap);
            stq.c(str3, "JSONHelper " + str2 + ", accountLock lock wait interrupted.", false);
        }
        return linkedHashMap;
    }

    public static String c(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null || !jSONObject.has(str)) {
            return null;
        }
        return jSONObject.getString(str);
    }

    public static String e(String str, int i, JSONObject jSONObject, Context context) {
        JSONObject jSONObject2;
        if (jSONObject == null) {
            return null;
        }
        stq.d(d, "prepareRequestStr dataStr : " + jSONObject.toString(), true);
        try {
            jSONObject2 = new JSONObject();
            jSONObject2.put("merchantID", str);
            jSONObject2.put("keyIndex", i);
            jSONObject2.put("data", jSONObject.toString());
        } catch (JSONException unused) {
            Log.e("", "createRequestStr, params invalid.");
            jSONObject2 = null;
        }
        if (jSONObject2 == null) {
            return null;
        }
        return d(jSONObject2.toString());
    }

    private static String d(String str) {
        return f17259a.matcher(str).replaceAll("/");
    }

    public static int d(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null || !jSONObject.has(str)) {
            return -1;
        }
        return jSONObject.getInt(str);
    }
}
