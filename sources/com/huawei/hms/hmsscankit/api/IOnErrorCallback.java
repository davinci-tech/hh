package com.huawei.hms.hmsscankit.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes4.dex */
public interface IOnErrorCallback extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements IOnErrorCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hms.hmsscankit.api.IOnErrorCallback
        public void onError(int i) throws RemoteException {
        }
    }

    void onError(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnErrorCallback {
        private static final String DESCRIPTOR = "com.huawei.hms.hmsscankit.api.IOnErrorCallback";
        static final int TRANSACTION_onError = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOnErrorCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IOnErrorCallback)) ? new Proxy(iBinder) : (IOnErrorCallback) queryLocalInterface;
        }

        public static IOnErrorCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IOnErrorCallback iOnErrorCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iOnErrorCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOnErrorCallback;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            onError(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        static class Proxy implements IOnErrorCallback {
            public static IOnErrorCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.hms.hmsscankit.api.IOnErrorCallback
            public void onError(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onError(i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }
    }
}
