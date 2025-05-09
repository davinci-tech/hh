package com.huawei.hwotamanager;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes5.dex */
public interface ICheckCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwotamanager.ICheckCallback";

    void onCheckComplete(String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements ICheckCallback {
        static final int TRANSACTION_onCheckComplete = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ICheckCallback.DESCRIPTOR);
        }

        public static ICheckCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ICheckCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ICheckCallback)) {
                return (ICheckCallback) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ICheckCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ICheckCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onCheckComplete(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class c implements ICheckCallback {
            private IBinder e;

            c(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.hwotamanager.ICheckCallback
            public void onCheckComplete(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICheckCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
