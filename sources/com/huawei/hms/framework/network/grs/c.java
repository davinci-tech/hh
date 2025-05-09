package com.huawei.hms.framework.network.grs;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.grs.g.g;
import com.huawei.hms.framework.network.grs.g.h;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes4.dex */
public class c {
    private static final String i = "c";
    private static final ExecutorService j = ExecutorsUtils.newSingleThreadExecutor("GrsInit");
    private static long k = 0;

    /* renamed from: a, reason: collision with root package name */
    private GrsBaseInfo f4529a;
    private Context b;
    private g c;
    private com.huawei.hms.framework.network.grs.e.a d;
    private com.huawei.hms.framework.network.grs.e.c e;
    private com.huawei.hms.framework.network.grs.e.c f;
    private com.huawei.hms.framework.network.grs.a g;
    private FutureTask<Boolean> h;

    boolean b() {
        GrsBaseInfo grsBaseInfo;
        Context context;
        if (!f() || (grsBaseInfo = this.f4529a) == null || (context = this.b) == null) {
            return false;
        }
        this.d.a(grsBaseInfo, context);
        return true;
    }

    boolean a(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && c.class == obj.getClass() && (obj instanceof c)) {
            return this.f4529a.compare(((c) obj).f4529a);
        }
        return false;
    }

    void a(String str, String str2, IQueryUrlCallBack iQueryUrlCallBack, int i2) {
        if (iQueryUrlCallBack == null) {
            Logger.w(i, "IQueryUrlCallBack is must not null for process continue.");
            return;
        }
        if (this.f4529a == null || str == null || str2 == null) {
            iQueryUrlCallBack.onCallBackFail(-6);
        } else if (f()) {
            this.g.a(str, str2, iQueryUrlCallBack, this.b, i2);
        } else {
            Logger.i(i, "grs init task has not completed.");
            iQueryUrlCallBack.onCallBackFail(-7);
        }
    }

    void a(String str, IQueryUrlsCallBack iQueryUrlsCallBack, int i2) {
        if (iQueryUrlsCallBack == null) {
            Logger.w(i, "IQueryUrlsCallBack is must not null for process continue.");
            return;
        }
        if (this.f4529a == null || str == null) {
            iQueryUrlsCallBack.onCallBackFail(-6);
        } else if (f()) {
            this.g.a(str, iQueryUrlsCallBack, this.b, i2);
        } else {
            Logger.i(i, "grs init task has not completed.");
            iQueryUrlsCallBack.onCallBackFail(-7);
        }
    }

    void a() {
        if (f()) {
            String grsParasKey = this.f4529a.getGrsParasKey(true, true, this.b);
            this.e.a(grsParasKey);
            this.e.a(grsParasKey + "time");
            this.e.a(grsParasKey + "ETag");
            this.c.a(grsParasKey);
        }
    }

    Map<String, String> a(String str, int i2) {
        if (this.f4529a != null && str != null) {
            return f() ? this.g.a(str, this.b, i2) : new HashMap();
        }
        Logger.w(i, "invalid para!");
        return new HashMap();
    }

    String a(String str, String str2, int i2) {
        if (this.f4529a == null || str == null || str2 == null) {
            Logger.w(i, "invalid para!");
            return null;
        }
        if (f()) {
            return this.g.a(str, str2, this.b, i2);
        }
        return null;
    }

    private boolean f() {
        String str;
        String str2;
        FutureTask<Boolean> futureTask = this.h;
        if (futureTask == null) {
            return false;
        }
        try {
            return futureTask.get(8L, TimeUnit.SECONDS).booleanValue();
        } catch (InterruptedException e) {
            e = e;
            str = i;
            str2 = "init compute task interrupted.";
            Logger.w(str, str2, e);
            return false;
        } catch (CancellationException unused) {
            Logger.i(i, "init compute task canceled.");
            return false;
        } catch (ExecutionException e2) {
            e = e2;
            str = i;
            str2 = "init compute task failed.";
            Logger.w(str, str2, e);
            return false;
        } catch (TimeoutException unused2) {
            Logger.w(i, "init compute task timed out");
            return false;
        } catch (Exception e3) {
            e = e3;
            str = i;
            str2 = "init compute task occur unknown Exception";
            Logger.w(str, str2, e);
            return false;
        }
    }

    private boolean b(long j2) {
        return System.currentTimeMillis() - j2 <= 604800000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Map<String, ?> map) {
        long j2;
        String substring;
        String str;
        if (map == null || map.isEmpty()) {
            Logger.v(i, "sp's content is empty.");
            return;
        }
        Set<String> keySet = map.keySet();
        for (String str2 : keySet) {
            if (str2.endsWith(this.b.getPackageName() + "time")) {
                String a2 = this.e.a(str2, "");
                if (!TextUtils.isEmpty(a2) && a2.matches("\\d+")) {
                    try {
                        j2 = Long.parseLong(a2);
                    } catch (NumberFormatException e) {
                        Logger.w(i, "convert expire time from String to Long catch NumberFormatException.", e);
                    }
                    substring = str2.substring(0, str2.length() - 4);
                    str = substring + "ETag";
                    if (b(j2) || !keySet.contains(substring) || !keySet.contains(str)) {
                        Logger.i(i, "init interface auto clear some invalid sp's data: " + str2);
                        this.e.a(substring);
                        this.e.a(str2);
                        this.e.a(str);
                    }
                }
                j2 = 0;
                substring = str2.substring(0, str2.length() - 4);
                str = substring + "ETag";
                if (b(j2)) {
                }
                Logger.i(i, "init interface auto clear some invalid sp's data: " + str2);
                this.e.a(substring);
                this.e.a(str2);
                this.e.a(str);
            }
        }
    }

    private void a(GrsBaseInfo grsBaseInfo) {
        try {
            this.f4529a = grsBaseInfo.m600clone();
        } catch (CloneNotSupportedException e) {
            Logger.w(i, "GrsClient catch CloneNotSupportedException", e);
            this.f4529a = grsBaseInfo.copy();
        }
    }

    class a implements Callable<Boolean> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f4530a;
        final /* synthetic */ GrsBaseInfo b;

        /* renamed from: com.huawei.hms.framework.network.grs.c$a$a, reason: collision with other inner class name */
        class RunnableC0096a implements Runnable {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ com.huawei.hms.framework.network.grs.f.b f4531a;
            final /* synthetic */ com.huawei.hms.framework.network.grs.e.c b;

            @Override // java.lang.Runnable
            public void run() {
                this.f4531a.a(a.this.f4530a.getPackageName(), this.b);
            }

            RunnableC0096a(com.huawei.hms.framework.network.grs.f.b bVar, com.huawei.hms.framework.network.grs.e.c cVar) {
                this.f4531a = bVar;
                this.b = cVar;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() {
            c.this.c = new g();
            c.this.e = new com.huawei.hms.framework.network.grs.e.c(this.f4530a, GrsApp.getInstance().getBrand("_") + "share_pre_grs_conf_");
            c.this.f = new com.huawei.hms.framework.network.grs.e.c(this.f4530a, GrsApp.getInstance().getBrand("_") + "share_pre_grs_services_");
            c cVar = c.this;
            cVar.d = new com.huawei.hms.framework.network.grs.e.a(cVar.e, c.this.f, c.this.c);
            c cVar2 = c.this;
            cVar2.g = new com.huawei.hms.framework.network.grs.a(cVar2.f4529a, c.this.d, c.this.c, c.this.f);
            if (com.huawei.hms.framework.network.grs.f.b.a(this.f4530a.getPackageName()) == null) {
                com.huawei.hms.framework.network.grs.e.c cVar3 = new com.huawei.hms.framework.network.grs.e.c(this.f4530a, GrsApp.getInstance().getBrand("_") + "share_pre_grs_local_conf_");
                String a2 = cVar3.a(this.f4530a.getPackageName() + "#localConfig", "");
                if (TextUtils.isEmpty(a2) || !new com.huawei.hms.framework.network.grs.f.b().a(this.f4530a, a2)) {
                    c.j.execute(new RunnableC0096a(new com.huawei.hms.framework.network.grs.f.b(this.f4530a, true), cVar3));
                }
            }
            String c = new com.huawei.hms.framework.network.grs.g.j.c(this.b, this.f4530a).c();
            Logger.v(c.i, "scan serviceSet is: " + c);
            String a3 = c.this.f.a("services", "");
            String a4 = h.a(a3, c);
            if (!TextUtils.isEmpty(a4)) {
                c.this.f.b("services", a4);
                Logger.i(c.i, "postList is:" + StringUtils.anonymizeMessage(a4));
                Logger.d(c.i, "currentServices:" + StringUtils.anonymizeMessage(a3));
                if (!a4.equals(a3)) {
                    c.this.c.a(c.this.f4529a.getGrsParasKey(true, true, this.f4530a));
                    c.this.c.a(new com.huawei.hms.framework.network.grs.g.j.c(this.b, this.f4530a), null, null, c.this.f, c.this.f4529a.getQueryTimeout());
                }
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j = c.k;
            if (c.k == 0 || TimeUnit.MILLISECONDS.toHours(elapsedRealtime - j) > 24) {
                Logger.i(c.i, "Try to clear unUsed sp data.");
                long unused = c.k = SystemClock.elapsedRealtime();
                c cVar4 = c.this;
                cVar4.a(cVar4.e.a());
            }
            c.this.d.b(this.b, this.f4530a);
            return true;
        }

        a(Context context, GrsBaseInfo grsBaseInfo) {
            this.f4530a = context;
            this.b = grsBaseInfo;
        }
    }

    c(GrsBaseInfo grsBaseInfo) {
        this.h = null;
        a(grsBaseInfo);
    }

    c(Context context, GrsBaseInfo grsBaseInfo) {
        this.h = null;
        this.b = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        a(grsBaseInfo);
        GrsBaseInfo grsBaseInfo2 = this.f4529a;
        FutureTask<Boolean> futureTask = new FutureTask<>(new a(this.b, grsBaseInfo2));
        this.h = futureTask;
        j.execute(futureTask);
        Logger.i(i, "GrsClient Instance is init, GRS SDK version: %s, GrsBaseInfoParam: app_name=%s, reg_country=%s, ser_country=%s, issue_country=%s ,queryTimeout=%d", com.huawei.hms.framework.network.grs.h.a.a(), grsBaseInfo2.getAppName(), grsBaseInfo.getRegCountry(), grsBaseInfo.getSerCountry(), grsBaseInfo.getIssueCountry(), Integer.valueOf(grsBaseInfo.getQueryTimeout()));
    }
}
