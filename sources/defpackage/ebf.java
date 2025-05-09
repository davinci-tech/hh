package defpackage;

import com.huawei.ui.commonui.listener.OnClickSectionListener;
import java.util.List;

/* loaded from: classes3.dex */
public class ebf {
    private List<String> b;
    private List<Object> c;
    private OnClickSectionListener d;
    private List<String> e;

    public List<Object> d() {
        return this.c;
    }

    public void d(List<Object> list) {
        this.c = list;
    }

    public List<String> c() {
        return this.b;
    }

    public void c(List<String> list) {
        this.b = list;
    }

    public List<String> b() {
        return this.e;
    }

    public void a(List<String> list) {
        this.e = list;
    }

    public OnClickSectionListener a() {
        return this.d;
    }

    public void c(OnClickSectionListener onClickSectionListener) {
        this.d = onClickSectionListener;
    }
}
