package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class olw {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("audioDuration")
    private long f15775a;

    @SerializedName("audioUrl")
    private String b;

    @SerializedName("audioId")
    private String e;

    public String c() {
        return this.e;
    }

    public String e() {
        return this.b;
    }

    public long d() {
        return this.f15775a;
    }

    public String toString() {
        return "AudioDaoBean{audioId='" + this.e + "', audioUrl='" + this.b + "', audioDuration=" + this.f15775a + '}';
    }
}
