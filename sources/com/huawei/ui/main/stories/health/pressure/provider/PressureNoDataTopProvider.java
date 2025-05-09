package com.huawei.ui.main.stories.health.pressure.provider;

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
public class PressureNoDataTopProvider extends BaseKnitDataProvider {
    private boolean c;
    SectionBean e;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("PressureNoDataTopProvider", "loadData");
        this.e = sectionBean;
        sectionBean.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        LogUtil.a("PressureNoDataTopProvider", "loadDefaultData");
        super.loadDefaultData(sectionBean);
        sectionBean.e(new Object());
        boolean e = e(c());
        this.c = e;
        LogUtil.a("PressureNoDataTopProvider", "mIsBindDevices: ", Boolean.valueOf(e));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        LogUtil.a("PressureNoDataTopProvider", "parseParams");
        hashMap.put("DEVICE_INFO_FLAG", Boolean.valueOf(this.c));
        if (this.c) {
            hashMap.put("RECT_CORNER_IMAGE", Integer.valueOf(R.drawable._2131431170_res_0x7f0b0f02));
            return;
        }
        hashMap.put("TOP_IMAGE", Integer.valueOf(R.drawable._2131430911_res_0x7f0b0dff));
        hashMap.put("TOP_IMAGE_TAHITI", Integer.valueOf(R.drawable._2131430912_res_0x7f0b0e00));
        hashMap.put("ROUND_CORNER_IMAGE", Integer.valueOf(R.drawable._2131431288_res_0x7f0b0f78));
    }

    private DeviceInfo c() {
        return cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "PressureNoDataTopProvider");
    }

    private boolean e(DeviceInfo deviceInfo) {
        return deviceInfo != null && deviceInfo.getDeviceConnectState() == 2;
    }
}
