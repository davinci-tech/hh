package defpackage;

import com.huawei.hwfoundationmodel.trackmodel.MotionPath;

/* loaded from: classes4.dex */
public class gya extends gxy {
    private static final long serialVersionUID = 4241032565572435995L;
    private MotionPath d;

    public gya(gxy gxyVar, MotionPath motionPath) {
        if (gxyVar != null) {
            d(gxyVar.a());
            e(gxyVar.b());
            c(gxyVar.d());
            d(motionPath);
        }
    }

    public MotionPath c() {
        return this.d;
    }

    private void d(MotionPath motionPath) {
        this.d = motionPath;
    }
}
