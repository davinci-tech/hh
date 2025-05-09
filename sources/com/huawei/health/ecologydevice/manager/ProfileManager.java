package com.huawei.health.ecologydevice.manager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.client.connect.ServiceConnectCallback;
import com.huawei.profile.client.profile.ProfileClient;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import com.huawei.profile.utils.BaseUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public enum ProfileManager {
    INSTANCE;

    private static final int CONNECT_TIMEOUT = 5000;
    private static final int DISCONNECT_DELAY_TIME = 15000;
    private static final int MSG_CONNECT_TIMEOUT = 2;
    private static final int MSG_DISCONNECT = 1;
    private static final String TAG = "Ecology_ProfileManager";
    private static final String TAG_RELEASE = "DEVMGR_Ecology_ProfileManager";
    private volatile boolean mIsConnected;
    private volatile boolean mIsConnecting;
    private final ProfileClient mClientAgent = new ProfileClient(BaseApplication.getContext());
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.ecologydevice.manager.ProfileManager.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                LogUtil.h(ProfileManager.TAG, "disconnect profile");
                ProfileManager.this.mClientAgent.disconnect();
            } else if (message.what == 2) {
                LogUtil.h(ProfileManager.TAG, "connect profile timeout");
                ProfileManager.this.mIsConnecting = false;
            } else {
                LogUtil.a(ProfileManager.TAG, "invalid msg: ", message);
            }
        }
    };
    private final ServiceConnectCallback mServiceConnectCallback = new ServiceConnectCallback() { // from class: com.huawei.health.ecologydevice.manager.ProfileManager.4
        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onConnect() {
            ProfileManager.this.mIsConnected = true;
            ProfileManager.this.mIsConnecting = false;
            ReleaseLogUtil.d(ProfileManager.TAG_RELEASE, "onConnect: profile connected");
            if (ProfileManager.this.mHandler.hasMessages(2)) {
                ProfileManager.this.mHandler.removeMessages(2);
            }
        }

        @Override // com.huawei.profile.client.connect.ServiceConnectCallback
        public void onDisconnect() {
            ProfileManager.this.mIsConnected = false;
            ReleaseLogUtil.d(ProfileManager.TAG_RELEASE, "onDisconnect: profile disconnected");
        }
    };

    ProfileManager() {
    }

    public boolean putDevice(DeviceProfile deviceProfile) {
        synchronized (this) {
            LogUtil.a(TAG, "putDevice: ", deviceProfile);
            checkConnectStatusAndConnect();
            if (this.mIsConnected) {
                boolean putDevice = this.mClientAgent.putDevice(deviceProfile);
                ReleaseLogUtil.e(TAG_RELEASE, "putDevice: isSuccess= ", Boolean.valueOf(putDevice));
                startDisconnect();
                return putDevice;
            }
            ReleaseLogUtil.d(TAG_RELEASE, "putDevice: profile client connect fail");
            return false;
        }
    }

    public boolean putServiceOfDevice(String str, ServiceProfile serviceProfile) {
        synchronized (this) {
            LogUtil.a(TAG, "putServiceOfDevice: deviceId = ", BaseUtil.anonymousContent(str));
            checkConnectStatusAndConnect();
            if (this.mIsConnected) {
                boolean putServiceOfDevice = this.mClientAgent.putServiceOfDevice(str, serviceProfile);
                ReleaseLogUtil.e(TAG_RELEASE, "putServiceOfDevice: isSuccess = ", Boolean.valueOf(putServiceOfDevice));
                startDisconnect();
                return putServiceOfDevice;
            }
            ReleaseLogUtil.d(TAG_RELEASE, "putServiceOfDevice: profile client connect fail");
            return false;
        }
    }

    public boolean putServiceCharacteristic(String str, String str2, ServiceCharacteristicProfile serviceCharacteristicProfile) {
        synchronized (this) {
            LogUtil.a(TAG, "putServiceCharacteristic: deviceId = ", BaseUtil.anonymousContent(str), ", serviceId = ", str2);
            checkConnectStatusAndConnect();
            if (this.mIsConnected) {
                boolean putServiceCharacteristic = this.mClientAgent.putServiceCharacteristic(str, str2, serviceCharacteristicProfile);
                ReleaseLogUtil.e(TAG_RELEASE, "putServiceCharacteristic: isSuccess ", Boolean.valueOf(putServiceCharacteristic));
                startDisconnect();
                return putServiceCharacteristic;
            }
            ReleaseLogUtil.d(TAG_RELEASE, "putServiceCharacteristic: profile client connect fail");
            return false;
        }
    }

    public boolean deleteDevice(String str) {
        synchronized (this) {
            LogUtil.a(TAG, "deleteDevice: deviceId = ", BaseUtil.anonymousContent(str));
            checkConnectStatusAndConnect();
            if (this.mIsConnected) {
                boolean deleteDevice = this.mClientAgent.deleteDevice(str);
                ReleaseLogUtil.e(TAG_RELEASE, "deleteDevice: isSuccess = ", Boolean.valueOf(deleteDevice));
                startDisconnect();
                return deleteDevice;
            }
            ReleaseLogUtil.d(TAG_RELEASE, "deleteDevice: profile client connect fail");
            return false;
        }
    }

    public boolean deleteServiceOfDevice(String str, String str2) {
        synchronized (this) {
            LogUtil.a(TAG, "deleteServiceOfDevice: deviceId = ", BaseUtil.anonymousContent(str), ", serviceId = ", str2);
            checkConnectStatusAndConnect();
            if (this.mIsConnected) {
                boolean deleteServiceOfDevice = this.mClientAgent.deleteServiceOfDevice(str, str2);
                ReleaseLogUtil.e(TAG_RELEASE, "deleteServiceOfDevice: isSuccess ", Boolean.valueOf(deleteServiceOfDevice));
                startDisconnect();
                return deleteServiceOfDevice;
            }
            ReleaseLogUtil.d(TAG_RELEASE, "deleteServiceOfDevice: profile client connect fail");
            return false;
        }
    }

    public boolean deleteServiceCharacteristic(String str, String str2, String str3) {
        synchronized (this) {
            LogUtil.a(TAG, "deleteServiceCharacteristic: deviceId = ", BaseUtil.anonymousContent(str), ", serviceId = ", str2, ", characteristicId = ", str3);
            checkConnectStatusAndConnect();
            if (this.mIsConnected) {
                boolean deleteServiceCharacteristic = this.mClientAgent.deleteServiceCharacteristic(str, str2, str3);
                ReleaseLogUtil.e(TAG_RELEASE, "deleteServiceCharacteristic: isSuccess ", Boolean.valueOf(deleteServiceCharacteristic));
                startDisconnect();
                return deleteServiceCharacteristic;
            }
            ReleaseLogUtil.d(TAG_RELEASE, "deleteServiceCharacteristic: profile client connect fail");
            return false;
        }
    }

    public ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, boolean z, List<String> list) {
        synchronized (this) {
            LogUtil.a(TAG, "getServiceCharacteristics: deviceId = ", BaseUtil.anonymousContent(str), ", serviceId = ", str2, ", isSync = ", Boolean.valueOf(z));
            checkConnectStatusAndConnect();
            if (this.mIsConnected) {
                ServiceCharacteristicProfile serviceCharacteristics = this.mClientAgent.getServiceCharacteristics(str, str2, z, list);
                startDisconnect();
                return serviceCharacteristics;
            }
            ReleaseLogUtil.d(TAG_RELEASE, "getServiceCharacteristics: profile client connect fail");
            return null;
        }
    }

    public List<ServiceProfile> getServicesOfDevice(String str, boolean z, List<String> list) {
        synchronized (this) {
            LogUtil.a(TAG, "getServicesOfDevice: deviceId = ", BaseUtil.anonymousContent(str), ", isSync = ", Boolean.valueOf(z));
            checkConnectStatusAndConnect();
            if (this.mIsConnected) {
                List<ServiceProfile> servicesOfDevice = this.mClientAgent.getServicesOfDevice(str, z, list);
                startDisconnect();
                return servicesOfDevice;
            }
            ReleaseLogUtil.d(TAG_RELEASE, "getServicesOfDevice: profile client connect fail");
            return new ArrayList();
        }
    }

    public List<DeviceProfile> getDevices(boolean z, List<String> list) {
        synchronized (this) {
            LogUtil.a(TAG, "getDevices: isSync = ", Boolean.valueOf(z));
            checkConnectStatusAndConnect();
            if (this.mIsConnected) {
                List<DeviceProfile> devices = this.mClientAgent.getDevices(z, list);
                startDisconnect();
                return devices;
            }
            ReleaseLogUtil.d(TAG_RELEASE, "getDevices: profile client connect fail");
            return new ArrayList(0);
        }
    }

    private void startDisconnect() {
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, 15000L);
    }

    private void checkConnectStatusAndConnect() {
        if (this.mIsConnected || this.mIsConnecting) {
            return;
        }
        LogUtil.a(TAG, "checkConnectStatusAndConnect: Not connected, open connection");
        this.mIsConnecting = true;
        this.mHandler.sendEmptyMessageDelayed(2, 5000L);
        this.mIsConnected = this.mClientAgent.connect(this.mServiceConnectCallback);
    }
}
