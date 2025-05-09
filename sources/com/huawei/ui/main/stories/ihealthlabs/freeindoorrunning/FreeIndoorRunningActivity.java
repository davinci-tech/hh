package com.huawei.ui.main.stories.ihealthlabs.freeindoorrunning;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.main.stories.ihealthlabs.freeindoorrunning.FreeIndoorRunningActivity;
import defpackage.gso;
import defpackage.guw;
import defpackage.ixx;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class FreeIndoorRunningActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f10321a;
    private Context b;
    private HealthButton c;
    private ImageView d;
    private HealthSwitchButton e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("FreeIndoorRunningActivity", "onCreate");
        setContentView(R.layout.activity_ihealth_free_indoor_running);
        this.b = BaseApplication.getContext();
        d();
    }

    private void d() {
        this.d = (ImageView) findViewById(R.id.free_indoor_running_background);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.free_indoor_running_background_layout);
        this.f10321a = linearLayout;
        nsn.cLA_(linearLayout, 2);
        HealthButton healthButton = (HealthButton) findViewById(R.id.experience_now);
        this.c = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: rec
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FreeIndoorRunningActivity.this.dMH_(view);
            }
        });
        this.e = (HealthSwitchButton) nsy.cMc_(this, R.id.switch_free_indoor_running);
        String b = SharedPreferenceManager.b(this.b, Integer.toString(20002), "ihealthlabs");
        LogUtil.a("FreeIndoorRunningActivity", "isOpenIndoorRunning is ", b);
        if (b != null && "true".equals(b)) {
            this.e.setChecked(true);
            this.c.setAlpha(1.0f);
            this.c.setClickable(true);
        } else {
            this.e.setChecked(false);
            this.c.setAlpha(0.5f);
            this.c.setClickable(false);
        }
        this.e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.ihealthlabs.freeindoorrunning.FreeIndoorRunningActivity$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                FreeIndoorRunningActivity.this.dMI_(compoundButton, z);
            }
        });
        boolean bc = LanguageUtil.bc(this.b);
        BitmapDrawable cKn_ = nrz.cKn_(this.b, R.drawable.pic_indoor_run);
        if (bc) {
            this.d.setImageDrawable(cKn_);
        } else {
            this.d.setImageDrawable(this.b.getResources().getDrawable(R.drawable.pic_indoor_run));
        }
    }

    public /* synthetic */ void dMH_(View view) {
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).checkUserInfo(this, new IBaseResponseCallback() { // from class: reb
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                FreeIndoorRunningActivity.this.e(i, obj);
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void e(int i, Object obj) {
        if (i != 0) {
            e();
        }
    }

    /* synthetic */ void dMI_(CompoundButton compoundButton, boolean z) {
        LogUtil.a("FreeIndoorRunningActivity", " mFreeIndoorRunningSwitchButton isChedked is ", Boolean.valueOf(z));
        b(z);
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    private void b(boolean z) {
        HashMap hashMap = new HashMap(1);
        if (z) {
            guw.b(true);
            hashMap.put("switch", "ON");
            this.c.setAlpha(1.0f);
            this.c.setClickable(true);
        } else {
            guw.b(false);
            hashMap.put("switch", "OFF");
            this.c.setAlpha(0.5f);
            this.c.setClickable(false);
        }
        ixx.d().d(getApplicationContext(), AnalyticsValue.BI_TRACK_CLICK_VIBRATE_STEPCOUNT_SWITCH_1040049.value(), hashMap, 0);
    }

    private void e() {
        e(this.b);
        gso.e().c(0, 264, c(this.b, "sport_target_type_indoor_running"), b(this.b, "sport_target_value_indoor_running"), null, this.b);
    }

    private void e(Context context) {
        LogUtil.a("FreeIndoorRunningActivity", "tickBIStartFreeIndoorRunning");
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", "1");
        ixx.d().d(context, AnalyticsValue.START_FREE_INDOOR_RUNNING.value(), hashMap, 0);
    }

    private float b(Context context, String str) {
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), str);
        if (b(b)) {
            return -1.0f;
        }
        return CommonUtil.j(b);
    }

    private int c(Context context, String str) {
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), str);
        if (b(b)) {
            return -1;
        }
        try {
            return Integer.parseInt(b);
        } catch (NumberFormatException unused) {
            LogUtil.b("FreeIndoorRunningActivity", "getDifferentSportTargetType NumberFormatException");
            return -1;
        }
    }

    private boolean b(String str) {
        return str == null || "".equals(str) || "-1".equals(str);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
