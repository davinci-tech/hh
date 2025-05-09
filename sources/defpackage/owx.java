package defpackage;

import android.text.TextUtils;

/* loaded from: classes6.dex */
public class owx {
    private String b;
    private String c;
    private boolean d;

    public owx() {
    }

    public owx(String str, boolean z, String str2) {
        this.c = str;
        this.d = z;
        this.b = str2;
    }

    public String b() {
        return this.c;
    }

    public boolean a() {
        return this.d;
    }

    public void e(boolean z) {
        this.d = z;
    }

    public String d() {
        return this.b;
    }

    public boolean e() {
        String str;
        return (this.c == null || (str = this.b) == null || !str.startsWith("http")) ? false : true;
    }

    public boolean b(owx owxVar) {
        return owxVar != null && TextUtils.equals(this.b, owxVar.d()) && TextUtils.equals(this.c, owxVar.b()) && this.d == owxVar.a();
    }
}
