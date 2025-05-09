package defpackage;

import android.app.Activity;
import huawei.android.widget.HwImmersiveMode;

/* loaded from: classes7.dex */
public class slr {

    /* renamed from: a, reason: collision with root package name */
    private HwImmersiveMode f17110a;
    private Activity b;

    public slr(Activity activity) {
        this.f17110a = new HwImmersiveMode(activity);
        this.b = activity;
    }

    public void c(boolean z) {
        this.f17110a.setNavigationBarBlurEnable(z);
    }
}
