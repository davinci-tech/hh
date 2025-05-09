package defpackage;

import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cjf;
import health.compact.a.utils.StringUtils;

/* loaded from: classes3.dex */
public class ceq implements MessageReceiveCallback {
    private final BleTaskQueueUtil b;

    public ceq(BleTaskQueueUtil bleTaskQueueUtil) {
        this.b = bleTaskQueueUtil;
    }

    @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
    public void onDataReceived(DeviceInfo deviceInfo, biu biuVar, int i) {
        if (biuVar != null) {
            String b = biuVar.b();
            byte[] a2 = biuVar.a();
            LogUtil.a("PluginDevice_UdsMessageReceiveCallback", "UdsMessageReceiveCallback#onDataReceived characterUuid is " + b);
            if (StringUtils.i(b)) {
                LogUtil.a("PluginDevice_UdsMessageReceiveCallback", "UdsMessageReceiveCallback#onDataReceived do onCharacterChanged");
                cgt.e().e(new cjf.b().e(b).d(a2).d());
                return;
            }
            LogUtil.a("PluginDevice_UdsMessageReceiveCallback", "UdsMessageReceiveCallback#onDataReceived characterUuid is null");
            return;
        }
        LogUtil.a("PluginDevice_UdsMessageReceiveCallback", "UdsMessageReceiveCallback#onDataReceived dataContents is null");
    }

    @Override // com.huawei.devicesdk.callback.MessageReceiveCallback
    public void onChannelEnable(DeviceInfo deviceInfo, String str, int i) {
        LogUtil.a("PluginDevice_UdsMessageReceiveCallback", "UdsMessageReceiveCallback#onChannelEnable");
        BleTaskQueueUtil bleTaskQueueUtil = this.b;
        if (bleTaskQueueUtil != null) {
            bleTaskQueueUtil.c();
        }
    }
}
