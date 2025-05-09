package defpackage;

import com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode;

/* loaded from: classes6.dex */
public class npn implements HwHealthRenderMode.NodeStyle {
    private float d;

    public npn(float f) {
        this.d = f;
    }

    @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.NodeStyle
    public float getOuterRadius() {
        return this.d;
    }

    @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.NodeStyle
    public float getInnerRadius() {
        return this.d;
    }
}
