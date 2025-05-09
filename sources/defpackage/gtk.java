package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.huawei.health.R;
import com.huawei.hms.kit.awareness.AwarenessStatusCodes;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gtk {
    private boolean b;
    private Context d;
    private StepRateData e;
    private long h = 0;
    private boolean c = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f12926a = false;
    private boolean g = false;
    private long f = System.currentTimeMillis();
    private int o = 0;
    private float j = 0.0f;
    private int n = 0;
    private int i = 0;

    public gtk(Context context, boolean z) {
        this.b = false;
        if (context == null) {
            throw new RuntimeException("AutoTrackManager init with null context.");
        }
        this.d = context;
        this.b = z;
    }

    public void b(boolean z) {
        this.c = z;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public boolean c(float f, int i) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f > TimeUnit.SECONDS.toMillis(30L)) {
            if (f - this.j > b()) {
                this.o = 0;
            } else {
                this.o++;
            }
            if (this.b) {
                if (i - this.i > 20) {
                    this.n = 0;
                } else {
                    this.n++;
                }
            }
            this.j = f;
            this.i = i;
            this.f = currentTimeMillis;
            ReleaseLogUtil.e("Track_AutoTrackManager", "isStopAutoTrack(), distance: ", LogAnonymous.b((int) f), " mTimesForLostGps: ", Integer.valueOf(this.o), " Time: ", LogAnonymous.d(this.f), " mTimesForLowSteps: ", Integer.valueOf(this.n));
        }
        return this.o >= 4 || this.n >= 6;
    }

    private int b() {
        return mwx.d() ? 25 : 50;
    }

    public boolean a() {
        return this.f12926a;
    }

    public boolean c() {
        if (!this.f12926a) {
            LogUtil.a("Track_AutoTrackManager", "recoveryAutoTrackSnapshotInfo() mIsEndSnapshot is false,");
            return false;
        }
        if (this.h == 0) {
            LogUtil.a("Track_AutoTrackManager", "isNeedSaveAutoTrack(), mMotionTrackFileLine is 0");
            return false;
        }
        d();
        return true;
    }

    public void a(boolean z, long j, long j2, float f) {
        LogUtil.a("Track_AutoTrackManager", "sendNotification, toSaveData: ", Boolean.valueOf(z), " ; mIsEndSnapshot: ", Boolean.valueOf(this.f12926a));
        Context context = this.d;
        if (context == null) {
            LogUtil.a("Track_AutoTrackManager", "sendNotification failed with null context.");
            return;
        }
        if (this.f12926a && z) {
            String b = SharedPreferenceManager.b(context, Integer.toString(10000), "health_msg_switch_noticebar");
            LogUtil.a("Track_AutoTrackManager", "showNotification() noticeBarRecommend", b);
            if ("0".equals(b)) {
                return;
            }
            a(this.d, j, j2, f);
        }
    }

    public void e() {
        LogUtil.a("Track_AutoTrackManager", "destroy()");
        f();
        this.d = null;
        this.i = 0;
        this.j = 0.0f;
        this.o = 0;
        this.n = 0;
        this.f = 0L;
    }

    private void f() {
        LogUtil.a("Track_AutoTrackManager", "removeSnapshot");
        if (this.c || this.f12926a || this.h > 0) {
            this.c = false;
            this.f12926a = false;
            this.g = false;
            this.h = 0L;
            gwo.e(this.d, "simplemotionbuffer.txt");
        }
    }

    private void d() {
        LogUtil.a("Track_AutoTrackManager", "recoveryAutoTrackSnapshotInfo start");
        if (this.d == null) {
            LogUtil.a("Track_AutoTrackManager", "recoveryAutoTrackSnapshotInfo failed with null context.");
            return;
        }
        if (gwo.c(Long.valueOf(this.h), this.d) && gwo.c(this.d, "simplemotionbuffer.txt", "simplemotion.txt")) {
            gtx c = gtx.c(this.d);
            c.s();
            c.a(true);
            c.e(this.e);
            LogUtil.a("Track_AutoTrackManager", "recoveryAutoTrackSnapshotInfo success recoveryMotionPathFile:", Long.valueOf(this.h), " copySimpleFile: SIMPLE_MOTION_FILE_NAME_BUFFER");
        }
    }

    private void a(Context context, long j, long j2, float f) {
        String string;
        int i = (int) f;
        LogUtil.a("Track_AutoTrackManager", "createNotification() startTime: ", LogAnonymous.d(j), " ;endTime: ", LogAnonymous.d(j2), " ;totalSportDistance: ", LogAnonymous.b(i));
        Intent aTx_ = aTx_(context, j, j2);
        if (aTx_ == null) {
            LogUtil.h("Track_AutoTrackManager", "Notification intent is null.");
            return;
        }
        if (j == 0 || j2 == 0 || f == 0.0f) {
            LogUtil.h("Track_AutoTrackManager", "startTime: ", LogAnonymous.d(j), " ;endTime: ", LogAnonymous.d(j2), " ;totalSportDistance: ", LogAnonymous.b(i));
            return;
        }
        PendingIntent service = PendingIntent.getService(context, 0, aTx_, 201326592);
        if (UnitUtil.h()) {
            string = context.getResources().getString(R.string._2130839806_res_0x7f0208fe, context.getResources().getString(R.string._2130841422_res_0x7f020f4e, UnitUtil.e(f, 1, 2)));
        } else {
            string = context.getResources().getString(R.string._2130839806_res_0x7f0208fe, context.getResources().getString(R.string._2130841421_res_0x7f020f4d, UnitUtil.e(f, 1, 2)));
        }
        jdh.c().xh_(AwarenessStatusCodes.AWARENESS_LOCATION_PERMISSION_CODE, aTy_(string, context.getString(context.getApplicationInfo().labelRes), string, service));
    }

    private Notification aTy_(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, PendingIntent pendingIntent) {
        Notification.Builder xf_ = jdh.c().xf_();
        nsn.cLB_(xf_);
        xf_.setTicker(charSequence);
        xf_.setContentTitle(charSequence2);
        xf_.setContentText(charSequence3);
        xf_.setContentIntent(pendingIntent);
        xf_.setAutoCancel(true);
        xf_.setOngoing(false);
        xf_.setOnlyAlertOnce(true);
        xf_.setPriority(0);
        xf_.setDefaults(2);
        return xf_.build();
    }

    private Intent aTx_(Context context, long j, long j2) {
        Intent intent;
        try {
            intent = new Intent(context, Class.forName("com.huawei.health.receiver.MainProcessHelperService"));
            try {
                intent.putExtra("startTime", j);
                intent.putExtra("endTime", j2);
                intent.setAction("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                intent.setFlags(268435456);
            } catch (ClassNotFoundException unused) {
                LogUtil.b("Track_AutoTrackManager", "getAutoTrackNotificationServiceIntent() AutoTrackNotificationService not found");
                return intent;
            }
        } catch (ClassNotFoundException unused2) {
            intent = null;
        }
        return intent;
    }
}
