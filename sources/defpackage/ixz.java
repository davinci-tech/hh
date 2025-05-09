package defpackage;

import android.text.TextUtils;
import com.huawei.utils.FoundationCommonUtil;

/* loaded from: classes5.dex */
public class ixz {

    /* renamed from: a, reason: collision with root package name */
    private String f13655a;
    private int b;
    private String c;
    private String d;
    private String e;
    private String i;
    private String j;

    public String d() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public String b() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public String a() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public void h(String str) {
        this.j = str;
    }

    public void e(String str) {
        this.i = str;
    }

    public void d(String str) {
        this.f13655a = str;
    }

    public void b(int i) {
        this.b = i;
    }

    public int hashCode() {
        String str = this.c;
        return (str == null ? 0 : str.hashCode()) + 527;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ixz) {
            return !TextUtils.isEmpty(b()) && b().equals(((ixz) obj).b());
        }
        return false;
    }

    public String e() {
        if (!TextUtils.isEmpty(this.j)) {
            return this.j;
        }
        return FoundationCommonUtil.getEncryptionLogMark(this.i, this.f13655a, this.b);
    }
}
