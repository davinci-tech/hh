package com.huawei.wearengine.service.api;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hms.network.embedded.r3;
import com.huawei.operation.ble.BleConstants;
import com.huawei.wearengine.P2pManager;
import com.huawei.wearengine.auth.Permission;
import com.huawei.wearengine.common.WearEngineBiOperate;
import com.huawei.wearengine.core.common.ClientBinderDied;
import com.huawei.wearengine.core.device.PowerModeChangeManager;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.p2p.FileIdentificationParcel;
import com.huawei.wearengine.p2p.IdentityInfo;
import com.huawei.wearengine.p2p.MessageParcel;
import com.huawei.wearengine.p2p.MessageParcelExtra;
import com.huawei.wearengine.p2p.P2pCancelFileTransferCallBack;
import com.huawei.wearengine.p2p.P2pPingCallback;
import com.huawei.wearengine.p2p.P2pSendCallback;
import com.huawei.wearengine.p2p.ReceiverCallback;
import com.huawei.wearengine.utils.DeviceProcessor;
import defpackage.toh;
import defpackage.tom;
import defpackage.tor;
import defpackage.tos;
import defpackage.tot;
import defpackage.toz;
import defpackage.tqb;
import defpackage.tqo;
import defpackage.tri;
import defpackage.trj;

/* loaded from: classes9.dex */
public class P2pManagerImpl extends P2pManager.Stub implements ClientBinderDied, PowerModeChangeManager.HandlePowerModeChange {

