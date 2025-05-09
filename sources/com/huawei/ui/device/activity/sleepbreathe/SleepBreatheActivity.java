package com.huawei.ui.device.activity.sleepbreathe;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.CompoundButton;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.activity.HealthMonitoringBaseActivity;
import com.huawei.ui.device.activity.sleepbreathe.SleepBreatheActivity;
import defpackage.jho;
import defpackage.jlj;
import defpackage.jqi;
import defpackage.jqp;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsn;

/* loaded from: classes.dex */
public class SleepBreatheActivity extends HealthMonitoringBaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f9225a;
    private jqi b;
    private CompoundButton.OnCheckedChangeListener c = new AnonymousClass4();
    private HealthTextView d;
    private String e;
    private HealthTextView f;
    private HealthSwitchButton j;

    /* renamed from: com.huawei.ui.device.activity.sleepbreathe.SleepBreatheActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements CompoundButton.OnCheckedChangeListener {
        AnonymousClass4() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            String str;
            LogUtil.a("SleepBreatheActivity", "mSleepBreatheSwitchListener isChecked: ", Boolean.valueOf(z));
            if (z) {
                SleepBreatheActivity.this.b.getSwitchSetting("core_sleep_button", new IBaseResponseCallback() { // from class: nye
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        SleepBreatheActivity.AnonymousClass4.this.a(i, obj);
                    }
                });
                str = "true";
            } else {
                str = "false";
            }
            jlj.a().d(str);
            ViewClickInstrumentation.clickOnView(compoundButton);
        }

        public /* synthetic */ void a(int i, Object obj) {
            LogUtil.a("SleepBreatheActivity", "getCoreSleepSwitch errorCode: ", Integer.valueOf(i));
            if (i == 0 && "1".equals(obj)) {
                return;
            }
            SleepBreatheActivity.this.b.setSwitchSetting("core_sleep_button", "1", new IBaseResponseCallback() { // from class: nyd
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj2) {
                    LogUtil.a("SleepBreatheActivity", "setCoreSleepSwitch errorCode: ", Integer.valueOf(i2));
                }
            });
            jqp.a("1");
            jho.e(1);
            SleepBreatheActivity.this.runOnUiThread(new Runnable() { // from class: nyf
                @Override // java.lang.Runnable
                public final void run() {
                    SleepBreatheActivity.AnonymousClass4.this.d();
                }
            });
        }

        public /* synthetic */ void d() {
            nrh.d(SleepBreatheActivity.this.getApplicationContext(), SleepBreatheActivity.this.getString(jlj.a().d() ? R.string._2130847158_res_0x7f0225b6 : R.string._2130846641_res_0x7f0223b1));
        }
    }

    @Override // com.huawei.ui.device.activity.HealthMonitoringBaseActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sleep_breathe);
        Intent intent = getIntent();
        if (intent != null) {
            this.e = intent.getStringExtra("SleepBreatheEnable");
        }
        LogUtil.a("SleepBreatheActivity", "mSleepBreatheSwitch: ", this.e);
        a();
        b();
    }

    private void a() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.setting_device_title_bar);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.sleep_breathe_switch_text);
        if (jlj.a().d()) {
            customTitleBar.setTitleText(getString(R.string._2130847159_res_0x7f0225b7));
            healthTextView.setText(R.string._2130847159_res_0x7f0225b7);
        }
        this.j = (HealthSwitchButton) findViewById(R.id.event_sleep_breathe_switch_btn);
        this.f9225a = (HealthTextView) findViewById(R.id.tip_1);
        this.d = (HealthTextView) findViewById(R.id.tip_2);
        this.f = (HealthTextView) findViewById(R.id.tip_3);
        e((HealthImageView) findViewById(R.id.sleep_breathe_image));
    }

    private void e(HealthImageView healthImageView) {
        int min = (int) (Math.min(nsn.j() / 2, nsn.n()) * 0.8d);
        healthImageView.getLayoutParams().height = min;
        healthImageView.getLayoutParams().width = min;
        healthImageView.setImageBitmap(nrf.cJL_(nrf.cHN_(R.mipmap._2131821119_res_0x7f11023f, BaseApplication.getContext()), healthImageView));
    }

    private void b() {
        this.b = jqi.a();
        this.f9225a.setText(getResources().getString(R.string._2130845626_res_0x7f021fba, 1));
        this.d.setText(getResources().getString(R.string._2130845627_res_0x7f021fbb, 2));
        this.f.setText(getResources().getString(R.string._2130845628_res_0x7f021fbc, 3));
        if ("true".equals(this.e) || "false".equals(this.e)) {
            d();
        } else {
            this.b.getSwitchSetting("sleep_breathe_key", new IBaseResponseCallback() { // from class: nya
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    SleepBreatheActivity.this.a(i, obj);
                }
            });
        }
    }

    public /* synthetic */ void a(int i, Object obj) {
        LogUtil.a("SleepBreatheActivity", "changeSleepBreatheSwitch errorCode: ", Integer.valueOf(i));
        this.e = "false";
        if (i == 0 && (obj instanceof String)) {
            this.e = (String) obj;
            if ("1".equals(obj)) {
                this.e = "true";
            }
        }
        runOnUiThread(new Runnable() { // from class: nxz
            @Override // java.lang.Runnable
            public final void run() {
                SleepBreatheActivity.this.d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void d() {
        jlj.a().d(this.e);
        this.j.setChecked(Boolean.parseBoolean(this.e));
        this.j.setOnCheckedChangeListener(this.c);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
