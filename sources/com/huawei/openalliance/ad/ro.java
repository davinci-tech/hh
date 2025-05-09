package com.huawei.openalliance.ad;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/* loaded from: classes9.dex */
public class ro implements SensorEventListener {

    /* renamed from: a, reason: collision with root package name */
    private SensorManager f7512a;
    private Sensor b;
    private a c;

    public interface a {
        void a(float f, float f2, float f3);
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (1 == sensorEvent.sensor.getType()) {
            float f = sensorEvent.values[0];
            float f2 = sensorEvent.values[1];
            float f3 = sensorEvent.values[2];
            if (ho.a()) {
                ho.a("PhoneAccelerometerDetec", "onSensorChanged x: %s, y: %s, z: %s", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3));
            }
            a aVar = this.c;
            if (aVar != null) {
                aVar.a(f, f2, f3);
            }
        }
    }

    public void b() {
        try {
            this.f7512a.unregisterListener(this, this.b);
        } catch (Throwable th) {
            ho.c("PhoneAccelerometerDetec", "unregister err: %s", th.getClass().getSimpleName());
        }
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a() {
        Sensor sensor = this.b;
        if (sensor != null) {
            try {
                this.f7512a.registerListener(this, sensor, 2);
            } catch (Throwable th) {
                ho.c("PhoneAccelerometerDetec", "registerListener exception: %s", th.getClass().getSimpleName());
            }
        }
    }

    public ro(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        this.f7512a = sensorManager;
        this.b = sensorManager.getDefaultSensor(1);
    }
}
