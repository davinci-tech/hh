package com.huawei.health.manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BadParcelableException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.icommon.SportIntensity;
import com.huawei.health.manager.reconnect.ReconnectManager;
import com.huawei.health.manager.util.TimeUtil;
import com.huawei.health.manager.util.UploadStaticAlarmUtil;
import com.huawei.health.widget.YOYOWidgetProvider;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import defpackage.jdl;
import health.compact.a.ApplicationLazyLoad;
import health.compact.a.BluetoothMonitor;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.DaemonServiceSpUtils;
import health.compact.a.DeviceUtil;
import health.compact.a.DfxMonitorCenter;
import health.compact.a.EnvironmentInfo;
import health.compact.a.HiCommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LogAnonymous;
import health.compact.a.LogSensor;
import health.compact.a.MigrationManager;
import health.compact.a.ProcessAliveMonitor;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.StepNotificationByHardWareUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class DaemonService extends Service {

    /* renamed from: a, reason: collision with root package name */
    private BroadcastReceiver f2765a;
    private BroadcastReceiver b;
    private MainProcessStatusReceiver c;
    private ExitByUserBroadcastReceiver h;
    private StepCounterRemoteProxy i = null;
    private ActivityRecognitionProxy d = null;
    private HealthDeviceOperateRemoteProxy e = null;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ApplicationLazyLoad.b();
        f();
        long e = SharedPerferenceUtils.e(this);
        ReleaseLogUtil.e("Step_DaemonService", "onCreate SHUTDOWN UTC:", Long.valueOf(e), " current:", Long.valueOf(System.currentTimeMillis()));
        if (Math.abs(System.currentTimeMillis() - e) < PreConnectManager.CONNECT_INTERNAL) {
            ReleaseLogUtil.e("Step_DaemonService", "onCreate stop self");
            Process.killProcess(Process.myPid());
        } else {
            SharedPerferenceUtils.c((Context) this, 0L);
        }
        DfxMonitorCenter.e();
        if (!"Athene4".equals(SharedPerferenceUtils.z(this))) {
            MigrationManager.e(this);
        }
        if (SharedPerferenceUtils.r(this) == 0) {
            SharedPerferenceUtils.d((Context) this, (int) TimeUtil.a(System.currentTimeMillis()));
        }
        SharedPerferenceUtils.d(this, String.valueOf(System.currentTimeMillis()));
        this.i = new StepCounterRemoteProxy(this);
        BluetoothMonitor.a().b();
        c();
        UploadStaticAlarmUtil.d(this, jdl.t(System.currentTimeMillis()) + 86100000 + UploadStaticAlarmUtil.b);
        UploadStaticAlarmUtil.b(this, jdl.t(System.currentTimeMillis()) + 84000000 + UploadStaticAlarmUtil.e);
        h();
        if (SharedPerferenceUtils.ag(this)) {
            DeviceUtil.fbV_(this, true, null);
        }
        this.b = new SportIntensityReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("DOWNLOAD_INTENSITY_FILE");
        BroadcastManagerUtil.bFE_(BaseApplication.e(), this.b, intentFilter);
        this.f2765a = new LoginSuccessReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.huawei.plugin.account.login");
        BroadcastManagerUtil.bFE_(BaseApplication.e(), this.f2765a, intentFilter2);
        if (SystemInfo.h()) {
            YOYOWidgetProvider.b(this);
            YOYOWidgetProvider.d(this);
            ReleaseLogUtil.e("Step_DaemonService", "onCreate HonorYoyoWidgetProvider");
        }
        if (!CommonUtil.bh()) {
            ThirdLoginDataStorageUtil.getAccessTokenFromDb();
        }
        e();
        g();
        ReconnectManager.c().e();
        j();
    }

    private void j() {
        if (this.c == null) {
            this.c = new MainProcessStatusReceiver();
        }
        BroadcastManager.wl_(this.c);
    }

    private void f() {
        if (EnvironmentInfo.j()) {
            Context e = BaseApplication.e();
            if (TextUtils.isEmpty(DaemonServiceSpUtils.e(e))) {
                DaemonServiceSpUtils.d(e, !(DaemonServiceSpUtils.d(e) || SharedPerferenceUtils.ag(e)));
            }
        }
    }

    class SportIntensityReceiver extends BroadcastReceiver {
        private SportIntensityReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ReleaseLogUtil.e("Step_DaemonService", "SportIntensityReceiver received");
            if (intent == null || !"DOWNLOAD_INTENSITY_FILE".equals(intent.getAction())) {
                return;
            }
            SportIntensity.a(DaemonService.this.getApplicationContext()).d();
        }
    }

    class LoginSuccessReceiver extends BroadcastReceiver {
        private LoginSuccessReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ReleaseLogUtil.e("Step_DaemonService", "LoginSuccessReceiver received");
            if (intent == null) {
                ReleaseLogUtil.c("Step_DaemonService", "LoginSuccessReceiver intent is null");
            } else if ("com.huawei.plugin.account.login".equals(intent.getAction()) && SystemInfo.h()) {
                YOYOWidgetProvider.b(BaseApplication.e());
                YOYOWidgetProvider.d(BaseApplication.e());
                ReleaseLogUtil.e("Step_DaemonService", "LoginSuccessReceiver HonorYoyoWidgetProvider");
            }
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ReleaseLogUtil.e("Step_DaemonService", "onDestroy");
        BluetoothMonitor.a().e();
        if (this.b != null) {
            BroadcastManagerUtil.bFK_(BaseApplication.e(), this.b);
            this.b = null;
        }
        if (this.f2765a != null) {
            BroadcastManagerUtil.bFK_(BaseApplication.e(), this.f2765a);
            this.f2765a = null;
        }
        if (SystemInfo.h()) {
            YOYOWidgetProvider.b(this);
            ReleaseLogUtil.e("Step_DaemonService", "unregisterSportData HonorYoyoWidgetProvider");
        }
        this.i.release();
        Process.killProcess(Process.myPid());
        if (this.h != null) {
            LogUtil.a("Step_DaemonService", "onDestroy unregisterReceiverPackage()");
            BroadcastManagerUtil.bFK_(getApplicationContext(), this.h);
            this.h = null;
        }
        if (this.c != null) {
            LogUtil.a("Step_DaemonService", "onDestroy unregister mMainProcessStatusReceiver");
            BroadcastManager.wz_(this.c);
            this.c = null;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (intent == null) {
            return this.i;
        }
        ReleaseLogUtil.e("Step_DaemonService", "onBind ", intent.getAction());
        String action = intent.getAction();
        if ("com.huawei.health.device.oper".equals(action)) {
            LogUtil.c("Step_DaemonService", "intent.getAction === ", action);
            if (this.e == null) {
                this.e = new HealthDeviceOperateRemoteProxy(this);
            }
            return this.e;
        }
        return this.i;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            super.onStartCommand(intent, i, i2);
        } catch (BadParcelableException e) {
            LogUtil.b("Step_DaemonService", "onStartCommand BadParcelableException = ", e.getMessage());
        } catch (ClassCastException e2) {
            LogUtil.b("Step_DaemonService", "onStartCommand ClassCastException = ", e2.getMessage());
        } catch (Exception e3) {
            ReleaseLogUtil.c("Step_DaemonService", "onStartCommand Exception = ", LogAnonymous.b((Throwable) e3));
        }
        if (intent != null && this.i != null) {
            if (intent.hasExtra("THE_MAIN_UI_START_DAEMON_SERVICE")) {
                ReleaseLogUtil.e("Step_DaemonService", "onStartCommand from ui");
                akg_(intent);
            } else if (intent.hasExtra("flushLog")) {
                ReleaseLogUtil.e("Step_DaemonService", "onStartCommand flush log");
                aki_(intent);
            } else if (ThirdLoginDataStorageUtil.START_DAEMON_FOR_REFRESH_TOKEN.equals(intent.getAction())) {
                ReleaseLogUtil.e("Step_DaemonService", "onStartCommand refresh token");
                if (Boolean.parseBoolean(KeyValDbManager.b(BaseApplication.e()).e("key_wether_to_auth")) && LoginInit.getInstance(BaseApplication.e()).getIsLogined()) {
                    LoginInit.getInstance(BaseApplication.e()).refreshAccessToken(null);
                }
            } else if (!"com.huawei.daemonservice".equals(intent.getAction())) {
                ReleaseLogUtil.e("Step_DaemonService", "onStartCommand else:", intent.getAction());
            } else {
                if (!akk_(intent) && !akj_(intent)) {
                    ReleaseLogUtil.e("Step_DaemonService", "onStartCommand ACTION_START_DAEMON_SERVICE else");
                }
                ReleaseLogUtil.e("Step_DaemonService", "onStartCommand start sync");
                return 1;
            }
            if (!akh_(intent)) {
                this.i.dealBroadcastEvents(intent);
            }
            ReconnectManager.c().b(this);
            return 1;
        }
        ReleaseLogUtil.d("Step_DaemonService", "onStartCommand intent/mStepCounterProxy is null");
        return 1;
    }

    private boolean akk_(Intent intent) {
        if (!intent.hasExtra("startSyncData")) {
            LogUtil.a("Step_DaemonService", "not startSyncData");
            return false;
        }
        LogUtil.a("Step_DaemonService", "startSyncData");
        DeviceUtil.b();
        return true;
    }

    private boolean akj_(Intent intent) {
        if (!intent.hasExtra("phoneServiceStarted")) {
            LogUtil.a("Step_DaemonService", "not phoneServiceStarted");
            return false;
        }
        LogUtil.a("Step_DaemonService", "phoneServiceStarted");
        ReconnectManager.c().d(this);
        return true;
    }

    private void akg_(Intent intent) {
        if (!intent.getBooleanExtra("THE_MAIN_UI_START_DAEMON_SERVICE", false) || DaemonServiceSpUtils.d(this)) {
            return;
        }
        b();
    }

    private void b() {
        ProcessAliveMonitor.a(this).b();
        SharedPerferenceUtils.c(this);
        DaemonServiceSpUtils.c(this);
    }

    private void aki_(Intent intent) {
        try {
            if (intent.getBooleanExtra("flushLog", false)) {
                a();
            }
        } catch (ClassCastException e) {
            LogUtil.b("Step_DaemonService", "isNeedFlushLog exception = ", e.getMessage());
        } catch (Exception e2) {
            LogUtil.b("Step_DaemonService", "isNeedFlushLog Exception = ", LogAnonymous.b((Throwable) e2));
        }
    }

    private void a() {
        LogUtil.b();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.huawei.health.manager.DaemonService.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.g();
            }
        }, 3000L);
        LogSensor.b();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.huawei.health.manager.DaemonService.2
            @Override // java.lang.Runnable
            public void run() {
                LogSensor.c();
            }
        }, 3000L);
    }

    private void c() {
        ThreadPoolManager.d().d("setArModuleAbility", new Runnable() { // from class: com.huawei.health.manager.DaemonService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DaemonService.this.d();
            }
        });
        ActivityRecognitionProxy activityRecognitionProxy = new ActivityRecognitionProxy(this);
        this.d = activityRecognitionProxy;
        activityRecognitionProxy.e();
    }

    private boolean akh_(Intent intent) {
        ActivityRecognitionProxy activityRecognitionProxy = this.d;
        if (activityRecognitionProxy != null) {
            return activityRecognitionProxy.ajY_(intent);
        }
        return false;
    }

    private void h() {
        Intent intent = new Intent(this, (Class<?>) PreDaemonService.class);
        intent.setPackage(getPackageName());
        try {
            stopService(intent);
        } catch (IllegalStateException | SecurityException e) {
            LogUtil.b("Step_DaemonService", LogAnonymous.b(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public void d() {
        if ((CommonUtil.bh() && CommonUtil.ch()) || CommonUtil.bf()) {
            if (StepNotificationByHardWareUtils.a("sensor.activity_recognition")) {
                KeyValDbManager.b(BaseApplication.e()).d("SUPPORT_AR_ABILITY", "1", null);
            } else {
                KeyValDbManager.b(BaseApplication.e()).d("SUPPORT_AR_ABILITY", "0", null);
            }
        }
    }

    private void e() {
        LogUtil.a("Step_DaemonService", "enter cancelOneHourSyncAlarm.");
        AlarmManager xy_ = CommonUtil.xy_();
        if (xy_ != null) {
            xy_.cancel(PendingIntent.getBroadcast(BaseApplication.e(), 0, new Intent("com.huawei.hihealth.action_one_hour_sync"), AppRouterExtras.COLDSTART));
            ReleaseLogUtil.e("Step_DaemonService", "cancelOneHourSyncAlarm success.");
        }
    }

    static class ExitByUserBroadcastReceiver extends BroadcastReceiver {
        private ExitByUserBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !"com.huawei.health.user.exit".equals(intent.getAction())) {
                return;
            }
            LogUtil.a("ExitByUserBroadcastReceiver", "### exit by user");
            Process.killProcess(Process.myPid());
        }
    }

    static class MainProcessStatusReceiver extends BroadcastReceiver {
        private MainProcessStatusReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra(HiCommonUtil.b);
            boolean booleanExtra = intent.getBooleanExtra(HiCommonUtil.d, false);
            LogUtil.a("MainProcessStatusReceiver", "processName: ", stringExtra, " isRunning: ", Boolean.valueOf(booleanExtra), " time: ", Long.valueOf(intent.getLongExtra("time", 0L)));
            if (HiCommonUtil.e.equals(stringExtra)) {
                HiCommonUtil.f13122a = booleanExtra;
            }
        }
    }

    private void g() {
        LogUtil.a("Step_DaemonService", "registerExitBroadcast enter!");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.health.user.exit");
        ExitByUserBroadcastReceiver exitByUserBroadcastReceiver = new ExitByUserBroadcastReceiver();
        this.h = exitByUserBroadcastReceiver;
        BroadcastManagerUtil.bFE_(this, exitByUserBroadcastReceiver, intentFilter);
    }
}
