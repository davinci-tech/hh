package defpackage;

import android.view.View;
import java.util.List;
import java.util.Objects;

/* loaded from: classes7.dex */
public class sch {

    /* renamed from: a, reason: collision with root package name */
    private View.OnClickListener f17018a;
    private View.OnClickListener b;
    private long c;
    private List<String> d;
    private Object e;
    private String i;

    public Object b() {
        return this.e;
    }

    public void e(Object obj) {
        this.e = obj;
    }

    public String h() {
        return this.i;
    }

    public void a(String str) {
        this.i = str;
    }

    public List<String> a() {
        return this.d;
    }

    public void c(List<String> list) {
        this.d = list;
    }

    public View.OnClickListener dVt_() {
        return this.f17018a;
    }

    public void dVv_(View.OnClickListener onClickListener) {
        this.f17018a = onClickListener;
    }

    public long d() {
        return this.c;
    }

    public void e(long j) {
        this.c = j;
    }

    public View.OnClickListener dVu_() {
        return this.b;
    }

    public void dVw_(View.OnClickListener onClickListener) {
        this.b = onClickListener;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof sch)) {
            return false;
        }
        sch schVar = (sch) obj;
        return this.c == schVar.c && Objects.equals(this.e, schVar.e) && Objects.equals(this.i, schVar.i) && Objects.equals(this.d, schVar.d);
    }

    public int hashCode() {
        return Objects.hash(this.e, this.i, this.d, Long.valueOf(this.c));
    }
}
