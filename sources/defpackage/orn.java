package defpackage;

import android.content.Context;
import com.huawei.ui.homehealth.runcard.trackfragments.models.SportNounChildData;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class orn {

    /* renamed from: a, reason: collision with root package name */
    private boolean f15920a = false;
    private String b = "";
    private List<oro> c = new ArrayList(10);
    private SportNounChildData d;

    public orn(String str, int i, Context context) {
        this.d = new SportNounChildData(context);
        e(str, i);
    }

    private void e(String str, int i) {
        d(str);
        switch (i) {
            case 0:
                this.c = this.d.a();
                break;
            case 1:
                this.c = this.d.j();
                break;
            case 2:
                this.c = this.d.g();
                break;
            case 3:
                this.c = this.d.l();
                break;
            case 4:
                this.c = this.d.i();
                break;
            case 5:
                this.c = this.d.d();
                break;
            case 6:
                this.c = this.d.h();
                break;
            case 7:
                this.c = this.d.c();
                break;
        }
    }

    public int e() {
        List<oro> list = this.c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public oro e(int i) {
        if (koq.b(this.c, i)) {
            return null;
        }
        return this.c.get(i);
    }

    public void b(boolean z) {
        this.f15920a = z;
    }

    public String d() {
        return this.b;
    }

    public void d(String str) {
        this.b = str;
    }
}
