package defpackage;

import android.graphics.drawable.Drawable;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import java.util.List;

/* loaded from: classes3.dex */
public class ebq {

    /* renamed from: a, reason: collision with root package name */
    private List<Drawable> f11945a;
    private List<Integer> b;
    private List<String> c;
    private OnClickSectionListener d;
    private List<String> e;
    private List<String> f;
    private List<Integer> g;
    private List<String> i;

    public ebq(List<Drawable> list, List<String> list2, List<String> list3, List<String> list4, List<Integer> list5, List<String> list6, List<Integer> list7, OnClickSectionListener onClickSectionListener) {
        this.f11945a = list;
        this.e = list2;
        this.i = list3;
        this.c = list4;
        this.b = list5;
        this.f = list6;
        this.g = list7;
        this.d = onClickSectionListener;
    }

    public List<Drawable> b() {
        return this.f11945a;
    }

    public List<String> e() {
        return this.e;
    }

    public List<String> c() {
        return this.c;
    }

    public List<Integer> d() {
        return this.b;
    }

    public List<String> g() {
        return this.f;
    }

    public List<Integer> i() {
        return this.g;
    }

    public OnClickSectionListener a() {
        return this.d;
    }
}
