package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IGetValueCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IGetValueCallback";

    public static class Default implements IGetValueCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IGetValueCallback
        public void onResult(int i, String str, String str2) throws RemoteException {
        }
    }

    void onResult(int i, String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IGetValueCallback {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IGetValueCallback.DESCRIPTOR);
        }

        public static IGetValueCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IGetValueCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IGetValueCallback)) {
                return (IGetValueCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IGetValueCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IGetValueCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements IGetValueCallback {
            private IBinder d;

            Proxy(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.hihealth.IGetValueCallback
            public void onResult(int i, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGetValueCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.d.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
