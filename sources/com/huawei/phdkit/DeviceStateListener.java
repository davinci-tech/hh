package com.huawei.phdkit;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes5.dex */
public interface DeviceStateListener extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.phdkit.DeviceStateListener";

    /* loaded from: classes9.dex */
    public static class Default implements DeviceStateListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.phdkit.DeviceStateListener
        public void onDeviceStateChanged(DvLiteDevice dvLiteDevice, int i) throws RemoteException {
        }

        @Override // com.huawei.phdkit.DeviceStateListener
        public void onServiceDied() throws RemoteException {
        }
    }

    void onDeviceStateChanged(DvLiteDevice dvLiteDevice, int i) throws RemoteException;

    void onServiceDied() throws RemoteException;

    public static abstract class Stub extends Binder implements DeviceStateListener {
        static final int TRANSACTION_onDeviceStateChanged = 1;
        static final int TRANSACTION_onServiceDied = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DeviceStateListener.DESCRIPTOR);
        }

        public static DeviceStateListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DeviceStateListener.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof DeviceStateListener)) {
                return (DeviceStateListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(DeviceStateListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(DeviceStateListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                DvLiteDevice dvLiteDevice = (DvLiteDevice) c.ccE_(parcel, DvLiteDevice.CREATOR);
                onDeviceStateChanged(dvLiteDevice, parcel.readInt());
                parcel2.writeNoException();
                c.ccF_(parcel2, dvLiteDevice, 1);
            } else if (i == 2) {
                onServiceDied();
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class Proxy implements DeviceStateListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.phdkit.DeviceStateListener
            public void onDeviceStateChanged(DvLiteDevice dvLiteDevice, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DeviceStateListener.DESCRIPTOR);
                    c.ccF_(obtain, dvLiteDevice, 0);
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        dvLiteDevice.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.phdkit.DeviceStateListener
            public void onServiceDied() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DeviceStateListener.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return DeviceStateListener.DESCRIPTOR;
            }
        }
    }

    public static class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T ccE_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void ccF_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
