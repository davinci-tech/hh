package health.compact.a;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.SystemClock;
import com.huawei.health.icommon.HwHealthSensorEventListener;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;

/* loaded from: classes.dex */
public class StandStepCounter implements HwHealthSensorEventListener {
    private static boolean b = false;

    /* renamed from: a, reason: collision with root package name */
    private Context f13136a;
    private SensorManager c;
    private int d;
    private int e;
    private b g;

    public StandStepCounter(Context context) {
        this(context, false);
    }

    public StandStepCounter(Context context, boolean z) {
        this.c = null;
        this.e = -1;
        this.d = -1;
        this.f13136a = context;
        this.g = new b(context);
        if (z) {
            return;
        }
        Object systemService = this.f13136a.getSystemService("sensor");
        if (systemService instanceof SensorManager) {
            this.c = (SensorManager) systemService;
        } else {
            com.huawei.hwlogsmodel.LogUtil.h("Step_StandStepCnt", "StandStepCounter object type is not SensorManager.");
        }
    }

    public void c() {
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("R_Step_StandStepCnt", "startStepCounter to enter");
        if (!StepCounterSupportUtils.e(this.f13136a)) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_StandStepCnt", " startStepCounter failed with cant step counter.");
            return;
        }
        try {
            SensorManager sensorManager = this.c;
            if (sensorManager != null) {
                boolean registerListener = sensorManager.registerListener(this, sensorManager.getDefaultSensor(19), 0);
                a(registerListener);
                health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_StandStepCnt", "startStepCounter registerListener isSensorEnabled = ", Boolean.valueOf(registerListener));
            }
        } catch (RuntimeException unused) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c("Step_StandStepCnt", "startStepCounter happened exception");
        }
    }

    public void b() {
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("R_Step_StandStepCnt", "stopStepCounter to enter");
        SensorManager sensorManager = this.c;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
            a(false);
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_StandStepCnt", "onSensorChanged SensorEvent is null.");
            return;
        }
        if (sensorEvent.sensor == null || sensorEvent.sensor.getType() != 19) {
            return;
        }
        if (sensorEvent.values != null && sensorEvent.values.length > 0) {
            int i = (int) sensorEvent.values[0];
            a(i, 0);
            this.e = i;
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.h("Step_StandStepCnt", "index is out of Bounds int event.values.");
    }

    @Override // com.huawei.health.icommon.HwHealthSensorEventListener
    public void dataReport(int i, boolean z) {
        a(i, 1);
        if (z) {
            this.e = i;
        } else {
            this.d = i;
        }
    }

    @Override // com.huawei.health.icommon.HwHealthSensorEventListener
    public void notifyStepCounterError(boolean z) {
        if (z) {
            this.e = -1;
        } else {
            this.d = -1;
        }
    }

    public void a(int i, int i2) {
        int max = Math.max(Math.max(i, this.e), this.d);
        if (this.g.b(max)) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_StandStepCnt", "wrong data,lose it.");
        } else {
            LogicalStepCounter.c(this.f13136a).b(System.currentTimeMillis(), max, i2);
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
        com.huawei.hwlogsmodel.LogUtil.c("Step_StandStepCnt", "onAccuracyChanged");
    }

    public void e() {
        if (this.c != null) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_StandStepCnt", "flush sensor data");
            this.c.flush(this);
        }
    }

    public void d() {
        health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_StandStepCnt", "reStartStepCounter");
        if (!StepCounterSupportUtils.e(this.f13136a)) {
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e("Step_StandStepCnt", " restartStepCounter failed with cant step counter.");
        } else {
            b();
            c();
        }
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private long f13137a;
        private Context b;
        private int d;
        private int e;

        private b(Context context) {
            this.f13137a = 0L;
            this.d = -1;
            this.e = 0;
            this.b = context;
            this.f13137a = SystemClock.elapsedRealtime();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b(int i) {
            String[] x;
            if (this.d == -1 && (x = SharedPerferenceUtils.x(this.b)) != null && x.length >= 2) {
                try {
                    this.d = Integer.parseInt(x[1]);
                } catch (NumberFormatException e) {
                    com.huawei.hwlogsmodel.LogUtil.a("Step_StandStepCnt", " isAvaliableReport Exception", e.getMessage());
                }
            }
            com.huawei.hwlogsmodel.LogUtil.c("Step_StandStepCnt", "isAvaliableReport baseStep = ", Integer.valueOf(this.d), " SensorChangedstep = ", Integer.valueOf(i));
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Long valueOf = Long.valueOf(elapsedRealtime - this.f13137a);
            if (valueOf.longValue() > PreConnectManager.CONNECT_INTERNAL) {
                if (i - this.d > Math.round(valueOf.floatValue() / 10000.0f) * 84) {
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_STEP_COUNT_85070014.value(), 1001);
                }
            }
            this.f13137a = elapsedRealtime;
            int i2 = this.d;
            if (i < i2 || i - i2 > 100000) {
                int i3 = this.e + 1;
                this.e = i3;
                com.huawei.hwlogsmodel.LogUtil.a("Step_StandStepCnt", "isAvaliableReport wrong data count = ", Integer.valueOf(i3), " baseStep = ", Integer.valueOf(this.d), " wrongstep = ", Integer.valueOf(i));
                if (this.e <= 3) {
                    return true;
                }
                this.e = 0;
                this.d = i;
                return false;
            }
            if (this.e != 0) {
                this.e = 0;
            }
            this.d = i;
            return false;
        }
    }

    public static boolean a() {
        return b;
    }

    private static void a(boolean z) {
        b = z;
    }
}
