package defpackage;

import android.text.TextUtils;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.unitedevice.callback.IResultAIDLCallback;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.SendFileCallbackProxy;
import com.huawei.unitedevice.hwcommonfilemgr.TransferFileQueueManager;
import com.huawei.unitedevice.hwcommonfilemgr.entity.CommonFileInfoParcel;
import com.huawei.unitedevice.hwcommonfilemgr.entity.FileInfo;
import com.huawei.unitedevice.p2p.EngineManagementInterface;
import com.huawei.unitedevice.p2p.EnumCapabilityItem;
import com.huawei.unitedevice.p2p.IdentityInfo;
import com.huawei.unitedevice.p2p.MessageParcel;
import health.compact.a.LogUtil;

/* loaded from: classes7.dex */
public class spk implements EngineManagementInterface {
    private String e;

    private spk() {
        this.e = "EngineManagement";
    }

    public static spk b() {
        return d.e;
    }

    @Override // com.huawei.unitedevice.p2p.EngineManagementInterface
    public void registerReceiver(IdentityInfo identityInfo, IdentityInfo identityInfo2, spr sprVar, SendCallback sendCallback) {
        if (!b(sprVar)) {
            LogUtil.a(this.e, "registerReceiver Parameter is invalid");
            if (sendCallback != null) {
                sendCallback.onSendResult(500001);
                return;
            }
            return;
        }
        if (spi.d().e() == null) {
            LogUtil.a(this.e, "registerReceiver device is not connect.");
            if (sendCallback != null) {
                sendCallback.onSendResult(520003);
                return;
            }
            return;
        }
        LogUtil.c(this.e, "registerReceiver register to UDS.");
        int c = spo.a().c(identityInfo, identityInfo2, sprVar);
        if (sendCallback != null) {
            if (c == 0) {
                sendCallback.onSendResult(500000);
            } else {
                sendCallback.onSendResult(c);
            }
        }
    }

    private boolean b(spr sprVar) {
        if (sprVar != null) {
            return true;
        }
        LogUtil.a(this.e, "receiver is null");
        return false;
    }

    @Override // com.huawei.unitedevice.p2p.EngineManagementInterface
    public void unregisterReceiver(spr sprVar) {
        if (!b(sprVar)) {
            LogUtil.a(this.e, "unregisterReceiver Parameter is invalid");
        } else {
            LogUtil.c(this.e, "unregisterReceiver unregister to UDS.");
            spo.a().a(sprVar);
        }
    }

    @Override // com.huawei.unitedevice.p2p.EngineManagementInterface
    public void p2pSend(UniteDevice uniteDevice, IdentityInfo identityInfo, IdentityInfo identityInfo2, MessageParcel messageParcel, SendCallback sendCallback) {
        LogUtil.c(this.e, "enter p2pSend");
        if (!c(identityInfo, identityInfo2, messageParcel)) {
            if (sendCallback != null) {
                sendCallback.onSendResult(500001);
            }
            LogUtil.a(this.e, "p2pSend Parameter is invalid");
        } else {
            try {
                d(spi.d().d((uniteDevice == null || uniteDevice.getDeviceInfo() == null) ? null : uniteDevice.getDeviceInfo().getDeviceMac()), identityInfo, identityInfo2, messageParcel, sendCallback);
            } catch (IllegalStateException unused) {
                LogUtil.e(this.e, "send data RemoteException");
                if (sendCallback != null) {
                    sendCallback.onSendResult(520001);
                }
            }
        }
    }

    private boolean c(IdentityInfo identityInfo, IdentityInfo identityInfo2, MessageParcel messageParcel) {
        if (identityInfo == null) {
            LogUtil.a(this.e, "srcInfo is empty");
            return false;
        }
        if (identityInfo2 == null) {
            LogUtil.a(this.e, "destInfo is empty");
            return false;
        }
        if (messageParcel != null) {
            return true;
        }
        LogUtil.a(this.e, "message is empty");
        return false;
    }

    private void d(UniteDevice uniteDevice, IdentityInfo identityInfo, IdentityInfo identityInfo2, MessageParcel messageParcel, SendCallback sendCallback) {
        if (uniteDevice != null && uniteDevice.getDeviceInfo().isUsing()) {
            LogUtil.c(this.e, "use p2pmanager to send p2p message.");
            e(uniteDevice, identityInfo, identityInfo2, messageParcel, sendCallback);
        } else {
            LogUtil.a(this.e, "sendP2pMessage send p2p message fail,the device is null or not connected");
            if (sendCallback != null) {
                sendCallback.onSendResult(520003);
            }
        }
    }

