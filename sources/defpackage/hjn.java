package defpackage;

import com.huawei.hwfoundationmodel.trackmodel.MotionPath;

/* loaded from: classes8.dex */
public class hjn {
    private static final Object b = new Object();
    private static hjn c;

    /* renamed from: a, reason: collision with root package name */
    private MotionPath f13188a;

    public static hjn c() {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new hjn();
                }
            }
        }
        return c;
    }

    public void b() {
        this.f13188a = null;
    }
}
