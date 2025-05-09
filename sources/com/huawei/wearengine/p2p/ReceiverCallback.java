package com.huawei.wearengine.p2p;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* loaded from: classes9.dex */
public interface ReceiverCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.p2p.ReceiverCallback";

    public static class Default implements ReceiverCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.wearengine.p2p.ReceiverCallback
        public void onReceiveFileMessage(MessageParcel messageParcel) throws RemoteException {
        }

        @Override // com.huawei.wearengine.p2p.ReceiverCallback
        public void onReceiveMessage(byte[] bArr) throws RemoteException {
        }
    }

    void onReceiveFileMessage(MessageParcel messageParcel) throws RemoteException;

    void onReceiveMessage(byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements ReceiverCallback {
        static final int TRANSACTION_onReceiveFileMessage = 2;
        static final int TRANSACTION_onReceiveMessage = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ReceiverCallback.DESCRIPTOR);
        }

        public static ReceiverCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ReceiverCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ReceiverCallback)) {
                return (ReceiverCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ReceiverCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ReceiverCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                byte[] createByteArray = parcel.createByteArray();
                onReceiveMessage(createByteArray);
                parcel2.writeNoException();
                parcel2.writeByteArray(createByteArray);
            } else if (i == 2) {
                onReceiveFileMessage((MessageParcel) e.fep_(parcel, MessageParcel.CREATOR));
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        static class Proxy implements ReceiverCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.wearengine.p2p.ReceiverCallback
            public void onReceiveMessage(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ReceiverCallback.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    obtain2.readByteArray(bArr);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.p2p.ReceiverCallback
            public void onReceiveFileMessage(MessageParcel messageParcel) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ReceiverCallback.DESCRIPTOR);
                    e.feq_(obtain, messageParcel, 0);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return ReceiverCallback.DESCRIPTOR;
            }
        }
    }

    public static class e {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T fep_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void feq_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
