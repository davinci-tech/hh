package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Locale;

/* loaded from: classes5.dex */
public class jmn {

    /* renamed from: a, reason: collision with root package name */
    private String f13960a;
    private String b;
    private String c = "";
    private int d;
    private String e;

    public int e() {
        return this.d;
    }

    public void e(int i) {
        this.d = i;
    }

    public String a() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public String c() {
        return this.f13960a;
    }

    public void a(String str) {
        this.f13960a = str;
    }

    public String b() {
        return this.b;
    }

    public void d(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            LogUtil.h("ApduCommand", "setChecker checkers is null or length is 0");
            this.b = null;
            return;
        }
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i].toUpperCase(Locale.getDefault()));
            if (i != strArr.length - 1) {
                sb.append("|");
            }
        }
        this.b = sb.toString();
    }

    public String d() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 4) {
            LogUtil.h("ApduCommand", "setChecker responseString length is 0 or less SW_LENGTH");
            this.f13960a = str;
        } else {
            this.f13960a = str.substring(0, str.length() - 4);
            this.c = str.substring(str.length() - 4, str.length());
        }
    }

    public String toString() {
        return "ApduCommand{index=" + this.d + ", apdu='" + this.e + "', rapdu='" + this.f13960a + "', checker='" + this.b + "', sw='" + this.c + "'}";
    }
}
