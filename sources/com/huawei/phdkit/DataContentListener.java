package com.huawei.phdkit;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes5.dex */
public interface DataContentListener extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.phdkit.DataContentListener";

    /* loaded from: classes9.dex */
    public static class Default implements DataContentListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.phdkit.DataContentListener
        public void getResult(String str, String str2) throws RemoteException {
        }

        @Override // com.huawei.phdkit.DataContentListener
        public void getStatus(DeviceData deviceData) throws RemoteException {
        }
    }

    void getResult(String str, String str2) throws RemoteException;

    void getStatus(DeviceData deviceData) throws RemoteException;

    public static abstract class Stub extends Binder implements DataContentListener {
        static final int TRANSACTION_getResult = 1;
        static final int TRANSACTION_getStatus = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DataContentListener.DESCRIPTOR);
        }

        public static DataContentListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DataContentListener.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof DataContentListener)) {
                return (DataContentListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(DataContentListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(DataContentListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                getResult(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
            } else {
                if (i != 2) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                DeviceData deviceData = (DeviceData) a.ccv_(parcel, DeviceData.CREATOR);
                getStatus(deviceData);
                parcel2.writeNoException();
                a.ccw_(parcel2, deviceData, 1);
            }
            return true;
        }

        static class Proxy implements DataContentListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.phdkit.DataContentListener
            public void getResult(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DataContentListener.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.phdkit.DataContentListener
            public void getStatus(DeviceData deviceData) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DataContentListener.DESCRIPTOR);
                    a.ccw_(obtain, deviceData, 0);
                    this.mRemote.transact(2, obtain, obtain2, 0);
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
                return DataContentListener.DESCRIPTOR;
            }
        }
    }

    public static class a {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T ccv_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void ccw_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
