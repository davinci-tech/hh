package com.huawei.ui.device.activity.adddevice;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.cun;
import defpackage.cwi;
import defpackage.jqi;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.oaz;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class DeviceAutoSwitchActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthSwitchButton f9017a;
    private Context b;
    private AnimationDrawable c;
    private CompoundButton.OnCheckedChangeListener d = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.adddevice.DeviceAutoSwitchActivity.2
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            LogUtil.a("DeviceAutoSwitchActivity", "device auto switch button is ", Boolean.valueOf(z));
            DeviceAutoSwitchActivity.this.f9017a.setChecked(z);
            DeviceAutoSwitchActivity.this.d(z);
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };
    private ImageView e;
    private HealthTextView j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("DeviceAutoSwitchActivity", "onCreate");
        this.b = this;
        setContentView(R.layout.activity_device_auto_switch);
        e();
        b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        f();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AnimationDrawable animationDrawable = this.c;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
    }

    private void e() {
        LogUtil.a("DeviceAutoSwitchActivity", "initView. ");
        ((CustomTitleBar) nsy.cMc_(this, R.id.device_auto_switch_bar)).setTitleText(BaseApplication.getContext().getString(R.string.IDS_device_auto_switch));
        ((HealthTextView) nsy.cMc_(this, R.id.device_auto_switch_tip)).setText(BaseApplication.getContext().getString(R.string.IDS_device_auto_switch_tip_title));
        ((HealthTextView) nsy.cMc_(this, R.id.device_auto_switch_button_name)).setText(BaseApplication.getContext().getString(R.string.IDS_device_auto_switch));
        this.f9017a = (HealthSwitchButton) findViewById(R.id.device_auto_switch_button);
        this.j = (HealthTextView) nsy.cMc_(this, R.id.device_auto_switch_support_list);
        this.e = (ImageView) nsy.cMc_(this, R.id.device_auto_switch_img);
        a();
    }

    private void b() {
        LogUtil.a("DeviceAutoSwitchActivity", "initData. ");
        String string = BaseApplication.getContext().getString(R.string.IDS_device_auto_switch_tip_support_list);
        this.j.setText(string + "\n" + c());
    }

    private void a() {
        if (oaz.d("autoSwitch")) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.device.activity.adddevice.DeviceAutoSwitchActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    DeviceAutoSwitchActivity.this.d();
                }
            });
            return;
        }
        LogUtil.a("DeviceAutoSwitchActivity", "initAutoSwitchView isExistResource is false.");
        cOm_(this.e, false);
        this.e.setScaleType(ImageView.ScaleType.MATRIX);
        this.e.setBackgroundResource(R.drawable._2131428622_res_0x7f0b050e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        ArrayList<Bitmap> e = oaz.e("autoSwitch");
        if (e == null || e.size() == 0) {
            LogUtil.a("DeviceAutoSwitchActivity", "initAutoSwitchAnimation bitmapList is empty.");
            runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.adddevice.DeviceAutoSwitchActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    DeviceAutoSwitchActivity deviceAutoSwitchActivity = DeviceAutoSwitchActivity.this;
                    deviceAutoSwitchActivity.cOm_(deviceAutoSwitchActivity.e, false);
                    DeviceAutoSwitchActivity.this.e.setScaleType(ImageView.ScaleType.MATRIX);
                    DeviceAutoSwitchActivity.this.e.setBackgroundResource(R.drawable._2131428622_res_0x7f0b050e);
                }
            });
            return;
        }
        this.c = new AnimationDrawable();
        for (int i = 0; i < e.size(); i++) {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(e.get(i));
            if (i == e.size() - 1) {
                this.c.addFrame(bitmapDrawable, 1000);
            } else {
                this.c.addFrame(bitmapDrawable, 100);
            }
        }
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.adddevice.DeviceAutoSwitchActivity.1
            @Override // java.lang.Runnable
            public void run() {
                DeviceAutoSwitchActivity deviceAutoSwitchActivity = DeviceAutoSwitchActivity.this;
                deviceAutoSwitchActivity.cOm_(deviceAutoSwitchActivity.e, true);
                DeviceAutoSwitchActivity.this.e.setImageDrawable(DeviceAutoSwitchActivity.this.c);
                DeviceAutoSwitchActivity.this.c.setOneShot(false);
                DeviceAutoSwitchActivity.this.c.start();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cOm_(View view, boolean z) {
        LinearLayout.LayoutParams layoutParams;
        if (z) {
            layoutParams = new LinearLayout.LayoutParams(nsn.c(this.b, 360.0f), nsn.c(this.b, 288.0f));
        } else {
            layoutParams = new LinearLayout.LayoutParams(nsn.c(this.b, 220.0f), nsn.c(this.b, 220.0f));
        }
        view.setLayoutParams(layoutParams);
    }

    private String c() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "DeviceAutoSwitchActivity");
        if (deviceList == null) {
            LogUtil.a("DeviceAutoSwitchActivity", "getDeviceList is null.");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (DeviceInfo deviceInfo : deviceList) {
            if (a(deviceInfo)) {
                sb.append(deviceInfo.getDeviceName());
                sb.append('\n');
            }
        }
        LogUtil.a("DeviceAutoSwitchActivity", "getDeviceList: ", Integer.valueOf(deviceList.size()), ",supportDeviceName: ", sb);
        return sb.toString();
    }

    private boolean a(DeviceInfo deviceInfo) {
        boolean c = cwi.c(deviceInfo, 109);
        LogUtil.a("DeviceAutoSwitchActivity", "isSupportDeviceAutoSwitch is:", Boolean.valueOf(c));
        return c;
    }

    private void f() {
        jqi.a().getSwitchSetting("device_auto_switch", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.adddevice.DeviceAutoSwitchActivity.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("DeviceAutoSwitchActivity", "AutoSwitch errorCode = ", Integer.valueOf(i));
                final boolean equals = (i == 0 && (obj instanceof String)) ? "1".equals((String) obj) : true;
                LogUtil.a("DeviceAutoSwitchActivity", "getAutoSwitchStatus():", Boolean.valueOf(equals));
                DeviceAutoSwitchActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.adddevice.DeviceAutoSwitchActivity.4.5
                    @Override // java.lang.Runnable
                    public void run() {
                        DeviceAutoSwitchActivity.this.f9017a.setChecked(equals);
                        DeviceAutoSwitchActivity.this.f9017a.setOnCheckedChangeListener(DeviceAutoSwitchActivity.this.d);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        String str = z ? "1" : "0";
        LogUtil.a("DeviceAutoSwitchActivity", "setAutoSwitchStatus():", str);
        jqi.a().setSwitchSetting("device_auto_switch", str, null);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
