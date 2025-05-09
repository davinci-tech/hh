package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes3.dex */
public class enf implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("description")
    private String f12107a;

    @SerializedName("cityId")
    private String b;

    @SerializedName("distanceTypeId")
    private int c;

    @SerializedName("createTime")
    private long d;

    @SerializedName("distanceToUser")
    private double e;

    @SerializedName(ParsedFieldTag.TASK_MODIFY_TIME)
    private long f;

    @SerializedName("feedbackNum")
    private long g;

    @SerializedName("participateNum")
    private long h;

    @SerializedName("geoHash")
    private String i;

    @SerializedName("flag")
    private int j;

    @SerializedName("pathId")
    private String k;

    @SerializedName("pathFlag")
    private int l;

    @SerializedName("participateUserNum")
    private long m;

    @SerializedName("pathImageInfo")
    private enm n;

    @SerializedName("pathClass")
    private int o;

    @SerializedName("pathLocation")
    private GpsPoint p;

    @SerializedName("pathName")
    private String q;

    @SerializedName("pathThemes")
    private List<Integer> r;

    @SerializedName("pathType")
    private int s;

    @SerializedName("pathLabel")
    private List<String> t;

    @SerializedName("pathTypes")
    private List<Integer> v;

    @SerializedName(BleConstants.TOTAL_DISTANCE)
    private double w;

    @SerializedName("validEndDay")
    private int x;

    @SerializedName("state")
    private int y;

    public String i() {
        return this.k;
    }

    public List<Integer> k() {
        return this.r;
    }

    public int b() {
        return this.o;
    }

    public int a() {
        return this.c;
    }

    public List<Integer> l() {
        return this.v;
    }

    public String h() {
        return this.q;
    }

    public List<String> j() {
        return this.t;
    }

    public enm f() {
        return this.n;
    }

    public long c() {
        return this.m;
    }

    public double m() {
        return this.w;
    }

    public GpsPoint g() {
        return this.p;
    }

    public double e() {
        return this.e;
    }

    public void b(double d) {
        this.e = d;
    }

    public long d() {
        return this.h;
    }

    public int o() {
        return this.y;
    }

    public String toString() {
        return "HotPathOperationInfo{pathId='" + this.k + "', cityId='" + this.b + "', geoHash='" + this.i + "', pathFlag=" + this.l + ", pathClass=" + this.o + ", pathThemes=" + this.r + ", pathType=" + this.s + ", distanceTypeId=" + this.c + ", pathTypes=" + this.v + ", pathName='" + this.q + "', pathLabel=" + this.t + ", pathImageInfo=" + this.n + ", participateUserNum=" + this.m + ", totalDistance=" + this.w + ", pathLocation=" + this.p + ", validEndDay=" + this.x + ", feedbackNum=" + this.g + ", distanceToUser=" + this.e + ", description='" + this.f12107a + "', flag=" + this.j + ", createTime=" + this.d + ", modifyTime=" + this.f + ", participateNum=" + this.h + ", state=" + this.y + '}';
    }
}
