package defpackage;

import com.huawei.health.marketing.views.IViewVisibilityChange;

/* loaded from: classes4.dex */
public class fuo {
    private IViewVisibilityChange b;
    private boolean d;

    public boolean c() {
        return this.d;
    }

    public void c(boolean z) {
        this.d = z;
        IViewVisibilityChange iViewVisibilityChange = this.b;
        if (iViewVisibilityChange != null) {
            iViewVisibilityChange.onVisibilityChange(z);
        }
    }

    public void b(IViewVisibilityChange iViewVisibilityChange) {
        this.b = iViewVisibilityChange;
    }
}
