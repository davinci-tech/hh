package com.huawei.wearkit;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public interface IRealTimeCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearkit.IRealTimeCallback";

    void onChange(int i, String str) throws RemoteException;

    void onResult(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IRealTimeCallback {
        static final int TRANSACTION_onChange = 2;
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IRealTimeCallback.DESCRIPTOR);
        }

        public static IRealTimeCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IRealTimeCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRealTimeCallback)) {
                return (IRealTimeCallback) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IRealTimeCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IRealTimeCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt());
                parcel2.writeNoException();
            } else if (i == 2) {
                onChange(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class e implements IRealTimeCallback {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f11250a;

            e(IBinder iBinder) {
                this.f11250a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f11250a;
            }

            @Override // com.huawei.wearkit.IRealTimeCallback
            public void onResult(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRealTimeCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.f11250a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearkit.IRealTimeCallback
            public void onChange(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRealTimeCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.f11250a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
