package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class mny {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("endTime")
    private long f15073a;

    @SerializedName(ParsedFieldTag.GOAL)
    private int b;

    @SerializedName("endDate")
    private String c;

    @SerializedName("coachAdvice")
    private String d;

    @SerializedName("imageUrl")
    private String e;

    @SerializedName("lastWeekStatisticsData")
    private mnx f;
    private transient boolean g;
    private transient boolean h;

    @SerializedName("pbBeforePlan")
    private int i;

    @SerializedName("pbCurrent")
    private int j;

    @SerializedName("reportPageUrl")
    private String k;

    @SerializedName("planName")
    private String l;

    @SerializedName(Constants.START_DATE)
    private String m;

    @SerializedName("reportPageTahitiUrl")
    private String n;

    @SerializedName("planWeekDataList")
    private List<moa> o;

    @SerializedName("timeZone")
    private String p;

    @SerializedName("totalStaticsData")
    private mnz q;

    @SerializedName("targetTime")
    private int r;

    @SerializedName("startTime")
    private long s;

    @SerializedName("weekNum")
    private int t;

    public String g() {
        return this.l;
    }

    public void j(String str) {
        this.l = str;
    }

    public String c() {
        return this.d;
    }

    public void e(String str) {
        this.d = str;
    }

    public int m() {
        return this.r;
    }

    public void e(int i) {
        this.r = i;
    }

    public int i() {
        return this.i;
    }

    public void c(int i) {
        this.i = i;
    }

    public int f() {
        return this.j;
    }

    public void a(int i) {
        this.j = i;
    }

    public mnz s() {
        return this.q;
    }

    public void d(mnz mnzVar) {
        this.q = mnzVar;
    }

    public mnx h() {
        return this.f;
    }

    public void d(mnx mnxVar) {
        this.f = mnxVar;
    }

    public void d(String str) {
        this.e = str;
    }

    public String n() {
        return this.m;
    }

    public void h(String str) {
        this.m = str;
    }

    public String b() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public boolean t() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public int q() {
        return this.t;
    }

    public void b(int i) {
        this.t = i;
    }

    public String l() {
        return this.p;
    }

    public void i(String str) {
        this.p = str;
    }

    public long o() {
        return this.s;
    }

    public void b(long j) {
        this.s = j;
    }

    public long a() {
        return this.f15073a;
    }

    public void d(long j) {
        this.f15073a = j;
    }

    public String e() {
        return this.k;
    }

    public void b(String str) {
        this.k = str;
    }

    public String d() {
        return this.n;
    }

    public void c(String str) {
        this.n = str;
    }

    public List<moa> k() {
        List<moa> list = this.o;
        return list != null ? list : Collections.emptyList();
    }

    public void c(List<moa> list) {
        this.o = list;
    }

    public boolean j() {
        return this.h;
    }

    public void b(boolean z) {
        this.h = z;
    }

    public void d(int i) {
        this.b = i;
    }
}
