package defpackage;

import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapP2pMessage;
import com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapPingCallback;
import com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback;
import com.huawei.maps.offlinedata.health.p2p.sender.IWearEngine4OfflineMap;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class oaq implements IWearEngine4OfflineMap {
    @Override // com.huawei.maps.offlinedata.health.p2p.sender.IWearEngine4OfflineMap
    public Map<Integer, Boolean> queryDeviceCapability(List<Integer> list) {
        if (CollectionUtils.d(list)) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (Integer num : list) {
            DeviceInfo d = jpt.d("DEVMGR_OfflineMapAdapterImpl");
            if (d == null) {
                LogUtil.a("DEVMGR_OfflineMapAdapterImpl", "queryDeviceCapability deviceInfo is null ", num);
                hashMap.put(num, false);
            } else {
                boolean c = cwi.c(d, num.intValue());
                ReleaseLogUtil.b("DEVMGR_OfflineMapAdapterImpl", "queryDeviceCapability index ", num, " isSupport ", Boolean.valueOf(c));
                hashMap.put(num, Boolean.valueOf(c));
            }
        }
        return hashMap;
    }

    @Override // com.huawei.maps.offlinedata.health.p2p.sender.IWearEngine4OfflineMap
    public void sendMsgToDevice(OfflineMapP2pMessage offlineMapP2pMessage) {
        oat.c().e(offlineMapP2pMessage);
    }

    @Override // com.huawei.maps.offlinedata.health.p2p.sender.IWearEngine4OfflineMap
    public void sendMsgToDevice(OfflineMapP2pMessage offlineMapP2pMessage, int i, IOfflineMapSendCallback iOfflineMapSendCallback) {
        oat.c().c(offlineMapP2pMessage, iOfflineMapSendCallback, i);
    }

    @Override // com.huawei.maps.offlinedata.health.p2p.sender.IWearEngine4OfflineMap
    public void sendPingToDevice(IOfflineMapPingCallback iOfflineMapPingCallback, String str) {
        oat.c().e(iOfflineMapPingCallback, str);
    }

    @Override // com.huawei.maps.offlinedata.health.p2p.sender.IWearEngine4OfflineMap
    public void sendCancelCommand(String str, int i, IOfflineMapSendCallback iOfflineMapSendCallback) {
        oat.c().e(str, iOfflineMapSendCallback, i);
    }
}
