package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.SystemClock;
import com.huawei.basefitnessadvice.model.Media;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.courseplanservice.api.OnStateListener;
import com.huawei.health.plan.model.data.DataSyncService;
import com.huawei.health.plan.model.ui.fitness.service.DeviceRecordSyncService;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.etn;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class etn {
    public static boolean b(String str) {
        Plan currentPlan = etr.b().getCurrentPlan();
        return currentPlan != null && str.equals(currentPlan.acquireId());
    }

    public static void d(JSONObject jSONObject) {
        if (jSONObject != null) {
            jSONObject.remove("resultCode");
            jSONObject.remove("resultDesc");
        }
    }

    public static void b() {
        a.e().c(null);
    }

    public static void a(OnStateListener onStateListener) {
        if (!CommonUtil.aa(arx.b())) {
            LogUtil.a("Suggestion_DataImplHelper", "the isNetworkConnected is false, stop to to sync data");
        } else if (LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) == null) {
            LogUtil.a("Suggestion_DataImplHelper", "the userId is null, User is not login, stop to sync data");
        } else {
            a.e().c(onStateListener);
        }
    }

    public static Media e(List<Media> list) {
        for (Media media : list) {
            if (!media.isFinished()) {
                return media;
            }
        }
        return null;
    }

    public static long d(List<Media> list) {
        long j = 0;
        if (list == null) {
            return 0L;
        }
        for (Media media : list) {
            if (media != null) {
                j += media.getDownloadLength();
            }
        }
        return j;
    }

    public static void d(int i, int i2, int i3) {
        ash.a("finishPlanCount", String.valueOf(i));
        ash.a("totalTrainingDays", String.valueOf(i3));
        ash.a("totalCalorie", String.valueOf(i2));
    }

    public static boolean c(WorkoutRecord workoutRecord) {
        if (workoutRecord == null) {
            return false;
        }
        return ((double) workoutRecord.acquireActualCalorie()) >= 1.0E-6d || ((double) workoutRecord.acquireActualDistance()) >= 1.0E-6d;
    }

    /* renamed from: etn$1, reason: invalid class name */
    class AnonymousClass1 implements ServiceConnection {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ OnStateListener f12295a;

        AnonymousClass1(OnStateListener onStateListener) {
            this.f12295a = onStateListener;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DeviceRecordSyncService.DeviceDataSyncBinder deviceDataSyncBinder = (DeviceRecordSyncService.DeviceDataSyncBinder) iBinder;
            deviceDataSyncBinder.setListener(new OnStateListener() { // from class: etn.1.2
                @Override // com.huawei.health.courseplanservice.api.OnStateListener
                public void onFailure(int i, String str) {
                    AnonymousClass1.this.onServiceDisconnected(null);
                    AnonymousClass1.this.f12295a.onFailure(i, str);
                }

                @Override // com.huawei.health.courseplanservice.api.OnStateListener
                public void onFinish() {
                    AnonymousClass1.this.onServiceDisconnected(null);
                    AnonymousClass1.this.f12295a.onFinish();
                }
            });
            deviceDataSyncBinder.dataSync();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            try {
                BaseApplication.getContext().unbindService(this);
            } catch (IllegalArgumentException e) {
                LogUtil.h("Suggestion_DataImplHelper", "startDeviceRecordSyncService " + e.getMessage());
            }
        }
    }

    public static void b(OnStateListener onStateListener) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(onStateListener);
        BaseApplication.getContext().bindService(new Intent(arx.b(), (Class<?>) DeviceRecordSyncService.class), anonymousClass1, 1);
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static DataSyncService.DataSyncBinder f12296a;
        private static volatile Map<OnStateListener, Long> c = new ConcurrentHashMap();
        private final OnStateListener b;
        private final OnStateListener d;
        private final Object e;
        private final ServiceConnection h;

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        private a() {
            this.e = new Object();
            this.h = new ServiceConnection() { // from class: etn.a.3
                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    LogUtil.a("Suggestion_DataSyncServiceInnerControl", "bind DataSyncService onServiceConnected.");
                    if (iBinder instanceof DataSyncService.DataSyncBinder) {
                        DataSyncService.DataSyncBinder unused = a.f12296a = (DataSyncService.DataSyncBinder) iBinder;
                        synchronized (a.this.e) {
                            a.this.e.notifyAll();
                        }
                        return;
                    }
                    LogUtil.b("Suggestion_DataSyncServiceInnerControl", "ClassCastException occurred: ");
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                    ReleaseLogUtil.e("Suggestion_DataSyncServiceInnerControl", "start to onServiceDisconnected");
                }
            };
            this.d = new OnStateListener() { // from class: etn.a.4
                @Override // com.huawei.health.courseplanservice.api.OnStateListener
                public void onFailure(int i, String str) {
                }

                @Override // com.huawei.health.courseplanservice.api.OnStateListener
                public void onFinish() {
                }
            };
            this.b = new OnStateListener() { // from class: etn.a.5
                @Override // com.huawei.health.courseplanservice.api.OnStateListener
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.c("Suggestion_DataSyncServiceInnerControl", "Sync Data Failed.");
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    for (OnStateListener onStateListener : a.c.keySet()) {
                        onStateListener.onFailure(i, str);
                        Long l = (Long) a.c.remove(onStateListener);
                        if (l != null) {
                            LogUtil.a("Suggestion_DataSyncServiceInnerControl", "startDataSyncToCloud failed in ", Long.valueOf(elapsedRealtime - l.longValue()), " ms");
                        }
                    }
                    a.this.c();
                }

                @Override // com.huawei.health.courseplanservice.api.OnStateListener
                public void onFinish() {
                    ReleaseLogUtil.e("Suggestion_DataSyncServiceInnerControl", "Sync Data Finished.");
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    for (OnStateListener onStateListener : a.c.keySet()) {
                        onStateListener.onFinish();
                        Long l = (Long) a.c.remove(onStateListener);
                        if (l != null) {
                            LogUtil.a("Suggestion_DataSyncServiceInnerControl", "startDataSyncToCloud finish in ", Long.valueOf(elapsedRealtime - l.longValue()), " ms");
                        }
                    }
                    a.this.c();
                }
            };
        }

        static class c {
            private static final a c = new a(null);
        }

        public static a e() {
            return c.c;
        }

        public void c(final OnStateListener onStateListener) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: etm
                @Override // java.lang.Runnable
                public final void run() {
                    etn.a.this.a(onStateListener);
                }
            });
        }

        /* synthetic */ void a(OnStateListener onStateListener) {
            synchronized (a.class) {
                DataSyncService.DataSyncBinder a2 = a();
                f12296a = a2;
                if (a2 == null) {
                    ReleaseLogUtil.e("Suggestion_DataSyncServiceInnerControl", "the mBinder is null, can‘t execute data sync.");
                    if (onStateListener != null) {
                        onStateListener.onFailure(-6, "bindService fail.");
                    }
                    return;
                }
                if (!c.isEmpty()) {
                    Map<OnStateListener, Long> map = c;
                    if (onStateListener == null) {
                        onStateListener = this.d;
                    }
                    map.put(onStateListener, Long.valueOf(SystemClock.elapsedRealtime()));
                    ReleaseLogUtil.d("Suggestion_DataSyncServiceInnerControl", "startDataSyncToCloud duplicate, [", Integer.valueOf(c.size()), "] task in query");
                    return;
                }
                Map<OnStateListener, Long> map2 = c;
                if (onStateListener == null) {
                    onStateListener = this.d;
                }
                map2.put(onStateListener, Long.valueOf(SystemClock.elapsedRealtime()));
                f12296a.setListener(this.b);
                DataSyncService dataSyncService = f12296a.getDataSyncService();
                if (dataSyncService != null) {
                    dataSyncService.a();
                }
            }
        }

        private DataSyncService.DataSyncBinder a() {
            DataSyncService.DataSyncBinder dataSyncBinder;
            DataSyncService.DataSyncBinder dataSyncBinder2 = f12296a;
            if (dataSyncBinder2 != null) {
                return dataSyncBinder2;
            }
            LogUtil.c("Suggestion_DataSyncServiceInnerControl", "start to bind DataSyncService");
            BaseApplication.getContext().bindService(new Intent(arx.b(), (Class<?>) DataSyncService.class), this.h, 1);
            synchronized (this.e) {
                try {
                    dataSyncBinder = f12296a;
                } catch (InterruptedException unused) {
                    LogUtil.b("Suggestion_DataSyncServiceInnerControl", "bindService() InterruptedException");
                }
                if (dataSyncBinder != null) {
                    LogUtil.a("Suggestion_DataSyncServiceInnerControl", "bindService() bind mBinder is not null = ", dataSyncBinder);
                    return f12296a;
                }
                this.e.wait(5000L);
                LogUtil.c("Suggestion_DataSyncServiceInnerControl", "bind DataSyncService finish, mBinder:", f12296a);
                return f12296a;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            synchronized (a.class) {
                if (f12296a != null) {
                    LogUtil.a("Suggestion_DataSyncServiceInnerControl", "unBind DataSyncService");
                    try {
                        BaseApplication.getContext().unbindService(this.h);
                    } catch (IllegalArgumentException unused) {
                        ReleaseLogUtil.c("Suggestion_DataSyncServiceInnerControl", "unBind DataSyncService appear IllegalArgumentException");
                    }
                    f12296a = null;
                }
            }
        }
    }

    public static String b(int i) {
        return "";
    }
}
