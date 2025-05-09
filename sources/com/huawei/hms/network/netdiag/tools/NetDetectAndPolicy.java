package com.huawei.hms.network.netdiag.tools;

import android.net.NetworkInfo;
import android.os.SystemClock;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.LimitQueue;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.framework.common.SettingUtil;
import com.huawei.hms.network.embedded.a5;
import com.huawei.hms.network.embedded.j5;
import com.huawei.hms.network.embedded.k5;
import com.huawei.hms.network.embedded.l5;
import com.huawei.hms.network.embedded.s4;
import com.huawei.hms.network.embedded.t4;
import com.huawei.hms.network.embedded.u4;
import com.huawei.hms.network.embedded.u5;
import com.huawei.hms.network.embedded.v5;
import com.huawei.hms.network.embedded.y4;
import com.huawei.hms.network.embedded.y5;
import com.huawei.hms.network.embedded.z4;
import com.huawei.hms.network.netdiag.cache.SignalInfoCache;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class NetDetectAndPolicy {
    public static final String l = "Netdiag";
    public l5 i;
    public int d = 2;
    public int e = 3;
    public int f = -1;
    public long g = 0;
    public boolean h = true;
    public int j = 0;
    public CountDownLatch k = null;

    /* renamed from: a, reason: collision with root package name */
    public ExecutorService f5646a = ExecutorsUtils.newSingleThreadExecutor("netdiag");
    public ExecutorService b = ExecutorsUtils.newSingleThreadExecutor("netdiag_execute_task");
    public LimitQueue<Boolean> c = new LimitQueue<>(Math.max(this.d, this.e) + 1, false);

    public boolean networkUnavailable(long j, long j2) {
        int currentNetworkType = NetworkUtil.getCurrentNetworkType();
        int obtainNetworkChanged = obtainNetworkChanged(j, j2);
        Logger.i(l, "current_network_change : %d ,current_network_type: %d", Integer.valueOf(obtainNetworkChanged), Integer.valueOf(currentNetworkType));
        return obtainNetworkChanged != 0 || currentNetworkType == -1;
    }

    public z4 netDetDiagInfo(long j, long j2) {
        Logger.v(l, "obtain the info time:" + j + "/" + j2);
        a5 a5Var = new a5();
        a5Var.a(u4.getInstance().getPeekLastInfo());
        a5Var.b(SignalInfoCache.getInstance().getPeekLastInfo());
        a5Var.a(SignalInfoCache.getInstance().getLastInfo());
        a5Var.a(t4.getInstance().getPeekLastInfo());
        if (ContextHolder.getResourceContext() != null) {
            a5Var.a(SettingUtil.getSecureInt(ContextHolder.getResourceContext().getContentResolver(), "adb_enabled", 0));
        }
        a5Var.b(obtainNetworkChanged(j, j2));
        try {
            CountDownLatch countDownLatch = this.k;
            if (countDownLatch != null) {
                Logger.i(l, "the netdiag has collected;and the timeout = " + countDownLatch.await(10L, TimeUnit.SECONDS));
            }
            a5Var.a(s4.getInstance().getPeekLastInfo());
        } catch (InterruptedException unused) {
            Logger.w(l, "the wait has timeout! and exit it!");
        }
        return a5Var;
    }

    public boolean executeDetectPolicy() {
        String str;
        try {
            Future<?> executeDetectPolicy = executeDetectPolicy("default");
            if (executeDetectPolicy != null) {
                executeDetectPolicy.get();
            }
            return e();
        } catch (InterruptedException e) {
            e = e;
            str = "the InterruptedException has occur";
            Logger.w(l, str, e);
            return false;
        } catch (ExecutionException e2) {
            e = e2;
            str = "the executionException has occur";
            Logger.w(l, str, e);
            return false;
        } catch (Exception e3) {
            e = e3;
            str = "the other Exception has occur";
            Logger.w(l, str, e);
            return false;
        }
    }

    public <T> Future<?> executeDetectPolicy(T t) {
        String str;
        try {
            return this.f5646a.submit(new a(t));
        } catch (RejectedExecutionException e) {
            e = e;
            str = "the taskExecutor has error! and reject";
            Logger.w(l, str, e);
            return null;
        } catch (Exception e2) {
            e = e2;
            str = "the taskExecutor has error! and other exception";
            Logger.w(l, str, e);
            return null;
        }
    }

    public static int obtainNetworkChanged(long j, long j2) {
        int obtainNetworkQuality = t4.getInstance().obtainNetworkQuality(j, j2);
        return obtainNetworkQuality == 0 ? SignalInfoCache.getInstance().obtainNetworkQuality(j, j2) : obtainNetworkQuality;
    }

    private void f() {
        this.g = 0L;
        this.f = -1;
    }

    private boolean e() {
        List<y4> a2 = s4.getInstance().getPeekLastInfo().a(1);
        return (a2 == null || a2.isEmpty() || a2.get(a2.size() - 1).c() != 204) ? false : true;
    }

    private boolean d() {
        String str;
        int size = this.c.size();
        if (size == 1) {
            return false;
        }
        int i = size - 1;
        if (this.c.get(size - 2) != this.c.get(i)) {
            str = "last two requests is different";
        } else {
            if (size < this.d) {
                Logger.v(l, "request times is not enough");
                return false;
            }
            while (i >= size - this.d) {
                if (this.c.get(i).booleanValue()) {
                    return false;
                }
                i--;
            }
            str = "meet bad threshold";
        }
        Logger.v(l, str);
        return true;
    }

    private int d(long j) {
        String str;
        int i;
        b(j);
        if (!this.h) {
            str = "the appId is available";
        } else if (b() || a(j)) {
            str = "inhibition this time and return it";
        } else {
            if (d()) {
                if (NetworkUtil.getCurrentNetworkType() == 1) {
                    Logger.v(l, "the http will detected ping and http");
                    i = 3;
                } else {
                    i = 2;
                }
                this.g = j;
                this.f++;
                Logger.v(l, "the time the date is : lastDetectTime = %s, detectCount = %s，selectDetectMode = %d", Long.valueOf(j), Integer.valueOf(this.f), Integer.valueOf(i));
                return i;
            }
            str = "the detect shouldn't be detected";
        }
        Logger.v(l, str);
        return 0;
    }

    private boolean c() {
        int size;
        if (this.c.isEmpty() || (size = this.c.size()) < this.e + 1) {
            return false;
        }
        for (int i = size - 1; i >= size - this.e; i--) {
            if (!this.c.get(i).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    private int c(long j) {
        Logger.v(l, "this time the initiativing ...");
        if (!this.h) {
            Logger.v(l, "the appid is error! pls check it");
            return 0;
        }
        long j2 = j - this.g;
        if (j2 > 300000) {
            this.j = 0;
        }
        int i = this.j;
        if (i >= 3 || j2 <= 60000) {
            return 0;
        }
        this.g = j;
        this.j = i + 1;
        return 2;
    }

    private boolean b() {
        NetworkInfo.DetailedState networkStatus = NetworkUtil.networkStatus(ContextHolder.getResourceContext());
        int currentNetworkType = NetworkUtil.getCurrentNetworkType();
        return networkStatus != NetworkInfo.DetailedState.CONNECTED || currentNetworkType == 2 || currentNetworkType == 3;
    }

    private void b(long j) {
        Logger.v(l, "the time the date is : nowTime = %s, lastDetectTime = %s", Long.valueOf(j), Long.valueOf(this.g));
        if (a() || j - this.g > 3600000 || c()) {
            Logger.i(l, "the time count will reset!");
            f();
        }
    }

    private boolean a(long j) {
        return j - this.g < (((long) this.f) * 600000) + 300000;
    }

    private boolean a() {
        return t4.getInstance().getPeekLastInfo().b() > this.g;
    }

    public class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Object f5647a;

        @Override // java.lang.Runnable
        public void run() {
            NetDetectAndPolicy.this.a((NetDetectAndPolicy) this.f5647a);
        }

        public a(Object obj) {
            this.f5647a = obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public <T> void a(T t) {
        int c;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (t instanceof Boolean) {
            this.c.add((Boolean) t);
            c = d(elapsedRealtime);
        } else {
            c = c(elapsedRealtime);
        }
        a(c, this.b);
    }

    private void a(int i, ExecutorService executorService) {
        v5 a2;
        Logger.v(l, "the time detect model is : " + i);
        if (i == 0) {
            Logger.v(l, "this time will do nothing!");
            return;
        }
        this.k = new CountDownLatch(1);
        u5 u5Var = new u5(executorService);
        u5Var.a(new j5(this.i));
        if (i == 2) {
            a2 = u5Var.a();
        } else {
            if (i != 3) {
                Logger.i(l, "the policy model has error! and the model = " + i);
                this.k.countDown();
            }
            y5 y5Var = new y5(executorService);
            y5Var.a(new k5(this.i));
            a2 = y5Var.a().a(u5Var);
        }
        this.h = a2.f5539a.a();
        this.k.countDown();
    }

    public NetDetectAndPolicy(l5 l5Var) {
        this.i = l5Var;
    }
}
