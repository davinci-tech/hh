package com.huawei.health.device.manager;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.client.connect.ServiceConnectCallback;
import com.huawei.profile.client.profile.ProfileClient;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public enum ProfileManager {
    INSTANCE;

    private static final int CONNECT_TIMEOUT = 15000;
    private static final int DEFAULT_CONNECTION_COUNT = 0;
    private static final Object LOCK = new Object();
    private static final String TAG = "ProfileManager";
    private volatile boolean mIsConnected;
    private TimerTask mTask;
    private Timer mTimer;
    private AtomicInteger mConnectCount = new AtomicInteger(0);
    private ProfileClient mClientAgent = new ProfileClient(BaseApplication.getContext());
    private final ServiceConnectCallback mServiceConnectCallback = new ServiceConnectCallback() { // from class: com.huawei.health.device.manager.ProfileManager.1
        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onConnect() {
            synchronized (ProfileManager.LOCK) {
                LogUtil.h(ProfileManager.TAG, "profile connect ");
                ProfileManager.this.mConnectCount.set(0);
                ProfileManager.this.mIsConnected = true;
                ProfileManager.LOCK.notifyAll();
            }
        }

        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onDisconnect() {
            synchronized (ProfileManager.LOCK) {
                LogUtil.h(ProfileManager.TAG, "profile disconnected ");
                ProfileManager.this.mIsConnected = false;
                ProfileManager.this.mConnectCount.set(0);
            }
        }
    };

    ProfileManager() {
    }

    public boolean putDevice(DeviceProfile deviceProfile) {
        synchronized (this) {
            checkConnectStatusAndConnect();
            if (this.mClientAgent.hasConnected()) {
                this.mConnectCount.incrementAndGet();
                boolean putDevice = this.mClientAgent.putDevice(deviceProfile);
                LogUtil.c(TAG, "putDevice complete currentCount ", Integer.valueOf(this.mConnectCount.decrementAndGet()));
                startDisconnectTimer();
                return putDevice;
            }
            LogUtil.h(TAG, "profile client connect fail");
            return false;
        }
    }

    public boolean putServiceOfDevice(String str, ServiceProfile serviceProfile) {
        synchronized (this) {
            checkConnectStatusAndConnect();
            if (this.mClientAgent.hasConnected()) {
                this.mConnectCount.incrementAndGet();
                boolean putServiceOfDevice = this.mClientAgent.putServiceOfDevice(str, serviceProfile);
                LogUtil.c(TAG, "putServiceOfDevice complete currentCount ", Integer.valueOf(this.mConnectCount.decrementAndGet()));
                startDisconnectTimer();
                return putServiceOfDevice;
            }
            LogUtil.h(TAG, "profile client connect fail");
            return false;
        }
    }

    public boolean putServiceCharacteristic(String str, String str2, ServiceCharacteristicProfile serviceCharacteristicProfile) {
        synchronized (this) {
            checkConnectStatusAndConnect();
            if (this.mClientAgent.hasConnected()) {
                this.mConnectCount.incrementAndGet();
                boolean putServiceCharacteristic = this.mClientAgent.putServiceCharacteristic(str, str2, serviceCharacteristicProfile);
                LogUtil.c(TAG, "putServiceCharacteristic complete currentCount ", Integer.valueOf(this.mConnectCount.decrementAndGet()));
                startDisconnectTimer();
                return putServiceCharacteristic;
            }
            LogUtil.h(TAG, "profile client connect fail");
            return false;
        }
    }

    public boolean deleteDevice(String str) {
        synchronized (this) {
            checkConnectStatusAndConnect();
            if (this.mClientAgent.hasConnected()) {
                this.mConnectCount.incrementAndGet();
                boolean deleteDevice = this.mClientAgent.deleteDevice(str);
                LogUtil.c(TAG, "deleteDevice complete currentCount ", Integer.valueOf(this.mConnectCount.decrementAndGet()));
                startDisconnectTimer();
                return deleteDevice;
            }
            LogUtil.h(TAG, "profile client connect fail");
            return false;
        }
    }

    public boolean deleteServiceOfDevice(String str, String str2) {
        synchronized (this) {
            checkConnectStatusAndConnect();
            if (this.mClientAgent.hasConnected()) {
                this.mConnectCount.incrementAndGet();
                boolean deleteServiceOfDevice = this.mClientAgent.deleteServiceOfDevice(str, str2);
                LogUtil.c(TAG, "deleteServiceOfDevice complete currentCount ", Integer.valueOf(this.mConnectCount.decrementAndGet()));
                startDisconnectTimer();
                return deleteServiceOfDevice;
            }
            LogUtil.h(TAG, "profile client connect fail");
            return false;
        }
    }

    public boolean deleteServiceCharacteristic(String str, String str2, String str3) {
        synchronized (this) {
            checkConnectStatusAndConnect();
            if (this.mClientAgent.hasConnected()) {
                this.mConnectCount.incrementAndGet();
                boolean deleteServiceCharacteristic = this.mClientAgent.deleteServiceCharacteristic(str, str2, str3);
                LogUtil.c(TAG, "deleteServiceCharacteristic complete currentCount ", Integer.valueOf(this.mConnectCount.decrementAndGet()));
                startDisconnectTimer();
                return deleteServiceCharacteristic;
            }
            LogUtil.h(TAG, "profile client connect fail");
            return false;
        }
    }

    public ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, boolean z, List<String> list) {
        synchronized (this) {
            checkConnectStatusAndConnect();
            if (this.mClientAgent.hasConnected()) {
                this.mConnectCount.incrementAndGet();
                ServiceCharacteristicProfile serviceCharacteristics = this.mClientAgent.getServiceCharacteristics(str, str2, z, list);
                LogUtil.c(TAG, "getServiceCharacteristics complete currentCount ", Integer.valueOf(this.mConnectCount.decrementAndGet()));
                startDisconnectTimer();
                return serviceCharacteristics;
            }
            LogUtil.h(TAG, "profile client connect fail");
            return null;
        }
    }

    public List<ServiceProfile> getServicesOfDevice(String str, boolean z, List<String> list) {
        synchronized (this) {
            checkConnectStatusAndConnect();
            if (this.mClientAgent.hasConnected()) {
                this.mConnectCount.incrementAndGet();
                List<ServiceProfile> servicesOfDevice = this.mClientAgent.getServicesOfDevice(str, z, list);
                LogUtil.c(TAG, "getServicesOfDevice complete currentCount ", Integer.valueOf(this.mConnectCount.decrementAndGet()));
                startDisconnectTimer();
                return servicesOfDevice;
            }
            LogUtil.h(TAG, "profile client connect fail");
            return new ArrayList();
        }
    }

    public List<DeviceProfile> getDevices(boolean z, List<String> list) {
        synchronized (this) {
            checkConnectStatusAndConnect();
            if (this.mClientAgent.hasConnected()) {
                this.mConnectCount.incrementAndGet();
                List<DeviceProfile> devices = this.mClientAgent.getDevices(z, list);
                LogUtil.c(TAG, "getDevices complete currentCount ", Integer.valueOf(this.mConnectCount.decrementAndGet()));
                startDisconnectTimer();
                return devices;
            }
            LogUtil.h(TAG, "profile client connect fail");
            return new ArrayList(0);
        }
    }

    private void startDisconnectTimer() {
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
        }
        this.mTimer = new Timer(TAG);
        TimerTask timerTask = new TimerTask() { // from class: com.huawei.health.device.manager.ProfileManager.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                synchronized (ProfileManager.LOCK) {
                    if (ProfileManager.this.mConnectCount.get() == 0 && ProfileManager.this.mClientAgent.hasConnected()) {
                        LogUtil.h(ProfileManager.TAG, "disconnect profile");
                        ProfileManager.this.mClientAgent.disconnect();
                    }
                }
            }
        };
        this.mTask = timerTask;
        this.mTimer.schedule(timerTask, 15000L);
    }

    private void checkConnectStatusAndConnect() {
        if (this.mClientAgent.hasConnected()) {
            return;
        }
        this.mClientAgent.connect(this.mServiceConnectCallback);
        Object obj = LOCK;
        synchronized (obj) {
            try {
                if (!this.mIsConnected) {
                    obj.wait(15000L);
                }
            } catch (InterruptedException unused) {
                LogUtil.h(TAG, "waiting for serivce connect interrupt");
            }
        }
    }
}
