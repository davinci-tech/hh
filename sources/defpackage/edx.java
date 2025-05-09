package defpackage;

import android.text.TextUtils;
import android.view.View;

/* loaded from: classes3.dex */
public class edx extends edp {

    /* renamed from: a, reason: collision with root package name */
    private int f11971a;
    private View.OnClickListener b;
    private String c;
    private String d;

    public edx() {
    }

    public edx(String str, String str2, int i, View.OnClickListener onClickListener) {
        this.c = str;
        this.d = str2;
        this.f11971a = i;
        this.b = onClickListener;
    }

    public edx b(int i) {
        super.a(i);
        return this;
    }

    public void d(String str) {
        this.d = str;
    }

    public String d() {
        return this.c;
    }

    public String j() {
        return this.d;
    }

    public int a() {
        return this.f11971a;
    }

    public View.OnClickListener agG_() {
        return this.b;
    }

    public boolean f() {
        return !TextUtils.isEmpty(this.c);
    }
}
