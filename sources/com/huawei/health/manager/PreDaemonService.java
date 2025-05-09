package com.huawei.health.manager;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.health.fwk.BaseStepCounter;
import com.huawei.health.manager.util.TimeUtil;
import health.compact.a.DaemonServiceSpUtils;
import health.compact.a.EmuiBuild;
import health.compact.a.HarmonyBuild;
import health.compact.a.HnStandStepCounter;
import health.compact.a.HwStandStepCounter;
import health.compact.a.LogUtil;
import health.compact.a.LogicalStepCounter;
import health.compact.a.MagicBuild;
import health.compact.a.ProcessAliveMonitor;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.StepCounterSupport;

/* loaded from: classes.dex */
public class PreDaemonService extends Service implements SensorEventListener {
    private Context e;
    private SensorManager f = null;
    private int d = -1;
    private boolean b = false;
    private BaseStepCounter c = null;

    /* renamed from: a, reason: collision with root package name */
    private MyServiceConnection f2778a = new MyServiceConnection();

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr;
        if (sensorEvent == null || sensorEvent.sensor.getType() != 19 || (fArr = sensorEvent.values) == null || fArr.length == 0) {
            return;
        }
        int i = (int) fArr[0];
        LogUtil.c("Step_PreDaemonService", "onSensorChanged: ", Integer.valueOf(i));
        if (this.d == -1) {
            this.d = i;
        }
        if (i - this.d > 10) {
            e();
            Intent intent = new Intent(this, (Class<?>) DaemonService.class);
            intent.setPackage(this.e.getPackageName());
            LogicalStepCounter.c(this.e).c(i - this.d);
            try {
                startService(intent);
            } catch (IllegalStateException e) {
                LogUtil.a("Step_PreDaemonService", "PreDaemonService onSensorChanged", e.getMessage());
            } catch (SecurityException e2) {
                LogUtil.a("Step_PreDaemonService", "PreDaemonService onSensorChanged", e2.getMessage());
            }
            stopSelf();
        }
    }

    private void e() {
        ProcessAliveMonitor.a(this).b();
        SharedPerferenceUtils.c(this);
        DaemonServiceSpUtils.c(this);
    }

    @Override // android.app.Service
    public void onCreate() {
        LogUtil.c("Step_PreDaemonService", "init ...");
        super.onCreate();
        Context applicationContext = getApplicationContext();
        this.e = applicationContext;
        if (SharedPerferenceUtils.p(applicationContext)) {
            if (SharedPerferenceUtils.r(this) == 0) {
                SharedPerferenceUtils.d((Context) this, (int) TimeUtil.a(System.currentTimeMillis()));
            }
            if (!a()) {
                ReleaseLogUtil.a("Step_PreDaemonService", "this system not support pre Step.");
                return;
            }
            if (Build.VERSION.SDK_INT >= 29) {
                ReleaseLogUtil.a("Step_PreDaemonService", "this system not support pre Step.");
                return;
            }
            Object systemService = getSystemService("sensor");
            if (systemService instanceof SensorManager) {
                LogUtil.c("Step_PreDaemonService", "onCreate object is not instanceof SensorManager");
                SensorManager sensorManager = (SensorManager) systemService;
                this.f = sensorManager;
                try {
                    ReleaseLogUtil.b("Step_PreDaemonService", "onCreate registerListener ", Boolean.valueOf(sensorManager.registerListener(this, sensorManager.getDefaultSensor(19), 0)));
                } catch (RuntimeException unused) {
                    ReleaseLogUtil.c("Step_PreDaemonService", "onCreate happened exception");
                }
            }
            if (a(this.e)) {
                return;
            }
            Intent aaC_ = StepCounterSupport.aaC_(this);
            try {
                if (aaC_ != null) {
                    this.b = bindService(aaC_, this.f2778a, 1);
                } else {
                    LogUtil.c("Step_PreDaemonService", "intent =null");
                }
            } catch (SecurityException e) {
                LogUtil.c("Step_PreDaemonService", e.getMessage());
            }
        }
    }

    public static boolean a() {
        return EmuiBuild.e() || EmuiBuild.d || HarmonyBuild.d;
    }

    private boolean a(Context context) {
        return SystemInfo.b(true) || !d(context);
    }

    private boolean d(Context context) {
        if (context == null) {
            LogUtil.e("Step_PreDaemonService", "isTelephonyEnable() context is null!!");
            return false;
        }
        Object systemService = context.getSystemService("phone");
        return (systemService instanceof TelephonyManager) && ((TelephonyManager) systemService).getPhoneType() != 0;
    }

    @Override // android.app.Service
    public void onDestroy() {
        LogUtil.c("Step_PreDaemonService", "onDestroy");
        super.onDestroy();
        if (SharedPerferenceUtils.p(this.e)) {
            if (!a()) {
                ReleaseLogUtil.a("Step_PreDaemonService", "this system not support pre Step.");
                return;
            }
            SensorManager sensorManager = this.f;
            if (sensorManager != null) {
                sensorManager.unregisterListener(this);
            }
            if (this.b) {
                unbindService(this.f2778a);
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.c("Step_PreDaemonService", "Not yet implemented");
        return null;
    }

    class MyServiceConnection implements ServiceConnection {
        private MyServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            String str = Build.BRAND;
            PreDaemonService.this.c = new HwStandStepCounter(iBinder);
            if ("HONOR".equalsIgnoreCase(str) && MagicBuild.f13130a && MagicBuild.d >= 33) {
                PreDaemonService.this.c = new HnStandStepCounter(iBinder);
            }
            PreDaemonService.this.c.openStepCounter();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (PreDaemonService.this.c != null) {
                PreDaemonService.this.c = null;
                ReleaseLogUtil.b("Step_PreDaemonService", "MyServiceConnection onServiceDisconnected ");
            }
        }
    }
}
