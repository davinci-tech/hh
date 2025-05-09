package defpackage;

import com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode;

/* loaded from: classes6.dex */
public class npl implements HwHealthRenderMode.NodeStyle {

    /* renamed from: a, reason: collision with root package name */
    private float f15425a;
    private float d;

    public npl(float f) {
        this.d = f;
        this.f15425a = f / 2.0f;
    }

    @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.NodeStyle
    public float getOuterRadius() {
        return this.d;
    }

    @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.NodeStyle
    public float getInnerRadius() {
        return this.f15425a;
    }
}
