package com.huawei.ui.thirdpartservice.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.thirdpartservice.activity.DeviceShareActivity;
import com.huawei.ui.thirdpartservice.activity.healthkitthirdparty.ThirdPartAppAdapter;
import defpackage.koq;
import defpackage.nsy;
import java.util.List;

/* loaded from: classes9.dex */
public class DeviceShareActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Context f10538a;
    private List<HiAppInfo> b;
    private HealthRecycleView c;
    private ThirdPartAppAdapter d;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_share);
        this.f10538a = this;
        b();
        d();
    }

    private void d() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        try {
            this.b = intent.getParcelableArrayListExtra(KnitFragment.KEY_EXTRA);
        } catch (Exception unused) {
            LogUtil.b("DeviceShareActivity", "initData Exception");
        }
        c();
    }

    private void b() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) nsy.cMc_(this, R.id.rv_device_share);
        this.c = healthRecycleView;
        healthRecycleView.a(false);
        this.c.d(false);
    }

    private void c() {
        if (koq.b(this.b)) {
            LogUtil.h("DeviceShareActivity", "addThirdPartyAppsInView list null");
            return;
        }
        this.c.setLayoutManager(new LinearLayoutManager(this));
        this.d = new ThirdPartAppAdapter(this.b);
        LogUtil.c("DeviceShareActivity", "appinfo size = ", Integer.valueOf(this.b.size()));
        this.d.d(new ThirdPartAppAdapter.ItemClickListener() { // from class: sdw
            @Override // com.huawei.ui.thirdpartservice.activity.healthkitthirdparty.ThirdPartAppAdapter.ItemClickListener
            public final void onItemClick(View view, int i) {
                DeviceShareActivity.this.dWo_(view, i);
            }
        });
        this.c.setAdapter(this.d);
        this.d.notifyDataSetChanged();
    }

    public /* synthetic */ void dWo_(View view, int i) {
        LogUtil.c("DeviceShareActivity", "appinfo onItemClick position = ", Integer.valueOf(i));
        Intent intent = new Intent();
        intent.setClassName(this.f10538a, "com.huawei.ui.main.stories.me.activity.thirdparty.WearKitAuthActivity");
        if (koq.d(this.b, i)) {
            intent.putExtra(MapKeyNames.APP_INFO, this.b.get(i));
        } else {
            LogUtil.h("DeviceShareActivity", "addThirdPartyAppsInView onItemClick isInBounds is false");
        }
        this.f10538a.startActivity(intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
