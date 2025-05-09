package com.huawei.ui.device.activity.emotionautomonitor;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.device.activity.emotionautomonitor.EmotionAutoMonitorActivity;
import com.huawei.ui.main.stories.me.activity.PrivacyCenterActivity;
import com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity;
import defpackage.jld;
import defpackage.jlj;
import defpackage.jps;
import defpackage.jqi;
import defpackage.nrt;
import defpackage.nsn;
import defpackage.oau;
import defpackage.pwr;
import defpackage.rvo;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes.dex */
public class EmotionAutoMonitorActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private String f9083a;
    private ImageView b;
    private HealthSwitchButton c;
    private RelativeLayout d;
    private HealthSwitchButton e;
    private CustomTextAlertDialog f;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private LinearLayout l;
    private rvo g = rvo.e(this);
    private CompoundButton.OnCheckedChangeListener m = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.emotionautomonitor.EmotionAutoMonitorActivity.2
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            LogUtil.a("EmotionAutoMonitorActivity", "mEmotionAutoMonitorListener isChecked: ", Boolean.valueOf(z));
            jps.b(z ? "1" : "0");
            ObserverManagerUtil.c(ObserveLabels.EMOTION_SWITCH_CHANGED, new Object[0]);
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };
    private CompoundButton.OnCheckedChangeListener n = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.emotionautomonitor.EmotionAutoMonitorActivity.1
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            LogUtil.a("EmotionAutoMonitorActivity", "mEmotionRiskWarningSwitch isChecked: ", Boolean.valueOf(z));
            if (z) {
                if (!EmotionAutoMonitorActivity.this.f().booleanValue()) {
                    EmotionAutoMonitorActivity.this.g();
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                }
                EmotionAutoMonitorActivity.this.f9083a = "1";
            } else {
                EmotionAutoMonitorActivity.this.f9083a = "0";
            }
            jps.a(EmotionAutoMonitorActivity.this.f9083a);
            ObserverManagerUtil.c(ObserveLabels.EMOTION_SWITCH_CHANGED, new Object[0]);
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("EmotionAutoMonitorActivity", "onCreate");
        setContentView(R.layout.activity_settings_emotion_auto_monitor);
        i();
        h();
        c();
    }

    private void c() {
        HashMap hashMap = new HashMap();
        hashMap.put("event", 0);
        hashMap.put(FunctionSetBeanReader.BI_ELEMENT, "m0");
        hashMap.put("moduleName", "main_page");
        oau.e(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put(FunctionSetBeanReader.BI_ELEMENT, str);
        hashMap.put("value", "health_data_to_cloud_switchStatus");
        hashMap.put("moduleName", "boot_window");
        hashMap.put("entrance", "e1");
        oau.e(hashMap);
    }

    public Boolean d() {
        boolean c = jlj.a().c();
        boolean l = Utils.l();
        LogUtil.a("EmotionAutoMonitorActivity", "isEnableNMPA = ", Boolean.valueOf(c), ",isAllowLogin = ", Boolean.valueOf(l), ",isAnAdult = ", Boolean.valueOf(j()));
        if (c || l || j()) {
            return false;
        }
        return true;
    }

    private boolean j() {
        String accountInfo = LoginInit.getInstance(this).getAccountInfo(1006);
        return TextUtils.isEmpty(accountInfo) || CommonUtil.m(this, accountInfo) + 180000 > DateFormatUtil.b(System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Boolean f() {
        String a2 = this.g.a(7);
        LogUtil.a("EmotionAutoMonitorActivity", ",privacyHealthData= ", a2);
        if (a2.equals("true")) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.d.setAlpha(0.38f);
        this.e.setEnabled(false);
        this.h.setEnabled(false);
        this.l.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.d.setAlpha(1.0f);
        this.e.setEnabled(true);
        this.h.setEnabled(true);
        this.l.setVisibility(8);
    }

    private void i() {
        this.c = (HealthSwitchButton) findViewById(R.id.event_emotion_monitor_switch_btn);
        this.e = (HealthSwitchButton) findViewById(R.id.event_emotion_risk_waring_switch_btn);
        this.d = (RelativeLayout) findViewById(R.id.event_emotion_risk_waring_view);
        this.l = (LinearLayout) findViewById(R.id.tip_show_view);
        this.j = (HealthTextView) findViewById(R.id.go_to_open);
        this.b = (ImageView) findViewById(R.id.close_tip);
        this.h = (HealthTextView) findViewById(R.id.learn_more_text);
        this.i = (HealthTextView) findViewById(R.id.learn_more_img);
        this.l.setElevation(40.0f);
        if (d().booleanValue()) {
            this.d.setVisibility(0);
            this.l.setVisibility(0);
        } else {
            this.d.setVisibility(8);
            this.l.setVisibility(8);
        }
        if (nrt.a(this)) {
            if (LanguageUtil.bc(this)) {
                this.l.setBackgroundResource(R.drawable._2131428061_res_0x7f0b02dd);
            } else {
                this.l.setBackgroundResource(R.drawable._2131428059_res_0x7f0b02db);
            }
            this.b.setImageResource(R.mipmap._2131821231_res_0x7f1102af);
        } else {
            if (LanguageUtil.bc(this)) {
                this.l.setBackgroundResource(R.drawable._2131428062_res_0x7f0b02de);
            } else {
                this.l.setBackgroundResource(R.drawable._2131428060_res_0x7f0b02dc);
            }
            this.b.setImageResource(R.mipmap._2131821230_res_0x7f1102ae);
        }
        this.j.setOnClickListener(new View.OnClickListener() { // from class: nuw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EmotionAutoMonitorActivity.this.cPC_(view);
            }
        });
        this.h.setOnClickListener(new View.OnClickListener() { // from class: nux
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EmotionAutoMonitorActivity.this.cPD_(view);
            }
        });
        this.i.setOnClickListener(new View.OnClickListener() { // from class: nuy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EmotionAutoMonitorActivity.this.cPE_(view);
            }
        });
        this.b.setOnClickListener(new View.OnClickListener() { // from class: nuv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EmotionAutoMonitorActivity.this.cPF_(view);
            }
        });
    }

    public /* synthetic */ void cPC_(View view) {
        l();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cPD_(View view) {
        n();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cPE_(View view) {
        n();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cPF_(View view) {
        this.l.setVisibility(8);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: com.huawei.ui.device.activity.emotionautomonitor.EmotionAutoMonitorActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements IBaseResponseCallback {
        final /* synthetic */ HealthSwitchButton b;

        AnonymousClass5(HealthSwitchButton healthSwitchButton) {
            this.b = healthSwitchButton;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i == 0 && (obj instanceof String)) {
                final String str = (String) obj;
                LogUtil.a("EmotionAutoMonitorActivity", "emotionAutoMonitorSwitch initData status:", str);
                EmotionAutoMonitorActivity emotionAutoMonitorActivity = EmotionAutoMonitorActivity.this;
                final HealthSwitchButton healthSwitchButton = this.b;
                emotionAutoMonitorActivity.runOnUiThread(new Runnable() { // from class: nuz
                    @Override // java.lang.Runnable
                    public final void run() {
                        EmotionAutoMonitorActivity.AnonymousClass5.this.b(healthSwitchButton, str);
                    }
                });
            }
        }

        public /* synthetic */ void b(HealthSwitchButton healthSwitchButton, String str) {
            healthSwitchButton.setChecked("1".equals(str));
            healthSwitchButton.setOnCheckedChangeListener(EmotionAutoMonitorActivity.this.m);
        }
    }

    private void h() {
        jps.e(new AnonymousClass5((HealthSwitchButton) findViewById(R.id.event_emotion_monitor_switch_btn)));
        if (d().booleanValue()) {
            jps.b(new AnonymousClass3());
        }
        a();
    }

    /* renamed from: com.huawei.ui.device.activity.emotionautomonitor.EmotionAutoMonitorActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements IBaseResponseCallback {
        AnonymousClass3() {
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("EmotionAutoMonitorActivity", "emotionRiskWarningSwitch initData errorCode:", Integer.valueOf(i), "objectData:", obj);
            final String str = (i == 0 && (obj instanceof String)) ? (String) obj : "";
            EmotionAutoMonitorActivity.this.runOnUiThread(new Runnable() { // from class: nvb
                @Override // java.lang.Runnable
                public final void run() {
                    EmotionAutoMonitorActivity.AnonymousClass3.this.a(str);
                }
            });
        }

        public /* synthetic */ void a(String str) {
            if ("1".equals(str)) {
                if (EmotionAutoMonitorActivity.this.f().booleanValue()) {
                    EmotionAutoMonitorActivity.this.e();
                } else {
                    EmotionAutoMonitorActivity.this.b();
                }
            } else {
                EmotionAutoMonitorActivity.this.e();
            }
            EmotionAutoMonitorActivity.this.e.setChecked("1".equals(str));
            EmotionAutoMonitorActivity.this.e.setOnCheckedChangeListener(EmotionAutoMonitorActivity.this.n);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        CustomTextAlertDialog.Builder b = new CustomTextAlertDialog.Builder(this).b(R.string._2130847418_res_0x7f0226ba);
        b.d(R.string._2130847419_res_0x7f0226bb);
        b.a(getResources().getDimensionPixelSize(R.dimen._2131363746_res_0x7f0a07a2), getResources().getDimensionPixelSize(R.dimen._2131362703_res_0x7f0a038f));
        b.c(false);
        b.b((Boolean) true);
        b.cyR_(R.string._2130839728_res_0x7f0208b0, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.emotionautomonitor.EmotionAutoMonitorActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EmotionAutoMonitorActivity.this.f != null) {
                    EmotionAutoMonitorActivity.this.e.setChecked(false);
                    EmotionAutoMonitorActivity.this.f9083a = "0";
                    EmotionAutoMonitorActivity.this.f.dismiss();
                    EmotionAutoMonitorActivity.this.f = null;
                    EmotionAutoMonitorActivity.this.c(1, "r2");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        b.cyU_(R.string._2130847339_res_0x7f02266b, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.emotionautomonitor.EmotionAutoMonitorActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EmotionAutoMonitorActivity.this.e.setChecked(true);
                EmotionAutoMonitorActivity.this.f9083a = "1";
                EmotionAutoMonitorActivity.this.g.e(7, true);
                jps.a(EmotionAutoMonitorActivity.this.f9083a);
                EmotionAutoMonitorActivity.this.c(1, "r1");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = b.a();
        this.f = a2;
        a2.setCancelable(false);
        this.f.setWindowGravityParams(17);
        this.f.show();
        c(0, "r0");
    }

    private void l() {
        Intent intent = new Intent();
        intent.setClass(this, PrivacyCenterActivity.class);
        startActivityForResult(intent, 0);
        nsn.ai(this);
    }

    private void n() {
        Intent intent = new Intent(this, (Class<?>) ServiceItemActivity.class);
        intent.putExtra("Agreement_key", "HealthPrivacy");
        intent.putExtra("Is_show_cancel_key", true);
        intent.addFlags(268435456);
        startActivity(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("EmotionAutoMonitorActivity", "resultCode=" + i2);
        if (i2 == 0) {
            String stringExtra = intent.getStringExtra("health_cloud");
            if (TextUtils.isEmpty(stringExtra) || !stringExtra.equals("true")) {
                return;
            }
            e();
        }
    }

    /* renamed from: com.huawei.ui.device.activity.emotionautomonitor.EmotionAutoMonitorActivity$9, reason: invalid class name */
    public class AnonymousClass9 implements CommonUiBaseResponse {
        AnonymousClass9() {
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            boolean booleanValue = (i == 0 && (obj instanceof Boolean)) ? ((Boolean) obj).booleanValue() : false;
            LogUtil.a("EmotionAutoMonitorActivity", "isCalibrated is ", Boolean.valueOf(booleanValue));
            if (booleanValue) {
                jqi.a().getSwitchSetting("press_auto_monitor_switch_status", new IBaseResponseCallback() { // from class: nva
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i2, Object obj2) {
                        EmotionAutoMonitorActivity.AnonymousClass9.e(i2, obj2);
                    }
                });
            }
        }

        public static /* synthetic */ void e(int i, Object obj) {
            int i2;
            if (i == 0 && (obj instanceof String)) {
                String str = (String) obj;
                if (!TextUtils.isEmpty(str) && "true".equals(str)) {
                    i2 = 1;
                    jld.b().a(i2);
                }
            }
            i2 = 2;
            jld.b().a(i2);
        }
    }

    private void a() {
        new pwr().e(new AnonymousClass9());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
