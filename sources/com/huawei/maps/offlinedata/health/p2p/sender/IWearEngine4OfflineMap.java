package com.huawei.maps.offlinedata.health.p2p.sender;

import com.huawei.maps.offlinedata.health.p2p.OfflineMapP2pMessage;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public interface IWearEngine4OfflineMap {
    Map<Integer, Boolean> queryDeviceCapability(List<Integer> list);

    void sendCancelCommand(String str, int i, IOfflineMapSendCallback iOfflineMapSendCallback);

    void sendMsgToDevice(OfflineMapP2pMessage offlineMapP2pMessage);

    void sendMsgToDevice(OfflineMapP2pMessage offlineMapP2pMessage, int i, IOfflineMapSendCallback iOfflineMapSendCallback);

    void sendPingToDevice(IOfflineMapPingCallback iOfflineMapPingCallback, String str);
}
