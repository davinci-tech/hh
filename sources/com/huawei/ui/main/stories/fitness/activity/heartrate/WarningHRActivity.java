package com.huawei.ui.main.stories.fitness.activity.heartrate;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.stories.fitness.activity.heartrate.WarningHRActivity;
import com.huawei.ui.main.stories.fitness.activity.heartrate.adapter.HeartRateAbnormalAdapter;
import defpackage.cun;
import defpackage.cvs;
import defpackage.gnm;
import defpackage.psc;
import java.util.List;

/* loaded from: classes6.dex */
public class WarningHRActivity extends BaseActivity {
    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_warning_heart_rate);
        b();
    }

    private void b() {
        getWindow().setBackgroundDrawableResource(R$color.colorSubBackground);
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.heart_rate_recycler);
        healthRecycleView.setLayoutManager(new HealthLinearLayoutManager(this));
        final HeartRateAbnormalAdapter heartRateAbnormalAdapter = new HeartRateAbnormalAdapter();
        healthRecycleView.setAdapter(heartRateAbnormalAdapter);
        ThreadPoolManager.d().execute(new Runnable() { // from class: prt
            @Override // java.lang.Runnable
            public final void run() {
                WarningHRActivity.this.e(heartRateAbnormalAdapter);
            }
        });
    }

    public /* synthetic */ void e(final HeartRateAbnormalAdapter heartRateAbnormalAdapter) {
        psc.a("HR_WARNING_DETAIL", 2101, new ResponseCallback() { // from class: prr
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                WarningHRActivity.this.b(heartRateAbnormalAdapter, i, (List) obj);
            }
        });
    }

    public /* synthetic */ void b(final HeartRateAbnormalAdapter heartRateAbnormalAdapter, int i, final List list) {
        if (list == null || list.size() == 0) {
            LogUtil.h("HealthHeartRate_WarningHRActivity", "queryModelUnitByDetail data is empty");
        } else {
            runOnUiThread(new Runnable() { // from class: prp
                @Override // java.lang.Runnable
                public final void run() {
                    WarningHRActivity.b(HeartRateAbnormalAdapter.this, list);
                }
            });
        }
    }

    public static /* synthetic */ void b(HeartRateAbnormalAdapter heartRateAbnormalAdapter, List list) {
        heartRateAbnormalAdapter.e(list);
        if (heartRateAbnormalAdapter.b()) {
            heartRateAbnormalAdapter.b(0);
        }
    }

    public static void e(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) WarningHRActivity.class);
        intent.addFlags(268435456);
        intent.setPackage(context.getPackageName());
        gnm.aPB_(context, intent);
    }

    public static void b(Context context) {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "HealthHeartRate_WarningHRActivity");
        if (context == null || deviceInfo == null) {
            LogUtil.h("HealthHeartRate_WarningHRActivity", "context or deviceInfo is null");
            return;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            LogUtil.h("HealthHeartRate_WarningHRActivity", "deviceMac is null");
            return;
        }
        DeviceCapability d = cvs.d();
        if (d != null && d.isSupportContinueHeartRate()) {
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.getContext(), "com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity");
            intent.putExtra("device_id", deviceIdentify);
            intent.addFlags(268435456);
            gnm.aPB_(context, intent);
            LogUtil.a("HealthHeartRate_WarningHRActivity", "jump to ContinueHeartRateSettingActivity");
            return;
        }
        if (d != null && d.isSupportHeartRateEnable() && !d.isSupportContinueHeartRate()) {
            Intent intent2 = new Intent();
            intent2.setClassName(BaseApplication.getContext(), "com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity");
            intent2.putExtra("device_id", deviceIdentify);
            intent2.addFlags(268435456);
            gnm.aPB_(context, intent2);
            LogUtil.a("HealthHeartRate_WarningHRActivity", "jump to HeartRateSettingsActivity");
            return;
        }
        LogUtil.h("HealthHeartRate_WarningHRActivity", "do nothing");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
