package com.huawei.devicesdk.callback;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes3.dex */
public interface CompatibleFitterCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.devicesdk.callback.CompatibleFitterCallback";

    void adapterOperate(List<String> list) throws RemoteException;

    public static abstract class Stub extends Binder implements CompatibleFitterCallback {
        static final int TRANSACTION_adapterOperate = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, CompatibleFitterCallback.DESCRIPTOR);
        }

        public static CompatibleFitterCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(CompatibleFitterCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof CompatibleFitterCallback)) {
                return (CompatibleFitterCallback) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(CompatibleFitterCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(CompatibleFitterCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                adapterOperate(parcel.createStringArrayList());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class e implements CompatibleFitterCallback {
            private IBinder b;

            e(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.devicesdk.callback.CompatibleFitterCallback
            public void adapterOperate(List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(CompatibleFitterCallback.DESCRIPTOR);
                    obtain.writeStringList(list);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
