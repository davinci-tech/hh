package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public interface ISyncServiceCallback extends IInterface {

    public static abstract class Stub extends Binder implements ISyncServiceCallback {
        private static final String DESCRIPTOR = "com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback";
        static final int TRANSACTION_handlerEventMsg = 1;

        static class b implements ISyncServiceCallback {
            public static ISyncServiceCallback c;
            private IBinder e;

            b(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.e;
            }

            @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback
            public void handlerEventMsg(int i, int i2, int i3, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.e.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().handlerEventMsg(i, i2, i3, bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISyncServiceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ISyncServiceCallback)) ? new b(iBinder) : (ISyncServiceCallback) queryLocalInterface;
        }

        public static ISyncServiceCallback getDefaultImpl() {
            return b.c;
        }

        public static boolean setDefaultImpl(ISyncServiceCallback iSyncServiceCallback) {
            if (b.c != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSyncServiceCallback == null) {
                return false;
            }
            b.c = iSyncServiceCallback;
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
            handlerEventMsg(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }
    }

    void handlerEventMsg(int i, int i2, int i3, Bundle bundle) throws RemoteException;
}
