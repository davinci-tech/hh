package com.huawei.ui.device.activity.menstrualpredict;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.device.activity.HealthMonitoringBaseActivity;
import com.huawei.ui.device.activity.menstrualpredict.MenstrualPredictActivity;
import defpackage.ixx;
import defpackage.jqi;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes.dex */
public class MenstrualPredictActivity extends HealthMonitoringBaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthSwitchButton f9110a;
    private HealthTextView h;
    private boolean e = false;
    private boolean b = false;
    private boolean c = false;
    private CompoundButton.OnCheckedChangeListener d = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.menstrualpredict.MenstrualPredictActivity$$ExternalSyntheticLambda3
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            MenstrualPredictActivity.this.cQA_(compoundButton, z);
        }
    };

    /* synthetic */ void cQA_(CompoundButton compoundButton, boolean z) {
        LogUtil.a("MenstrualPredictActivity", "mMenstrualPredictSwitchListener isChecked = ", Boolean.valueOf(z));
        if (z) {
            LoginInit loginInit = LoginInit.getInstance(this);
            int a2 = a(loginInit);
            int d = d(loginInit);
            if (a2 != 1 || d < 18) {
                this.f9110a.setEnabled(false);
                b();
                ViewClickInstrumentation.clickOnView(compoundButton);
                return;
            }
        }
        if (this.e) {
            LogUtil.h("MenstrualPredictActivity", "mMenstrualPredictSwitchListener don't set menstrual predict status");
            this.e = false;
            ViewClickInstrumentation.clickOnView(compoundButton);
        } else {
            c(z);
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    }

    @Override // com.huawei.ui.device.activity.HealthMonitoringBaseActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_menstrual_predict);
        Intent intent = getIntent();
        if (intent != null) {
            this.b = intent.getBooleanExtra("isFromHealthMonitoring", false);
        }
        a();
        e();
    }

    private void a() {
        this.f9110a = (HealthSwitchButton) findViewById(R.id.menstrual_predict_switch_button);
        this.h = (HealthTextView) findViewById(R.id.menstrual_predict_tv1);
        this.h.setText(getResources().getString(R.string._2130846617_res_0x7f022399, UnitUtil.e(70.0d, 1, 0)));
    }

    private void e() {
        jqi.a().getSwitchSetting("menstrual_predict_switch", new IBaseResponseCallback() { // from class: nwn
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                MenstrualPredictActivity.this.e(i, obj);
            }
        });
    }

    public /* synthetic */ void e(int i, Object obj) {
        LogUtil.a("MenstrualPredictActivity", "initSwitchStatus errorCode = ", Integer.valueOf(i));
        String str = (i == 0 && (obj instanceof String)) ? (String) obj : "0";
        LogUtil.a("MenstrualPredictActivity", "initSwitchStatus switchStatus = ", str);
        final boolean equals = "1".equals(str);
        this.c = equals;
        runOnUiThread(new Runnable() { // from class: nwl
            @Override // java.lang.Runnable
            public final void run() {
                MenstrualPredictActivity.this.b(equals);
            }
        });
    }

    public /* synthetic */ void b(boolean z) {
        this.f9110a.setChecked(z);
        this.f9110a.setOnCheckedChangeListener(this.d);
    }

    private int a(LoginInit loginInit) {
        if (loginInit == null) {
            return -1;
        }
        String accountInfo = loginInit.getAccountInfo(1005);
        LogUtil.a("MenstrualPredictActivity", "getUserGender genderStr = ", accountInfo);
        return CommonUtil.e(accountInfo, -1);
    }

    private int d(LoginInit loginInit) {
        String accountInfo = loginInit.getAccountInfo(1006);
        if (TextUtils.isEmpty(accountInfo) || accountInfo.length() <= 4) {
            LogUtil.h("MenstrualPredictActivity", "getUserAge birthDate is empty or length is illegal");
            return 0;
        }
        int c = HiDateUtil.c(System.currentTimeMillis());
        String valueOf = String.valueOf(c);
        if (valueOf.length() <= 4) {
            LogUtil.h("MenstrualPredictActivity", "getUserAge currentDateStr length is illegal");
            return 0;
        }
        try {
            int parseInt = Integer.parseInt(accountInfo.substring(accountInfo.length() - 4));
            int parseInt2 = Integer.parseInt(valueOf.substring(valueOf.length() - 4));
            int parseInt3 = (c / 10000) - (Integer.parseInt(accountInfo) / 10000);
            if (parseInt2 < parseInt) {
                parseInt3--;
            }
            return parseInt3;
        } catch (NumberFormatException unused) {
            LogUtil.b("MenstrualPredictActivity", "getUserAge NumberFormatException");
            return 0;
        }
    }

    private void c(boolean z) {
        LogUtil.a("MenstrualPredictActivity", "setMenstrualPredictStatus");
        this.c = z;
        e(false);
        String str = z ? "1" : "0";
        this.f9110a.setEnabled(true);
        this.f9110a.setChecked(z);
        jqi.a().sendSettingSwitchCommand("hw.unitedevice.physiological", "hw.watch.health.physiological", 900300012L, str);
        jqi.a().setSwitchSetting("menstrual_predict_switch", str, new IBaseResponseCallback() { // from class: nwk
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                LogUtil.a("MenstrualPredictActivity", "setMenstrualPredictStatus errorCode = ", Integer.valueOf(i));
            }
        });
        Intent intent = new Intent();
        intent.putExtra("menstrualPredictResultStatus", str);
        setResult(-1, intent);
    }

    private void b() {
        LogUtil.a("MenstrualPredictActivity", "showNoticeDialog");
        String format = String.format(Locale.getDefault(), getResources().getString(R.string._2130845719_res_0x7f022017), 18);
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(format);
        builder.czA_(getResources().getString(R.string._2130837555_res_0x7f020033), new View.OnClickListener() { // from class: nwj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenstrualPredictActivity.this.cQB_(view);
            }
        });
        builder.czE_(getString(R.string._2130837648_res_0x7f020090), new View.OnClickListener() { // from class: nwm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MenstrualPredictActivity.this.cQC_(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    public /* synthetic */ void cQB_(View view) {
        LogUtil.a("MenstrualPredictActivity", "click negative button");
        this.e = true;
        this.f9110a.setEnabled(true);
        this.f9110a.setChecked(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cQC_(View view) {
        LogUtil.a("MenstrualPredictActivity", "click positive button");
        c(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.device.activity.HealthMonitoringBaseActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        e(true);
    }

    private void e(boolean z) {
        LogUtil.a("MenstrualPredictActivity", "setSwitchStatusBi isSwitchStatus = ", Boolean.valueOf(z));
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (this.c) {
            if (z) {
                hashMap.put("switchStatus", "true");
            } else {
                hashMap.put("event", "1");
            }
        } else if (z) {
            hashMap.put("switchStatus", "false");
        } else {
            hashMap.put("event", "2");
        }
        if (this.b) {
            hashMap.put("setPath", "2");
        } else {
            hashMap.put("setPath", "1");
        }
        String value = AnalyticsValue.MENSTRUAL_PREDICT_SWITCH_EVENT.value();
        LogUtil.a("MenstrualPredictActivity", "setSwitchEventBi value = ", value, ", map = ", hashMap.toString());
        ixx.d().d(this, value, hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
