package com.huawei.ui.main.stories.health.pressure.provider;

import android.content.Context;
import android.content.Intent;
import android.widget.RelativeLayout;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.listener.ILayoutCreatedCallback;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.pressure.provider.PressureToastProvider;
import defpackage.gnm;
import defpackage.jfq;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.nlg;
import defpackage.sdg;
import health.compact.a.CommonUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class PressureToastProvider extends BaseKnitDataProvider {

    /* renamed from: a, reason: collision with root package name */
    private Context f10218a;
    private RelativeLayout b;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("PressureToastProvider", "loadData");
        this.f10218a = context;
        sectionBean.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        LogUtil.a("PressureToastProvider", "parseParams");
        hashMap.put("FULL_SCREEN_CALLBACK", new ILayoutCreatedCallback() { // from class: com.huawei.ui.main.stories.health.pressure.provider.PressureToastProvider.1
            @Override // com.huawei.health.knit.section.listener.ILayoutCreatedCallback
            public void onLayoutUpdate(RelativeLayout relativeLayout) {
                LogUtil.a("PressureToastProvider", "onLayoutUpdate, view: ", relativeLayout);
                PressureToastProvider.this.b = relativeLayout;
                PressureToastProvider.this.c();
            }
        });
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        LogUtil.a("PressureToastProvider", "resume");
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("PressureToastProvider", "configToast pressure");
        if (this.b == null) {
            LogUtil.a("PressureToastProvider", "mBtnTipsLayout is null");
            return;
        }
        if (CommonUtil.aa(this.f10218a)) {
            DeviceInfo a2 = jpt.a("PressureToastProvider");
            boolean isLogined = LoginInit.getInstance(this.f10218a).getIsLogined();
            LogUtil.a("PressureToastProvider", "onResume ", "isLogin = ", Boolean.valueOf(isLogined));
            if (!isLogined || a2 == null) {
                return;
            }
            LogUtil.a("PressureToastProvider", "currentDeviceInfo", a2.toString());
            if (a2.getDeviceConnectState() == 2) {
                b(a2);
            }
        }
    }

    private void b(DeviceInfo deviceInfo) {
        boolean d = d(deviceInfo);
        LogUtil.a("PressureToastProvider", "isSupportPressAutoMonitor = ", Boolean.valueOf(d));
        if (d) {
            jqi.a().getSwitchSetting("press_auto_monitor_switch_status", new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.health.pressure.provider.PressureToastProvider.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("PressureToastProvider", "processIfDeviceSupportPressureAutoMonitor errorCode = ", Integer.valueOf(i));
                    if (i == -1) {
                        PressureToastProvider.this.d();
                        return;
                    }
                    if (i == 0 && (obj instanceof String)) {
                        if ("false".equals((String) obj)) {
                            PressureToastProvider.this.d();
                            return;
                        }
                        return;
                    }
                    LogUtil.a("PressureToastProvider", "processIfDeviceSupportPressureAutoMonitor errorCode is other.");
                }
            });
        }
    }

    private boolean d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return false;
        }
        Map<String, DeviceCapability> a2 = jfq.c().a(1, deviceInfo.getDeviceIdentify(), "PressureToastProvider");
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("PressureToastProvider", "inquireDeviceMsdpCapability, deviceCapabilityHashMaps is empty");
            return false;
        }
        DeviceCapability deviceCapability = a2.get(deviceInfo.getDeviceIdentify());
        return deviceCapability != null && deviceCapability.isSupportPressAutoMonitor();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        nlg.cxS_(this.f10218a, new IBaseResponseCallback() { // from class: qmo
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                PressureToastProvider.this.a(i, obj);
            }
        }, this.b, this.f10218a.getResources().getString(R$string.IDS_hw_open_auto_pressure_detector_content, sdg.a()), new String[]{"pressure_auto_detector_agree_no_again_tip", "pressure_auto_detector_dialog_time", "pressure_auto_detector_count", "pressure_auto_detetor_is_show"});
    }

    public /* synthetic */ void a(int i, Object obj) {
        LogUtil.a("PressureToastProvider", "errorCode = ", Integer.valueOf(i));
        if (i == 0) {
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setPackage(BaseApplication.getAppPackage());
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.pressautomonitor.PressAutoMonitorActivity");
            intent.putExtra("pressure_is_have_datas", false);
            intent.putExtra("from_card", true);
            gnm.aPB_(this.f10218a, intent);
        }
    }
}
