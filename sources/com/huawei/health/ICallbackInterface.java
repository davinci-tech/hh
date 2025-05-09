package com.huawei.health;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.IGetbindDeviceCommonCallback;
import com.huawei.health.IRemoteProxyCallback;
import com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public interface ICallbackInterface extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.ICallbackInterface";

    void getCommonData(int i, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void getDeviceList(IGetbindDeviceCommonCallback iGetbindDeviceCommonCallback) throws RemoteException;

    void getDeviceListSize(IGetbindDeviceCommonCallback iGetbindDeviceCommonCallback) throws RemoteException;

    void isHealthSupportWearDevice(IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    boolean onResponse(Map map) throws RemoteException;

    void registerBinder(IBinder iBinder, String str, String str2) throws RemoteException;

    void registerRemoteCallback(IRemoteProxyCallback iRemoteProxyCallback) throws RemoteException;

    void registerRemoteCallbackForHealth(String str, IRemoteProxyCallback iRemoteProxyCallback) throws RemoteException;

    void sendDataToHealth(String str, IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    void setDeviceCapability(String str) throws RemoteException;

    void setDeviceCapabilityForHealth(String str) throws RemoteException;

    void setUsedDeviceList(List<DeviceInfo> list) throws RemoteException;

    void setUsedDeviceListForHealth(List<DeviceInfo> list) throws RemoteException;

    void unRegisterRemoteCallback(IRemoteProxyCallback iRemoteProxyCallback) throws RemoteException;

    void unbindAllDevice(IBaseCommonCallback iBaseCommonCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ICallbackInterface {
        static final int TRANSACTION_getCommonData = 12;
        static final int TRANSACTION_getDeviceList = 8;
        static final int TRANSACTION_getDeviceListSize = 11;
        static final int TRANSACTION_isHealthSupportWearDevice = 9;
        static final int TRANSACTION_onResponse = 3;
        static final int TRANSACTION_registerBinder = 6;
        static final int TRANSACTION_registerRemoteCallback = 1;
        static final int TRANSACTION_registerRemoteCallbackForHealth = 15;
        static final int TRANSACTION_sendDataToHealth = 10;
        static final int TRANSACTION_setDeviceCapability = 5;
        static final int TRANSACTION_setDeviceCapabilityForHealth = 14;
        static final int TRANSACTION_setUsedDeviceList = 4;
        static final int TRANSACTION_setUsedDeviceListForHealth = 13;
        static final int TRANSACTION_unRegisterRemoteCallback = 2;
        static final int TRANSACTION_unbindAllDevice = 7;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ICallbackInterface.DESCRIPTOR);
        }

        public static ICallbackInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ICallbackInterface.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ICallbackInterface)) {
                return (ICallbackInterface) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ICallbackInterface.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ICallbackInterface.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    registerRemoteCallback(IRemoteProxyCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    unRegisterRemoteCallback(IRemoteProxyCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    boolean onResponse = onResponse(parcel.readHashMap(getClass().getClassLoader()));
                    parcel2.writeNoException();
                    parcel2.writeInt(onResponse ? 1 : 0);
                    return true;
                case 4:
                    setUsedDeviceList(parcel.createTypedArrayList(DeviceInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    setDeviceCapability(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    registerBinder(parcel.readStrongBinder(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    unbindAllDevice(IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    getDeviceList(IGetbindDeviceCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    isHealthSupportWearDevice(IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    sendDataToHealth(parcel.readString(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    getDeviceListSize(IGetbindDeviceCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    getCommonData(parcel.readInt(), IBaseCommonCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    setUsedDeviceListForHealth(parcel.createTypedArrayList(DeviceInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    setDeviceCapabilityForHealth(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    registerRemoteCallbackForHealth(parcel.readString(), IRemoteProxyCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class c implements ICallbackInterface {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f2171a;

            c(IBinder iBinder) {
                this.f2171a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2171a;
            }

            @Override // com.huawei.health.ICallbackInterface
            public void registerRemoteCallback(IRemoteProxyCallback iRemoteProxyCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICallbackInterface.DESCRIPTOR);
                    obtain.writeStrongInterface(iRemoteProxyCallback);
                    this.f2171a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ICallbackInterface
            public void unRegisterRemoteCallback(IRemoteProxyCallback iRemoteProxyCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICallbackInterface.DESCRIPTOR);
                    obtain.writeStrongInterface(iRemoteProxyCallback);
                    this.f2171a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ICallbackInterface
            public boolean onResponse(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICallbackInterface.DESCRIPTOR);
                    obtain.writeMap(map);
                    this.f2171a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ICallbackInterface
            public void setUsedDeviceList(List<DeviceInfo> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICallbackInterface.DESCRIPTOR);
                    c.Av_(obtain, list, 0);
                    this.f2171a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ICallbackInterface
            public void setDeviceCapability(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICallbackInterface.DESCRIPTOR);
                    obtain.writeString(str);
                    this.f2171a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ICallbackInterface
            public void registerBinder(IBinder iBinder, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICallbackInterface.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.f2171a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ICallbackInterface
            public void unbindAllDevice(IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICallbackInterface.DESCRIPTOR);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.f2171a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ICallbackInterface
            public void getDeviceList(IGetbindDeviceCommonCallback iGetbindDeviceCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICallbackInterface.DESCRIPTOR);
                    obtain.writeStrongInterface(iGetbindDeviceCommonCallback);
                    this.f2171a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ICallbackInterface
            public void isHealthSupportWearDevice(IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICallbackInterface.DESCRIPTOR);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.f2171a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ICallbackInterface
            public void sendDataToHealth(String str, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICallbackInterface.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.f2171a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ICallbackInterface
            public void getDeviceListSize(IGetbindDeviceCommonCallback iGetbindDeviceCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICallbackInterface.DESCRIPTOR);
                    obtain.writeStrongInterface(iGetbindDeviceCommonCallback);
                    this.f2171a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ICallbackInterface
            public void getCommonData(int i, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICallbackInterface.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iBaseCommonCallback);
                    this.f2171a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ICallbackInterface
            public void setUsedDeviceListForHealth(List<DeviceInfo> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICallbackInterface.DESCRIPTOR);
                    c.Av_(obtain, list, 0);
                    this.f2171a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ICallbackInterface
            public void setDeviceCapabilityForHealth(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICallbackInterface.DESCRIPTOR);
                    obtain.writeString(str);
                    this.f2171a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.ICallbackInterface
            public void registerRemoteCallbackForHealth(String str, IRemoteProxyCallback iRemoteProxyCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICallbackInterface.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iRemoteProxyCallback);
                    this.f2171a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class c {
        private static <T extends Parcelable> void Aw_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void Av_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                Aw_(parcel, list.get(i2), i);
            }
        }
    }
}
