package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class fqc {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("coordinatingScore")
    private int f12606a;

    @SerializedName("beautyScore")
    private int b;

    @SerializedName("balanceScore")
    private int c;

    @SerializedName("softScore")
    private int d;

    @SerializedName("completionScore")
    private int e;

    @SerializedName("trickScore")
    private int i;

    public int c() {
        return this.c;
    }

    public int a() {
        return this.f12606a;
    }

    public int h() {
        return this.i;
    }

    public int d() {
        return this.d;
    }

    public int b() {
        return this.b;
    }

    public int e() {
        return this.e;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append("CompletedActionScoreBean{balanceScore=");
        stringBuffer.append(this.c);
        stringBuffer.append(", coordinatingScore=").append(this.f12606a);
        stringBuffer.append(", trickScore=").append(this.i);
        stringBuffer.append(", softScore=").append(this.d);
        stringBuffer.append(", beautyScore=").append(this.b);
        stringBuffer.append(", completionScore=").append(this.e);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
