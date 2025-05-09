package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class fcq {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("taskTime")
    private int f12444a;

    @SerializedName("taskNum")
    private int b;

    public int b() {
        return this.b;
    }

    public int d() {
        return this.f12444a;
    }

    public String toString() {
        return "{\"DailyTaskCard\": {\"taskNum\":" + this.b + ", \"taskTime\":" + this.f12444a + "}}";
    }
}
