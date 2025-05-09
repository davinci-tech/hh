package com.huawei.ui.homewear21.home;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.basichealth.bloodpressure.BloodPressureSyncManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.ActivityReminder;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.device.activity.bloodoxygen.CycleBloodOxygenSettingActivity;
import com.huawei.ui.device.activity.bloodoxygen.HighLandBloodOxygenSettingActivity;
import com.huawei.ui.device.activity.bloodpressure.BloodPressureSettingActivity;
import com.huawei.ui.device.activity.coresleep.CoreSleepSelectorActivity;
import com.huawei.ui.device.activity.emotionautomonitor.EmotionAutoMonitorActivity;
import com.huawei.ui.device.activity.heartrate.ContinueHeartRateSettingActivity;
import com.huawei.ui.device.activity.heartrate.HeartRateSettingsActivity;
import com.huawei.ui.device.activity.menstrualpredict.MenstrualPredictActivity;
import com.huawei.ui.device.activity.pressautomonitor.PressAutoMonitorActivity;
import com.huawei.ui.device.activity.sleepbreathe.SleepBreatheActivity;
import com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.homewear21.home.WearHomeHealthMonitoringActivity;
import defpackage.cwi;
import defpackage.dkx;
import defpackage.ixx;
import defpackage.jad;
import defpackage.jhn;
import defpackage.jin;
import defpackage.jlj;
import defpackage.jlk;
import defpackage.jll;
import defpackage.jpo;
import defpackage.jps;
import defpackage.jpt;
import defpackage.jpu;
import defpackage.jqi;
import defpackage.jqy;
import defpackage.njh;
import defpackage.nrh;
import defpackage.obz;
import defpackage.ozh;
import defpackage.ozj;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class WearHomeHealthMonitoringActivity extends WearHomeBaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private String f9650a;
    private final CompoundButton.OnCheckedChangeListener c = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homewear21.home.WearHomeHealthMonitoringActivity$$ExternalSyntheticLambda23
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            WearHomeHealthMonitoringActivity.this.djS_(compoundButton, z);
        }
    };
    private boolean d;

    /* synthetic */ void djS_(CompoundButton compoundButton, final boolean z) {
        if (!this.mIsClickScreen) {
            LogUtil.h("WearHomeBaseActivity", "mIdleRemindListener return");
            ViewClickInstrumentation.clickOnView(compoundButton);
        } else {
            if (compoundButton == null) {
                ViewClickInstrumentation.clickOnView(compoundButton);
                return;
            }
            LogUtil.a("WearHomeBaseActivity", "Reminder is onCheckedChanged isChecked is ", Boolean.valueOf(z));
            compoundButton.setClickable(false);
            getItem(this.mGeneralList, 1).e(z);
            djQ_(compoundButton, z);
            compoundButton.setOnClickListener(new View.OnClickListener() { // from class: oyu
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WearHomeHealthMonitoringActivity.djP_(z, view);
                }
            });
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    }

    public static /* synthetic */ void djP_(boolean z, View view) {
        LogUtil.a("WearHomeBaseActivity", "onClick: HOME_1010023");
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (z) {
            hashMap.put("status", "1");
        } else {
            hashMap.put("status", "0");
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010023.value(), hashMap, 0);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void djQ_(final CompoundButton compoundButton, boolean z) {
        boolean c = cwi.c(this.mCurrentDeviceInfo, 154);
        ReleaseLogUtil.e("R_WearHomeHealthMonitoringActivity", "setActivityReminder isSupportCircleReminderSwitch :", Boolean.valueOf(c));
        if (c) {
            njh.b("900200004", z);
            compoundButton.setClickable(true);
            return;
        }
        if (b("custom.activity_reminder", z)) {
            LogUtil.a("WearHomeBaseActivity", "is family mode");
        } else {
            jqi.a().setSwitchSetting("custom.activity_reminder", a(z), new IBaseResponseCallback() { // from class: ozb
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    WearHomeHealthMonitoringActivity.this.djT_(compoundButton, i, obj);
                }
            });
        }
        ActivityReminder activityReminder = new ActivityReminder();
        activityReminder.setEnabled(z);
        byte[] a2 = jhn.a(activityReminder);
        if (jpt.d("WearHomeBaseActivity") != null) {
            LogUtil.a("03", 1, "WearHomeBaseActivity", "sendSetSwitchSettingCmd enter", activityReminder);
            jqi.a().sendSetSwitchSettingCmd(a2, "", 7, 7);
        } else {
            LogUtil.h("WearHomeBaseActivity", "sendSetSwitchSettingCmd getConnectDeviceInfo is null");
        }
        if (jpu.e("WearHomeBaseActivity") != null) {
            LogUtil.a("03", 1, "WearHomeBaseActivity", "sendSetSwitchSettingCmd Aw70 enter", activityReminder);
            jqi.a().sendSetSwitchSettingCmd(a2, jin.a(), 7, 7);
        } else {
            LogUtil.h("WearHomeBaseActivity", "sendSetSwitchSettingCmd HwGetAw70DeviceInfoUtil.getConnectDeviceInfo is null");
        }
    }

    public /* synthetic */ void djT_(CompoundButton compoundButton, int i, Object obj) {
        LogUtil.a("WearHomeBaseActivity", "setActivityReminder errorCode is ", Integer.valueOf(i), ",objectData is ", obj);
        if (i != 0) {
            runOnUiThread(new Runnable() { // from class: oze
                @Override // java.lang.Runnable
                public final void run() {
                    WearHomeHealthMonitoringActivity.this.c();
                }
            });
        }
        compoundButton.setClickable(true);
    }

    public /* synthetic */ void c() {
        nrh.e(this.mContext, R.string._2130841440_res_0x7f020f60);
    }

    private String a(boolean z) {
        ActivityReminder activityReminder = new ActivityReminder();
        activityReminder.setEnabled(z);
        return new Gson().toJson(activityReminder);
    }

    @Override // com.huawei.ui.homewear21.home.WearHomeBaseActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCustomTitleBar.setTitleText(BaseApplication.getContext().getString(R.string.IDS_device_home_health_monitor));
    }

    @Override // com.huawei.ui.homewear21.home.WearHomeBaseActivity
    protected void initGeneralList() {
        this.mGeneralList.clear();
        if (this.mDeviceCapability.isSupportCoreSleep() && jad.d(58)) {
            addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820768_res_0x7f1100e0, 23, 3), new ozh(getString(R.string._2130841941_res_0x7f021155), "", getString(R.string._2130841535_res_0x7f020fbf)), new CompoundButton.OnCheckedChangeListener[0]);
            e(this.mGeneralList);
        }
        boolean c = cwi.c(this.mCurrentDeviceInfo, 154);
        if (this.mDeviceCapability.isActivityReminder() || c) {
            o();
        }
        if (this.mDeviceCapability.isSupportContinueHeartRate()) {
            LogUtil.a("WearHomeBaseActivity", "prepare add press continue heart rate item");
            j();
        }
        if (this.mDeviceCapability.isSupportHeartRateEnable() && !this.mDeviceCapability.isSupportContinueHeartRate()) {
            LogUtil.a("WearHomeBaseActivity", "prepare add press cycle heart rate item");
            m();
        }
        boolean c2 = cwi.c(this.mCurrentDeviceInfo, 206);
        boolean b = dkx.b();
        if (this.mDeviceCapability.isSupportPressAutoMonitor() && (!c2 || b)) {
            LogUtil.a("WearHomeBaseActivity", "prepare add press auto monitor item");
            l();
        }
        if (c2 && !b) {
            LogUtil.a("WearHomeBaseActivity", "prepare add emotion auto monitor item");
            n();
        }
        if (this.mDeviceCapability.isSupportCycleBloodOxygenSwitch()) {
            f();
        }
        if (jll.c()) {
            t();
        }
        if (BloodPressureSyncManager.e()) {
            i();
        }
        if (cwi.c(this.mCurrentDeviceInfo, 72) && !Utils.o()) {
            k();
        }
        boolean e = jlj.a().e(this.mCurrentDeviceInfo);
        LogUtil.a("WearHomeBaseActivity", "device isSupportSleepBreathe：", Boolean.valueOf(e));
        if (e) {
            r();
        }
        b();
    }

    private void k() {
        addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820821_res_0x7f110115, 60, 3), new ozh(getString(R.string._2130845104_res_0x7f021db0), "", getString(R.string._2130841536_res_0x7f020fc0)), new CompoundButton.OnCheckedChangeListener[0]);
        e();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        djO_(i, i2, intent);
        b(i);
    }

    private void djO_(int i, int i2, Intent intent) {
        String str;
        if (i == 23) {
            if (i2 != -1) {
                return;
            }
            if (intent != null) {
                DeviceSettingsInteractors deviceSettingsInteractors = this.mDeviceInteractors;
                str = intent.getStringExtra("status");
            } else {
                str = "";
            }
            LogUtil.a("WearHomeBaseActivity", "coreSleepButton:", str);
            DeviceSettingsInteractors deviceSettingsInteractors2 = this.mDeviceInteractors;
            if ("0".equals(str)) {
                setSettingItemRightText(23, this.mGeneralList, getString(R.string._2130841535_res_0x7f020fbf));
                return;
            } else {
                setSettingItemRightText(23, this.mGeneralList, getString(R.string._2130841536_res_0x7f020fc0));
                return;
            }
        }
        if (i != 24) {
            if (i == 35) {
                f(this.mGeneralList);
                return;
            }
            if (i == 68) {
                djR_(i2, intent);
                return;
            } else if (i == 329) {
                j(this.mGeneralList);
                return;
            } else {
                LogUtil.h("WearHomeBaseActivity", "handleReturnedResult default branch.");
                return;
            }
        }
        if (i2 != -1) {
            return;
        }
        DeviceSettingsInteractors deviceSettingsInteractors3 = this.mDeviceInteractors;
        String stringExtra = intent.getStringExtra("status");
        LogUtil.a("WearHomeBaseActivity", "heartRateButton:", stringExtra);
        DeviceSettingsInteractors deviceSettingsInteractors4 = this.mDeviceInteractors;
        if ("0".equals(stringExtra)) {
            this.d = false;
            setSettingItemRightText(24, this.mGeneralList, getString(R.string._2130841535_res_0x7f020fbf));
        } else {
            this.d = true;
            setSettingItemRightText(24, this.mGeneralList, getString(R.string._2130841536_res_0x7f020fc0));
        }
    }

    private void b(int i) {
        LogUtil.a("WearHomeBaseActivity", "onActivityResult processOtherActivityResult");
        if (i == 52) {
            d(this.mGeneralList);
            return;
        }
        if (i == 121) {
            LogUtil.a("WearHomeBaseActivity", "onActivityResult ID_SLEEP_BREATHE");
            r();
        } else if (i == 59) {
            i();
        } else {
            if (i == 60) {
                LogUtil.a("WearHomeBaseActivity", "onActivityResult SUPPORT_HIGH_LAND_BLOOD_OXYGEN");
                e();
                d();
                return;
            }
            LogUtil.h("WearHomeBaseActivity", "handleTemperature default branch.");
        }
    }

    @Override // com.huawei.ui.homewear21.home.WearHomeBaseActivity
    protected void setItemClick(int i) {
        if (i >= this.mGeneralList.size()) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        int e = this.mGeneralList.get(i).e();
        if (e == 1) {
            LogUtil.h("WearHomeBaseActivity", "DeviceSettingConstants.ID_DEVICE_SETTING_IDLE:");
            return;
        }
        if (e == 35) {
            startActivity(ContinueHeartRateSettingActivity.class, 35);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090022.value(), hashMap, 0);
            return;
        }
        if (e == 47) {
            startActivity(CycleBloodOxygenSettingActivity.class, 47);
            return;
        }
        if (e == 52) {
            startActivity(TemperatureMonitoringActivity.class, 52);
            return;
        }
        if (e == 78) {
            startActivity(EmotionAutoMonitorActivity.class, 78);
            return;
        }
        if (e == 121) {
            p();
            return;
        }
        if (e == 329) {
            startActivity(PressAutoMonitorActivity.class, 329);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010050.value(), hashMap, 0);
        } else if (e == 23) {
            c(hashMap);
        } else if (e == 24) {
            startActivity(HeartRateSettingsActivity.class, 24);
        } else {
            c(e);
        }
    }

    private void c(int i) {
        if (i == 59) {
            startActivity(BloodPressureSettingActivity.class, 59);
            return;
        }
        if (i == 60) {
            startActivity(HighLandBloodOxygenSettingActivity.class, 60);
        } else if (i == 68) {
            s();
        } else {
            LogUtil.h("WearHomeBaseActivity", "setItemClickContinue else branch.");
        }
    }

    private void c(Map<String, Object> map) {
        Intent intent = new Intent(this.mContext, (Class<?>) CoreSleepSelectorActivity.class);
        intent.putExtra("device_id", this.mDeviceMac);
        intent.putExtra("coreSleepEnable", this.d);
        startActivityForResult(intent, 23);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090005.value(), map, 0);
    }

    private void l() {
        addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820777_res_0x7f1100e9, 329, 3), new ozh(getString(R.string._2130843320_res_0x7f0216b8), "", getString(R.string._2130841536_res_0x7f020fc0)), new CompoundButton.OnCheckedChangeListener[0]);
        j(this.mGeneralList);
    }

    private void n() {
        addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820777_res_0x7f1100e9, 78, 3), new ozh(getString(R.string._2130847318_res_0x7f022656), "", getString(R.string._2130841536_res_0x7f020fc0)), new CompoundButton.OnCheckedChangeListener[0]);
        i(this.mGeneralList);
    }

    private void e() {
        jqi.a().getSwitchSetting("highland.blood.oxygen.switch", new IBaseResponseCallback() { // from class: oym
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WearHomeHealthMonitoringActivity.this.e(i, obj);
            }
        });
    }

    public /* synthetic */ void e(int i, Object obj) {
        LogUtil.a("WearHomeBaseActivity", "initHighLandItem errorCode: ", Integer.valueOf(i));
        final String c = c((i == 0 && (obj instanceof String)) ? (String) obj : "0");
        LogUtil.a("WearHomeBaseActivity", "showStatus high land:", c);
        runOnUiThread(new Runnable() { // from class: oyo
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeHealthMonitoringActivity.this.a(c);
            }
        });
    }

    public /* synthetic */ void a(String str) {
        getItem(this.mGeneralList, 60).e(str);
        refreshGeneralDataAdapter();
    }

    private void j(final List<obz> list) {
        jqi.a().getSwitchSetting("press_auto_monitor_switch_status", new IBaseResponseCallback() { // from class: ozc
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WearHomeHealthMonitoringActivity.this.c(list, i, obj);
            }
        });
    }

    public /* synthetic */ void c(final List list, int i, Object obj) {
        final String string;
        LogUtil.a("WearHomeBaseActivity", "showPressItem errorCode: ", Integer.valueOf(i), " ; objectData: ", obj);
        boolean z = (i == 0 && (obj instanceof String)) ? !"false".equals((String) obj) : false;
        LogUtil.a("WearHomeBaseActivity", "showPressItem isEnable: ", Boolean.valueOf(z));
        if (z) {
            string = getString(R.string._2130841536_res_0x7f020fc0);
        } else {
            string = getString(R.string._2130841535_res_0x7f020fbf);
        }
        runOnUiThread(new Runnable() { // from class: oza
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeHealthMonitoringActivity.this.b(list, string);
            }
        });
    }

    public /* synthetic */ void b(List list, String str) {
        getItem(list, 329).e(str);
        refreshGeneralDataAdapter();
    }

    /* renamed from: com.huawei.ui.homewear21.home.WearHomeHealthMonitoringActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements IBaseResponseCallback {
        final /* synthetic */ List c;

        AnonymousClass4(List list) {
            this.c = list;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i == 0 && (obj instanceof String)) {
                final String c = WearHomeHealthMonitoringActivity.this.c((String) obj);
                LogUtil.a("WearHomeBaseActivity", "showEmotionItem howStatus :", c);
                WearHomeHealthMonitoringActivity wearHomeHealthMonitoringActivity = WearHomeHealthMonitoringActivity.this;
                final List list = this.c;
                wearHomeHealthMonitoringActivity.runOnUiThread(new Runnable() { // from class: ozi
                    @Override // java.lang.Runnable
                    public final void run() {
                        WearHomeHealthMonitoringActivity.AnonymousClass4.this.a(list, c);
                    }
                });
            }
        }

        public /* synthetic */ void a(List list, String str) {
            WearHomeHealthMonitoringActivity.this.getItem(list, 78).e(str);
            WearHomeHealthMonitoringActivity.this.refreshGeneralDataAdapter();
        }
    }

    private void i(List<obz> list) {
        jps.e(new AnonymousClass4(list));
    }

    private void f() {
        addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820820_res_0x7f110114, 47, 3), new ozh(getString(R.string._2130843907_res_0x7f021903), "", getString(R.string._2130841536_res_0x7f020fc0)), new CompoundButton.OnCheckedChangeListener[0]);
        d();
    }

    private void d() {
        if (jpo.c(this.mDeviceMac) == 2) {
            String b = SharedPreferenceManager.b(this.mContext, "100", "custom.blood.oxygen.switch");
            LogUtil.a("WearHomeBaseActivity", "initBloodOxygenItem is:", b);
            getItem(this.mGeneralList, 47).e(c(b));
            refreshGeneralDataAdapter();
            return;
        }
        jqi.a().getSwitchSetting("custom.blood.oxygen.switch", new IBaseResponseCallback() { // from class: oyl
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WearHomeHealthMonitoringActivity.this.c(i, obj);
            }
        });
    }

    public /* synthetic */ void c(int i, Object obj) {
        LogUtil.a("WearHomeBaseActivity", "initBloodOxygenItem errorCode: ", Integer.valueOf(i));
        final String c = c((i == 0 && (obj instanceof String)) ? (String) obj : "0");
        LogUtil.a("WearHomeBaseActivity", "showStatus", c);
        runOnUiThread(new Runnable() { // from class: oyp
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeHealthMonitoringActivity.this.d(c);
            }
        });
    }

    public /* synthetic */ void d(String str) {
        getItem(this.mGeneralList, 47).e(str);
        refreshGeneralDataAdapter();
    }

    private void m() {
        addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820853_res_0x7f110135, 24, 3), new ozh(getString(R.string._2130842694_res_0x7f021446), "", getString(R.string._2130841535_res_0x7f020fbf)), new CompoundButton.OnCheckedChangeListener[0]);
        c(this.mGeneralList);
    }

    private void c(final List<obz> list) {
        if (jpo.c(this.mDeviceMac) == 2) {
            String b = SharedPreferenceManager.b(this.mContext, "102", "heart_rate_button");
            LogUtil.a("WearHomeBaseActivity", "changeHeartRateSwitch familyStatus is:", b);
            getItem(list, 24).e(c(b));
            refreshGeneralDataAdapter();
            return;
        }
        jqi.a().getSwitchSetting("heart_rate_button", new IBaseResponseCallback() { // from class: oyw
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WearHomeHealthMonitoringActivity.this.a(list, i, obj);
            }
        });
    }

    public /* synthetic */ void a(final List list, int i, Object obj) {
        final String string;
        LogUtil.a("WearHomeBaseActivity", "changeHeartRateSwitch errorCode is ", Integer.valueOf(i));
        final boolean z = false;
        if (i == 0 && (obj instanceof String)) {
            z = setEnable(i, (String) obj, false);
        }
        LogUtil.a("WearHomeBaseActivity", "changeHeartRateSwitch isEnable is ", Boolean.valueOf(z));
        if (z) {
            string = getString(R.string._2130841536_res_0x7f020fc0);
        } else {
            string = getString(R.string._2130841535_res_0x7f020fbf);
        }
        runOnUiThread(new Runnable() { // from class: oyk
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeHealthMonitoringActivity.this.d(list, string, z);
            }
        });
    }

    public /* synthetic */ void d(List list, String str, boolean z) {
        getItem(list, 24).e(str);
        refreshGeneralDataAdapter();
        d(z);
    }

    private void j() {
        addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820853_res_0x7f110135, 35, 3), new ozh(getString(R.string._2130842694_res_0x7f021446), "", getString(R.string._2130841536_res_0x7f020fc0)), new CompoundButton.OnCheckedChangeListener[0]);
        f(this.mGeneralList);
    }

    private void f(final List<obz> list) {
        if (jpo.c(this.mDeviceMac) == 2) {
            String b = SharedPreferenceManager.b(this.mContext, "101", "continue_heart_rate");
            LogUtil.a("WearHomeBaseActivity", "showContinueHearItem familyStatus is:", b);
            getItem(list, 35).e(c(b));
            refreshGeneralDataAdapter();
            return;
        }
        jqi.a().getSwitchSetting("continue_heart_rate", new IBaseResponseCallback() { // from class: oyi
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WearHomeHealthMonitoringActivity.this.e(list, i, obj);
            }
        });
    }

    public /* synthetic */ void e(final List list, int i, Object obj) {
        final String string;
        LogUtil.a("WearHomeBaseActivity", "showContinueHearItem errorCode is ", Integer.valueOf(i));
        final boolean z = false;
        if (i == 0 && (obj instanceof String)) {
            z = setEnable(i, (String) obj, false);
        }
        LogUtil.a("WearHomeBaseActivity", "showContinueHearItem isEnable is ", Boolean.valueOf(z));
        if (z) {
            string = getString(R.string._2130841536_res_0x7f020fc0);
        } else {
            string = getString(R.string._2130841535_res_0x7f020fbf);
        }
        runOnUiThread(new Runnable() { // from class: oyz
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeHealthMonitoringActivity.this.b(list, string, z);
            }
        });
    }

    public /* synthetic */ void b(List list, String str, boolean z) {
        getItem(list, 35).e(str);
        refreshGeneralDataAdapter();
        d(z);
    }

    private void d(boolean z) {
        if (!jll.c()) {
            LogUtil.a("WearHomeBaseActivity", "checkTemperatureStatusByHeartRate isn't Sport Temperature.");
            return;
        }
        LogUtil.a("WearHomeBaseActivity", "checkTemperatureStatusByHeartRate isEnable : ", Boolean.valueOf(z));
        if (z) {
            return;
        }
        jlk.a().c();
    }

    private void d(List<obz> list) {
        String string;
        if (Utils.o()) {
            int m = CommonUtil.m(this, jqy.c("continuous_temp_monitoring"));
            if (m == 1) {
                string = getString(R.string._2130841536_res_0x7f020fc0);
            } else {
                string = getString(R.string._2130841535_res_0x7f020fbf);
            }
            LogUtil.a("WearHomeBaseActivity", "setTemperatureStatus isOpened is", Integer.valueOf(m));
            getItem(list, 52).e(string);
            refreshGeneralDataAdapter();
        }
    }

    private void o() {
        LogUtil.a("WearHomeBaseActivity", "showReminderItem getIdleRemind");
        addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820773_res_0x7f1100e5, 1, 0), new ozh(getString(R.string._2130841501_res_0x7f020f9d), getString(R.string._2130841390_res_0x7f020f2e), ""), this.c);
        boolean c = cwi.c(this.mCurrentDeviceInfo, 154);
        ReleaseLogUtil.e("R_WearHomeHealthMonitoringActivity", "showReminderItem isSupportCircleReminderSwitch :", Boolean.valueOf(c));
        if (c) {
            h();
        } else {
            if (c("custom.activity_reminder", 1)) {
                return;
            }
            jqi.a().getSwitchSetting("custom.activity_reminder", new IBaseResponseCallback() { // from class: oyr
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    WearHomeHealthMonitoringActivity.this.b(i, obj);
                }
            });
        }
    }

    public /* synthetic */ void b(int i, Object obj) {
        final boolean z = true;
        if (i == 0 && (obj instanceof String)) {
            try {
                String str = (String) obj;
                if (str.contains(k.g)) {
                    LogUtil.a("WearHomeBaseActivity", "showReminderItem info contains enable");
                    str = str.replaceAll(k.g, "isEnable");
                }
                boolean isEnabled = ((ActivityReminder) new Gson().fromJson(str, ActivityReminder.class)).isEnabled();
                try {
                    LogUtil.a("WearHomeBaseActivity", "showReminderItem getActivityReminder ", Boolean.valueOf(isEnabled));
                    z = isEnabled;
                } catch (JsonSyntaxException unused) {
                    z = isEnabled;
                    LogUtil.b("WearHomeBaseActivity", "showReminderItem JsonSyntaxException");
                    runOnUiThread(new Runnable() { // from class: oyt
                        @Override // java.lang.Runnable
                        public final void run() {
                            WearHomeHealthMonitoringActivity.this.c(z);
                        }
                    });
                }
            } catch (JsonSyntaxException unused2) {
            }
        }
        runOnUiThread(new Runnable() { // from class: oyt
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeHealthMonitoringActivity.this.c(z);
            }
        });
    }

    public /* synthetic */ void c(boolean z) {
        getItem(this.mGeneralList, 1).e(z);
        refreshGeneralDataAdapter();
    }

    private void h() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("900200004");
        njh.c(arrayList, new AnonymousClass1());
    }

    /* renamed from: com.huawei.ui.homewear21.home.WearHomeHealthMonitoringActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements IBaseResponseCallback {
        AnonymousClass1() {
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            final boolean equals = obj instanceof HashMap ? "1".equals((String) ((HashMap) obj).get("900200004")) : true;
            ReleaseLogUtil.e("R_WearHomeHealthMonitoringActivity", "refreshReminderItemByCircleSwitch isEnable:", Boolean.valueOf(equals));
            WearHomeHealthMonitoringActivity.this.runOnUiThread(new Runnable() { // from class: ozg
                @Override // java.lang.Runnable
                public final void run() {
                    WearHomeHealthMonitoringActivity.AnonymousClass1.this.b(equals);
                }
            });
        }

        public /* synthetic */ void b(boolean z) {
            WearHomeHealthMonitoringActivity wearHomeHealthMonitoringActivity = WearHomeHealthMonitoringActivity.this;
            wearHomeHealthMonitoringActivity.getItem(wearHomeHealthMonitoringActivity.mGeneralList, 1).e(z);
            WearHomeHealthMonitoringActivity.this.refreshGeneralDataAdapter();
        }
    }

    private void i() {
        addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820765_res_0x7f1100dd, 59, 3), new ozh(getString(R.string._2130845112_res_0x7f021db8), "", getString(R.string._2130841536_res_0x7f020fc0)), new CompoundButton.OnCheckedChangeListener[0]);
        a();
    }

    private void a() {
        jqi.a().getSwitchSetting("blood_pressure_remind", new IBaseResponseCallback() { // from class: oyx
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WearHomeHealthMonitoringActivity.this.d(i, obj);
            }
        });
    }

    public /* synthetic */ void d(int i, Object obj) {
        LogUtil.a("WearHomeBaseActivity", "initBloodPressureItem errorCode: ", Integer.valueOf(i));
        final String c = c((i == 0 && (obj instanceof String)) ? (String) obj : "0");
        LogUtil.a("WearHomeBaseActivity", "showStatus", c);
        runOnUiThread(new Runnable() { // from class: oyh
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeHealthMonitoringActivity.this.e(c);
            }
        });
    }

    public /* synthetic */ void e(String str) {
        getItem(this.mGeneralList, 59).e(str);
        refreshGeneralDataAdapter();
    }

    private void e(final List<obz> list) {
        jqi.a().getSwitchSetting("core_sleep_button", new IBaseResponseCallback() { // from class: ozd
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WearHomeHealthMonitoringActivity.this.b(list, i, obj);
            }
        });
    }

    public /* synthetic */ void b(final List list, int i, Object obj) {
        final String string;
        LogUtil.a("WearHomeBaseActivity", "changeSleepSwitch errorCode is ", Integer.valueOf(i));
        this.d = false;
        if (i == 0 && (obj instanceof String)) {
            this.d = setEnable(i, (String) obj, false);
        }
        LogUtil.a("WearHomeBaseActivity", "changeSleepSwitch isEnable is ", Boolean.valueOf(this.d));
        if (this.d) {
            string = getString(R.string._2130841536_res_0x7f020fc0);
        } else {
            string = getString(R.string._2130841535_res_0x7f020fbf);
        }
        runOnUiThread(new Runnable() { // from class: oyy
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeHealthMonitoringActivity.this.c(list, string);
            }
        });
    }

    public /* synthetic */ void c(List list, String str) {
        setSettingItemRightText(23, list, str);
        refreshGeneralDataAdapter();
    }

    private void t() {
        String string;
        if (Utils.o()) {
            string = getString(R.string._2130844206_res_0x7f021a2e);
        } else if (jll.d()) {
            string = getString(R.string._2130846676_res_0x7f0223d4);
        } else {
            string = getString(R.string._2130844195_res_0x7f021a23);
        }
        addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131821128_res_0x7f110248, 52, 3), new ozh(string, "", ""), new CompoundButton.OnCheckedChangeListener[0]);
        d(this.mGeneralList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(String str) {
        if ("1".equals(str)) {
            return getString(R.string._2130841536_res_0x7f020fc0);
        }
        return getString(R.string._2130841535_res_0x7f020fbf);
    }

    private boolean c(String str, int i) {
        if (jpo.c(this.mDeviceMac) != 2) {
            return false;
        }
        String b = SharedPreferenceManager.b(this.mContext, String.valueOf(10008), str);
        getItem(this.mGeneralList, i).e(TextUtils.isEmpty(b) ? true : Boolean.parseBoolean(b));
        refreshGeneralDataAdapter();
        return true;
    }

    private boolean b(String str, boolean z) {
        if (jpo.c(this.mDeviceMac) != 2) {
            return false;
        }
        SharedPreferenceManager.e(this.mContext, String.valueOf(10008), str, Boolean.toString(z), (StorageParams) null);
        return true;
    }

    private void p() {
        Intent intent = new Intent(this.mContext, (Class<?>) SleepBreatheActivity.class);
        intent.putExtra("SleepBreatheEnable", this.f9650a);
        startActivityForResult(intent, 121);
    }

    private void r() {
        addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131821120_res_0x7f110240, 121, 3), new ozh(getString(jlj.a().d() ? R.string._2130847159_res_0x7f0225b7 : R.string._2130846642_res_0x7f0223b2), "", getString(R.string._2130841536_res_0x7f020fc0)), new CompoundButton.OnCheckedChangeListener[0]);
        b(this.mGeneralList);
    }

    private void b(final List<obz> list) {
        jqi.a().getSwitchSetting("sleep_breathe_key", new IBaseResponseCallback() { // from class: oyq
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WearHomeHealthMonitoringActivity.this.d(list, i, obj);
            }
        });
    }

    public /* synthetic */ void d(final List list, int i, Object obj) {
        LogUtil.a("WearHomeBaseActivity", "changeSleepBreatheSwitch errorCode: ", Integer.valueOf(i));
        this.f9650a = "false";
        if (i == 0 && (obj instanceof String)) {
            this.f9650a = (String) obj;
            if ("1".equals(obj)) {
                this.f9650a = "true";
            }
        }
        jlj.a().a(this.f9650a);
        runOnUiThread(new Runnable() { // from class: oyv
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeHealthMonitoringActivity.this.a(list);
            }
        });
    }

    public /* synthetic */ void a(List list) {
        setSettingItemRightText(121, list, getString("true".equals(this.f9650a) ? R.string._2130841536_res_0x7f020fc0 : R.string._2130841535_res_0x7f020fbf));
        refreshGeneralDataAdapter();
    }

    private void b() {
        boolean c = cwi.c(this.mCurrentDeviceInfo, 114);
        LogUtil.a("WearHomeBaseActivity", "addMenstrualPredictItem isSupportMenstrualPredict: ", Boolean.valueOf(c));
        if (c) {
            String b = SharedPreferenceManager.b(this.mContext, Integer.toString(10100), "PHYSIOLOGICAL_SHOW_STATE_KEY");
            LogUtil.a("WearHomeBaseActivity", "addMenstrualPredictItem showState: ", b);
            if ("false".equals(b)) {
                return;
            }
            addGeneralSettingItem(this.mIsConnected, new ozj(R.drawable._2131430192_res_0x7f0b0b30, 68, 3), new ozh(getString(R.string._2130845716_res_0x7f022014), "", getString(R.string._2130841535_res_0x7f020fbf)), new CompoundButton.OnCheckedChangeListener[0]);
            g();
        }
    }

    private void g() {
        jqi.a().getSwitchSetting("menstrual_predict_switch", new IBaseResponseCallback() { // from class: oys
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WearHomeHealthMonitoringActivity.this.a(i, obj);
            }
        });
    }

    public /* synthetic */ void a(int i, Object obj) {
        LogUtil.a("WearHomeBaseActivity", "initMenstrualPredictStatus errorCode: ", Integer.valueOf(i));
        final String c = c((i == 0 && (obj instanceof String)) ? (String) obj : "0");
        LogUtil.a("WearHomeBaseActivity", "initMenstrualPredictStatus showStatus: ", c);
        runOnUiThread(new Runnable() { // from class: oyn
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeHealthMonitoringActivity.this.b(c);
            }
        });
    }

    public /* synthetic */ void b(String str) {
        getItem(this.mGeneralList, 68).e(str);
        refreshGeneralDataAdapter();
    }

    private void djR_(int i, Intent intent) {
        LogUtil.a("WearHomeBaseActivity", "showMenstrualPredictResultStatus resultCode: ", Integer.valueOf(i));
        if (i != -1) {
            return;
        }
        String stringExtra = intent != null ? intent.getStringExtra("menstrualPredictResultStatus") : "";
        LogUtil.a("WearHomeBaseActivity", "showMenstrualPredictResultStatus switchStatus: ", stringExtra);
        if ("1".equals(stringExtra)) {
            setSettingItemRightText(68, this.mGeneralList, getString(R.string._2130841536_res_0x7f020fc0));
        } else {
            setSettingItemRightText(68, this.mGeneralList, getString(R.string._2130841535_res_0x7f020fbf));
        }
    }

    private void s() {
        try {
            Intent intent = new Intent(this.mContext, (Class<?>) MenstrualPredictActivity.class);
            intent.putExtra("device_id", this.mDeviceMac);
            intent.putExtra("isFromHealthMonitoring", true);
            startActivityForResult(intent, 68);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("WearHomeBaseActivity", "startMenstrualPredictActivity ActivityNotFoundException.");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
