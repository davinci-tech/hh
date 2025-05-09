package com.huawei.ui.homewear21.home;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import com.huawei.datatype.DataDeviceAvoidDisturbInfo;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.device.activity.donotdisturb.NoDisturbSettingActivity;
import com.huawei.ui.device.activity.doublephone.DoublePhoneActivity;
import com.huawei.ui.device.activity.leftrighthand.LeftRightHandSettingsActivity;
import com.huawei.ui.device.activity.onelevelmenu.OneLevelMenuManagerActivity;
import com.huawei.ui.device.interactors.NotificationPushInteractor;
import com.huawei.ui.homewear21.home.WearHomeOtherSettingActivity;
import defpackage.cwi;
import defpackage.drl;
import defpackage.ixx;
import defpackage.jfq;
import defpackage.jjd;
import defpackage.jpo;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.jwe;
import defpackage.nrh;
import defpackage.oae;
import defpackage.obz;
import defpackage.oxa;
import defpackage.ozh;
import defpackage.ozj;
import defpackage.peg;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class WearHomeOtherSettingActivity extends WearHomeBaseActivity {
    private NotificationPushInteractor d;
    private Handler i = new d(this);
    private final CompoundButton.OnCheckedChangeListener c = new AnonymousClass2();
    private final CompoundButton.OnCheckedChangeListener e = new AnonymousClass1();
    private final CompoundButton.OnCheckedChangeListener b = new AnonymousClass3();

    /* renamed from: a, reason: collision with root package name */
    private final CompoundButton.OnCheckedChangeListener f9652a = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homewear21.home.WearHomeOtherSettingActivity.4
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!WearHomeOtherSettingActivity.this.mIsClickScreen) {
                LogUtil.h("WearHomeOtherSettingActivity", "mMusicControllistener return");
                ViewClickInstrumentation.clickOnView(compoundButton);
                return;
            }
            LogUtil.a("WearHomeOtherSettingActivity", "MusicControl is onCheckedChanged isChecked is ", Boolean.valueOf(z));
            if (z && !WearHomeOtherSettingActivity.this.d.e()) {
                WearHomeOtherSettingActivity.this.startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
            }
            WearHomeOtherSettingActivity wearHomeOtherSettingActivity = WearHomeOtherSettingActivity.this;
            wearHomeOtherSettingActivity.getItem(wearHomeOtherSettingActivity.mGeneralList, 34).e(z);
            jjd.b(BaseApplication.getContext()).c(z, true);
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };

    /* renamed from: com.huawei.ui.homewear21.home.WearHomeOtherSettingActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements CompoundButton.OnCheckedChangeListener {
        AnonymousClass2() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(final CompoundButton compoundButton, final boolean z) {
            if (!WearHomeOtherSettingActivity.this.mIsClickScreen) {
                LogUtil.h("WearHomeOtherSettingActivity", "mRotateSwitchScreenListener return");
                ViewClickInstrumentation.clickOnView(compoundButton);
                return;
            }
            if (compoundButton == null) {
                ViewClickInstrumentation.clickOnView(compoundButton);
                return;
            }
            LogUtil.a("WearHomeOtherSettingActivity", "mRotateSwitchScreenListener onCheckedChanged isChecked is ", Boolean.valueOf(z));
            compoundButton.setClickable(false);
            WearHomeOtherSettingActivity wearHomeOtherSettingActivity = WearHomeOtherSettingActivity.this;
            wearHomeOtherSettingActivity.getItem(wearHomeOtherSettingActivity.mGeneralList, 25).e(z);
            jqi.a().sendSetSwitchSettingCmd(z, WearHomeOtherSettingActivity.this.mDeviceMac, 1, 27, new IBaseResponseCallback() { // from class: com.huawei.ui.homewear21.home.WearHomeOtherSettingActivity.2.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("WearHomeOtherSettingActivity", "mRotateSwitchScreenListener errorCode: ", Integer.valueOf(i), " ; objectData: ", obj);
                    if (i != 0) {
                        nrh.e(WearHomeOtherSettingActivity.this.mContext, R.string._2130841440_res_0x7f020f60);
                    }
                    compoundButton.setClickable(true);
                }
            });
            if (jpt.e(WearHomeOtherSettingActivity.this.mDeviceMac, "WearHomeOtherSettingActivity") != null) {
                jqi.a().setSwitchSetting("rotate_switch_screen", z ? "1" : "0", null);
            } else {
                LogUtil.h("WearHomeOtherSettingActivity", "mRotateSwitchScreenListener deviceInfo is null");
            }
            compoundButton.setOnClickListener(new View.OnClickListener() { // from class: ozv
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WearHomeOtherSettingActivity.AnonymousClass2.this.djW_(z, view);
                }
            });
            ViewClickInstrumentation.clickOnView(compoundButton);
        }

        public /* synthetic */ void djW_(boolean z, View view) {
            LogUtil.a("WearHomeOtherSettingActivity", "onClick: HOME_1010045");
            WearHomeOtherSettingActivity.this.c(z, AnalyticsValue.HOME_1010045.value());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* renamed from: com.huawei.ui.homewear21.home.WearHomeOtherSettingActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements CompoundButton.OnCheckedChangeListener {
        AnonymousClass1() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(final CompoundButton compoundButton, final boolean z) {
            if (!WearHomeOtherSettingActivity.this.mIsClickScreen) {
                LogUtil.h("WearHomeOtherSettingActivity", "mRotateWakeScreenListener return");
                ViewClickInstrumentation.clickOnView(compoundButton);
                return;
            }
            if (compoundButton == null) {
                ViewClickInstrumentation.clickOnView(compoundButton);
                return;
            }
            LogUtil.a("WearHomeOtherSettingActivity", "mRotateWakeScreenListener onCheckedChanged isChecked is ", Boolean.valueOf(z));
            compoundButton.setClickable(false);
            WearHomeOtherSettingActivity wearHomeOtherSettingActivity = WearHomeOtherSettingActivity.this;
            wearHomeOtherSettingActivity.getItem(wearHomeOtherSettingActivity.mGeneralList, 11).e(z);
            jqi.a().sendSetSwitchSettingCmd(z, WearHomeOtherSettingActivity.this.mDeviceMac, 1, 9, new IBaseResponseCallback() { // from class: com.huawei.ui.homewear21.home.WearHomeOtherSettingActivity.1.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("WearHomeOtherSettingActivity", "mRotateWakeScreenListener errorCode: ", Integer.valueOf(i), " ; objectData: ", obj);
                    if (i != 0) {
                        nrh.e(WearHomeOtherSettingActivity.this.mContext, R.string._2130841440_res_0x7f020f60);
                    }
                    compoundButton.setClickable(true);
                }
            });
            if (!WearHomeOtherSettingActivity.this.e("auto_light_screen", z)) {
                if (jpt.e(WearHomeOtherSettingActivity.this.mDeviceMac, "WearHomeOtherSettingActivity") != null) {
                    jqi.a().setSwitchSetting("auto_light_screen", z ? "1" : "0", null);
                } else {
                    LogUtil.h("WearHomeOtherSettingActivity", "mRotateWakeScreenListener deviceInfo is null");
                }
            }
            compoundButton.setOnClickListener(new View.OnClickListener() { // from class: ozy
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WearHomeOtherSettingActivity.AnonymousClass1.this.djX_(z, view);
                }
            });
            ViewClickInstrumentation.clickOnView(compoundButton);
        }

        public /* synthetic */ void djX_(boolean z, View view) {
            LogUtil.a("WearHomeOtherSettingActivity", "onClick: HOME_1010025");
            WearHomeOtherSettingActivity.this.c(z, AnalyticsValue.HOME_1010025.value());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* renamed from: com.huawei.ui.homewear21.home.WearHomeOtherSettingActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements CompoundButton.OnCheckedChangeListener {
        AnonymousClass3() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(final CompoundButton compoundButton, final boolean z) {
            if (!WearHomeOtherSettingActivity.this.mIsClickScreen) {
                LogUtil.h("WearHomeOtherSettingActivity", "mBluetoothOffAlertListener return");
                ViewClickInstrumentation.clickOnView(compoundButton);
            } else {
                if (compoundButton == null) {
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                }
                LogUtil.a("WearHomeOtherSettingActivity", "mBluetoothOffAlertListener onCheckedChanged isChecked is ", Boolean.valueOf(z));
                compoundButton.setClickable(false);
                WearHomeOtherSettingActivity wearHomeOtherSettingActivity = WearHomeOtherSettingActivity.this;
                wearHomeOtherSettingActivity.getItem(wearHomeOtherSettingActivity.mGeneralList, 2).e(z);
                WearHomeOtherSettingActivity.this.mDeviceInteractors.e(WearHomeOtherSettingActivity.this.mDeviceMac, z, new IBaseResponseCallback() { // from class: com.huawei.ui.homewear21.home.WearHomeOtherSettingActivity.3.2
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        if (i != 0) {
                            nrh.e(WearHomeOtherSettingActivity.this.mContext, R.string._2130841440_res_0x7f020f60);
                        }
                        compoundButton.setClickable(true);
                        LogUtil.a("WearHomeOtherSettingActivity", "mBluetoothOffAlertListener errorCode：", Integer.valueOf(i), ",objectData is ", obj);
                    }
                });
                compoundButton.setOnClickListener(new View.OnClickListener() { // from class: ozx
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        WearHomeOtherSettingActivity.AnonymousClass3.this.djY_(z, view);
                    }
                });
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        }

        public /* synthetic */ void djY_(boolean z, View view) {
            LogUtil.a("WearHomeOtherSettingActivity", "onClick: HOME_1010024");
            WearHomeOtherSettingActivity.this.c(z, AnalyticsValue.HOME_1010024.value());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z, String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (z) {
            hashMap.put("status", "1");
        } else {
            hashMap.put("status", "0");
        }
        ixx.d().d(BaseApplication.getContext(), str, hashMap, 0);
    }

    @Override // com.huawei.ui.homewear21.home.WearHomeBaseActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("WearHomeOtherSettingActivity", "onCreate");
        this.mCustomTitleBar.setTitleText(BaseApplication.getContext().getString(R.string.IDS_device_wear_home_device_setting));
        this.d = new NotificationPushInteractor(this.mApplicationContext);
    }

    @Override // com.huawei.ui.homewear21.home.WearHomeBaseActivity
    protected void initGeneralList() {
        this.mGeneralList.clear();
        if (this.mDeviceCapability.isAvoidDisturb()) {
            addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820772_res_0x7f1100e4, 5, 3), new ozh(getString(R.string._2130841475_res_0x7f020f83), "", ""), new CompoundButton.OnCheckedChangeListener[0]);
            h();
            refreshGeneralDataAdapter();
        }
        if (this.mDeviceCapability.isSupportPhonesInfo() && this.mDeviceCapability.isSupportNotifyDeviceBroadCast()) {
            addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820776_res_0x7f1100e8, 37, 3), new ozh(getString(R.string._2130842817_res_0x7f0214c1), "", ""), new CompoundButton.OnCheckedChangeListener[0]);
        }
        if (this.mDeviceCapability.isSupportMusicControl() && !this.mDeviceCapability.isSupportMusicInfoList()) {
            f();
        }
        if (this.mDeviceCapability.isBluetoothOffAlert()) {
            c();
        }
        if (this.mDeviceCapability.isSupportOneLevelMenu()) {
            addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820775_res_0x7f1100e7, 28, 3), new ozh(getString(R.string._2130842257_res_0x7f021291), "", ""), new CompoundButton.OnCheckedChangeListener[0]);
        }
        if (this.mDeviceCapability.isSupportAutoLightScreen()) {
            b();
        }
        if (this.mDeviceCapability.isSupportRotateSwitchScreen()) {
            j();
        }
        if (this.mDeviceCapability.isSupportLeftRightHandWearMode()) {
            i();
        }
        if (peg.b()) {
            addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820771_res_0x7f1100e3, 50, 3), new ozh(getString(R.string._2130844150_res_0x7f0219f6), "", ""), new CompoundButton.OnCheckedChangeListener[0]);
        }
        boolean d2 = jwe.e().d(this.mCurrentDeviceInfo);
        LogUtil.a("WearHomeOtherSettingActivity", "isShowSmartGestureItem: ", Boolean.valueOf(d2));
        if (d2) {
            addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131821110_res_0x7f110236, OldToNewMotionPath.SPORT_TYPE_TENNIS, 3), new ozh(getString(R.string._2130847542_res_0x7f022736), "", ""), new CompoundButton.OnCheckedChangeListener[0]);
        }
        a();
    }

    private void a() {
        boolean isSupportEcgAuth = this.mDeviceCapability != null ? this.mDeviceCapability.isSupportEcgAuth() : false;
        boolean z = cwi.c(this.mCurrentDeviceInfo, 106) && drl.c("com.huawei.health_deviceFeature_config", "txt", "com.huawei.health.h5.ecgce");
        boolean o = Utils.o();
        LogUtil.a("WearHomeOtherSettingActivity", "showAccessibilityItem isSupportEcgAuth:", Boolean.valueOf(isSupportEcgAuth), ", isSupportEcgAnalysis:", Boolean.valueOf(z), ", isOversea:", Boolean.valueOf(o));
        if (z && isSupportEcgAuth && !o) {
            addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131821108_res_0x7f110234, 70, 3), new ozh(getString(R.string._2130845723_res_0x7f02201b), "", ""), new CompoundButton.OnCheckedChangeListener[0]);
        }
    }

    private void f() {
        addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820774_res_0x7f1100e6, 34, 1), new ozh(getString(R.string._2130842661_res_0x7f021425), "", ""), this.f9652a);
        boolean c = jjd.b(BaseApplication.getContext()).c();
        getItem(this.mGeneralList, 34).e(c);
        jjd.b(BaseApplication.getContext()).c(c, false);
    }

    private void i() {
        addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820924_res_0x7f11017c, 26, 3), new ozh(getString(R.string._2130842262_res_0x7f021296), "", getString(R.string._2130842263_res_0x7f021297)), new CompoundButton.OnCheckedChangeListener[0]);
        d(this.mGeneralList);
    }

    private void d(final List<obz> list) {
        jqi.a().getSwitchSetting("left_or_right_hand_wear_status", new IBaseResponseCallback() { // from class: ozp
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WearHomeOtherSettingActivity.this.e(list, i, obj);
            }
        });
    }

    public /* synthetic */ void e(final List list, int i, Object obj) {
        final String string = getString(R.string._2130842263_res_0x7f021297);
        LogUtil.a("WearHomeOtherSettingActivity", "LEFT_OR_RIGHT_HAND_WEAR_STATUS errorCode: ", Integer.valueOf(i), " ; objectData: ", obj);
        if (i == 0 && (obj instanceof String) && "1".equals((String) obj)) {
            string = getString(R.string._2130842264_res_0x7f021298);
        }
        LogUtil.a("WearHomeOtherSettingActivity", "wearStatus: ", string);
        runOnUiThread(new Runnable() { // from class: ozl
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeOtherSettingActivity.this.d(list, string);
            }
        });
    }

    public /* synthetic */ void d(List list, String str) {
        getItem(list, 26).e(str);
        refreshGeneralDataAdapter();
    }

    @Override // com.huawei.ui.homewear21.home.WearHomeBaseActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.mDeviceCapability == null) {
            LogUtil.h("WearHomeOtherSettingActivity", "onResume() mDeviceCapability is null return !");
        } else if (CommonUtil.ar() && this.mDeviceCapability.isSupportSyncWifi()) {
            e();
        }
    }

    @Override // com.huawei.ui.homewear21.home.WearHomeBaseActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.i;
        if (handler != null) {
            handler.removeMessages(100);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 5) {
            h();
            return;
        }
        if (i == 26) {
            d(this.mGeneralList);
            return;
        }
        if (i != 37) {
            LogUtil.h("WearHomeOtherSettingActivity", "onActivityResult else branch");
            return;
        }
        if (i2 != -1) {
            return;
        }
        if (this.mCurrentDeviceInfo == null) {
            LogUtil.h("WearHomeOtherSettingActivity", "mCurrentDeviceInfo is null.");
            return;
        }
        LogUtil.c("WearHomeOtherSettingActivity", "ready reconnect double phone");
        int deviceConnectState = this.mCurrentDeviceInfo.getDeviceConnectState();
        if (deviceConnectState == 2 || deviceConnectState == 1) {
            return;
        }
        LogUtil.a("WearHomeOtherSettingActivity", "begin connect in ui , name:", this.mCurrentDeviceInfo.getDeviceName());
        oxa.a().e(oxa.a().i(), this.mCurrentDeviceInfo.getDeviceIdentify());
    }

    @Override // com.huawei.ui.homewear21.home.WearHomeBaseActivity
    protected void setItemClick(int i) {
        LogUtil.a("WearHomeOtherSettingActivity", "setItemClick index: ", Integer.valueOf(i), ";size: ", Integer.valueOf(this.mGeneralList.size()));
        if (i >= this.mGeneralList.size()) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        int e = this.mGeneralList.get(i).e();
        if (e == 5) {
            LogUtil.a("WearHomeOtherSettingActivity", "startNoDisturbSettingActivity");
            Intent intent = new Intent(this, (Class<?>) NoDisturbSettingActivity.class);
            intent.putExtra("device_id", this.mDeviceMac);
            startActivityForResult(intent, 5);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010029.value(), hashMap, 0);
            return;
        }
        if (e == 26) {
            LogUtil.a("WearHomeOtherSettingActivity", "startActivity LeftRightHandSettingsActivity");
            startActivity(LeftRightHandSettingsActivity.class, 26);
            return;
        }
        if (e == 28) {
            LogUtil.a("WearHomeOtherSettingActivity", "OneLevelMenuManagerActivity");
            Intent intent2 = new Intent(this, (Class<?>) OneLevelMenuManagerActivity.class);
            intent2.putExtra("device_id", this.mDeviceMac);
            startActivity(intent2);
            return;
        }
        if (e == 37) {
            startActivityForResult(new Intent(this, (Class<?>) DoublePhoneActivity.class), 37);
            return;
        }
        if (e == 50) {
            Intent intent3 = new Intent(this, (Class<?>) WearHomeDataSyncSwitchActivity.class);
            intent3.putExtra("device_id", this.mDeviceMac);
            startActivity(intent3);
        } else {
            if (e == 130) {
                LogUtil.a("WearHomeOtherSettingActivity", "startActivity SmartGesturesActivity");
                Intent intent4 = new Intent(this, (Class<?>) SmartGestureActivity.class);
                intent4.putExtra("device_id", this.mDeviceMac);
                startActivity(intent4);
                return;
            }
            LogUtil.h("WearHomeOtherSettingActivity", "setItemClick else branch");
            b(e);
        }
    }

    private void b(int i) {
        if (i == 70) {
            Intent intent = new Intent(this, (Class<?>) WearHomeAccessibilityActivity.class);
            intent.putExtra("device_id", this.mDeviceMac);
            startActivity(intent);
            return;
        }
        LogUtil.h("WearHomeOtherSettingActivity", "setItemClick else branch");
    }

    private void j() {
        addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820778_res_0x7f1100ea, 25, 1), new ozh(getString(R.string._2130842258_res_0x7f021292), "", ""), this.c);
        j(this.mGeneralList);
    }

    private void j(final List<obz> list) {
        jqi.a().getSwitchSetting("rotate_switch_screen", new IBaseResponseCallback() { // from class: ozq
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WearHomeOtherSettingActivity.this.d(list, i, obj);
            }
        });
    }

    public /* synthetic */ void d(final List list, int i, Object obj) {
        Object[] objArr = new Object[4];
        final boolean z = false;
        objArr[0] = "rotateSwitch errorCode: ";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = " ; objectData: ";
        objArr[3] = obj == null ? Constants.NULL : obj;
        LogUtil.a("WearHomeOtherSettingActivity", objArr);
        if (i == 0 && (obj instanceof String)) {
            z = !"0".equals((String) obj);
        }
        LogUtil.a("WearHomeOtherSettingActivity", "rotateSwitch isEnable: ", Boolean.valueOf(z));
        runOnUiThread(new Runnable() { // from class: ozm
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeOtherSettingActivity.this.e(list, z);
            }
        });
    }

    public /* synthetic */ void e(List list, boolean z) {
        getItem(list, 25).e(z);
        refreshGeneralDataAdapter();
    }

    private void b() {
        addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820769_res_0x7f1100e1, 11, 1), new ozh(getString(R.string._2130841485_res_0x7f020f8d), getString(R.string._2130842268_res_0x7f02129c), ""), this.e);
        e(this.mGeneralList);
    }

    private void e(final List<obz> list) {
        if (d("auto_light_screen", 11)) {
            return;
        }
        jqi.a().getSwitchSetting("auto_light_screen", new IBaseResponseCallback() { // from class: ozs
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WearHomeOtherSettingActivity.this.b(list, i, obj);
            }
        });
    }

    public /* synthetic */ void b(final List list, int i, Object obj) {
        ReleaseLogUtil.e("DEVMGR_WearHomeOtherSettingActivity", "lightScreenSwitch errorCode: ", Integer.valueOf(i), " ; objectData: ", obj);
        final boolean z = true;
        if (i == 0 && (obj instanceof String)) {
            z = true ^ "0".equals((String) obj);
        }
        LogUtil.a("WearHomeOtherSettingActivity", "lightScreenSwitch isEnable = ", Boolean.valueOf(z));
        runOnUiThread(new Runnable() { // from class: ozu
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeOtherSettingActivity.this.d(list, z);
            }
        });
    }

    public /* synthetic */ void d(List list, boolean z) {
        getItem(list, 11).e(z);
        refreshGeneralDataAdapter();
    }

    private void c() {
        addGeneralSettingItem(this.mIsConnected, new ozj(R.mipmap._2131820770_res_0x7f1100e2, 2, 1), new ozh(getString(R.string._2130841483_res_0x7f020f8b), "", ""), this.b);
        LogUtil.a("WearHomeOtherSettingActivity", "showBtDisconnectItem.getWearCommonSetting");
        a(this.mGeneralList);
    }

    private void a(final List<obz> list) {
        if (d("bt_lost_remind", 2)) {
            return;
        }
        jqi.a().getSwitchSetting("bt_lost_remind", new IBaseResponseCallback() { // from class: ozn
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WearHomeOtherSettingActivity.this.c(list, i, obj);
            }
        });
    }

    public /* synthetic */ void c(final List list, int i, Object obj) {
        LogUtil.a("WearHomeOtherSettingActivity", "changeLostRemind errorCode is ", Integer.valueOf(i), " ; objectData is ", obj);
        final boolean z = (i == 0 && (obj instanceof String)) ? !"0".equals((String) obj) : false;
        LogUtil.a("WearHomeOtherSettingActivity", "changeLostRemind isEnable:", Boolean.valueOf(z));
        runOnUiThread(new Runnable() { // from class: ozr
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeOtherSettingActivity.this.c(list, z);
            }
        });
    }

    public /* synthetic */ void c(List list, boolean z) {
        getItem(list, 2).e(z);
        refreshGeneralDataAdapter();
    }

    private void h() {
        LogUtil.a("WearHomeOtherSettingActivity", "updateNoDisturb()");
        this.mDeviceInteractors.a(BaseApplication.getContext(), new IBaseResponseCallback() { // from class: ozk
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                WearHomeOtherSettingActivity.this.c(i, obj);
            }
        });
    }

    public /* synthetic */ void c(int i, Object obj) {
        if (obj == null) {
            LogUtil.h("WearHomeOtherSettingActivity", "updateNoDisturb objectData is null.");
            return;
        }
        final List list = (List) obj;
        if (list.isEmpty()) {
            list.add(new DataDeviceAvoidDisturbInfo());
        }
        runOnUiThread(new Runnable() { // from class: ozw
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeOtherSettingActivity.this.c(list);
            }
        });
    }

    public /* synthetic */ void c(List list) {
        b((List<DataDeviceAvoidDisturbInfo>) list);
    }

    private void b(List<DataDeviceAvoidDisturbInfo> list) {
        if (list == null || list.size() == 0) {
            LogUtil.h("WearHomeOtherSettingActivity", "list is null or size is 0");
            return;
        }
        DataDeviceAvoidDisturbInfo dataDeviceAvoidDisturbInfo = list.get(0);
        LogUtil.a("WearHomeOtherSettingActivity", "handleNoDisturbStatus() switch is ", Integer.valueOf(dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbSwitch()));
        if (dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbSwitch() == 0 && dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbTimeSwitch() == 0) {
            setSettingItemRightText(5, this.mGeneralList, this.mApplicationContext.getString(R.string._2130841535_res_0x7f020fbf));
        } else if (dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbSwitch() != 0) {
            setSettingItemRightText(5, this.mGeneralList, this.mApplicationContext.getString(R.string._2130841536_res_0x7f020fc0));
        } else if (dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbSwitch() == 0 && dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbTimeSwitch() != 0) {
            setSettingItemRightText(5, this.mGeneralList, this.mDeviceInteractors.b(this.mContext, dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbStartTime(), dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbEndTime()));
        } else {
            LogUtil.h("WearHomeOtherSettingActivity", "dataDeviceAvoidDisturbInfo.getDevice_avoid_disturb_switch() else");
        }
        c(dataDeviceAvoidDisturbInfo);
    }

    private void c(DataDeviceAvoidDisturbInfo dataDeviceAvoidDisturbInfo) {
        LogUtil.a("WearHomeOtherSettingActivity", "Enter updateDeviceSettingRotate()!");
        this.mDeviceCapability = this.mDeviceInteractors.e(this.mDeviceMac);
        if (this.mDeviceCapability == null) {
            LogUtil.h("WearHomeOtherSettingActivity", "updateDeviceSettingRotate() mDeviceCapability is null return !");
            return;
        }
        if (!this.mDeviceCapability.isSupportAutoLightScreen()) {
            LogUtil.h("WearHomeOtherSettingActivity", "updateDeviceSettingRotate() ", "mDeviceCapability not support isAuto_light_screen return !");
            return;
        }
        oae c = oae.c(this.mApplicationContext);
        int d2 = (c == null || !this.mDeviceCapability.isSupportQueryAllowDisturbContent()) ? 0 : c.d(this.mDeviceMac);
        LogUtil.a("WearHomeOtherSettingActivity", "updateDeviceSettingRotate() allowContent is ", Integer.valueOf(d2));
        if ((d2 & 20) == 20) {
            if ((dataDeviceAvoidDisturbInfo.getDeviceAvoidDisturbType() & 20) == 20) {
                getItem(this.mGeneralList, 11).d((String) null);
                return;
            } else {
                getItem(this.mGeneralList, 11).d(this.mApplicationContext.getString(R.string._2130842268_res_0x7f02129c));
                return;
            }
        }
        getItem(this.mGeneralList, 11).d(this.mApplicationContext.getString(R.string._2130842268_res_0x7f02129c));
    }

    private void e() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ozo
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeOtherSettingActivity.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("sync_wifi_toggle_status");
        if (userPreference == null) {
            HiHealthManager.d(BaseApplication.getContext()).setUserPreference(new HiUserPreference("sync_wifi_toggle_status", "1"), true);
            userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("sync_wifi_toggle_status");
        }
        if (userPreference != null) {
            this.i.sendMessage(this.i.obtainMessage(100, userPreference));
        }
    }

    static class d extends BaseHandler<WearHomeOtherSettingActivity> {
        d(WearHomeOtherSettingActivity wearHomeOtherSettingActivity) {
            super(wearHomeOtherSettingActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: djZ_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(WearHomeOtherSettingActivity wearHomeOtherSettingActivity, Message message) {
            if (message == null) {
                LogUtil.h("WearHomeOtherSettingActivity", "handleMessageWhenReferenceNotNull msg is null");
            } else if (message.what == 100) {
                Object obj = message.obj;
                if (obj instanceof HiUserPreference) {
                    wearHomeOtherSettingActivity.e((HiUserPreference) obj);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HiUserPreference hiUserPreference) {
        if (hiUserPreference == null) {
            LogUtil.h("WearHomeOtherSettingActivity", "show sync wifi toggle item param HiUserPreference is null");
            return;
        }
        String value = hiUserPreference.getValue();
        if (value != null) {
            value = value.trim();
        }
        c(!"0".equals(value));
    }

    private void c(boolean z) {
        String string;
        ozj ozjVar = new ozj(R.mipmap._2131820925_res_0x7f11017d, 47, 0);
        if (Utils.o()) {
            string = getString(R.string._2130844016_res_0x7f021970);
        } else {
            string = getString(R.string._2130844015_res_0x7f02196f);
        }
        ozh ozhVar = new ozh(string, getString(R.string._2130844017_res_0x7f021971), "");
        boolean z2 = this.mIsConnected;
        if (Utils.m() && !Utils.o()) {
            e(true);
            z2 = false;
            z = true;
        }
        addGeneralSettingItem(z2, ozjVar, ozhVar, new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homewear21.home.WearHomeOtherSettingActivity$$ExternalSyntheticLambda5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z3) {
                WearHomeOtherSettingActivity.this.djV_(compoundButton, z3);
            }
        });
        getItem(this.mGeneralList, 47).e(z);
        refreshGeneralDataAdapter();
    }

    /* synthetic */ void djV_(CompoundButton compoundButton, boolean z) {
        e(z);
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    private void e(final boolean z) {
        LogUtil.a("WearHomeOtherSettingActivity", "click sync wifi toggle in the WearHomeOtherSettingActivity");
        ThreadPoolManager.d().execute(new Runnable() { // from class: ozt
            @Override // java.lang.Runnable
            public final void run() {
                WearHomeOtherSettingActivity.b(z);
            }
        });
    }

    public static /* synthetic */ void b(boolean z) {
        HiUserPreference hiUserPreference;
        if (z) {
            hiUserPreference = new HiUserPreference("sync_wifi_toggle_status", "1");
        } else {
            hiUserPreference = new HiUserPreference("sync_wifi_toggle_status", "0");
        }
        HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference, true);
        if (z) {
            LogUtil.a("WearHomeOtherSettingActivity", "click sync wifi toggle and send wifi data in main process");
            jfq.c().g();
        } else {
            LogUtil.a("WearHomeOtherSettingActivity", "click sync wifi toggle is closed");
        }
    }

    private boolean d(String str, int i) {
        boolean z = false;
        if (jpo.c(this.mDeviceMac) != 2) {
            return false;
        }
        String b = SharedPreferenceManager.b(this.mContext, String.valueOf(10008), str);
        if (TextUtils.isEmpty(b)) {
            if (!"bt_lost_remind".equals(str)) {
                z = true;
            }
        } else {
            z = Boolean.parseBoolean(b);
        }
        getItem(this.mGeneralList, i).e(z);
        refreshGeneralDataAdapter();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(String str, boolean z) {
        if (jpo.c(this.mDeviceMac) != 2) {
            return false;
        }
        SharedPreferenceManager.e(this.mContext, String.valueOf(10008), str, Boolean.toString(z), (StorageParams) null);
        return true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