    /* renamed from: a, reason: collision with root package name */
    private tor f11245a;
    private toz b;
    private IBinder.DeathRecipient c = new IBinder.DeathRecipient() { // from class: com.huawei.wearengine.service.api.P2pManagerImpl.4
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            P2pManagerImpl.this.b.a();
        }
    };
    private toh d;

    public P2pManagerImpl(toh tohVar, tor torVar) {
        toz tozVar = new toz(torVar);
        this.b = tozVar;
        this.d = tohVar;
        this.f11245a = torVar;
        tozVar.h();
    }

    @Override // com.huawei.wearengine.P2pManager
    public int ping(Device device, String str, String str2, P2pPingCallback p2pPingCallback) throws RemoteException {
        tos.a("P2pManagerImpl", "Start ping");
        tom.e(device, "device must not be null!");
        tom.e(str, "srcPkgName must not be null!");
        tom.e(str2, "destPkgName must not be null!");
        tom.e(p2pPingCallback, "pingCallback must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        int callingUid = Binder.getCallingUid();
        String c = tri.c(callingUid, a2, this.f11245a.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            if (!tot.a(callingUid, str)) {
                tos.e("P2pManagerImpl", "Ping Invalid srcPkgName");
                throw new IllegalStateException(String.valueOf(5));
            }
            PowerModeChangeManager.a().b(true);
            this.d.a(c, r3.r, tqo.f17349a, Permission.DEVICE_MANAGER);
            if (TextUtils.isEmpty(str2)) {
                str2 = str;
            }
            this.b.e(DeviceProcessor.processInputDevice(c, device), str, str2, p2pPingCallback);
            wearEngineBiOperate.biApiCalling(a2, c, r3.r, String.valueOf(0));
            return 0;
        } catch (IllegalStateException e) {
            tos.e("P2pManagerImpl", "ping illegalStateException");
            wearEngineBiOperate.biApiCalling(a2, c, r3.r, String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.P2pManager
    public int send(Device device, MessageParcel messageParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException {
        tos.a("P2pManagerImpl", "Start send");
        tom.e(device, "device must not be null!");
        tom.e(messageParcel, "message must not be null!");
        tom.e(identityInfo, "srcInfo must not be null!");
        tom.e(identityInfo2, "destInfo must not be null!");
        tom.e(p2pSendCallback, "sendCallback must not be null!");
        return sendExtra(device, b(messageParcel), identityInfo, identityInfo2, p2pSendCallback);
    }

    @Override // com.huawei.wearengine.P2pManager
    public int registerReceiver(Device device, IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiverCallback receiverCallback, int i) throws RemoteException {
        int callingPid = Binder.getCallingPid();
        tos.a("P2pManagerImpl", "Start registerReceiver pid is:" + callingPid + ", hashcode is:" + i);
        tom.e(device, "device must not be null!");
        tom.e(identityInfo, "srcInfo must not be null!");
        tom.e(identityInfo2, "destInfo must not be null!");
        tom.e(receiverCallback, "receiverCallback must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        int callingUid = Binder.getCallingUid();
        String c = tri.c(callingUid, tot.a(), this.f11245a.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            if (!tot.a(callingUid, identityInfo.getPackageName())) {
                tos.e("registerReceiver", "Invalid srcPkgName");
                throw new IllegalStateException(String.valueOf(5));
            }
            if (TextUtils.isEmpty(identityInfo2.getFingerPrint())) {
                tos.e("P2pManagerImpl", "Invalid destination FingerPrint");
                throw new IllegalStateException(String.valueOf(5));
            }
            PowerModeChangeManager.a().b(true);
            this.d.a(c, "registerReceiver", tqo.f17349a, Permission.DEVICE_MANAGER);
            receiverCallback.asBinder().linkToDeath(this.c, 0);
            int e = this.b.e(DeviceProcessor.processInputDevice(c, device), identityInfo, e(identityInfo, identityInfo2), new tqb(callingPid, i, receiverCallback));
            wearEngineBiOperate.biApiCalling(tot.a(), c, "registerReceiver", String.valueOf(e));
            return e;
        } catch (IllegalStateException e2) {
            tos.e("P2pManagerImpl", "registerReceiver illegalStateException");
            wearEngineBiOperate.biApiCalling(tot.a(), c, "registerReceiver", String.valueOf(trj.b(e2)));
            throw e2;
        }
    }

    @Override // com.huawei.wearengine.P2pManager
    public int unregisterReceiver(ReceiverCallback receiverCallback, int i) throws RemoteException {
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        int callingPid = Binder.getCallingPid();
        tos.a("P2pManagerImpl", "start unregisterReceiver pid is:" + callingPid + ", hashcode is:" + i);
        tom.e(receiverCallback, "receiverCallback must not be null!");
        String c = tri.c(Binder.getCallingUid(), tot.a(), this.f11245a.getApplicationIdByPid(Integer.valueOf(callingPid)));
        tos.a("P2pManagerImpl", "unregisterReceiver srcPackageName is:" + c);
        try {
            PowerModeChangeManager.a().b(true);
            this.d.a(c, "unregisterReceiver", tqo.f17349a, Permission.DEVICE_MANAGER);
            tqb d = this.b.j().d(new tqb(callingPid, i, receiverCallback));
            if (d == null) {
                tos.e("P2pManagerImpl", "unregisterReceiver map do not have the receiver");
                wearEngineBiOperate.biApiCalling(tot.a(), c, "unregisterReceiver", String.valueOf(0));
                return 0;
            }
            d.c().asBinder().unlinkToDeath(this.c, 0);
            int d2 = this.b.d(d);
            wearEngineBiOperate.biApiCalling(tot.a(), c, "unregisterReceiver", String.valueOf(d2));
            return d2;
        } catch (IllegalStateException e) {
            tos.e("P2pManagerImpl", "unregisterReceiver illegalStateException");
            wearEngineBiOperate.biApiCalling(tot.a(), c, "unregisterReceiver", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.P2pManager
    public int sendExtra(Device device, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException {
        tos.a("P2pManagerImpl", "Start sendExtra");
        tom.e(device, "device must not be null!");
        tom.e(messageParcelExtra, "message must not be null!");
        tom.e(identityInfo, "srcInfo must not be null!");
        tom.e(identityInfo2, "destInfo must not be null!");
        tom.e(p2pSendCallback, "sendCallback must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        int callingUid = Binder.getCallingUid();
        String c = tri.c(callingUid, a2, this.f11245a.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            if (!tot.a(callingUid, identityInfo.getPackageName())) {
                tos.e("P2pManagerImpl", "Invalid srcPkgName");
                throw new IllegalStateException(String.valueOf(5));
            }
            if (TextUtils.isEmpty(identityInfo2.getFingerPrint())) {
                tos.e("P2pManagerImpl", "Invalid destination FingerPrint");
                throw new IllegalStateException(String.valueOf(5));
            }
            PowerModeChangeManager.a().b(true);
            this.d.a(c, "send", tqo.f17349a, Permission.DEVICE_MANAGER);
            IdentityInfo e = e(identityInfo, identityInfo2);
            this.b.a(DeviceProcessor.processInputDevice(c, device), messageParcelExtra, identityInfo, e, p2pSendCallback);
            wearEngineBiOperate.biApiCalling(a2, c, "send", String.valueOf(0));
            return 0;
        } catch (IllegalStateException e2) {
            tos.e("P2pManagerImpl", "sendExtra illegalStateException");
            wearEngineBiOperate.biApiCalling(a2, c, "send", String.valueOf(trj.b(e2)));
            throw e2;
        }
    }

    private IdentityInfo e(IdentityInfo identityInfo, IdentityInfo identityInfo2) {
        return TextUtils.isEmpty(identityInfo2.getPackageName()) ? identityInfo : identityInfo2;
    }

    @Override // com.huawei.wearengine.P2pManager
    public int getDeviceAppVersionCode(Device device, String str, String str2) throws RemoteException {
        tos.a("P2pManagerImpl", "Start getDeviceAppVersionCode");
        tom.e(device, "device must not be null!");
        tom.c(str, "srcPkgName must not be null!");
        tom.c(str2, "destPkgName must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        int callingUid = Binder.getCallingUid();
        String c = tri.c(callingUid, a2, this.f11245a.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            if (!tot.a(callingUid, str)) {
                tos.e("P2pManagerImpl", "getDeviceAppVersionCode Invalid srcPkgName");
                throw new IllegalStateException(String.valueOf(5));
            }
            PowerModeChangeManager.a().b(true);
            this.d.a(c, BleConstants.GET_APP_VERSION, tqo.f17349a, Permission.DEVICE_MANAGER);
            int c2 = this.b.c(DeviceProcessor.processInputDevice(c, device), str, str2);
            wearEngineBiOperate.biApiCalling(a2, c, BleConstants.GET_APP_VERSION, String.valueOf(c2));
            return c2;
        } catch (IllegalStateException e) {
            tos.e("P2pManagerImpl", "getDeviceAppVersionCode illegalStateException");
            wearEngineBiOperate.biApiCalling(a2, c, BleConstants.GET_APP_VERSION, String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.P2pManager
    public int sendInternal(Device device, MessageParcelExtra messageParcelExtra, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pSendCallback p2pSendCallback) throws RemoteException {
        tos.a("P2pManagerImpl", "sendInternal enter");
        tom.e(device, "device must not be null!");
        tom.e(messageParcelExtra, "message must not be null!");
        tom.e(identityInfo, "srcInfo must not be null!");
        tom.e(identityInfo2, "destInfo must not be null!");
        tom.e(p2pSendCallback, "sendCallback must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = tri.c(Binder.getCallingUid(), a2, this.f11245a.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        int callingUid = Binder.getCallingUid();
        try {
            PowerModeChangeManager.a().b(true);
            if (!this.d.b(Binder.getCallingUid(), c)) {
                tos.e("P2pManagerImpl", "sendInternal invalid interface invoking");
                throw new IllegalStateException(String.valueOf(18));
            }
            if (!tot.a(callingUid, identityInfo.getPackageName())) {
                tos.e("P2pManagerImpl", "Invalid srcPkgName");
                throw new IllegalStateException(String.valueOf(5));
            }
            IdentityInfo e = e(identityInfo, identityInfo2);
            this.b.a(DeviceProcessor.processInputDevice(c, device), messageParcelExtra, identityInfo, e, p2pSendCallback);
            wearEngineBiOperate.biApiCalling(a2, c, "send", String.valueOf(0));
            return 0;
        } catch (IllegalStateException e2) {
            tos.e("P2pManagerImpl", "sendInternal illegalStateException");
            wearEngineBiOperate.biApiCalling(a2, c, "send", String.valueOf(trj.b(e2)));
            throw e2;
        }
    }

    @Override // com.huawei.wearengine.P2pManager
    public int registerReceiverInternal(Device device, IdentityInfo identityInfo, IdentityInfo identityInfo2, ReceiverCallback receiverCallback, int i) throws RemoteException {
        tos.a("P2pManagerImpl", "registerReceiverInternal enter");
        tom.e(device, "device must not be null!");
        tom.e(identityInfo, "srcInfo must not be null!");
        tom.e(identityInfo2, "destInfo must not be null!");
        tom.e(receiverCallback, "receiverCallback must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = tri.c(Binder.getCallingUid(), a2, this.f11245a.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        int callingUid = Binder.getCallingUid();
        try {
            PowerModeChangeManager.a().b(true);
            if (!this.d.b(Binder.getCallingUid(), c)) {
                tos.e("P2pManagerImpl", "registerReceiverInternal invalid interface invoking");
                throw new IllegalStateException(String.valueOf(18));
            }
            if (!tot.a(callingUid, identityInfo.getPackageName())) {
                tos.e("registerReceiver", "Invalid srcPkgName");
                throw new IllegalStateException(String.valueOf(5));
            }
            receiverCallback.asBinder().linkToDeath(this.c, 0);
            IdentityInfo e = e(identityInfo, identityInfo2);
            int callingPid = Binder.getCallingPid();
            int e2 = this.b.e(DeviceProcessor.processInputDevice(c, device), identityInfo, e, new tqb(callingPid, i, receiverCallback));
            wearEngineBiOperate.biApiCalling(tot.a(), c, "registerReceiver", String.valueOf(e2));
            return e2;
        } catch (IllegalStateException e3) {
            tos.e("P2pManagerImpl", "registerReceiverInternal illegalStateException");
            wearEngineBiOperate.biApiCalling(a2, c, "registerReceiver", String.valueOf(trj.b(e3)));
            throw e3;
        }
    }

    @Override // com.huawei.wearengine.P2pManager
    public int cancelFileTransfer(Device device, FileIdentificationParcel fileIdentificationParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack) throws RemoteException {
        tos.a("P2pManagerImpl", "Start cancel file transfer");
        tom.e(device, "device must not be null!");
        tom.e(fileIdentificationParcel, "fileIdentification must not be null!");
        tom.e(identityInfo, "srcInfo must not be null!");
        tom.e(identityInfo2, "destInfo must not be null!");
        tom.c(fileIdentificationParcel.getFileName(), "fileIdentification fileName must not be empty");
        tom.e(p2pCancelFileTransferCallBack, "cancelFileTransferCallBack must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = tri.c(Binder.getCallingUid(), a2, this.f11245a.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            PowerModeChangeManager.a().b(true);
            this.d.a(c, "cancelFileTransfer", tqo.f17349a, Permission.DEVICE_MANAGER);
            int b = this.b.b(DeviceProcessor.processInputDevice(c, device), fileIdentificationParcel, identityInfo, identityInfo2, p2pCancelFileTransferCallBack);
            wearEngineBiOperate.biApiCalling(a2, c, "cancelFileTransfer", String.valueOf(b));
            return b;
        } catch (IllegalStateException e) {
            tos.e("P2pManagerImpl", "cancelFileTransfer illegalStateException");
            wearEngineBiOperate.biApiCalling(a2, c, "cancelFileTransfer", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.core.common.ClientBinderDied
    public void handleClientBinderDied(String str) {
        tos.b("P2pManagerImpl", "handleClientBinderDied clientPkgName is: " + str);
    }

    private MessageParcelExtra b(MessageParcel messageParcel) {
        MessageParcelExtra messageParcelExtra = new MessageParcelExtra();
        messageParcelExtra.setType(messageParcel.getType());
        messageParcelExtra.setData(messageParcel.getData());
        messageParcelExtra.setParcelFileDescriptor(messageParcel.getParcelFileDescriptor());
        messageParcelExtra.setFileName(messageParcel.getFileName());
        messageParcelExtra.setDescription(messageParcel.getDescription());
        messageParcelExtra.setFileSha256(messageParcel.getFileSha256());
        messageParcelExtra.setExtendJson("");
        return messageParcelExtra;
    }

    int c(int i) {
        if (!b()) {
            tos.e("P2pManagerImpl", "removeReceiver checkPermission failed");
            return 8;
        }
        int e = this.b.e(i);
        tos.a("P2pManagerImpl", "removeReceiver ret: " + e);
        return e;
    }

    private void c(String str) {
        tos.a("P2pManagerImpl", "removeReceiverByDevice ret: " + this.b.a(str));
    }

    private boolean b() {
        return Binder.getCallingUid() == getCallingUid();
    }

    @Override // com.huawei.wearengine.core.device.PowerModeChangeManager.HandlePowerModeChange
    public void startClearData(String str) {
        tos.a("P2pManagerImpl", "startClearData");
        tos.b("P2pManagerImpl", "startClearData deviceId is: " + str);
        c(str);
    }
}
