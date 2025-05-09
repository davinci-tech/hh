package com.huawei.health.ecologydevice.fitness;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.PluginDeviceAdapter;
import com.huawei.health.device.api.PluginDeviceApi;
import com.huawei.health.ecologydevice.callback.PickerChooseCallback;
import com.huawei.health.ecologydevice.manager.EcologyDevicePickerManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginbase.PluginBaseAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnlayout.HealthColumnRelativeLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ash;
import defpackage.czs;
import defpackage.dds;
import defpackage.dij;
import defpackage.dis;
import defpackage.diy;
import defpackage.gso;
import defpackage.gvv;
import defpackage.ixx;
import defpackage.lql;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SkipperTargetSettingActivity extends BaseActivity implements View.OnClickListener, PickerChooseCallback<Integer> {

    /* renamed from: a, reason: collision with root package name */
    private Context f2286a;
    private HealthTextView b;
    private EcologyDevicePickerManager c;
    private ImageView d;
    private int e;
    private HealthColumnRelativeLayout f;
    private HealthTextView h;
    private ImageView i;
    private CustomTitleBar k;
    private EcologyDevicePickerManager l;
    private HealthTextView n;
    private HealthButton o;
    private int m = 0;
    private int j = 2;
    private int g = 100;
    private int q = 60;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f2286a = this;
        setContentView(R.layout.skipper_target_setting_layout);
        Intent intent = getIntent();
        PluginDeviceApi pluginDeviceApi = (PluginDeviceApi) Services.c("PluginDevice", PluginDeviceApi.class);
        if (pluginDeviceApi != null) {
            LogUtil.a("indoor_SkipperTargetSettingActivity", "checkAutoTrackStatus");
            pluginDeviceApi.checkAutoTrackStatus();
            PluginBaseAdapter adapter = pluginDeviceApi.getAdapter();
            if (adapter == null || !(adapter instanceof PluginDeviceAdapter)) {
                pluginDeviceApi.init(BaseApplication.getContext());
                pluginDeviceApi.setAdapter(czs.a());
            }
        }
        if (intent != null) {
            this.e = intent.getIntExtra("currentSkipperTarget", 6);
        }
        LogUtil.c("indoor_SkipperTargetSettingActivity", "CURRENT MODLE ", Integer.valueOf(this.e));
        a();
        b();
    }

    private void b() {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.i.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.i.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        int i = this.e;
        if (i == 0) {
            int a2 = SharedPreferenceManager.a("first_rope_target", "rope_skipping_picker_time", 0);
            if (a2 == 0) {
                a2 = 60;
            }
            this.q = a2;
            c();
            this.b.setTextColor(this.f2286a.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            this.f.setVisibility(0);
            this.d.setBackground(this.f2286a.getResources().getDrawable(R.drawable._2131431505_res_0x7f0b1051));
            this.b.setText(this.f2286a.getString(R.string._2130843707_res_0x7f02183b));
            this.k.setTitleText(this.f2286a.getString(R.string._2130843697_res_0x7f021831));
            this.h.setText(R.string._2130843700_res_0x7f021834);
            return;
        }
        if (i != 5) {
            if (i != 6) {
                return;
            }
            this.b.setTextColor(this.f2286a.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            this.f.setVisibility(8);
            this.d.setBackground(this.f2286a.getResources().getDrawable(R.drawable._2131431497_res_0x7f0b1049));
            this.b.setText(this.f2286a.getString(R.string._2130843705_res_0x7f021839));
            this.k.setTitleText(this.f2286a.getString(R.string._2130843699_res_0x7f021833));
            return;
        }
        e();
        this.b.setTextColor(this.f2286a.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.f.setVisibility(0);
        this.d.setBackground(this.f2286a.getResources().getDrawable(R.drawable._2131431500_res_0x7f0b104c));
        this.b.setText(this.f2286a.getString(R.string._2130843706_res_0x7f02183a));
        this.k.setTitleText(this.f2286a.getString(R.string._2130843698_res_0x7f021832));
        HealthTextView healthTextView = this.n;
        Resources resources = this.f2286a.getResources();
        int i2 = this.g;
        healthTextView.setText(resources.getQuantityString(R.plurals._2130903272_res_0x7f0300e8, i2, Integer.valueOf(i2)));
        this.h.setText(R.string._2130843701_res_0x7f021835);
    }

    private void e() {
        String e = SharedPreferenceManager.e("first_rope_target", "rope_skipping_picker_number", "");
        if (TextUtils.isEmpty(e)) {
            return;
        }
        Map<String, Integer> map = (Map) gvv.a(e, new TypeToken<Map<String, Integer>>() { // from class: com.huawei.health.ecologydevice.fitness.SkipperTargetSettingActivity.5
        });
        this.g = b(map, "target_cache_value");
        this.j = b(map, "target_cache_select_index");
    }

    private int b(Map<String, Integer> map, String str) {
        if (map == null) {
            LogUtil.h("indoor_SkipperTargetSettingActivity", "getCacheValue() targetMap is null");
            return 0;
        }
        Integer num = map.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    private void a() {
        this.k = (CustomTitleBar) findViewById(R.id.titlebar_skipper_target_setting);
        this.d = (ImageView) findViewById(R.id.device_show_image);
        this.b = (HealthTextView) findViewById(R.id.skipper_device_describe);
        this.h = (HealthTextView) findViewById(R.id.skipper_target_setting);
        this.f = (HealthColumnRelativeLayout) findViewById(R.id.skipper_target_setting_layout);
        this.i = (ImageView) findViewById(R.id.skipper_target_arrow);
        this.n = (HealthTextView) findViewById(R.id.skipper_target_value);
        this.f.setOnClickListener(this);
        HealthButton healthButton = (HealthButton) findViewById(R.id.start_skipping);
        this.o = healthButton;
        healthButton.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.b("indoor_SkipperTargetSettingActivity", "view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.skipper_target_setting_layout) {
            LogUtil.c("indoor_SkipperTargetSettingActivity", "Show dialog");
            f();
        } else if (view.getId() == R.id.start_skipping) {
            LogUtil.a("indoor_SkipperTargetSettingActivity", "click start skipping");
            int i = this.e;
            if (i == 5) {
                this.m = this.g;
            } else if (i == 0) {
                this.m = this.q;
            } else if (i == 6) {
                this.m = 0;
            }
            LogUtil.a("indoor_SkipperTargetSettingActivity", "Current sport :", 283, "current target :", Integer.valueOf(this.e), "current  value:", Integer.valueOf(this.m));
            if ("true".equals(ash.b("ROPE_DISPLAY_PROCESS"))) {
                i();
            } else {
                diy.a(this.f2286a, this.e, this.m, null, dds.c().j());
                finish();
            }
            h();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        if (this.e == 5) {
            if (this.c == null) {
                this.c = new EcologyDevicePickerManager.Builder(this).a(50.0d).e(10000.0d).c(this.j == 0 ? this.g : 0.0d).c(getString(R.string._2130843701_res_0x7f021835)).c(this).e();
            }
            this.c.b(d(), 5, R.plurals._2130903274_res_0x7f0300ea, this.j);
        } else {
            if (this.l == null) {
                this.l = new EcologyDevicePickerManager.Builder(this).c(getString(R.string._2130843700_res_0x7f021834)).b(3599).c(60).a(this.q).c(this).e();
            }
            this.l.c();
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.PickerChooseCallback
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onValueSelected(Integer num, int i) {
        if (this.e == 5) {
            HashMap hashMap = new HashMap();
            hashMap.put("target_cache_value", num);
            hashMap.put("target_cache_select_index", Integer.valueOf(i));
            SharedPreferenceManager.c("first_rope_target", "rope_skipping_picker_number", lql.b(hashMap));
            this.g = num.intValue();
            HealthTextView healthTextView = this.n;
            Resources resources = this.f2286a.getResources();
            int i2 = this.g;
            healthTextView.setText(resources.getQuantityString(R.plurals._2130903272_res_0x7f0300e8, i2, Integer.valueOf(i2)));
            return;
        }
        SharedPreferenceManager.b("first_rope_target", "rope_skipping_picker_time", num.intValue());
        this.q = num.intValue();
        c();
    }

    private void c() {
        int i = this.q;
        int i2 = i % 60;
        int i3 = i / 60;
        if (i2 == 0) {
            HealthTextView healthTextView = this.n;
            Context context = this.f2286a;
            healthTextView.setText(context.getString(R.string._2130843703_res_0x7f021837, context.getResources().getQuantityString(R.plurals._2130903270_res_0x7f0300e6, i3, Integer.valueOf(i3))));
        } else {
            HealthTextView healthTextView2 = this.n;
            Context context2 = this.f2286a;
            healthTextView2.setText(context2.getString(R.string._2130843702_res_0x7f021836, context2.getResources().getQuantityString(R.plurals._2130903270_res_0x7f0300e6, i3, Integer.valueOf(i3)), this.f2286a.getResources().getQuantityString(R.plurals._2130903271_res_0x7f0300e7, i2, Integer.valueOf(i2))));
        }
    }

    private int[] d() {
        int[] iArr = new int[23];
        int i = 0;
        while (i < 20) {
            int i2 = i + 1;
            iArr[i] = i2 * 50;
            i = i2;
        }
        iArr[20] = 2000;
        iArr[21] = 3000;
        iArr[22] = 5000;
        return iArr;
    }

    private void i() {
        gso.e().c(0, 283, this.e, this.m, null, this.f2286a.getApplicationContext());
        finish();
    }

    private void h() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("currentSkipperTargetType", Integer.valueOf(this.e));
        hashMap.put("currentSkipperTarget", Integer.valueOf(this.m));
        hashMap.put("prodId", dij.e(dds.c().j()));
        hashMap.put("macAddress", dis.b(dds.c().d()));
        ixx.d().d(this, AnalyticsValue.HEALTH_SKIPPING_2170016.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
