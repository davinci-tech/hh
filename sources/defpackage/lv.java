package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.huawei.operation.utils.Constants;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class lv {

    /* renamed from: a, reason: collision with root package name */
    public Context f14866a;
    public final String b;
    public String c;
    public String d;
    public final long e;
    public final String f;
    public final int j;
    public final kn n;
    public final ActivityInfo o;
    public boolean h = false;
    public boolean g = false;
    public boolean i = false;

    public lv(Context context, String str, String str2) {
        String str3;
        this.d = "";
        this.c = "";
        this.f14866a = null;
        boolean isEmpty = TextUtils.isEmpty(str2);
        this.n = new kn(context, isEmpty);
        String d = d(str, this.c);
        this.b = d;
        this.e = SystemClock.elapsedRealtime();
        this.j = md.f();
        ActivityInfo ba_ = md.ba_(context);
        this.o = ba_;
        this.f = str2;
        if (!isEmpty) {
            kl.a(this, "biz", "eptyp", str2 + "|" + d);
            if (ba_ != null) {
                str3 = ba_.name + "|" + ba_.launchMode;
            } else {
                str3 = Constants.NULL;
            }
            kl.a(this, "biz", "actInfo", str3);
            kl.a(this, "biz", NotificationCompat.CATEGORY_SYSTEM, md.b(this));
            kl.a(this, "biz", "sdkv", "1d4833b-clean");
        }
        try {
            this.f14866a = context.getApplicationContext();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.d = packageInfo.versionName;
            this.c = packageInfo.packageName;
        } catch (Exception e2) {
            ma.c(e2);
        }
        if (!isEmpty) {
            kl.b(this, "biz", "u" + md.f());
            kl.a(this, "biz", "PgApiInvoke", "" + SystemClock.elapsedRealtime());
            kl.c(context, this, str, this.b);
        }
        if (isEmpty || !kr.a().y()) {
            return;
        }
        kr.a().a(this, this.f14866a, true, 2);
    }

    private String a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str.substring(19));
            jSONObject.put("bizcontext", c(jSONObject.optString("bizcontext")));
            return "new_external_info==" + jSONObject.toString();
        } catch (Throwable unused) {
            return str;
        }
    }

    private String b(String str) {
        try {
            String e2 = e(str, "\"&", "bizcontext=\"");
            if (TextUtils.isEmpty(e2)) {
                return str + "&" + c("bizcontext=\"", "\"");
            }
            if (!e2.endsWith("\"")) {
                e2 = e2 + "\"";
            }
            int indexOf = str.indexOf(e2);
            return str.substring(0, indexOf) + d(e2, "bizcontext=\"", "\"") + str.substring(indexOf + e2.length());
        } catch (Throwable th) {
            kl.a(this, "biz", "fmt2", th, str);
            return str;
        }
    }

    public static lv c() {
        return null;
    }

    private boolean f(String str) {
        return !str.contains("\"&");
    }

    private JSONObject h() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ap_link_token", this.b);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    public Context a() {
        return this.f14866a;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.c;
    }

    private String d(String str) {
        try {
            String e2 = e(str, "&", "bizcontext=");
            if (TextUtils.isEmpty(e2)) {
                str = str + "&" + c("bizcontext=", "");
            } else {
                int indexOf = str.indexOf(e2);
                str = str.substring(0, indexOf) + d(e2, "bizcontext=", "") + str.substring(indexOf + e2.length());
            }
        } catch (Throwable th) {
            kl.a(this, "biz", "fmt1", th, str);
        }
        return str;
    }

    private String d(String str, String str2, String str3) throws JSONException {
        JSONObject jSONObject;
        String substring = str.substring(str2.length());
        boolean z = false;
        String substring2 = substring.substring(0, substring.length() - str3.length());
        if (substring2.length() >= 2 && substring2.startsWith("\"") && substring2.endsWith("\"")) {
            jSONObject = new JSONObject(substring2.substring(1, substring2.length() - 1));
            z = true;
        } else {
            jSONObject = new JSONObject(substring2);
        }
        String a2 = a(jSONObject);
        if (z) {
            a2 = "\"" + a2 + "\"";
        }
        return str2 + a2 + str3;
    }

    public String e(String str) {
        return TextUtils.isEmpty(str) ? str : str.startsWith("new_external_info==") ? a(str) : f(str) ? d(str) : b(str);
    }

    public boolean f() {
        return this.i;
    }

    public static final class e {
        public static final HashMap<UUID, lv> b = new HashMap<>();
        public static final HashMap<String, lv> d = new HashMap<>();

        public static void a(lv lvVar, Intent intent) {
            if (lvVar == null || intent == null) {
                return;
            }
            UUID randomUUID = UUID.randomUUID();
            b.put(randomUUID, lvVar);
            intent.putExtra("i_uuid_b_c", randomUUID);
        }

        public static lv a(Intent intent) {
            if (intent == null) {
                return null;
            }
            Serializable serializableExtra = intent.getSerializableExtra("i_uuid_b_c");
            if (serializableExtra instanceof UUID) {
                return b.remove((UUID) serializableExtra);
            }
            return null;
        }

        public static void e(lv lvVar, String str) {
            if (lvVar == null || TextUtils.isEmpty(str)) {
                return;
            }
            d.put(str, lvVar);
        }

        public static lv b(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return d.remove(str);
        }
    }

    public boolean b() {
        return this.g;
    }

    public void c(boolean z) {
        this.i = z;
    }

    private String e(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(str2);
        for (int i = 0; i < split.length; i++) {
            if (!TextUtils.isEmpty(split[i]) && split[i].startsWith(str3)) {
                return split[i];
            }
        }
        return null;
    }

    public boolean i() {
        return this.h;
    }

    private String c(String str) throws JSONException {
        return a(new JSONObject(str));
    }

    private String c(String str, String str2) {
        return str + a(new JSONObject()) + str2;
    }

    public String a(JSONObject jSONObject) {
        String str;
        try {
            if (!jSONObject.has("appkey")) {
                jSONObject.put("appkey", "2014052600006128");
            }
            if (!jSONObject.has("ty")) {
                jSONObject.put("ty", "and_lite");
            }
            if (!jSONObject.has("sv")) {
                jSONObject.put("sv", "h.a.3.8.14");
            }
            if (!jSONObject.has("an")) {
                jSONObject.put("an", this.c);
            }
            if (!jSONObject.has("av")) {
                jSONObject.put("av", this.d);
            }
            if (!jSONObject.has("sdk_start_time")) {
                jSONObject.put("sdk_start_time", System.currentTimeMillis());
            }
            if (!jSONObject.has("extInfo")) {
                jSONObject.put("extInfo", h());
            }
            if (!jSONObject.has("act_info")) {
                if (this.o != null) {
                    str = this.o.name + "|" + this.o.launchMode;
                } else {
                    str = Constants.NULL;
                }
                jSONObject.put("act_info", str);
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            kl.a(this, "biz", "fmt3", th, String.valueOf(jSONObject));
            ma.c(th);
            return jSONObject != null ? jSONObject.toString() : "{}";
        }
    }

    public static String d(String str, String str2) {
        try {
            Locale locale = Locale.getDefault();
            Object[] objArr = new Object[4];
            if (str == null) {
                str = "";
            }
            objArr[0] = str;
            if (str2 == null) {
                str2 = "";
            }
            objArr[1] = str2;
            objArr[2] = Long.valueOf(System.currentTimeMillis());
            objArr[3] = UUID.randomUUID().toString();
            return String.format("EP%s%s_%s", "1", md.h(String.format(locale, "%s%s%d%s", objArr)), Long.valueOf(System.currentTimeMillis()));
        } catch (Throwable unused) {
            return com.huawei.openalliance.ad.constant.Constants.LINK;
        }
    }

    public static HashMap<String, String> b(lv lvVar) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (lvVar != null) {
            hashMap.put("sdk_ver", "15.8.14");
            hashMap.put("app_name", lvVar.c);
            hashMap.put("token", lvVar.b);
            hashMap.put("call_type", lvVar.f);
            hashMap.put("ts_api_invoke", String.valueOf(lvVar.e));
            lt.c(lvVar, hashMap);
        }
        return hashMap;
    }

    public void e(boolean z) {
        this.h = z;
    }

    public void a(boolean z) {
        this.g = z;
    }
}
