package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes6.dex */
public class owc {

    /* renamed from: a, reason: collision with root package name */
    private int f15982a;
    private View.OnClickListener b;
    private CharSequence c;
    private int d;
    private Drawable e;
    private CharSequence g;
    private Drawable h;
    private String i;
    private Map<String, Object> j;

    public void a(owc owcVar) {
        if (owcVar == null) {
            return;
        }
        this.d = owcVar.a();
        this.f15982a = owcVar.d();
        this.e = owcVar.diE_();
        this.c = owcVar.c();
        this.g = owcVar.i();
        this.h = owcVar.diF_();
        this.b = owcVar.diD_();
        this.i = owcVar.f();
        this.j = owcVar.j();
    }

    public int a() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }

    public int d() {
        return this.f15982a;
    }

    public void d(int i) {
        this.f15982a = i;
    }

    public Drawable diE_() {
        return this.e;
    }

    public void diH_(Drawable drawable) {
        this.e = drawable;
    }

    public CharSequence c() {
        return this.c;
    }

    public void d(CharSequence charSequence) {
        this.c = charSequence;
    }

    public CharSequence i() {
        return this.g;
    }

    public void a(CharSequence charSequence) {
        this.g = charSequence;
    }

    public Drawable diF_() {
        return this.h;
    }

    public void diI_(Drawable drawable) {
        this.h = drawable;
    }

    public View.OnClickListener diD_() {
        return this.b;
    }

    public void diG_(View.OnClickListener onClickListener) {
        this.b = onClickListener;
    }

    public String f() {
        return this.i;
    }

    public void a(String str) {
        this.i = str;
    }

    public Map<String, Object> j() {
        return this.j;
    }

    public void c(Map<String, Object> map) {
        this.j = map;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof owc)) {
            return false;
        }
        owc owcVar = (owc) obj;
        return a() == owcVar.a() && d() == owcVar.d() && Objects.equals(diE_(), owcVar.diE_()) && d(c(), owcVar.c()) && d(i(), owcVar.i()) && Objects.equals(diF_(), owcVar.diF_()) && Objects.equals(diD_(), owcVar.diD_()) && Objects.equals(f(), owcVar.f()) && Objects.equals(j(), owcVar.j());
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(a()), Integer.valueOf(d()), diE_(), c(), i(), diF_(), diD_(), f(), j());
    }

    private boolean d(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == charSequence2) {
            return true;
        }
        return (charSequence == null || charSequence2 == null || !charSequence.toString().equals(charSequence2.toString())) ? false : true;
    }
}
