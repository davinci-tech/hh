package com.huawei.ui.main.stories.me.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.CompoundButton;
import androidx.core.content.ContextCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.me.activity.AutoIdentifyRecordActivity;
import com.huawei.ui.main.stories.me.util.AppSettingUtil;
import defpackage.cao;
import defpackage.gso;
import defpackage.guz;
import defpackage.ixx;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class AutoIdentifyRecordActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthSwitchButton f10331a;
    private AppSettingUtil c;
    private int d = 0;
    private guz e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_auto_identify_record);
        c();
        b();
        a();
    }

    private void c() {
        ((CustomTitleBar) findViewById(R.id.ctb_auto_identify_record)).setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        this.f10331a = (HealthSwitchButton) findViewById(R.id.hwb_auto_switch);
    }

    private void b() {
        e();
        guz d = gso.e().d(this);
        this.e = d;
        if (d.g()) {
            this.e.a(false);
        }
        this.c = new AppSettingUtil(this);
        this.e.d(this);
        d();
        if (this.e.f()) {
            return;
        }
        ((HealthTextView) findViewById(R.id.auto_identify_record_explain)).setText(R$string.IDS_auto_track_explain_run);
    }

    private void a() {
        this.f10331a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.me.activity.AutoIdentifyRecordActivity$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AutoIdentifyRecordActivity.this.dMW_(compoundButton, z);
            }
        });
    }

    /* synthetic */ void dMW_(CompoundButton compoundButton, final boolean z) {
        if (!this.f10331a.isPressed()) {
            ViewClickInstrumentation.clickOnView(compoundButton);
        } else {
            LoginInit.getInstance(this).browsingToLogin(new IBaseResponseCallback() { // from class: ren
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    AutoIdentifyRecordActivity.this.d(z, i, obj);
                }
            }, "");
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    }

    public /* synthetic */ void d(boolean z, int i, Object obj) {
        if (i == 0) {
            this.c.e(z, this.e, this.f10331a);
            cao.a(this, this.d, true, this.f10331a.isChecked(), (int) this.e.c());
        } else {
            this.f10331a.setChecked(!z);
            LogUtil.h("Track_AutoIdentifyRecordActivity", "errorCode is not success", Integer.valueOf(i));
        }
    }

    private void e() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Track_AutoIdentifyRecordActivity", "initIntentData intent is null");
        } else {
            e(intent.getIntExtra("auto_track_launch_source", 0));
        }
    }

    private void e(int i) {
        if (i == 0) {
            LogUtil.h("Track_AutoIdentifyRecordActivity", "setLaunchSourceBi launchSourceCode is error");
            return;
        }
        this.d = i;
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("from", Integer.valueOf(i));
        ixx.d().d(this, AnalyticsValue.HEALTH_AUTO_TRACK_ENTER_1040072.value(), hashMap, 0);
    }

    private void d() {
        this.f10331a.setChecked(this.e.e());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
