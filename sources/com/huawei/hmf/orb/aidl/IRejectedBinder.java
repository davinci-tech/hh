package com.huawei.hmf.orb.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes9.dex */
public interface IRejectedBinder extends IInterface {

    public static class Default implements IRejectedBinder {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hmf.orb.aidl.IRejectedBinder
        public int getCode() throws RemoteException {
            return 0;
        }
    }

    int getCode() throws RemoteException;

    public static abstract class Stub extends Binder implements IRejectedBinder {
        private static final String DESCRIPTOR = "com.huawei.hmf.orb.aidl.IRejectedBinder";
        static final int TRANSACTION_getCode = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRejectedBinder asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRejectedBinder)) {
                return (IRejectedBinder) queryLocalInterface;
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
            int code = getCode();
            parcel2.writeNoException();
            parcel2.writeInt(code);
            return true;
        }

        static class Proxy implements IRejectedBinder {
            public static IRejectedBinder sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.hmf.orb.aidl.IRejectedBinder
            public int getCode() throws RemoteException {
                int readInt;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        readInt = Stub.getDefaultImpl().getCode();
                    } else {
                        obtain2.readException();
                        readInt = obtain2.readInt();
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public static boolean setDefaultImpl(IRejectedBinder iRejectedBinder) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRejectedBinder == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRejectedBinder;
            return true;
        }

        public static IRejectedBinder getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
