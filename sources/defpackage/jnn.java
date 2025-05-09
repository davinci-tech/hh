package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.hwidauth.datatype.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jnn {
    public static JSONObject a(String str, String str2, boolean z) {
        if (str2 == null) {
            LogUtil.h("JsonHelper", "createHeaderString commandString invalid param");
            return null;
        }
        LogUtil.a("JsonHelper", "createHeaderString commandString invalid param: ", str2);
        String g = jnu.g();
        String f = jnu.f();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("srcTranID", str);
            if (z) {
                jSONObject.put("version", "1.1");
                jSONObject.put("accessTokenAuth", e(g, f));
            } else {
                jSONObject.put("version", "1.0");
            }
            jSONObject.put("ts", System.currentTimeMillis() / 1000);
            jSONObject.put("commander", str2);
            return jSONObject;
        } catch (JSONException unused) {
            LogUtil.b("JsonHelper", "createHeaderObject, params invalid: ", str2);
            return null;
        }
    }

    public static String a(String str, int i, JSONObject jSONObject) {
        JSONObject jSONObject2;
        if (jSONObject == null) {
            LogUtil.h("JsonHelper", "createRequestString dataObject invalid");
            return null;
        }
        try {
            jSONObject2 = new JSONObject();
            jSONObject2.put("merchantID", str);
            jSONObject2.put("keyIndex", i);
            jSONObject2.put("data", jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.b("JsonHelper", "createRequestString dataObject params invalid");
            jSONObject2 = null;
        }
        if (jSONObject2 == null) {
            return null;
        }
        return jSONObject2.toString();
    }

    public static String e(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null || !jSONObject.has(str)) {
            return null;
        }
        return jSONObject.getString(str);
    }

    public static int d(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null) {
            LogUtil.h("JsonHelper", "getIntValue dataObject invalid");
            return -1;
        }
        if (jSONObject.has(str)) {
            return jSONObject.getInt(str);
        }
        return -1;
    }

    public static JSONObject e(String str, String str2) {
        LoginInit loginInit = LoginInit.getInstance(null);
        if (TextUtils.isEmpty(loginInit.getAccountInfo(1011))) {
            LogUtil.h("JsonHelper", "createServiceTokenAuthString, accountInfo invalid.");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("userid", loginInit.getAccountInfo(1011));
            jSONObject.put("accessToken", loginInit.getAccountInfo(1008));
            jSONObject.put("appID", "com.huawei.health");
            jSONObject.put(DeviceInfo.TAG_DEVICE_ID, str);
            jSONObject.put("deviceType", 0);
            jSONObject.put("terminalType", str2);
            jSONObject.put("stSite", loginInit.getAccountInfo(1009));
            return jSONObject;
        } catch (JSONException unused) {
            LogUtil.b("JsonHelper", "createServiceTokenAuthString, accountInfo invalid.");
            return null;
        }
    }

    public static String e(jmp jmpVar, int i, jmg jmgVar, int i2, int i3) {
        if (jmpVar == null || jmgVar == null) {
            LogUtil.h("JsonHelper", "getReqNextJsonResult, params or bean is null.");
            return "";
        }
        jmj jmjVar = new jmj();
        e(jmjVar, jmpVar);
        jmf jmfVar = new jmf();
        jmfVar.a(i);
        jmfVar.c(jmgVar);
        jmfVar.c(i2);
        jmfVar.e(i3);
        jmjVar.a(jmfVar);
        try {
            return new Gson().toJson(jmjVar);
        } catch (JsonSyntaxException unused) {
            return null;
        }
    }

    public static String a(jmp jmpVar, int i, int i2) {
        if (jmpVar == null) {
            LogUtil.h("JsonHelper", "getReqNextJsonResult, params is null");
            return "";
        }
        jmj jmjVar = new jmj();
        e(jmjVar, jmpVar);
        jmd jmdVar = new jmd();
        jmdVar.a(i);
        jmdVar.e(i2);
        jmjVar.a(jmdVar);
        try {
            return new Gson().toJson(jmjVar);
        } catch (JsonSyntaxException | IllegalArgumentException unused) {
            return null;
        }
    }

    private static void e(jmj jmjVar, jmp jmpVar) {
        jmjVar.b("2.0.6");
        jmjVar.a(jnu.g());
        jmjVar.e(jnu.f());
        jmjVar.i("1.0");
        jmjVar.h(jmpVar.c());
        jmjVar.d(jmpVar.a());
        jmjVar.c(jmpVar.e());
    }
}
