package com.huawei.devicesdk.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.devicesdk.callback.CompatibleFitterCallback;
import com.huawei.devicesdk.callback.ConnectFilter;
import com.huawei.devicesdk.callback.DeviceCompatibleCallback;
import com.huawei.devicesdk.callback.DeviceHandshakeCallback;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.callback.FrameReceiver;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.callback.ScanCallback;
import com.huawei.devicesdk.callback.StatusCallback;
import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.CommandMessageParcel;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.ScanFilterParcel;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.devicesdk.service.DevicesManagementBinderInterface;
import com.huawei.devicesdk.service.DevicesManagementService;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.unitedevice.callback.IResultAIDLCallback;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.entity.CommonFileInfoParcel;
import com.huawei.unitedevice.hwcommonfilemgr.entity.FileInfo;
import com.huawei.unitedevice.hwcommonfilemgr.entity.RequestFileInfo;
import com.huawei.unitedevice.hwwifip2ptransfermgr.HwWifiP2pTransferManager;
import com.huawei.unitedevice.p2p.IdentityInfo;
import com.huawei.unitedevice.p2p.MessageParcel;
import com.huawei.unitedevice.p2p.P2pPingCallback;
import com.huawei.unitedevice.p2p.P2pReceiver;
import com.huawei.unitedevice.p2p.P2pSendCallback;
import com.huawei.unitedevice.p2p.ReceiverCallback;
import defpackage.bgl;
import defpackage.bir;
import defpackage.biu;
import defpackage.bjf;
import defpackage.bju;
import defpackage.bjx;
import defpackage.bka;
import defpackage.bkt;
import defpackage.bml;
import defpackage.bmw;
import defpackage.iyv;
import defpackage.snu;
import defpackage.snw;
import defpackage.snz;
import defpackage.sph;
import defpackage.spk;
import defpackage.spn;
import defpackage.spr;
import defpackage.sps;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes8.dex */
public class DevicesManagementService extends Service {
    private final Object e = new Object();
    private snw c = snw.d();

