package defpackage;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.EnvironmentInfo;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kag {
    public static JSONObject a(Context context) {
        Bundle call;
        JSONObject jSONObject = new JSONObject();
        if (context == null) {
            LogUtil.h("ContactUtils", "buildContactConfig: context is null");
            return jSONObject;
        }
        ContentProviderClient contentProviderClient = null;
        try {
            try {
                ContentResolver contentResolver = context.getContentResolver();
                if (contentResolver == null) {
                    ReleaseLogUtil.d("R_ContactUtils", "buildContactConfig: contentResolver is null");
                    return jSONObject;
                }
                if (EnvironmentInfo.j()) {
                    contentProviderClient = contentResolver.acquireUnstableContentProviderClient("com.huawei.contacts.app");
                    if (contentProviderClient == null) {
                        ReleaseLogUtil.d("R_ContactUtils", "buildContactConfig: contentProviderClient is null");
                        if (contentProviderClient != null) {
                            contentProviderClient.close();
                        }
                        return jSONObject;
                    }
                    call = contentProviderClient.call("com.huawei.contacts.app", "call_method_query_config", "", new Bundle());
                } else {
                    call = contentResolver.call("com.huawei.contacts.app", "call_method_query_config", "", new Bundle());
                }
                if (call == null) {
                    LogUtil.h("ContactUtils", "buildContactConfig: result is null");
                    if (contentProviderClient != null) {
                        contentProviderClient.close();
                    }
                    return jSONObject;
                }
                String string = call.getString("contacts.CONTACT_CONFIG");
                if (TextUtils.isEmpty(string)) {
                    LogUtil.h("ContactUtils", "buildContactConfig: config is null");
                    if (contentProviderClient != null) {
                        contentProviderClient.close();
                    }
                    return jSONObject;
                }
                JSONObject jSONObject2 = new JSONObject(string);
                if (contentProviderClient != null) {
                    contentProviderClient.close();
                }
                return jSONObject2;
            } catch (Exception e) {
                ReleaseLogUtil.c("R_ContactUtils", "buildContactConfig: exception occurred.", ExceptionUtils.d(e));
                if (0 != 0) {
                    contentProviderClient.close();
                }
                return jSONObject;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                contentProviderClient.close();
            }
            throw th;
        }
    }

    public static String e(String str) {
        String b = b("_id", str);
        if (TextUtils.isEmpty(b)) {
            return e() + " AND " + a();
        }
        return b + " AND " + e() + " AND " + a();
    }

    public static String a(String str) {
        String d = d(str);
        if (TextUtils.isEmpty(d)) {
            return a();
        }
        return d + " AND " + a();
    }

    public static String b() {
        return c() + " AND " + d();
    }

    public static String b(String str) {
        String b = b("raw_contact_id", str);
        if (TextUtils.isEmpty(b)) {
            return a();
        }
        return b + " AND " + a();
    }

    private static String a() {
        return "(account_type IS NULL OR account_type NOT IN (" + kac.d() + "))";
    }

    private static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return "contact_id IN (" + str + Constants.RIGHT_BRACKET_ONLY;
    }

    private static String b(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        return str + " IN (" + str2 + Constants.RIGHT_BRACKET_ONLY;
    }

    private static String c() {
        return "has_phone_number=1";
    }

    private static String e() {
        return "deleted=0";
    }

    private static String d() {
        return "contact_last_updated_timestamp>=?";
    }
}
