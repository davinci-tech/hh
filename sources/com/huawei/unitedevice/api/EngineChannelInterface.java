package com.huawei.unitedevice.api;

import android.content.Context;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.unitedevice.callback.IResultAIDLCallback;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.entity.RequestFileInfo;
import com.huawei.unitedevice.p2p.P2pReceiver;
import defpackage.snt;
import defpackage.sol;

/* loaded from: classes.dex */
public interface EngineChannelInterface {
    void p2pSendForWearEngine(Context context, UniteDevice uniteDevice, snt sntVar, SendCallback sendCallback);

    void pingSendForWearEngine(Context context, UniteDevice uniteDevice, String str, PingCallback pingCallback, int i);

    void registerReceiver(Context context, snt sntVar, P2pReceiver p2pReceiver, SendCallback sendCallback);

    void startReceiveFileFromWear(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback);

    void startTransferFileToWear(sol solVar, IResultAIDLCallback iResultAIDLCallback);

    void stopReceiveFileFromWear(RequestFileInfo requestFileInfo, ITransferFileCallback iTransferFileCallback);

    void stopTransferFileToWear(sol solVar, ITransferFileCallback iTransferFileCallback);

    void unregisterReceiver(Context context, P2pReceiver p2pReceiver, snt sntVar);
}
