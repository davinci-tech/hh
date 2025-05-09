package com.huawei.health;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* loaded from: classes3.dex */
public interface IRemoteProxyCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.IRemoteProxyCallback";

    void requestWearTask(Map map) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteProxyCallback {
        static final int TRANSACTION_requestWearTask = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IRemoteProxyCallback.DESCRIPTOR);
        }

        public static IRemoteProxyCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IRemoteProxyCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRemoteProxyCallback)) {
                return (IRemoteProxyCallback) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IRemoteProxyCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IRemoteProxyCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                requestWearTask(parcel.readHashMap(getClass().getClassLoader()));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class a implements IRemoteProxyCallback {
            private IBinder b;

            a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.health.IRemoteProxyCallback
            public void requestWearTask(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRemoteProxyCallback.DESCRIPTOR);
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
