package defpackage;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class fcn implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("task_code")
    private String f12442a;

    @SerializedName("task_type")
    private int b;

    @SerializedName("task_id")
    private int c;

    @SerializedName("daily_task_card")
    private fcr e;

    public void c(int i) {
        this.c = i;
    }

    public int e() {
        return this.c;
    }

    public void b(int i) {
        this.b = i;
    }

    public int a() {
        return this.b;
    }

    public void e(String str) {
        this.f12442a = str;
    }

    public String c() {
        return this.f12442a;
    }

    public void c(fcr fcrVar) {
        this.e = fcrVar;
    }

    public fcr d() {
        return this.e;
    }

    public String toString() {
        return "{\"DailyTask\": {\"taskId\":" + this.c + ", \"taskType\":" + this.b + ", \"taskCode\": \"" + this.f12442a + "\", \"dailyTaskCard\":" + this.e + "}}";
    }
}
