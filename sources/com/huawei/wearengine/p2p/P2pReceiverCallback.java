package com.huawei.wearengine.p2p;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.p2p.ReceiveResultCallback;

/* loaded from: classes7.dex */
public interface P2pReceiverCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.p2p.P2pReceiverCallback";

    /* loaded from: classes9.dex */
    public static class Default implements P2pReceiverCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.wearengine.p2p.P2pReceiverCallback
        public void onDataReceived(Device device, MessageParcel messageParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiveResultCallback receiveResultCallback) throws RemoteException {
        }
    }

    void onDataReceived(Device device, MessageParcel messageParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiveResultCallback receiveResultCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements P2pReceiverCallback {
        static final int TRANSACTION_onDataReceived = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, P2pReceiverCallback.DESCRIPTOR);
        }

        public static P2pReceiverCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(P2pReceiverCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof P2pReceiverCallback)) {
                return (P2pReceiverCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(P2pReceiverCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(P2pReceiverCallback.DESCRIPTOR);
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            Device device = (Device) e.fek_(parcel, Device.CREATOR);
            MessageParcel messageParcel = (MessageParcel) e.fek_(parcel, MessageParcel.CREATOR);
            onDataReceived(device, messageParcel, (IdentityInfo) e.fek_(parcel, IdentityInfo.CREATOR), (IdentityInfo) e.fek_(parcel, IdentityInfo.CREATOR), ReceiveResultCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            e.fel_(parcel2, device, 1);
            e.fel_(parcel2, messageParcel, 1);
            return true;
        }

        static class Proxy implements P2pReceiverCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.wearengine.p2p.P2pReceiverCallback
            public void onDataReceived(Device device, MessageParcel messageParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiveResultCallback receiveResultCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(P2pReceiverCallback.DESCRIPTOR);
                    e.fel_(obtain, device, 0);
                    e.fel_(obtain, messageParcel, 0);
                    e.fel_(obtain, identityInfo, 0);
                    e.fel_(obtain, identityInfo2, 0);
                    obtain.writeStrongInterface(receiveResultCallback);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        device.readFromParcel(obtain2);
                    }
                    if (obtain2.readInt() != 0) {
                        messageParcel.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return P2pReceiverCallback.DESCRIPTOR;
            }
        }
    }

    public static class e {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T fek_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fel_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
