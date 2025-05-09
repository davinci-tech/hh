package defpackage;

import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ImageSpan;
import android.text.style.TypefaceSpan;
import com.huawei.health.R;
import com.huawei.uikit.hwsubtab.widget.HwSubTabListener;
import com.huawei.uikit.hwsubtab.widget.HwSubTabWidget;

/* loaded from: classes7.dex */
public class smy {

    /* renamed from: a, reason: collision with root package name */
    private int f17131a;
    private HwSubTabListener b;
    private CharSequence c;
    private Object d;
    private HwSubTabWidget e;
    private int f;

    public smy(HwSubTabWidget hwSubTabWidget) {
        this(hwSubTabWidget, "");
    }

    private SpannableString eho_(CharSequence charSequence, CharSequence charSequence2) {
        SpannableString spannableString = new SpannableString(((Object) charSequence) + " " + ((Object) charSequence2));
        spannableString.setSpan(new ImageSpan(this.e.getContext(), R.drawable._2131429573_res_0x7f0b08c5), charSequence.length(), charSequence.length() + 1, 33);
        spannableString.setSpan(new AbsoluteSizeSpan(this.e.getContext().getResources().getDimensionPixelSize(R.dimen._2131364470_res_0x7f0a0a76)), charSequence.length(), spannableString.length(), 33);
        spannableString.setSpan(new TypefaceSpan(this.e.getContext().getString(R.string._2130850837_res_0x7f023415)), charSequence.length(), spannableString.length(), 33);
        return spannableString;
    }

    public HwSubTabListener a() {
        return this.b;
    }

    public void c(int i) {
        this.f17131a = i;
    }

    public smy d(HwSubTabListener hwSubTabListener) {
        this.b = hwSubTabListener;
        return this;
    }

    public int e() {
        return this.f17131a;
    }

    public smy e(Object obj) {
        this.d = obj;
        return this;
    }

    public void g() {
        this.e.c(this);
        this.e.e(this);
    }

    public int h() {
        return this.f;
    }

    public CharSequence i() {
        return this.c;
    }

    public Object j() {
        return this.d;
    }

    public smy(HwSubTabWidget hwSubTabWidget, CharSequence charSequence) {
        this(hwSubTabWidget, charSequence, null);
    }

    public smy(HwSubTabWidget hwSubTabWidget, CharSequence charSequence, HwSubTabListener hwSubTabListener) {
        this(hwSubTabWidget, charSequence, hwSubTabListener, null);
    }

    public smy(HwSubTabWidget hwSubTabWidget, CharSequence charSequence, HwSubTabListener hwSubTabListener, Object obj) {
        this(hwSubTabWidget, charSequence, "", hwSubTabListener, obj);
    }

    public smy d(CharSequence charSequence) {
        this.c = charSequence;
        int i = this.f17131a;
        if (i >= 0) {
            this.e.d(i);
        }
        return this;
    }

    public smy(HwSubTabWidget hwSubTabWidget, CharSequence charSequence, CharSequence charSequence2, HwSubTabListener hwSubTabListener, Object obj) {
        this.f17131a = -1;
        this.f = -1;
        this.e = hwSubTabWidget;
        this.b = hwSubTabListener;
        this.d = obj;
        if (charSequence2 != null && !charSequence2.equals("")) {
            this.c = eho_(charSequence, charSequence2);
        } else {
            this.c = charSequence;
        }
    }
}
