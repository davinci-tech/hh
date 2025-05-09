package com.huawei.wearkit;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* loaded from: classes8.dex */
public interface IBaseCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearkit.IBaseCallback";

    void onResult(int i, Map map) throws RemoteException;

    public static abstract class Stub extends Binder implements IBaseCallback {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IBaseCallback.DESCRIPTOR);
        }

        public static IBaseCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IBaseCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IBaseCallback)) {
                return (IBaseCallback) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IBaseCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IBaseCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt(), parcel.readHashMap(getClass().getClassLoader()));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class d implements IBaseCallback {
            private IBinder c;

            d(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.wearkit.IBaseCallback
            public void onResult(int i, Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IBaseCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeMap(map);
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
