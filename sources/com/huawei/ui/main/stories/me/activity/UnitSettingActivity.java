package com.huawei.ui.main.stories.me.activity;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.main.R$string;
import defpackage.dwo;
import defpackage.gnp;
import defpackage.ixx;
import defpackage.jlk;
import defpackage.kor;
import defpackage.nro;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class UnitSettingActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f10351a;
    private Handler b = new b(this);
    private LinearLayout c;
    private HealthRadioButton d;
    private HealthTextView e;
    private ImageView f;
    private HealthRadioButton g;
    private HealthTextView h;
    private LinearLayout j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_unit_setting);
        b();
    }

    private void b() {
        this.c = (LinearLayout) findViewById(R.id.metric_imperial_unit_setting_layout);
        this.e = (HealthTextView) findViewById(R.id.metric_imperial_text);
        this.f10351a = (ImageView) findViewById(R.id.metric_imperial_arrow);
        this.c.setOnClickListener(this);
        this.j = (LinearLayout) findViewById(R.id.temperature_unit_setting_layout);
        this.h = (HealthTextView) findViewById(R.id.temperature_unit_text);
        this.f = (ImageView) findViewById(R.id.temperature_unit_arrow);
        this.j.setOnClickListener(this);
        if (LanguageUtil.bc(this)) {
            Drawable drawable = getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201);
            this.f10351a.setBackground(drawable);
            this.f.setBackground(drawable);
        } else {
            Drawable drawable2 = getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202);
            this.f10351a.setBackground(drawable2);
            this.f.setBackground(drawable2);
        }
        e();
        c();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (isDestroyed() || isFinishing()) {
            LogUtil.h("UnitSettingActivity", "onClick activity isDestroyed or isFinishing is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (nsn.a(750)) {
                LogUtil.a("UnitSettingActivity", "onClick is too fast");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (view == this.c) {
                e(101);
            } else if (view == this.j) {
                e(102);
            } else {
                LogUtil.h("UnitSettingActivity", "onClick click to other view");
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void e(int i) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService("layout_inflater");
        if (getSystemService("layout_inflater") instanceof LayoutInflater) {
            View inflate = layoutInflater.inflate(R.layout.hw_show_settings_unit_view, (ViewGroup) null);
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.metric_unit_name_text);
            HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.imperial_unit_name_text);
            if (i == 101) {
                healthTextView.setText(getString(R$string.IDS_system_set_metric));
                healthTextView2.setText(getString(R$string.IDS_system_set_imperial));
            } else {
                healthTextView.setText(getString(R$string.IDS_celsius_unit));
                healthTextView2.setText(getString(R$string.IDS_fahrenheit_unit));
            }
            dOa_(i, inflate);
        }
    }

    private void dOa_(final int i, View view) {
        String string;
        this.g = (HealthRadioButton) view.findViewById(R.id.metric_unit_radio_button);
        this.d = (HealthRadioButton) view.findViewById(R.id.imperial_unit_radio_button);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
        if (i == 101) {
            string = getString(R$string.IDS_system_set_unit);
        } else {
            string = getString(R$string.IDS_temperature_show_style);
        }
        builder.a(string).czh_(view, 0, 0).cze_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.UnitSettingActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                LogUtil.a("UnitSettingActivity", "createUnitDialog onClick positive view");
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        final CustomViewDialog e = builder.e();
        this.g.setClickable(false);
        this.d.setClickable(false);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.settings_metric_unit_layout);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.settings_imperial_unit_layout);
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.UnitSettingActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (i == 101) {
                    UnitSettingActivity.this.a(0, true, false);
                } else {
                    UnitSettingActivity.this.d(true, false);
                }
                e.dismiss();
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.UnitSettingActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (i == 101) {
                    UnitSettingActivity.this.a(1, false, true);
                } else {
                    UnitSettingActivity.this.d(false, true);
                }
                e.dismiss();
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        d(i);
        e.show();
    }

    private void d(int i) {
        if (i == 101) {
            boolean h = UnitUtil.h();
            e(!h, h);
        } else {
            boolean d = UnitUtil.d();
            e(d, !d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z, boolean z2) {
        e(z, z2);
        b(i);
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z, boolean z2) {
        e(z, z2);
        c(z);
        LogUtil.a("UnitSettingActivity", "clickCelsiusOrFahrenheit send command to device");
        jlk.a().b(z);
    }

    private void e(boolean z, boolean z2) {
        this.g.setChecked(z);
        this.d.setChecked(z2);
    }

    private void b(int i) {
        UnitUtil.c(i == 1);
        if (SystemInfo.h()) {
            LogUtil.a("UnitSettingActivity", "isNewHonor");
            gnp.a(this);
        }
        LogUtil.a("UnitSettingActivity", "saveMetricImperialUnit : writeUnitToDb");
        kor.a().a("custom.metric_imperial_unit", String.valueOf(i));
        this.b.sendEmptyMessage(101);
        nro.e(this, -1);
        String value = AnalyticsValue.HEALTH_MINE_SETTING_UNIT_2040070.value();
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(this, value, hashMap, 0);
    }

    private void c(boolean z) {
        UnitUtil.a(z);
        LogUtil.a("UnitSettingActivity", "saveTemperatureUnit : isCelsius = ", Boolean.valueOf(z));
        kor.a().a("custom.temperature_unit", String.valueOf(z));
        this.b.sendEmptyMessage(102);
        nro.e(this, -1);
        String value = AnalyticsValue.HEALTH_MINE_SETTING_UNIT_2041086.value();
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("type", Boolean.valueOf(z));
        ixx.d().d(this, value, hashMap, 0);
    }

    private void d() {
        boolean h = UnitUtil.h();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("unit", h);
        } catch (JSONException unused) {
            LogUtil.b("UnitSettingActivity", "notifyWearDevice setMetricUnit json error");
        }
        LogUtil.a("UnitSettingActivity", "notifyWearDevice isFlag = ", Boolean.valueOf(h));
        dwo.d().g(jSONObject, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("UnitSettingActivity", "updateMetricImperialUnit isImperialUnit : ", Boolean.valueOf(UnitUtil.h()));
        this.e.setText(UnitUtil.h() ? R$string.IDS_system_set_imperial : R$string.IDS_system_set_metric);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("UnitSettingActivity", "updateTemperatureUnit isShowCelsiusUnit : ", Boolean.valueOf(UnitUtil.d()));
        this.h.setText(UnitUtil.d() ? R$string.IDS_celsius_unit : R$string.IDS_fahrenheit_unit);
    }

    static class b extends BaseHandler<UnitSettingActivity> {
        b(UnitSettingActivity unitSettingActivity) {
            super(unitSettingActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dOb_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(UnitSettingActivity unitSettingActivity, Message message) {
            if (unitSettingActivity == null || message == null) {
                LogUtil.h("UnitSettingActivity", "handleMessageWhenReferenceNotNull activity is null or msg is null");
                return;
            }
            LogUtil.a("UnitSettingActivity", "handleMessageWhenReferenceNotNull msg.what = ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 101) {
                unitSettingActivity.e();
            } else if (i == 102) {
                unitSettingActivity.c();
            } else {
                LogUtil.h("UnitSettingActivity", "handleMessageWhenReferenceNotNull is default value");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
