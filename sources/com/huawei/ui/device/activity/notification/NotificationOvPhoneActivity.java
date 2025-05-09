package com.huawei.ui.device.activity.notification;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.nsn;
import defpackage.nsy;
import java.util.Locale;

/* loaded from: classes9.dex */
public class NotificationOvPhoneActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f9161a;
    private HealthTextView b;
    private CustomTitleBar c;
    private ImageView e;
    private HealthTextView f;
    private HealthTextView g;
    private LinearLayout i;
    private HealthTextView j;
    private RelativeLayout k;
    private HealthTextView m;
    private HealthTextView n;
    private RelativeLayout o;
    private HealthTextView p;
    private String d = "";
    private boolean h = false;
    private String l = "";

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.notification_ovphone_setting_layout);
        this.f9161a = this;
        Intent intent = getIntent();
        if (intent != null) {
            this.d = intent.getStringExtra("device_id");
            this.h = intent.getBooleanExtra("isFromWear", false);
        }
        this.l = Build.BRAND;
        b();
    }

    private void b() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.notification_open_title);
        this.c = customTitleBar;
        customTitleBar.setLeftButtonVisibility(8);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.open_management_title);
        this.j = healthTextView;
        healthTextView.getPaint().setFakeBoldText(true);
        this.f = (HealthTextView) findViewById(R.id.open_management_content);
        this.g = (HealthTextView) findViewById(R.id.open_management_attention);
        this.j.setText(R.string.IDS_device_msgnotif_open_manager);
        this.f.setText(R.string.IDS_device_msgnotif_open_manager_content);
        this.g.setText(R.string.IDS_device_msgnotif_open_manager_attention);
        this.g.setAlpha(0.5f);
        this.b = (HealthTextView) findViewById(R.id.open_management_phone_brand);
        String str = this.f9161a.getResources().getString(R.string.IDS_device_msgnotif_open_manager_phone_brand) + this.l.toUpperCase(Locale.ENGLISH);
        if (nsn.ae(BaseApplication.getContext())) {
            str = this.f9161a.getString(R.string.IDS_pad_device_phone_brand, this.l.toUpperCase(Locale.ENGLISH));
        }
        this.b.setText(str);
        this.e = (ImageView) findViewById(R.id.open_management_instraction_pictrue);
        this.n = (HealthTextView) findViewById(R.id.open_management_step_one);
        this.p = (HealthTextView) findViewById(R.id.open_management_step_two);
        this.m = (HealthTextView) findViewById(R.id.open_management_step_three);
        e(d());
        LinearLayout linearLayout = (LinearLayout) nsy.cMc_(this, R.id.notification_last_layout);
        this.i = linearLayout;
        linearLayout.setOnClickListener(this);
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMc_(this, R.id.notification_next_layout);
        this.k = relativeLayout;
        relativeLayout.setOnClickListener(this);
        RelativeLayout relativeLayout2 = (RelativeLayout) nsy.cMc_(this, R.id.notification_next_layout_twice);
        this.o = relativeLayout2;
        relativeLayout2.setOnClickListener(this);
    }

    private void e(boolean z) {
        if (z) {
            this.e.setImageResource(R.drawable._2131430229_res_0x7f0b0b55);
            String string = this.f9161a.getString(R.string.IDS_device_msgnotif_open_manager_oppo_step_one);
            if (nsn.ae(BaseApplication.getContext())) {
                string = String.format(Locale.ENGLISH, this.f9161a.getString(R.string.IDS_pad_device_oppo_step_one), 1);
            }
            this.n.setText(string);
            this.p.setText(R.string.IDS_device_msgnotif_open_manager_oppo_step_two);
            this.m.setText(R.string.IDS_device_msgnotif_open_manager_oppo_step_three);
            return;
        }
        this.e.setImageResource(R.drawable._2131430231_res_0x7f0b0b57);
        this.n.setText(R.string.IDS_device_msgnotif_open_manager_vivo_step_one);
        this.p.setText(R.string.IDS_device_msgnotif_open_manager_vivo_step_two);
        this.m.setText(R.string.IDS_device_msgnotif_open_manager_vivo_step_three);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == this.k.getId()) {
            this.j.setText(R.string.IDS_device_msgnotif_battery_manager);
            this.f.setText(nsn.ae(BaseApplication.getContext()) ? R.string.IDS_pad_device_manager_content : R.string.IDS_device_msgnotif_battery_manager_content);
            this.g.setVisibility(4);
            if (d()) {
                this.e.setImageResource(R.drawable._2131430230_res_0x7f0b0b56);
                this.n.setText(R.string.IDS_device_msgnotif_battery_manager_oppo_step_one);
                this.p.setText(R.string.IDS_device_msgnotif_battery_manager_oppo_step_two);
                this.m.setText(R.string.IDS_device_msgnotif_battery_manager_oppo_step_three);
            } else {
                this.e.setImageResource(R.drawable._2131430232_res_0x7f0b0b58);
                this.n.setText(R.string.IDS_device_msgnotif_battery_manager_vivo_step_one);
                this.p.setText(R.string.IDS_device_msgnotif_battery_manager_vivo_step_two);
                this.m.setText(R.string.IDS_device_msgnotif_battery_manager_vivo_step_three);
            }
            this.i.setVisibility(0);
            this.k.setVisibility(8);
            this.o.setVisibility(0);
        }
        if (view.getId() == this.i.getId()) {
            this.j.setText(R.string.IDS_device_msgnotif_open_manager);
            this.f.setText(R.string.IDS_device_msgnotif_open_manager_content);
            this.g.setVisibility(0);
            String str = this.f9161a.getResources().getString(R.string.IDS_device_msgnotif_open_manager_phone_brand) + this.l.toUpperCase(Locale.ENGLISH);
            if (nsn.ae(BaseApplication.getContext())) {
                str = this.f9161a.getResources().getString(R.string.IDS_pad_device_phone_brand, this.l.toUpperCase(Locale.ENGLISH));
            }
            this.b.setText(str);
            e(d());
            this.i.setVisibility(4);
            this.k.setVisibility(0);
            this.o.setVisibility(8);
        }
        if (view.getId() == this.o.getId()) {
            a();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        NoTitleCustomAlertDialog.Builder czz_ = new NoTitleCustomAlertDialog.Builder(this.f9161a).czC_(R.string._2130841379_res_0x7f020f23, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.notification.NotificationOvPhoneActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("NotificationOvPhoneActivity", "continue click");
                Intent intent = new Intent();
                intent.setClassName(NotificationOvPhoneActivity.this.f9161a, "com.huawei.ui.device.activity.notification.NotificationOpenActivity");
                intent.putExtra("isFromWear", NotificationOvPhoneActivity.this.h);
                intent.putExtra("device_id", NotificationOvPhoneActivity.this.d);
                NotificationOvPhoneActivity.this.startActivity(intent);
                NotificationOvPhoneActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.notification.NotificationOvPhoneActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("NotificationOvPhoneActivity", "notification cancel click");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        czz_.e(this.f9161a.getString(R.string.IDS_device_msgnotif_next_step_dialog));
        NoTitleCustomAlertDialog e = czz_.e();
        e.setCancelable(false);
        if (e.isShowing()) {
            return;
        }
        e.show();
    }

    private boolean d() {
        return (e() == null || e().toLowerCase(Locale.ENGLISH).indexOf("oppo") == -1) ? false : true;
    }

    private static String e() {
        return Build.BRAND;
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
