package defpackage;

import android.os.Looper;
import android.util.SparseArray;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class haa {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13034a = new Object();
    private static volatile haa c;
    private volatile boolean b = false;
    private volatile boolean d = false;
    private ScheduledExecutorService e;
    private long f;
    private int g;
    private float h;
    private float i;
    private int j;
    private int l;
    private long o;

    static /* synthetic */ int c(haa haaVar) {
        int i = haaVar.j;
        haaVar.j = i - 1;
        return i;
    }

    public int i() {
        return this.j;
    }

    public long a() {
        return this.f;
    }

    public long f() {
        return this.o;
    }

    public int d() {
        return this.g;
    }

    public int g() {
        return this.l;
    }

    public float h() {
        return this.h;
    }

    public float j() {
        return this.i;
    }

    public static haa c() {
        if (c == null) {
            synchronized (f13034a) {
                if (c == null) {
                    c = new haa();
                }
            }
        }
        return c;
    }

    public void d(final int i) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: haa.1
                @Override // java.lang.Runnable
                public void run() {
                    haa.this.d(i);
                }
            });
            return;
        }
        LogUtil.c("Track_SmartCoachHistorySportData", "enter startQueryUserHistorySportData() ");
        try {
            Thread.sleep(PreConnectManager.CONNECT_INTERNAL);
            LogUtil.c("Track_SmartCoachHistorySportData", "Thread.sleep recover");
            e(i);
        } catch (InterruptedException e2) {
            LogUtil.b("Track_SmartCoachHistorySportData", LogAnonymous.b((Throwable) e2));
        }
    }

    private void e(int i) {
        LogUtil.c("Track_SmartCoachHistorySportData", "enter executeQuerySportData()");
        l();
        long currentTimeMillis = System.currentTimeMillis();
        b(i, currentTimeMillis, jdl.e(currentTimeMillis, 0));
        if (this.e == null) {
            ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
            this.e = newSingleThreadScheduledExecutor;
            newSingleThreadScheduledExecutor.scheduleAtFixedRate(new e(), 5L, 5L, TimeUnit.SECONDS);
        }
    }

    private void b(int i, long j, long j2) {
        gzz.b(j2, j, i, new IBaseResponseCallback() { // from class: haa.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (koq.e(obj, HiHealthData.class)) {
                    List list = (List) obj;
                    haa.this.j = list.size();
                    haa.this.f = 0L;
                    haa.this.g = 0;
                    haa.this.h = Float.MAX_VALUE;
                    for (int i3 = 0; i3 < list.size(); i3++) {
                        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(((HiHealthData) list.get(i3)).getMetaData(), HiTrackMetaData.class);
                        if (!haa.this.b(hiTrackMetaData)) {
                            if (hiTrackMetaData.getTotalTime() > haa.this.f) {
                                haa.this.f = hiTrackMetaData.getTotalTime();
                            }
                            if (hiTrackMetaData.getTotalDistance() > haa.this.g) {
                                haa.this.g = hiTrackMetaData.getTotalDistance();
                            }
                            float d = haa.this.d(hiTrackMetaData.getPaceMap());
                            if (d > 0.0f && d < haa.this.h) {
                                haa.this.h = d;
                            }
                        } else {
                            haa.c(haa.this);
                        }
                    }
                    if (Math.abs(haa.this.h - Float.MAX_VALUE) <= 1.0E-6f) {
                        haa.this.h = 0.0f;
                    }
                    LogUtil.c("Track_SmartCoachHistorySportData", "executeQuerySportData mLastMonthRunCount = ", Integer.valueOf(haa.this.j), " mLastMonthMaxRunTime = ", Long.valueOf(haa.this.f), " mLastMonthMaxRunDistance = ", Integer.valueOf(haa.this.g), " mLastMonthOneKiloMetreFastPace = ", Float.valueOf(haa.this.h));
                } else {
                    LogUtil.h("Track_SmartCoachHistorySportData", "last month have no sport record.");
                }
                haa.this.b = true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float d(Map<Integer, Float> map) {
        Map<Integer, Float> a2;
        if (map == null || map.size() == 0 || (a2 = gvv.a(map)) == null || a2.size() <= 0) {
            return 0.0f;
        }
        float f = Float.MAX_VALUE;
        for (Map.Entry<Integer, Float> entry : a2.entrySet()) {
            if (entry.getValue().floatValue() < f) {
                f = entry.getValue().floatValue();
            }
        }
        return f;
    }

    private void l() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        final int[] iArr = {30006};
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(10);
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: haa.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (obj == null) {
                    LogUtil.h("Track_SmartCoachHistorySportData", "getLastRunData The return run data is null");
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("Track_SmartCoachHistorySportData", "getLastRunData The return run data is empty");
                    return;
                }
                Object obj2 = sparseArray.get(iArr[0]);
                List list = koq.e(obj2, HiHealthData.class) ? (List) obj2 : null;
                if (list != null && list.size() != 0) {
                    haa.this.d((List<HiHealthData>) list);
                } else {
                    LogUtil.h("Track_SmartCoachHistorySportData", "getLastRunData list is null or empty");
                }
                haa.this.d = true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<HiHealthData> list) {
        int i = 0;
        boolean z = false;
        while (true) {
            if (i >= list.size()) {
                break;
            }
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(list.get(i).getMetaData(), HiTrackMetaData.class);
            if (!b(hiTrackMetaData)) {
                if (!z) {
                    this.l = hiTrackMetaData.getTotalDistance();
                    this.o = hiTrackMetaData.getTotalTime();
                    z = true;
                }
                if (hiTrackMetaData.getTotalDistance() > 3000 && hiTrackMetaData.getAvgPace() < 600.0f) {
                    this.i = hiTrackMetaData.getAvgPace();
                    break;
                }
            }
            i++;
        }
        LogUtil.c("Track_SmartCoachHistorySportData", "getLastRunData mRecentOneRunDistance = ", Integer.valueOf(this.l), " mRecentOneRunTime = ", Long.valueOf(this.o), " mRecentOneRunAveragePace = ", Float.valueOf(this.i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(HiTrackMetaData hiTrackMetaData) {
        return hiTrackMetaData == null || hiTrackMetaData.getDuplicated() == 1 || hiTrackMetaData.getAbnormalTrack() != 0;
    }

    class e implements Runnable {
        private e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (haa.c != null && haa.this.b && haa.this.d) {
                hac.a().d();
            }
        }
    }

    private void m() {
        ScheduledExecutorService scheduledExecutorService = this.e;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.e = null;
        }
    }

    private static void o() {
        synchronized (f13034a) {
            c = null;
        }
    }

    public void b() {
        LogUtil.c("Track_SmartCoachHistorySportData", "destory()");
        m();
        o();
    }
}
