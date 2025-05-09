package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;

/* loaded from: classes3.dex */
public class edu {

    /* renamed from: a, reason: collision with root package name */
    private String f11970a;
    private Drawable b;
    private String c;
    private String d;
    private View.OnClickListener e;
    private eem i;
    private String j;

    public edu(String str, String str2, Drawable drawable, eem eemVar, String str3, View.OnClickListener onClickListener) {
        this.j = str;
        this.c = str2;
        this.b = drawable;
        this.i = eemVar;
        this.f11970a = str3;
        this.e = onClickListener;
    }

    public edu(String str, String str2, eem eemVar, String str3, View.OnClickListener onClickListener, String str4) {
        this.j = str;
        this.c = str2;
        this.i = eemVar;
        this.f11970a = str3;
        this.e = onClickListener;
        this.d = str4;
    }

    public String b() {
        return this.d;
    }

    public String d() {
        return this.j;
    }

    public String a() {
        return this.c;
    }

    public eem i() {
        return this.i;
    }

    public String c() {
        return this.f11970a;
    }

    public View.OnClickListener agI_() {
        return this.e;
    }

    public String toString() {
        return "Section16_9Card_01_BannerItem{mTitle='" + this.j + "', mDesc='" + this.c + "', mTopRightStatus=" + this.i + ", mBottomRightText='" + this.f11970a + "', mOnClickListener=" + this.e + '}';
    }
}
