package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes7.dex */
public class rbq {
    private static rbq c;

    private rbq() {
    }

    public static rbq d() {
        if (c == null) {
            c = new rbq();
        }
        return c;
    }

    private String b() {
        String str;
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainSnsHicloud");
        String str2 = "version=0111&clientversion=" + rbo.d();
        if (TextUtils.isEmpty(url)) {
            str = "https://SNS/client/ISNS/findUserV2?" + str2;
        } else {
            str = url + "/SNS/client/ISNS/findUserV2?" + str2;
        }
        return a(str);
    }

    private String a() {
        String str;
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainSnsHicloud");
        String str2 = "version=0111&clientversion=" + rbo.d();
        if (TextUtils.isEmpty(url)) {
            str = "https://SNS/client/ISNS/getOtherUserInfo?" + str2;
        } else {
            str = url + "/SNS/client/ISNS/getOtherUserInfo?" + str2;
        }
        return a(str);
    }

    private String c() {
        String str;
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainSnsHicloud");
        String str2 = "version=0111&clientversion=" + rbo.d();
        if (TextUtils.isEmpty(url)) {
            str = "https://SNS/client/ISNS/loginSNS?" + str2;
        } else {
            str = url + "/SNS/client/ISNS/loginSNS?" + str2;
        }
        return a(str);
    }

    private String a(String str) {
        int i;
        try {
            i = Integer.parseInt(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        } catch (NumberFormatException unused) {
            LogUtil.b("HealthZoneRequestUtil", "siteId exception");
            i = 1;
        }
        return String.format(Locale.ENGLISH, str, Integer.valueOf(i));
    }

    public exh a(rba rbaVar) {
        String b = b();
        String json = new Gson().toJson(rbaVar);
        LogUtil.c("getFindUserSync params: ", json);
        exh exhVar = new exh();
        if (TextUtils.isEmpty(b) || TextUtils.isEmpty(json)) {
            LogUtil.h("HealthZoneRequestUtil", "getFindUserSync url or params invalidate");
            return exhVar;
        }
        HashMap hashMap = new HashMap(16);
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), "cookie");
        if (!TextUtils.isEmpty(b2)) {
            LogUtil.c("cookie: ", b2);
            hashMap.put("Cookie", b2);
        }
        String a2 = a(b, json, hashMap);
        LogUtil.c("getFindUserSync json: ", a2);
        try {
            exhVar = (exh) new Gson().fromJson(CommonUtil.z(a2), exh.class);
        } catch (JsonSyntaxException e) {
            LogUtil.b("HealthZoneRequestUtil", "FindUserV2Response fromJson exception :", e.getMessage());
            exhVar.setResultCode(-1);
            exhVar.setResultDesc(e.getMessage());
        }
        if (exhVar == null) {
            exhVar = new exh();
            exhVar.setResultCode(-1);
        }
        LogUtil.c("HealthZoneRequestUtil", "Response String: ", exhVar.toString());
        return exhVar;
    }

    public exg e(exi exiVar) {
        String a2 = a();
        String json = new Gson().toJson(exiVar);
        LogUtil.c("getUserAllInfoSync params: ", json);
        exg exgVar = new exg();
        if (TextUtils.isEmpty(a2) || TextUtils.isEmpty(json)) {
            LogUtil.h("HealthZoneRequestUtil", "getUserAllInfoSync url or params invalidate");
            return exgVar;
        }
        HashMap hashMap = new HashMap(16);
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI), "cookie");
        if (!TextUtils.isEmpty(b)) {
            LogUtil.c("cookie: ", b);
            hashMap.put("Cookie", b);
        }
        String a3 = a(a2, json, hashMap);
        LogUtil.a("getUserAllInfoSync json: ", a3);
        try {
            exgVar = (exg) new Gson().fromJson(CommonUtil.z(a3), exg.class);
        } catch (JsonSyntaxException e) {
            LogUtil.b("HealthZoneRequestUtil", "getUserAllInfoSync fromJson exception :", e.getMessage());
            exgVar.a(-1);
        }
        if (exgVar == null) {
            exgVar = new exg();
            exgVar.a(-1);
        }
        LogUtil.c("HealthZoneRequestUtil", "getUserAllInfoSync response String: ", exgVar.toString());
        return exgVar;
    }

    public rbf a(rbc rbcVar) {
        String c2 = c();
        String json = new Gson().toJson(rbcVar);
        LogUtil.a("HealthZoneRequestUtil", "getUserLoginStatus paramString: ", json);
        rbf rbfVar = new rbf();
        if (TextUtils.isEmpty(c2) || TextUtils.isEmpty(json)) {
            LogUtil.h("HealthZoneRequestUtil", "getUserLoginStatus url or params invalidate");
            return rbfVar;
        }
        try {
            rbfVar = (rbf) new Gson().fromJson(CommonUtil.z(a(c2, json, new HashMap())), rbf.class);
        } catch (JsonSyntaxException e) {
            LogUtil.b("HealthZoneRequestUtil", "getUserLoginStatus fromJson exception: JsonSyntaxException");
            rbfVar.setResultCode(-1);
            rbfVar.setResultDesc(e.getMessage());
        }
        if (rbfVar == null) {
            rbfVar = new rbf();
            rbfVar.setResultCode(-1);
        }
        LogUtil.c("HealthZoneRequestUtil", "getUserLoginStatus response is :", rbfVar.toString());
        return rbfVar;
    }

    public static String a(String str, String str2, HashMap<String, String> hashMap) {
        return !TextUtils.isEmpty(str) ? rbv.e(BaseApplication.getContext(), str, str2, hashMap) : "";
    }
}
