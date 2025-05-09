package defpackage;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.utils.Utils;

/* loaded from: classes6.dex */
public class nnu extends XAxis {
    private boolean b = false;

    public nnu() {
        this.mYOffset = Utils.convertDpToPixel(0.0f);
    }

    public void b(boolean z) {
        this.b = z;
    }

    public boolean e() {
        return this.b;
    }
}