    private void e(UniteDevice uniteDevice, IdentityInfo identityInfo, IdentityInfo identityInfo2, MessageParcel messageParcel, SendCallback sendCallback) {
        LogUtil.c(this.e, "send enter");
        if (a(uniteDevice, identityInfo, identityInfo2, messageParcel, sendCallback)) {
            if (messageParcel.getType() == 1) {
                b(uniteDevice, identityInfo, identityInfo2, messageParcel, sendCallback);
            } else {
                c(uniteDevice, identityInfo, identityInfo2, messageParcel, sendCallback);
            }
        }
    }

    private boolean a(UniteDevice uniteDevice, IdentityInfo identityInfo, IdentityInfo identityInfo2, MessageParcel messageParcel, SendCallback sendCallback) {
        if (messageParcel == null) {
            LogUtil.a(this.e, "checkSendParams message is null");
            return false;
        }
        if (identityInfo == null || identityInfo2 == null) {
            LogUtil.a(this.e, "checkSendParams pkgInfo is null");
            return false;
        }
        LogUtil.c(this.e, "checkSendParams srcPkgName is ", identityInfo.getPackageName(), " destPkgName is ", identityInfo2.getPackageName());
        if (!TextUtils.isEmpty(uniteDevice.getIdentify()) && spl.e(identityInfo.getPackageName()) && spl.e(identityInfo2.getPackageName())) {
            return true;
        }
        LogUtil.a(this.e, "checkSendParams device id is null or pkgName is invalid");
        if (sendCallback != null) {
            sendCallback.onSendProgress(0L);
        }
        return false;
    }

    private void b(UniteDevice uniteDevice, IdentityInfo identityInfo, IdentityInfo identityInfo2, MessageParcel messageParcel, SendCallback sendCallback) {
        if (messageParcel.getData().length == 0) {
            LogUtil.a(this.e, "send message data is invalid");
            if (sendCallback != null) {
                sendCallback.onSendProgress(0L);
            }
            iyv.c("P2PMessage", "P2PMessage sendData message data is invalid");
            return;
        }
        int c = spi.d().c();
        LogUtil.c(this.e, "send sequenceNum is ", Integer.valueOf(c));
        bir d2 = spl.d(uniteDevice, c, identityInfo, identityInfo2, messageParcel);
        if (d2 == null) {
            LogUtil.a(this.e, "ping deviceCommand is null");
            if (sendCallback != null) {
                sendCallback.onSendResult(12);
            }
            iyv.c("P2PMessage", "P2PMessage sendData commandMessage is null");
            return;
        }
        if (!bla.e(uniteDevice.getIdentify(), EnumCapabilityItem.POINT_TO_POINT_PING_AND_SEND_BYTES_ENUM)) {
            LogUtil.a(this.e, "send device is not support");
            return;
        }
        blt.c(this.e, d2.e(), "send deviceCommand is ");
        spq.c().b(c, sendCallback);
        b(uniteDevice, d2);
    }

    private void c(UniteDevice uniteDevice, IdentityInfo identityInfo, IdentityInfo identityInfo2, MessageParcel messageParcel, SendCallback sendCallback) {
        LogUtil.c(this.e, "sendFile enter");
        if (!bla.e(uniteDevice.getIdentify(), EnumCapabilityItem.POINT_TO_POINT_SEND_FILES_TO_WATCH_ENUM)) {
            LogUtil.a(this.e, "sendFile device is not support");
        }
        sol a2 = a(messageParcel, identityInfo, identityInfo2, new SendFileCallbackProxy(sendCallback));
        a2.d(uniteDevice);
        a2.e(TransferFileQueueManager.TaskMode.ENGINE);
        if (TransferFileQueueManager.d().a(a2, "engineManagement.sendFile")) {
            snz.a().e(uniteDevice, a2);
        } else {
            LogUtil.c(this.e, "wait send file queue over.");
        }
    }

    private sol a(MessageParcel messageParcel, IdentityInfo identityInfo, IdentityInfo identityInfo2, SendFileCallbackProxy sendFileCallbackProxy) {
        sol solVar = new sol();
        solVar.ejU_(messageParcel.getParcelFileDescriptor());
        solVar.a(messageParcel.getFileName());
        solVar.m(7);
        solVar.c(messageParcel.getDescription());
        solVar.h(identityInfo.getPackageName());
        solVar.d(identityInfo2.getPackageName());
        solVar.f(identityInfo.getFingerPrint());
        solVar.b(identityInfo2.getFingerPrint());
        solVar.g(messageParcel.getFileSha256());
        solVar.b(sendFileCallbackProxy);
        solVar.e(messageParcel.getFilePath());
        return solVar;
    }

    public void b(UniteDevice uniteDevice, bir birVar) {
        bgl.c().sendCommand(uniteDevice.getDeviceInfo(), birVar);
    }

