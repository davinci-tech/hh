package defpackage;

import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;

/* loaded from: classes3.dex */
public class edo extends edp {

    /* renamed from: a, reason: collision with root package name */
    private boolean f11964a = true;
    private View.OnClickListener b;
    private CharSequence c;
    private String d;
    private int e;
    private int g;
    private String h;

    public edo() {
    }

    public edo(String str, CharSequence charSequence, String str2, int i, int i2, View.OnClickListener onClickListener) {
        this.d = str;
        this.c = charSequence;
        this.h = str2;
        this.g = i;
        this.e = i2;
        this.b = onClickListener;
    }

    public edo e(int i) {
        super.a(i);
        return this;
    }

    public String e() {
        return this.d;
    }

    public CharSequence d() {
        return this.c;
    }

    public String i() {
        return this.h;
    }

    public int f() {
        return this.g;
    }

    public int h() {
        return this.e;
    }

    public View.OnClickListener agE_() {
        return this.b;
    }

    public boolean j() {
        return !TextUtils.isEmpty(this.d);
    }

    public boolean g() {
        return this.f11964a;
    }

    public void b(boolean z) {
        this.f11964a = z;
    }

    public void agF_(SpannableString spannableString) {
        this.c = spannableString;
    }
}
