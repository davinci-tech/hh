package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.unitedevice.api.EngineChannelInterface;
import com.huawei.unitedevice.callback.IResultAIDLCallback;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.entity.RequestFileInfo;
import com.huawei.unitedevice.p2p.P2pReceiver;
import health.compact.a.LogUtil;

/* loaded from: classes7.dex */
public class snp implements EngineChannelInterface {
    private final snr c;
    private String e;

    private snp() {
        this.e = "EngineLogicService";
        this.c = snr.a();
    }

    public static snp c() {
        return c.d;
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void registerReceiver(Context context, snt sntVar, P2pReceiver p2pReceiver, SendCallback sendCallback) {
        if (sntVar == null || !e(context, p2pReceiver)) {
            LogUtil.e(this.e, "registerReceiver Parameter is invalid");
            if (sendCallback != null) {
                sendCallback.onSendResult(500001);
                return;
            }
            return;
        }
        this.c.registerReceiver(context, sntVar, p2pReceiver, sendCallback);
    }

    private boolean e(Context context, P2pReceiver p2pReceiver) {
        if (context == null) {
            LogUtil.e(this.e, "context is null");
            return false;
        }
        if (p2pReceiver != null) {
            return true;
        }
        LogUtil.e(this.e, "receiver is null");
        return false;
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void unregisterReceiver(Context context, P2pReceiver p2pReceiver, snt sntVar) {
        if (sntVar == null || !e(context, p2pReceiver)) {
            LogUtil.e(this.e, "unregisterReceiver Parameter is invalid");
        } else {
            LogUtil.c(this.e, "unregisterReceiver unregister to UDS.");
            this.c.unregisterReceiver(context, p2pReceiver, sntVar);
        }
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void p2pSendForWearEngine(Context context, UniteDevice uniteDevice, snt sntVar, SendCallback sendCallback) {
        if (!e(context, sntVar)) {
            if (sendCallback != null) {
                sendCallback.onSendResult(500001);
            }
            iyv.c("P2PMessage", "P2PMessage p2pSendForWearEngine context/message/WearPkgName/WearFingerprint is null");
            LogUtil.e(this.e, "Parameter is invalid");
            return;
        }
        this.c.p2pSendForWearEngine(context, uniteDevice, sntVar, sendCallback);
    }

    private boolean e(Context context, snt sntVar) {
        if (context == null) {
            LogUtil.e(this.e, "context is null");
            return false;
        }
        if (sntVar == null) {
            LogUtil.e(this.e, "message is null");
            return false;
        }
        if (TextUtils.isEmpty(sntVar.m())) {
            LogUtil.e(this.e, "message WearPkgName is empty");
            return false;
        }
        if (!TextUtils.isEmpty(sntVar.h())) {
            return true;
        }
        LogUtil.e(this.e, "message WearFingerprint is empty");
        return false;
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void startTransferFileToWear(sol solVar, IResultAIDLCallback iResultAIDLCallback) {
        if (solVar == null) {
            LogUtil.e(this.e, "fileInfo is invalid");
        } else {
            this.c.startTransferFileToWear(solVar, iResultAIDLCallback);
        }
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void pingSendForWearEngine(Context context, UniteDevice uniteDevice, String str, PingCallback pingCallback, int i) {
        if (!d(context, str)) {
            pingCallback.onPingResult(500001);
            LogUtil.e(this.e, "Parameter is invalid");
        } else {
            this.c.pingSendForWearEngine(context, uniteDevice, str, pingCallback, i);
        }
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void startReceiveFileFromWear(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) {
        if (requestFileInfo == null) {
            LogUtil.e(this.e, "startRequestFile RequestFileInfo is null");
        } else if (iTransferFileCallback == null) {
            LogUtil.e(this.e, "startRequestFile ITransferFileCallback is null");
        } else {
            this.c.startReceiveFileFromWear(requestFileInfo, iTransferFileCallback);
        }
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void stopReceiveFileFromWear(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback) {
        if (requestFileInfo == null) {
            LogUtil.e(this.e, "stopRequestFile RequestFileInfo is null");
        } else if (iTransferFileCallback == null) {
            LogUtil.e(this.e, "stopRequestFile IBaseCallback is null");
        } else {
            this.c.stopReceiveFileFromWear(requestFileInfo, iTransferFileCallback);
        }
    }

    @Override // com.huawei.unitedevice.api.EngineChannelInterface
    public void stopTransferFileToWear(sol solVar, ITransferFileCallback iTransferFileCallback) {
        if (solVar == null || iTransferFileCallback == null) {
            LogUtil.e(this.e, "stopTransferByQueue param invalid");
        } else {
            this.c.stopTransferFileToWear(solVar, iTransferFileCallback);
        }
    }

    private boolean d(Context context, String str) {
        if (context == null) {
            LogUtil.e(this.e, "ping context is null");
            return false;
        }
        if (str != null) {
            return true;
        }
        LogUtil.e(this.e, "ping packageName is null");
        return false;
    }

    static class c {
        private static snp d = new snp();
    }
}
