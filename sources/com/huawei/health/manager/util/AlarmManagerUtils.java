package com.huawei.health.manager.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.huawei.health.manager.DaemonService;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.sqa;
import health.compact.a.EnvironmentInfo;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class AlarmManagerUtils {

    /* renamed from: a, reason: collision with root package name */
    private final StateHandleBean f2801a;
    private boolean b;
    private Context c;
    private final boolean d;
    private AlarmManager e;
    private final StateHandleBean f;
    private int h;

    public enum SportTypeUtils {
        STATE_IDLE,
        STATE_ENTER_RUNNING,
        STATE_EXIT_RUNNING,
        STATE_ENTER_BICYCLE,
        STATE_EXIT_BICYCLE,
        STATE_ENTER_WALKING,
        STATE_EXIT_WALKING
    }

    public AlarmManagerUtils(Context context) {
        this.e = null;
        this.c = null;
        StateHandleBean stateHandleBean = new StateHandleBean(257);
        this.f = stateHandleBean;
        StateHandleBean stateHandleBean2 = new StateHandleBean(258);
        this.f2801a = stateHandleBean2;
        this.h = 180;
        this.d = EnvironmentInfo.r();
        this.b = false;
        if (context != null) {
            this.c = context;
            Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            if (!(systemService instanceof AlarmManager)) {
                LogUtil.a("Track_AlarmManagerUtils", "object is not instance of AlarmManager");
                return;
            }
            this.e = (AlarmManager) systemService;
            stateHandleBean2.a(context);
            stateHandleBean.a(context);
        }
    }

    public void c(SportTypeUtils sportTypeUtils) {
        StateHandleBean stateHandleBean;
        if (sportTypeUtils == SportTypeUtils.STATE_ENTER_RUNNING || sportTypeUtils == SportTypeUtils.STATE_EXIT_RUNNING) {
            stateHandleBean = this.f2801a;
        } else if (sportTypeUtils != SportTypeUtils.STATE_ENTER_WALKING && sportTypeUtils != SportTypeUtils.STATE_EXIT_WALKING) {
            return;
        } else {
            stateHandleBean = this.f;
        }
        if (stateHandleBean.d() == stateHandleBean.b()) {
            if (stateHandleBean.a() < SystemClock.elapsedRealtime()) {
                stateHandleBean.b(SportTypeUtils.STATE_IDLE);
                LogUtil.a("Track_AlarmManagerUtils", "I have deal exit cmd enter idle");
            }
        }
        LogUtil.a("Track_AlarmManagerUtils", "setCurrentSportType cur ", stateHandleBean.d(), " new ", sportTypeUtils);
        if (stateHandleBean.d() == SportTypeUtils.STATE_IDLE && sportTypeUtils != stateHandleBean.c()) {
            LogUtil.a("Track_AlarmManagerUtils", "idle now only support running or walking");
        } else {
            b(stateHandleBean, sportTypeUtils);
        }
    }

    private void b(StateHandleBean stateHandleBean, SportTypeUtils sportTypeUtils) {
        LogUtil.a("Track_AlarmManagerUtils", "handleCurrentSportType mCurrentSportType is ", stateHandleBean.d());
        if (stateHandleBean.d() == SportTypeUtils.STATE_IDLE) {
            c(stateHandleBean, sportTypeUtils);
        } else if (stateHandleBean.d() == stateHandleBean.c()) {
            a(stateHandleBean, true, sportTypeUtils);
        } else if (stateHandleBean.d() == stateHandleBean.b()) {
            a(stateHandleBean, false, sportTypeUtils);
        }
    }

    private void a(StateHandleBean stateHandleBean, boolean z, SportTypeUtils sportTypeUtils) {
        LogUtil.a("Track_AlarmManagerUtils", "handleNewState sportType is ", sportTypeUtils, "isEnter is", Boolean.valueOf(z));
        if (sportTypeUtils == stateHandleBean.c()) {
            if (z) {
                a(sportTypeUtils);
                return;
            } else {
                e(stateHandleBean, sportTypeUtils);
                return;
            }
        }
        if (sportTypeUtils == stateHandleBean.b() && z) {
            a(stateHandleBean, sportTypeUtils);
        }
    }

    private void e(StateHandleBean stateHandleBean, SportTypeUtils sportTypeUtils) {
        LogUtil.a("Track_AlarmManagerUtils", "handExitStateEnter sportType is ", sportTypeUtils);
        b(sportTypeUtils, stateHandleBean);
    }

    private void c(StateHandleBean stateHandleBean, SportTypeUtils sportTypeUtils) {
        LogUtil.a("Track_AlarmManagerUtils", "handleStateIdle sportType is ", sportTypeUtils);
        b(sportTypeUtils, stateHandleBean);
    }

    private void a(StateHandleBean stateHandleBean, SportTypeUtils sportTypeUtils) {
        b(sportTypeUtils, stateHandleBean);
    }

    private void a(SportTypeUtils sportTypeUtils) {
        LogUtil.a("Track_AlarmManagerUtils", "handleEnterStateEnter sportType is", sportTypeUtils);
    }

    private void b(SportTypeUtils sportTypeUtils, StateHandleBean stateHandleBean) {
        stateHandleBean.d(sportTypeUtils);
        LogUtil.a("Track_AlarmManagerUtils", "sendBroadcast mNewSportType is ", sportTypeUtils);
        if (sportTypeUtils == stateHandleBean.c()) {
            d(stateHandleBean, stateHandleBean.h() * 1000);
        } else if (sportTypeUtils == stateHandleBean.b()) {
            c(stateHandleBean, stateHandleBean.i() * 1000);
        } else {
            LogUtil.a("Track_AlarmManagerUtils", "sportType have no match");
        }
    }

    public void e(int i, int i2) {
        this.f2801a.b(i);
        this.f2801a.d(i2);
    }

    private void d(StateHandleBean stateHandleBean, int i) {
        boolean z;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        List<String> singletonList = Collections.singletonList(stateHandleBean.l());
        if (stateHandleBean.d() == stateHandleBean.b()) {
            if (stateHandleBean.a() > elapsedRealtime) {
                LogUtil.a("Track_AlarmManagerUtils", "cancel stop ", Long.valueOf(stateHandleBean.a()), " current ", Long.valueOf(elapsedRealtime));
                a(singletonList);
                d(stateHandleBean);
                stateHandleBean.e(0L);
                z = false;
            }
            z = true;
        } else {
            if (stateHandleBean.d() != SportTypeUtils.STATE_IDLE) {
                LogUtil.a("Track_AlarmManagerUtils", "mCurrentSportType have no match");
                z = false;
            }
            z = true;
        }
        LogUtil.a("Track_AlarmManagerUtils", "sendStartBroadcast needSetAlarm ", Boolean.valueOf(z));
        if (z) {
            LogUtil.a("Track_AlarmManagerUtils", "sendStartBroadcast mNewSportType ", stateHandleBean.g());
            this.b = false;
            long j = elapsedRealtime + i;
            LogUtil.a("Track_AlarmManagerUtils", "sendStartBroadcast triggerAtTime ", Long.valueOf(j));
            PendingIntent alu_ = stateHandleBean.alu_();
            if (this.d) {
                e(singletonList);
                sqa.ekA_(this.e, 2, j, alu_, "Track_AlarmManagerUtilssendStartBroadcast");
            } else {
                this.e.set(2, j, alu_);
            }
            stateHandleBean.c(j);
            stateHandleBean.b(stateHandleBean.g());
        }
    }

    private void e(List<String> list) {
        if (this.d) {
            ReleaseLogUtil.e("Track_AlarmManagerUtils", "applyForAlarmExemption actions ", list);
            PowerKitManager.e().e(list);
        }
    }

    private void a(List<String> list) {
        if (this.d) {
            ReleaseLogUtil.e("Track_AlarmManagerUtils", "unapplyForAlarmExemption actions ", list);
            PowerKitManager.e().a(list);
        }
    }

    private void c(StateHandleBean stateHandleBean, int i) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        List<String> singletonList = Collections.singletonList(stateHandleBean.n());
        if (elapsedRealtime < stateHandleBean.e()) {
            LogUtil.a("Track_AlarmManagerUtils", "cancel start dest:  ", Long.valueOf(stateHandleBean.e()), " curr: ", Long.valueOf(elapsedRealtime));
            a(singletonList);
            c(stateHandleBean);
            return;
        }
        this.b = true;
        LogUtil.a("Track_AlarmManagerUtils", "sendStopBroadcast mNewSportType ", stateHandleBean.g());
        long j = elapsedRealtime + i;
        LogUtil.a("Track_AlarmManagerUtils", "sendStopBroadcast triggerAtTime ", Long.valueOf(j));
        PendingIntent alv_ = stateHandleBean.alv_();
        if (this.d) {
            e(singletonList);
            sqa.ekA_(this.e, 2, j, alv_, "Track_AlarmManagerUtilssendStopBroadcast");
        } else {
            this.e.set(2, j, alv_);
        }
        stateHandleBean.e(j);
        stateHandleBean.b(stateHandleBean.g());
    }

    public void b() {
        if (this.b) {
            return;
        }
        LogUtil.a("Track_AlarmManagerUtils", "heartBeatSendStopBroadcast");
        StateHandleBean stateHandleBean = this.f2801a;
        c(stateHandleBean, stateHandleBean.i() * 1000);
    }

    private void d(StateHandleBean stateHandleBean) {
        AlarmManager alarmManager = this.e;
        if (alarmManager != null) {
            this.b = false;
            alarmManager.cancel(stateHandleBean.alv_());
            LogUtil.a("Track_AlarmManagerUtils", "cancel(mStopPendingIntent)");
        }
        stateHandleBean.b(stateHandleBean.g());
    }

    private void c(StateHandleBean stateHandleBean) {
        AlarmManager alarmManager = this.e;
        if (alarmManager != null) {
            alarmManager.cancel(stateHandleBean.alu_());
            LogUtil.a("Track_AlarmManagerUtils", "cancel(mStartWalkPendingIntent)");
        }
        stateHandleBean.b(stateHandleBean.g());
    }

    public static class StateHandleBean {

        /* renamed from: a, reason: collision with root package name */
        private final int f2802a;
        private SportTypeUtils e;
        private PendingIntent f;
        private SportTypeUtils d = SportTypeUtils.STATE_IDLE;
        private int i = 180;
        private int j = 180;
        private PendingIntent g = null;
        private long c = 0;
        private long b = 0;

        public StateHandleBean(int i) {
            this.f2802a = i;
            o();
        }

        private void o() {
            int i = this.f2802a;
            if (i == 258) {
                this.i = 40;
            } else if (i == 257) {
                this.i = 180;
            }
        }

        public SportTypeUtils b() {
            if (this.f2802a == 258) {
                return SportTypeUtils.STATE_EXIT_RUNNING;
            }
            return SportTypeUtils.STATE_EXIT_WALKING;
        }

        public SportTypeUtils c() {
            if (this.f2802a == 258) {
                return SportTypeUtils.STATE_ENTER_RUNNING;
            }
            return SportTypeUtils.STATE_ENTER_WALKING;
        }

        public void a(Context context) {
            e(context);
            d(context);
        }

        public SportTypeUtils d() {
            return this.d;
        }

        public void b(SportTypeUtils sportTypeUtils) {
            this.d = sportTypeUtils;
        }

        public int h() {
            return this.i;
        }

        public void b(int i) {
            this.i = i;
        }

        public SportTypeUtils g() {
            return this.e;
        }

        public void d(SportTypeUtils sportTypeUtils) {
            this.e = sportTypeUtils;
        }

        public PendingIntent alv_() {
            return this.f;
        }

        public int i() {
            return this.j;
        }

        public void d(int i) {
            this.j = i;
        }

        public long a() {
            return this.c;
        }

        public void e(long j) {
            this.c = j;
        }

        public long e() {
            return this.b;
        }

        public void c(long j) {
            this.b = j;
        }

        public PendingIntent alu_() {
            return this.g;
        }

        private void e(Context context) {
            this.g = alt_(context, l());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String l() {
            return this.f2802a == 258 ? "SEND_START_BROADCAST" : "SEND_START_WALK_BROADCAST";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String n() {
            return this.f2802a == 258 ? "SEND_STOP_BROADCAST" : "SEND_STOP_WALK_BROADCAST";
        }

        private void d(Context context) {
            this.f = alt_(context, n());
        }

        private PendingIntent alt_(Context context, String str) {
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setClass(context, DaemonService.class);
            intent.setAction(str);
            return PendingIntent.getService(context, 0, intent, 201326592);
        }
    }
}
