package com.huawei.phdkit;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes9.dex */
public interface DataReceiveListener extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.phdkit.DataReceiveListener";

    public static class Default implements DataReceiveListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.phdkit.DataReceiveListener
        public void onDataChanged(DeviceData deviceData) throws RemoteException {
        }
    }

    void onDataChanged(DeviceData deviceData) throws RemoteException;

    public static abstract class Stub extends Binder implements DataReceiveListener {
        static final int TRANSACTION_onDataChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DataReceiveListener.DESCRIPTOR);
        }

        public static DataReceiveListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DataReceiveListener.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof DataReceiveListener)) {
                return (DataReceiveListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(DataReceiveListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(DataReceiveListener.DESCRIPTOR);
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            DeviceData deviceData = (DeviceData) e.ccz_(parcel, DeviceData.CREATOR);
            onDataChanged(deviceData);
            parcel2.writeNoException();
            e.ccA_(parcel2, deviceData, 1);
            return true;
        }

        static class Proxy implements DataReceiveListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.phdkit.DataReceiveListener
            public void onDataChanged(DeviceData deviceData) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DataReceiveListener.DESCRIPTOR);
                    e.ccA_(obtain, deviceData, 0);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        deviceData.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return DataReceiveListener.DESCRIPTOR;
            }
        }
    }

    public static class e {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T ccz_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void ccA_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
