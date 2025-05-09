package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class fde {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("task_prob")
    private String f12456a;

    @SerializedName("task_id")
    private int b;

    @SerializedName("task_type")
    private int c;

    @SerializedName("task_code")
    private String d;

    @SerializedName("daily_task_card")
    private fcr e;

    public void c(int i) {
        this.b = i;
    }

    public void b(int i) {
        this.c = i;
    }

    public void b(String str) {
        this.d = str;
    }

    public void b(fcr fcrVar) {
        this.e = fcrVar;
    }
}
