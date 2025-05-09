package defpackage;

import android.util.Log;
import com.huawei.uikit.hwcolumnsystem.widget.akxao;

/* loaded from: classes7.dex */
public class sky extends akxao {
    private static final String o = "avpbg";
    private int m;
    private int n;
    private int p;

    @Override // com.huawei.uikit.hwcolumnsystem.widget.akxao
    public final float a(int i) {
        return (i * this.j) + ((i - 1) * this.f);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.akxao
    public final int b() {
        return this.m;
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.akxao
    public int c() {
        return this.n;
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.akxao
    public int d() {
        return this.m;
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.akxao
    public void e() {
        int i = this.i;
        if (i == 0) {
            Log.e(o, "total column is 0");
            return;
        }
        this.j = ((this.f10621a - (this.e * 2)) - (this.f * (i - 1))) / (i * 1.0f);
        int a2 = (int) (a(i) + 0.5f);
        this.p = a2;
        int i2 = this.g;
        if (i2 == -2) {
            this.m = a2 + (this.e * 2);
        } else {
            this.m = (int) (a(i2) + d(true) + 0.5f);
        }
        int i3 = this.h;
        if (i3 == -2) {
            this.n = this.p + (this.e * 2);
        } else {
            this.n = (int) (a(i3) + d(false) + 0.5f);
        }
    }

    private int d(boolean z) {
        int i;
        if (!this.l) {
            return 0;
        }
        int i2 = this.k;
        if (i2 == 1 || i2 == 17) {
            if (this.i != 4 || !z) {
                return 0;
            }
            i = this.f;
        } else {
            if (i2 != 19) {
                return 0;
            }
            int i3 = this.i;
            if (i3 != 8 && i3 != 12) {
                return 0;
            }
            i = this.f;
        }
        return i * 2;
    }
}
