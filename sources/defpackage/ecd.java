package defpackage;

import android.view.View;
import java.util.List;

/* loaded from: classes3.dex */
public class ecd {
    private List<String> b;
    private int d;
    private List<View.OnClickListener> e;

    public ecd(List<String> list, List<View.OnClickListener> list2, int i) {
        this.b = list;
        this.e = list2;
        this.d = i;
    }

    public List<String> c() {
        return this.b;
    }

    public List<View.OnClickListener> b() {
        return this.e;
    }

    public int e() {
        return this.d;
    }
}
