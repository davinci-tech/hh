package com.huawei.ui.main.stories.ihealthlabs;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.main.stories.ihealthlabs.freeindoorrunning.FreeIndoorRunningActivity;
import defpackage.ixx;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class IHealthLabsAcitivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f10320a;
    private RelativeLayout b;
    private LinearLayout c;
    private ImageView e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("IHealthLabsAcitivity", "onCreate");
        setContentView(R.layout.activity_ihealth_labs);
        a();
        b();
        getWindow().clearFlags(AppRouterExtras.COLDSTART);
        getWindow().addFlags(Integer.MIN_VALUE);
        getWindow().setStatusBarColor(0);
    }

    private void b() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.free_indoor_running);
        this.b = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.e = (ImageView) findViewById(R.id.iHealth_lab_background);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.free_indoor_running_img_layout);
        this.c = linearLayout;
        nsn.cLA_(linearLayout, 2);
        this.f10320a = (ImageView) findViewById(R.id.indoor_arrow);
        Context context = BaseApplication.getContext();
        boolean bc = LanguageUtil.bc(context);
        BitmapDrawable cKn_ = nrz.cKn_(context, R.drawable.pic_laboratory);
        if (bc) {
            this.f10320a.setImageDrawable(nrz.cKn_(context, R.drawable.ic_health_list_arrow_gray));
            this.e.setImageDrawable(cKn_);
        } else {
            this.e.setImageDrawable(context.getResources().getDrawable(R.drawable.pic_laboratory));
            this.f10320a.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_health_list_arrow_gray));
        }
    }

    private void a() {
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), "ihealthredpoint", "clicked", new StorageParams());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.a("IHealthLabsAcitivity", "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.free_indoor_running) {
            HashMap hashMap = new HashMap(1);
            hashMap.put("click", 1);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_MINE_FREE_INDOOR_RUNNING_2040073.value(), hashMap, 0);
            startActivity(new Intent(this, (Class<?>) FreeIndoorRunningActivity.class));
        } else {
            LogUtil.a("IHealthLabsAcitivity", "onClick else");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
