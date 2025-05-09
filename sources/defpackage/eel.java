package defpackage;

import java.util.Objects;

/* loaded from: classes3.dex */
public class eel {

    /* renamed from: a, reason: collision with root package name */
    private long f11987a;
    private int c;
    private long e;

    public eel() {
        this.c = -1;
        this.e = -1L;
    }

    public eel(long j, int i) {
        this.e = -1L;
        this.f11987a = j;
        this.c = i;
    }

    public eel(long j, long j2) {
        this.c = -1;
        this.f11987a = j;
        this.e = j2;
    }

    public int e() {
        return this.c;
    }

    public long d() {
        return this.e;
    }

    public long a() {
        return this.f11987a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        eel eelVar = (eel) obj;
        return this.f11987a == eelVar.f11987a && this.c == eelVar.c && this.e == eelVar.e;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.f11987a), Integer.valueOf(this.c), Long.valueOf(this.e));
    }
}
