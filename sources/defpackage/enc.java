package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.operation.ble.BleConstants;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes3.dex */
public class enc implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("coordinate")
    private String f12104a;

    @SerializedName("timeRanking")
    private List<eng> ab;

    @SerializedName("validEndDay")
    private int ad;

    @SerializedName("cityId")
    private String b;

    @SerializedName("description")
    private String c;

    @SerializedName("distanceTypeId")
    private int d;

    @SerializedName("cpPoint")
    private emf e;

    @SerializedName("participateNum")
    private long f;

    @SerializedName("numsRanking")
    private List<eng> g;

    @SerializedName("flag")
    private int h;

    @SerializedName("num")
    private int i;

    @SerializedName("minTime")
    private long j;

    @SerializedName("pathBehaviorwait")
    private ena k;

    @SerializedName("participateUserNum")
    private long l;

    @SerializedName("pathFlag")
    private int m;

    @SerializedName("pathClass")
    private int n;

    @SerializedName("pathComment")
    private String o;

    @SerializedName("pathName")
    private String p;

    @SerializedName("pathLabel")
    private List<String> q;

    @SerializedName("pathLocation")
    private GpsPoint r;

    @SerializedName("pathId")
    private String s;

    @SerializedName("pathImageInfo")
    private enm t;

    @SerializedName("pathThemes")
    private List<Integer> u;

    @SerializedName("pathType")
    private int v;

    @SerializedName("state")
    private int w;

    @SerializedName("pathTypes")
    private List<Integer> x;

    @SerializedName("pathPoints")
    private List<GpsPoint> y;

    @SerializedName(BleConstants.TOTAL_DISTANCE)
    private double z;

    public String h() {
        return this.s;
    }

    public String b() {
        return this.b;
    }

    public List<GpsPoint> k() {
        return this.y;
    }

    public int q() {
        return this.v;
    }

    public List<Integer> s() {
        return this.x;
    }

    public String n() {
        return this.p;
    }

    public enm m() {
        return this.t;
    }

    public long f() {
        return this.l;
    }

    public long j() {
        return this.f;
    }

    public double r() {
        return this.z;
    }

    public GpsPoint o() {
        return this.r;
    }

    public long c() {
        return this.j;
    }

    public List<eng> i() {
        return this.g;
    }

    public List<eng> t() {
        return this.ab;
    }

    public String d() {
        return this.c;
    }

    public int p() {
        return this.w;
    }

    public emf a() {
        return this.e;
    }

    public String e() {
        return this.f12104a;
    }

    public List<Integer> l() {
        return this.u;
    }

    public int g() {
        return this.n;
    }

    public String toString() {
        return "HotPathDetailInfo{pathId='" + this.s + "', pathPoints=" + this.y + ", pathFlag=" + this.m + ", pathType=" + this.v + ", pathTypes=" + this.x + ", pathClass=" + this.n + ", pathThemes=" + this.u + ", pathName='" + this.p + "', pathLabel=" + this.q + ", pathImageInfo=" + this.t + ", participateUserNum=" + this.l + ", participateNum=" + this.f + ", validEndDay=" + this.ad + ", totalDistance=" + this.z + ", pathLocation=" + this.r + ", num=" + this.i + ", minTime=" + this.j + ", numsRanking=" + this.g + ", timeRanking=" + this.ab + ", description='" + this.c + "', flag=" + this.h + ", state=" + this.w + ", cpPoint=" + this.e + ", pathComment=" + this.o + ", coordinate=" + this.f12104a + ", pathBehavior=" + this.k + '}';
    }
}