    /* renamed from: a, reason: collision with root package name */
    private IBinder.DeathRecipient f1946a = new IBinder.DeathRecipient() { // from class: com.huawei.devicesdk.service.DevicesManagementService.2
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            LogUtil.c("DevicesManagementService", "mMessageReceiveDeathRecipient binderDied");
            Iterator<FrameReceiver> it = bka.c().b().iterator();
            while (it.hasNext()) {
                FrameReceiver next = it.next();
                if (!next.asBinder().pingBinder()) {
                    LogUtil.c("DevicesManagementService", "messageReceiveBinderDied");
                    next.asBinder().unlinkToDeath(this, 0);
                    bka.c().b(next);
                }
            }
        }
    };
    private IBinder.DeathRecipient d = new IBinder.DeathRecipient() { // from class: com.huawei.devicesdk.service.DevicesManagementService.1
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            LogUtil.c("DevicesManagementService", "mDeviceStatusDeathRecipient binderDied");
            Iterator<StatusCallback> it = bka.c().a().iterator();
            while (it.hasNext()) {
                StatusCallback next = it.next();
                if (!next.asBinder().pingBinder()) {
                    LogUtil.c("DevicesManagementService", "deviceStatusBinderDied");
                    next.asBinder().unlinkToDeath(this, 0);
                    bka.c().d(next);
                }
            }
        }
    };
    private final DevicesManagementBinderInterface.Stub b = new AnonymousClass5();

    /* renamed from: com.huawei.devicesdk.service.DevicesManagementService$5, reason: invalid class name */
    public class AnonymousClass5 extends DevicesManagementBinderInterface.Stub {
        AnonymousClass5() {
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public int initUniteService(final CompatibleFitterCallback compatibleFitterCallback) {
            synchronized (DevicesManagementService.this.e) {
                ReleaseLogUtil.b("DEVMGR_DevicesManagementService", "enter initUniteService");
                bmw.e(100082, "", "", "");
                bgl.c().initUniteService(new DeviceCompatibleCallback() { // from class: bkh
                    @Override // com.huawei.devicesdk.callback.DeviceCompatibleCallback
                    public final void adapterOperate(List list) {
                        DevicesManagementService.AnonymousClass5.c(CompatibleFitterCallback.this, list);
                    }
                });
            }
            return 0;
        }

        public static /* synthetic */ void c(CompatibleFitterCallback compatibleFitterCallback, List list) {
            if (compatibleFitterCallback != null) {
                try {
                    compatibleFitterCallback.adapterOperate(list);
                } catch (RemoteException unused) {
                    ReleaseLogUtil.c("DEVMGR_DevicesManagementService", "adapterOperate RemoteException");
                }
            }
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public int scanDevice(int i, List<ScanFilterParcel> list, final ScanCallback scanCallback) {
            if (list == null || list.size() == 0 || scanCallback == null) {
                LogUtil.e("DevicesManagementService", "type filters or callback is null");
                return 1;
            }
            ScanMode value = ScanMode.getValue(i);
            if (value == null) {
                return 1;
            }
            ArrayList arrayList = new ArrayList(16);
            for (ScanFilterParcel scanFilterParcel : list) {
                bjf.a aVar = new bjf.a();
                aVar.e(scanFilterParcel.getType());
                aVar.a(scanFilterParcel.getMatcher());
                arrayList.add(aVar.a());
            }
            bgl.c().scanDevice(value, arrayList, new DeviceScanCallback() { // from class: com.huawei.devicesdk.service.DevicesManagementService.5.4
                @Override // com.huawei.devicesdk.callback.DeviceScanCallback
                public void scanResult(UniteDevice uniteDevice, byte[] bArr, String str, int i2) {
                    try {
                        scanCallback.onScanResult(i2, uniteDevice, bArr, str);
                    } catch (RemoteException unused) {
                        LogUtil.e("DevicesManagementService", "remoteException");
                    }
                }
            });
            LogUtil.c("DevicesManagementService", "enter DevicesManagement ScanDevice success");
            return 0;
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public void stopScanDevice() {
            bgl.c().stopScanDevice();
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public int pairDevice(UniteDevice uniteDevice, int i) {
            if (uniteDevice == null) {
                LogUtil.e("DevicesManagementService", "Device Error");
                return 1;
            }
            if (i != 2) {
                LogUtil.e("DevicesManagementService", "type is not correct");
                return 1;
            }
            bgl.c().createSystemBond(uniteDevice.getDeviceInfo(), ConnectMode.getValue(i));
            LogUtil.c("DevicesManagementService", "enter DevicesManagement createSystemBond success");
            return 0;
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public int connectDevice(UniteDevice uniteDevice, boolean z, int i, final DeviceHandshakeCallback deviceHandshakeCallback) {
            synchronized (DevicesManagementService.this.e) {
                ConnectMode value = ConnectMode.getValue(i);
                if (uniteDevice != null && value != null) {
                    if (deviceHandshakeCallback != null) {
                        bgl.c().registerHandshakeFilter(new ConnectFilter() { // from class: com.huawei.devicesdk.service.DevicesManagementService.5.3
                            @Override // com.huawei.devicesdk.callback.ConnectFilter
                            public int onFilter(UniteDevice uniteDevice2, String str, bir birVar) {
                                try {
                                    CommandMessageParcel d = AnonymousClass5.this.d(birVar);
                                    int onProcess = deviceHandshakeCallback.onProcess(uniteDevice2, str, d);
                                    AnonymousClass5.this.a(birVar, d);
                                    return onProcess;
                                } catch (RemoteException unused) {
                                    LogUtil.e("DevicesManagementService", "onFilter remoteException");
                                    return -1;
                                }
                            }

                            @Override // com.huawei.devicesdk.callback.ConnectFilter
                            public String preProcess(UniteDevice uniteDevice2, String str) {
                                try {
                                    return deviceHandshakeCallback.preProcess(uniteDevice2, str);
                                } catch (RemoteException unused) {
                                    LogUtil.e("DevicesManagementService", "preProcess RemoteException");
                                    return "";
                                }
                            }
                        });
                    }
                    bgl.c().connectDevice(uniteDevice.getDeviceInfo(), z, value);
                    LogUtil.c("DevicesManagementService", "enter DevicesManagement connectDevice success");
                    return 0;
                }
                LogUtil.e("DevicesManagementService", "device or connect is null");
                return 1;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CommandMessageParcel d(bir birVar) {
            CommandMessageParcel commandMessageParcel = new CommandMessageParcel();
            if (birVar == null) {
                return commandMessageParcel;
            }
            commandMessageParcel.setCharacteristicUuid(birVar.b());
            if (birVar.g() != null) {
                commandMessageParcel.setCommandType(birVar.g().value());
            }
            commandMessageParcel.setCommand(birVar.e());
            commandMessageParcel.setServiceUuid(birVar.j());
            return commandMessageParcel;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(bir birVar, CommandMessageParcel commandMessageParcel) {
            if (birVar == null || commandMessageParcel == null) {
                return;
            }
            birVar.b(commandMessageParcel.getCharacteristicUuid());
            birVar.c(commandMessageParcel.getServiceUuid());
            birVar.b(SendMode.getValue(commandMessageParcel.getCommandType()));
            birVar.e(commandMessageParcel.getCommand());
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public int sendCommand(UniteDevice uniteDevice, CommandMessageParcel commandMessageParcel) throws RemoteException {
            if (uniteDevice == null || commandMessageParcel == null) {
                LogUtil.e("DevicesManagementService", "device or command is null");
                return 1;
            }
            SendMode value = SendMode.getValue(commandMessageParcel.getCommandType());
            if (value == null) {
                LogUtil.e("DevicesManagementService", "send mode is error");
                return 1;
            }
            bir.a aVar = new bir.a();
            aVar.c(commandMessageParcel.isNeedEncrypt());
            aVar.d(commandMessageParcel.getPriorityType());
            CharacterOperationType value2 = CharacterOperationType.getValue(commandMessageParcel.getOptionsType());
            if (value2 != null) {
                aVar.d(value2);
            }
            bir birVar = new bir();
            birVar.b(commandMessageParcel.getCharacteristicUuid());
            birVar.c(commandMessageParcel.getServiceUuid());
            birVar.e(commandMessageParcel.getCommand());
            birVar.b(value);
            birVar.c(commandMessageParcel.getSocketChannel());
            bgl.c().sendCommand(uniteDevice.getDeviceInfo(), aVar.b(birVar));
            LogUtil.c("DevicesManagementService", "enter DevicesManagement sendCommand success");
            return 0;
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public List<UniteDevice> getDeviceList() {
            return new ArrayList(bgl.c().getDeviceList().values());
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public int unpairDevice(UniteDevice uniteDevice) {
            if (uniteDevice == null) {
                LogUtil.e("DevicesManagementService", "Device Error");
                return 1;
            }
            bgl.c().unPairDevice(uniteDevice.getDeviceInfo());
            LogUtil.c("DevicesManagementService", "enter DevicesManagement unpairDevice success");
            return 0;
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public int disconnectDevice(UniteDevice uniteDevice) {
            if (uniteDevice == null) {
                LogUtil.e("DevicesManagementService", "Device Error");
                return 1;
            }
            bgl.c().disconnect(uniteDevice.getDeviceInfo());
            LogUtil.c("DevicesManagementService", "enter DevicesManagement disconnectDevice success");
            return 0;
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public boolean isSupportService(UniteDevice uniteDevice, String str) {
            if (uniteDevice == null || str == null) {
                LogUtil.e("DevicesManagementService", "device or serviceId is null");
                return false;
            }
            LogUtil.c("DevicesManagementService", "enter DevicesManagement isSupportService success");
            return bgl.c().isSupportService(uniteDevice.getDeviceInfo(), str);
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public boolean isSupportCharacteristic(UniteDevice uniteDevice, String str, String str2) {
            if (uniteDevice == null || str == null || str2 == null) {
                LogUtil.e("DevicesManagementService", "device serviceid or characteristicId is null");
                return false;
            }
            LogUtil.c("DevicesManagementService", "enter DevicesManagement isSupportCharacteristic success");
            return bgl.c().isSupportCharacter(uniteDevice.getDeviceInfo(), str, str2);
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public int registerDeviceStatusListener(StatusCallback statusCallback) {
            if (statusCallback == null) {
                return 1;
            }
            bka.c().c(statusCallback);
            LogUtil.c("DevicesManagementService", "registerDeviceStatusListener success pid:", Integer.valueOf(getCallingPid()));
            try {
                statusCallback.asBinder().linkToDeath(DevicesManagementService.this.d, 0);
            } catch (RemoteException unused) {
                LogUtil.e("DevicesManagementService", "registerDeviceStatusListener remoteException");
            }
            return 0;
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public int unregisterDeviceStatusListener(StatusCallback statusCallback) {
            bka.c().d(statusCallback);
            LogUtil.c("DevicesManagementService", "unregisterDeviceStatusListener success pid:", Integer.valueOf(getCallingPid()));
            if (statusCallback != null) {
                statusCallback.asBinder().unlinkToDeath(DevicesManagementService.this.d, 0);
            }
            return 0;
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public int registerDeviceFrameListener(FrameReceiver frameReceiver) {
            if (frameReceiver == null) {
                return 1;
            }
            bka.c().c(frameReceiver);
            LogUtil.c("DevicesManagementService", "registerDeviceFrameListener success pid:", Integer.valueOf(getCallingPid()));
            try {
                frameReceiver.asBinder().linkToDeath(DevicesManagementService.this.f1946a, 0);
            } catch (RemoteException unused) {
                LogUtil.e("DevicesManagementService", "registerDeviceFrameListener remoteException");
            }
            return 0;
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public int unregisterDeviceFrameListener(FrameReceiver frameReceiver) {
            bka.c().b(frameReceiver);
            LogUtil.c("DevicesManagementService", "unregisterDeviceFrameListener success pid:", Integer.valueOf(getCallingPid()));
            if (frameReceiver != null) {
                frameReceiver.asBinder().unlinkToDeath(DevicesManagementService.this.f1946a, 0);
            }
            return 0;
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public int setCharacteristicNotify(UniteDevice uniteDevice, String str, String str2, int i, boolean z) {
            CharacterOperationType characterOperationType;
            if (uniteDevice == null) {
                return 1;
            }
            bir.a aVar = new bir.a();
            if (z) {
                characterOperationType = CharacterOperationType.ENABLE;
            } else {
                characterOperationType = CharacterOperationType.DISABLE;
            }
            aVar.d(characterOperationType);
            bir birVar = new bir();
            birVar.c(str);
            birVar.b(str2);
            birVar.b(SendMode.getValue(i));
            bgl.c().sendCommand(uniteDevice.getDeviceInfo(), aVar.b(birVar));
            LogUtil.c("DevicesManagementService", "enter sendCommand enabled success");
            return 0;
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public void ping(UniteDevice uniteDevice, String str, String str2, final P2pPingCallback p2pPingCallback, int i) throws RemoteException {
            LogUtil.c("DevicesManagementService", "enter ping");
            if (str == null) {
                LogUtil.e("DevicesManagementService", "srcPkgName is null");
                return;
            }
            if (str2 == null) {
                LogUtil.e("DevicesManagementService", "destPkgName is null");
            } else if (p2pPingCallback == null) {
                LogUtil.e("DevicesManagementService", "pingCallback is null");
            } else {
                spk.b().ping(str, str2, new PingCallback() { // from class: com.huawei.devicesdk.service.DevicesManagementService.5.1
                    @Override // com.huawei.health.deviceconnect.callback.PingCallback
                    public void onPingResult(int i2) {
                        P2pPingCallback p2pPingCallback2 = p2pPingCallback;
                        if (p2pPingCallback2 != null) {
                            try {
                                p2pPingCallback2.onResult(i2);
                            } catch (RemoteException unused) {
                                LogUtil.e("DevicesManagementService", "ping remoteException");
                            }
                        }
                    }
                }, uniteDevice, i);
            }
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public int send(UniteDevice uniteDevice, MessageParcel messageParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException {
            LogUtil.c("DevicesManagementService", "enter send");
            if (identityInfo == null) {
                LogUtil.e("DevicesManagementService", "srcInfo is null");
                iyv.c("P2PMessage", "P2PMessage send srcInfo is null");
                return 1;
            }
            if (identityInfo2 == null) {
                LogUtil.e("DevicesManagementService", "destInfo is null");
                iyv.c("P2PMessage", "P2PMessage send destInfo is null");
                return 1;
            }
            if (messageParcel != null) {
                spk.b().p2pSend(uniteDevice, identityInfo, identityInfo2, messageParcel, DevicesManagementService.this.e(p2pSendCallback));
                return 0;
            }
            LogUtil.e("DevicesManagementService", "messageParcel is null");
            iyv.c("P2PMessage", "P2PMessage send messageParcel is null");
            return 1;
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public int startTransferFile(FileInfo fileInfo, IResultAIDLCallback iResultAIDLCallback) {
            LogUtil.c("DevicesManagementService", "transferCommonFile send");
            if (fileInfo == null || iResultAIDLCallback == null) {
                LogUtil.e("DevicesManagementService", "filePath is null");
                return 1;
            }
            spk.b().startTransferFile(fileInfo, iResultAIDLCallback);
            return 0;
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public void stopTransferByQueue(CommonFileInfoParcel commonFileInfoParcel, ITransferFileCallback iTransferFileCallback) throws RemoteException {
            if (commonFileInfoParcel == null || iTransferFileCallback == null) {
                LogUtil.e("DevicesManagementService", "fileInfo or callback is null");
            } else {
                LogUtil.c("DevicesManagementService", "stopTransferByQueue");
                spk.b().stopTransferByQueue(commonFileInfoParcel, iTransferFileCallback);
            }
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public void subscribeDeviceDataReceiver(IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiverCallback receiverCallback, String str, P2pSendCallback p2pSendCallback) throws RemoteException {
            if (identityInfo == null) {
                LogUtil.e("DevicesManagementService", "srcInfo is null");
                return;
            }
            if (identityInfo2 == null) {
                LogUtil.e("DevicesManagementService", "destInfo is null");
                return;
            }
            if (receiverCallback == null) {
                LogUtil.e("DevicesManagementService", "receiverCallback is null");
                return;
            }
            if (p2pSendCallback == null) {
                LogUtil.e("DevicesManagementService", "p2pSendCallback is null");
                return;
            }
            spr c = DevicesManagementService.this.c(receiverCallback, str, identityInfo.getPackageName(), identityInfo2.getPackageName());
            LogUtil.c("DevicesManagementService", "registerReceiver processName is: ", str, "srcInfo name: ", identityInfo.getPackageName(), "destInfo name: ", identityInfo2.getPackageName());
            spk.b().registerReceiver(identityInfo, identityInfo2, c, DevicesManagementService.this.e(p2pSendCallback));
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public void unsubscribeDeviceDataReceiver(ReceiverCallback receiverCallback, String str, String str2, String str3) throws RemoteException {
            if (receiverCallback == null) {
                return;
            }
            spk.b().unregisterReceiver(DevicesManagementService.this.c(receiverCallback, str, str2, str3));
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public void startRequestFile(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) throws RemoteException {
            LogUtil.c("DevicesManagementService", "enter startRequestFile");
            if (requestFileInfo == null) {
                LogUtil.e("DevicesManagementService", "RequestFileInfo is null");
            } else if (iTransferFileCallback != null) {
                DevicesManagementService.this.c.a(requestFileInfo, iTransferFileCallback);
            } else {
                LogUtil.e("DevicesManagementService", "callback is null");
            }
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public void stopRequestFile(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) throws RemoteException {
            LogUtil.c("DevicesManagementService", "enter stopRequestFile");
            if (requestFileInfo == null) {
                LogUtil.e("DevicesManagementService", "fileInfo is null");
            } else if (iTransferFileCallback != null) {
                DevicesManagementService.this.c.e(requestFileInfo, iTransferFileCallback);
            } else {
                LogUtil.e("DevicesManagementService", "IBaseCallback is null");
            }
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public void registerListener(String str, String str2, FrameReceiver frameReceiver) {
            if ("sampleCommandCallback".equals(str2)) {
                sps.a().c(str, frameReceiver);
            }
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public void unregisterListener(String str, String str2) {
            if ("sampleCommandCallback".equals(str2)) {
                sps.a().a(str);
            }
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public void registerHandshakeCallback(final DeviceHandshakeCallback deviceHandshakeCallback) {
            if (deviceHandshakeCallback == null) {
                LogUtil.a("DevicesManagementService", "registerHandshakeCallback callback is null");
            }
            bgl.c().registerHandshakeFilter(new ConnectFilter() { // from class: com.huawei.devicesdk.service.DevicesManagementService.5.2
                @Override // com.huawei.devicesdk.callback.ConnectFilter
                public int onFilter(UniteDevice uniteDevice, String str, bir birVar) {
                    try {
                        CommandMessageParcel d = AnonymousClass5.this.d(birVar);
                        int onProcess = deviceHandshakeCallback.onProcess(uniteDevice, str, d);
                        AnonymousClass5.this.a(birVar, d);
                        return onProcess;
                    } catch (RemoteException unused) {
                        LogUtil.e("DevicesManagementService", "onProcess remoteException");
                        return -1;
                    }
                }

                @Override // com.huawei.devicesdk.callback.ConnectFilter
                public String preProcess(UniteDevice uniteDevice, String str) {
                    try {
                        return deviceHandshakeCallback.preProcess(uniteDevice, str);
                    } catch (RemoteException unused) {
                        LogUtil.e("DevicesManagementService", "preProcess RemoteException");
                        return "";
                    }
                }
            });
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public void updateDeviceAfterSimulatConnected(String str) {
            bjx.a().k(str);
        }

        @Override // com.huawei.devicesdk.service.DevicesManagementBinderInterface
        public List<DeviceInfo> getDeviceMgrList(int i, String str) {
            return snu.e().getDeviceMgrList(i, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public spr c(final ReceiverCallback receiverCallback, String str, String str2, String str3) {
        return new spr(str, str2, str3, new P2pReceiver() { // from class: com.huawei.devicesdk.service.DevicesManagementService.3
            @Override // com.huawei.unitedevice.p2p.P2pReceiver
            public void onReceiveMessage(com.huawei.devicesdk.entity.DeviceInfo deviceInfo, spn spnVar) {
                ReceiverCallback receiverCallback2 = receiverCallback;
                if (receiverCallback2 == null || spnVar == null) {
                    return;
                }
                try {
                    receiverCallback2.onReceiveMessage(deviceInfo, sph.e(spnVar));
                } catch (RemoteException unused) {
                    LogUtil.e("DevicesManagementService", "onReceiveMessage remoteException");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SendCallback e(final P2pSendCallback p2pSendCallback) {
        if (p2pSendCallback != null) {
            return new SendCallback() { // from class: com.huawei.devicesdk.service.DevicesManagementService.4
                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendResult(int i) {
                    P2pSendCallback p2pSendCallback2 = p2pSendCallback;
                    if (p2pSendCallback2 != null) {
                        try {
                            p2pSendCallback2.onSendResult(i);
                        } catch (RemoteException unused) {
                            LogUtil.e("DevicesManagementService", "onSendResult send remoteException");
                        }
                    }
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onSendProgress(long j) {
                    P2pSendCallback p2pSendCallback2 = p2pSendCallback;
                    if (p2pSendCallback2 != null) {
                        try {
                            p2pSendCallback2.onSendProgress(j);
                        } catch (RemoteException unused) {
                            LogUtil.e("DevicesManagementService", "onSendProgress send remoteException");
                        }
                    }
                }

                @Override // com.huawei.health.deviceconnect.callback.SendCallback
                public void onFileTransferReport(String str) {
                    if (p2pSendCallback != null) {
                        try {
                            LogUtil.c("DevicesManagementService", "getSendCallback onFileTransferReport transferWay:", str);
                            p2pSendCallback.onFileTransferReport(str);
                        } catch (RemoteException unused) {
                            LogUtil.e("DevicesManagementService", "onFileTransferReport send remoteException");
                        }
                    }
                }
            };
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        d();
        bgl.c().registerDeviceStateListener(new DeviceStatusChangeCallback() { // from class: com.huawei.devicesdk.service.DevicesManagementService.7
            @Override // com.huawei.devicesdk.callback.DeviceStatusChangeCallback
            public void onConnectStatusChanged(com.huawei.devicesdk.entity.DeviceInfo deviceInfo, int i, int i2) {
                DevicesManagementService.this.c(deviceInfo, i, i2);
            }
        });
        bgl.c().registerDeviceMessageListener(new MessageReceiveCallback() { // from class: com.huawei.devicesdk.service.DevicesManagementService.8
            @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
            public void onDataReceived(com.huawei.devicesdk.entity.DeviceInfo deviceInfo, biu biuVar, int i) {
                if (deviceInfo == null) {
                    LogUtil.e("DevicesManagementService", "deviceInfo is null");
                } else {
                    bju.a().a(deviceInfo, biuVar, i);
                }
            }

            @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
            public void onChannelEnable(com.huawei.devicesdk.entity.DeviceInfo deviceInfo, String str, int i) {
                if (deviceInfo == null) {
                    LogUtil.e("DevicesManagementService", "DeviceInfo is null");
                    return;
                }
                UniteDevice uniteDevice = new UniteDevice();
                uniteDevice.setIdentify(deviceInfo.getDeviceMac());
                uniteDevice.setDeviceInfo(deviceInfo);
                Iterator<FrameReceiver> it = bka.c().b().iterator();
                while (it.hasNext()) {
                    FrameReceiver next = it.next();
                    if (next != null) {
                        try {
                            next.onChannelResult(i, uniteDevice, str);
                        } catch (RemoteException unused) {
                            LogUtil.e("DevicesManagementService", "onChannelEnable RemoteException");
                        }
                    }
                }
            }
        });
        b();
    }

    private void d() {
        ThreadPoolManager.d().d("initDms", new Runnable() { // from class: bkf
            @Override // java.lang.Runnable
            public final void run() {
                DevicesManagementService.c();
            }
        });
    }

    public static /* synthetic */ void c() {
        LogUtil.c("DevicesManagementService", "start initDms");
        if (bml.b(BaseApplication.e(), "com.huawei.hwservicesmgr.PhoneService")) {
            LogUtil.c("DevicesManagementService", "startPhoneService is running");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.huawei.bone.action.StartPhoneService");
        intent.setPackage(BaseApplication.e().getPackageName());
        try {
            LogUtil.c("DevicesManagementService", "ready initDms");
            BaseApplication.e().startService(intent);
        } catch (IllegalStateException | SecurityException e) {
            LogUtil.e("DevicesManagementService", "startPhoneService exception:", ExceptionUtils.d(e));
        }
    }

    private void b() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: bke
            @Override // java.lang.Runnable
            public final void run() {
                DevicesManagementService.e();
            }
        });
    }

    public static /* synthetic */ void e() {
        LogUtil.c("DevicesManagementService", "start initHfpAndHid");
        bkt.e();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        LogUtil.c("DevicesManagementService", "onStartCommand() intent = ", intent, ",flags = ", Integer.valueOf(i), ",startId = ", Integer.valueOf(i2));
        if (!CommonUtil.as()) {
            return 2;
        }
        LogUtil.c("DevicesManagementService", "stopDevicesManagementService startId:", Integer.valueOf(i2));
        stopSelf(i2);
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(com.huawei.devicesdk.entity.DeviceInfo deviceInfo, int i, int i2) {
        if (deviceInfo == null) {
            LogUtil.e("DevicesManagementService", "DeviceInfo is null");
            return;
        }
        UniteDevice uniteDevice = new UniteDevice();
        uniteDevice.setIdentify(deviceInfo.getDeviceMac());
        uniteDevice.setDeviceInfo(deviceInfo);
        CopyOnWriteArraySet<StatusCallback> a2 = bka.c().a();
        if (a2 == null || a2.isEmpty()) {
            LogUtil.a("DevicesManagementService", "onConnectStatusChanged: callbacks is empty.");
            return;
        }
        Iterator<StatusCallback> it = a2.iterator();
        while (it.hasNext()) {
            StatusCallback next = it.next();
            if (next != null) {
                try {
                    next.onStatusChanged(i2, uniteDevice, i);
                } catch (RemoteException unused) {
                    LogUtil.e("DevicesManagementService", "onStatusChanged RemoteException");
                }
            } else {
                LogUtil.e("DevicesManagementService", "onConnectStatusChanged: callback is null.");
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.c("DevicesManagementService", "enter onBind success");
        return this.b;
    }

    @Override // android.app.Service
    public void onDestroy() {
        LogUtil.c("DevicesManagementService", "onDestroy");
        super.onDestroy();
        bgl.c().unregisterDeviceStateListener();
        bgl.c().unregisterDeviceMessageListener();
        bgl.c().destory();
        this.c.a();
        snz.a().e();
        HwWifiP2pTransferManager.d().t();
    }
}
