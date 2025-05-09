package com.huawei.ui.device.activity.notificationlive;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.device.interactors.NotificationPushInteractor;
import defpackage.jqi;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class NotificationLiveActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Boolean f9165a;
    private boolean b = false;
    private TextView c;
    private NotificationPushInteractor d;
    private TextView e;
    private HealthSwitchButton h;
    private HealthTextView j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_notification_live);
        Intent intent = getIntent();
        if (intent != null) {
            this.f9165a = Boolean.valueOf(intent.getBooleanExtra("LiveViewType", false));
        }
        LogUtil.a("NotificationLiveActivity", "LiveViewType: ", this.f9165a);
        c();
        this.d = new NotificationPushInteractor(this);
    }

    private void c() {
        this.h = (HealthSwitchButton) findViewById(R.id.notification_live_switch_btn);
        this.c = (TextView) findViewById(R.id.notification_live_detail);
        this.j = (HealthTextView) findViewById(R.id.notification_live_switch_note);
        this.e = (TextView) findViewById(R.id.notification_live_note);
        if (this.f9165a.booleanValue()) {
            this.c.setText(R.string._2130847548_res_0x7f02273c);
            this.j.setText(R.string._2130847547_res_0x7f02273b);
            this.e.setText(R.string._2130847549_res_0x7f02273d);
        } else {
            this.c.setText(R.string._2130846776_res_0x7f022438);
            this.j.setText(R.string._2130846777_res_0x7f022439);
            this.e.setText(R.string._2130847109_res_0x7f022585);
        }
        this.h.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.notificationlive.NotificationLiveActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ReleaseLogUtil.e("R_NotificationLiveActivity", "onCheckedChanged, isChecked is", Boolean.valueOf(z));
                NotificationLiveActivity.this.b = true;
                jqi.a().setSwitchSetting("notification_live_switch", z ? "true" : "false", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.notificationlive.NotificationLiveActivity.2.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        LogUtil.a("NotificationLiveActivity", "onCheckedChanged setSwitchSetting errorCode:", Integer.valueOf(i));
                    }
                });
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        b();
    }

    private void b() {
        this.h.setEnabled(this.d.b());
        this.b = false;
        jqi.a().getSwitchSetting("notification_live_switch", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.notificationlive.NotificationLiveActivity.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("R_NotificationLiveActivity", "updateView getSwitchSetting onResponse errorCode:", Integer.valueOf(i), " objData:", obj);
                final boolean z = true;
                if (i == 0 && (obj instanceof String)) {
                    z = true ^ "false".equals(obj);
                }
                NotificationLiveActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.notificationlive.NotificationLiveActivity.5.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (NotificationLiveActivity.this.b) {
                            return;
                        }
                        NotificationLiveActivity.this.h.setChecked(z);
                    }
                });
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
