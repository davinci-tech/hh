package com.huawei.wearengine.p2p;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes7.dex */
public interface P2pSendCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.p2p.P2pSendCallback";

    /* loaded from: classes9.dex */
    public static class Default implements P2pSendCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.wearengine.p2p.P2pSendCallback
        public void onFileTransferReport(String str) throws RemoteException {
        }

        @Override // com.huawei.wearengine.p2p.P2pSendCallback
        public void onSendProgress(long j) throws RemoteException {
        }

        @Override // com.huawei.wearengine.p2p.P2pSendCallback
        public void onSendResult(int i) throws RemoteException {
        }
    }

    void onFileTransferReport(String str) throws RemoteException;

    void onSendProgress(long j) throws RemoteException;

    void onSendResult(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements P2pSendCallback {
        static final int TRANSACTION_onFileTransferReport = 3;
        static final int TRANSACTION_onSendProgress = 2;
        static final int TRANSACTION_onSendResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, P2pSendCallback.DESCRIPTOR);
        }

        public static P2pSendCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(P2pSendCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof P2pSendCallback)) {
                return (P2pSendCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(P2pSendCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(P2pSendCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onSendResult(parcel.readInt());
                parcel2.writeNoException();
            } else if (i == 2) {
                onSendProgress(parcel.readLong());
                parcel2.writeNoException();
            } else if (i == 3) {
                onFileTransferReport(parcel.readString());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class Proxy implements P2pSendCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.wearengine.p2p.P2pSendCallback
            public void onSendResult(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(P2pSendCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.p2p.P2pSendCallback
            public void onSendProgress(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(P2pSendCallback.DESCRIPTOR);
                    obtain.writeLong(j);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.p2p.P2pSendCallback
            public void onFileTransferReport(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(P2pSendCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return P2pSendCallback.DESCRIPTOR;
            }
        }
    }
}
