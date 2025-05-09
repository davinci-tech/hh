package defpackage;

import androidx.core.view.GravityCompat;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class dhd implements Serializable {
    private static final long serialVersionUID = -479718036212547170L;

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<String> f11656a;
    private ArrayList<Object> b;
    private int c = GravityCompat.START;
    private ArrayList<String> d;
    private CharSequence e;
    private boolean i;
    private boolean j;

    public ArrayList<String> e() {
        return this.d;
    }

    public void a(ArrayList<String> arrayList) {
        this.d = arrayList;
    }

    public ArrayList<Object> a() {
        return this.b;
    }

    public ArrayList<String> b() {
        return this.f11656a;
    }

    public void e(ArrayList<Object> arrayList) {
        this.b = arrayList;
    }

    public void b(ArrayList<String> arrayList) {
        this.f11656a = arrayList;
    }

    public CharSequence c() {
        return this.e;
    }

    public void c(CharSequence charSequence) {
        this.e = charSequence;
    }

    public void b(int i) {
        this.c = i;
    }

    public boolean d() {
        return this.j;
    }

    public void e(boolean z) {
        this.j = z;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public String toString() {
        return "DeviceMeasureOperateModel [measureModelImgList=" + this.b + ", measureModelGuidePrompt=" + ((Object) this.e) + ", measureModelGuideGravity=" + this.c + ", measureModelShowButton=" + this.j + ", measureModelShowReselcet=" + this.i + "]";
    }
}
