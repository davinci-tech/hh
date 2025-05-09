package health.compact.a;

import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.receiver.EcgPushReceiver;
import com.huawei.health.suggestion.receiver.FitnessHistoryPushReceiver;
import com.huawei.healthcloud.plugintrack.wearengine.rqmanager.RqDataPushReceiver;
import com.huawei.hihealthservice.sync.util.HiSyncMsgReceiver;
import com.huawei.hihealthservice.sync.util.HiWiFiSyncMsgReceiver;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import defpackage.ezq;
import defpackage.ezs;
import defpackage.qbl;

/* loaded from: classes.dex */
public class MessageCenterConfig {
    public static void e() {
        c();
    }

    private static void c() {
        com.huawei.hwlogsmodel.LogUtil.a("MessageCenterConfig", "registerHmsMsgHandlers enter");
        MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        messageCenterApi.registerMessageHandler(new EcgPushReceiver());
        messageCenterApi.registerMessageHandler(new FitnessHistoryPushReceiver());
        messageCenterApi.registerMessageHandler(new ezq());
        messageCenterApi.registerMessageHandler(new qbl());
        messageCenterApi.registerMessageHandler(new HealthZonePushReceiver());
        messageCenterApi.registerMessageHandler(new HiSyncMsgReceiver());
        messageCenterApi.registerMessageHandler(new HiWiFiSyncMsgReceiver());
        messageCenterApi.registerMessageHandler(new RqDataPushReceiver());
        messageCenterApi.registerMessageHandler(new ezs());
    }
}
