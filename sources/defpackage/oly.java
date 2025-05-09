package defpackage;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

/* loaded from: classes6.dex */
public class oly {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("audioInfo")
    private olw f15777a;

    @SerializedName("audioWorkoutInfo")
    private omd b;

    @SerializedName("calendarInfo")
    private omi c;
    private boolean d;
    private String e;

    @SerializedName("pictureInfo")
    private omf f;

    @SerializedName("lecturerInfo")
    private ome g;
    private String h;
    private String i;

    @SerializedName("lastPlayTime")
    private long j;

    @SerializedName("playCount")
    private long l;

    @SerializedName("status")
    private int m;

    @SerializedName("playRecord")
    private long o;

    public omd a() {
        return this.b;
    }

    public omf m() {
        return this.f;
    }

    public olw e() {
        return this.f15777a;
    }

    public ome i() {
        return this.g;
    }

    public String g() {
        ome omeVar = this.g;
        return omeVar == null ? "" : omeVar.b();
    }

    public String h() {
        ome omeVar = this.g;
        return omeVar == null ? "" : omeVar.c();
    }

    public long o() {
        return this.o;
    }

    public int l() {
        return this.m;
    }

    public omi d() {
        return this.c;
    }

    public String f() {
        return this.i;
    }

    public void b(String str) {
        this.i = str;
    }

    public String j() {
        return this.h;
    }

    public void e(String str) {
        this.h = str;
    }

    public long c() {
        return this.j;
    }

    public String b() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public boolean k() {
        return this.d;
    }

    public void c(boolean z) {
        this.d = z;
    }

    public String toString() {
        return "AudioWorkoutDetail{audioWorkoutInfo=" + this.b + ", pictureInfo=" + this.f + ", audioInfo=" + this.f15777a + ", lecturerInfo=" + this.g + ", calendarInfo=" + this.c + ", playCount=" + this.l + ", playRecord=" + this.o + ", status=" + this.m + ", lastPlayTime=" + this.j + ", name='" + this.h + "', onlineDate='" + this.i + "'}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.c, ((oly) obj).c);
    }

    public int hashCode() {
        return Objects.hash(this.c);
    }
}
