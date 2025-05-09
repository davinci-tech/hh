package defpackage;

import com.huawei.ui.commonui.listener.OnClickSectionListener;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class eav {

    /* renamed from: a, reason: collision with root package name */
    private List<Object> f11935a;
    private List<Object> b;
    private OnClickSectionListener c;
    private List<Object> d;
    private List<Object> e;
    private List<Map<Object, Object>> f;

    public eav(List<Object> list, List<Object> list2, List<Object> list3, List<Map<Object, Object>> list4, List<Object> list5, OnClickSectionListener onClickSectionListener) {
        this.f11935a = list;
        this.b = list2;
        this.e = list3;
        this.f = list4;
        this.d = list5;
        this.c = onClickSectionListener;
    }

    public List<Object> d() {
        return this.f11935a;
    }

    public List<Object> a() {
        return this.b;
    }

    public List<Object> b() {
        return this.e;
    }

    public List<Map<Object, Object>> j() {
        return this.f;
    }

    public List<Object> c() {
        return this.d;
    }

    public OnClickSectionListener e() {
        return this.c;
    }
}
