package defpackage;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.huawei.treadmill.CallBackToReportStepsOrEvent;
import com.huawei.treadmill.JniTest;

/* loaded from: classes6.dex */
public class njm implements SensorEventListener {
    private static volatile njm d;
    private Context e;
    private Runnable f;
    private CallBackToReportStepsOrEvent g;
    private Handler h;
    private JniTest j;
    private boolean m;
    private SensorManager o;
    private boolean i = false;
    private boolean c = false;

    /* renamed from: a, reason: collision with root package name */
    private int f15332a = 1000;
    private int l = 0;
    private int k = 0;
    private int s = -1;
    private int n = 300;
    private int b = 1000;
    private int r = 0;

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    private njm(Context context, boolean z) {
        this.e = context;
        this.m = z;
    }

    public static njm a(Context context, boolean z) {
        synchronized (njm.class) {
            if (context == null) {
                Log.i("Track_stepCounter", "getInstance of StepsCounter, context is null, return null");
                return null;
            }
            if (d == null) {
                d = new njm(context, z);
                Log.i("Track_stepCounter", "getInstance of StepsCounter, instance == null, create a new one");
            } else {
                d.e = context;
                d.m = z;
                Log.i("Track_stepCounter", "getInstance of StepsCounter, instance is not null, no need to create a new one, just refresh context and stop last counter");
                d.d();
            }
            return d;
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (!this.c || this.j == null) {
            return;
        }
        if (sensorEvent.sensor.getType() == 1) {
            float[] fArr = sensorEvent.values;
            int[] processAlg = this.j.processAlg(new int[]{(int) ((fArr[0] * 2048.0f) / 9.8d), (int) ((fArr[1] * 2048.0f) / 9.8d), (int) ((fArr[2] * 2048.0f) / 9.8d)}, new int[]{this.b, this.n, this.l, this.r});
            if (processAlg.length != 2 || 1 == processAlg[0]) {
                CallBackToReportStepsOrEvent callBackToReportStepsOrEvent = this.g;
                if (callBackToReportStepsOrEvent != null) {
                    callBackToReportStepsOrEvent.onUpdateStepsOrEvent(-1, -1);
                }
                d();
                return;
            }
            this.k += processAlg[1];
            return;
        }
        if (this.m || sensorEvent.sensor.getType() != 19) {
            return;
        }
        Log.d("Track_stepCounter", " get a sensorStep value from TYPE_STEP_COUNTER");
        if (sensorEvent.values[0] > this.l) {
            this.l = Math.round(sensorEvent.values[0]);
        }
    }

    public boolean a(njl njlVar, CallBackToReportStepsOrEvent callBackToReportStepsOrEvent, int i) {
        Log.i("Track_stepCounter", "just into initAndStartCountSteps");
        if (this.i) {
            Log.i("Track_stepCounter", "counter is running already, return false");
            return false;
        }
        if (this.e == null) {
            Log.i("Track_stepCounter", "context is null, return false");
            this.o = null;
            this.j = null;
            this.g = null;
            this.h = null;
            this.f = null;
            return false;
        }
        if (!e()) {
            Log.i("Track_stepCounter", "no write/read permissions, return false");
            this.o = null;
            this.j = null;
            this.g = null;
            this.h = null;
            this.f = null;
            return false;
        }
        if (njlVar == null || callBackToReportStepsOrEvent == null || i < 100 || i > 10000) {
            Log.i("Track_stepCounter", "input invalid, return false");
            this.o = null;
            this.j = null;
            this.g = null;
            this.h = null;
            this.f = null;
            return false;
        }
        SensorManager sensorManager = (SensorManager) this.e.getSystemService("sensor");
        if (sensorManager == null) {
            Log.i("Track_stepCounter", "cannot get sensormanager, return false");
            this.o = null;
            this.j = null;
            this.g = null;
            this.h = null;
            this.f = null;
            return false;
        }
        this.g = callBackToReportStepsOrEvent;
        if (this.j == null) {
            this.j = new JniTest();
        }
        if (this.h == null) {
            this.h = new Handler();
        }
        int[] b = njlVar.b();
        Log.i("Track_stepCounter", "will initAlg, length of personInfo is " + b.length);
        if (1 == this.j.initAlg(b)) {
            Log.i("Track_stepCounter", "init alg error, return false");
            this.o = null;
            this.j = null;
            this.g = null;
            this.h = null;
            this.f = null;
            return false;
        }
        this.i = true;
        this.c = true;
        b();
        this.f15332a = i;
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(1), 10000);
        if (this.m) {
            Log.i("Track_stepCounter", "flag is true and will use steps outside, do not need to register TYPE_STEP_COUNTER");
        } else {
            Log.i("Track_stepCounter", "flag is false and will not use steps outside, need to register TYPE_STEP_COUNTER");
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(19), 500000);
        }
        this.o = sensorManager;
        Runnable runnable = new Runnable() { // from class: njm.5
            @Override // java.lang.Runnable
            public void run() {
                if (njm.this.c) {
                    if (njm.this.g != null) {
                        if (njm.this.j == null) {
                            njm.this.s = -1;
                        } else {
                            njm njmVar = njm.this;
                            njmVar.s = njmVar.j.getCurrentStepSource();
                        }
                        Log.d("Track_stepCounter", "report steps: ***,stepSource:" + njm.this.s);
                        njm.this.g.onUpdateStepsOrEvent(njm.this.k, njm.this.s);
                    } else {
                        Log.d("Track_stepCounter", "cannot report steps, mCallBackToReportStepsOrEvent is null");
                    }
                    if (njm.this.h == null || njm.this.f == null) {
                        return;
                    }
                    njm.this.h.postDelayed(njm.this.f, njm.d.f15332a);
                    return;
                }
                Log.d("Track_stepCounter", "cannot report steps,isAllowedCounterRunning:" + njm.this.c);
            }
        };
        this.f = runnable;
        this.h.postDelayed(runnable, d.f15332a);
        Log.i("Track_stepCounter", "start stepcounter success, return true");
        return true;
    }

    public void d() {
        Log.i("Track_stepCounter", "just into stopCountSteps.");
        this.c = false;
        Handler handler = this.h;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if (!e()) {
            Log.i("Track_stepCounter", "stopCountSteps. no read/write permissions");
        } else {
            JniTest jniTest = this.j;
            if (jniTest != null && this.e != null) {
                jniTest.stopAlg();
            }
        }
        SensorManager sensorManager = this.o;
        if (sensorManager != null) {
            sensorManager.unregisterListener(d, this.o.getDefaultSensor(1));
            if (this.m) {
                Log.i("Track_stepCounter", "flag is true and use steps outside, do not need to unregister TYPE_STEP_COUNTER");
            } else {
                Log.i("Track_stepCounter", "flag is false and not use steps outside, now unregister TYPE_STEP_COUNTER");
                this.o.unregisterListener(d, this.o.getDefaultSensor(19));
            }
        }
        this.o = null;
        this.j = null;
        this.g = null;
        this.h = null;
        this.f = null;
        this.i = false;
    }

    public boolean e() {
        return Build.VERSION.SDK_INT >= 33 ? ContextCompat.checkSelfPermission(this.e, "android.permission.READ_MEDIA_VIDEO") == 0 : ContextCompat.checkSelfPermission(this.e, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public boolean c(int i, int i2, int i3) {
        Log.d("Track_stepCounter", "just into refreshWorkoutParameters");
        int i4 = (i + 50) / 100;
        if (i4 < 0 || i4 > 300 || i2 < -1 || i2 > 9999999 || i3 < 0 || i3 > 2) {
            Log.d("Track_stepCounter", "input invalid, return false");
            return false;
        }
        this.n = i4;
        this.b = i2;
        this.r = i3;
        return true;
    }

    public int c() {
        Log.d("Track_stepCounter", "getCurrentsteps: ***");
        return this.k;
    }

    public void e(int i) {
        if (this.m) {
            Log.d("Track_stepCounter", "setCurStepsFromStepCountModule: ***");
            this.l = i;
        } else {
            Log.d("Track_stepCounter", "setCurStepsFromStepCountModule: flag is false and not allowed to use steps outside");
        }
    }

    private void b() {
        this.l = 0;
        this.k = 0;
        this.s = -1;
        this.n = 100;
        this.b = 1000;
        this.r = 0;
    }
}
