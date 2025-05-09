package com.huawei.devicesdk.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.huawei.devicesdk.callback.CompatibleFitterCallback;
import com.huawei.devicesdk.callback.DeviceHandshakeCallback;
import com.huawei.devicesdk.callback.FrameReceiver;
import com.huawei.devicesdk.callback.ScanCallback;
import com.huawei.devicesdk.callback.StatusCallback;
import com.huawei.devicesdk.entity.CommandMessageParcel;
import com.huawei.devicesdk.entity.ScanFilterParcel;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.unitedevice.callback.IResultAIDLCallback;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.entity.CommonFileInfoParcel;
import com.huawei.unitedevice.hwcommonfilemgr.entity.FileInfo;
import com.huawei.unitedevice.hwcommonfilemgr.entity.RequestFileInfo;
import com.huawei.unitedevice.p2p.IdentityInfo;
import com.huawei.unitedevice.p2p.MessageParcel;
import com.huawei.unitedevice.p2p.P2pPingCallback;
import com.huawei.unitedevice.p2p.P2pSendCallback;
import com.huawei.unitedevice.p2p.ReceiverCallback;
import java.util.List;

/* loaded from: classes3.dex */
public interface DevicesManagementBinderInterface extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.devicesdk.service.DevicesManagementBinderInterface";

    int connectDevice(UniteDevice uniteDevice, boolean z, int i, DeviceHandshakeCallback deviceHandshakeCallback) throws RemoteException;

    int disconnectDevice(UniteDevice uniteDevice) throws RemoteException;

    List<UniteDevice> getDeviceList() throws RemoteException;

    List<DeviceInfo> getDeviceMgrList(int i, String str) throws RemoteException;

    int initUniteService(CompatibleFitterCallback compatibleFitterCallback) throws RemoteException;

    boolean isSupportCharacteristic(UniteDevice uniteDevice, String str, String str2) throws RemoteException;

    boolean isSupportService(UniteDevice uniteDevice, String str) throws RemoteException;

    int pairDevice(UniteDevice uniteDevice, int i) throws RemoteException;

    void ping(UniteDevice uniteDevice, String str, String str2, P2pPingCallback p2pPingCallback, int i) throws RemoteException;

    int registerDeviceFrameListener(FrameReceiver frameReceiver) throws RemoteException;

    int registerDeviceStatusListener(StatusCallback statusCallback) throws RemoteException;

    void registerHandshakeCallback(DeviceHandshakeCallback deviceHandshakeCallback) throws RemoteException;

    void registerListener(String str, String str2, FrameReceiver frameReceiver) throws RemoteException;

    int scanDevice(int i, List<ScanFilterParcel> list, ScanCallback scanCallback) throws RemoteException;

    int send(UniteDevice uniteDevice, MessageParcel messageParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException;

    int sendCommand(UniteDevice uniteDevice, CommandMessageParcel commandMessageParcel) throws RemoteException;

    int setCharacteristicNotify(UniteDevice uniteDevice, String str, String str2, int i, boolean z) throws RemoteException;

    void startRequestFile(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) throws RemoteException;

    int startTransferFile(FileInfo fileInfo, IResultAIDLCallback iResultAIDLCallback) throws RemoteException;

    void stopRequestFile(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) throws RemoteException;

    void stopScanDevice() throws RemoteException;

    void stopTransferByQueue(CommonFileInfoParcel commonFileInfoParcel, ITransferFileCallback iTransferFileCallback) throws RemoteException;

    void subscribeDeviceDataReceiver(IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiverCallback receiverCallback, String str, P2pSendCallback p2pSendCallback) throws RemoteException;

    int unpairDevice(UniteDevice uniteDevice) throws RemoteException;

    int unregisterDeviceFrameListener(FrameReceiver frameReceiver) throws RemoteException;

    int unregisterDeviceStatusListener(StatusCallback statusCallback) throws RemoteException;

    void unregisterListener(String str, String str2) throws RemoteException;

    void unsubscribeDeviceDataReceiver(ReceiverCallback receiverCallback, String str, String str2, String str3) throws RemoteException;

    void updateDeviceAfterSimulatConnected(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements DevicesManagementBinderInterface {
        static final int TRANSACTION_connectDevice = 5;
        static final int TRANSACTION_disconnectDevice = 9;
        static final int TRANSACTION_getDeviceList = 7;
        static final int TRANSACTION_getDeviceMgrList = 29;
        static final int TRANSACTION_initUniteService = 1;
        static final int TRANSACTION_isSupportCharacteristic = 11;
        static final int TRANSACTION_isSupportService = 10;
        static final int TRANSACTION_pairDevice = 4;
        static final int TRANSACTION_ping = 17;
        static final int TRANSACTION_registerDeviceFrameListener = 14;
        static final int TRANSACTION_registerDeviceStatusListener = 12;
        static final int TRANSACTION_registerHandshakeCallback = 27;
        static final int TRANSACTION_registerListener = 25;
        static final int TRANSACTION_scanDevice = 2;
        static final int TRANSACTION_send = 18;
        static final int TRANSACTION_sendCommand = 6;
        static final int TRANSACTION_setCharacteristicNotify = 16;
        static final int TRANSACTION_startRequestFile = 21;
        static final int TRANSACTION_startTransferFile = 23;
        static final int TRANSACTION_stopRequestFile = 22;
        static final int TRANSACTION_stopScanDevice = 3;
        static final int TRANSACTION_stopTransferByQueue = 24;
        static final int TRANSACTION_subscribeDeviceDataReceiver = 19;
        static final int TRANSACTION_unpairDevice = 8;
        static final int TRANSACTION_unregisterDeviceFrameListener = 15;
        static final int TRANSACTION_unregisterDeviceStatusListener = 13;
        static final int TRANSACTION_unregisterListener = 26;
        static final int TRANSACTION_unsubscribeDeviceDataReceiver = 20;
        static final int TRANSACTION_updateDeviceAfterSimulatConnected = 28;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DevicesManagementBinderInterface.DESCRIPTOR);
        }

        public static DevicesManagementBinderInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DevicesManagementBinderInterface.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof DevicesManagementBinderInterface)) {
                return (DevicesManagementBinderInterface) queryLocalInterface;
            }
            return new d(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(DevicesManagementBinderInterface.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(DevicesManagementBinderInterface.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    int initUniteService = initUniteService(CompatibleFitterCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(initUniteService);
                    return true;
                case 2:
                    int scanDevice = scanDevice(parcel.readInt(), parcel.createTypedArrayList(ScanFilterParcel.CREATOR), ScanCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(scanDevice);
                    return true;
                case 3:
                    stopScanDevice();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    int pairDevice = pairDevice((UniteDevice) b.sh_(parcel, UniteDevice.CREATOR), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(pairDevice);
                    return true;
                case 5:
                    int connectDevice = connectDevice((UniteDevice) b.sh_(parcel, UniteDevice.CREATOR), parcel.readInt() != 0, parcel.readInt(), DeviceHandshakeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(connectDevice);
                    return true;
                case 6:
                    int sendCommand = sendCommand((UniteDevice) b.sh_(parcel, UniteDevice.CREATOR), (CommandMessageParcel) b.sh_(parcel, CommandMessageParcel.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(sendCommand);
                    return true;
                case 7:
                    List<UniteDevice> deviceList = getDeviceList();
                    parcel2.writeNoException();
                    b.si_(parcel2, deviceList, 1);
                    return true;
                case 8:
                    int unpairDevice = unpairDevice((UniteDevice) b.sh_(parcel, UniteDevice.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(unpairDevice);
                    return true;
                case 9:
                    int disconnectDevice = disconnectDevice((UniteDevice) b.sh_(parcel, UniteDevice.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(disconnectDevice);
                    return true;
                case 10:
                    boolean isSupportService = isSupportService((UniteDevice) b.sh_(parcel, UniteDevice.CREATOR), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(isSupportService ? 1 : 0);
                    return true;
                case 11:
                    boolean isSupportCharacteristic = isSupportCharacteristic((UniteDevice) b.sh_(parcel, UniteDevice.CREATOR), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(isSupportCharacteristic ? 1 : 0);
                    return true;
                case 12:
                    int registerDeviceStatusListener = registerDeviceStatusListener(StatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(registerDeviceStatusListener);
                    return true;
                case 13:
                    int unregisterDeviceStatusListener = unregisterDeviceStatusListener(StatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(unregisterDeviceStatusListener);
                    return true;
                case 14:
                    int registerDeviceFrameListener = registerDeviceFrameListener(FrameReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(registerDeviceFrameListener);
                    return true;
                case 15:
                    int unregisterDeviceFrameListener = unregisterDeviceFrameListener(FrameReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(unregisterDeviceFrameListener);
                    return true;
                case 16:
                    int characteristicNotify = setCharacteristicNotify((UniteDevice) b.sh_(parcel, UniteDevice.CREATOR), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(characteristicNotify);
                    return true;
                case 17:
                    ping((UniteDevice) b.sh_(parcel, UniteDevice.CREATOR), parcel.readString(), parcel.readString(), P2pPingCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    int send = send((UniteDevice) b.sh_(parcel, UniteDevice.CREATOR), (MessageParcel) b.sh_(parcel, MessageParcel.CREATOR), (IdentityInfo) b.sh_(parcel, IdentityInfo.CREATOR), (IdentityInfo) b.sh_(parcel, IdentityInfo.CREATOR), P2pSendCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(send);
                    return true;
                case 19:
                    subscribeDeviceDataReceiver((IdentityInfo) b.sh_(parcel, IdentityInfo.CREATOR), (IdentityInfo) b.sh_(parcel, IdentityInfo.CREATOR), ReceiverCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), P2pSendCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    unsubscribeDeviceDataReceiver(ReceiverCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    startRequestFile((RequestFileInfo) b.sh_(parcel, RequestFileInfo.CREATOR), ITransferFileCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    stopRequestFile((RequestFileInfo) b.sh_(parcel, RequestFileInfo.CREATOR), ITransferFileCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    int startTransferFile = startTransferFile((FileInfo) b.sh_(parcel, FileInfo.CREATOR), IResultAIDLCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(startTransferFile);
                    return true;
                case 24:
                    stopTransferByQueue((CommonFileInfoParcel) b.sh_(parcel, CommonFileInfoParcel.CREATOR), ITransferFileCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 25:
                    registerListener(parcel.readString(), parcel.readString(), FrameReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    unregisterListener(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    registerHandshakeCallback(DeviceHandshakeCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 28:
                    updateDeviceAfterSimulatConnected(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    List<DeviceInfo> deviceMgrList = getDeviceMgrList(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    b.si_(parcel2, deviceMgrList, 1);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        static class d implements DevicesManagementBinderInterface {
            private IBinder c;

            d(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public int initUniteService(CompatibleFitterCallback compatibleFitterCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    obtain.writeStrongInterface(compatibleFitterCallback);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public int scanDevice(int i, List<ScanFilterParcel> list, ScanCallback scanCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    obtain.writeInt(i);
                    b.si_(obtain, list, 0);
                    obtain.writeStrongInterface(scanCallback);
                    this.c.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public void stopScanDevice() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    this.c.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public int pairDevice(UniteDevice uniteDevice, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    b.sj_(obtain, uniteDevice, 0);
                    obtain.writeInt(i);
                    this.c.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public int connectDevice(UniteDevice uniteDevice, boolean z, int i, DeviceHandshakeCallback deviceHandshakeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    b.sj_(obtain, uniteDevice, 0);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(deviceHandshakeCallback);
                    this.c.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public int sendCommand(UniteDevice uniteDevice, CommandMessageParcel commandMessageParcel) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    b.sj_(obtain, uniteDevice, 0);
                    b.sj_(obtain, commandMessageParcel, 0);
                    this.c.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public List<UniteDevice> getDeviceList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    this.c.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(UniteDevice.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public int unpairDevice(UniteDevice uniteDevice) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    b.sj_(obtain, uniteDevice, 0);
                    this.c.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public int disconnectDevice(UniteDevice uniteDevice) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    b.sj_(obtain, uniteDevice, 0);
                    this.c.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public boolean isSupportService(UniteDevice uniteDevice, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    b.sj_(obtain, uniteDevice, 0);
                    obtain.writeString(str);
                    this.c.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public boolean isSupportCharacteristic(UniteDevice uniteDevice, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    b.sj_(obtain, uniteDevice, 0);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.c.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public int registerDeviceStatusListener(StatusCallback statusCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    obtain.writeStrongInterface(statusCallback);
                    this.c.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public int unregisterDeviceStatusListener(StatusCallback statusCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    obtain.writeStrongInterface(statusCallback);
                    this.c.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public int registerDeviceFrameListener(FrameReceiver frameReceiver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    obtain.writeStrongInterface(frameReceiver);
                    this.c.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public int unregisterDeviceFrameListener(FrameReceiver frameReceiver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    obtain.writeStrongInterface(frameReceiver);
                    this.c.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public int setCharacteristicNotify(UniteDevice uniteDevice, String str, String str2, int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    b.sj_(obtain, uniteDevice, 0);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    this.c.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public void ping(UniteDevice uniteDevice, String str, String str2, P2pPingCallback p2pPingCallback, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    b.sj_(obtain, uniteDevice, 0);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(p2pPingCallback);
                    obtain.writeInt(i);
                    this.c.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public int send(UniteDevice uniteDevice, MessageParcel messageParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    b.sj_(obtain, uniteDevice, 0);
                    b.sj_(obtain, messageParcel, 0);
                    b.sj_(obtain, identityInfo, 0);
                    b.sj_(obtain, identityInfo2, 0);
                    obtain.writeStrongInterface(p2pSendCallback);
                    this.c.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public void subscribeDeviceDataReceiver(IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiverCallback receiverCallback, String str, P2pSendCallback p2pSendCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    b.sj_(obtain, identityInfo, 0);
                    b.sj_(obtain, identityInfo2, 0);
                    obtain.writeStrongInterface(receiverCallback);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(p2pSendCallback);
                    this.c.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public void unsubscribeDeviceDataReceiver(ReceiverCallback receiverCallback, String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    obtain.writeStrongInterface(receiverCallback);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.c.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public void startRequestFile(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    b.sj_(obtain, requestFileInfo, 0);
                    obtain.writeStrongInterface(iTransferFileCallback);
                    this.c.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public void stopRequestFile(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    b.sj_(obtain, requestFileInfo, 0);
                    obtain.writeStrongInterface(iTransferFileCallback);
                    this.c.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public int startTransferFile(FileInfo fileInfo, IResultAIDLCallback iResultAIDLCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    b.sj_(obtain, fileInfo, 0);
                    obtain.writeStrongInterface(iResultAIDLCallback);
                    this.c.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public void stopTransferByQueue(CommonFileInfoParcel commonFileInfoParcel, ITransferFileCallback iTransferFileCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    b.sj_(obtain, commonFileInfoParcel, 0);
                    obtain.writeStrongInterface(iTransferFileCallback);
                    this.c.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public void registerListener(String str, String str2, FrameReceiver frameReceiver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongInterface(frameReceiver);
                    this.c.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public void unregisterListener(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.c.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public void registerHandshakeCallback(DeviceHandshakeCallback deviceHandshakeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    obtain.writeStrongInterface(deviceHandshakeCallback);
                    this.c.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public void updateDeviceAfterSimulatConnected(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    obtain.writeString(str);
                    this.c.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
            public List<DeviceInfo> getDeviceMgrList(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(DevicesManagementBinderInterface.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.c.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DeviceInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class b {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T sh_(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void sj_(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void si_(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                sj_(parcel, list.get(i2), i);
            }
        }
    }
}
