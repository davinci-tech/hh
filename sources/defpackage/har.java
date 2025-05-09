package defpackage;

import android.graphics.Color;
import com.huawei.healthcloud.plugintrack.trackanimation.dynamiccurve.auxiliary.CurveColorControl;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class har extends CurveColorControl {
    private static final int[] e = {Color.argb(76, 255, 189, 0), Color.argb(255, 255, 189, 0), Color.argb(255, 255, 189, 0)};

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f13052a = {Color.argb(51, 255, 255, 255), Color.argb(0, 255, 255, 255)};
    private static final int[] c = {Color.argb(255, 255, 189, 0), Color.argb(0, 255, 189, 0)};
    private static final int b = Color.argb(255, 255, 189, 0);

    public har() {
        this.mRunCurveColor = e;
        this.mRunFillingColor = f13052a;
        this.mRunLineColor = c;
        this.mRunCircleColor = b;
        LogUtil.a("Track_StepRateCurveColor", "init step rate curve");
    }
}
