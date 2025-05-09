package com.huawei.ui.homewear21.home;

import com.huawei.haf.router.facade.service.SerializationService;
import com.huawei.haf.router.facade.template.SyringeHandler;

/* loaded from: classes9.dex */
public final class WearHomeBaseActivity$$AppRouter$$Autowired implements SyringeHandler {
    @Override // com.huawei.haf.router.facade.template.SyringeHandler
    public void inject(Object obj, SerializationService serializationService) {
        WearHomeBaseActivity wearHomeBaseActivity = (WearHomeBaseActivity) obj;
        wearHomeBaseActivity.mDeviceMac = wearHomeBaseActivity.getIntent().getExtras() == null ? wearHomeBaseActivity.mDeviceMac : wearHomeBaseActivity.getIntent().getExtras().getString("device_id", wearHomeBaseActivity.mDeviceMac);
    }
}
