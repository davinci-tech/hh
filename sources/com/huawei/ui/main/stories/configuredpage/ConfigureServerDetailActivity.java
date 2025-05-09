package com.huawei.ui.main.stories.configuredpage;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.stories.configuredpage.adpters.ConfiguredServerAdapter;
import defpackage.cdu;
import defpackage.cdy;
import defpackage.koq;
import defpackage.mfm;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class ConfigureServerDetailActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private CustomTitleBar f9685a;
    private ConfiguredServerAdapter b;
    private List<cdu> c = new ArrayList(16);
    private cdy d;
    private Context e;
    private LinearLayoutManager i;
    private HealthRecycleView j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.hw_configure_server_detail_layout);
        this.e = this;
        e();
        b();
    }

    private void e() {
        this.f9685a = (CustomTitleBar) mfm.cgL_(this, R.id.title_layout);
        this.j = (HealthRecycleView) mfm.cgL_(this, R.id.server_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 1, false);
        this.i = linearLayoutManager;
        this.j.setLayoutManager(linearLayoutManager);
        ConfiguredServerAdapter configuredServerAdapter = new ConfiguredServerAdapter(this, this.c);
        this.b = configuredServerAdapter;
        this.j.setAdapter(configuredServerAdapter);
    }

    private void b() {
        if (getIntent() == null) {
            LogUtil.h("ConfigureServerDetailActivity", "getIntent() == null!");
            return;
        }
        cdy cdyVar = (cdy) getIntent().getSerializableExtra("server_name");
        this.d = cdyVar;
        if (cdyVar == null) {
            LogUtil.h("ConfigureServerDetailActivity", "mPageModule == null!");
            return;
        }
        if (!TextUtils.isEmpty(cdyVar.g())) {
            this.f9685a.setTitleText(this.d.g());
        }
        List<cdu> e = this.d.e();
        this.c = e;
        if (!koq.b(e)) {
            this.b.d(this.c);
        } else {
            LogUtil.h("ConfigureServerDetailActivity", "initData() mList is empty.");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
