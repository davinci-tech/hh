package com.huawei.hihealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* loaded from: classes.dex */
public interface IDeleteListenerEx extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hihealth.IDeleteListenerEx";

    public static class Default implements IDeleteListenerEx {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hihealth.IDeleteListenerEx
        public void onResult(Map map) throws RemoteException {
        }
    }

    void onResult(Map map) throws RemoteException;

    public static abstract class Stub extends Binder implements IDeleteListenerEx {
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IDeleteListenerEx.DESCRIPTOR);
        }

        public static IDeleteListenerEx asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDeleteListenerEx.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDeleteListenerEx)) {
                return (IDeleteListenerEx) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDeleteListenerEx.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IDeleteListenerEx.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onResult(parcel.readHashMap(getClass().getClassLoader()));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements IDeleteListenerEx {
            private IBinder b;

            Proxy(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.hihealth.IDeleteListenerEx
            public void onResult(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDeleteListenerEx.DESCRIPTOR);
                    obtain.writeMap(map);
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