    @Override // com.huawei.unitedevice.p2p.EngineManagementInterface
    public void ping(String str, String str2, PingCallback pingCallback, UniteDevice uniteDevice, int i) {
        LogUtil.c(this.e, "enter ping");
        if (!e(str, str2, pingCallback)) {
            if (pingCallback != null) {
                pingCallback.onPingResult(500001);
            }
            LogUtil.a(this.e, "ping Parameter is invalid");
        } else {
            try {
                b(spi.d().d((uniteDevice == null || uniteDevice.getDeviceInfo() == null) ? null : uniteDevice.getDeviceInfo().getDeviceMac()), str, str2, pingCallback, i);
            } catch (IllegalStateException unused) {
                LogUtil.e(this.e, "ping RemoteException");
            }
        }
    }

    private void d(UniteDevice uniteDevice, String str, String str2, PingCallback pingCallback, int i) {
        if (pingCallback == null) {
            LogUtil.a(this.e, "ping pingCallback is null");
            return;
        }
        LogUtil.c(this.e, "ping enter ping srcPkgName is ", str, " destPkgName is ", str2);
        if (uniteDevice == null || !spl.e(str2) || !spl.e(str)) {
            LogUtil.a(this.e, "ping device or pkgName is invalid");
            pingCallback.onPingResult(5);
            return;
        }
        String identify = uniteDevice.getIdentify();
        if (TextUtils.isEmpty(identify)) {
            LogUtil.a(this.e, "ping deviceId is empty");
            pingCallback.onPingResult(5);
            return;
        }
        if (!bla.e(identify, EnumCapabilityItem.POINT_TO_POINT_PING_AND_SEND_BYTES_ENUM)) {
            LogUtil.a(this.e, "ping device is not support");
            pingCallback.onPingResult(13);
            return;
        }
        int c = spi.d().c();
        LogUtil.c(this.e, "ping sequenceNum is ", Integer.valueOf(c));
        bir c2 = spl.c(uniteDevice, c, 1, str, str2, i);
        if (c2 == null) {
            LogUtil.a(this.e, "ping deviceCommand is null");
            return;
        }
        LogUtil.d(this.e, "ping deviceCommand is ", c2.toString());
        spq.c().d(c, pingCallback);
        b(uniteDevice, c2);
    }

    private boolean e(String str, String str2, PingCallback pingCallback) {
        if (str == null) {
            LogUtil.a(this.e, "ping srcPkgName is null");
            return false;
        }
        if (str2 == null) {
            LogUtil.a(this.e, "ping packageName is null");
            return false;
        }
        if (pingCallback != null) {
            return true;
        }
        LogUtil.a(this.e, "ping pingCallback is null");
        return false;
    }

    private void b(UniteDevice uniteDevice, String str, String str2, PingCallback pingCallback, int i) {
        if (uniteDevice != null && uniteDevice.getDeviceInfo().isUsing()) {
            d(uniteDevice, str, str2, pingCallback, i);
        } else {
            pingCallback.onPingResult(520003);
        }
    }

    @Override // com.huawei.unitedevice.p2p.EngineManagementInterface
    public void startTransferFile(FileInfo fileInfo, IResultAIDLCallback iResultAIDLCallback) {
        LogUtil.c(this.e, "enter transferCommonFile");
        if (fileInfo == null || iResultAIDLCallback == null || fileInfo.getIdentify() == null) {
            LogUtil.e(this.e, "startTransferFile Parameter is invalid");
        } else {
            b(fileInfo, iResultAIDLCallback);
        }
    }

    @Override // com.huawei.unitedevice.p2p.EngineManagementInterface
    public void stopTransferByQueue(CommonFileInfoParcel commonFileInfoParcel, ITransferFileCallback iTransferFileCallback) {
        if (commonFileInfoParcel == null) {
            return;
        }
        UniteDevice d2 = spi.d().d(commonFileInfoParcel.getIdentify());
        if (d2 != null && d2.getDeviceInfo().isUsing()) {
            LogUtil.c(this.e, "stopTransferFile.");
            snz.a().e(d2, commonFileInfoParcel, iTransferFileCallback);
        } else {
            LogUtil.c(this.e, "stopTransferByQueue send p2p message fail,the device is null or not connected");
        }
    }

    private void b(FileInfo fileInfo, IResultAIDLCallback iResultAIDLCallback) {
        UniteDevice d2 = spi.d().d(fileInfo.getIdentify());
        if (d2 != null && d2.getDeviceInfo().isUsing()) {
            LogUtil.c(this.e, "startTransferFileMessage. fileInfo.RequestType()", fileInfo.getRequestType());
            snz.a().e(d2, fileInfo, iResultAIDLCallback);
        } else {
            LogUtil.a(this.e, "startTransferFileMessage send p2p message fail,the device is null or not connected");
        }
    }

    static class d {
        private static spk e = new spk();
    }
}
