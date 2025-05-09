package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.template.BaseActivity;
import com.huawei.up.model.UserInfomation;
import defpackage.nsy;
import defpackage.qrv;
import health.compact.a.Services;
import health.compact.a.UnitUtil;

/* loaded from: classes9.dex */
public class Vo2maxSpecificationActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private int f10091a;
    private UserInfomation b;
    private int c;
    private CustomTitleBar d;
    private Integer[] e;
    private Vo2MaxHelp j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_vo2max_specification_layout);
        c();
        a();
    }

    private void a() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        this.b = userInfo;
        if (userInfo == null) {
            LogUtil.b("Track_Vo2maxSpecificationActivity", "mLocalUserInfo is null");
            this.b = new UserInfomation(UnitUtil.h() ? 1 : 0);
        }
        this.f10091a = this.b.getGender();
        int age = this.b.getAge();
        this.c = age;
        this.e = qrv.a(this.f10091a, age);
        Vo2MaxHelp vo2MaxHelp = new Vo2MaxHelp(this);
        this.j = vo2MaxHelp;
        vo2MaxHelp.c(this.e, this.f10091a);
        this.j.dBm_(findViewById(R.id.specification_help_view));
    }

    private void c() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.vo2max_speci_title_bar);
        this.d = customTitleBar;
        customTitleBar.setTitleText(getString(R$string.IDS_fitness_core_sleep_explain_title));
        this.d.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.Vo2maxSpecificationActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Vo2maxSpecificationActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
