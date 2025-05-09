package com.huawei.ui.main.stories.privacy.template.view.showdata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.stories.privacy.template.contract.PrivacyActivityContract;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.SourceInfoBean;
import com.huawei.ui.main.stories.privacy.template.view.PrivacyDataDetailMvpActivity;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity;
import com.huawei.ui.main.stories.template.BaseView;
import defpackage.nsn;
import defpackage.rsf;
import defpackage.rsp;
import defpackage.rsr;
import defpackage.rxx;
import defpackage.rzf;

/* loaded from: classes.dex */
public class PrivacyDataModelActivity extends PrivacyDataDetailMvpActivity {

    /* renamed from: a, reason: collision with root package name */
    private PrivacyGroupDataFragment f10432a;
    private PrivacyDayDataFragment b;
    private long c;
    private PrivacyDoubleGroupDataFragment d;
    private CustomTitleBar e;
    private SourceInfoBean f;
    private Uri g;
    private String h;
    private PageModelArgs i;
    private CustomTitleBar j;
    private rsf n;
    private String o;

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpActivity
    public int getLayoutId() {
        return R.layout.privacy_activity_data_all;
    }

    @Override // com.huawei.ui.main.stories.template.BaseView
    public Context getViewContext() {
        return this;
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpActivity
    public void initViews() {
    }

    public static void b(Context context, PageModelArgs pageModelArgs) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) PrivacyDataModelActivity.class);
        intent.putExtra("extra_page_model_args", pageModelArgs);
        context.startActivity(intent);
    }

    public static void dQK_(Context context, PageModelArgs pageModelArgs, Parcelable parcelable) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) PrivacyDataModelActivity.class);
        intent.putExtra("extra_page_model_args", pageModelArgs);
        intent.putExtra("extra_source_info", parcelable);
        context.startActivity(intent);
    }

    @Override // com.huawei.ui.main.stories.privacy.template.contract.PrivacyActivityContract.PrivacyActivityView
    public CustomTitleBar getCustomTitleBar() {
        return this.e;
    }

    @Override // com.huawei.ui.main.stories.privacy.template.contract.PrivacyActivityContract.PrivacyActivityView
    public void setTitle(String str) {
        this.e.setTitleText(str);
        this.o = str;
    }

    @Override // com.huawei.ui.main.stories.privacy.template.contract.PrivacyActivityContract.PrivacyActivityView
    public String getTitleBarContent() {
        return this.o;
    }

    @Override // com.huawei.ui.main.stories.privacy.template.contract.PrivacyActivityContract.PrivacyActivityView
    public CustomTitleBar getSpinnerCustomTitleBar() {
        return this.j;
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpActivity
    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.g = AppRouterUtils.zs_(intent);
            PageModelArgs a2 = a();
            this.i = a2;
            if (a2 == null) {
                this.i = (PageModelArgs) intent.getParcelableExtra("extra_page_model_args");
                this.f = (SourceInfoBean) intent.getParcelableExtra("extra_source_info");
            }
            PageModelArgs pageModelArgs = this.i;
            if (pageModelArgs == null) {
                return;
            }
            this.h = pageModelArgs.getServiceId();
            this.c = this.i.getTimestamp();
            c();
            d();
        }
    }

    private PageModelArgs a() {
        Uri uri = this.g;
        if (uri == null) {
            LogUtil.h("PrivacyDataModelActivity", "getPageModelByDeeplink mSchemeData is null.");
            return null;
        }
        String queryParameter = uri.getQueryParameter(CommonUtil.PAGE_TYPE);
        if (TextUtils.isEmpty(queryParameter)) {
            LogUtil.h("PrivacyDataModelActivity", "getPageModelByDeeplink pageType empty.");
            return null;
        }
        if (!nsn.c(queryParameter)) {
            LogUtil.h("PrivacyDataModelActivity", "getPageModelByDeeplink pageType type error.");
            return null;
        }
        PageModelArgs pageModelArgs = new PageModelArgs(Integer.parseInt(queryParameter), "PrivacyDataConstructor", 3, 1);
        pageModelArgs.setClassType(0);
        return pageModelArgs;
    }

    private void c() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.privacy_detail_title_bar);
        this.e = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131297799_res_0x7f090607));
        CustomTitleBar customTitleBar2 = (CustomTitleBar) findViewById(R.id.privacy_spinner_title_bar);
        this.j = customTitleBar2;
        customTitleBar2.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131297799_res_0x7f090607));
        boolean h = rsr.h(this.i);
        this.e.setAppBarVisible(!h);
        this.j.setAppBarVisible(h);
        cancelLayoutById(this.e, this.j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.ui.main.stories.template.health.HealthMvpActivity
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public PrivacyActivityContract.PrivacyActivityPresenter onCreatePresenter() {
        return (PrivacyActivityContract.PrivacyActivityPresenter) rsp.b(this.n.b(), this);
    }

    private void d() {
        rxx.b().d(R.raw._2131886226_res_0x7f120092, new Consumer() { // from class: com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity$$ExternalSyntheticLambda0
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                PrivacyDataModelActivity.this.d((rsf) obj);
            }
        });
    }

    /* synthetic */ void d(rsf rsfVar) {
        if (rsfVar != null) {
            this.n = rsfVar;
            e(rsfVar);
        }
    }

    private void e(final rsf rsfVar) {
        if (rsfVar.b() != null) {
            createPresenter(this.n.b(), this);
            runOnUiThread(new Runnable() { // from class: rtm
                @Override // java.lang.Runnable
                public final void run() {
                    PrivacyDataModelActivity.this.a(rsfVar);
                }
            });
        }
    }

    public /* synthetic */ void a(rsf rsfVar) {
        if (getPresenter() != null && rsfVar.c() != null) {
            getPresenter().initPage(this.i.getModuleType(), this.h, this.c);
        } else {
            LogUtil.b("PrivacyDataModelActivity", "getPresenter = null or config = null");
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.template.contract.PrivacyActivityContract.PrivacyActivityView
    public void showAllDataView() {
        if (this.f10432a == null) {
            this.f10432a = PrivacyGroupDataFragment.dQR_(this.n.e(), this.i, this.f);
            rzf.e(getSupportFragmentManager(), this.f10432a, R.id.privacy_all_data_container);
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.template.contract.PrivacyActivityContract.PrivacyActivityView
    public void showSegmentDataView() {
        if (this.b == null) {
            this.b = PrivacyDayDataFragment.dQL_(this.n.a(), this.i, this.f);
            rzf.e(getSupportFragmentManager(), this.b, R.id.privacy_all_data_container);
        }
    }

    @Override // com.huawei.ui.main.stories.privacy.template.contract.PrivacyActivityContract.PrivacyActivityView
    public void showDoubleGroupDataView() {
        if (this.d == null) {
            this.d = PrivacyDoubleGroupDataFragment.dQP_(this.n.d(), this.i, this.f);
            rzf.e(getSupportFragmentManager(), this.d, R.id.privacy_all_data_container);
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [P extends com.huawei.ui.main.stories.template.BasePresenter, com.huawei.ui.main.stories.template.BasePresenter] */
    @Override // com.huawei.ui.main.stories.template.health.HealthMvpActivity
    public void createPresenter(String str, BaseView baseView) {
        if (this.mPresenter == 0) {
            this.mPresenter = rsp.b(str, baseView);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        PrivacyGroupDataFragment privacyGroupDataFragment = this.f10432a;
        if (privacyGroupDataFragment == null || !privacyGroupDataFragment.onBackPressed()) {
            PrivacyDoubleGroupDataFragment privacyDoubleGroupDataFragment = this.d;
            if (privacyDoubleGroupDataFragment == null || !privacyDoubleGroupDataFragment.onBackPressed()) {
                PrivacyDayDataFragment privacyDayDataFragment = this.b;
                if (privacyDayDataFragment == null || !privacyDayDataFragment.onBackPressed()) {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        rxx.b().c();
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
