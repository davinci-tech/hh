package com.huawei.health;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes3.dex */
public interface IBaseCommonCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.IBaseCommonCallback";

    void onResponse(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IBaseCommonCallback {
        static final int TRANSACTION_onResponse = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IBaseCommonCallback.DESCRIPTOR);
        }

        public static IBaseCommonCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IBaseCommonCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IBaseCommonCallback)) {
                return (IBaseCommonCallback) queryLocalInterface;
            }
            return new b(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IBaseCommonCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IBaseCommonCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResponse(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class b implements IBaseCommonCallback {
            private IBinder e;

            b(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.health.IBaseCommonCallback
            public void onResponse(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBaseCommonCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
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
