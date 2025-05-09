package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import java.util.List;

/* loaded from: classes3.dex */
public class fdb {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("acti_dur")
    private List<Integer> f12453a;

    @SerializedName("sleep_latency")
    private int aa;

    @SerializedName("sleep_osa")
    private int ab;

    @SerializedName("sleep_avgrr")
    private int ac;

    @SerializedName("sleep_avgspo")
    private int ad;

    @SerializedName("wake_duration")
    private int ae;

    @SerializedName("wake_times")
    private int af;

    @SerializedName("total_duration")
    private int ah;

    @SerializedName("avg_pressure")
    private int b;

    @SerializedName("awake_time")
    private long c;

    @SerializedName("asleep_time")
    private long d;

    @SerializedName("acti_ts")
    private List<Long> e;

    @SerializedName("data_src")
    private int f;

    @SerializedName("deep_continuity")
    private int g;

    @SerializedName("cur_time")
    private long h;

    @SerializedName("bed_duration")
    private int i;

    @SerializedName("deep_duration")
    private int j;

    @SerializedName("max_hr")
    private int k;

    @SerializedName("light_duration")
    private int l;

    @SerializedName("hr_ts")
    private List<Long> m;

    @SerializedName("hr_list")
    private List<Integer> n;

    @SerializedName("nap_dur")
    private List<Integer> o;

    @SerializedName("nap_ts")
    private List<Long> p;

    @SerializedName("on_bed_time")
    private long q;

    @SerializedName("rdi")
    private int r;

    @SerializedName("noise")
    private int s;

    @SerializedName("off_bed_time")
    private long t;

    @SerializedName("rem_duration")
    private int u;

    @SerializedName("sleep_avghrv")
    private int v;

    @SerializedName(HwHealthChartHolder.LAYER_ID_REST_HR)
    private int w;

    @SerializedName("sleep_avghr")
    private int x;

    @SerializedName(JsUtil.SCORE)
    private int y;

    @SerializedName("sleep_efficiency")
    private int z;

    public void b(long j) {
        this.h = j;
    }

    public void k(int i) {
        this.y = i;
    }

    public void a(long j) {
        this.d = j;
    }

    public long c() {
        return this.d;
    }

    public void d(long j) {
        this.c = j;
    }

    public long d() {
        return this.c;
    }

    public void e(long j) {
        this.q = j;
    }

    public long a() {
        return this.q;
    }

    public void c(long j) {
        this.t = j;
    }

    public void e(int i) {
        this.j = i;
    }

    public void i(int i) {
        this.l = i;
    }

    public void f(int i) {
        this.u = i;
    }

    public void r(int i) {
        this.ah = i;
    }

    public int i() {
        return this.ah;
    }

    public void x(int i) {
        this.af = i;
    }

    public void v(int i) {
        this.ae = i;
    }

    public void c(int i) {
        this.g = i;
    }

    public void g(int i) {
        this.r = i;
    }

    public void o(int i) {
        this.w = i;
    }

    public void j(int i) {
        this.k = i;
    }

    public void d(int i) {
        this.b = i;
    }

    public void h(int i) {
        this.s = i;
    }

    public void t(int i) {
        this.z = i;
    }

    public void p(int i) {
        this.aa = i;
    }

    public void f(List<Long> list) {
        this.p = list;
    }

    public void c(List<Integer> list) {
        this.o = list;
    }

    public void b(List<Long> list) {
        this.m = list;
    }

    public void a(List<Integer> list) {
        this.n = list;
    }

    public void e(List<Long> list) {
        this.e = list;
    }

    public void d(List<Integer> list) {
        this.f12453a = list;
    }

    public int b() {
        return this.i;
    }

    public void b(int i) {
        this.i = i;
    }

    public void a(int i) {
        this.f = i;
    }

    public int e() {
        return this.f;
    }

    public void q(int i) {
        this.ab = i;
    }

    public void n(int i) {
        this.x = i;
    }

    public void m(int i) {
        this.ac = i;
    }

    public void l(int i) {
        this.v = i;
    }

    public void s(int i) {
        this.ad = i;
    }

    public String toString() {
        return "SleepInfo{dataSrc=" + this.f + ", curTime=" + this.h + ", score=" + this.y + ", asleepTime=" + this.d + ", awakeTime=" + this.c + ", onBedTime=" + this.q + ", offBedTime=" + this.t + ", deepDuration=" + this.j + ", lightDuration=" + this.l + ", remDuration=" + this.u + ", totalDuration=" + this.ah + ", bedDuration=" + this.i + ", wakeTimes=" + this.af + ", wakeDuration=" + this.ae + ", deepContinuity=" + this.g + ", rdi=" + this.r + ", restHr=" + this.w + ", maxHr=" + this.k + ", avgPressure=" + this.b + ", noise=" + this.s + ", sleepEfficiency=" + this.z + ", sleepLatency=" + this.aa + ", sleepOsa=" + this.ab + ", sleepAvghr=" + this.x + ", sleepAvghrv=" + this.v + ", sleepAvgrr=" + this.ac + ", sleepAvgspo=" + this.ad + ", napTs=" + this.p + ", napDur=" + this.o + ", hrTs=" + this.m + ", hrList=" + this.n + ", actiTs=" + this.e + ", actiDur=" + this.f12453a + '}';
    }
}
