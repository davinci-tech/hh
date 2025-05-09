package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode;

/* loaded from: classes6.dex */
public class npk implements HwHealthRenderMode.NodeStyle {
    private float c;
    private float e;

    public npk(float f) {
        this.e = f;
        this.c = f - nsn.c(BaseApplication.getContext(), 2.0f);
    }

    @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.NodeStyle
    public float getOuterRadius() {
        return this.e;
    }

    @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.NodeStyle
    public float getInnerRadius() {
        return this.c;
    }
}
