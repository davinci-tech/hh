package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode;

/* loaded from: classes6.dex */
public class npm implements HwHealthRenderMode.NodeStyle {

    /* renamed from: a, reason: collision with root package name */
    private float f15426a;
    private float d;

    public npm(float f) {
        this.f15426a = f;
        this.d = f - nsn.c(BaseApplication.getContext(), 2.0f);
    }

    @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.NodeStyle
    public float getOuterRadius() {
        return this.f15426a;
    }

    @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.NodeStyle
    public float getInnerRadius() {
        return this.d;
    }
}
