package com.huawei.wearengine.monitor;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes7.dex */
public interface SwitchStatusCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.monitor.SwitchStatusCallback";

    /* loaded from: classes9.dex */
    public static class Default implements SwitchStatusCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.wearengine.monitor.SwitchStatusCallback
        public void onResult(int i) throws RemoteException {
        }
    }

    void onResult(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements SwitchStatusCallback {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, SwitchStatusCallback.DESCRIPTOR);
        }

        public static SwitchStatusCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(SwitchStatusCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof SwitchStatusCallback)) {
                return (SwitchStatusCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(SwitchStatusCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(SwitchStatusCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements SwitchStatusCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.wearengine.monitor.SwitchStatusCallback
            public void onResult(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(SwitchStatusCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return SwitchStatusCallback.DESCRIPTOR;
            }
        }
    }
}
