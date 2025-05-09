package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes6.dex */
public class mfs {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("gainTime")
    private String f14957a;

    @SerializedName(ParsedFieldTag.GAIN_COUNT)
    private int b;

    @SerializedName(ParsedFieldTag.MEDAL_ID)
    private String c;

    @SerializedName(ParsedFieldTag.MEDAL_TYPE)
    private String d;

    @SerializedName("timestamp")
    private long e;

    public String a() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public void e(int i) {
        this.b = i;
    }

    public void e(String str) {
        this.f14957a = str;
    }

    public void b(long j) {
        this.e = j;
    }

    public String d() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }
}
