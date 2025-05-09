package com.huawei.openalliance.ad;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/* loaded from: classes9.dex */
public class rp implements SensorEventListener {

    /* renamed from: a, reason: collision with root package name */
    private SensorManager f7513a;
    private Sensor b;
    private a c;
    private final float[] d;
    private float[] e = new float[3];

    public interface a {
        void a(double d, double d2, double d3);
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 15) {
            SensorManager.getRotationMatrixFromVector(this.d, sensorEvent.values);
            SensorManager.getOrientation(this.d, this.e);
            double degrees = Math.toDegrees(this.e[0]);
            double degrees2 = Math.toDegrees(this.e[1]);
            double degrees3 = Math.toDegrees(this.e[2]);
            ho.a("RotateDetector", "degree x: " + degrees2 + " y: " + degrees3 + " z: " + degrees);
            a aVar = this.c;
            if (aVar != null) {
                aVar.a(degrees2, degrees3, degrees);
            }
        }
    }

    public void b() {
        try {
            this.f7513a.unregisterListener(this, this.b);
        } catch (Throwable th) {
            ho.c("RotateDetector", "unregister err: %s", th.getClass().getSimpleName());
        }
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a() {
        try {
            this.f7513a.registerListener(this, this.b, 3);
        } catch (Throwable th) {
            ho.c("RotateDetector", "registerListener exception: %s", th.getClass().getSimpleName());
        }
    }

    public rp(Context context) {
        float[] fArr = new float[16];
        this.d = fArr;
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        this.f7513a = sensorManager;
        this.b = sensorManager.getDefaultSensor(15);
        fArr[0] = 1.0f;
        fArr[4] = 1.0f;
        fArr[8] = 1.0f;
        fArr[12] = 1.0f;
    }
}
