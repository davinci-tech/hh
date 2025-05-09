package defpackage;

import com.huawei.health.device.wifi.interfaces.WiFiDevicePushReceiver;
import com.huawei.health.device.wifi.interfaces.WiFiMultiUserPushReceiver;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.hmf.services.ModuleProvider;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class dza extends ModuleProvider {
    @Override // com.huawei.hmf.services.ModuleProvider
    public void initialize() {
        super.initialize();
        MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        messageCenterApi.registerMessageHandler(new WiFiDevicePushReceiver());
        messageCenterApi.registerMessageHandler(new WiFiMultiUserPushReceiver());
    }
}
