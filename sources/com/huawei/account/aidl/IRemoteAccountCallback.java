package com.huawei.account.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public interface IRemoteAccountCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.account.aidl.IRemoteAccountCallback";

    void onResponse(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteAccountCallback {
        static final int TRANSACTION_onResponse = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IRemoteAccountCallback.DESCRIPTOR);
        }

        public static IRemoteAccountCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IRemoteAccountCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRemoteAccountCallback)) {
                return (IRemoteAccountCallback) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IRemoteAccountCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IRemoteAccountCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResponse(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class a implements IRemoteAccountCallback {
            private IBinder d;

            a(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.account.aidl.IRemoteAccountCallback
            public void onResponse(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteAccountCallback.DESCRIPTOR);
                    obtain.writeInt(i);
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
