package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class fqq {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("cheerCount")
    private int f12610a;

    @SerializedName("actType")
    private String b;

    @SerializedName("goodCount")
    private int c;

    @SerializedName("actionName")
    private String d;

    @SerializedName("finishedAct")
    private int e;

    @SerializedName("theoryAct")
    private int f;

    @SerializedName("perfectCount")
    private int h;

    @SerializedName("missCount")
    private int i;

    @SerializedName("greatCount")
    private int j;

    public String c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public int i() {
        return this.h;
    }

    public int b() {
        return this.j;
    }

    public int a() {
        return this.c;
    }

    public int e() {
        return this.f12610a;
    }

    public int g() {
        return this.i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append("SingleActionSummaryBean{actionName=");
        stringBuffer.append(this.d);
        stringBuffer.append(", actType=").append(this.b);
        stringBuffer.append(", finishedAct=").append(this.e);
        stringBuffer.append(", theoryAct=").append(this.f);
        stringBuffer.append(", perfectCount=").append(this.h);
        stringBuffer.append(", greatCount=").append(this.j);
        stringBuffer.append(", goodCount=").append(this.c);
        stringBuffer.append(", cheerCount=").append(this.f12610a);
        stringBuffer.append(", missCount=").append(this.i);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
