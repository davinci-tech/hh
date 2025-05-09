package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cun;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class SleepNoDataTopProvider extends BaseKnitDataProvider {
    SectionBean b;
    private boolean d;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("SleepNoDataTopProvider", "loadData");
        this.b = sectionBean;
        sectionBean.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        LogUtil.a("SleepNoDataTopProvider", "loadDefaultData");
        super.loadDefaultData(sectionBean);
        sectionBean.e(new Object());
        boolean e = e(c());
        this.d = e;
        LogUtil.a("SleepNoDataTopProvider", "mIsBindDevices: ", Boolean.valueOf(e));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        LogUtil.a("SleepNoDataTopProvider", "parseParams");
        hashMap.put("DEVICE_INFO_FLAG", Boolean.valueOf(this.d));
        if (this.d) {
            hashMap.put("RECT_CORNER_IMAGE", Integer.valueOf(R.drawable._2131431170_res_0x7f0b0f02));
            return;
        }
        hashMap.put("TOP_IMAGE", Integer.valueOf(R.drawable._2131430913_res_0x7f0b0e01));
        hashMap.put("TOP_IMAGE_TAHITI", Integer.valueOf(R.drawable._2131430914_res_0x7f0b0e02));
        hashMap.put("ROUND_CORNER_IMAGE", Integer.valueOf(R.drawable._2131431288_res_0x7f0b0f78));
    }

    private DeviceInfo c() {
        return cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "SleepNoDataTopProvider");
    }

    private boolean e(DeviceInfo deviceInfo) {
        return deviceInfo != null && deviceInfo.getDeviceConnectState() == 2;
    }
}
