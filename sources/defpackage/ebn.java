package defpackage;

import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import java.util.List;

/* loaded from: classes8.dex */
public class ebn {

    /* renamed from: a, reason: collision with root package name */
    private OnClickSectionListener f11944a;
    private List<String> b;
    private List<Object> c;
    private List<String> d;
    private List<List<PriceTagBean>> e;

    public List<Object> a() {
        return this.c;
    }

    public void e(List<Object> list) {
        this.c = list;
    }

    public List<String> b() {
        return this.d;
    }

    public void d(List<String> list) {
        this.d = list;
    }

    public List<String> e() {
        return this.b;
    }

    public void a(List<String> list) {
        this.b = list;
    }

    public List<List<PriceTagBean>> c() {
        return this.e;
    }

    public void b(List<List<PriceTagBean>> list) {
        this.e = list;
    }

    public OnClickSectionListener d() {
        return this.f11944a;
    }

    public void a(OnClickSectionListener onClickSectionListener) {
        this.f11944a = onClickSectionListener;
    }
}
