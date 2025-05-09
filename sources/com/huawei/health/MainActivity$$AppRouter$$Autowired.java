package com.huawei.health;

import com.huawei.haf.router.facade.service.SerializationService;
import com.huawei.haf.router.facade.template.SyringeHandler;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;

/* loaded from: classes8.dex */
public final class MainActivity$$AppRouter$$Autowired implements SyringeHandler {
    @Override // com.huawei.haf.router.facade.template.SyringeHandler
    public void inject(Object obj, SerializationService serializationService) {
        MainActivity mainActivity = (MainActivity) obj;
        mainActivity.c = mainActivity.getIntent().getExtras() == null ? mainActivity.c : mainActivity.getIntent().getExtras().getString(Constants.HOME_TAB_NAME, mainActivity.c);
        mainActivity.e = mainActivity.getIntent().getIntExtra(BleConstants.SPORT_TYPE, mainActivity.e);
    }
}
