package com.huawei.hms.hmsscankit.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hms.hmsscankit.api.IRemoteFrameDecoderDelegate;

/* loaded from: classes9.dex */
public interface IRemoteDecoderCreator extends IInterface {

    public static class Default implements IRemoteDecoderCreator {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderCreator
        public IRemoteFrameDecoderDelegate newRemoteFrameDecoderDelegate() throws RemoteException {
            return null;
        }
    }

    IRemoteFrameDecoderDelegate newRemoteFrameDecoderDelegate() throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteDecoderCreator {
        private static final String DESCRIPTOR = "com.huawei.hms.hmsscankit.api.IRemoteDecoderCreator";
        static final int TRANSACTION_newRemoteFrameDecoderDelegate = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteDecoderCreator asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IRemoteDecoderCreator)) ? new Proxy(iBinder) : (IRemoteDecoderCreator) queryLocalInterface;
        }

        public static IRemoteDecoderCreator getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IRemoteDecoderCreator iRemoteDecoderCreator) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRemoteDecoderCreator == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRemoteDecoderCreator;
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
            IRemoteFrameDecoderDelegate newRemoteFrameDecoderDelegate = newRemoteFrameDecoderDelegate();
            parcel2.writeNoException();
            parcel2.writeStrongBinder(newRemoteFrameDecoderDelegate != null ? newRemoteFrameDecoderDelegate.asBinder() : null);
            return true;
        }

        static class Proxy implements IRemoteDecoderCreator {
            public static IRemoteDecoderCreator sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.hms.hmsscankit.api.IRemoteDecoderCreator
            public IRemoteFrameDecoderDelegate newRemoteFrameDecoderDelegate() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().newRemoteFrameDecoderDelegate();
                    }
                    obtain2.readException();
                    return IRemoteFrameDecoderDelegate.Stub.asInterface(obtain2.readStrongBinder());
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
