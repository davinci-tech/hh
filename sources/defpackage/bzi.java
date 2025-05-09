package defpackage;

import com.google.gson.annotations.SerializedName;
import defpackage.bzh;
import java.util.Comparator;

/* loaded from: classes3.dex */
public class bzi implements Comparator<bzi> {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("sleepMonitorReport")
    private bzh.c f566a;

    @SerializedName("isRead")
    private boolean e = false;

    @SerializedName("isFavourite")
    private boolean d = false;

    @SerializedName("isAutoCleared")
    private boolean c = false;

    public bzh.c a() {
        return this.f566a;
    }

    public void d(bzh.c cVar) {
        this.f566a = cVar;
    }

    public boolean c() {
        return this.d;
    }

    public boolean b() {
        return this.c;
    }

    public void c(boolean z) {
        this.c = z;
    }

    public int hashCode() {
        bzh.c cVar = this.f566a;
        if (cVar == null) {
            return 0;
        }
        return cVar.hashCode();
    }

    @Override // java.util.Comparator
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public int compare(bzi bziVar, bzi bziVar2) {
        if (bziVar.a().e() > bziVar2.a().e()) {
            return 1;
        }
        return bziVar.a().e() < bziVar2.a().e() ? -1 : 0;
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof bzi)) {
            return false;
        }
        bzi bziVar = (bzi) obj;
        if (this.f566a == null && bziVar.a() == null) {
            return true;
        }
        if (this.f566a == null && bziVar.a() != null) {
            return false;
        }
        if (this.f566a == null || bziVar.a() != null) {
            return this.f566a.equals(bziVar.a());
        }
        return false;
    }

    public String toString() {
        return "SleepVoiceInfoWrapper{sleepMonitorReport=" + this.f566a + ", isRead=" + this.e + ", isFavourite=" + this.d + ", isAutoCleared=" + this.c + '}';
    }
}
