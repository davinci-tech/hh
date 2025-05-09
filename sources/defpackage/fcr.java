package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class fcr implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("task_time")
    private int f12445a;

    @SerializedName("task_num")
    private int d;

    public void a(int i) {
        this.d = i;
    }

    public int a() {
        return this.d;
    }

    public void b(int i) {
        this.f12445a = i;
    }

    public int b() {
        return this.f12445a;
    }

    public String toString() {
        return "{\"DailyTaskCard\": {\"taskNum\":" + this.d + ", \"taskTime\":" + this.f12445a + "}}";
    }
}
