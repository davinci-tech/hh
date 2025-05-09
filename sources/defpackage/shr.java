package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Map;

/* loaded from: classes8.dex */
public class shr {

    /* renamed from: a, reason: collision with root package name */
    private String f17061a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String h;
    private String i;

    shr(Map<String, String> map, boolean z) {
        if (map == null) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry != null) {
                String key = entry.getKey();
                if (TextUtils.equals(key, "resultStatus")) {
                    this.h = entry.getValue();
                } else if (TextUtils.equals(key, "result")) {
                    this.f17061a = entry.getValue();
                } else if (TextUtils.equals(key, "memo")) {
                    this.b = entry.getValue();
                } else {
                    LogUtil.a("AuthResult", "key = ", key);
                }
            }
        }
        if (TextUtils.isEmpty(this.f17061a)) {
            return;
        }
        for (String str : this.f17061a.split("&")) {
            if (str.startsWith("alipay_open_id")) {
                this.c = e(d("alipay_open_id=", str), z);
            } else if (str.startsWith("auth_code")) {
                this.e = e(d("auth_code=", str), z);
            } else if (str.startsWith("result_code")) {
                this.d = e(d("result_code=", str), z);
            } else if (str.startsWith("user_id")) {
                this.i = e(d("user_id=", str), z);
            }
        }
    }

    private String e(String str, boolean z) {
        if (!z || TextUtils.isEmpty(str)) {
            return str;
        }
        String replaceFirst = str.startsWith("\"") ? str.replaceFirst("\"", "") : str;
        return replaceFirst.endsWith("\"") ? str.substring(0, replaceFirst.length() - 1) : replaceFirst;
    }

    private String d(String str, String str2) {
        return (str == null || str2 == null) ? "" : str2.substring(str.length(), str2.length());
    }

    public String b() {
        return this.h;
    }

    public String e() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public String a() {
        return this.i;
    }

    public String toString() {
        return "resultStatus={" + this.h + "};memo={" + this.b + "};result={" + this.f17061a + "}";
    }
}
