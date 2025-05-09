package defpackage;

import com.huawei.basichealthmodel.receiver.HealthModelPushReceiver;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.hmf.services.ModuleProvider;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class asz extends ModuleProvider {
    @Override // com.huawei.hmf.services.ModuleProvider
    public void initialize() {
        super.initialize();
        ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).registerMessageHandler(new HealthModelPushReceiver());
    }
}
