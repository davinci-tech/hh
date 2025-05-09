package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.widget.RelativeLayout;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.listener.ILayoutCreatedCallback;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.CoreSleepToastProvider;
import defpackage.jqi;
import defpackage.nlg;
import defpackage.scn;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class CoreSleepToastProvider extends BaseKnitDataProvider {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f9772a;
    private Context c;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("CoreSleepToastProvider", "loadData");
        this.c = context;
        sectionBean.e(new Object());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap hashMap, Object obj) {
        LogUtil.a("CoreSleepToastProvider", "parseParams");
        hashMap.put("FULL_SCREEN_CALLBACK", new ILayoutCreatedCallback() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.CoreSleepToastProvider.3
            @Override // com.huawei.health.knit.section.listener.ILayoutCreatedCallback
            public void onLayoutUpdate(RelativeLayout relativeLayout) {
                LogUtil.a("CoreSleepToastProvider", "onLayoutUpdate, view: ", relativeLayout);
                CoreSleepToastProvider.this.f9772a = relativeLayout;
                CoreSleepToastProvider.this.c();
            }
        });
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        LogUtil.a("CoreSleepToastProvider", "resume");
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("CoreSleepToastProvider", "configToast sleep");
        if (this.f9772a == null) {
            LogUtil.a("CoreSleepToastProvider", "mCoreSleepBtnTipsLayout is null");
            return;
        }
        boolean isLogined = LoginInit.getInstance(this.c).getIsLogined();
        LogUtil.a("CoreSleepToastProvider", "onResume ", "isLogin = ", Boolean.valueOf(isLogined));
        if (isLogined && scn.a()) {
            LogUtil.a("CoreSleepToastProvider", "DeviceConnectState.DEVICE_CONNECTED");
            jqi.a().getSwitchSetting("core_sleep_button", new IBaseResponseCallback() { // from class: pla
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    CoreSleepToastProvider.this.e(i, obj);
                }
            });
        }
    }

    public /* synthetic */ void e(int i, Object obj) {
        LogUtil.a("CoreSleepToastProvider", "coreSleep btn errorCode = ", Integer.valueOf(i));
        if (i == -1) {
            d();
            return;
        }
        if (i == 0 && (obj instanceof String)) {
            String str = (String) obj;
            LogUtil.a("CoreSleepToastProvider", "info = ", str);
            if ("0".equals(str)) {
                d();
                return;
            }
            return;
        }
        LogUtil.h("CoreSleepToastProvider", "currentDevice don't support CoreSleep.");
    }

    private void d() {
        LogUtil.a("CoreSleepToastProvider", "showSwitch");
        nlg.cxS_(this.c, new IBaseResponseCallback() { // from class: plc
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                CoreSleepToastProvider.this.a(i, obj);
            }
        }, this.f9772a, this.c.getResources().getString(R$string.IDS_core_sleep_switch_dialog_note), new String[]{"core_sleep_btn_tips_do_not_remind_again", "core_sleep_btn_tips_intervals", "core_sleep_btn_tips_cnt", "core_sleep_btn_tips_if_show"});
    }

    public /* synthetic */ void a(int i, Object obj) {
        if (i == 0) {
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setPackage(BaseApplication.getAppPackage());
            intent.putExtra("core_sleep_active_open_control_btn", true);
            intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.device.activity.coresleep.CoreSleepSelectorActivity");
            try {
                this.c.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                LogUtil.b("CoreSleepToastProvider", "ActivityNotFoundException", e.getMessage());
            }
        }
    }
}
