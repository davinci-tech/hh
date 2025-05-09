package defpackage;

import android.graphics.drawable.Drawable;
import com.huawei.uikit.hwsubtab.widget.HwSubTabWidget;

/* loaded from: classes6.dex */
public class nqv extends smy {

    /* renamed from: a, reason: collision with root package name */
    private Drawable f15447a;
    private Drawable b;
    private Drawable c;

    public nqv(HwSubTabWidget hwSubTabWidget, CharSequence charSequence) {
        super(hwSubTabWidget, charSequence);
    }

    public Drawable cGm_() {
        return this.f15447a;
    }

    public nqv cGp_(Drawable drawable) {
        this.f15447a = drawable;
        return this;
    }

    public Drawable cGn_() {
        return this.c;
    }

    public nqv cGq_(Drawable drawable) {
        this.c = drawable;
        return this;
    }

    public Drawable cGo_() {
        return this.b;
    }

    public void cGr_(Drawable drawable) {
        this.b = drawable;
    }
}
