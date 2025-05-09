package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public interface IDataHiDeviceInfoListener extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IDataHiDeviceInfoListener";

    public static class Default implements IDataHiDeviceInfoListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IDataHiDeviceInfoListener
        public void onResult(List list) throws RemoteException {
        }
    }

    void onResult(List list) throws RemoteException;

    public static abstract class Stub extends Binder implements IDataHiDeviceInfoListener {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IDataHiDeviceInfoListener.DESCRIPTOR);
        }

        public static IDataHiDeviceInfoListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDataHiDeviceInfoListener.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDataHiDeviceInfoListener)) {
                return (IDataHiDeviceInfoListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDataHiDeviceInfoListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IDataHiDeviceInfoListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readArrayList(getClass().getClassLoader()));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements IDataHiDeviceInfoListener {
            private IBinder e;

            Proxy(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.hihealth.IDataHiDeviceInfoListener
            public void onResult(List list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDataHiDeviceInfoListener.DESCRIPTOR);
                    obtain.writeList(list);
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
