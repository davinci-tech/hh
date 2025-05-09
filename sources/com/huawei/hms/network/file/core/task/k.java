package com.huawei.hms.network.file.core.task;

import com.huawei.hms.network.file.api.Request;
import com.huawei.hms.network.file.core.task.e;
import com.huawei.hms.network.file.core.util.FLogger;
import com.huawei.hms.network.file.core.util.Utils;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.Submit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public abstract class k<R extends Request> implements e<R> {

    /* renamed from: a, reason: collision with root package name */
    private R f5636a;
    private long b;
    private volatile String c;
    volatile ITaskResult d;
    private volatile int e;
    private String f;
    private String g;
    private String h;
    private String i;
    private int j;
    private RequestFinishedInfo k;
    volatile boolean l;
    volatile boolean m;
    volatile Submit n;
    private int o;
    private boolean p;
    public long q;
    public volatile long r;
    volatile boolean s;
    public int t;
    private Future<?> u;

    public void a(RequestFinishedInfo.MetricsTime metricsTime) {
    }

    public abstract com.huawei.hms.network.file.a.g m();

    public abstract k x();

    public boolean w() {
        return this.p;
    }

    public String v() {
        return this.c;
    }

    public ITaskResult u() {
        return this.d;
    }

    public String toString() {
        return "Task{id = '" + this.b + "', totalSize = " + a() + ", finishedSize = " + this.r + ", isCanceled = " + this.s + '}';
    }

    public int t() {
        return this.t;
    }

    public String s() {
        if (!this.l) {
            a(this.k);
        }
        return this.f;
    }

    public RequestFinishedInfo r() {
        return this.k;
    }

    public long q() {
        return this.q;
    }

    public R p() {
        return this.f5636a;
    }

    public String o() {
        if (!this.l) {
            a(this.k);
        }
        return this.g;
    }

    public String n() {
        if (!this.l) {
            a(this.k);
        }
        return this.h;
    }

    public int l() {
        if (!this.l) {
            a(this.k);
        }
        return this.j;
    }

    public int k() {
        return this.o;
    }

    public int j() {
        return this.e;
    }

    public String i() {
        if (!this.l) {
            a(this.k);
        }
        return this.i;
    }

    @Override // com.huawei.hms.network.file.core.task.e
    public boolean h() {
        return this.s;
    }

    @Override // com.huawei.hms.network.file.core.task.e
    public Submit g() {
        return this.n;
    }

    @Override // com.huawei.hms.network.file.core.task.e
    public long f() {
        return this.b;
    }

    public void e(String str) {
        this.f = str;
    }

    @Override // com.huawei.hms.network.file.core.task.e
    public Future<?> e() {
        return this.u;
    }

    public void d(String str) {
        this.g = str;
    }

    public void c(boolean z) {
        this.p = z;
    }

    public void c(String str) {
        this.h = str;
    }

    public void c(int i) {
        this.j = i;
    }

    @Override // com.huawei.hms.network.file.core.task.e
    public long c() {
        return this.r;
    }

    @Override // com.huawei.hms.network.file.core.task.e
    public boolean b() {
        return this.m;
    }

    @Override // com.huawei.hms.network.file.core.task.e
    public void b(boolean z) {
        this.s = z;
    }

    public void b(String str) {
        this.i = str;
    }

    public void b(RequestFinishedInfo requestFinishedInfo) {
        this.k = requestFinishedInfo;
    }

    public void b(int i) {
        this.o = i;
    }

    @Override // com.huawei.hms.network.file.core.task.e
    public void a(boolean z) {
        this.m = z;
    }

    @Override // com.huawei.hms.network.file.core.task.e
    public void a(Future<?> future) {
        this.u = future;
    }

    @Override // com.huawei.hms.network.file.core.task.e
    public void a(String str) {
        this.c = str;
    }

    public void a(Submit submit) {
        this.n = submit;
    }

    public void a(RequestFinishedInfo requestFinishedInfo) {
        FLogger.v("Task", "CheckoutFromRequestFinishedInfo");
        if (requestFinishedInfo == null) {
            FLogger.w("Task", "checkoutFromRequestFinishedInfo, but requestFinishedInfo is null", new Object[0]);
            return;
        }
        d(requestFinishedInfo.getNetworkSdkType());
        RequestFinishedInfo.Metrics metrics = requestFinishedInfo.getMetrics();
        if (metrics != null) {
            e(metrics.getSuccessIp());
            c(metrics.getProtocol());
            b(metrics.getCongestionControlType());
            c(metrics.getMultipathAlgorithm());
        }
        a(requestFinishedInfo.getMetricsTime());
        this.l = true;
    }

    public void a(ITaskResult iTaskResult) {
        this.d = iTaskResult;
    }

    @Override // com.huawei.hms.network.file.core.task.e
    public void a(R r) {
        this.f5636a = r;
    }

    public void a(long j) {
        this.r = j;
    }

    public void a(int i) {
        this.e = i;
    }

    public static boolean d(int i) {
        return i > e.a.PAUSE.ordinal();
    }

    public static List<k> b(List<k> list) {
        if (list == null) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<k> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().x());
        }
        return arrayList;
    }

    public static void a(List<? extends k> list, RequestFinishedInfo requestFinishedInfo) {
        FLogger.v("Task", "checkRequestFinishedInfo");
        k a2 = a(list);
        if (a2.r() == null) {
            FLogger.w("Task", "requestFinishedInfo is null", new Object[0]);
            a2.b(requestFinishedInfo);
        }
    }

    public static k a(List<? extends k> list) {
        if (Utils.isEmpty(list)) {
            return null;
        }
        for (k kVar : list) {
            if (kVar != null && kVar.w()) {
                return kVar;
            }
        }
        return list.get(0);
    }

    public k(R r, long j, long j2) {
        this.f = "unknown";
        this.g = "unknown";
        this.h = "unknown";
        this.i = "unknown";
        this.l = false;
        this.m = false;
        this.p = false;
        this.s = false;
        this.t = 1;
        this.u = null;
        this.f5636a = r;
        this.b = j2;
        this.r = j;
    }

    public k(R r, long j) {
        this(r, j, Utils.getLongId());
    }
}
