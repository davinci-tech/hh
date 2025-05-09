package defpackage;

import android.text.TextUtils;

/* loaded from: classes2.dex */
public class vq {
    private int b;
    private StringBuilder c;
    private int e;

    public int c() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public String a() {
        StringBuilder sb = this.c;
        if (sb == null) {
            return null;
        }
        return sb.toString();
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        StringBuilder sb = this.c;
        if (sb == null) {
            this.c = new StringBuilder(str);
        } else {
            sb.append("|");
            sb.append(str);
        }
    }

    public void e(int i) {
        this.b = i;
    }

    public int b() {
        return this.b;
    }
}
