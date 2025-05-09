package com.huawei.wearengine;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.device.FoundListener;
import com.huawei.wearengine.device.GetAttributeListener;
import com.huawei.wearengine.monitor.MonitorCallback;
import com.huawei.wearengine.monitor.QueryDataCallback;
import com.huawei.wearengine.monitor.SwitchStatusCallback;
import com.huawei.wearengine.notify.NotificationParcel;
import com.huawei.wearengine.notify.NotifySendCallback;
import com.huawei.wearengine.p2p.FileIdentificationParcel;
import com.huawei.wearengine.p2p.IdentityInfo;
import com.huawei.wearengine.p2p.MessageParcelExtra;
import com.huawei.wearengine.p2p.P2pCancelFileTransferCallBack;
import com.huawei.wearengine.p2p.P2pPingCallback;
import com.huawei.wearengine.p2p.P2pReceiverCallback;
import com.huawei.wearengine.p2p.P2pSendCallback;

/* loaded from: classes2.dex */
public interface HiWearEngineService extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.wearengine.HiWearEngineService";

    /* loaded from: classes9.dex */
    public static class Default implements HiWearEngineService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void cancelFileTransfer(Device device, FileIdentificationParcel fileIdentificationParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack) throws RemoteException {
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void getAllBondedDevices(FoundListener foundListener, String str) throws RemoteException {
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void getBondedDevices(FoundListener foundListener, String str, boolean z) throws RemoteException {
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void getCommonDevices(FoundListener foundListener, String str, boolean z) throws RemoteException {
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void getDeviceAppVersionCode(Device device, String str, String str2, P2pPingCallback p2pPingCallback) throws RemoteException {
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void getHiLinkDeviceId(Device device, GetAttributeListener getAttributeListener) throws RemoteException {
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void notify(String str, NotificationParcel notificationParcel, NotifySendCallback notifySendCallback) throws RemoteException {
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void ping(Device device, String str, String str2, P2pPingCallback p2pPingCallback) throws RemoteException {
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void query(Device device, String str, QueryDataCallback queryDataCallback) throws RemoteException {
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void queryDeviceCapability(Device device, int i, GetAttributeListener getAttributeListener) throws RemoteException {
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void send(String str, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException {
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void subscribeDeviceDataReceiver(P2pReceiverCallback p2pReceiverCallback) throws RemoteException {
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void subscribeMonitorReceiver(MonitorCallback monitorCallback) throws RemoteException {
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void switchMonitorStatus(int i, String str, String str2, SwitchStatusCallback switchStatusCallback) throws RemoteException {
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void unsubscribeDeviceDataReceiver() throws RemoteException {
        }

        @Override // com.huawei.wearengine.HiWearEngineService
        public void unsubscribeMonitorReceiver() throws RemoteException {
        }
    }

    void cancelFileTransfer(Device device, FileIdentificationParcel fileIdentificationParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack) throws RemoteException;

    void getAllBondedDevices(FoundListener foundListener, String str) throws RemoteException;

    void getBondedDevices(FoundListener foundListener, String str, boolean z) throws RemoteException;

    void getCommonDevices(FoundListener foundListener, String str, boolean z) throws RemoteException;

    void getDeviceAppVersionCode(Device device, String str, String str2, P2pPingCallback p2pPingCallback) throws RemoteException;

    void getHiLinkDeviceId(Device device, GetAttributeListener getAttributeListener) throws RemoteException;

    void notify(String str, NotificationParcel notificationParcel, NotifySendCallback notifySendCallback) throws RemoteException;

    void ping(Device device, String str, String str2, P2pPingCallback p2pPingCallback) throws RemoteException;

    void query(Device device, String str, QueryDataCallback queryDataCallback) throws RemoteException;

    void queryDeviceCapability(Device device, int i, GetAttributeListener getAttributeListener) throws RemoteException;

    void send(String str, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException;

    void subscribeDeviceDataReceiver(P2pReceiverCallback p2pReceiverCallback) throws RemoteException;

    void subscribeMonitorReceiver(MonitorCallback monitorCallback) throws RemoteException;

    void switchMonitorStatus(int i, String str, String str2, SwitchStatusCallback switchStatusCallback) throws RemoteException;

    void unsubscribeDeviceDataReceiver() throws RemoteException;

    void unsubscribeMonitorReceiver() throws RemoteException;

    public static abstract class Stub extends Binder implements HiWearEngineService {
        static final int TRANSACTION_cancelFileTransfer = 13;
        static final int TRANSACTION_getAllBondedDevices = 14;
        static final int TRANSACTION_getBondedDevices = 1;
        static final int TRANSACTION_getCommonDevices = 15;
        static final int TRANSACTION_getDeviceAppVersionCode = 12;
        static final int TRANSACTION_getHiLinkDeviceId = 11;
        static final int TRANSACTION_notify = 10;
        static final int TRANSACTION_ping = 2;
        static final int TRANSACTION_query = 6;
        static final int TRANSACTION_queryDeviceCapability = 16;
        static final int TRANSACTION_send = 3;
        static final int TRANSACTION_subscribeDeviceDataReceiver = 4;
        static final int TRANSACTION_subscribeMonitorReceiver = 7;
        static final int TRANSACTION_switchMonitorStatus = 9;
        static final int TRANSACTION_unsubscribeDeviceDataReceiver = 5;
        static final int TRANSACTION_unsubscribeMonitorReceiver = 8;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, HiWearEngineService.DESCRIPTOR);
        }

        public static HiWearEngineService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(HiWearEngineService.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof HiWearEngineService)) {
                return (HiWearEngineService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(HiWearEngineService.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(HiWearEngineService.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    getBondedDevices(FoundListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    ping((Device) c.fcj_(parcel, Device.CREATOR), parcel.readString(), parcel.readString(), P2pPingCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    send(parcel.readString(), (MessageParcelExtra) c.fcj_(parcel, MessageParcelExtra.CREATOR), (IdentityInfo) c.fcj_(parcel, IdentityInfo.CREATOR), (IdentityInfo) c.fcj_(parcel, IdentityInfo.CREATOR), P2pSendCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    subscribeDeviceDataReceiver(P2pReceiverCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    unsubscribeDeviceDataReceiver();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    query((Device) c.fcj_(parcel, Device.CREATOR), parcel.readString(), QueryDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    subscribeMonitorReceiver(MonitorCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    unsubscribeMonitorReceiver();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    switchMonitorStatus(parcel.readInt(), parcel.readString(), parcel.readString(), SwitchStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    notify(parcel.readString(), (NotificationParcel) c.fcj_(parcel, NotificationParcel.CREATOR), NotifySendCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    getHiLinkDeviceId((Device) c.fcj_(parcel, Device.CREATOR), GetAttributeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    getDeviceAppVersionCode((Device) c.fcj_(parcel, Device.CREATOR), parcel.readString(), parcel.readString(), P2pPingCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    cancelFileTransfer((Device) c.fcj_(parcel, Device.CREATOR), (FileIdentificationParcel) c.fcj_(parcel, FileIdentificationParcel.CREATOR), (IdentityInfo) c.fcj_(parcel, IdentityInfo.CREATOR), (IdentityInfo) c.fcj_(parcel, IdentityInfo.CREATOR), P2pCancelFileTransferCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    getAllBondedDevices(FoundListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    getCommonDevices(FoundListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    queryDeviceCapability((Device) c.fcj_(parcel, Device.CREATOR), parcel.readInt(), GetAttributeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes9.dex */
        static class Proxy implements HiWearEngineService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void getBondedDevices(FoundListener foundListener, String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    obtain.writeStrongInterface(foundListener);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void ping(Device device, String str, String str2, P2pPingCallback p2pPingCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    c.fck_(obtain, device, 0);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(p2pPingCallback);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void send(String str, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    obtain.writeString(str);
                    c.fck_(obtain, messageParcelExtra, 0);
                    c.fck_(obtain, identityInfo, 0);
                    c.fck_(obtain, identityInfo2, 0);
                    obtain.writeStrongInterface(p2pSendCallback);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void subscribeDeviceDataReceiver(P2pReceiverCallback p2pReceiverCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    obtain.writeStrongInterface(p2pReceiverCallback);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void unsubscribeDeviceDataReceiver() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void query(Device device, String str, QueryDataCallback queryDataCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    c.fck_(obtain, device, 0);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(queryDataCallback);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void subscribeMonitorReceiver(MonitorCallback monitorCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    obtain.writeStrongInterface(monitorCallback);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void unsubscribeMonitorReceiver() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void switchMonitorStatus(int i, String str, String str2, SwitchStatusCallback switchStatusCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(switchStatusCallback);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void notify(String str, NotificationParcel notificationParcel, NotifySendCallback notifySendCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    obtain.writeString(str);
                    c.fck_(obtain, notificationParcel, 0);
                    obtain.writeStrongInterface(notifySendCallback);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void getHiLinkDeviceId(Device device, GetAttributeListener getAttributeListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    c.fck_(obtain, device, 0);
                    obtain.writeStrongInterface(getAttributeListener);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void getDeviceAppVersionCode(Device device, String str, String str2, P2pPingCallback p2pPingCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    c.fck_(obtain, device, 0);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(p2pPingCallback);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void cancelFileTransfer(Device device, FileIdentificationParcel fileIdentificationParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    c.fck_(obtain, device, 0);
                    c.fck_(obtain, fileIdentificationParcel, 0);
                    c.fck_(obtain, identityInfo, 0);
                    c.fck_(obtain, identityInfo2, 0);
                    obtain.writeStrongInterface(p2pCancelFileTransferCallBack);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void getAllBondedDevices(FoundListener foundListener, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    obtain.writeStrongInterface(foundListener);
                    obtain.writeString(str);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void getCommonDevices(FoundListener foundListener, String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    obtain.writeStrongInterface(foundListener);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.wearengine.HiWearEngineService
            public void queryDeviceCapability(Device device, int i, GetAttributeListener getAttributeListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(HiWearEngineService.DESCRIPTOR);
                    c.fck_(obtain, device, 0);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(getAttributeListener);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return HiWearEngineService.DESCRIPTOR;
            }
        }
    }

    /* loaded from: classes7.dex */
    public static class c {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T fcj_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void fck_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
