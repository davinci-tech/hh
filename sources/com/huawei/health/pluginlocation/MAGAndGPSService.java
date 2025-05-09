package com.huawei.health.pluginlocation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.PowerManager;
import androidx.core.app.ActivityCompat;
import com.huawei.health.pluginlocation.logger.Logger;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import defpackage.exw;
import defpackage.eym;
import java.io.File;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes3.dex */
public class MAGAndGPSService implements SensorEventListener {
    private static final int GPS_TRANSMIT_FREQUENCY = 5;
    private static final int MAG_SAMPLING_PERIODUS = 10000;
    private static final int MAG_TIME_INTERVAL = 99;
    private static final String TAG = "AAR,MAGAndGPSService";
    private static Context context;
    private static boolean isLoadSuccess;
    private static MAGAndGPSService magInstance;
    private GpsData mGpsData;
    private MagData mMagData;
    private Sensor magnetometer;
    private SensorManager sensorManager;
    private int stopFlag;
    private long lastUpdate = 0;
    private long currentTime = 0;
    private int gpsNum = 0;
    private LinkedBlockingQueue<GpsData> locationQue = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<MagData> magDataQue = new LinkedBlockingQueue<>();
    private PowerManager.WakeLock mWakeLock = null;

    public native void alignGpsAndMagData(LinkedBlockingQueue<GpsData> linkedBlockingQueue, int i, LinkedBlockingQueue<MagData> linkedBlockingQueue2, int i2, int i3);

    static {
        try {
            System.loadLibrary("TrackProcessNew");
            isLoadSuccess = true;
        } catch (UnsatisfiedLinkError unused) {
            eym.e(TAG, "no TrackProcessNew lib");
            isLoadSuccess = false;
        }
    }

    public MAGAndGPSService(Context context2) {
        context = context2;
        SensorManager sensorManager = (SensorManager) context2.getSystemService("sensor");
        this.sensorManager = sensorManager;
        this.magnetometer = sensorManager.getDefaultSensor(2);
    }

    public static void init(Context context2) {
        if (magInstance == null) {
            magInstance = new MAGAndGPSService(context2);
        }
    }

    public static MAGAndGPSService getInstance(Context context2) {
        init(context2);
        return magInstance;
    }

    public void start(String str, Logger logger) {
        eym.c(logger);
        resume();
        this.gpsNum = 0;
        try {
            new exw().c(context, str, ((File) Objects.requireNonNull(((File) Objects.requireNonNull(context.getExternalCacheDir())).getParentFile())).getPath() + "/files/RunwayRoutes/roadData/");
            eym.b(TAG, "download roaddata successed.");
        } catch (Exception e) {
            eym.c(TAG, "download roaddata failed." + e.getMessage());
        }
    }

    public void resume() {
        if (!isLoadSuccess) {
            eym.c(TAG, "loadLibrary failed, can not start transfer gps and mag data.");
            return;
        }
        acquireWakeLock();
        this.sensorManager.registerListener(this, this.magnetometer, 10000);
        this.stopFlag = 0;
        eym.b(TAG, "start, stopFlag=" + this.stopFlag);
    }

    public void pause() {
        releaseWakeLock();
        this.sensorManager.unregisterListener(this);
        this.stopFlag = 2;
        eym.b(TAG, "pause, stopFlag=" + this.stopFlag);
        Iterator<MagData> it = this.magDataQue.iterator();
        while (it.hasNext()) {
            eym.b(TAG, "$MagData," + it.next().toString());
        }
        LinkedBlockingQueue<GpsData> linkedBlockingQueue = this.locationQue;
        int size = linkedBlockingQueue.size();
        LinkedBlockingQueue<MagData> linkedBlockingQueue2 = this.magDataQue;
        alignGpsAndMagData(linkedBlockingQueue, size, linkedBlockingQueue2, linkedBlockingQueue2.size(), this.stopFlag);
    }

    public void stop() {
        releaseWakeLock();
        this.sensorManager.unregisterListener(this);
        this.stopFlag = 1;
        eym.b(TAG, "stop, stopFlag=" + this.stopFlag);
        Iterator<MagData> it = this.magDataQue.iterator();
        while (it.hasNext()) {
            eym.b(TAG, "$MagData," + it.next().toString());
        }
        LinkedBlockingQueue<GpsData> linkedBlockingQueue = this.locationQue;
        int size = linkedBlockingQueue.size();
        LinkedBlockingQueue<MagData> linkedBlockingQueue2 = this.magDataQue;
        alignGpsAndMagData(linkedBlockingQueue, size, linkedBlockingQueue2, linkedBlockingQueue2.size(), this.stopFlag);
        magInstance = null;
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 2) {
            long currentTimeMillis = System.currentTimeMillis();
            this.currentTime = currentTimeMillis;
            if (currentTimeMillis - this.lastUpdate >= 99) {
                this.lastUpdate = currentTimeMillis;
                MagData magData = new MagData(this.currentTime, sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
                this.mMagData = magData;
                try {
                    this.magDataQue.put(magData);
                } catch (InterruptedException e) {
                    eym.c(TAG, "magDataQue put exception" + e.getMessage());
                }
            }
        }
    }

    public void setGpsLocation(Location location) {
        try {
            this.mGpsData = new GpsData(location.getTime(), location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getAccuracy(), location.getSpeed());
            if (location.getProvider().equals(HAWebViewInterface.NETWORK) && this.gpsNum == 0) {
                eym.c(TAG, "first location exception," + this.mGpsData.toString());
                return;
            }
            this.gpsNum++;
            this.locationQue.put(this.mGpsData);
            this.stopFlag = 0;
            eym.b(TAG, "$GpsData," + this.mGpsData.toString());
            if (this.locationQue.size() < 5) {
                return;
            }
            Iterator<MagData> it = this.magDataQue.iterator();
            while (it.hasNext()) {
                eym.b(TAG, "$MagData," + it.next().toString());
            }
            LinkedBlockingQueue<GpsData> linkedBlockingQueue = this.locationQue;
            int size = linkedBlockingQueue.size();
            LinkedBlockingQueue<MagData> linkedBlockingQueue2 = this.magDataQue;
            alignGpsAndMagData(linkedBlockingQueue, size, linkedBlockingQueue2, linkedBlockingQueue2.size(), this.stopFlag);
        } catch (Exception e) {
            eym.c(TAG, "setGpsLocation exception" + e.getMessage());
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
        if (sensor.getType() == 2) {
            if (i == 0) {
                eym.b(TAG, "Magnetic sensor is unreliable");
                return;
            }
            if (i == 1) {
                eym.b(TAG, "Magnetic sensor accuracy low");
            } else if (i == 2) {
                eym.b(TAG, "Magnetic sensor accuracy medium");
            } else {
                if (i != 3) {
                    return;
                }
                eym.b(TAG, "Magnetic sensor accuracy High");
            }
        }
    }

    private void acquireWakeLock() {
        if (this.mWakeLock == null) {
            if (ActivityCompat.checkSelfPermission(context, "android.permission.WAKE_LOCK") != 0) {
                eym.e(TAG, "no WAKE_LOCK permission, plz check permission setting.");
                return;
            }
            PowerManager.WakeLock newWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, TAG);
            this.mWakeLock = newWakeLock;
            if (newWakeLock != null) {
                newWakeLock.acquire();
                eym.b(TAG, "wakelock start");
            }
        }
    }

    private void releaseWakeLock() {
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        this.mWakeLock.release();
        this.mWakeLock = null;
        eym.b(TAG, "wakelock stop");
    }
}
