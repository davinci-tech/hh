package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public interface ICommonListenerOhos extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.ICommonListenerOhos";

    public static class Default implements ICommonListenerOhos {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.ICommonListenerOhos
        public void onFailure(int i, List<String> list) throws RemoteException {
        }

        @Override // com.huawei.hihealth.ICommonListenerOhos
        public void onSuccess(int i, List<String> list) throws RemoteException {
        }
    }

    void onFailure(int i, List<String> list) throws RemoteException;

    void onSuccess(int i, List<String> list) throws RemoteException;

    public static abstract class Stub extends Binder implements ICommonListenerOhos {
        static final int TRANSACTION_onFailure = 2;
        static final int TRANSACTION_onSuccess = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ICommonListenerOhos.DESCRIPTOR);
        }

        public static ICommonListenerOhos asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ICommonListenerOhos.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ICommonListenerOhos)) {
                return (ICommonListenerOhos) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ICommonListenerOhos.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ICommonListenerOhos.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onSuccess(parcel.readInt(), parcel.createStringArrayList());
                parcel2.writeNoException();
            } else if (i == 2) {
                onFailure(parcel.readInt(), parcel.createStringArrayList());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class Proxy implements ICommonListenerOhos {
            private IBinder e;

            Proxy(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.hihealth.ICommonListenerOhos
            public void onSuccess(int i, List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICommonListenerOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStringList(list);
                    this.e.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.hihealth.ICommonListenerOhos
            public void onFailure(int i, List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICommonListenerOhos.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStringList(list);
                    this.e.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
