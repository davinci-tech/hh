package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.huawei.hms.utils.Util;
import com.huawei.hwidauth.api.ResultCallBack;
import com.huawei.hwidauth.c.j;
import com.huawei.hwidauth.datatype.DeviceInfo;
import com.huawei.hwidauth.ui.WebViewActivity;
import defpackage.kql;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class kqi {
    private static String d = "LOGIN_MODE_DEFAULT";

    public static void b(Context context, ResultCallBack<kqg> resultCallBack) throws kqk {
        ksy.b("HuaweiIdOAuthService", "signOut start.", true);
        if (context == null) {
            ksy.c("HuaweiIdOAuthService", "context is null.", true);
            return;
        }
        String a2 = ksg.a();
        ksg.c(context, 907115002, 0, "signOut start.", a2, "accountPickerH5.signOut_v2", "api_entry");
        if (resultCallBack == null) {
            ksy.c("HuaweiIdOAuthService", "resultResultCallBack is null.", true);
            ksg.c(context, 907115002, 404, "resultResultCallBack is null.", a2, "accountPickerH5.signOut_v2", "api_ret");
            throw new kqk("resultCallback is empty");
        }
        b(context, resultCallBack, a2);
    }

    private static void b(Context context, ResultCallBack<kqg> resultCallBack, String str) {
        try {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeSessionCookie();
            cookieManager.removeAllCookie();
            krn.c(context).e("siteID");
            ksy.b("HuaweiIdOAuthService", "signOut success.", true);
            d(context, resultCallBack, "");
        } catch (RuntimeException e) {
            ksy.c("HuaweiIdOAuthService", "RuntimeException", true);
            c(resultCallBack);
            ksg.c(context, 907115002, 404, "RuntimeException:" + e.getMessage(), str, "accountPickerH5.signOut_v2", "api_ret");
        } catch (Exception e2) {
            ksy.c("HuaweiIdOAuthService", "Exception", true);
            c(resultCallBack);
            ksg.c(context, 907115002, 404, "Exception:" + e2.getMessage(), str, "accountPickerH5.signOut_v2", "api_ret");
        }
    }

    private static void c(ResultCallBack<kqg> resultCallBack) {
        ksy.b("HuaweiIdOAuthService", "setErrorResultCallBack start.", true);
        kqm kqmVar = new kqm(404, "Sign Out Fail");
        kqmVar.b(false);
        resultCallBack.onResult(new kqg(kqmVar));
    }

    private static void d(Context context, ResultCallBack<kqg> resultCallBack, String str) {
        ksy.b("HuaweiIdOAuthService", "setSuccessResultCallBack start.", true);
        kqm kqmVar = new kqm(200, "Sign Out Success");
        kqmVar.b(true);
        resultCallBack.onResult(new kqg(kqmVar));
        ksg.c(context, 907115002, 200, "signOut success.", str, "accountPickerH5.signOut_v2", "api_ret");
    }

    public static void bQm_(Activity activity, String str, String str2, String[] strArr, String str3, String str4, String str5, boolean z, ResultCallBack<kqo> resultCallBack) throws kqk {
        if (activity == null) {
            ksy.c("HuaweiIdOAuthService", "activity is null.", true);
            return;
        }
        kql c = new kql.c(activity).a(str2).a(strArr).c(str3).d(str4).a(resultCallBack).c();
        if (!TextUtils.isEmpty(str5)) {
            c.e(str, str5, z);
        } else {
            c.e(str);
        }
    }

    public static void bQk_(Activity activity, String str, String str2, kqr kqrVar, String str3, ResultCallBack<kqn> resultCallBack) {
        ksy.b("HuaweiIdOAuthService", "qrCodeAuthLogin start.", true);
        if (activity == null) {
            ksy.c("HuaweiIdOAuthService", "activity is null.", true);
            return;
        }
        String a2 = ksg.a();
        ksg.c(activity, 907115008, 0, "enter QRCodeAuth", a2, "accountPickerH5.qrCodeAuthLogin", "api_entry");
        if (TextUtils.isEmpty(str2) || kqrVar == null || resultCallBack == null || TextUtils.isEmpty(str)) {
            ksy.c("HuaweiIdOAuthService", "QRCodeAuth param error", true);
            ksg.c(activity, 907115008, 404, "QRCodeAuth param error", a2, "accountPickerH5.qrCodeAuthLogin", "api_ret");
            if (resultCallBack != null) {
                resultCallBack.onResult(new kqn(new kqm(404, "QRCodeAuth param error")));
                return;
            }
            return;
        }
        if (!ksi.g(activity)) {
            ksy.c("HuaweiIdOAuthService", "network is unavailable.", true);
            ksg.c(activity, 907115008, 2005, "QRCodeAuth network is unavailable.", a2, "accountPickerH5.qrCodeAuthLogin", "api_ret");
            kqm kqmVar = new kqm(2005, "Network is Unavailable");
            kqmVar.b(false);
            resultCallBack.onResult(new kqn(kqmVar));
            return;
        }
        bQd_(activity, str, str2, str3, a2, kqrVar, resultCallBack);
    }

    private static void bQd_(Activity activity, String str, String str2, String str3, String str4, kqr kqrVar, ResultCallBack<kqn> resultCallBack) {
        try {
            ksi.b(resultCallBack);
            Intent intent = new Intent(activity, (Class<?>) WebViewActivity.class);
            intent.putExtra("key_access_token", str2);
            intent.putExtra("key_app_id", Util.getAppId(activity));
            intent.putExtra("key_device_info", (Parcelable) DeviceInfo.a(activity, str3));
            intent.putExtra("key_oper", "scan_code_login");
            intent.putExtra("key_transId", str4);
            intent.putExtra("key_qr_code", kqrVar.e());
            intent.putExtra("key_qr_siteid", kqrVar.b());
            intent.putExtra("key_qr_code_source", kqrVar.d());
            intent.putExtra("grs_app_name", str);
            intent.setPackage(activity.getPackageName());
            activity.startActivity(intent);
        } catch (IllegalArgumentException e) {
            ksy.c("HuaweiIdOAuthService", "IllegalArgumentException", true);
            ksg.c(activity, 907115008, 404, "IllegalArgumentException:" + e.getMessage(), str4, "accountPickerH5.qrCodeAuthLogin", "api_ret");
        } catch (RuntimeException e2) {
            ksy.c("HuaweiIdOAuthService", "RuntimeException", true);
            ksg.c(activity, 907115008, 404, "RuntimeException:" + e2.getMessage(), str4, "accountPickerH5.qrCodeAuthLogin", "api_ret");
        } catch (Exception e3) {
            ksy.c("HuaweiIdOAuthService", "Exception", true);
            ksg.c(activity, 907115008, 404, "Exception:" + e3.getMessage(), str4, "accountPickerH5.qrCodeAuthLogin", "api_ret");
        }
    }

    public static void bQj_(Activity activity, String str, String str2, String str3, ResultCallBack<kqs> resultCallBack) {
        ksy.b("HuaweiIdOAuthService", "openPersonalInfo start.", true);
        if (activity == null) {
            ksy.c("HuaweiIdOAuthService", "activity is null.", true);
            return;
        }
        String a2 = ksg.a();
        ksg.c(activity, 907115006, 0, "enter openPersonalInfo", a2, "accountPickerH5.openPersonalInfo", "api_entry");
        if (TextUtils.isEmpty(str2) || resultCallBack == null || TextUtils.isEmpty(str)) {
            ksy.c("HuaweiIdOAuthService", "openPersonalInfo param error", true);
            ksg.c(activity, 907115006, 404, "openPersonalInfo param error", a2, "accountPickerH5.openPersonalInfo", "api_ret");
            if (resultCallBack != null) {
                resultCallBack.onResult(new kqs(new kqm(404, "openPersonalInfo param error")));
                return;
            }
            return;
        }
        if (!ksi.g(activity)) {
            ksy.c("HuaweiIdOAuthService", "network is unavailable.", true);
            ksg.c(activity, 907115006, 2005, "Network is Unavailable", a2, "accountPickerH5.openPersonalInfo", "api_ret");
            kqm kqmVar = new kqm(2005, "Network is Unavailable");
            kqmVar.b(false);
            resultCallBack.onResult(new kqs(kqmVar));
            return;
        }
        bQc_(activity, str, str3, str2, a2, resultCallBack);
    }

    private static void bQc_(Activity activity, String str, String str2, String str3, String str4, ResultCallBack<kqs> resultCallBack) {
        try {
            ksi.e(resultCallBack);
            Intent intent = new Intent(activity, (Class<?>) WebViewActivity.class);
            intent.putExtra("key_app_id", Util.getAppId(activity));
            intent.putExtra("key_device_info", (Parcelable) DeviceInfo.a(activity, str2));
            intent.putExtra("key_access_token", str3);
            intent.putExtra("key_oper", "open_personal_info");
            intent.putExtra("key_transId", str4);
            intent.putExtra("grs_app_name", str);
            intent.setPackage(activity.getPackageName());
            activity.startActivity(intent);
        } catch (IllegalArgumentException e) {
            ksy.c("HuaweiIdOAuthService", "IllegalArgumentException", true);
            ksg.c(activity, 907115006, 404, "IllegalArgumentException:" + e.getMessage(), str4, "accountPickerH5.openPersonalInfo", "api_ret");
        } catch (RuntimeException e2) {
            ksy.c("HuaweiIdOAuthService", "RuntimeException", true);
            ksg.c(activity, 907115006, 404, "RuntimeException:" + e2.getMessage(), str4, "accountPickerH5.openPersonalInfo", "api_ret");
        } catch (Exception e3) {
            ksy.c("HuaweiIdOAuthService", "Exception", true);
            ksg.c(activity, 907115006, 404, "Exception:" + e3.getMessage(), str4, "accountPickerH5.openPersonalInfo", "api_ret");
        }
    }

    public static void bQi_(Activity activity, String str, String str2, String str3, String str4, ResultCallBack<kqq> resultCallBack) {
        ksy.b("HuaweiIdOAuthService", "openAccountCenter start.", true);
        if (activity == null) {
            ksy.c("HuaweiIdOAuthService", "activity is null.", true);
            return;
        }
        String a2 = ksg.a();
        ksg.c(activity, 907115004, 0, "enter openAccountCenter", a2, "accountPickerH5.openAccountManager_v3", "api_entry");
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || resultCallBack == null || TextUtils.isEmpty(str)) {
            ksy.c("HuaweiIdOAuthService", "openAccountManager param error", true);
            ksg.c(activity, 907115004, 404, "openAccountManager param error", a2, "accountPickerH5.openAccountManager_v3", "api_ret");
            if (resultCallBack != null) {
                resultCallBack.onResult(new kqq(new kqm(404, "openAccountManager param error")));
                return;
            }
            return;
        }
        if (!ksi.g(activity)) {
            ksy.c("HuaweiIdOAuthService", "network is unavailable.", true);
            ksg.c(activity, 907115004, 2005, "Network is Unavailable", a2, "accountPickerH5.openAccountManager_v3", "api_ret");
            kqm kqmVar = new kqm(2005, "Network is Unavailable");
            kqmVar.b(false);
            resultCallBack.onResult(new kqq(kqmVar));
            return;
        }
        bQe_(activity, str, str2, str4, a2, str3, resultCallBack);
    }

    private static void bQe_(Activity activity, String str, String str2, String str3, String str4, String str5, ResultCallBack<kqq> resultCallBack) {
        try {
            ksi.h(resultCallBack);
            Intent intent = new Intent(activity, (Class<?>) WebViewActivity.class);
            intent.putExtra("key_app_id", Util.getAppId(activity));
            intent.putExtra("key_access_token", str2);
            intent.putExtra("reqClientType", str3);
            intent.putExtra("key_transId", str4);
            intent.putExtra("key_device_info", (Parcelable) DeviceInfo.a(activity, str5));
            intent.putExtra("key_oper", "from_open_center_mng_new");
            intent.putExtra("key_login_mode", d);
            intent.putExtra("grs_app_name", str);
            intent.setPackage(activity.getPackageName());
            activity.startActivity(intent);
        } catch (IllegalArgumentException e) {
            ksy.c("HuaweiIdOAuthService", "enterAccountCenter IllegalArgumentException", true);
            ksg.c(activity, 907115004, 404, "IllegalArgumentException:" + e.getMessage(), str4, "accountPickerH5.openAccountManager_v3", "api_ret");
        } catch (RuntimeException e2) {
            ksy.c("HuaweiIdOAuthService", "RuntimeException", true);
            ksg.c(activity, 907115004, 404, "RuntimeException:" + e2.getMessage(), str4, "accountPickerH5.openAccountManager_v3", "api_ret");
        } catch (Exception e3) {
            ksy.c("HuaweiIdOAuthService", "Exception", true);
            ksg.c(activity, 907115004, 404, "Exception:" + e3.getMessage(), str4, "accountPickerH5.openAccountManager_v3", "api_ret");
        }
    }

    public static void bQg_(Activity activity, String str, String str2, String str3, String str4, ResultCallBack<kqc> resultCallBack) {
        ksy.b("HuaweiIdOAuthService", "chkUserPassword start.", true);
        if (activity == null) {
            ksy.c("HuaweiIdOAuthService", "activity is null.", true);
            return;
        }
        String a2 = ksg.a();
        ksg.c(activity, 907115003, 0, "enter chkUserPassword", a2, "accountPickerH5.chkUserPassword_v3", "api_entry");
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str4) || TextUtils.isEmpty(str)) {
            ksy.c("HuaweiIdOAuthService", "chkUserPassword param error", true);
            ksg.c(activity, 907115003, 404, "chkUserPassword param error", a2, "accountPickerH5.chkUserPassword_v3", "api_ret");
            if (resultCallBack != null) {
                resultCallBack.onResult(new kqc("", new kqm(404, "chkUserPassword param error")));
                return;
            }
            return;
        }
        if (!ksi.g(activity)) {
            ksy.c("HuaweiIdOAuthService", "network is unavailable.", true);
            ksg.c(activity, 907115003, 2005, "Network is Unavailable", a2, "accountPickerH5.chkUserPassword_v3", "api_ret");
            kqm kqmVar = new kqm(2005, "Network is Unavailable");
            kqmVar.b(false);
            resultCallBack.onResult(new kqc("", kqmVar));
            return;
        }
        bQf_(activity, str, str2, str3, a2, str4, resultCallBack);
    }

    private static void bQf_(Activity activity, String str, String str2, String str3, String str4, String str5, ResultCallBack<kqc> resultCallBack) {
        try {
            ksi.d(resultCallBack);
            Intent intent = new Intent(activity, (Class<?>) WebViewActivity.class);
            intent.putExtra("key_app_id", Util.getAppId(activity));
            intent.putExtra("key_access_token", str2);
            intent.putExtra("key_check_password_type", str3);
            intent.putExtra("key_transId", str4);
            intent.putExtra("key_device_info", (Parcelable) DeviceInfo.a(activity, str5));
            intent.putExtra("key_oper", "verify_password_new");
            intent.putExtra("grs_app_name", str);
            intent.setPackage(activity.getPackageName());
            activity.startActivity(intent);
        } catch (IllegalArgumentException e) {
            ksy.c("HuaweiIdOAuthService", "chkUserPassword IllegalArgumentException", true);
            ksg.c(activity, 907115003, 404, "IllegalArgumentException:" + e.getMessage(), str4, "accountPickerH5.chkUserPassword_v3", "api_ret");
        } catch (RuntimeException e2) {
            ksy.c("HuaweiIdOAuthService", "RuntimeException", true);
            ksg.c(activity, 907115003, 404, "RuntimeException:" + e2.getMessage(), str4, "accountPickerH5.chkUserPassword_v3", "api_ret");
        } catch (Exception e3) {
            ksy.c("HuaweiIdOAuthService", "Exception", true);
            ksg.c(activity, 907115003, 404, "Exception:" + e3.getMessage(), str4, "accountPickerH5.chkUserPassword_v3", "api_ret");
        }
    }

    public static void c(final Context context, String str, String str2, final ResultCallBack<kqp> resultCallBack) throws kqk {
        ksy.b("HuaweiIdOAuthService", "enter revoke", true);
        if (context == null) {
            ksy.c("HuaweiIdOAuthService", "mContext is null.", true);
            return;
        }
        final String a2 = ksg.a();
        ksg.c(context, 907115009, 0, "enter revoke", a2, "accountPickerH5.revoke", "api_entry");
        if (TextUtils.isEmpty(str2)) {
            ksy.b("HuaweiIdOAuthService", "revoke parameter error: AT invalid", true);
            ksg.c(context, 907115009, 404, "AT is empty", a2, "accountPickerH5.revoke", "api_ret");
            throw new kqk("AT is empty");
        }
        if (TextUtils.isEmpty(str)) {
            ksy.b("HuaweiIdOAuthService", "revoke parameter error: grsAppName invalid", true);
            ksg.c(context, 907115009, 404, "grsAppName is empty", a2, "accountPickerH5.revoke", "api_ret");
            throw new kqk("grsAppName is empty");
        }
        if (resultCallBack == null) {
            ksy.b("HuaweiIdOAuthService", "revoke parameter error: resultCallBack is null", true);
            ksg.c(context, 907115009, 404, "ResultResultCallBack is null", a2, "accountPickerH5.revoke", "api_ret");
            throw new kqk("ResultResultCallBack is null");
        }
        if (!ksi.g(context)) {
            ksy.c("HuaweiIdOAuthService", "network is unavailable.", true);
            ksg.c(context, 907115009, 2005, "Network is Unavailable", a2, "accountPickerH5.revoke", "api_ret");
            kqm kqmVar = new kqm(2005, "Network is Unavailable");
            kqmVar.b(false);
            resultCallBack.onResult(new kqp(kqmVar));
            return;
        }
        krf.c().c(context, str, new kra(context, str2), new j() { // from class: kqi.2
            @Override // com.huawei.hwidauth.c.j
            public void onSuccess(String str3) {
                ksy.b("HuaweiIdOAuthService", "revoke onSuccess", true);
                ksy.b("HuaweiIdOAuthService", "revoke onSuccess response： " + str3, false);
                ResultCallBack.this.onResult(new kqp(new kqm(200, "success")));
                ksg.c(context, 907115009, 200, "revoke success", a2, "accountPickerH5.revoke", "api_ret");
            }

            @Override // com.huawei.hwidauth.c.j
            public void onFailure(int i, String str3) {
                int i2;
                ksy.b("HuaweiIdOAuthService", "revoke fail", true);
                ksy.b("HuaweiIdOAuthService", "revoke fail response： " + str3, false);
                try {
                    i2 = new JSONObject(str3).optInt("sub_error");
                } catch (JSONException unused) {
                    i2 = 0;
                }
                try {
                    ksy.b("HuaweiIdOAuthService", "revoke fail:server errorCode=" + i2, true);
                } catch (JSONException unused2) {
                    ksy.c("HuaweiIdOAuthService", "revoke parse json exception", true);
                    ResultCallBack.this.onResult(new kqp(kqi.d(i2, str3, 0)));
                    ksg.c(context, 907115009, 0, "revoke fail", a2, "accountPickerH5.revoke", "api_ret");
                }
                ResultCallBack.this.onResult(new kqp(kqi.d(i2, str3, 0)));
                ksg.c(context, 907115009, 0, "revoke fail", a2, "accountPickerH5.revoke", "api_ret");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static kqm d(int i, String str, int i2) {
        int i3;
        String str2;
        if (i == 31218 || i == 31202 || i == 11205 || i == 31204) {
            i3 = 2008;
            str2 = "AccessToken is invalid.";
        } else if (i == 60005) {
            i3 = 404;
            str2 = "Server handle error";
        } else {
            i3 = 2015;
            str2 = "oauth server inner error";
        }
        ksy.b("HuaweiIdOAuthService", "revoke fail  sdkErrorCode=" + i3 + " sdkErrCodeDes=" + str2, true);
        return new kqm(i3, str2);
    }

    public static void bQl_(Activity activity, String str, String str2, String str3, ResultCallBack<kqs> resultCallBack) {
        ksy.b("HuaweiIdOAuthService", "qrCodeAuthorize start.", true);
        if (activity == null) {
            ksy.c("HuaweiIdOAuthService", "activity is null.", true);
            return;
        }
        String a2 = ksg.a();
        ksg.c(activity, 907115011, 0, "enter qrCodeAuthorize", a2, "accountPickerH5.qrCodeAuthorize", "api_entry");
        if (TextUtils.isEmpty(str3) || resultCallBack == null || TextUtils.isEmpty(str)) {
            ksy.c("HuaweiIdOAuthService", "qrCodeAuthorize param error", true);
            ksg.c(activity, 907115011, 404, "qrCodeAuthorize param error", a2, "accountPickerH5.qrCodeAuthorize", "api_ret");
            if (resultCallBack != null) {
                resultCallBack.onResult(new kqs(new kqm(404, "qrCodeAuthorize param error")));
                return;
            }
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str3);
            String optString = jSONObject.optString("user_code");
            String optString2 = jSONObject.optString("verification_url");
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                ksy.c("HuaweiIdOAuthService", "userCode or verificationUrl is null", true);
                ksg.c(activity, 907115011, 404, "qrCodeAuthorize param error", a2, "accountPickerH5.qrCodeAuthorize", "api_ret");
                resultCallBack.onResult(new kqs(new kqm(404, "qrCodeAuthorize param error")));
            } else {
                if (!ksi.g(activity)) {
                    ksy.c("HuaweiIdOAuthService", "network is unavailable.", true);
                    ksg.c(activity, 907115011, 2005, "Network is Unavailable", a2, "accountPickerH5.qrCodeAuthorize", "api_ret");
                    kqm kqmVar = new kqm(2005, "Network is Unavailable");
                    kqmVar.b(false);
                    resultCallBack.onResult(new kqs(kqmVar));
                    return;
                }
                bQh_(activity, str, str2, optString, optString2, a2, resultCallBack);
            }
        } catch (JSONException e) {
            ksy.c("HuaweiIdOAuthService", "JSONException", true);
            ksg.c(activity, 907115011, 404, "Exception:" + e.getMessage(), a2, "accountPickerH5.qrCodeAuthorize", "api_ret");
            resultCallBack.onResult(new kqs(new kqm(404, "exception occured")));
        }
    }

    private static void bQh_(Activity activity, String str, String str2, String str3, String str4, String str5, ResultCallBack<kqs> resultCallBack) {
        try {
            ksi.a(resultCallBack);
            Intent intent = new Intent(activity, (Class<?>) WebViewActivity.class);
            intent.putExtra("key_app_id", Util.getAppId(activity));
            intent.putExtra("key_qr_code_source", TextUtils.isEmpty(str2) ? "0" : str2);
            intent.putExtra("user_code", str3);
            intent.putExtra("verification_url", str4);
            try {
                intent.putExtra("key_transId", str5);
                intent.putExtra("key_oper", "from_qr_authorize");
                intent.putExtra("grs_app_name", str);
                intent.setPackage(activity.getPackageName());
                activity.startActivity(intent);
            } catch (IllegalArgumentException e) {
                e = e;
                ksy.c("HuaweiIdOAuthService", "openRealNameInfo IllegalArgumentException", true);
                ksg.c(activity, 907115011, 404, "IllegalArgumentException:" + e.getMessage(), str5, "accountPickerH5.qrCodeAuthorize", "api_ret");
                resultCallBack.onResult(new kqs(new kqm(404, "exception occured")));
            } catch (RuntimeException e2) {
                e = e2;
                ksy.c("HuaweiIdOAuthService", "RuntimeException", true);
                ksg.c(activity, 907115011, 404, "RuntimeException:" + e.getMessage(), str5, "accountPickerH5.qrCodeAuthorize", "api_ret");
                resultCallBack.onResult(new kqs(new kqm(404, "exception occured")));
            } catch (Exception e3) {
                e = e3;
                ksy.c("HuaweiIdOAuthService", "Exception", true);
                ksg.c(activity, 907115011, 404, "Exception:" + e.getMessage(), str5, "accountPickerH5.qrCodeAuthorize", "api_ret");
                resultCallBack.onResult(new kqs(new kqm(404, "exception occured")));
            }
        } catch (IllegalArgumentException e4) {
            e = e4;
        } catch (RuntimeException e5) {
            e = e5;
        } catch (Exception e6) {
            e = e6;
        }
    }
}
