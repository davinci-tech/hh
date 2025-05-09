package com.huawei.watchface;

import android.app.ActivityManager;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.watchface.api.WebViewActivity;
import com.huawei.watchface.utils.HwLog;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes7.dex */
public class fj implements Runnable {
    private static volatile fj k;
    private static final c m = new c() { // from class: com.huawei.watchface.fj.1
        @Override // com.huawei.watchface.fj.c
        public void a() {
            HwLog.i("WatchFaceAnrWatchDog", "onAppNotResponding anr maybe happen");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            String b2 = b();
            linkedHashMap.put("stackTraces", b2);
            HwLog.d("WatchFaceAnrWatchDog", "onAppNotResponding anr mainThreadStackTrace:" + b2);
            if (WebViewActivity.getActivity() == null) {
                eb.a((LinkedHashMap<String, String>) linkedHashMap);
                return;
            }
            ActivityManager activityManager = (ActivityManager) WebViewActivity.getActivity().getSystemService("activity");
            if (activityManager == null) {
                HwLog.e("WatchFaceAnrWatchDog", "onAppNotResponding activityManager is null");
                eb.a((LinkedHashMap<String, String>) linkedHashMap);
                return;
            }
            List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
            if (processesInErrorState != null) {
                for (ActivityManager.ProcessErrorStateInfo processErrorStateInfo : processesInErrorState) {
                    if (processErrorStateInfo.condition == 2) {
                        HwLog.d("WatchFaceAnrWatchDog", "onAppNotResponding anr shortMsg:" + processErrorStateInfo.shortMsg);
                        linkedHashMap.put("shortMsg", processErrorStateInfo.shortMsg);
                        linkedHashMap.put("longMsg", processErrorStateInfo.longMsg);
                    }
                }
            }
            if (TextUtils.isEmpty(b2)) {
                return;
            }
            eb.a((LinkedHashMap<String, String>) linkedHashMap);
        }

        private String b() {
            StackTraceElement[] stackTrace;
            Thread thread = Looper.getMainLooper().getThread();
            if (thread == null || (stackTrace = thread.getStackTrace()) == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement stackTraceElement : stackTrace) {
                sb.append(stackTraceElement.toString());
                sb.append("\n");
            }
            return sb.toString();
        }
    };
    private static final a n = new a() { // from class: com.huawei.watchface.fj.2
        @Override // com.huawei.watchface.fj.a
        public long a(long j) {
            return 0L;
        }
    };
    private static final b o = new b() { // from class: com.huawei.watchface.fj.3
        @Override // com.huawei.watchface.fj.b
        public void a(Exception exc) {
            HwLog.w("WatchFaceAnrWatchDog", "Interrupted: " + HwLog.printException(exc));
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private ExecutorService f11043a;
    private c b;
    private a c;
    private b d;
    private final Handler e;
    private final int f;
    private boolean g;
    private boolean h;
    private volatile long i;
    private volatile boolean j;
    private final Runnable l;

    public interface a {
        long a(long j);
    }

    public interface b {
        void a(Exception exc);
    }

    public interface c {
        void a();
    }

    public static fj a() {
        if (k == null) {
            synchronized (fj.class) {
                if (k == null) {
                    k = new fj();
                }
            }
        }
        return k;
    }

    public fj() {
        this(5000);
    }

    public fj(int i) {
        this.b = m;
        this.c = n;
        this.d = o;
        this.e = new Handler(Looper.getMainLooper());
        this.g = false;
        this.h = true;
        this.i = 0L;
        this.j = false;
        this.l = new Runnable() { // from class: com.huawei.watchface.fj$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                fj.this.d();
            }
        };
        this.f = i;
    }

    public void a(boolean z) {
        HwLog.d("WatchFaceAnrWatchDog", "enableAnrCollect:" + z);
        this.h = z;
        if (z) {
            return;
        }
        this.g = false;
    }

    public void b() {
        try {
            HwLog.d("WatchFaceAnrWatchDog", "startAnrServer");
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            this.f11043a = newSingleThreadExecutor;
            newSingleThreadExecutor.execute(k);
        } catch (Exception e) {
            HwLog.d("WatchFaceAnrWatchDog", "startAnrServer(): " + HwLog.printException(e));
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        HwLog.d("WatchFaceAnrWatchDog", "ANR-WatchDog start");
        if (this.g) {
            HwLog.d("WatchFaceAnrWatchDog", "ANR-WatchDog is running");
            return;
        }
        this.g = true;
        long j = this.f;
        while (this.h) {
            boolean z = this.i == 0;
            this.i += j;
            if (z) {
                this.e.post(this.l);
            }
            try {
                Thread.currentThread();
                Thread.sleep(j);
                if (this.i != 0 && !this.j) {
                    j = this.c.a(this.i);
                    if (j <= 0) {
                        this.b.a();
                        j = this.f;
                        this.j = true;
                    }
                }
            } catch (Exception e) {
                this.d.a(e);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d() {
        this.i = 0L;
        this.j = false;
    }

    public void c() {
        HwLog.d("WatchFaceAnrWatchDog", "stopAnrServer");
        ExecutorService executorService = this.f11043a;
        if (executorService == null) {
            HwLog.d("WatchFaceAnrWatchDog", "executorService is null");
            return;
        }
        try {
            if (executorService.isShutdown()) {
                return;
            }
            this.f11043a.shutdownNow();
            this.f11043a = null;
        } catch (Exception e) {
            HwLog.d("WatchFaceAnrWatchDog", "stopAnrServer Exception:" + HwLog.printException(e));
        }
    }
}
