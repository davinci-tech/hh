package com.huawei.hwservicesmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.datatype.DeviceStatusParam;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;

/* loaded from: classes.dex */
public interface IDeviceStateAIDLCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwservicesmgr.IDeviceStateAIDLCallback";

    void onAckReceived(DeviceInfo deviceInfo, int i, byte[] bArr) throws RemoteException;

    void onDataReceived(DeviceInfo deviceInfo, int i, byte[] bArr) throws RemoteException;

    void onDatasReceived(DeviceInfo deviceInfo, int i, String str) throws RemoteException;

    void onDeviceConnectionStateChanged(DeviceInfo deviceInfo, int i, DeviceStatusParam deviceStatusParam) throws RemoteException;

    /* loaded from: classes5.dex */
    public static abstract class Stub extends Binder implements IDeviceStateAIDLCallback {
        static final int TRANSACTION_onAckReceived = 3;
        static final int TRANSACTION_onDataReceived = 2;
        static final int TRANSACTION_onDatasReceived = 4;
        static final int TRANSACTION_onDeviceConnectionStateChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IDeviceStateAIDLCallback.DESCRIPTOR);
        }

        public static IDeviceStateAIDLCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDeviceStateAIDLCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDeviceStateAIDLCallback)) {
                return (IDeviceStateAIDLCallback) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDeviceStateAIDLCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IDeviceStateAIDLCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onDeviceConnectionStateChanged((DeviceInfo) c.bRE_(parcel, DeviceInfo.CREATOR), parcel.readInt(), (DeviceStatusParam) c.bRE_(parcel, DeviceStatusParam.CREATOR));
                parcel2.writeNoException();
            } else if (i == 2) {
                onDataReceived((DeviceInfo) c.bRE_(parcel, DeviceInfo.CREATOR), parcel.readInt(), parcel.createByteArray());
                parcel2.writeNoException();
            } else if (i == 3) {
                onAckReceived((DeviceInfo) c.bRE_(parcel, DeviceInfo.CREATOR), parcel.readInt(), parcel.createByteArray());
                parcel2.writeNoException();
            } else if (i == 4) {
                onDatasReceived((DeviceInfo) c.bRE_(parcel, DeviceInfo.CREATOR), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class d implements IDeviceStateAIDLCallback {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f6391a;

            d(IBinder iBinder) {
                this.f6391a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f6391a;
            }

            @Override // com.huawei.hwservicesmgr.IDeviceStateAIDLCallback
            public void onDeviceConnectionStateChanged(DeviceInfo deviceInfo, int i, DeviceStatusParam deviceStatusParam) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDeviceStateAIDLCallback.DESCRIPTOR);
                    c.bRF_(obtain, deviceInfo, 0);
                    obtain.writeInt(i);
                    c.bRF_(obtain, deviceStatusParam, 0);
                    this.f6391a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IDeviceStateAIDLCallback
            public void onDataReceived(DeviceInfo deviceInfo, int i, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDeviceStateAIDLCallback.DESCRIPTOR);
                    c.bRF_(obtain, deviceInfo, 0);
                    obtain.writeInt(i);
                    obtain.writeByteArray(bArr);
                    this.f6391a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IDeviceStateAIDLCallback
            public void onAckReceived(DeviceInfo deviceInfo, int i, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDeviceStateAIDLCallback.DESCRIPTOR);
                    c.bRF_(obtain, deviceInfo, 0);
                    obtain.writeInt(i);
                    obtain.writeByteArray(bArr);
                    this.f6391a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hwservicesmgr.IDeviceStateAIDLCallback
            public void onDatasReceived(DeviceInfo deviceInfo, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDeviceStateAIDLCallback.DESCRIPTOR);
                    c.bRF_(obtain, deviceInfo, 0);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.f6391a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    /* loaded from: classes5.dex */
    public static class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T bRE_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bRF_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
