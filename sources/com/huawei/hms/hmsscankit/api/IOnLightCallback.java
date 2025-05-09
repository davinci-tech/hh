package com.huawei.hms.hmsscankit.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes4.dex */
public interface IOnLightCallback extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements IOnLightCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hms.hmsscankit.api.IOnLightCallback
        public void onVisibleChanged(boolean z) throws RemoteException {
        }
    }

    void onVisibleChanged(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnLightCallback {
        private static final String DESCRIPTOR = "com.huawei.hms.hmsscankit.api.IOnLightCallback";
        static final int TRANSACTION_onVisibleChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOnLightCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IOnLightCallback)) ? new Proxy(iBinder) : (IOnLightCallback) queryLocalInterface;
        }

        public static IOnLightCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IOnLightCallback iOnLightCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iOnLightCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOnLightCallback;
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
            onVisibleChanged(parcel.readInt() != 0);
            parcel2.writeNoException();
            return true;
        }

        static class Proxy implements IOnLightCallback {
            public static IOnLightCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.hms.hmsscankit.api.IOnLightCallback
            public void onVisibleChanged(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onVisibleChanged(z);
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
