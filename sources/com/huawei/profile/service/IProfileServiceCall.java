package com.huawei.profile.service;

import android.content.ComponentName;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.profile.container.ObjectContainer;
import com.huawei.profile.profile.DeviceProfile;
import com.huawei.profile.profile.DeviceProfileEx;
import com.huawei.profile.profile.ServiceCharacteristicProfile;
import com.huawei.profile.profile.ServiceProfile;
import com.huawei.profile.profile.StreamData;
import com.huawei.profile.profile.SubscribeInfo;
import com.huawei.profile.profile.SyncMode;
import com.huawei.profile.service.GetDeviceInfoListener;
import com.huawei.profile.service.GetDeviceInfoListenerEx;
import com.huawei.profile.service.PutDeviceExListener;
import com.huawei.profile.service.SyncDeviceInfoExListener;
import com.huawei.profile.service.SyncDeviceInfoListener;
import com.huawei.profile.subscription.event.EventInfo;
import java.util.List;

/* loaded from: classes6.dex */
public interface IProfileServiceCall extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements IProfileServiceCall {
        @Override // com.huawei.profile.service.IProfileServiceCall
        public Bundle addDeviceList(String str, List<DeviceProfile> list, int i, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean deleteDevice(String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public Bundle deleteDeviceListById(String str, List<String> list, int i, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public Bundle deleteDeviceListByType(String str, int i, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean deleteDeviceLocal(String str, String str2, String str3) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean deleteServiceCharacteristic(String str, String str2, String str3, String str4) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean deleteServiceOfDevice(String str, String str2, String str3) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public Bundle deleteServiceOfDeviceWithExtraInfo(String str, String str2, List<String> list, boolean z, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean findDevice(String str, boolean z, String str2, GetDeviceInfoListenerEx getDeviceInfoListenerEx, Bundle bundle) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public String getApiVersion() throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public int getApiVersionCode() throws RemoteException {
            return 0;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean getAvailableDeviceInfoExs(String str, boolean z, String str2, List<String> list, List<String> list2, GetDeviceInfoListenerEx getDeviceInfoListenerEx) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean getAvailableDeviceInfos(String str, boolean z, String str2, GetDeviceInfoListener getDeviceInfoListener) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public ObjectContainer getDevices(String str, boolean z, int i) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public ObjectContainer getDevicesById(String str, String str2, boolean z, int i) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public List<DeviceProfileEx> getDevicesByIdEx(String str, List<String> list, boolean z, int i, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public ObjectContainer getDevicesByType(String str, String str2, boolean z, int i) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public List<DeviceProfileEx> getDevicesByTypeEx(String str, List<String> list, boolean z, int i, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public ObjectContainer getDevicesByTypeLocal(String str, String str2, List<String> list) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public List<DeviceProfileEx> getDevicesEx(String str, boolean z, int i, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public ObjectContainer getDevicesWithExtraInfo(String str, boolean z, int i, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public DeviceProfileEx getHostDevice(String str, List<String> list, List<String> list2) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, String str3, boolean z, int i) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public ServiceCharacteristicProfile getServiceCharacteristicsWithExtraInfo(String str, String str2, String str3, boolean z, int i, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public ObjectContainer getServicesOfDevice(String str, String str2, boolean z, int i) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public Bundle getServicesOfDeviceWithExtraInfo(String str, String str2, boolean z, int i, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean putDevice(String str, DeviceProfile deviceProfile) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean putDeviceEx(String str, StreamData streamData, PutDeviceExListener putDeviceExListener) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean putDeviceLocal(String str, DeviceProfile deviceProfile) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean putDeviceWithExtraInfo(String str, DeviceProfile deviceProfile, Bundle bundle) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean putServiceCharacteristic(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean putServiceCharacteristicWithExtraInfo(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile, Bundle bundle) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean putServiceOfDevice(String str, String str2, ServiceProfile serviceProfile) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean putServiceOfDeviceWithExtraInfo(String str, String str2, ServiceProfile serviceProfile, Bundle bundle) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public List<DeviceProfile> queryDeviceList(String str, int i, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean setSwitch(String str, boolean z) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public int setSwitchFlag(String str, boolean z) throws RemoteException {
            return 0;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public Bundle subscribeDeviceList(String str, int i, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public Bundle subscribeDeviceProfile(String str, List<SubscribeInfo> list, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public Bundle subscribeDeviceProfileWithListener(String str, List<com.huawei.profile.subscription.deviceinfo.SubscribeInfo> list, ComponentName componentName, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public String subscribeEvents(String str, List<EventInfo> list, ComponentName componentName, String str2) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean syncDeviceInfoEx(String str, List<String> list, SyncMode syncMode, SyncDeviceInfoExListener syncDeviceInfoExListener, Bundle bundle) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public boolean syncDeviceInfos(String str, String str2, SyncMode syncMode, SyncDeviceInfoListener syncDeviceInfoListener, Bundle bundle) throws RemoteException {
            return false;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public Bundle unsubscribeDeviceList(String str, int i, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public Bundle unsubscribeDeviceProfile(String str, List<SubscribeInfo> list, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public Bundle unsubscribeDeviceProfileWithListener(String str, List<com.huawei.profile.subscription.deviceinfo.SubscribeInfo> list, ComponentName componentName, Bundle bundle) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public String unsubscribeEvents(String str, List<EventInfo> list, ComponentName componentName, String str2) throws RemoteException {
            return null;
        }

        @Override // com.huawei.profile.service.IProfileServiceCall
        public Bundle updateDeviceListInfo(String str, List<DeviceProfile> list, int i, Bundle bundle) throws RemoteException {
            return null;
        }
    }

    Bundle addDeviceList(String str, List<DeviceProfile> list, int i, Bundle bundle) throws RemoteException;

    boolean deleteDevice(String str, String str2) throws RemoteException;

    Bundle deleteDeviceListById(String str, List<String> list, int i, Bundle bundle) throws RemoteException;

    Bundle deleteDeviceListByType(String str, int i, Bundle bundle) throws RemoteException;

    boolean deleteDeviceLocal(String str, String str2, String str3) throws RemoteException;

    boolean deleteServiceCharacteristic(String str, String str2, String str3, String str4) throws RemoteException;

    boolean deleteServiceOfDevice(String str, String str2, String str3) throws RemoteException;

    Bundle deleteServiceOfDeviceWithExtraInfo(String str, String str2, List<String> list, boolean z, Bundle bundle) throws RemoteException;

    boolean findDevice(String str, boolean z, String str2, GetDeviceInfoListenerEx getDeviceInfoListenerEx, Bundle bundle) throws RemoteException;

    String getApiVersion() throws RemoteException;

    int getApiVersionCode() throws RemoteException;

    boolean getAvailableDeviceInfoExs(String str, boolean z, String str2, List<String> list, List<String> list2, GetDeviceInfoListenerEx getDeviceInfoListenerEx) throws RemoteException;

    boolean getAvailableDeviceInfos(String str, boolean z, String str2, GetDeviceInfoListener getDeviceInfoListener) throws RemoteException;

    ObjectContainer getDevices(String str, boolean z, int i) throws RemoteException;

    ObjectContainer getDevicesById(String str, String str2, boolean z, int i) throws RemoteException;

    List<DeviceProfileEx> getDevicesByIdEx(String str, List<String> list, boolean z, int i, Bundle bundle) throws RemoteException;

    ObjectContainer getDevicesByType(String str, String str2, boolean z, int i) throws RemoteException;

    List<DeviceProfileEx> getDevicesByTypeEx(String str, List<String> list, boolean z, int i, Bundle bundle) throws RemoteException;

    ObjectContainer getDevicesByTypeLocal(String str, String str2, List<String> list) throws RemoteException;

    List<DeviceProfileEx> getDevicesEx(String str, boolean z, int i, Bundle bundle) throws RemoteException;

    ObjectContainer getDevicesWithExtraInfo(String str, boolean z, int i, Bundle bundle) throws RemoteException;

    DeviceProfileEx getHostDevice(String str, List<String> list, List<String> list2) throws RemoteException;

    ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, String str3, boolean z, int i) throws RemoteException;

    ServiceCharacteristicProfile getServiceCharacteristicsWithExtraInfo(String str, String str2, String str3, boolean z, int i, Bundle bundle) throws RemoteException;

    ObjectContainer getServicesOfDevice(String str, String str2, boolean z, int i) throws RemoteException;

    Bundle getServicesOfDeviceWithExtraInfo(String str, String str2, boolean z, int i, Bundle bundle) throws RemoteException;

    boolean putDevice(String str, DeviceProfile deviceProfile) throws RemoteException;

    boolean putDeviceEx(String str, StreamData streamData, PutDeviceExListener putDeviceExListener) throws RemoteException;

    boolean putDeviceLocal(String str, DeviceProfile deviceProfile) throws RemoteException;

    boolean putDeviceWithExtraInfo(String str, DeviceProfile deviceProfile, Bundle bundle) throws RemoteException;

    boolean putServiceCharacteristic(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile) throws RemoteException;

    boolean putServiceCharacteristicWithExtraInfo(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile, Bundle bundle) throws RemoteException;

    boolean putServiceOfDevice(String str, String str2, ServiceProfile serviceProfile) throws RemoteException;

    boolean putServiceOfDeviceWithExtraInfo(String str, String str2, ServiceProfile serviceProfile, Bundle bundle) throws RemoteException;

    List<DeviceProfile> queryDeviceList(String str, int i, Bundle bundle) throws RemoteException;

    boolean setSwitch(String str, boolean z) throws RemoteException;

    int setSwitchFlag(String str, boolean z) throws RemoteException;

    Bundle subscribeDeviceList(String str, int i, Bundle bundle) throws RemoteException;

    Bundle subscribeDeviceProfile(String str, List<SubscribeInfo> list, Bundle bundle) throws RemoteException;

    Bundle subscribeDeviceProfileWithListener(String str, List<com.huawei.profile.subscription.deviceinfo.SubscribeInfo> list, ComponentName componentName, Bundle bundle) throws RemoteException;

    String subscribeEvents(String str, List<EventInfo> list, ComponentName componentName, String str2) throws RemoteException;

    boolean syncDeviceInfoEx(String str, List<String> list, SyncMode syncMode, SyncDeviceInfoExListener syncDeviceInfoExListener, Bundle bundle) throws RemoteException;

    boolean syncDeviceInfos(String str, String str2, SyncMode syncMode, SyncDeviceInfoListener syncDeviceInfoListener, Bundle bundle) throws RemoteException;

    Bundle unsubscribeDeviceList(String str, int i, Bundle bundle) throws RemoteException;

    Bundle unsubscribeDeviceProfile(String str, List<SubscribeInfo> list, Bundle bundle) throws RemoteException;

    Bundle unsubscribeDeviceProfileWithListener(String str, List<com.huawei.profile.subscription.deviceinfo.SubscribeInfo> list, ComponentName componentName, Bundle bundle) throws RemoteException;

    String unsubscribeEvents(String str, List<EventInfo> list, ComponentName componentName, String str2) throws RemoteException;

    Bundle updateDeviceListInfo(String str, List<DeviceProfile> list, int i, Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IProfileServiceCall {
        private static final String DESCRIPTOR = "com.huawei.profile.service.IProfileServiceCall";
        static final int TRANSACTION_addDeviceList = 28;
        static final int TRANSACTION_deleteDevice = 9;
        static final int TRANSACTION_deleteDeviceListById = 30;
        static final int TRANSACTION_deleteDeviceListByType = 29;
        static final int TRANSACTION_deleteDeviceLocal = 21;
        static final int TRANSACTION_deleteServiceCharacteristic = 11;
        static final int TRANSACTION_deleteServiceOfDevice = 10;
        static final int TRANSACTION_deleteServiceOfDeviceWithExtraInfo = 39;
        static final int TRANSACTION_findDevice = 26;
        static final int TRANSACTION_getApiVersion = 13;
        static final int TRANSACTION_getApiVersionCode = 14;
        static final int TRANSACTION_getAvailableDeviceInfoExs = 16;
        static final int TRANSACTION_getAvailableDeviceInfos = 15;
        static final int TRANSACTION_getDevices = 4;
        static final int TRANSACTION_getDevicesById = 5;
        static final int TRANSACTION_getDevicesByIdEx = 24;
        static final int TRANSACTION_getDevicesByType = 6;
        static final int TRANSACTION_getDevicesByTypeEx = 25;
        static final int TRANSACTION_getDevicesByTypeLocal = 19;
        static final int TRANSACTION_getDevicesEx = 36;
        static final int TRANSACTION_getDevicesWithExtraInfo = 45;
        static final int TRANSACTION_getHostDevice = 17;
        static final int TRANSACTION_getServiceCharacteristics = 8;
        static final int TRANSACTION_getServiceCharacteristicsWithExtraInfo = 38;
        static final int TRANSACTION_getServicesOfDevice = 7;
        static final int TRANSACTION_getServicesOfDeviceWithExtraInfo = 37;
        static final int TRANSACTION_putDevice = 1;
        static final int TRANSACTION_putDeviceEx = 44;
        static final int TRANSACTION_putDeviceLocal = 20;
        static final int TRANSACTION_putDeviceWithExtraInfo = 46;
        static final int TRANSACTION_putServiceCharacteristic = 3;
        static final int TRANSACTION_putServiceCharacteristicWithExtraInfo = 48;
        static final int TRANSACTION_putServiceOfDevice = 2;
        static final int TRANSACTION_putServiceOfDeviceWithExtraInfo = 47;
        static final int TRANSACTION_queryDeviceList = 31;
        static final int TRANSACTION_setSwitch = 27;
        static final int TRANSACTION_setSwitchFlag = 12;
        static final int TRANSACTION_subscribeDeviceList = 32;
        static final int TRANSACTION_subscribeDeviceProfile = 22;
        static final int TRANSACTION_subscribeDeviceProfileWithListener = 34;
        static final int TRANSACTION_subscribeEvents = 42;
        static final int TRANSACTION_syncDeviceInfoEx = 41;
        static final int TRANSACTION_syncDeviceInfos = 18;
        static final int TRANSACTION_unsubscribeDeviceList = 33;
        static final int TRANSACTION_unsubscribeDeviceProfile = 23;
        static final int TRANSACTION_unsubscribeDeviceProfileWithListener = 35;
        static final int TRANSACTION_unsubscribeEvents = 43;
        static final int TRANSACTION_updateDeviceListInfo = 40;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IProfileServiceCall asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IProfileServiceCall)) {
                return (IProfileServiceCall) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean putDevice = putDevice(parcel.readString(), parcel.readInt() != 0 ? DeviceProfile.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(putDevice ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean putServiceOfDevice = putServiceOfDevice(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? ServiceProfile.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(putServiceOfDevice ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean putServiceCharacteristic = putServiceCharacteristic(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? ServiceCharacteristicProfile.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(putServiceCharacteristic ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    ObjectContainer devices = getDevices(parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    if (devices != null) {
                        parcel2.writeInt(1);
                        devices.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    ObjectContainer devicesById = getDevicesById(parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    if (devicesById != null) {
                        parcel2.writeInt(1);
                        devicesById.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    ObjectContainer devicesByType = getDevicesByType(parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    if (devicesByType != null) {
                        parcel2.writeInt(1);
                        devicesByType.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    ObjectContainer servicesOfDevice = getServicesOfDevice(parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    if (servicesOfDevice != null) {
                        parcel2.writeInt(1);
                        servicesOfDevice.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    ServiceCharacteristicProfile serviceCharacteristics = getServiceCharacteristics(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    if (serviceCharacteristics != null) {
                        parcel2.writeInt(1);
                        serviceCharacteristics.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean deleteDevice = deleteDevice(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(deleteDevice ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean deleteServiceOfDevice = deleteServiceOfDevice(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(deleteServiceOfDevice ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean deleteServiceCharacteristic = deleteServiceCharacteristic(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(deleteServiceCharacteristic ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int switchFlag = setSwitchFlag(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(switchFlag);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    String apiVersion = getApiVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(apiVersion);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    int apiVersionCode = getApiVersionCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(apiVersionCode);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean availableDeviceInfos = getAvailableDeviceInfos(parcel.readString(), parcel.readInt() != 0, parcel.readString(), GetDeviceInfoListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(availableDeviceInfos ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean availableDeviceInfoExs = getAvailableDeviceInfoExs(parcel.readString(), parcel.readInt() != 0, parcel.readString(), parcel.createStringArrayList(), parcel.createStringArrayList(), GetDeviceInfoListenerEx.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(availableDeviceInfoExs ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    DeviceProfileEx hostDevice = getHostDevice(parcel.readString(), parcel.createStringArrayList(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    if (hostDevice != null) {
                        parcel2.writeInt(1);
                        hostDevice.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean syncDeviceInfos = syncDeviceInfos(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? SyncMode.CREATOR.createFromParcel(parcel) : null, SyncDeviceInfoListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(syncDeviceInfos ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    ObjectContainer devicesByTypeLocal = getDevicesByTypeLocal(parcel.readString(), parcel.readString(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    if (devicesByTypeLocal != null) {
                        parcel2.writeInt(1);
                        devicesByTypeLocal.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean putDeviceLocal = putDeviceLocal(parcel.readString(), parcel.readInt() != 0 ? DeviceProfile.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(putDeviceLocal ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean deleteDeviceLocal = deleteDeviceLocal(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(deleteDeviceLocal ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle subscribeDeviceProfile = subscribeDeviceProfile(parcel.readString(), parcel.createTypedArrayList(SubscribeInfo.CREATOR), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (subscribeDeviceProfile != null) {
                        parcel2.writeInt(1);
                        subscribeDeviceProfile.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle unsubscribeDeviceProfile = unsubscribeDeviceProfile(parcel.readString(), parcel.createTypedArrayList(SubscribeInfo.CREATOR), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (unsubscribeDeviceProfile != null) {
                        parcel2.writeInt(1);
                        unsubscribeDeviceProfile.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DeviceProfileEx> devicesByIdEx = getDevicesByIdEx(parcel.readString(), parcel.createStringArrayList(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(devicesByIdEx);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DeviceProfileEx> devicesByTypeEx = getDevicesByTypeEx(parcel.readString(), parcel.createStringArrayList(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(devicesByTypeEx);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean findDevice = findDevice(parcel.readString(), parcel.readInt() != 0, parcel.readString(), GetDeviceInfoListenerEx.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(findDevice ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean z = setSwitch(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(z ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle addDeviceList = addDeviceList(parcel.readString(), parcel.createTypedArrayList(DeviceProfile.CREATOR), parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (addDeviceList != null) {
                        parcel2.writeInt(1);
                        addDeviceList.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle deleteDeviceListByType = deleteDeviceListByType(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (deleteDeviceListByType != null) {
                        parcel2.writeInt(1);
                        deleteDeviceListByType.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle deleteDeviceListById = deleteDeviceListById(parcel.readString(), parcel.createStringArrayList(), parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (deleteDeviceListById != null) {
                        parcel2.writeInt(1);
                        deleteDeviceListById.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DeviceProfile> queryDeviceList = queryDeviceList(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(queryDeviceList);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle subscribeDeviceList = subscribeDeviceList(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (subscribeDeviceList != null) {
                        parcel2.writeInt(1);
                        subscribeDeviceList.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle unsubscribeDeviceList = unsubscribeDeviceList(parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (unsubscribeDeviceList != null) {
                        parcel2.writeInt(1);
                        unsubscribeDeviceList.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle subscribeDeviceProfileWithListener = subscribeDeviceProfileWithListener(parcel.readString(), parcel.createTypedArrayList(com.huawei.profile.subscription.deviceinfo.SubscribeInfo.CREATOR), parcel.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (subscribeDeviceProfileWithListener != null) {
                        parcel2.writeInt(1);
                        subscribeDeviceProfileWithListener.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle unsubscribeDeviceProfileWithListener = unsubscribeDeviceProfileWithListener(parcel.readString(), parcel.createTypedArrayList(com.huawei.profile.subscription.deviceinfo.SubscribeInfo.CREATOR), parcel.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (unsubscribeDeviceProfileWithListener != null) {
                        parcel2.writeInt(1);
                        unsubscribeDeviceProfileWithListener.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DeviceProfileEx> devicesEx = getDevicesEx(parcel.readString(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(devicesEx);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle servicesOfDeviceWithExtraInfo = getServicesOfDeviceWithExtraInfo(parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (servicesOfDeviceWithExtraInfo != null) {
                        parcel2.writeInt(1);
                        servicesOfDeviceWithExtraInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    ServiceCharacteristicProfile serviceCharacteristicsWithExtraInfo = getServiceCharacteristicsWithExtraInfo(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (serviceCharacteristicsWithExtraInfo != null) {
                        parcel2.writeInt(1);
                        serviceCharacteristicsWithExtraInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle deleteServiceOfDeviceWithExtraInfo = deleteServiceOfDeviceWithExtraInfo(parcel.readString(), parcel.readString(), parcel.createStringArrayList(), parcel.readInt() != 0, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (deleteServiceOfDeviceWithExtraInfo != null) {
                        parcel2.writeInt(1);
                        deleteServiceOfDeviceWithExtraInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle updateDeviceListInfo = updateDeviceListInfo(parcel.readString(), parcel.createTypedArrayList(DeviceProfile.CREATOR), parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (updateDeviceListInfo != null) {
                        parcel2.writeInt(1);
                        updateDeviceListInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean syncDeviceInfoEx = syncDeviceInfoEx(parcel.readString(), parcel.createStringArrayList(), parcel.readInt() != 0 ? SyncMode.CREATOR.createFromParcel(parcel) : null, SyncDeviceInfoExListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(syncDeviceInfoEx ? 1 : 0);
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    String subscribeEvents = subscribeEvents(parcel.readString(), parcel.createTypedArrayList(EventInfo.CREATOR), parcel.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(subscribeEvents);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    String unsubscribeEvents = unsubscribeEvents(parcel.readString(), parcel.createTypedArrayList(EventInfo.CREATOR), parcel.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(unsubscribeEvents);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean putDeviceEx = putDeviceEx(parcel.readString(), parcel.readInt() != 0 ? StreamData.CREATOR.createFromParcel(parcel) : null, PutDeviceExListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(putDeviceEx ? 1 : 0);
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    ObjectContainer devicesWithExtraInfo = getDevicesWithExtraInfo(parcel.readString(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (devicesWithExtraInfo != null) {
                        parcel2.writeInt(1);
                        devicesWithExtraInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean putDeviceWithExtraInfo = putDeviceWithExtraInfo(parcel.readString(), parcel.readInt() != 0 ? DeviceProfile.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(putDeviceWithExtraInfo ? 1 : 0);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean putServiceOfDeviceWithExtraInfo = putServiceOfDeviceWithExtraInfo(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? ServiceProfile.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(putServiceOfDeviceWithExtraInfo ? 1 : 0);
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean putServiceCharacteristicWithExtraInfo = putServiceCharacteristicWithExtraInfo(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? ServiceCharacteristicProfile.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(putServiceCharacteristicWithExtraInfo ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class Proxy implements IProfileServiceCall {
            public static IProfileServiceCall sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean putDevice(String str, DeviceProfile deviceProfile) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (deviceProfile != null) {
                        obtain.writeInt(1);
                        deviceProfile.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().putDevice(str, deviceProfile);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean putServiceOfDevice(String str, String str2, ServiceProfile serviceProfile) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (serviceProfile != null) {
                        obtain.writeInt(1);
                        serviceProfile.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().putServiceOfDevice(str, str2, serviceProfile);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean putServiceCharacteristic(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (serviceCharacteristicProfile != null) {
                        obtain.writeInt(1);
                        serviceCharacteristicProfile.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().putServiceCharacteristic(str, str2, str3, serviceCharacteristicProfile);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public ObjectContainer getDevices(String str, boolean z, int i) throws RemoteException {
                ObjectContainer createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getDevices(str, z, i);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? ObjectContainer.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public ObjectContainer getDevicesById(String str, String str2, boolean z, int i) throws RemoteException {
                ObjectContainer createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getDevicesById(str, str2, z, i);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? ObjectContainer.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public ObjectContainer getDevicesByType(String str, String str2, boolean z, int i) throws RemoteException {
                ObjectContainer createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getDevicesByType(str, str2, z, i);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? ObjectContainer.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public ObjectContainer getServicesOfDevice(String str, String str2, boolean z, int i) throws RemoteException {
                ObjectContainer createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getServicesOfDevice(str, str2, z, i);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? ObjectContainer.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public ServiceCharacteristicProfile getServiceCharacteristics(String str, String str2, String str3, boolean z, int i) throws RemoteException {
                ServiceCharacteristicProfile createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getServiceCharacteristics(str, str2, str3, z, i);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? ServiceCharacteristicProfile.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean deleteDevice(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteDevice(str, str2);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean deleteServiceOfDevice(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteServiceOfDevice(str, str2, str3);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean deleteServiceCharacteristic(String str, String str2, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteServiceCharacteristic(str, str2, str3, str4);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public int setSwitchFlag(String str, boolean z) throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().setSwitchFlag(str, z);
                    } else {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public String getApiVersion() throws RemoteException {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().getApiVersion();
                    } else {
                        obtain2.readException();
                        readString = obtain2.readString();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public int getApiVersionCode() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getApiVersionCode();
                    } else {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean getAvailableDeviceInfos(String str, boolean z, String str2, GetDeviceInfoListener getDeviceInfoListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(getDeviceInfoListener != null ? getDeviceInfoListener.asBinder() : null);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailableDeviceInfos(str, z, str2, getDeviceInfoListener);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean getAvailableDeviceInfoExs(String str, boolean z, String str2, List<String> list, List<String> list2, GetDeviceInfoListenerEx getDeviceInfoListenerEx) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str2);
                    obtain.writeStringList(list);
                    obtain.writeStringList(list2);
                    obtain.writeStrongBinder(getDeviceInfoListenerEx != null ? getDeviceInfoListenerEx.asBinder() : null);
                    try {
                        if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                            boolean availableDeviceInfoExs = Stub.getDefaultImpl().getAvailableDeviceInfoExs(str, z, str2, list, list2, getDeviceInfoListenerEx);
                            obtain2.recycle();
                            obtain.recycle();
                            return availableDeviceInfoExs;
                        }
                        obtain2.readException();
                        boolean z2 = obtain2.readInt() != 0;
                        obtain2.recycle();
                        obtain.recycle();
                        return z2;
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public DeviceProfileEx getHostDevice(String str, List<String> list, List<String> list2) throws RemoteException {
                DeviceProfileEx createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeStringList(list2);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getHostDevice(str, list, list2);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? DeviceProfileEx.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean syncDeviceInfos(String str, String str2, SyncMode syncMode, SyncDeviceInfoListener syncDeviceInfoListener, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (syncMode != null) {
                        obtain.writeInt(1);
                        syncMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(syncDeviceInfoListener != null ? syncDeviceInfoListener.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    try {
                        if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                            boolean syncDeviceInfos = Stub.getDefaultImpl().syncDeviceInfos(str, str2, syncMode, syncDeviceInfoListener, bundle);
                            obtain2.recycle();
                            obtain.recycle();
                            return syncDeviceInfos;
                        }
                        obtain2.readException();
                        boolean z = obtain2.readInt() != 0;
                        obtain2.recycle();
                        obtain.recycle();
                        return z;
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public ObjectContainer getDevicesByTypeLocal(String str, String str2, List<String> list) throws RemoteException {
                ObjectContainer createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringList(list);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getDevicesByTypeLocal(str, str2, list);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? ObjectContainer.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean putDeviceLocal(String str, DeviceProfile deviceProfile) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (deviceProfile != null) {
                        obtain.writeInt(1);
                        deviceProfile.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().putDeviceLocal(str, deviceProfile);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean deleteDeviceLocal(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteDeviceLocal(str, str2, str3);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public Bundle subscribeDeviceProfile(String str, List<SubscribeInfo> list, Bundle bundle) throws RemoteException {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeTypedList(list);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        bundle2 = Stub.getDefaultImpl().subscribeDeviceProfile(str, list, bundle);
                    } else {
                        obtain2.readException();
                        bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return bundle2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public Bundle unsubscribeDeviceProfile(String str, List<SubscribeInfo> list, Bundle bundle) throws RemoteException {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeTypedList(list);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        bundle2 = Stub.getDefaultImpl().unsubscribeDeviceProfile(str, list, bundle);
                    } else {
                        obtain2.readException();
                        bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return bundle2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public List<DeviceProfileEx> getDevicesByIdEx(String str, List<String> list, boolean z, int i, Bundle bundle) throws RemoteException {
                List<DeviceProfileEx> createTypedArrayList;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = Stub.getDefaultImpl().getDevicesByIdEx(str, list, z, i, bundle);
                    } else {
                        obtain2.readException();
                        createTypedArrayList = obtain2.createTypedArrayList(DeviceProfileEx.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public List<DeviceProfileEx> getDevicesByTypeEx(String str, List<String> list, boolean z, int i, Bundle bundle) throws RemoteException {
                List<DeviceProfileEx> createTypedArrayList;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = Stub.getDefaultImpl().getDevicesByTypeEx(str, list, z, i, bundle);
                    } else {
                        obtain2.readException();
                        createTypedArrayList = obtain2.createTypedArrayList(DeviceProfileEx.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean findDevice(String str, boolean z, String str2, GetDeviceInfoListenerEx getDeviceInfoListenerEx, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(getDeviceInfoListenerEx != null ? getDeviceInfoListenerEx.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    try {
                        if (!this.mRemote.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                            boolean findDevice = Stub.getDefaultImpl().findDevice(str, z, str2, getDeviceInfoListenerEx, bundle);
                            obtain2.recycle();
                            obtain.recycle();
                            return findDevice;
                        }
                        obtain2.readException();
                        boolean z2 = obtain2.readInt() != 0;
                        obtain2.recycle();
                        obtain.recycle();
                        return z2;
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean setSwitch(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSwitch(str, z);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public Bundle addDeviceList(String str, List<DeviceProfile> list, int i, Bundle bundle) throws RemoteException {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeTypedList(list);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        bundle2 = Stub.getDefaultImpl().addDeviceList(str, list, i, bundle);
                    } else {
                        obtain2.readException();
                        bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return bundle2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public Bundle deleteDeviceListByType(String str, int i, Bundle bundle) throws RemoteException {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(29, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        bundle2 = Stub.getDefaultImpl().deleteDeviceListByType(str, i, bundle);
                    } else {
                        obtain2.readException();
                        bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return bundle2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public Bundle deleteDeviceListById(String str, List<String> list, int i, Bundle bundle) throws RemoteException {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(30, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        bundle2 = Stub.getDefaultImpl().deleteDeviceListById(str, list, i, bundle);
                    } else {
                        obtain2.readException();
                        bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return bundle2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public List<DeviceProfile> queryDeviceList(String str, int i, Bundle bundle) throws RemoteException {
                List<DeviceProfile> createTypedArrayList;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(31, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = Stub.getDefaultImpl().queryDeviceList(str, i, bundle);
                    } else {
                        obtain2.readException();
                        createTypedArrayList = obtain2.createTypedArrayList(DeviceProfile.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public Bundle subscribeDeviceList(String str, int i, Bundle bundle) throws RemoteException {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(32, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        bundle2 = Stub.getDefaultImpl().subscribeDeviceList(str, i, bundle);
                    } else {
                        obtain2.readException();
                        bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return bundle2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public Bundle unsubscribeDeviceList(String str, int i, Bundle bundle) throws RemoteException {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(33, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        bundle2 = Stub.getDefaultImpl().unsubscribeDeviceList(str, i, bundle);
                    } else {
                        obtain2.readException();
                        bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return bundle2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public Bundle subscribeDeviceProfileWithListener(String str, List<com.huawei.profile.subscription.deviceinfo.SubscribeInfo> list, ComponentName componentName, Bundle bundle) throws RemoteException {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeTypedList(list);
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(34, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        bundle2 = Stub.getDefaultImpl().subscribeDeviceProfileWithListener(str, list, componentName, bundle);
                    } else {
                        obtain2.readException();
                        bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return bundle2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public Bundle unsubscribeDeviceProfileWithListener(String str, List<com.huawei.profile.subscription.deviceinfo.SubscribeInfo> list, ComponentName componentName, Bundle bundle) throws RemoteException {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeTypedList(list);
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(35, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        bundle2 = Stub.getDefaultImpl().unsubscribeDeviceProfileWithListener(str, list, componentName, bundle);
                    } else {
                        obtain2.readException();
                        bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return bundle2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public List<DeviceProfileEx> getDevicesEx(String str, boolean z, int i, Bundle bundle) throws RemoteException {
                List<DeviceProfileEx> createTypedArrayList;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(36, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createTypedArrayList = Stub.getDefaultImpl().getDevicesEx(str, z, i, bundle);
                    } else {
                        obtain2.readException();
                        createTypedArrayList = obtain2.createTypedArrayList(DeviceProfileEx.CREATOR);
                    }
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public Bundle getServicesOfDeviceWithExtraInfo(String str, String str2, boolean z, int i, Bundle bundle) throws RemoteException {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(37, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        bundle2 = Stub.getDefaultImpl().getServicesOfDeviceWithExtraInfo(str, str2, z, i, bundle);
                    } else {
                        obtain2.readException();
                        bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return bundle2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public ServiceCharacteristicProfile getServiceCharacteristicsWithExtraInfo(String str, String str2, String str3, boolean z, int i, Bundle bundle) throws RemoteException {
                ServiceCharacteristicProfile createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    try {
                        if (!this.mRemote.transact(38, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                            createFromParcel = Stub.getDefaultImpl().getServiceCharacteristicsWithExtraInfo(str, str2, str3, z, i, bundle);
                        } else {
                            obtain2.readException();
                            createFromParcel = obtain2.readInt() != 0 ? ServiceCharacteristicProfile.CREATOR.createFromParcel(obtain2) : null;
                        }
                        obtain2.recycle();
                        obtain.recycle();
                        return createFromParcel;
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public Bundle deleteServiceOfDeviceWithExtraInfo(String str, String str2, List<String> list, boolean z, Bundle bundle) throws RemoteException {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringList(list);
                    obtain.writeInt(z ? 1 : 0);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(39, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        bundle2 = Stub.getDefaultImpl().deleteServiceOfDeviceWithExtraInfo(str, str2, list, z, bundle);
                    } else {
                        obtain2.readException();
                        bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return bundle2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public Bundle updateDeviceListInfo(String str, List<DeviceProfile> list, int i, Bundle bundle) throws RemoteException {
                Bundle bundle2;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeTypedList(list);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(40, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        bundle2 = Stub.getDefaultImpl().updateDeviceListInfo(str, list, i, bundle);
                    } else {
                        obtain2.readException();
                        bundle2 = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return bundle2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean syncDeviceInfoEx(String str, List<String> list, SyncMode syncMode, SyncDeviceInfoExListener syncDeviceInfoExListener, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    if (syncMode != null) {
                        obtain.writeInt(1);
                        syncMode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(syncDeviceInfoExListener != null ? syncDeviceInfoExListener.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    try {
                        if (!this.mRemote.transact(41, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                            boolean syncDeviceInfoEx = Stub.getDefaultImpl().syncDeviceInfoEx(str, list, syncMode, syncDeviceInfoExListener, bundle);
                            obtain2.recycle();
                            obtain.recycle();
                            return syncDeviceInfoEx;
                        }
                        obtain2.readException();
                        boolean z = obtain2.readInt() != 0;
                        obtain2.recycle();
                        obtain.recycle();
                        return z;
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public String subscribeEvents(String str, List<EventInfo> list, ComponentName componentName, String str2) throws RemoteException {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeTypedList(list);
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(42, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().subscribeEvents(str, list, componentName, str2);
                    } else {
                        obtain2.readException();
                        readString = obtain2.readString();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public String unsubscribeEvents(String str, List<EventInfo> list, ComponentName componentName, String str2) throws RemoteException {
                String readString;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeTypedList(list);
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(43, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readString = Stub.getDefaultImpl().unsubscribeEvents(str, list, componentName, str2);
                    } else {
                        obtain2.readException();
                        readString = obtain2.readString();
                    }
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean putDeviceEx(String str, StreamData streamData, PutDeviceExListener putDeviceExListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (streamData != null) {
                        obtain.writeInt(1);
                        streamData.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(putDeviceExListener != null ? putDeviceExListener.asBinder() : null);
                    if (!this.mRemote.transact(44, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().putDeviceEx(str, streamData, putDeviceExListener);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public ObjectContainer getDevicesWithExtraInfo(String str, boolean z, int i, Bundle bundle) throws RemoteException {
                ObjectContainer createFromParcel;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(45, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        createFromParcel = Stub.getDefaultImpl().getDevicesWithExtraInfo(str, z, i, bundle);
                    } else {
                        obtain2.readException();
                        createFromParcel = obtain2.readInt() != 0 ? ObjectContainer.CREATOR.createFromParcel(obtain2) : null;
                    }
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean putDeviceWithExtraInfo(String str, DeviceProfile deviceProfile, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (deviceProfile != null) {
                        obtain.writeInt(1);
                        deviceProfile.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(46, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().putDeviceWithExtraInfo(str, deviceProfile, bundle);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean putServiceOfDeviceWithExtraInfo(String str, String str2, ServiceProfile serviceProfile, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (serviceProfile != null) {
                        obtain.writeInt(1);
                        serviceProfile.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(47, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().putServiceOfDeviceWithExtraInfo(str, str2, serviceProfile, bundle);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.profile.service.IProfileServiceCall
            public boolean putServiceCharacteristicWithExtraInfo(String str, String str2, String str3, ServiceCharacteristicProfile serviceCharacteristicProfile, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (serviceCharacteristicProfile != null) {
                        obtain.writeInt(1);
                        serviceCharacteristicProfile.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    try {
                        if (!this.mRemote.transact(48, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                            boolean putServiceCharacteristicWithExtraInfo = Stub.getDefaultImpl().putServiceCharacteristicWithExtraInfo(str, str2, str3, serviceCharacteristicProfile, bundle);
                            obtain2.recycle();
                            obtain.recycle();
                            return putServiceCharacteristicWithExtraInfo;
                        }
                        obtain2.readException();
                        boolean z = obtain2.readInt() != 0;
                        obtain2.recycle();
                        obtain.recycle();
                        return z;
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public static boolean setDefaultImpl(IProfileServiceCall iProfileServiceCall) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iProfileServiceCall == null) {
                return false;
            }
            Proxy.sDefaultImpl = iProfileServiceCall;
            return true;
        }

        public static IProfileServiceCall getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
