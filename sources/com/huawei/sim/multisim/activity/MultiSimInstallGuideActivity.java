package com.huawei.sim.multisim.activity;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.jdm;
import defpackage.ncf;
import defpackage.nrh;
import defpackage.nrr;
import defpackage.nsn;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.Locale;

/* loaded from: classes6.dex */
public class MultiSimInstallGuideActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8715a;
    private HealthButton d;
    private RelativeLayout f;
    private HealthTextView g;
    private HealthTextView h;
    private CustomTitleBar i;
    private ImageView j;
    private Context e = null;
    private String c = "";
    private int b = 1;
    private boolean l = true;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("MultiSimInstallGuideActivity", "onCreate");
        this.e = this;
        setContentView(R.layout.activity_multi_sim_install_guide);
        Intent intent = getIntent();
        if (intent != null) {
            this.c = intent.getStringExtra("simImsi");
            this.b = intent.getIntExtra("cardType", 1);
        }
        a();
        c();
    }

    private void a() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.multi_sim_install_guide_title_bar);
        this.i = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.f = (RelativeLayout) findViewById(R.id.multi_sim_install_guide_image_layout);
        this.j = (ImageView) findViewById(R.id.multi_sim_install_guide_image);
        this.g = (HealthTextView) findViewById(R.id.multi_sim_install_guide_title);
        this.f8715a = (HealthTextView) findViewById(R.id.multi_sim_install_guide_content);
        this.h = (HealthTextView) findViewById(R.id.multi_sim_install_guide_page_num);
        HealthButton healthButton = (HealthButton) findViewById(R.id.multi_sim_install_guide_download);
        this.d = healthButton;
        healthButton.setOnClickListener(this);
        if (nsn.j() - nsn.r(this.e) < nrr.e(this.e, 640.0f)) {
            LogUtil.a("MultiSimInstallGuideActivity", "initView low phone");
            this.j.getLayoutParams().width = nrr.e(this.e, 212.0f);
            this.j.getLayoutParams().height = nrr.e(this.e, 212.0f);
            this.f.getLayoutParams().height = nrr.e(this.e, 248.0f);
        }
    }

    private void c() {
        LogUtil.a("MultiSimInstallGuideActivity", "setViewData()");
        Bitmap csL_ = ncf.csL_(false, this.c, this.b);
        if (csL_ != null) {
            this.j.setImageBitmap(csL_);
        } else {
            LogUtil.h("MultiSimInstallGuideActivity", "install guide image is default");
            this.j.setImageResource(R.drawable._2131430950_res_0x7f0b0e26);
        }
        String format = String.format(Locale.ENGLISH, this.e.getString(R.string._2130840019_res_0x7f0209d3), UnitUtil.e(1.0d, 1, 0), UnitUtil.e(2.0d, 1, 0));
        String c = ncf.c(this.e, this.c, this.b);
        this.g.setText(String.format(Locale.ENGLISH, getResources().getString(R.string._2130848019_res_0x7f022913), c));
        String string = getResources().getString(R.string._2130848136_res_0x7f022988);
        if (this.b == 1) {
            string = getResources().getString(R.string._2130848020_res_0x7f022914);
        }
        this.f8715a.setText(String.format(Locale.ENGLISH, string, c, c));
        this.h.setText(format);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        String e = ncf.e(this.c, this.b);
        boolean c = Utils.c(this.e, e);
        boolean a2 = ncf.a(this.e, e);
        if (!c) {
            this.l = false;
            this.d.setText(getResources().getString(R.string.IDS_device_to_intelligent_home_linkage_go_to_download));
        } else if (a2) {
            this.l = true;
            this.d.setText(getResources().getString(R.string._2130847937_res_0x7f0228c1));
        } else {
            this.l = false;
            this.d.setText(getResources().getString(R.string._2130841550_res_0x7f020fce));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.multi_sim_install_guide_download) {
            LogUtil.a("MultiSimInstallGuideActivity", "onClick download button");
            e();
        } else {
            LogUtil.h("MultiSimInstallGuideActivity", "onClick other");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e() {
        if (this.l) {
            try {
                Intent intent = new Intent(this.e, (Class<?>) MultiSimOpenGuideActivity.class);
                intent.putExtra("simImsi", this.c);
                intent.putExtra("cardType", this.b);
                this.e.startActivity(intent);
                return;
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("MultiSimInstallGuideActivity", "clickDownload ActivityNotFoundException");
                return;
            }
        }
        if (CommonUtil.bh()) {
            d();
        } else {
            b();
        }
    }

    private void d() {
        String e = ncf.e(this.c, this.b);
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("MultiSimInstallGuideActivity", "operatorPackageName is null");
            sqo.o("operatorPackageName is null");
            return;
        }
        try {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=" + e));
            intent.addFlags(268435456);
            if (jdm.b(this.e, "com.huawei.appmarket")) {
                intent.setPackage("com.huawei.appmarket");
                nsn.cLM_(intent, "com.huawei.appmarket", this, getString(R.string.IDS_device_fragment_application_market));
            } else {
                LogUtil.h("MultiSimInstallGuideActivity", "Not installed Market");
                nrh.d(this, this.e.getResources().getString(R.string._2130841726_res_0x7f02107e));
            }
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("MultiSimInstallGuideActivity", "jumpToMarket Exception");
        }
    }

    private void b() {
        final String a2 = ncf.a(this.c, this.b);
        if (TextUtils.isEmpty(a2)) {
            LogUtil.h("MultiSimInstallGuideActivity", "operatorAppId is null");
            sqo.o("operatorAppId is null");
        } else {
            ThreadPoolManager.d().d("MultiSimInstallGuideActivity", new Runnable() { // from class: com.huawei.sim.multisim.activity.MultiSimInstallGuideActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainAppgalleryCloudHuawei");
                    final Intent intent = new Intent();
                    intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
                    intent.setData(Uri.parse(url + a2));
                    PackageManager packageManager = MultiSimInstallGuideActivity.this.e.getPackageManager();
                    if (packageManager != null) {
                        LogUtil.a("MultiSimInstallGuideActivity", "jumpToBrowserDownload packageManager is not null");
                        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
                        if (resolveActivity != null) {
                            LogUtil.a("MultiSimInstallGuideActivity", "jumpToBrowserDownload resolveInfo is not null");
                            ComponentName componentName = new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name);
                            intent.setComponent(componentName);
                            final String packageName = componentName.getPackageName();
                            MultiSimInstallGuideActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.sim.multisim.activity.MultiSimInstallGuideActivity.5.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    nsn.cLM_(intent, packageName, MultiSimInstallGuideActivity.this.e, MultiSimInstallGuideActivity.this.e.getString(R.string._2130847432_res_0x7f0226c8));
                                }
                            });
                        }
                    }
                }
            });
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
