package com.huawei.wearengine;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.wearengine.connect.ServiceConnectCallback;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.ota.CheckBinderCallback;
import com.huawei.wearengine.ota.DeviceListBinderCallback;
import com.huawei.wearengine.ota.UpgradeBinderCallBack;
import com.huawei.wearengine.ota.UpgradeStatusBinderCallBack;

/* loaded from: classes9.dex */
public interface OtaManager extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.OtaManager";

    void doUpgrade(Device device, String str, UpgradeBinderCallBack upgradeBinderCallBack) throws RemoteException;

    void getConnectedDevices(DeviceListBinderCallback deviceListBinderCallback) throws RemoteException;

    void getDeviceNewVersion(Device device, CheckBinderCallback checkBinderCallback) throws RemoteException;

    void getUpgradeStatus(Device device, UpgradeStatusBinderCallBack upgradeStatusBinderCallBack) throws RemoteException;

    int registerOtaServiceConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException;

    void registerUpgradeListener(Device device, UpgradeStatusBinderCallBack upgradeStatusBinderCallBack) throws RemoteException;

    void unRegisterUpgradeListener(Device device) throws RemoteException;

    int unregisterOtaServiceConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements OtaManager {
        static final int TRANSACTION_doUpgrade = 3;
        static final int TRANSACTION_getConnectedDevices = 1;
        static final int TRANSACTION_getDeviceNewVersion = 2;
        static final int TRANSACTION_getUpgradeStatus = 4;
        static final int TRANSACTION_registerOtaServiceConnectCallback = 7;
        static final int TRANSACTION_registerUpgradeListener = 5;
        static final int TRANSACTION_unRegisterUpgradeListener = 6;
        static final int TRANSACTION_unregisterOtaServiceConnectCallback = 8;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, OtaManager.DESCRIPTOR);
        }

        public static OtaManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(OtaManager.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof OtaManager)) {
                return (OtaManager) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(OtaManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(OtaManager.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    getConnectedDevices(DeviceListBinderCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    Device device = (Device) a.fcD_(parcel, Device.CREATOR);
                    getDeviceNewVersion(device, CheckBinderCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    a.fcE_(parcel2, device, 1);
                    return true;
                case 3:
                    Device device2 = (Device) a.fcD_(parcel, Device.CREATOR);
                    doUpgrade(device2, parcel.readString(), UpgradeBinderCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    a.fcE_(parcel2, device2, 1);
                    return true;
                case 4:
                    Device device3 = (Device) a.fcD_(parcel, Device.CREATOR);
                    getUpgradeStatus(device3, UpgradeStatusBinderCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    a.fcE_(parcel2, device3, 1);
                    return true;
                case 5:
                    Device device4 = (Device) a.fcD_(parcel, Device.CREATOR);
                    registerUpgradeListener(device4, UpgradeStatusBinderCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    a.fcE_(parcel2, device4, 1);
                    return true;
                case 6:
                    Device device5 = (Device) a.fcD_(parcel, Device.CREATOR);
                    unRegisterUpgradeListener(device5);
                    parcel2.writeNoException();
                    a.fcE_(parcel2, device5, 1);
                    return true;
                case 7:
                    int registerOtaServiceConnectCallback = registerOtaServiceConnectCallback(ServiceConnectCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(registerOtaServiceConnectCallback);
                    return true;
                case 8:
                    int unregisterOtaServiceConnectCallback = unregisterOtaServiceConnectCallback(ServiceConnectCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(unregisterOtaServiceConnectCallback);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class d implements OtaManager {
            private IBinder b;

            d(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.wearengine.OtaManager
            public void getConnectedDevices(DeviceListBinderCallback deviceListBinderCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(OtaManager.DESCRIPTOR);
                    obtain.writeStrongInterface(deviceListBinderCallback);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.OtaManager
            public void getDeviceNewVersion(Device device, CheckBinderCallback checkBinderCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(OtaManager.DESCRIPTOR);
                    a.fcE_(obtain, device, 0);
                    obtain.writeStrongInterface(checkBinderCallback);
                    this.b.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        device.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.OtaManager
            public void doUpgrade(Device device, String str, UpgradeBinderCallBack upgradeBinderCallBack) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(OtaManager.DESCRIPTOR);
                    a.fcE_(obtain, device, 0);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(upgradeBinderCallBack);
                    this.b.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        device.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.OtaManager
            public void getUpgradeStatus(Device device, UpgradeStatusBinderCallBack upgradeStatusBinderCallBack) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(OtaManager.DESCRIPTOR);
                    a.fcE_(obtain, device, 0);
                    obtain.writeStrongInterface(upgradeStatusBinderCallBack);
                    this.b.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        device.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.OtaManager
            public void registerUpgradeListener(Device device, UpgradeStatusBinderCallBack upgradeStatusBinderCallBack) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(OtaManager.DESCRIPTOR);
                    a.fcE_(obtain, device, 0);
                    obtain.writeStrongInterface(upgradeStatusBinderCallBack);
                    this.b.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        device.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.OtaManager
            public void unRegisterUpgradeListener(Device device) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(OtaManager.DESCRIPTOR);
                    a.fcE_(obtain, device, 0);
                    this.b.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        device.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.OtaManager
            public int registerOtaServiceConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(OtaManager.DESCRIPTOR);
                    obtain.writeStrongInterface(serviceConnectCallback);
                    this.b.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.OtaManager
            public int unregisterOtaServiceConnectCallback(ServiceConnectCallback serviceConnectCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(OtaManager.DESCRIPTOR);
                    obtain.writeStrongInterface(serviceConnectCallback);
                    this.b.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class a {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T fcD_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fcE_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
