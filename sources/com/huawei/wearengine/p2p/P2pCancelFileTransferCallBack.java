package com.huawei.wearengine.p2p;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes7.dex */
public interface P2pCancelFileTransferCallBack extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.p2p.P2pCancelFileTransferCallBack";

    /* loaded from: classes9.dex */
    public static class Default implements P2pCancelFileTransferCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.wearengine.p2p.P2pCancelFileTransferCallBack
        public void onCancelFileTransferResult(int i, String str) throws RemoteException {
        }
    }

    void onCancelFileTransferResult(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements P2pCancelFileTransferCallBack {
        static final int TRANSACTION_onCancelFileTransferResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, P2pCancelFileTransferCallBack.DESCRIPTOR);
        }

        public static P2pCancelFileTransferCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(P2pCancelFileTransferCallBack.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof P2pCancelFileTransferCallBack)) {
                return (P2pCancelFileTransferCallBack) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(P2pCancelFileTransferCallBack.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(P2pCancelFileTransferCallBack.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onCancelFileTransferResult(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class Proxy implements P2pCancelFileTransferCallBack {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.wearengine.p2p.P2pCancelFileTransferCallBack
            public void onCancelFileTransferResult(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(P2pCancelFileTransferCallBack.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return P2pCancelFileTransferCallBack.DESCRIPTOR;
            }
        }
    }
}
