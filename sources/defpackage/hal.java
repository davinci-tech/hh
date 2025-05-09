package defpackage;

import android.graphics.Color;
import com.huawei.healthcloud.plugintrack.trackanimation.dynamiccurve.auxiliary.CurveColorControl;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class hal extends CurveColorControl {
    private static final int[] c = {Color.argb(76, 120, 226, 228), Color.argb(255, 120, 226, 228), Color.argb(255, 120, 226, 228)};
    private static final int[] b = {Color.argb(51, 255, 255, 255), Color.argb(0, 255, 255, 255)};

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f13047a = {Color.argb(255, 120, 226, 228), Color.argb(0, 120, 226, 228)};
    private static final int e = Color.argb(255, 120, 226, 228);

    public hal() {
        this.mRunCurveColor = c;
        this.mRunFillingColor = b;
        this.mRunLineColor = f13047a;
        this.mRunCircleColor = e;
        LogUtil.a("Track_AltitudeCurveColor", "init altitude curve");
    }
}
