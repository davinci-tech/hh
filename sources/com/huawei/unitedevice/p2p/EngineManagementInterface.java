package com.huawei.unitedevice.p2p;

import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.unitedevice.callback.IResultAIDLCallback;
import com.huawei.unitedevice.callback.ITransferFileCallback;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.hwcommonfilemgr.entity.CommonFileInfoParcel;
import com.huawei.unitedevice.hwcommonfilemgr.entity.FileInfo;
import defpackage.spr;

/* loaded from: classes7.dex */
public interface EngineManagementInterface {
    void p2pSend(UniteDevice uniteDevice, IdentityInfo identityInfo, IdentityInfo identityInfo2, MessageParcel messageParcel, SendCallback sendCallback);

    void ping(String str, String str2, PingCallback pingCallback, UniteDevice uniteDevice, int i);

    void registerReceiver(IdentityInfo identityInfo, IdentityInfo identityInfo2, spr sprVar, SendCallback sendCallback);

    void startTransferFile(FileInfo fileInfo, IResultAIDLCallback iResultAIDLCallback);

    void stopTransferByQueue(CommonFileInfoParcel commonFileInfoParcel, ITransferFileCallback iTransferFileCallback);

    void unregisterReceiver(spr sprVar);
}
