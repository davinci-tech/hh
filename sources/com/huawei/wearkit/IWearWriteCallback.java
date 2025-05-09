package com.huawei.wearkit;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public interface IWearWriteCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearkit.IWearWriteCallback";

    void onResult(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IWearWriteCallback {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IWearWriteCallback.DESCRIPTOR);
        }

        public static IWearWriteCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IWearWriteCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IWearWriteCallback)) {
                return (IWearWriteCallback) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IWearWriteCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IWearWriteCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class e implements IWearWriteCallback {
            private IBinder d;

            e(IBinder iBinder) {
                this.d = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.d;
            }

            @Override // com.huawei.wearkit.IWearWriteCallback
            public void onResult(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearWriteCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
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
