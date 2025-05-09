package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public interface IDataReadResultListener extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IDataReadResultListener";

    public static class Default implements IDataReadResultListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IDataReadResultListener
        public void onResult(List list, int i, int i2) throws RemoteException {
        }
    }

    void onResult(List list, int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IDataReadResultListener {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IDataReadResultListener.DESCRIPTOR);
        }

        public static IDataReadResultListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDataReadResultListener.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDataReadResultListener)) {
                return (IDataReadResultListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDataReadResultListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IDataReadResultListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readArrayList(getClass().getClassLoader()), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements IDataReadResultListener {
            private IBinder b;

            Proxy(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.hihealth.IDataReadResultListener
            public void onResult(List list, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDataReadResultListener.DESCRIPTOR);
                    obtain.writeList(list);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
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
