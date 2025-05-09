package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ISetValueCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.ISetValueCallback";

    public static class Default implements ISetValueCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.ISetValueCallback
        public void onResult(int i, String str) throws RemoteException {
        }
    }

    void onResult(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ISetValueCallback {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ISetValueCallback.DESCRIPTOR);
        }

        public static ISetValueCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISetValueCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISetValueCallback)) {
                return (ISetValueCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ISetValueCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ISetValueCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements ISetValueCallback {
            private IBinder c;

            Proxy(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.hihealth.ISetValueCallback
            public void onResult(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISetValueCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
