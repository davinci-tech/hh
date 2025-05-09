package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes8.dex */
public class aya {

    @SerializedName("challengeId")
    private int c;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("healthGoalName")
    private String f279a = "";

    @SerializedName("healthGoalDescription")
    private String e = "";

    @SerializedName("healthGoalSelected")
    private int b = 0;

    public int a() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public String e() {
        return this.f279a;
    }

    public void a(String str) {
        this.f279a = str;
    }

    public String b() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public int c() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }
}
