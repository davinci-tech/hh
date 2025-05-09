package com.huawei.health;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes3.dex */
public interface IGetbindDeviceCommonCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.IGetbindDeviceCommonCallback";

    void onResponse(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IGetbindDeviceCommonCallback {
        static final int TRANSACTION_onResponse = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IGetbindDeviceCommonCallback.DESCRIPTOR);
        }

        public static IGetbindDeviceCommonCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IGetbindDeviceCommonCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IGetbindDeviceCommonCallback)) {
                return (IGetbindDeviceCommonCallback) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IGetbindDeviceCommonCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IGetbindDeviceCommonCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResponse(parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class a implements IGetbindDeviceCommonCallback {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f2172a;

            a(IBinder iBinder) {
                this.f2172a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2172a;
            }

            @Override // com.huawei.health.IGetbindDeviceCommonCallback
            public void onResponse(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IGetbindDeviceCommonCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.f2172a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
