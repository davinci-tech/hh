package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class aur extends auh {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("cfgUpgradeTime")
    private int f243a;

    @SerializedName("joinTime")
    private int b;

    @SerializedName("joinStatus")
    private int c;

    @SerializedName("challengeStatus")
    private int d;

    @SerializedName("cfgVersion")
    private int e;

    @SerializedName("kakaMakeup")
    private int f;

    public void a(int i) {
        this.f243a = i;
    }

    public void e(int i) {
        this.d = i;
    }

    public void c(int i) {
        this.c = i;
    }

    public void b(int i) {
        this.b = i;
    }

    public int e() {
        return this.f243a;
    }

    public int b() {
        return this.d;
    }

    public int c() {
        return this.b;
    }

    @Override // defpackage.auh
    public String toString() {
        return "HealthModelSyncResponse{cfgUpgradeTime=" + this.f243a + ", cfgVersion=" + this.e + ", challengeStatus=" + this.d + ", joinStatus=" + this.c + ", joinTime=" + this.b + ", kakaMakeup=" + this.f + "}";
    }
}
