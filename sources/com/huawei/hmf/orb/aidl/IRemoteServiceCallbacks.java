package com.huawei.hmf.orb.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes9.dex */
public interface IRemoteServiceCallbacks extends IInterface {

    public static class Default implements IRemoteServiceCallbacks {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hmf.orb.aidl.IRemoteServiceCallbacks
        public void onResult(int i, IBinder iBinder) throws RemoteException {
        }
    }

    void onResult(int i, IBinder iBinder) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteServiceCallbacks {
        private static final String DESCRIPTOR = "com.huawei.hmf.orb.aidl.IRemoteServiceCallbacks";
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteServiceCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRemoteServiceCallbacks)) {
                return (IRemoteServiceCallbacks) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onResult(parcel.readInt(), parcel.readStrongBinder());
            parcel2.writeNoException();
            return true;
        }

        static class Proxy implements IRemoteServiceCallbacks {
            public static IRemoteServiceCallbacks sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.hmf.orb.aidl.IRemoteServiceCallbacks
            public void onResult(int i, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onResult(i, iBinder);
                    } else {
                        obtain2.readException();
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

        public static boolean setDefaultImpl(IRemoteServiceCallbacks iRemoteServiceCallbacks) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRemoteServiceCallbacks == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRemoteServiceCallbacks;
            return true;
        }

        public static IRemoteServiceCallbacks getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
