package defpackage;

import java.util.Arrays;
import java.util.Objects;

/* loaded from: classes3.dex */
public class eef {

    /* renamed from: a, reason: collision with root package name */
    private eel[] f11984a;
    private int e;

    public int d() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public eel[] c() {
        return this.f11984a;
    }

    public void d(eel[] eelVarArr) {
        this.f11984a = eelVarArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        eef eefVar = (eef) obj;
        return this.e == eefVar.e && Arrays.equals(this.f11984a, eefVar.f11984a);
    }

    public int hashCode() {
        return (Objects.hash(Integer.valueOf(this.e)) * 31) + Arrays.hashCode(this.f11984a);
    }
}
