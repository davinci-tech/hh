package com.huawei.ui.homewear21.home;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.homewear21.home.WearHomeCalendarSettingActivity;
import defpackage.dsl;
import defpackage.jeg;
import defpackage.jfu;
import defpackage.koq;
import defpackage.nhu;
import defpackage.njj;
import defpackage.nrk;
import defpackage.nsn;
import defpackage.oxa;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class WearHomeCalendarSettingActivity extends BaseActivity {
    private static final String d = "WearHomeCalendarSettingActivity";

    /* renamed from: a, reason: collision with root package name */
    private String f9645a;
    private DeviceInfo c;
    private HealthSwitchButton e;

    public static class c implements HiDataReadResultListener {
        private final WeakReference<WearHomeCalendarSettingActivity> c;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        private c(WearHomeCalendarSettingActivity wearHomeCalendarSettingActivity) {
            this.c = new WeakReference<>(wearHomeCalendarSettingActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            final Boolean bool;
            ReleaseLogUtil.e(WearHomeCalendarSettingActivity.d, "onResult errorCode: ", Integer.valueOf(i), ", data: ", obj);
            if (obj instanceof List) {
                List list = (List) obj;
                if (!koq.b(list) && koq.e(obj, HiSampleConfig.class)) {
                    HiSampleConfig hiSampleConfig = (HiSampleConfig) list.get(0);
                    LogUtil.a(WearHomeCalendarSettingActivity.d, "calendar switch num : ", Integer.valueOf(list.size()), ", config : ", hiSampleConfig.getConfigData(), ", modifyTime : ", Long.valueOf(hiSampleConfig.getModifiedTime()));
                    bool = Boolean.valueOf("1".equals(dsl.c(hiSampleConfig.getConfigData(), "calendarToDeviceSwitch")));
                    HandlerExecutor.a(new Runnable() { // from class: oya
                        @Override // java.lang.Runnable
                        public final void run() {
                            WearHomeCalendarSettingActivity.c.this.a(bool);
                        }
                    });
                }
            }
            bool = null;
            HandlerExecutor.a(new Runnable() { // from class: oya
                @Override // java.lang.Runnable
                public final void run() {
                    WearHomeCalendarSettingActivity.c.this.a(bool);
                }
            });
        }

        public /* synthetic */ void a(Boolean bool) {
            WearHomeCalendarSettingActivity wearHomeCalendarSettingActivity = this.c.get();
            if (wearHomeCalendarSettingActivity == null || wearHomeCalendarSettingActivity.isFinishing()) {
                LogUtil.h(WearHomeCalendarSettingActivity.d, "activity is finished");
                return;
            }
            boolean d = nrk.d(BaseApplication.getContext());
            ReleaseLogUtil.e(WearHomeCalendarSettingActivity.d, "hasCalendarPermission: ", Boolean.valueOf(d), " finalIsOpenSwitch: ", bool);
            if (bool == null) {
                wearHomeCalendarSettingActivity.e(d);
                return;
            }
            if (d) {
                wearHomeCalendarSettingActivity.e.setChecked(bool.booleanValue());
            } else if (bool.booleanValue()) {
                wearHomeCalendarSettingActivity.e(false);
            } else {
                wearHomeCalendarSettingActivity.e.setChecked(false);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.wear_home_other_settings_camera_layout);
        Intent intent = getIntent();
        if (intent != null) {
            this.f9645a = intent.getStringExtra("device_id");
        }
        if (TextUtils.isEmpty(this.f9645a)) {
            LogUtil.h(d, "mDeviceMac is empty");
            this.c = oxa.a().f();
        } else {
            this.c = oxa.a().b(this.f9645a);
        }
        c();
    }

    private void c() {
        ((CustomTitleBar) findViewById(R.id.setting_device_title_bar)).setTitleText(getString(R.string._2130839194_res_0x7f02069a));
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.setting_switch_description);
        DeviceInfo deviceInfo = this.c;
        if (deviceInfo != null && jfu.h(deviceInfo.getProductType())) {
            healthTextView.setText(getResources().getString(R.string._2130839203_res_0x7f0206a3));
        } else {
            healthTextView.setText(getResources().getString(R.string._2130839193_res_0x7f020699));
        }
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) findViewById(R.id.setting_camera_switch_button);
        this.e = healthSwitchButton;
        healthSwitchButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homewear21.home.WearHomeCalendarSettingActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!(view instanceof CompoundButton)) {
                    LogUtil.a(WearHomeCalendarSettingActivity.d, "view not CompoundButton");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                boolean isChecked = ((CompoundButton) view).isChecked();
                LogUtil.a(WearHomeCalendarSettingActivity.d, "onclick, isChecked: ", Boolean.valueOf(isChecked));
                if (!isChecked || nrk.d(BaseApplication.getContext())) {
                    WearHomeCalendarSettingActivity.this.a(isChecked);
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    WearHomeCalendarSettingActivity.this.a();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        LogUtil.a(d, "onStart");
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("900100019");
        njj.d("9001", arrayList, new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a(d, "request CalendarPermissions");
        PermissionsResultAction permissionsResultAction = new PermissionsResultAction() { // from class: com.huawei.ui.homewear21.home.WearHomeCalendarSettingActivity.1
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a(WearHomeCalendarSettingActivity.d, "CalendarPermission Granted");
                WearHomeCalendarSettingActivity.this.e(true);
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a(WearHomeCalendarSettingActivity.d, "CalendarPermission Denied");
                if (!WearHomeCalendarSettingActivity.this.shouldShowRequestPermissionRationale("android.permission.READ_CALENDAR")) {
                    LogUtil.a(WearHomeCalendarSettingActivity.d, "show calendarPermission setting guide");
                    nsn.e(WearHomeCalendarSettingActivity.this, PermissionUtil.PermissionType.CALENDAR);
                }
                WearHomeCalendarSettingActivity.this.e(false);
            }
        };
        jeg.d().bGx_(this, new String[]{"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"}, permissionsResultAction);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        LogUtil.a(d, "setAndSaveSwitchStatus : ", Boolean.valueOf(z));
        this.e.setChecked(z);
        a(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a(d, "isOpen: ", Boolean.valueOf(z), ", currentTime: ", Long.valueOf(currentTimeMillis));
        njj.a("9001", "900100019", dsl.e("calendarToDeviceSwitch", z ? "1" : "0"), new b(z, currentTimeMillis), currentTimeMillis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        this.e.setChecked(z);
    }

    public static class b implements HiDataOperateListener {
        private final boolean b;
        private final WeakReference<WearHomeCalendarSettingActivity> d;
        private final long e;

        private b(WearHomeCalendarSettingActivity wearHomeCalendarSettingActivity, boolean z, long j) {
            this.d = new WeakReference<>(wearHomeCalendarSettingActivity);
            this.b = z;
            this.e = j;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public void onResult(int i, Object obj) {
            LogUtil.a(WearHomeCalendarSettingActivity.d, "saveSampleConfig errorCode: ", Integer.valueOf(i), ", object: ", obj, ", isOpen: ", Boolean.valueOf(this.b), ", modifyTime: ", Long.valueOf(this.e));
            if (1 != i) {
                if (this.b) {
                    nhu.b().syncCalendarSwitch(1);
                } else {
                    nhu.b().syncCalendarSwitch(7);
                }
                HandlerExecutor.a(new Runnable() { // from class: oyd
                    @Override // java.lang.Runnable
                    public final void run() {
                        WearHomeCalendarSettingActivity.b.this.a();
                    }
                });
            }
        }

        public /* synthetic */ void a() {
            WearHomeCalendarSettingActivity wearHomeCalendarSettingActivity = this.d.get();
            if (wearHomeCalendarSettingActivity == null || wearHomeCalendarSettingActivity.isFinishing()) {
                return;
            }
            wearHomeCalendarSettingActivity.b(this.b);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
