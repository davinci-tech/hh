package com.huawei.ui.device.activity.leftrighthand;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import defpackage.jqi;
import defpackage.nrh;
import defpackage.nsy;
import defpackage.oae;

/* loaded from: classes6.dex */
public class LeftRightHandSettingsActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f9108a;
    private jqi b;
    private boolean c = false;
    private HealthRadioButton d;
    private HealthRadioButton e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_left_right_hand_setting);
        this.f9108a = this;
        this.b = jqi.a();
        this.e = (HealthRadioButton) nsy.cMc_(this, R.id.settings_left_btn);
        this.d = (HealthRadioButton) nsy.cMc_(this, R.id.settings_right_btn);
        this.e.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.b.getSwitchSetting("left_or_right_hand_wear_status", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.leftrighthand.LeftRightHandSettingsActivity.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("LeftRightHandSettingsActivity", "LEFT_OR_RIGHT_HAND_WEAR_STATUS errorCode = ", Integer.valueOf(i), " ; objectData = ", obj);
                if (i == 0 && (obj instanceof String) && "1".equals((String) obj)) {
                    LeftRightHandSettingsActivity.this.c = true;
                }
                LogUtil.a("LeftRightHandSettingsActivity", "mWearState = ", Boolean.valueOf(LeftRightHandSettingsActivity.this.c));
                LeftRightHandSettingsActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.leftrighthand.LeftRightHandSettingsActivity.2.3
                    @Override // java.lang.Runnable
                    public void run() {
                        LeftRightHandSettingsActivity.this.e(LeftRightHandSettingsActivity.this.c);
                    }
                });
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("LeftRightHandSettingsActivity", "onResume()");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.settings_left_btn == id) {
            if (this.c) {
                this.c = false;
                e(false);
                d(this.c);
            }
        } else if (R.id.settings_right_btn == id) {
            if (!this.c) {
                this.c = true;
                e(true);
                d(this.c);
            }
        } else {
            LogUtil.a("LeftRightHandSettingsActivity", "ignore onclick id = ", Integer.valueOf(view.getId()));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(boolean z) {
        LogUtil.a("LeftRightHandSettingsActivity", "clickLeftOrRightBtnProcess isStateOpen = ", Boolean.valueOf(z));
        if (oae.c(this.f9108a).d() != 2) {
            LogUtil.a("LeftRightHandSettingsActivity", "showNoConnectedToast()");
            nrh.b(this.f9108a, R.string.IDS_device_not_connect);
        }
        this.b.sendSetSwitchSettingCmd(z, "", 1, 26, null);
        this.b.setSwitchSetting("left_or_right_hand_wear_status", z ? "1" : "0", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        if (!z) {
            this.e.setChecked(true);
            this.d.setChecked(false);
        } else {
            this.e.setChecked(false);
            this.d.setChecked(true);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("LeftRightHandSettingsActivity", "onDestroy()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
