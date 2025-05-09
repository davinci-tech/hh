package defpackage;

import com.github.mikephil.charting.utils.Utils;
import com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode;

/* loaded from: classes6.dex */
public class npo implements HwHealthRenderMode.NodeStyle {

    /* renamed from: a, reason: collision with root package name */
    private float f15427a;
    private float c;

    public npo() {
        this(5.0f, 3.0f);
    }

    public npo(float f, float f2) {
        this.f15427a = Utils.convertDpToPixel(f);
        this.c = Utils.convertDpToPixel(f2);
    }

    @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.NodeStyle
    public float getOuterRadius() {
        return this.f15427a;
    }

    @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.NodeStyle
    public float getInnerRadius() {
        return this.c;
    }
}
