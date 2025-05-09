package com.huawei.profile.client.profile;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.profile.client.connect.RemoteServiceConnection;
import com.huawei.profile.client.connect.ServiceConnectCallback;
import com.huawei.profile.kv.ProfileStoreBaseProxy;
import com.huawei.profile.kv.ProfileStoreExtendProxy;
import com.huawei.profile.kv.ProfileStoreFactory;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.DeviceProfileEx;
import com.huawei.profile.profile.ProfileGeneralException;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import com.huawei.profile.profile.ServiceProfileEx;
import com.huawei.profile.profile.SubscribeInfo;
import com.huawei.profile.profile.SyncMode;
import com.huawei.profile.service.AbstractSubscribeProfileListener;
import com.huawei.profile.service.GetDeviceInfoListener;
import com.huawei.profile.service.GetDeviceInfoListenerEx;
import com.huawei.profile.service.IProfileServiceCall;
import com.huawei.profile.service.PutDeviceExListener;
import com.huawei.profile.service.SyncDeviceInfoExListener;
import com.huawei.profile.service.SyncDeviceInfoListener;
import com.huawei.profile.subscription.event.EventInfo;
import com.huawei.profile.utils.BaseUtil;
import com.huawei.profile.utils.DeviceListType;
import com.huawei.profile.utils.OpResult;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class ProfileClient implements IProfileClient, ISwitchClient, IDeviceListClient {
    public static final String CLOUD_LOCAL_DOMAIN = "cloud_local_domain";
    private static final int DATA_SOURCE_CLOUD = 1;
    private static final int DATA_SOURCE_DEVICE = 2;
    private static final int DATA_SOURCE_INVALID = -1;
    private static final int DATA_SOURCE_P2P = 4;
    private static final String DEAD_OBJECT_EXCEPTION = "receive a dead object exception";
    public static final String DEFAULT_TRUST_DOMAIN = "default_trust_domain";
    private static final int DEFAULT_VERSION_CODE = -1;
    private static final String DEFAULT_WRONG_SDK_VERSION = "1.0.0";
    private static final String DEVICE_PROFILE_SDK_VERSION = "device_profile_sdk_version";
    private static final String EMPTY_STRING = " ";
    private static final String HIV = "hiv";
    public static final String IS_UPLOAD_IMMEDIATELY = "isUploadImmediately";
    private static final int MAX_CHARACTERS_SIZE = 200;
    private static final int MAX_NUM_SID_TO_DELETE = 20;
    private static final int MAX_SERVICES_SIZE = 2500;
    private static final String OBTAIN_EXTEND_PROXY_FAILED = "Failed to obtain the ProfileStoreExtendProxy.";
    private static final String ON_CONNECTED_SERVICE = " no profile service connected";
    public static final String P2P_TRUST_DOMAIN = "p2p_trust_domain";
    public static final int RETURN_CACHE = 0;
    public static final int RETURN_FAIL = 1;
    public static final String SYNC_TIMEOUT_MODE = "syncTimeoutMode";
    private static final String TAG = "ProfileClient";
    private String callingPkgName;
    private volatile ServiceConnectCallback connectCallback;
    private Context context;
    private final RemoteServiceConnection dsConnection;
    private volatile boolean isBinded;
    private volatile boolean isConnected;
    private final boolean isUseProfileApk;
    private final Object locker;
    private volatile IProfileServiceCall profileService;
    private final ProfileStoreFactory profileStoreFactory;
    private String sdkVersionCode;

    private boolean hasP2pDataSource(int i) {
        return (i & 4) != 0;
    }

    public ProfileClient(Context context) throws IllegalArgumentException {
        this(context, -2);
        BaseUtil.markCallInterface(TAG, this.callingPkgName);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ProfileClient(android.content.Context r3, int r4) throws java.lang.IllegalArgumentException {
        /*
            r2 = this;
            boolean r0 = com.huawei.profile.utils.BaseUtil.isProfileApkInstalled(r3)
            if (r0 == 0) goto L19
            if (r3 != 0) goto Lb
            java.lang.String r0 = ""
            goto Lf
        Lb:
            java.lang.String r0 = r3.getPackageName()
        Lf:
            java.lang.String r1 = "com.huawei.health"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L19
            r0 = 1
            goto L1a
        L19:
            r0 = 0
        L1a:
            r2.<init>(r3, r0, r4)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = "new ProfileClient, userId:"
            r3.<init>(r0)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "ProfileClient"
            android.util.Log.i(r4, r3)
            java.lang.String r3 = "ProfileClientWithUserId"
            java.lang.String r4 = r2.callingPkgName
            com.huawei.profile.utils.BaseUtil.markCallInterface(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.profile.client.profile.ProfileClient.<init>(android.content.Context, int):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ProfileClient(android.content.Context r3, com.huawei.profile.client.profile.CloudInteractionMode r4) throws java.lang.IllegalArgumentException {
        /*
            r2 = this;
            com.huawei.profile.client.profile.CloudInteractionMode r0 = com.huawei.profile.client.profile.CloudInteractionMode.BY_PROFILE
            if (r4 != r0) goto L17
            if (r3 != 0) goto L9
            java.lang.String r0 = ""
            goto Ld
        L9:
            java.lang.String r0 = r3.getPackageName()
        Ld:
            java.lang.String r1 = "com.huawei.health"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L17
            r0 = 1
            goto L18
        L17:
            r0 = 0
        L18:
            r1 = -2
            r2.<init>(r3, r0, r1)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = "ProfileClient_"
            r3.<init>(r0)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = r2.callingPkgName
            com.huawei.profile.utils.BaseUtil.markCallInterface(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.profile.client.profile.ProfileClient.<init>(android.content.Context, com.huawei.profile.client.profile.CloudInteractionMode):void");
    }

    private ProfileClient(Context context, boolean z, int i) throws IllegalArgumentException {
        this.profileStoreFactory = new ProfileStoreFactory();
        if (context == null) {
            throw new IllegalArgumentException("Context is null");
        }
        this.dsConnection = new RemoteServiceConnection(context, i);
        this.connectCallback = null;
        this.profileService = null;
        this.callingPkgName = context.getPackageName();
        this.locker = new Object();
        this.isConnected = false;
        this.isBinded = false;
        this.context = context;
        this.isUseProfileApk = z;
        this.sdkVersionCode = getSdkVersion();
    }

    private void invokeConnectCallback(boolean z) {
        if (this.connectCallback == null) {
            Log.i(TAG, "invokeConnectCallback but connectCallback is null");
        } else if (z) {
            this.connectCallback.onConnect();
        } else {
            this.connectCallback.onDisconnect();
        }
    }

    public boolean connect() {
        return connect(null);
    }

    public boolean connect(ServiceConnectCallback serviceConnectCallback) {
        synchronized (this.locker) {
            BaseUtil.markCallInterface("connect", this.callingPkgName);
            Log.i(TAG, "sdk version = " + this.sdkVersionCode);
            if (this.isBinded) {
                Log.i(TAG, "isBinded");
                return true;
            }
            this.connectCallback = serviceConnectCallback;
            if (this.isUseProfileApk && !"com.huawei.health".equals(this.callingPkgName)) {
                if (this.profileService != null) {
                    Log.i(TAG, "profileService is not null, invoke connectCallback");
                    invokeConnectCallback(true);
                }
                this.isBinded = this.dsConnection.open(new RemoteServiceConnection.OnConnectListener() { // from class: com.huawei.profile.client.profile.ProfileClient.1
                    @Override // com.huawei.profile.client.connect.RemoteServiceConnection.OnConnectListener
                    public void onConnect(IBinder iBinder) {
                        ProfileClient.this.invokeOnConnected(iBinder);
                    }

                    @Override // com.huawei.profile.client.connect.RemoteServiceConnection.OnConnectListener
                    public void onDisconnect() {
                        ProfileClient.this.invokeOnDisconnected();
                    }
                });
                if (!this.isBinded) {
                    Log.e(TAG, "Failed to open connection");
                }
                return this.isBinded;
            }
            this.isConnected = true;
            this.isBinded = true;
            invokeConnectCallback(true);
            Log.i(TAG, "APK is not installed, invoke SDK.");
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeOnConnected(IBinder iBinder) {
        if (iBinder != null) {
            synchronized (this.locker) {
                this.profileStoreFactory.releaseStore(this.isUseProfileApk);
                this.profileService = (IProfileServiceCall) BaseUtil.cast(Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{IProfileServiceCall.class}, new ProfileServiceHandler(IProfileServiceCall.Stub.asInterface(iBinder))), IProfileServiceCall.class);
                this.isConnected = true;
                this.isBinded = true;
                Log.i(TAG, "Succeed to connect, invoke callback");
                invokeConnectCallback(true);
            }
            return;
        }
        Log.e(TAG, "Binder is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeOnDisconnected() {
        synchronized (this.locker) {
            this.profileService = null;
            this.isConnected = false;
            this.isBinded = false;
            invokeConnectCallback(false);
        }
        Log.w(TAG, "Connection to is broken down");
    }

    public boolean disconnect() {
        synchronized (this.locker) {
            BaseUtil.markCallInterface(AwarenessRequest.MessageType.DISCONNECT, this.callingPkgName);
            if (!this.isUseProfileApk) {
                invokeConnectCallback(false);
                this.isConnected = false;
                this.isBinded = false;
                Log.i(TAG, "Profile SDK close connection");
                return true;
            }
            invokeConnectCallback(false);
            boolean close = this.dsConnection.close();
            Log.i(TAG, "close connection: " + close);
            this.profileService = null;
            this.isBinded = false;
            this.isConnected = false;
            return close;
        }
    }

    public boolean hasConnected() {
        BaseUtil.markCallInterface("hasConnected", this.callingPkgName);
        synchronized (this.locker) {
            boolean z = false;
            if (this.isUseProfileApk && (this.profileService == null || !this.profileService.asBinder().isBinderAlive())) {
                Log.i(TAG, "profileService is null or not active, return false");
                if (this.isConnected && isBinded()) {
                    disconnect();
                }
                return false;
            }
            Log.i(TAG, "isConnected = " + this.isConnected + ", isBinded = " + isBinded());
            if (this.isConnected && isBinded()) {
                z = true;
            }
            return z;
        }
    }

    public boolean isBinded() {
        BaseUtil.markCallInterface("isBinded", this.callingPkgName);
        return this.isBinded;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public String getApiVersion() {
        BaseUtil.markCallInterface("getApiVersion", this.callingPkgName);
        if (this.profileService == null) {
            Log.e(TAG, "getApiVersion: no profile service connected");
            return "";
        }
        try {
            return this.profileService.getApiVersion();
        } catch (RemoteException e) {
            Log.e(TAG, "getApiVersion failed: " + e.getClass().getName());
            return "";
        }
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public int getApiVersionCode() {
        BaseUtil.markCallInterface("getApiVersionCode", this.callingPkgName);
        if (this.profileService == null) {
            Log.e(TAG, "getApiVersionCode: no profile service connected");
            return -1;
        }
        try {
            return this.profileService.getApiVersionCode();
        } catch (RemoteException e) {
            Log.e(TAG, "getApiVersionCode failed: " + e.getClass().getName());
            return -1;
        }
    }

    private String getSdkVersion() {
        PackageManager packageManager;
        try {
            packageManager = this.context.getPackageManager();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "name not found: " + e.getMessage());
        }
        if (packageManager == null) {
            Log.e(TAG, "Failed to get packageManager.");
            return DEFAULT_WRONG_SDK_VERSION;
        }
        ApplicationInfo applicationInfo = packageManager.getApplicationInfo(this.context.getPackageName(), 128);
        Bundle bundle = applicationInfo != null ? applicationInfo.metaData : null;
        if (bundle != null) {
            return bundle.getString(DEVICE_PROFILE_SDK_VERSION);
        }
        return DEFAULT_WRONG_SDK_VERSION;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean putDevice(DeviceProfile deviceProfile) {
        BaseUtil.markCallInterface("putDevice", this.callingPkgName);
        if (deviceProfile == null) {
            Log.e(TAG, "putDevice: empty deviceProfile");
            return false;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        deviceProfile.addEntityInfo("hiv", this.sdkVersionCode);
        boolean putDevice = generateStore.putDevice(this.callingPkgName, deviceProfile);
        Log.i(TAG, "putDevice result is " + putDevice);
        return putDevice;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean putDevice(DeviceProfile deviceProfile, Bundle bundle) {
        BaseUtil.markCallInterface("putDeviceWithExtraInfo", this.callingPkgName);
        if (deviceProfile == null) {
            Log.e(TAG, "putDeviceWithExtraInfo: empty deviceProfile");
            return false;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        deviceProfile.addEntityInfo("hiv", this.sdkVersionCode);
        boolean putDeviceWithExtraInfo = generateStore.putDeviceWithExtraInfo(this.callingPkgName, deviceProfile, bundle);
        Log.i(TAG, "putDeviceWithExtraInfo result is " + putDeviceWithExtraInfo);
        return putDeviceWithExtraInfo;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean putServiceOfDevice(String str, ServiceProfile serviceProfile) {
        BaseUtil.markCallInterface("putServiceOfDevice", this.callingPkgName);
        if (TextUtils.isEmpty(str) || serviceProfile == null) {
            Log.e(TAG, "putServiceOfDevice: invalid parameter.");
            return false;
        }
        boolean putServiceOfDevice = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk).putServiceOfDevice(this.callingPkgName, str, serviceProfile);
        Log.i(TAG, "putServiceOfDevice result is " + putServiceOfDevice);
        return putServiceOfDevice;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean putServiceOfDevice(String str, ServiceProfile serviceProfile, Bundle bundle) {
        BaseUtil.markCallInterface("putServiceOfDeviceWithExtraInfo", this.callingPkgName);
        if (TextUtils.isEmpty(str) || serviceProfile == null) {
            Log.e(TAG, "putServiceOfDeviceWithExtraInfo: invalid parameter.");
            return false;
        }
        boolean putServiceOfDeviceWithExtraInfo = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk).putServiceOfDeviceWithExtraInfo(this.callingPkgName, str, serviceProfile, bundle);
        Log.i(TAG, "putServiceOfDeviceWithExtraInfo result is " + putServiceOfDeviceWithExtraInfo);
        return putServiceOfDeviceWithExtraInfo;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean putServiceCharacteristic(String str, String str2, ServiceCharacteristicProfile serviceCharacteristicProfile) {
        BaseUtil.markCallInterface("putServiceCharacteristic", this.callingPkgName);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || serviceCharacteristicProfile == null) {
            Log.e(TAG, "putServiceCharacteristic: invalid parameter.");
            return false;
        }
        boolean putServiceCharacteristic = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk).putServiceCharacteristic(this.callingPkgName, str, str2, serviceCharacteristicProfile);
        Log.i(TAG, "putServiceCharacteristic result is " + putServiceCharacteristic);
        return putServiceCharacteristic;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean putServiceCharacteristic(String str, String str2, ServiceCharacteristicProfile serviceCharacteristicProfile, Bundle bundle) {
        BaseUtil.markCallInterface("putServiceCharacteristicWithExtraInfo", this.callingPkgName);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || serviceCharacteristicProfile == null) {
            Log.e(TAG, "putServiceCharacteristicWithExtraInfo: invalid parameter.");
            return false;
        }
        boolean putServiceCharacteristicWithExtraInfo = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk).putServiceCharacteristicWithExtraInfo(this.callingPkgName, str, str2, serviceCharacteristicProfile, bundle);
        Log.i(TAG, "putServiceCharacteristicWithExtraInfo result is " + putServiceCharacteristicWithExtraInfo);
        return putServiceCharacteristicWithExtraInfo;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public List<DeviceProfile> getDevices(boolean z, List<String> list) {
        BaseUtil.markCallInterface("getDevices", this.callingPkgName);
        int dataSource = getDataSource(list);
        if (dataSource == -1) {
            Log.e(TAG, "getDevices: data source is invalid.");
            return null;
        }
        if (hasP2pSync(z, dataSource)) {
            Log.e(TAG, "getDevices: don't support p2p synchronization. ");
            return null;
        }
        List<DeviceProfile> devices = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk).getDevices(this.callingPkgName, z, dataSource);
        Log.i(TAG, "getDevices result is " + size(devices));
        return devices;
    }

    private boolean hasP2pSync(boolean z, int i) {
        return z && hasP2pDataSource(i);
    }

    private int size(Collection<?> collection) {
        if (collection == null) {
            return 0;
        }
        return collection.size();
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public DeviceProfileEx getHostDevice(List<String> list, List<String> list2) {
        BaseUtil.markCallInterface("getHostDevice", this.callingPkgName);
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        DeviceProfileEx hostDevice = ((ProfileStoreExtendProxy) generateStore).getHostDevice(this.callingPkgName, list, list2);
        StringBuilder sb = new StringBuilder("getHostDevice result is ");
        sb.append(hostDevice != null);
        Log.i(TAG, sb.toString());
        return hostDevice;
    }

    private int getDataSource(List<String> list) {
        char c;
        if (list == null || list.isEmpty()) {
            return 1;
        }
        int i = 0;
        for (String str : list) {
            str.hashCode();
            int hashCode = str.hashCode();
            if (hashCode == -866597431) {
                if (str.equals(DEFAULT_TRUST_DOMAIN)) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode != -10177988) {
                if (hashCode == 1118821858 && str.equals(CLOUD_LOCAL_DOMAIN)) {
                    c = 2;
                }
                c = 65535;
            } else {
                if (str.equals(P2P_TRUST_DOMAIN)) {
                    c = 1;
                }
                c = 65535;
            }
            if (c == 0) {
                i |= 2;
            } else if (c == 1) {
                i |= 4;
            } else {
                if (c != 2) {
                    return -1;
                }
                i |= 1;
            }
        }
        Log.i(TAG, "data source is " + i);
        return i;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public List<DeviceProfile> getDevicesById(String str, boolean z, List<String> list) {
        BaseUtil.markCallInterface("getDevicesById", this.callingPkgName);
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "getDevicesById: invalid parameter.");
            return null;
        }
        int dataSource = getDataSource(list);
        if (dataSource == -1) {
            Log.e(TAG, "getDevicesById: data source is invalid.");
            return null;
        }
        if (hasP2pSync(z, dataSource)) {
            Log.e(TAG, "getDevicesById: don't support p2p synchronization. ");
            return null;
        }
        List<DeviceProfile> devicesById = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk).getDevicesById(this.callingPkgName, str, z, dataSource);
        Log.i(TAG, "getDevicesById result is " + size(devicesById));
        return devicesById;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public List<DeviceProfile> getDevicesByType(String str, boolean z, List<String> list) {
        BaseUtil.markCallInterface("getDevicesByType", this.callingPkgName);
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "getDevicesByType: invalid parameter.");
            return null;
        }
        int dataSource = getDataSource(list);
        if (dataSource == -1) {
            Log.e(TAG, "getDevicesByType: data source is invalid.");
            return null;
        }
        if (hasP2pSync(z, dataSource)) {
            Log.e(TAG, "getDevicesByType: don't support p2p synchronization. ");
            return null;
        }
        List<DeviceProfile> devicesByType = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk).getDevicesByType(this.callingPkgName, str, z, dataSource);
        Log.i(TAG, "getDevicesByType result is " + size(devicesByType));
        return devicesByType;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    @Deprecated
    public List<ServiceProfile> getServicesOfDevice(String str, boolean z, List<String> list) {
        BaseUtil.markCallInterface("getServicesOfDevice", this.callingPkgName);
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "getServicesOfDevice: invalid parameter.");
            return null;
        }
        int dataSource = getDataSource(list);
        if (dataSource == -1) {
            Log.e(TAG, "getServicesOfDevice: data source is invalid.");
            return null;
        }
        if (hasP2pSync(z, dataSource)) {
            Log.e(TAG, "getServicesOfDevice: don't support p2p synchronization.");
            return Collections.emptyList();
        }
        List<ServiceProfile> servicesOfDevice = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk).getServicesOfDevice(this.callingPkgName, str, z, dataSource);
        Log.i(TAG, "getServicesOfDevice result is " + size(servicesOfDevice));
        return servicesOfDevice;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public List<ServiceProfile> getServicesOfDevice(String str, boolean z, List<String> list, Bundle bundle) throws ProfileGeneralException {
        BaseUtil.markCallInterface("getServicesOfDevice with extra info", this.callingPkgName);
        if (TextUtils.isEmpty(str) || bundle == null || bundle.isEmpty()) {
            Log.e(TAG, "getServicesOfDevice: invalid parameter.");
            return null;
        }
        int dataSource = getDataSource(list);
        if (dataSource == -1) {
            Log.e(TAG, "getServicesOfDevice: data source is invalid.");
            return null;
        }
        if (hasP2pSync(z, dataSource)) {
            Log.e(TAG, "getServicesOfDevice: don't support p2p synchronization.");
            return null;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        List<ServiceProfile> servicesOfDevice = ((ProfileStoreExtendProxy) generateStore).getServicesOfDevice(this.callingPkgName, str, z, dataSource, bundle);
        Log.i(TAG, "getServicesOfDevice result is " + size(servicesOfDevice));
        return servicesOfDevice;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    @Deprecated
    public ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, boolean z, List<String> list) {
        BaseUtil.markCallInterface("getServiceCharacteristics", this.callingPkgName);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Log.e(TAG, "getServiceCharacteristics: invalid parameter.");
            return null;
        }
        int dataSource = getDataSource(list);
        if (dataSource == -1) {
            Log.e(TAG, "getServiceCharacteristics: data source is invalid.");
            return null;
        }
        if (hasP2pSync(z, dataSource)) {
            Log.e(TAG, "getServiceCharacteristics: don't support p2p synchronization.");
            return null;
        }
        ServiceCharacteristicProfile serviceCharacteristics = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk).getServiceCharacteristics(this.callingPkgName, str, str2, z, dataSource);
        Log.i(TAG, "getServiceCharacteristics result is " + ((serviceCharacteristics == null || serviceCharacteristics.getProfile() == null) ? 0 : serviceCharacteristics.getProfile().size()));
        return serviceCharacteristics;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, boolean z, List<String> list, Bundle bundle) {
        BaseUtil.markCallInterface("getServiceCharacteristics with extra info", this.callingPkgName);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Log.e(TAG, "getServiceCharacteristics: invalid parameter.");
            return null;
        }
        int dataSource = getDataSource(list);
        if (dataSource == -1) {
            Log.e(TAG, "getServiceCharacteristics: invalid dataSource");
            return null;
        }
        if (hasP2pSync(z, dataSource)) {
            Log.e(TAG, "getServiceCharacteristics: don't support p2p synchronization.");
            return null;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        ServiceCharacteristicProfile serviceCharacteristics = ((ProfileStoreExtendProxy) generateStore).getServiceCharacteristics(this.callingPkgName, str, str2, z, dataSource, bundle);
        Log.i(TAG, "getServiceCharacteristics result is " + ((serviceCharacteristics == null || serviceCharacteristics.getProfile() == null) ? 0 : serviceCharacteristics.getProfile().size()));
        return serviceCharacteristics;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean deleteDevice(String str) {
        BaseUtil.markCallInterface("deleteDevice", this.callingPkgName);
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "deleteDevice: empty devId");
            return false;
        }
        boolean deleteDevice = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk).deleteDevice(this.callingPkgName, str);
        Log.i(TAG, "deleteDevice result is " + deleteDevice);
        return deleteDevice;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    @Deprecated
    public boolean deleteServiceOfDevice(String str, String str2) {
        BaseUtil.markCallInterface("deleteServiceOfDevice", this.callingPkgName);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Log.e(TAG, "deleteServiceOfDevice: empty devId or sid");
            return false;
        }
        boolean deleteServiceOfDevice = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk).deleteServiceOfDevice(this.callingPkgName, str, str2);
        Log.i(TAG, "deleteServiceOfDevice result is " + deleteServiceOfDevice);
        return deleteServiceOfDevice;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public Bundle deleteServiceOfDevice(String str, List<String> list, boolean z, Bundle bundle) {
        BaseUtil.markCallInterface("deleteServiceOfDevice with sid list", this.callingPkgName);
        Bundle bundle2 = new Bundle();
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "deleteServiceOfDevice: empty devId");
            bundle2.putInt("retCode", 2);
            return bundle2;
        }
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "deleteServiceOfDevice: empty sidList");
            bundle2.putInt("retCode", 3);
            return bundle2;
        }
        if (list.size() > 20) {
            Log.e(TAG, "deleteServiceOfDevice: sidList over max num to delete");
            bundle2.putInt("retCode", 4);
            return bundle2;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        Bundle deleteServiceOfDevice = ((ProfileStoreExtendProxy) generateStore).deleteServiceOfDevice(this.callingPkgName, str, list, z, bundle);
        if (deleteServiceOfDevice == null) {
            Log.i(TAG, "empty result");
            bundle2.putInt("retCode", 1);
        } else {
            bundle2 = deleteServiceOfDevice;
        }
        Log.i(TAG, "deleteServiceOfDevice with sidList result is " + bundle2.getInt("retCode", 1));
        return bundle2;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean deleteServiceCharacteristic(String str, String str2, String str3) {
        BaseUtil.markCallInterface("deleteServiceCharacteristic", this.callingPkgName);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            Log.e(TAG, "deleteServiceCharacteristic: invalid parameter.");
            return false;
        }
        boolean deleteServiceCharacteristic = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk).deleteServiceCharacteristic(this.callingPkgName, str, str2, str3);
        Log.i(TAG, "deleteServiceCharacteristic result is " + deleteServiceCharacteristic);
        return deleteServiceCharacteristic;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean getAvailableDeviceInfo(boolean z, String str, GetDeviceInfoListener getDeviceInfoListener) {
        BaseUtil.markCallInterface("getAvailableDeviceInfo", this.callingPkgName);
        if (this.profileService == null) {
            Log.e(TAG, "getAvailableDeviceInfo: no profile service connected");
            return false;
        }
        try {
            return this.profileService.getAvailableDeviceInfos(this.callingPkgName, z, str, getDeviceInfoListener);
        } catch (RemoteException e) {
            Log.e(TAG, "getAvailableDeviceInfo: error is" + e.getClass().getSimpleName());
            return false;
        } catch (RuntimeException unused) {
            Log.e(TAG, "getAvailableDeviceInfo: with Unexpected runtimeException");
            return false;
        }
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean getAvailableDeviceInfoEx(boolean z, String str, List<String> list, List<String> list2, GetDeviceInfoListenerEx getDeviceInfoListenerEx) {
        BaseUtil.markCallInterface("getAvailableDeviceInfoEx", this.callingPkgName);
        if (this.profileService == null) {
            Log.e(TAG, "getAvailableDeviceInfoExs: no profile service connected");
            return false;
        }
        if ((list == null || list.isEmpty()) && (list2 == null || list2.isEmpty())) {
            Log.e(TAG, "getAvailableDeviceInfoExs: empty services and characters");
            return false;
        }
        try {
            return this.profileService.getAvailableDeviceInfoExs(this.callingPkgName, z, str, list, list2, getDeviceInfoListenerEx);
        } catch (RemoteException e) {
            Log.e(TAG, "getAvailableDeviceInfoEx: error is" + e.getClass().getSimpleName() + " " + e.getMessage());
            return false;
        } catch (RuntimeException unused) {
            Log.e(TAG, "getAvailableDeviceInfoEx: with Unexpected runtimeException");
            return false;
        }
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    @Deprecated
    public boolean syncDevicesInfo(String str, SyncMode syncMode, SyncDeviceInfoListener syncDeviceInfoListener, Bundle bundle) {
        BaseUtil.markCallInterface("deprecated syncDevicesInfo", this.callingPkgName);
        if (this.profileService == null) {
            Log.e(TAG, "deprecated syncDevicesInfo: no profile service connected");
            return false;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        return ((ProfileStoreExtendProxy) generateStore).syncDevicesInfos(this.callingPkgName, str, syncMode, syncDeviceInfoListener, bundle);
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean syncDevicesInfo(List<String> list, SyncMode syncMode, SyncDeviceInfoExListener syncDeviceInfoExListener, Bundle bundle) {
        BaseUtil.markCallInterface("new syncDevicesInfo", this.callingPkgName);
        if (this.profileService == null) {
            Log.e(TAG, "new syncDeviceInfoEx: no profile service connected");
            return false;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        return ((ProfileStoreExtendProxy) generateStore).syncDeviceInfoEx(this.callingPkgName, list, syncMode, syncDeviceInfoExListener, bundle);
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public List<DeviceProfile> getDevicesByTypeLocal(String str, List<String> list) {
        BaseUtil.markCallInterface("getDevicesByTypeLocal", this.callingPkgName);
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "getDevicesByTypeLocal: empty devType");
            return Collections.emptyList();
        }
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "getDevicesByTypeLocal: empty sources");
            return Collections.emptyList();
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        List<DeviceProfile> devicesByTypeLocal = ((ProfileStoreExtendProxy) generateStore).getDevicesByTypeLocal(this.callingPkgName, str, list);
        Log.i(TAG, "getDevicesByTypeLocal: result is " + size(devicesByTypeLocal));
        return devicesByTypeLocal;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public List<DeviceProfile> getDevicesByTypeLocal(String str) {
        BaseUtil.markCallInterface("getDevicesByTypeLocal", this.callingPkgName);
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "getDevicesByTypeLocal: empty devType");
            return Collections.emptyList();
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        List<DeviceProfile> devicesByTypeLocal = ((ProfileStoreExtendProxy) generateStore).getDevicesByTypeLocal(this.callingPkgName, str);
        Log.i(TAG, "getDevicesByTypeLocal: result is " + size(devicesByTypeLocal));
        return devicesByTypeLocal;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean putDeviceLocal(DeviceProfile deviceProfile) {
        BaseUtil.markCallInterface("putDeviceLocal", this.callingPkgName);
        if (deviceProfile == null) {
            Log.e(TAG, "putDeviceLocal: empty deviceProfile");
            return false;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        boolean putDeviceLocal = ((ProfileStoreExtendProxy) generateStore).putDeviceLocal(this.callingPkgName, deviceProfile);
        Log.i(TAG, "putDeviceLocal: result is " + putDeviceLocal);
        return putDeviceLocal;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean deleteDeviceLocal(String str) {
        BaseUtil.markCallInterface("deleteDeviceLocal", this.callingPkgName);
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "deleteDeviceLocal: empty devId");
            return false;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        boolean deleteDeviceLocal = ((ProfileStoreExtendProxy) generateStore).deleteDeviceLocal(this.callingPkgName, str);
        Log.i(TAG, "deleteDeviceLocal: result is " + deleteDeviceLocal);
        return deleteDeviceLocal;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean deleteDeviceLocal(String str, String str2) {
        BaseUtil.markCallInterface("deleteDeviceLocal", this.callingPkgName);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            Log.e(TAG, "deleteDeviceLocal: empty devId or sourceId");
            return false;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        boolean deleteDeviceLocal = ((ProfileStoreExtendProxy) generateStore).deleteDeviceLocal(this.callingPkgName, str, str2);
        Log.i(TAG, "deleteDeviceLocal: result is " + deleteDeviceLocal);
        return deleteDeviceLocal;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public Bundle subscribeDeviceProfile(List<SubscribeInfo> list, Bundle bundle) {
        BaseUtil.markCallInterface("subscribeDeviceProfile", this.callingPkgName);
        if (this.profileService == null) {
            Log.e(TAG, "subscribeDeviceProfile: no profile service connected");
            return Bundle.EMPTY;
        }
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "subscribeDeviceProfile: empty subscribeInfo");
            return Bundle.EMPTY;
        }
        try {
            return this.profileService.subscribeDeviceProfile(this.callingPkgName, list, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "subscribeDeviceProfile: error is" + e.getClass().getSimpleName() + " " + e.getMessage());
            return Bundle.EMPTY;
        } catch (RuntimeException unused) {
            Log.e(TAG, "subscribeDeviceProfile: with Unexpected runtimeException");
            return Bundle.EMPTY;
        }
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public Bundle subscribeDeviceProfile(List<com.huawei.profile.subscription.deviceinfo.SubscribeInfo> list, AbstractSubscribeProfileListener abstractSubscribeProfileListener, Bundle bundle) {
        BaseUtil.markCallInterface("subscribeDeviceProfile with listener", this.callingPkgName);
        if (list == null || list.isEmpty()) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("retCode", 3);
            return bundle2;
        }
        if (abstractSubscribeProfileListener == null) {
            Bundle bundle3 = new Bundle();
            bundle3.putInt("retCode", 4);
            return bundle3;
        }
        ComponentName componentName = new ComponentName(this.callingPkgName, abstractSubscribeProfileListener.getClass().getName());
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        Bundle subscribeDeviceProfileWithListener = ((ProfileStoreExtendProxy) generateStore).subscribeDeviceProfileWithListener(this.callingPkgName, list, componentName, bundle);
        showResult("subscribeDeviceProfile with listener", subscribeDeviceProfileWithListener);
        return subscribeDeviceProfileWithListener;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public Bundle unsubscribeDeviceProfile(List<SubscribeInfo> list, Bundle bundle) {
        BaseUtil.markCallInterface("unsubscribeDeviceProfile", this.callingPkgName);
        if (this.profileService == null) {
            Log.e(TAG, "unsubscribeDeviceProfile: no profile service connected");
            return Bundle.EMPTY;
        }
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "unsubscribeDeviceProfile: empty subscribeInfo");
            return Bundle.EMPTY;
        }
        try {
            return this.profileService.unsubscribeDeviceProfile(this.callingPkgName, list, bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "unsubscribeDeviceProfile failed: error is" + e.getClass().getSimpleName() + " " + e.getMessage());
            return Bundle.EMPTY;
        } catch (RuntimeException unused) {
            Log.e(TAG, "unsubscribeDeviceProfile failed with Unexpected runtimeException");
            return Bundle.EMPTY;
        }
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public Bundle unsubscribeDeviceProfile(List<com.huawei.profile.subscription.deviceinfo.SubscribeInfo> list, AbstractSubscribeProfileListener abstractSubscribeProfileListener, Bundle bundle) {
        BaseUtil.markCallInterface("unsubscribeDeviceProfile with listener", this.callingPkgName);
        if (list == null || list.isEmpty()) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("retCode", 3);
            return bundle2;
        }
        Log.i(TAG, "unsubscribe info size:" + list.size());
        if (abstractSubscribeProfileListener == null) {
            Bundle bundle3 = new Bundle();
            bundle3.putInt("retCode", 4);
            return bundle3;
        }
        ComponentName componentName = new ComponentName(this.callingPkgName, abstractSubscribeProfileListener.getClass().getName());
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        Bundle unsubscribeDeviceProfileWithListener = ((ProfileStoreExtendProxy) generateStore).unsubscribeDeviceProfileWithListener(this.callingPkgName, list, componentName, bundle);
        showResult("unsubscribeDeviceProfile with listener", unsubscribeDeviceProfileWithListener);
        return unsubscribeDeviceProfileWithListener;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public List<DeviceProfileEx> getDevicesByIdEx(List<String> list, boolean z, List<String> list2, Bundle bundle) {
        BaseUtil.markCallInterface("getDevicesByIdEx", this.callingPkgName);
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "getDevicesByIdEx: empty devIds");
            return Collections.emptyList();
        }
        int dataSource = getDataSource(list2);
        if (dataSource == -1) {
            Log.e(TAG, "getDevicesByIdEx: invalid dataSource");
            return Collections.emptyList();
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        List<DeviceProfileEx> devicesByIdEx = ((ProfileStoreExtendProxy) generateStore).getDevicesByIdEx(this.callingPkgName, list, z, dataSource, bundle);
        Log.i(TAG, "getDevicesByIdEx size is " + size(devicesByIdEx));
        return devicesByIdEx;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public List<DeviceProfileEx> getDevicesByTypeEx(List<String> list, boolean z, List<String> list2, Bundle bundle) {
        BaseUtil.markCallInterface("getDevicesByTypeEx", this.callingPkgName);
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "getDevicesByTypeEx: empty devTypes");
            return null;
        }
        int dataSource = getDataSource(list2);
        if (dataSource == -1) {
            Log.e(TAG, "getDevicesByTypeEx: invalid dataSource");
            return null;
        }
        if (z) {
            Log.e(TAG, "getDevicesByTypeEx: invalid isSync: true");
            return null;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        List<DeviceProfileEx> devicesByTypeEx = ((ProfileStoreExtendProxy) generateStore).getDevicesByTypeEx(this.callingPkgName, list, z, dataSource, bundle);
        Log.i(TAG, "getDevicesByTypeEx result is " + size(devicesByTypeEx));
        return devicesByTypeEx;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean findDevice(boolean z, String str, GetDeviceInfoListenerEx getDeviceInfoListenerEx, Bundle bundle) {
        BaseUtil.markCallInterface("findDevice", this.callingPkgName);
        if (this.profileService == null) {
            Log.e(TAG, "findDevice: no profile service connected");
            return false;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        boolean findDevice = ((ProfileStoreExtendProxy) generateStore).findDevice(this.callingPkgName, z, str, getDeviceInfoListenerEx, bundle);
        Log.i(TAG, "findDevice result is " + findDevice);
        return findDevice;
    }

    @Override // com.huawei.profile.client.profile.ISwitchClient
    public boolean setSwitch(boolean z) {
        BaseUtil.markCallInterface(" setSwitch", this.callingPkgName);
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        boolean z2 = ((ProfileStoreExtendProxy) generateStore).setSwitch(this.callingPkgName, z);
        Log.i(TAG, "setSwitch result is " + z2);
        return z2;
    }

    @Override // com.huawei.profile.client.profile.IDeviceListClient
    public Bundle addDeviceList(List<DeviceProfile> list, int i, Bundle bundle) {
        BaseUtil.markCallInterface("addDeviceList, type:" + i, this.callingPkgName);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("retCode", 1);
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "addDeviceList: empty deviceList");
            return bundle2;
        }
        if (!DeviceListType.isValidListType(i)) {
            Log.e(TAG, "addDeviceList: invalid listType");
            return bundle2;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        Bundle addDeviceList = ((ProfileStoreExtendProxy) generateStore).addDeviceList(this.callingPkgName, list, i, bundle);
        showResult("addDeviceList", addDeviceList);
        return addDeviceList;
    }

    @Override // com.huawei.profile.client.profile.IDeviceListClient
    public Bundle deleteDeviceListByType(int i, Bundle bundle) {
        BaseUtil.markCallInterface("deleteDeviceListByType, type:" + i, this.callingPkgName);
        if (!DeviceListType.isValidListTypes(i)) {
            Log.e(TAG, "deleteDeviceListByType: invalid listTypes");
            Bundle bundle2 = new Bundle();
            bundle2.putInt("retCode", 1);
            return bundle2;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        Bundle deleteDeviceListByType = ((ProfileStoreExtendProxy) generateStore).deleteDeviceListByType(this.callingPkgName, i, bundle);
        showResult("deleteDeviceListByType", deleteDeviceListByType);
        return deleteDeviceListByType;
    }

    @Override // com.huawei.profile.client.profile.IDeviceListClient
    public Bundle deleteDeviceListById(List<String> list, int i, Bundle bundle) {
        BaseUtil.markCallInterface("deleteDeviceListById, type:" + i, this.callingPkgName);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("retCode", 1);
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "deleteDeviceListById: empty deviceIdList");
            return bundle2;
        }
        if (!DeviceListType.isValidListType(i)) {
            Log.e(TAG, "deleteDeviceListById: invalid listType");
            return bundle2;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        Bundle deleteDeviceListById = ((ProfileStoreExtendProxy) generateStore).deleteDeviceListById(this.callingPkgName, list, i, bundle);
        showResult("deleteDeviceListById", deleteDeviceListById);
        return deleteDeviceListById;
    }

    @Override // com.huawei.profile.client.profile.IDeviceListClient
    public List<DeviceProfile> queryDeviceList(int i, Bundle bundle) {
        BaseUtil.markCallInterface("queryDeviceList, type:" + i, this.callingPkgName);
        if (!DeviceListType.isValidListType(i)) {
            Log.e(TAG, "queryDeviceList: invalid listType");
            return null;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        List<DeviceProfile> queryDeviceList = ((ProfileStoreExtendProxy) generateStore).queryDeviceList(this.callingPkgName, i, bundle);
        Log.i(TAG, "queryDeviceList result: size is " + size(queryDeviceList));
        return queryDeviceList;
    }

    @Override // com.huawei.profile.client.profile.IDeviceListClient
    public Bundle subscribeDeviceList(int i, AbstractSubscribeProfileListener abstractSubscribeProfileListener, Bundle bundle) {
        Bundle bundle2;
        BaseUtil.markCallInterface("subscribeDeviceList, type:" + i, this.callingPkgName);
        Bundle bundle3 = new Bundle();
        bundle3.putInt("retCode", 1);
        if (!DeviceListType.isValidListTypes(i)) {
            Log.e(TAG, "subscribeDeviceList: invalid listTypes");
            return bundle3;
        }
        if (abstractSubscribeProfileListener == null) {
            Log.e(TAG, "subscribeDeviceList: empty listener");
            return bundle3;
        }
        ComponentName componentName = new ComponentName(this.callingPkgName, abstractSubscribeProfileListener.getClass().getName());
        if (bundle == null) {
            bundle2 = new Bundle();
        } else {
            Object clone = bundle.clone();
            bundle2 = clone instanceof Bundle ? (Bundle) clone : null;
        }
        bundle2.putParcelable("subscriberComponent", componentName);
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        Bundle subscribeDeviceList = ((ProfileStoreExtendProxy) generateStore).subscribeDeviceList(this.callingPkgName, i, bundle2);
        showResult("subscribeDeviceList", subscribeDeviceList);
        return subscribeDeviceList;
    }

    @Override // com.huawei.profile.client.profile.IDeviceListClient
    public Bundle unsubscribeDeviceList(int i, AbstractSubscribeProfileListener abstractSubscribeProfileListener, Bundle bundle) {
        Bundle bundle2;
        BaseUtil.markCallInterface("unsubscribeDeviceList, type:" + i, this.callingPkgName);
        Bundle bundle3 = new Bundle();
        bundle3.putInt("retCode", 1);
        if (!DeviceListType.isValidListTypes(i)) {
            Log.e(TAG, "unsubscribeDeviceList: invalid listTypes");
            return bundle3;
        }
        if (abstractSubscribeProfileListener == null) {
            Log.e(TAG, "unsubscribeDeviceList: empty listener");
            return bundle3;
        }
        ComponentName componentName = new ComponentName(this.callingPkgName, abstractSubscribeProfileListener.getClass().getName());
        if (bundle == null) {
            bundle2 = new Bundle();
        } else {
            Object clone = bundle.clone();
            bundle2 = clone instanceof Bundle ? (Bundle) clone : null;
        }
        bundle2.putParcelable("subscriberComponent", componentName);
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        Bundle unsubscribeDeviceList = ((ProfileStoreExtendProxy) generateStore).unsubscribeDeviceList(this.callingPkgName, i, bundle2);
        showResult("unsubscribeDeviceList", unsubscribeDeviceList);
        return unsubscribeDeviceList;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public List<DeviceProfileEx> getDevicesEx(boolean z, List<String> list, Bundle bundle) {
        BaseUtil.markCallInterface("getDevicesEx", this.callingPkgName);
        if (z) {
            Log.e(TAG, "getDevicesEx: invalid isSync: true");
            return Collections.emptyList();
        }
        if (bundle == null || bundle.isEmpty()) {
            Log.e(TAG, "getDevicesEx: empty extraInfo");
            return Collections.emptyList();
        }
        int dataSource = getDataSource(list);
        if (dataSource == -1) {
            Log.e(TAG, "getDevicesEx: invalid dataSources");
            return Collections.emptyList();
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        List<DeviceProfileEx> devicesEx = ((ProfileStoreExtendProxy) generateStore).getDevicesEx(this.callingPkgName, false, dataSource, bundle);
        Log.i(TAG, "getDevicesEx result is " + size(devicesEx));
        return devicesEx;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public String subscribeEvents(List<EventInfo> list, AbstractSubscribeProfileListener abstractSubscribeProfileListener, String str) {
        BaseUtil.markCallInterface("subscribeEvents", this.callingPkgName);
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "subscribeEvent: eventInfoList are null or empty");
            return OpResult.EVENTS_SUBSCRIBE_FAILED_RESULT;
        }
        if (abstractSubscribeProfileListener == null) {
            Log.e(TAG, "subscribeEvents: listener is null");
            return OpResult.EVENTS_SUBSCRIBE_FAILED_RESULT;
        }
        ComponentName componentName = new ComponentName(this.callingPkgName, abstractSubscribeProfileListener.getClass().getName());
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        String subscribeEvents = ((ProfileStoreExtendProxy) generateStore).subscribeEvents(this.callingPkgName, list, componentName, str);
        Log.i(TAG, "subscribeEvents: result is " + subscribeEvents);
        return subscribeEvents;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public String unsubscribeEvents(List<EventInfo> list, AbstractSubscribeProfileListener abstractSubscribeProfileListener, String str) {
        BaseUtil.markCallInterface("unsubscribeEvents", this.callingPkgName);
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "unsubscribeEvents: eventInfoList are null or empty");
            return OpResult.EVENTS_SUBSCRIBE_FAILED_RESULT;
        }
        if (abstractSubscribeProfileListener == null) {
            Log.e(TAG, "unsubscribeEvents: listener is null");
            return OpResult.EVENTS_SUBSCRIBE_FAILED_RESULT;
        }
        ComponentName componentName = new ComponentName(this.callingPkgName, abstractSubscribeProfileListener.getClass().getName());
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        String unsubscribeEvents = ((ProfileStoreExtendProxy) generateStore).unsubscribeEvents(this.callingPkgName, list, componentName, str);
        Log.i(TAG, "unsubscribeEvents: result is " + unsubscribeEvents);
        return unsubscribeEvents;
    }

    @Override // com.huawei.profile.client.profile.IDeviceListClient
    public Bundle updateDeviceListInfo(List<DeviceProfile> list, int i, Bundle bundle) {
        BaseUtil.markCallInterface("updateDeviceListInfo, type:" + i, this.callingPkgName);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("retCode", 1);
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "updateDeviceListInfo: empty deviceList");
            return bundle2;
        }
        if (!DeviceListType.isValidListType(i)) {
            Log.e(TAG, "updateDeviceListInfo: invalid listType");
            return bundle2;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        Bundle updateDeviceListInfo = ((ProfileStoreExtendProxy) generateStore).updateDeviceListInfo(this.callingPkgName, list, i, bundle);
        showResult("updateDeviceListInfo", updateDeviceListInfo);
        return updateDeviceListInfo;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public boolean putDeviceEx(DeviceProfileEx deviceProfileEx, PutDeviceExListener putDeviceExListener) {
        BaseUtil.markCallInterface("putDeviceEx", this.callingPkgName);
        if (deviceProfileEx == null || putDeviceExListener == null) {
            Log.e(TAG, "putDeviceEx: invalid parameter.");
            return false;
        }
        if (deviceProfileEx.getIsNeedCloud()) {
            Log.e(TAG, "putDeviceEx not support cloud sync");
            return false;
        }
        if (deviceProfileEx.getServices() != null) {
            if (deviceProfileEx.getServices().size() > MAX_SERVICES_SIZE) {
                Log.e(TAG, "putDeviceEx: service list size exceed threshold");
                return false;
            }
            Iterator<ServiceProfileEx> it = deviceProfileEx.getServices().iterator();
            while (it.hasNext()) {
                ServiceCharacteristicProfile characters = it.next().getCharacters();
                if (characters != null && characters.getProfile() != null && characters.getProfile().size() > 200) {
                    Log.e(TAG, "putDeviceEx: characters size exceed threshold");
                    return false;
                }
            }
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        deviceProfileEx.addEntityInfo("hiv", this.sdkVersionCode);
        boolean putDeviceEx = ((ProfileStoreExtendProxy) generateStore).putDeviceEx(this.callingPkgName, deviceProfileEx, putDeviceExListener);
        Log.i(TAG, "putDeviceEx result is " + putDeviceEx);
        return putDeviceEx;
    }

    @Override // com.huawei.profile.client.profile.IProfileClient
    public List<DeviceProfile> getDevices(boolean z, List<String> list, Bundle bundle) {
        BaseUtil.markCallInterface("getDevices with extra info", this.callingPkgName);
        if (bundle == null || bundle.isEmpty()) {
            Log.e(TAG, "getDevices: extra info is invalid.");
            return null;
        }
        int dataSource = getDataSource(list);
        if (dataSource == -1) {
            Log.e(TAG, "getDevices: data source is invalid.");
            return null;
        }
        if (hasP2pSync(z, dataSource)) {
            Log.e(TAG, "getDevices: don't support p2p synchronization.");
            return null;
        }
        ProfileStoreBaseProxy generateStore = this.profileStoreFactory.generateStore(this.context, this.profileService, this.isUseProfileApk);
        assertInstanceOfProfileStoreExtendProxy(generateStore);
        List<DeviceProfile> devices = ((ProfileStoreExtendProxy) generateStore).getDevices(this.callingPkgName, z, dataSource, bundle);
        Log.i(TAG, "getDevices result is " + size(devices));
        return devices;
    }

    private void showResult(String str, Bundle bundle) {
        if (bundle == null) {
            Log.i(TAG, "call " + str + " result is null");
            return;
        }
        Log.i(TAG, "call " + str + " result code: " + bundle.getInt("retCode", 1));
    }

    private void assertInstanceOfProfileStoreExtendProxy(ProfileStoreBaseProxy profileStoreBaseProxy) {
        if (!(profileStoreBaseProxy instanceof ProfileStoreExtendProxy)) {
            throw new ProfileGeneralException(1, OBTAIN_EXTEND_PROXY_FAILED);
        }
    }

    class ProfileServiceHandler implements InvocationHandler {
        private IProfileServiceCall profileServiceCall;

        ProfileServiceHandler(IProfileServiceCall iProfileServiceCall) {
            this.profileServiceCall = iProfileServiceCall;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                return method.invoke(this.profileServiceCall, objArr);
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof DeadObjectException) {
                    Log.e(ProfileClient.TAG, ProfileClient.DEAD_OBJECT_EXCEPTION);
                    ProfileClient.this.disconnect();
                }
                if (cause instanceof RuntimeException) {
                    throw new RemoteException("Unknown error with Unexpected runtimeException");
                }
                throw cause;
            }
        }
    }
}
