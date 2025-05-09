package com.huawei.openalliance.ad.beans.metadata;

import java.util.List;

/* loaded from: classes5.dex */
public class DelayInfo {
    private static final int AD_LOAD_TIMEOUT_AFTER_RESPONSE = 20;
    private static final int AD_LOAD_TIMEOUT_BEFORE_RESPONSE = 10;
    private int adAmount;
    private long adFilterDuration;
    private List<String> adIds;
    private String adSign;
    private String contentDownMethod;
    private List<String> contentIds;
    private String costFromServer;
    private Integer creativeType;
    private Integer detailedRetCode;
    private int exSplashFlag;
    private long hmsRecEngineCostTime;
    private String preloadMode;
    private long recEngineCostTime;
    private Integer requestType;
    private long resDownloadDuration;
    private int resultCode;
    private int serverRetCode;
    private String spareContentId;
    private long splashContentLoadedCost;
    private String splashShowMode;
    private long threadSwitchCost;
    private long uiThreadSwithCostTime;
    private boolean isSpare = false;
    private AdTimeStatistics timeStatistics = new AdTimeStatistics();
    private boolean spareExist = false;

    private long a(long j, long j2) {
        if (j <= 0 || j >= j2) {
            return 0L;
        }
        return j2 - j;
    }

    public String z() {
        return this.spareContentId;
    }

    public String y() {
        return this.adSign;
    }

    public String x() {
        return this.costFromServer;
    }

    public Integer w() {
        return this.detailedRetCode;
    }

    public Integer v() {
        return this.creativeType;
    }

    public AdTimeStatistics u() {
        return this.timeStatistics;
    }

    public boolean t() {
        return this.isSpare;
    }

    public int s() {
        long b = this.timeStatistics.b();
        long e = this.timeStatistics.e();
        if (b == 0 || e == 0) {
            return 0;
        }
        return b <= e ? 10 : 20;
    }

    public int r() {
        return this.serverRetCode;
    }

    public int q() {
        return this.resultCode;
    }

    public int p() {
        return this.exSplashFlag;
    }

    public long o() {
        return this.splashContentLoadedCost;
    }

    public long n() {
        return a(this.timeStatistics.a(), this.timeStatistics.i());
    }

    public long m() {
        return this.resDownloadDuration;
    }

    public String l() {
        return this.contentDownMethod;
    }

    public String k() {
        return this.splashShowMode;
    }

    public long j() {
        return a(this.timeStatistics.a(), this.timeStatistics.h());
    }

    public void i(long j) {
        this.hmsRecEngineCostTime = j;
    }

    public int i() {
        return this.adAmount;
    }

    public void h(long j) {
        this.recEngineCostTime = j;
    }

    public List<String> h() {
        return this.contentIds;
    }

    public void g(long j) {
        this.uiThreadSwithCostTime = j;
    }

    public List<String> g() {
        return this.adIds;
    }

    public void f(String str) {
        this.preloadMode = str;
    }

    public void f(long j) {
        this.splashContentLoadedCost = a(this.timeStatistics.a(), j);
    }

    public long f() {
        return a(this.timeStatistics.f(), this.timeStatistics.g());
    }

    public void e(String str) {
        this.spareContentId = str;
    }

    public void e(long j) {
        this.resDownloadDuration = j;
    }

    public long e() {
        return a(this.timeStatistics.a(), this.timeStatistics.d());
    }

    public void d(String str) {
        this.adSign = str;
    }

    public void d(long j) {
        this.threadSwitchCost = j;
    }

    public long d() {
        return this.threadSwitchCost;
    }

    public void c(String str) {
        this.costFromServer = str;
    }

    public void c(Integer num) {
        this.requestType = num;
    }

    public void c(long j) {
        this.adFilterDuration = j;
    }

    public long c() {
        return this.adFilterDuration;
    }

    public void b(boolean z) {
        this.spareExist = z;
    }

    public void b(List<String> list) {
        this.contentIds = list;
    }

    public void b(String str) {
        this.contentDownMethod = str;
    }

    public void b(Integer num) {
        this.detailedRetCode = num;
    }

    public void b(long j) {
        this.timeStatistics.b(j);
    }

    public void b(int i) {
        this.serverRetCode = i;
    }

    public long b() {
        return a(this.timeStatistics.d(), this.timeStatistics.e());
    }

    public void a(boolean z) {
        this.isSpare = z;
    }

    public void a(List<String> list) {
        this.adIds = list;
    }

    public void a(String str) {
        this.splashShowMode = str;
    }

    public void a(Integer num) {
        this.creativeType = num;
    }

    public void a(long j) {
        this.timeStatistics.a(j);
    }

    public void a(int i) {
        this.resultCode = i;
    }

    public long a() {
        return a(this.timeStatistics.a(), this.timeStatistics.b());
    }

    public Integer F() {
        return this.requestType;
    }

    public long E() {
        return this.hmsRecEngineCostTime;
    }

    public long D() {
        return this.recEngineCostTime;
    }

    public String C() {
        return this.preloadMode;
    }

    public long B() {
        return this.uiThreadSwithCostTime;
    }

    public boolean A() {
        return this.spareExist;
    }
}
