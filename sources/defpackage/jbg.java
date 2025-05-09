package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.utils.Constants;
import java.util.List;

/* loaded from: classes5.dex */
public class jbg {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("contentTag")
    private String f13710a;

    @SerializedName("fromVersion")
    private String b;

    @SerializedName(Constants.VMALL_ARG_TYPE)
    private Integer c;

    @SerializedName("branchId")
    private int d;

    @SerializedName("country")
    private String e;

    @SerializedName("greyKeyWordList")
    private List<String> f;

    @SerializedName("language")
    private String g;

    @SerializedName("latestVersion")
    private long h;

    @SerializedName("isAgree")
    private boolean i;

    @SerializedName("needSign")
    private boolean j;

    @SerializedName("signTime")
    private long l;

    @SerializedName("newestVersion")
    private long m;

    @SerializedName("version")
    private long o;

    public Integer e() {
        return this.c;
    }

    public long b() {
        return this.o;
    }

    public long c() {
        return this.m;
    }

    public boolean d() {
        return this.j;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HonorPrivacy{agrType=");
        stringBuffer.append(this.c);
        stringBuffer.append(", country=").append(this.e);
        stringBuffer.append(", branchId=").append(this.d);
        stringBuffer.append(", fromVersion=").append(this.b);
        stringBuffer.append(", greyKeyWordList=").append(this.f);
        stringBuffer.append(", language=").append(this.g);
        stringBuffer.append(", isAgree=").append(this.i);
        stringBuffer.append(", contentTag=").append(this.f13710a);
        stringBuffer.append(", version=").append(this.o);
        stringBuffer.append(", signTime=").append(this.l);
        stringBuffer.append(", latestVersion=").append(this.h);
        stringBuffer.append(", newestVersion=").append(this.m);
        stringBuffer.append(", needSign=").append(this.j);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
