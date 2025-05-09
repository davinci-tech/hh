package com.autonavi.aps.amapapi.restruct;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;

/* loaded from: classes2.dex */
public final class b implements SensorEventListener {

    /* renamed from: a, reason: collision with root package name */
    SensorManager f1626a;
    Sensor b;
    Sensor c;
    Sensor d;
    private Context s;
    public boolean e = false;
    public double f = 0.0d;
    public float g = 0.0f;
    private float t = 1013.25f;
    private float u = 0.0f;
    Handler h = new Handler();
    double i = 0.0d;
    double j = 0.0d;
    double k = 0.0d;
    double l = 0.0d;
    double[] m = new double[3];
    volatile double n = 0.0d;
    long o = 0;
    long p = 0;
    final int q = 100;
    final int r = 30;

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    public b(Context context) {
        this.s = null;
        this.f1626a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        try {
            this.s = context;
            if (this.f1626a == null) {
                this.f1626a = (SensorManager) context.getSystemService("sensor");
            }
            try {
                this.b = this.f1626a.getDefaultSensor(6);
            } catch (Throwable unused) {
            }
            try {
                this.c = this.f1626a.getDefaultSensor(11);
            } catch (Throwable unused2) {
            }
            try {
                this.d = this.f1626a.getDefaultSensor(1);
            } catch (Throwable unused3) {
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "AMapSensorManager", "<init>");
        }
    }

    public final void a() {
        SensorManager sensorManager = this.f1626a;
        if (sensorManager == null || this.e) {
            return;
        }
        this.e = true;
        try {
            Sensor sensor = this.b;
            if (sensor != null) {
                sensorManager.registerListener(this, sensor, 3, this.h);
            }
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "AMapSensorManager", "registerListener mPressure");
        }
        try {
            Sensor sensor2 = this.c;
            if (sensor2 != null) {
                this.f1626a.registerListener(this, sensor2, 3, this.h);
            }
        } catch (Throwable th2) {
            com.autonavi.aps.amapapi.utils.b.a(th2, "AMapSensorManager", "registerListener mRotationVector");
        }
        try {
            Sensor sensor3 = this.d;
            if (sensor3 != null) {
                this.f1626a.registerListener(this, sensor3, 3, this.h);
            }
        } catch (Throwable th3) {
            com.autonavi.aps.amapapi.utils.b.a(th3, "AMapSensorManager", "registerListener mAcceleroMeterVector");
        }
    }

    public final void b() {
        SensorManager sensorManager = this.f1626a;
        if (sensorManager == null || !this.e) {
            return;
        }
        this.e = false;
        try {
            Sensor sensor = this.b;
            if (sensor != null) {
                sensorManager.unregisterListener(this, sensor);
            }
        } catch (Throwable unused) {
        }
        try {
            Sensor sensor2 = this.c;
            if (sensor2 != null) {
                this.f1626a.unregisterListener(this, sensor2);
            }
        } catch (Throwable unused2) {
        }
        try {
            Sensor sensor3 = this.d;
            if (sensor3 != null) {
                this.f1626a.unregisterListener(this, sensor3);
            }
        } catch (Throwable unused3) {
        }
    }

    public final double c() {
        return this.f;
    }

    public final float d() {
        return this.u;
    }

    public final double e() {
        return this.l;
    }

    public final void f() {
        try {
            b();
            this.b = null;
            this.c = null;
            this.f1626a = null;
            this.d = null;
            this.e = false;
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "AMapSensorManager", "destroy");
        }
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent == null) {
            return;
        }
        try {
            int type = sensorEvent.sensor.getType();
            if (type == 1) {
                if (this.d != null) {
                    a((float[]) sensorEvent.values.clone());
                    return;
                }
                return;
            }
            if (type != 6) {
                if (type != 11) {
                    return;
                }
                try {
                    if (this.c != null) {
                        c((float[]) sensorEvent.values.clone());
                        return;
                    }
                    return;
                } catch (Throwable unused) {
                    return;
                }
            }
            try {
                if (this.b != null) {
                    float[] fArr = (float[]) sensorEvent.values.clone();
                    if (fArr != null) {
                        this.g = fArr[0];
                    }
                    b(fArr);
                }
            } catch (Throwable unused2) {
            }
        } catch (Throwable unused3) {
        }
    }

    private void a(float[] fArr) {
        double[] dArr = this.m;
        double d = dArr[0];
        float f = fArr[0];
        double d2 = (d * 0.800000011920929d) + (f * 0.19999999f);
        dArr[0] = d2;
        double d3 = dArr[1];
        float f2 = fArr[1];
        double d4 = (d3 * 0.800000011920929d) + (f2 * 0.19999999f);
        dArr[1] = d4;
        double d5 = dArr[2];
        float f3 = fArr[2];
        double d6 = (d5 * 0.800000011920929d) + (0.19999999f * f3);
        dArr[2] = d6;
        this.i = f - d2;
        this.j = f2 - d4;
        this.k = f3 - d6;
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.o < 100) {
            return;
        }
        double d7 = this.i;
        double d8 = this.j;
        double d9 = this.k;
        double sqrt = Math.sqrt((d7 * d7) + (d8 * d8) + (d9 * d9));
        this.p++;
        this.o = currentTimeMillis;
        this.n += sqrt;
        if (this.p >= 30) {
            this.l = this.n / this.p;
            this.n = 0.0d;
            this.p = 0L;
        }
    }

    private void b(float[] fArr) {
        if (fArr != null) {
            this.f = com.autonavi.aps.amapapi.utils.i.a(SensorManager.getAltitude(this.t, fArr[0]));
        }
    }

    private void c(float[] fArr) {
        if (fArr != null) {
            float[] fArr2 = new float[9];
            SensorManager.getRotationMatrixFromVector(fArr2, fArr);
            SensorManager.getOrientation(fArr2, new float[3]);
            float degrees = (float) Math.toDegrees(r3[0]);
            this.u = degrees;
            if (degrees <= 0.0f) {
                degrees += 360.0f;
            }
            this.u = (float) Math.floor(degrees);
        }
    }
}
