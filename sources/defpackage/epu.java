package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class epu {

    @SerializedName("remindTime")
    private int c;

    @SerializedName("startTime")
    private long d;

    @SerializedName("planTempId")
    private String e;

    public String a() {
        return this.e;
    }

    public long b() {
        return this.d;
    }

    public int c() {
        return this.c;
    }
}
