package com.huawei.wearkit;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public interface IWearCommonCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearkit.IWearCommonCallback";

    void onResult(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IWearCommonCallback {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IWearCommonCallback.DESCRIPTOR);
        }

        public static IWearCommonCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IWearCommonCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IWearCommonCallback)) {
                return (IWearCommonCallback) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IWearCommonCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IWearCommonCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class a implements IWearCommonCallback {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f11251a;

            a(IBinder iBinder) {
                this.f11251a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f11251a;
            }

            @Override // com.huawei.wearkit.IWearCommonCallback
            public void onResult(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IWearCommonCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.f11251a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
