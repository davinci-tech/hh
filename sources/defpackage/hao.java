package defpackage;

import android.graphics.Color;
import com.huawei.healthcloud.plugintrack.trackanimation.dynamiccurve.auxiliary.CurveColorControl;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class hao extends CurveColorControl {
    private static final int[] d = {Color.argb(76, 0, a.A, 38), Color.argb(255, 0, a.A, 38), Color.argb(255, 0, a.A, 38)};
    private static final int[] c = {Color.argb(51, 255, 255, 255), Color.argb(0, 255, 255, 255)};
    private static final int[] e = {Color.argb(255, 0, a.A, 38), Color.argb(0, 0, a.A, 38)};

    /* renamed from: a, reason: collision with root package name */
    private static final int f13050a = Color.argb(255, 0, a.A, 38);

    public hao() {
        this.mRunCurveColor = d;
        this.mRunFillingColor = c;
        this.mRunLineColor = e;
        this.mRunCircleColor = f13050a;
        LogUtil.a("Track_SpeedCurveColor", "init speed curve");
    }
}
