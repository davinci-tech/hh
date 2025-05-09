package com.huawei.hihealth;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ISportDataCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.ISportDataCallback";

    public static class Default implements ISportDataCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.ISportDataCallback
        public void onDataChanged(int i, Bundle bundle) throws RemoteException {
        }

        @Override // com.huawei.hihealth.ISportDataCallback
        public void onResult(int i) throws RemoteException {
        }
    }

    void onDataChanged(int i, Bundle bundle) throws RemoteException;

    void onResult(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ISportDataCallback {
        static final int TRANSACTION_onDataChanged = 2;
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ISportDataCallback.DESCRIPTOR);
        }

        public static ISportDataCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISportDataCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISportDataCallback)) {
                return (ISportDataCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ISportDataCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ISportDataCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt());
                parcel2.writeNoException();
            } else if (i == 2) {
                int readInt = parcel.readInt();
                Bundle bundle = (Bundle) _Parcel.bvR_(parcel, Bundle.CREATOR);
                onDataChanged(readInt, bundle);
                parcel2.writeNoException();
                _Parcel.bvS_(parcel2, bundle, 1);
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class Proxy implements ISportDataCallback {
            private IBinder c;

            Proxy(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.hihealth.ISportDataCallback
            public void onResult(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISportDataCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.ISportDataCallback
            public void onDataChanged(int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISportDataCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.bvS_(obtain, bundle, 0);
                    this.c.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T bvR_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void bvS_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
