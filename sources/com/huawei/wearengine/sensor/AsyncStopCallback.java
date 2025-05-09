package com.huawei.wearengine.sensor;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes9.dex */
public interface AsyncStopCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.sensor.AsyncStopCallback";

    public static class Default implements AsyncStopCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.wearengine.sensor.AsyncStopCallback
        public void onStopResult(int i) throws RemoteException {
        }
    }

    void onStopResult(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements AsyncStopCallback {
        static final int TRANSACTION_onStopResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, AsyncStopCallback.DESCRIPTOR);
        }

        public static AsyncStopCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(AsyncStopCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof AsyncStopCallback)) {
                return (AsyncStopCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(AsyncStopCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(AsyncStopCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onStopResult(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements AsyncStopCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.wearengine.sensor.AsyncStopCallback
            public void onStopResult(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AsyncStopCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return AsyncStopCallback.DESCRIPTOR;
            }
        }
    }
}
