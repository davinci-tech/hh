package com.huawei.health;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IResultCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.IResultCallback";

    void onFailed(Bundle bundle) throws RemoteException;

    void onServiceException(Bundle bundle) throws RemoteException;

    void onSuccess(Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IResultCallback {
        static final int TRANSACTION_onFailed = 2;
        static final int TRANSACTION_onServiceException = 3;
        static final int TRANSACTION_onSuccess = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IResultCallback.DESCRIPTOR);
        }

        public static IResultCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IResultCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IResultCallback)) {
                return (IResultCallback) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IResultCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IResultCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onSuccess((Bundle) e.AH_(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
            } else if (i == 2) {
                onFailed((Bundle) e.AH_(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
            } else if (i == 3) {
                onServiceException((Bundle) e.AH_(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        /* loaded from: classes3.dex */
        static class e implements IResultCallback {
            private IBinder c;

            e(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.health.IResultCallback
            public void onSuccess(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IResultCallback.DESCRIPTOR);
                    e.AI_(obtain, bundle, 0);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IResultCallback
            public void onFailed(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IResultCallback.DESCRIPTOR);
                    e.AI_(obtain, bundle, 0);
                    this.c.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IResultCallback
            public void onServiceException(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IResultCallback.DESCRIPTOR);
                    e.AI_(obtain, bundle, 0);
                    this.c.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class e {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T AH_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void AI_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
