package com.huawei.hwotamanager;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes5.dex */
public interface IDeviceListCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwotamanager.IDeviceListCallback";

    void onDeviceListObtain(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IDeviceListCallback {
        static final int TRANSACTION_onDeviceListObtain = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IDeviceListCallback.DESCRIPTOR);
        }

        public static IDeviceListCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDeviceListCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDeviceListCallback)) {
                return (IDeviceListCallback) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDeviceListCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IDeviceListCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onDeviceListObtain(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class a implements IDeviceListCallback {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f6389a;

            a(IBinder iBinder) {
                this.f6389a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f6389a;
            }

            @Override // com.huawei.hwotamanager.IDeviceListCallback
            public void onDeviceListObtain(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDeviceListCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    this.f6389a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
