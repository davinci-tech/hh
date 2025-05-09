package com.huawei.datatype;

import android.bluetooth.BluetoothDevice;

/* loaded from: classes3.dex */
public class GoogleDeviceCache {
    private static final Object LOCK = new Object();
    private static GoogleDeviceCache mInstance;
    private QrBleCache mCache;

    private GoogleDeviceCache() {
    }

    public static GoogleDeviceCache getInstance() {
        GoogleDeviceCache googleDeviceCache;
        synchronized (LOCK) {
            if (mInstance == null) {
                mInstance = new GoogleDeviceCache();
            }
            googleDeviceCache = mInstance;
        }
        return googleDeviceCache;
    }

    public void saveCache(QrBleCache qrBleCache) {
        this.mCache = qrBleCache;
    }

    public QrBleCache getCache() {
        return this.mCache;
    }

    public static class QrBleCache {
        private BluetoothDevice mBluetoothDevice;
        private int mRssi;
        private long mTime;

        public BluetoothDevice getBluetoothDevice() {
            return this.mBluetoothDevice;
        }

        public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
            this.mBluetoothDevice = bluetoothDevice;
        }

        public int getRssi() {
            return this.mRssi;
        }

        public void setRssi(int i) {
            this.mRssi = i;
        }

        public long getTime() {
            return this.mTime;
        }

        public void setTime(long j) {
            this.mTime = j;
        }
    }
}
