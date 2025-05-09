package com.huawei.wearengine;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.p2p.FileIdentificationParcel;
import com.huawei.wearengine.p2p.IdentityInfo;
import com.huawei.wearengine.p2p.MessageParcel;
import com.huawei.wearengine.p2p.MessageParcelExtra;
import com.huawei.wearengine.p2p.P2pCancelFileTransferCallBack;
import com.huawei.wearengine.p2p.P2pPingCallback;
import com.huawei.wearengine.p2p.P2pSendCallback;
import com.huawei.wearengine.p2p.ReceiverCallback;

/* loaded from: classes9.dex */
public interface P2pManager extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.P2pManager";

    int cancelFileTransfer(Device device, FileIdentificationParcel fileIdentificationParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack) throws RemoteException;

    int getDeviceAppVersionCode(Device device, String str, String str2) throws RemoteException;

    int ping(Device device, String str, String str2, P2pPingCallback p2pPingCallback) throws RemoteException;

    int registerReceiver(Device device, IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiverCallback receiverCallback, int i) throws RemoteException;

    int registerReceiverInternal(Device device, IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiverCallback receiverCallback, int i) throws RemoteException;

    int send(Device device, MessageParcel messageParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException;

    int sendExtra(Device device, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException;

    int sendInternal(Device device, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException;

    int unregisterReceiver(ReceiverCallback receiverCallback, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements P2pManager {
        static final int TRANSACTION_cancelFileTransfer = 9;
        static final int TRANSACTION_getDeviceAppVersionCode = 6;
        static final int TRANSACTION_ping = 1;
        static final int TRANSACTION_registerReceiver = 3;
        static final int TRANSACTION_registerReceiverInternal = 8;
        static final int TRANSACTION_send = 2;
        static final int TRANSACTION_sendExtra = 5;
        static final int TRANSACTION_sendInternal = 7;
        static final int TRANSACTION_unregisterReceiver = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, P2pManager.DESCRIPTOR);
        }

        public static P2pManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(P2pManager.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof P2pManager)) {
                return (P2pManager) queryLocalInterface;
            }
            return new e(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(P2pManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(P2pManager.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    int ping = ping((Device) c.fcH_(parcel, Device.CREATOR), parcel.readString(), parcel.readString(), P2pPingCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(ping);
                    return true;
                case 2:
                    int send = send((Device) c.fcH_(parcel, Device.CREATOR), (MessageParcel) c.fcH_(parcel, MessageParcel.CREATOR), (IdentityInfo) c.fcH_(parcel, IdentityInfo.CREATOR), (IdentityInfo) c.fcH_(parcel, IdentityInfo.CREATOR), P2pSendCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(send);
                    return true;
                case 3:
                    int registerReceiver = registerReceiver((Device) c.fcH_(parcel, Device.CREATOR), (IdentityInfo) c.fcH_(parcel, IdentityInfo.CREATOR), (IdentityInfo) c.fcH_(parcel, IdentityInfo.CREATOR), ReceiverCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(registerReceiver);
                    return true;
                case 4:
                    int unregisterReceiver = unregisterReceiver(ReceiverCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(unregisterReceiver);
                    return true;
                case 5:
                    int sendExtra = sendExtra((Device) c.fcH_(parcel, Device.CREATOR), (MessageParcelExtra) c.fcH_(parcel, MessageParcelExtra.CREATOR), (IdentityInfo) c.fcH_(parcel, IdentityInfo.CREATOR), (IdentityInfo) c.fcH_(parcel, IdentityInfo.CREATOR), P2pSendCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(sendExtra);
                    return true;
                case 6:
                    int deviceAppVersionCode = getDeviceAppVersionCode((Device) c.fcH_(parcel, Device.CREATOR), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(deviceAppVersionCode);
                    return true;
                case 7:
                    int sendInternal = sendInternal((Device) c.fcH_(parcel, Device.CREATOR), (MessageParcelExtra) c.fcH_(parcel, MessageParcelExtra.CREATOR), (IdentityInfo) c.fcH_(parcel, IdentityInfo.CREATOR), (IdentityInfo) c.fcH_(parcel, IdentityInfo.CREATOR), P2pSendCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(sendInternal);
                    return true;
                case 8:
                    int registerReceiverInternal = registerReceiverInternal((Device) c.fcH_(parcel, Device.CREATOR), (IdentityInfo) c.fcH_(parcel, IdentityInfo.CREATOR), (IdentityInfo) c.fcH_(parcel, IdentityInfo.CREATOR), ReceiverCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(registerReceiverInternal);
                    return true;
                case 9:
                    int cancelFileTransfer = cancelFileTransfer((Device) c.fcH_(parcel, Device.CREATOR), (FileIdentificationParcel) c.fcH_(parcel, FileIdentificationParcel.CREATOR), (IdentityInfo) c.fcH_(parcel, IdentityInfo.CREATOR), (IdentityInfo) c.fcH_(parcel, IdentityInfo.CREATOR), P2pCancelFileTransferCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(cancelFileTransfer);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class e implements P2pManager {
            private IBinder b;

            e(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }

            @Override // com.huawei.wearengine.P2pManager
            public int ping(Device device, String str, String str2, P2pPingCallback p2pPingCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(P2pManager.DESCRIPTOR);
                    c.fcI_(obtain, device, 0);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(p2pPingCallback);
                    this.b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.P2pManager
            public int send(Device device, MessageParcel messageParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(P2pManager.DESCRIPTOR);
                    c.fcI_(obtain, device, 0);
                    c.fcI_(obtain, messageParcel, 0);
                    c.fcI_(obtain, identityInfo, 0);
                    c.fcI_(obtain, identityInfo2, 0);
                    obtain.writeStrongInterface(p2pSendCallback);
                    this.b.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.P2pManager
            public int registerReceiver(Device device, IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiverCallback receiverCallback, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(P2pManager.DESCRIPTOR);
                    c.fcI_(obtain, device, 0);
                    c.fcI_(obtain, identityInfo, 0);
                    c.fcI_(obtain, identityInfo2, 0);
                    obtain.writeStrongInterface(receiverCallback);
                    obtain.writeInt(i);
                    this.b.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.P2pManager
            public int unregisterReceiver(ReceiverCallback receiverCallback, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(P2pManager.DESCRIPTOR);
                    obtain.writeStrongInterface(receiverCallback);
                    obtain.writeInt(i);
                    this.b.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.P2pManager
            public int sendExtra(Device device, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(P2pManager.DESCRIPTOR);
                    c.fcI_(obtain, device, 0);
                    c.fcI_(obtain, messageParcelExtra, 0);
                    c.fcI_(obtain, identityInfo, 0);
                    c.fcI_(obtain, identityInfo2, 0);
                    obtain.writeStrongInterface(p2pSendCallback);
                    this.b.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.P2pManager
            public int getDeviceAppVersionCode(Device device, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(P2pManager.DESCRIPTOR);
                    c.fcI_(obtain, device, 0);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.b.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.P2pManager
            public int sendInternal(Device device, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(P2pManager.DESCRIPTOR);
                    c.fcI_(obtain, device, 0);
                    c.fcI_(obtain, messageParcelExtra, 0);
                    c.fcI_(obtain, identityInfo, 0);
                    c.fcI_(obtain, identityInfo2, 0);
                    obtain.writeStrongInterface(p2pSendCallback);
                    this.b.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.P2pManager
            public int registerReceiverInternal(Device device, IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiverCallback receiverCallback, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(P2pManager.DESCRIPTOR);
                    c.fcI_(obtain, device, 0);
                    c.fcI_(obtain, identityInfo, 0);
                    c.fcI_(obtain, identityInfo2, 0);
                    obtain.writeStrongInterface(receiverCallback);
                    obtain.writeInt(i);
                    this.b.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.P2pManager
            public int cancelFileTransfer(Device device, FileIdentificationParcel fileIdentificationParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(P2pManager.DESCRIPTOR);
                    c.fcI_(obtain, device, 0);
                    c.fcI_(obtain, fileIdentificationParcel, 0);
                    c.fcI_(obtain, identityInfo, 0);
                    c.fcI_(obtain, identityInfo2, 0);
                    obtain.writeStrongInterface(p2pCancelFileTransferCallBack);
                    this.b.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T fcH_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fcI_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
