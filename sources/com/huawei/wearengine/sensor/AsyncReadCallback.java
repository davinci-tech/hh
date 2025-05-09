package com.huawei.wearengine.sensor;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes9.dex */
public interface AsyncReadCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.sensor.AsyncReadCallback";

    public static class Default implements AsyncReadCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.wearengine.sensor.AsyncReadCallback
        public void onReadResult(int i, DataResult dataResult) throws RemoteException {
        }
    }

    void onReadResult(int i, DataResult dataResult) throws RemoteException;

    public static abstract class Stub extends Binder implements AsyncReadCallback {
        static final int TRANSACTION_onReadResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, AsyncReadCallback.DESCRIPTOR);
        }

        public static AsyncReadCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(AsyncReadCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof AsyncReadCallback)) {
                return (AsyncReadCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(AsyncReadCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(AsyncReadCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                int readInt = parcel.readInt();
                DataResult dataResult = (DataResult) b.feF_(parcel, DataResult.CREATOR);
                onReadResult(readInt, dataResult);
                parcel2.writeNoException();
                b.feG_(parcel2, dataResult, 1);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements AsyncReadCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.wearengine.sensor.AsyncReadCallback
            public void onReadResult(int i, DataResult dataResult) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AsyncReadCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    b.feG_(obtain, dataResult, 0);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        dataResult.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return AsyncReadCallback.DESCRIPTOR;
            }
        }
    }

    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T feF_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void feG_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
