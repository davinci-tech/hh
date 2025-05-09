package com.huawei.ui.main.stories.health.temperature.provider;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.template.ResourceParseHelper;
import defpackage.cjx;
import defpackage.cun;
import defpackage.jll;
import defpackage.koq;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class TemperatureNoDataDesProvider extends BaseKnitDataProvider implements ResourceParseHelper.ConfigInfoCallback {

    /* renamed from: a, reason: collision with root package name */
    private String f10246a;
    SectionBean c;
    private String d;
    private boolean e;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("PressureNoDataTopProvider", "loadData");
        this.c = sectionBean;
        sectionBean.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        LogUtil.a("PressureNoDataTopProvider", "loadDefaultData");
        super.loadDefaultData(sectionBean);
        sectionBean.e(new Object());
        this.e = e(c()) || jll.c() || b() > 0;
        this.d = BaseApplication.getContext().getString(R$string.IDS_desc_body_temperature);
        ResourceParseHelper.d(this);
        a(this.e);
        LogUtil.a("PressureNoDataTopProvider", "mIsBindDevices: ", Boolean.valueOf(this.e));
    }

    private void a(boolean z) {
        if (z) {
            ResourceParseHelper.a("IDS_desc_has_device_no_data", "BodyTemperatureCardConstructor", this);
            ResourceParseHelper.d("temperature_img_has_device_no_data", "BodyTemperatureCardConstructor", this);
        } else {
            ResourceParseHelper.a("IDS_desc_body_temperature", "BodyTemperatureCardConstructor", this);
            ResourceParseHelper.d("pic_body_temperature", "BodyTemperatureCardConstructor", this);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        LogUtil.a("PressureNoDataTopProvider", "parseParams");
        hashMap.put("DEVICE_INFO_FLAG", Boolean.valueOf(this.e));
        hashMap.put("NO_DATA_DESCRIPTION", this.d);
        if (this.e) {
            hashMap.put("HAS_DEVICE_NO_DATA_IMAGE", this.f10246a);
        } else {
            hashMap.put("TOP_IMAGE", this.f10246a);
            hashMap.put("ROUND_CORNER_IMAGE", Integer.valueOf(R.drawable._2131431288_res_0x7f0b0f78));
        }
    }

    private DeviceInfo c() {
        return cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "PressureNoDataTopProvider");
    }

    private boolean e(DeviceInfo deviceInfo) {
        return deviceInfo != null && deviceInfo.getDeviceConnectState() == 2;
    }

    private int b() {
        ArrayList<String> a2 = cjx.e().a(HealthDevice.HealthDeviceKind.HDK_BODY_TEMPERATURE);
        if (koq.b(a2)) {
            return 0;
        }
        return a2.size();
    }

    @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
    public void getTitleName(String str) {
        LogUtil.a("PressureNoDataTopProvider", "getTitleName");
    }

    @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
    public void getDescription(String str) {
        this.d = str;
        this.c.e(new Object());
    }

    @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
    public void getImagePath(String str) {
        this.f10246a = str;
        this.c.e(new Object());
    }

    @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.ConfigInfoCallback
    public void showParseErrorAlert() {
        LogUtil.a("PressureNoDataTopProvider", "showParseErrorAlert");
    }
}
