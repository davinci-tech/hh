package com.huawei.pluginmessagecenter;

import com.huawei.hmf.services.ModuleProvider;
import com.huawei.pluginmessagecenter.receiver.AccountMigratePushReceiver;
import com.huawei.pluginmessagecenter.receiver.MemberPushReceiver;
import com.huawei.pluginmessagecenter.receiver.TradePushReceiver;
import com.huawei.pluginmessagecenter.receiver.WifiSyncMsgReceiver;
import com.huawei.pluginmessagecenter.service.HuaweiHealthHmsPushService;
import defpackage.mrh;
import defpackage.mri;
import defpackage.mrl;
import defpackage.mrm;

/* loaded from: classes6.dex */
public class MessageCenterModule extends ModuleProvider {
    @Override // com.huawei.hmf.services.ModuleProvider
    public void initialize() {
        super.initialize();
        HuaweiHealthHmsPushService.registerMessageHandler(new AccountMigratePushReceiver());
        HuaweiHealthHmsPushService.registerMessageHandler(new WifiSyncMsgReceiver());
        HuaweiHealthHmsPushService.registerMessageHandler(new mrh());
        HuaweiHealthHmsPushService.registerMessageHandler(new TradePushReceiver());
        HuaweiHealthHmsPushService.registerMessageHandler(new MemberPushReceiver());
        HuaweiHealthHmsPushService.registerMessageHandler(new mrl());
        HuaweiHealthHmsPushService.registerMessageHandler(new mri());
        HuaweiHealthHmsPushService.registerMessageHandler(new mrm());
    }
}
