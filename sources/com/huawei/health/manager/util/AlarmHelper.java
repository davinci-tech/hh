package com.huawei.health.manager.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.manager.DaemonService;
import com.huawei.health.manager.LiteAutoTrackDataManager;
import com.huawei.health.manager.RealTimeStepDataReportHelper;
import com.huawei.health.manager.SportStatusDetectManager;
import com.huawei.health.manager.TriggerSportInterface;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.operation.OpAnalyticsConstants;
import defpackage.sqa;
import health.compact.a.CommonUtil;
import health.compact.a.LogicalStepCounter;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class AlarmHelper {
    private static final boolean c;

    /* renamed from: a, reason: collision with root package name */
    private AlarmManager f2800a;
    private PendingIntent b;
    private LiteAutoTrackDataManager d;
    private int h;
    private PendingIntent n;
    private RealTimeStepDataReportHelper o;
    private int w;
    private final List<String> e = new CopyOnWriteArrayList();
    private final List<String> p = new CopyOnWriteArrayList();
    private long t = 0;
    private long v = 0;
    private long u = 0;
    private long q = 0;
    private long r = 0;
    private int l = 0;
    private long m = 0;
    private long i = 0;
    private long f = 0;
    private long j = 0;
    private long s = 0;
    private long k = 0;
    private LogicalStepCounter g = LogicalStepCounter.c(BaseApplication.e());

    static {
        c = Build.VERSION.SDK_INT > 30 && CommonUtil.bh();
    }

    public AlarmHelper() {
        this.f2800a = null;
        Object systemService = BaseApplication.e().getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (!(systemService instanceof AlarmManager)) {
            ReleaseLogUtil.e("Track_AlarmHelper", "object is not instance of AlarmManager");
        } else {
            this.f2800a = (AlarmManager) systemService;
            this.d = new LiteAutoTrackDataManager();
        }
    }

    private void w() {
        t();
        int c2 = this.g.c();
        ReleaseLogUtil.e("Track_AlarmHelper", "get get Base steps");
        this.d.b(c2);
    }

    private void t() {
        if (this.o == null) {
            this.o = new RealTimeStepDataReportHelper(BaseApplication.e());
        }
        ReleaseLogUtil.e("Track_AlarmHelper", "forceRefreshStep enter");
        this.o.b();
    }

    private void als_(List<String> list, long j, PendingIntent pendingIntent) {
        if (c) {
            c(list);
            sqa.ekA_(this.f2800a, 2, j, pendingIntent, "Track_AlarmHelpersetAlarmByAction");
        } else {
            this.f2800a.set(2, j, pendingIntent);
        }
    }

    public void n() {
        this.q = System.currentTimeMillis();
        ReleaseLogUtil.e("Track_AlarmHelper", "setRun alarm,last Run Time", Long.valueOf(this.t));
        this.e.clear();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.t < 30) {
            this.d.e(0);
            this.e.add("SEND_ENTER_PRE_SPORT_BROADCAST");
            PendingIntent alq_ = alq_(BaseApplication.e(), "SEND_ENTER_PRE_SPORT_BROADCAST");
            this.n = alq_;
            als_(this.e, OpAnalyticsConstants.H5_LOADING_DELAY + elapsedRealtime, alq_);
        }
        if (this.i == 0) {
            this.p.add("SEND_END_RECOGNIZE_BROADCAST");
            PendingIntent alq_2 = alq_(BaseApplication.e(), "SEND_END_RECOGNIZE_BROADCAST");
            this.b = alq_2;
            als_(this.p, elapsedRealtime + 120000, alq_2);
            this.i = this.q;
        }
    }

    public void o() {
        this.h++;
        this.r = System.currentTimeMillis();
        ReleaseLogUtil.e("Track_AlarmHelper", "setHangTime alarm,last hang is ", Integer.valueOf(this.w));
        this.e.clear();
        this.e.add("SEND_STOP_BROADCAST");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = (11000 + elapsedRealtime) - this.w;
        if (this.h >= 4) {
            ReleaseLogUtil.e("Track_AlarmHelper", "max hang time" + this.h);
            j = 1000 + elapsedRealtime;
        }
        if (this.w > 11000) {
            j = 2000 + elapsedRealtime;
        }
        ReleaseLogUtil.e("Track_AlarmHelper", "triggerAtTime is", Long.valueOf(j));
        PendingIntent alq_ = alq_(BaseApplication.e(), "SEND_STOP_BROADCAST");
        this.n = alq_;
        als_(this.e, j, alq_);
    }

    public void l() {
        this.u = System.currentTimeMillis();
        ReleaseLogUtil.e("Track_AlarmHelper", "setWalk alarm,last WalkTime is ", Long.valueOf(this.v));
        this.p.clear();
        this.p.add("SEND_END_RECOGNIZE_BROADCAST");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.v;
        PendingIntent alq_ = alq_(BaseApplication.e(), "SEND_END_RECOGNIZE_BROADCAST");
        this.b = alq_;
        als_(this.p, (elapsedRealtime + 120000) - j, alq_);
    }

    public void b() {
        if (this.f2800a == null || this.n == null) {
            ReleaseLogUtil.c("Track_AlarmHelper", "mAlarmManager is null or mPendingIntent is null");
            return;
        }
        Iterator<String> it = this.e.iterator();
        while (it.hasNext()) {
            ReleaseLogUtil.e("Track_AlarmHelper", "cancel tag is ", it.next());
        }
        a(this.e);
        this.e.clear();
        this.f2800a.cancel(this.n);
    }

    public void e() {
        if (this.f2800a == null || this.b == null) {
            ReleaseLogUtil.c("Track_AlarmHelper", "cancelAlarmTasks mAlarmManager is null or mPendingIntent is null");
            return;
        }
        Iterator<String> it = this.p.iterator();
        while (it.hasNext()) {
            ReleaseLogUtil.e("Track_AlarmHelper", "cancelAlarmTasks cancel tag is ", it.next());
        }
        a(this.p);
        this.p.clear();
        this.f2800a.cancel(this.b);
    }

    public void d() {
        this.h = 0;
        this.t = 0L;
        this.v = 0L;
        this.w = 0;
        this.r = 0L;
        this.q = 0L;
        this.u = 0L;
        this.i = 0L;
        this.f = 0L;
        this.k = 0L;
        this.j = 0L;
    }

    public void c() {
        this.i = 0L;
        this.h = 0;
        this.r = 0L;
        this.q = 0L;
        this.u = 0L;
    }

    public void a() {
        this.f = 0L;
        this.q = 0L;
        this.u = 0L;
    }

    public void q() {
        long j = this.t;
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.q;
        this.t = j + (currentTimeMillis - j2);
        ReleaseLogUtil.e("Track_AlarmHelper", "settlementRunTime enter mStartRunTimestamp ", Long.valueOf(j2), " mStartRunTime ", Long.valueOf(this.t));
        this.q = 0L;
    }

    public void s() {
        ReleaseLogUtil.e("Track_AlarmHelper", "settlementHangTime enter mStartHangTimestamp ", Long.valueOf(this.r));
        this.w = (int) (this.w + (System.currentTimeMillis() - this.r));
        this.r = 0L;
    }

    public void r() {
        long j = this.v;
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.u;
        this.v = j + (currentTimeMillis - j2);
        ReleaseLogUtil.e("Track_AlarmHelper", "settlementForWalk enter mStartWalkTimestamp ", Long.valueOf(j2), " mStartWalkTime ", Long.valueOf(this.v));
        this.u = 0L;
    }

    public void k() {
        this.l = (int) (this.l + (System.currentTimeMillis() - this.m));
        this.m = 0L;
    }

    private PendingIntent alq_(Context context, String str) {
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        intent.setClass(context, DaemonService.class);
        intent.setAction(str);
        return PendingIntent.getService(context, 0, intent, 201326592);
    }

    private PendingIntent alr_(Context context, String str, int i) {
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        intent.setClass(context, DaemonService.class);
        intent.setAction(str);
        intent.putExtra("type", i);
        return PendingIntent.getService(context, 0, intent, 201326592);
    }

    private void a(List<String> list) {
        if (c) {
            ReleaseLogUtil.e("Track_AlarmHelper", "unapplyForAlarmExemption actions ", list);
            PowerKitManager.e().a(list);
        }
    }

    private void c(List<String> list) {
        if (c) {
            ReleaseLogUtil.e("Track_AlarmHelper", "applyForAlarmExemption actions ", list);
            PowerKitManager.e().e(list);
        }
    }

    public void m() {
        long currentTimeMillis = System.currentTimeMillis();
        this.m = currentTimeMillis;
        ReleaseLogUtil.e("Track_AlarmHelper", "setPauseInPreSport ", Long.valueOf(currentTimeMillis));
        this.e.clear();
        this.e.add("SEND_STOP_BROADCAST");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.l > 108000) {
            PendingIntent alq_ = alq_(BaseApplication.e(), "SEND_STOP_BROADCAST");
            this.n = alq_;
            als_(this.e, elapsedRealtime + 60000, alq_);
        }
    }

    private void v() {
        t();
        ReleaseLogUtil.e("Track_AlarmHelper", "get final steps");
        this.d.d(this.g.c());
    }

    public SportStatusDetectManager.AutoRecognizeStatus a(SportStatusDetectManager.AutoRecognizeStatus autoRecognizeStatus, TriggerSportInterface triggerSportInterface) {
        SportStatusDetectManager.AutoRecognizeStatus autoRecognizeStatus2;
        b();
        v();
        if (autoRecognizeStatus.getStatus() == SportStatusDetectManager.AutoRecognizeStatus.IDLE.getStatus()) {
            return SportStatusDetectManager.AutoRecognizeStatus.FAULT_TOLERANT;
        }
        long j = this.t / 1000;
        long j2 = (this.v / 1000) + j;
        int i = (int) ((j * 100) / j2);
        ReleaseLogUtil.e("Track_AlarmHelper", "setEndPreSport totalSportTime ", Long.valueOf(j2), " mPauseTimeInPreSport ", Integer.valueOf(this.l), " mStartRunTime ", Long.valueOf(this.t), " runPercent ", Integer.valueOf(i), " mStartWalkTime ", Long.valueOf(this.v));
        if (i > 50) {
            autoRecognizeStatus2 = SportStatusDetectManager.AutoRecognizeStatus.RUN;
            triggerSportInterface.startRunning(this.d.akm_());
        } else {
            autoRecognizeStatus2 = SportStatusDetectManager.AutoRecognizeStatus.WALK;
            triggerSportInterface.startWalking(this.d.akm_());
        }
        return autoRecognizeStatus2;
    }

    public SportStatusDetectManager.AutoRecognizeStatus b(SportStatusDetectManager.AutoRecognizeStatus autoRecognizeStatus) {
        this.e.clear();
        long j = this.t / 1000;
        long j2 = this.v / 1000;
        long j3 = j + j2;
        ReleaseLogUtil.e("Track_AlarmHelper", "setEndRecognize enter totalSportDuration ", Long.valueOf(j3), " walkSecond ", Long.valueOf(j2), " runSecond ", Long.valueOf(j));
        if (j3 == 0) {
            return SportStatusDetectManager.AutoRecognizeStatus.IDLE;
        }
        int i = (int) ((j * 100) / j3);
        int i2 = (int) ((j2 * 100) / j3);
        boolean z = i2 > 60 || i > 60;
        ReleaseLogUtil.e("Track_AlarmHelper", "setEndRecognize enter mStartRunTime ", Long.valueOf(this.t), " mStartWalkTime ", Long.valueOf(this.v), " walkPercent ", Integer.valueOf(i2), " runPercent ", Integer.valueOf(i));
        if (!z) {
            this.e.add("SEND_STOP_BROADCAST");
            long elapsedRealtime = SystemClock.elapsedRealtime();
            PendingIntent alq_ = alq_(BaseApplication.e(), "SEND_STOP_BROADCAST");
            this.n = alq_;
            als_(this.e, elapsedRealtime + 1000, alq_);
            return SportStatusDetectManager.AutoRecognizeStatus.IDLE;
        }
        this.d.e(120000);
        return autoRecognizeStatus;
    }

    public void g() {
        long currentTimeMillis = System.currentTimeMillis();
        this.q = currentTimeMillis;
        ReleaseLogUtil.e("Track_AlarmHelper", "setEnterPreSportRun enter ", Long.valueOf(currentTimeMillis), " mEnterPreSportTimestamp ", Long.valueOf(this.f));
        if (this.f == 0) {
            this.p.clear();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.p.add("SEND_EXIT_PRE_SPORT_BROADCAST");
            PendingIntent alr_ = alr_(BaseApplication.e(), "SEND_EXIT_PRE_SPORT_BROADCAST", SportStatusDetectManager.AutoRecognizeStatus.RUN.getStatus());
            this.b = alr_;
            als_(this.p, elapsedRealtime + 180000, alr_);
            this.f = this.q;
            w();
        }
    }

    public void h() {
        long currentTimeMillis = System.currentTimeMillis();
        this.u = currentTimeMillis;
        ReleaseLogUtil.e("Track_AlarmHelper", "setEnterPreSportWalk enter ", Long.valueOf(currentTimeMillis), " mEnterPreSportTimestamp ", Long.valueOf(this.f));
        if (this.f == 0) {
            this.p.clear();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.p.add("SEND_EXIT_PRE_SPORT_BROADCAST");
            PendingIntent alr_ = alr_(BaseApplication.e(), "SEND_EXIT_PRE_SPORT_BROADCAST", SportStatusDetectManager.AutoRecognizeStatus.WALK.getStatus());
            this.b = alr_;
            als_(this.p, elapsedRealtime + 180000, alr_);
            this.f = this.u;
            w();
        }
    }

    public void f() {
        if (this.f == 0) {
            this.f = System.currentTimeMillis();
        }
        this.m = System.currentTimeMillis();
    }

    public void j() {
        if (this.j == 0 || System.currentTimeMillis() - this.j > 180000) {
            this.j = System.currentTimeMillis();
            this.k = 0L;
        }
        this.s = System.currentTimeMillis();
    }

    public void p() {
        if (this.s == 0) {
            ReleaseLogUtil.c("Track_AlarmHelper", "settleStopTime error enter");
        } else {
            this.k += System.currentTimeMillis() - this.s;
            this.s = 0L;
        }
    }

    public void i() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.p.add("SEND_ENTER_PRE_STOP_BROADCAST");
        long j = this.k;
        PendingIntent alq_ = alq_(BaseApplication.e(), "SEND_ENTER_PRE_STOP_BROADCAST");
        this.b = alq_;
        als_(this.p, (elapsedRealtime + 126000) - j, alq_);
    }
}
