package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.List;

/* loaded from: classes3.dex */
public class buo {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("completeValue")
    private double f514a;

    @SerializedName("frequency")
    private int b;

    @SerializedName("endTime")
    private long c;

    @SerializedName("createTime")
    private long d;

    @SerializedName("currentWeight")
    private double e;

    @SerializedName("id")
    private long f;

    @SerializedName("recordDays")
    private List<Long> g;

    @SerializedName("goalType")
    private int h;

    @SerializedName(ParsedFieldTag.TASK_MODIFY_TIME)
    private long i;

    @SerializedName("goalValue")
    private double j;

    @SerializedName("startTime")
    private long k;

    @SerializedName("status")
    private int l;

    @SerializedName("unitValue")
    private double m;

    public long i() {
        return this.f;
    }

    public int b() {
        return this.h;
    }

    public double d() {
        return this.j;
    }

    public void d(double d) {
        this.f514a = d;
    }

    public double e() {
        return this.f514a;
    }

    public void c(List<Long> list) {
        this.g = list;
    }

    public List<Long> h() {
        return this.g;
    }

    public double c() {
        return this.e;
    }

    public long f() {
        return this.k;
    }

    public long a() {
        return this.c;
    }

    public int g() {
        return this.l;
    }

    public void b(int i) {
        this.l = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GetActiveTargetsRsp{id=");
        stringBuffer.append(this.f);
        stringBuffer.append(", goalType=").append(this.h);
        stringBuffer.append(", goalValue=").append(this.j);
        stringBuffer.append(", completeValue=").append(this.f514a);
        stringBuffer.append(", recordDays=").append(this.g);
        stringBuffer.append(", currentWeight=").append(this.e);
        stringBuffer.append(", frequency=").append(this.b);
        stringBuffer.append(", unitValue=").append(this.m);
        stringBuffer.append(", startTime=").append(this.k);
        stringBuffer.append(", endTime=").append(this.c);
        stringBuffer.append(", createTime=").append(this.d);
        stringBuffer.append(", modifyTime=").append(this.i);
        stringBuffer.append(", status=").append(this.l);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
