package com.huawei.wearengine.auth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes9.dex */
public interface AuthListener extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.auth.AuthListener";

    void onCancel() throws RemoteException;

    void onOk(Permission[] permissionArr) throws RemoteException;

    public static abstract class Stub extends Binder implements AuthListener {
        static final int TRANSACTION_onCancel = 2;
        static final int TRANSACTION_onOk = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, AuthListener.DESCRIPTOR);
        }

        public static AuthListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(AuthListener.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof AuthListener)) {
                return (AuthListener) queryLocalInterface;
            }
            return new b(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(AuthListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(AuthListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onOk((Permission[]) parcel.createTypedArray(Permission.CREATOR));
                parcel2.writeNoException();
            } else if (i == 2) {
                onCancel();
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class b implements AuthListener {
            private IBinder e;

            b(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.wearengine.auth.AuthListener
            public void onOk(Permission[] permissionArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AuthListener.DESCRIPTOR);
                    obtain.writeTypedArray(permissionArr, 0);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.auth.AuthListener
            public void onCancel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AuthListener.DESCRIPTOR);
                    this.e.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
