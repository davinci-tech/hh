package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICommonCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.ICommonCallback";

    public static class Default implements ICommonCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.ICommonCallback
        public void onResult(int i, String str) throws RemoteException {
        }
    }

    void onResult(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ICommonCallback {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ICommonCallback.DESCRIPTOR);
        }

        public static ICommonCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ICommonCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ICommonCallback)) {
                return (ICommonCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ICommonCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ICommonCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements ICommonCallback {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f4052a;

            Proxy(IBinder iBinder) {
                this.f4052a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4052a;
            }

            @Override // com.huawei.hihealth.ICommonCallback
            public void onResult(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICommonCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.f4052a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
