package defpackage;

import com.huawei.ui.commonui.linechart.icommon.IHwHealthDataSetStyle;

/* loaded from: classes6.dex */
public class nnj {
    private boolean e = false;

    /* renamed from: a, reason: collision with root package name */
    private IHwHealthDataSetStyle f15399a = new nqp();

    public void a(boolean z) {
        this.e = z;
    }

    public boolean a() {
        return this.e;
    }

    public void d(IHwHealthDataSetStyle iHwHealthDataSetStyle) {
        this.f15399a = iHwHealthDataSetStyle;
    }

    public IHwHealthDataSetStyle e() {
        return this.f15399a;
    }

    public void d(nnj nnjVar) {
        if (nnjVar != null) {
            this.e = nnjVar.e;
            this.f15399a = nnjVar.f15399a;
        }
    }
}
