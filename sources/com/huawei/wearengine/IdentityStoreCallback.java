package com.huawei.wearengine;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes7.dex */
public interface IdentityStoreCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.IdentityStoreCallback";

    void storePermissionIdentity(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IdentityStoreCallback {
        static final int TRANSACTION_storePermissionIdentity = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IdentityStoreCallback.DESCRIPTOR);
        }

        public static IdentityStoreCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IdentityStoreCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IdentityStoreCallback)) {
                return (IdentityStoreCallback) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IdentityStoreCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IdentityStoreCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                storePermissionIdentity(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class c implements IdentityStoreCallback {
            private IBinder c;

            c(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.wearengine.IdentityStoreCallback
            public void storePermissionIdentity(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IdentityStoreCallback.DESCRIPTOR);
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
