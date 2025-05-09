package com.huawei.ui.main.stories.template.health.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BloodSugarBootPagerHelper;
import com.huawei.ui.main.stories.template.ComponentParam;
import com.huawei.ui.main.stories.template.ResourceParseHelper;
import com.huawei.ui.main.stories.template.health.HealthMvpActivity;
import com.huawei.ui.main.stories.template.health.contract.DataDetailActivityContract;
import com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity;
import com.huawei.ui.main.stories.template.health.module.hasdata.HealthHasDataFragment;
import com.huawei.ui.main.stories.template.health.module.nodevice.HealthNoDeviceFragment;
import com.huawei.ui.main.stories.template.health.module.nodevice.bean.NoDataPageInfoBean;
import defpackage.efb;
import defpackage.gnm;
import defpackage.qle;
import defpackage.rxx;
import defpackage.rye;
import defpackage.ryk;
import defpackage.ryl;
import defpackage.rze;
import defpackage.rzf;
import defpackage.rzh;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public class HealthDataDetailActivity extends HealthMvpActivity<DataDetailActivityContract.DetailActivityPresenter> implements DataDetailActivityContract.DetailActivityView {
    private static final AtomicBoolean d = new AtomicBoolean(false);

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f10516a;
    private Activity b;
    private CustomTitleBar c;
    private HealthHasDataFragment e;
    private HealthToolBar f;
    private FrameLayout g;
    private FrameLayout h;
    private LinearLayout i;
    private HealthNoDeviceFragment j;
    private String k;
    private ResourceParseHelper l;
    private a m = new a(this);
    private int n;
    private long o;
    private ryk p;

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpActivity
    public int getLayoutId() {
        return R.layout.activity_health_data_detail;
    }

    @Override // com.huawei.ui.main.stories.template.BaseView
    public Context getViewContext() {
        return this;
    }

    public static void a(Context context, String str, int i) {
        c(context, str, i, 0L);
    }

    public static void c(Context context, String str, int i, long j) {
        dUg_(context, str, i, j, null);
    }

    public static void dUg_(Context context, String str, int i, long j, Bundle bundle) {
        Intent intent = new Intent(context, (Class<?>) HealthDataDetailActivity.class);
        intent.putExtra("extra_service_id", str);
        intent.putExtra("extra_time_stamp", j);
        intent.putExtra("extra_page_type", i);
        if (bundle != null) {
            intent.putExtra("extra_bundle", bundle);
        }
        gnm.aPB_(context, intent);
        Activity activity = BaseApplication.getActivity();
        if (activity == null) {
            LogUtil.h("HealthDataDetailActivity", "launch context is null. return");
        } else {
            activity.overridePendingTransition(0, R.anim._2130771981_res_0x7f01000d);
        }
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpActivity
    public void create() {
        getWindow().setBackgroundDrawableResource(R$color.health_chart_extend_background_color);
        setContentView(getLayoutId());
        initViews();
        initData();
    }

    private void b(final boolean z) {
        if (this.mPresenter instanceof qle) {
            if (Utils.o() || ((qle) this.mPresenter).e((IBaseResponseCallback) null, 0L) > 0 || !efb.n()) {
                if (z) {
                    a(true);
                    return;
                }
                return;
            }
            BloodSugarBootPagerHelper bloodSugarBootPagerHelper = new BloodSugarBootPagerHelper(getViewContext());
            bloodSugarBootPagerHelper.a(new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i == 0 && z) {
                        HealthDataDetailActivity.this.a(false);
                    }
                }
            });
            bloodSugarBootPagerHelper.showBootPage(this.f10516a);
            LinearLayout linearLayout = this.f10516a;
            if (linearLayout != null) {
                linearLayout.setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        int i = z ? 600 : 200;
        HealthToolBar healthToolBar = this.f;
        if (healthToolBar != null) {
            healthToolBar.postDelayed(this.m, i);
        }
    }

    /* loaded from: classes7.dex */
    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<HealthDataDetailActivity> f10518a;

        a(HealthDataDetailActivity healthDataDetailActivity) {
            this.f10518a = new WeakReference<>(healthDataDetailActivity);
        }

        @Override // java.lang.Runnable
        public void run() {
            HealthDataDetailActivity healthDataDetailActivity = this.f10518a.get();
            if (healthDataDetailActivity != null && !healthDataDetailActivity.isDestroyed() && !healthDataDetailActivity.isFinishing()) {
                if (healthDataDetailActivity.mPresenter instanceof qle) {
                    ((qle) healthDataDetailActivity.mPresenter).a();
                    return;
                }
                return;
            }
            ReleaseLogUtil.d("HealthDataDetailActivity", "activity is null");
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        dUf_(intent);
        Bundle bundleExtra = intent.getBundleExtra("extra_bundle");
        if (bundleExtra == null || !"jump_from_notify".equals(bundleExtra.getString("jump_from_tag", ""))) {
            return;
        }
        finish();
        gnm.aPB_(this, intent);
    }

    private void dUf_(Intent intent) {
        Intent intent2 = getIntent();
        if (intent == null || intent2 == null || intent2.getIntExtra("extra_page_type", -1) == intent.getIntExtra("extra_page_type", -1)) {
            return;
        }
        int intExtra = intent.getIntExtra("extra_page_type", 0);
        if (8 == intExtra || 24 == intExtra) {
            setIntent(intent);
            this.mPresenter = null;
            this.e = null;
            setContentView(getLayoutId());
            initViews();
            initData();
        }
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpActivity
    public void initViews() {
        this.c = (CustomTitleBar) findViewById(R.id.health_detail_title_bar);
        this.f = (HealthToolBar) findViewById(R.id.health_detail_tool_bar);
        this.h = (FrameLayout) findViewById(R.id.health_detail_has_data_container);
        this.g = (FrameLayout) findViewById(R.id.health_detail_no_data_container);
        this.i = (LinearLayout) findViewById(R.id.health_detail_loading);
        this.f10516a = (LinearLayout) findViewById(R.id.extra_guide_layout_container);
        setMinibarBottomMargin(getResources().getDimensionPixelSize(R.dimen._2131363906_res_0x7f0a0842));
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpActivity
    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.k = intent.getStringExtra("extra_service_id");
            this.n = intent.getIntExtra("extra_page_type", 1);
            this.o = intent.getLongExtra("extra_time_stamp", 0L);
            b();
        }
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpActivity
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public DataDetailActivityContract.DetailActivityPresenter onCreatePresenter() {
        String c2 = this.p.c();
        LogUtil.c("HealthDataDetailActivity", "onCreatePresenter:" + c2);
        return (DataDetailActivityContract.DetailActivityPresenter) rze.d(c2, this);
    }

    public void b() {
        this.b = this;
        this.l = new ResourceParseHelper();
        if (Utils.o() && "BodyTemperatureCardConstructor".equals(this.k) && TextUtils.isEmpty(rye.b(this.k))) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ryp
                @Override // java.lang.Runnable
                public final void run() {
                    HealthDataDetailActivity.this.i();
                }
            });
        } else {
            rxx.b().e(this.k, new Consumer() { // from class: com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity$$ExternalSyntheticLambda3
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    HealthDataDetailActivity.this.d((ryk) obj);
                }
            });
            j();
        }
    }

    /* synthetic */ void d(ryk rykVar) {
        if (rykVar != null) {
            LogUtil.a("HealthDataDetailActivity", "has down config");
            this.p = rykVar;
            a(rykVar);
        } else {
            LogUtil.a("HealthDataDetailActivity", "readLocalConfig");
            i();
            j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        int i;
        if ("BloodSugarCardConstructor".equals(this.k)) {
            i = R.raw._2131886083_res_0x7f120003;
        } else if ("BodyTemperatureCardConstructor".equals(this.k)) {
            i = R.raw._2131886084_res_0x7f120004;
        } else {
            LogUtil.h("HealthDataDetailActivity", "inputStream is null");
            i = -1;
        }
        if (i == -1) {
            return;
        }
        rxx.b().d(i, new Consumer() { // from class: com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity$$ExternalSyntheticLambda0
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                HealthDataDetailActivity.this.b((ryk) obj);
            }
        });
    }

    /* synthetic */ void b(ryk rykVar) {
        Activity activity;
        if (rykVar == null || (activity = this.b) == null || activity.isFinishing() || this.b.isDestroyed()) {
            return;
        }
        this.p = rykVar;
        a(rykVar);
    }

    private void j() {
        AtomicBoolean atomicBoolean = d;
        if (atomicBoolean.get() || !this.l.e(this.k)) {
            return;
        }
        atomicBoolean.set(true);
        this.l.a(this.k, new c());
    }

    private void a(final ryk rykVar) {
        if (rykVar != null && rykVar.c() != null) {
            a();
            createPresenter(this.p.c(), this);
            runOnUiThread(new Runnable() { // from class: ryr
                @Override // java.lang.Runnable
                public final void run() {
                    HealthDataDetailActivity.this.c(rykVar);
                }
            });
            return;
        }
        LogUtil.b("HealthDataDetailActivity", "healthDetailTemplateConfig = null");
    }

    public /* synthetic */ void c(ryk rykVar) {
        if (getPresenter() != null && rykVar.a() != null) {
            getPresenter().initPage(rykVar.a().a(), this.o, this.k);
        } else {
            LogUtil.b("HealthDataDetailActivity", "getPresenter = null or config.getInfo() = null");
        }
    }

    private void a() {
        Bundle bundleExtra;
        if (!"health_common_activity_presenter".equals(this.p.c()) || (bundleExtra = getIntent().getBundleExtra("extra_bundle")) == null) {
            return;
        }
        ComponentParam componentParam = new ComponentParam(null);
        componentParam.setmName("jump_from_tag");
        componentParam.setmValue(bundleExtra.getString("jump_from_tag", ""));
        this.p.d().getDayFragmentConfig().getLayoutConfig().getChartView().getParams().add(componentParam);
    }

    @Override // com.huawei.ui.main.stories.template.health.contract.DataDetailActivityContract.DetailActivityView
    public void showDataView(long j) {
        int e;
        this.i.setVisibility(8);
        HealthHasDataFragment healthHasDataFragment = this.e;
        if (healthHasDataFragment == null) {
            ryl operationData = this.p.d().getOperationData();
            if (operationData != null) {
                e = operationData.e();
            } else {
                e = this.p.b().e();
            }
            this.n = e;
            this.o = j;
            HealthHasDataFragment healthHasDataFragment2 = new HealthHasDataFragment();
            this.e = healthHasDataFragment2;
            healthHasDataFragment2.e(this.p.d(), this.n, this.o);
            rzf.e(getSupportFragmentManager(), this.e, R.id.health_detail_has_data_container);
        } else {
            healthHasDataFragment.e(j);
        }
        this.g.setVisibility(8);
        this.h.setVisibility(0);
        this.c.setTitleBarBackgroundColor(getResources().getColor(R.color._2131298875_res_0x7f090a3b, null));
        b(true);
        HealthNoDeviceFragment healthNoDeviceFragment = this.j;
        if (healthNoDeviceFragment == null || !healthNoDeviceFragment.isAdded()) {
            return;
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.remove(this.j);
        beginTransaction.commitNowAllowingStateLoss();
        this.j = null;
    }

    @Override // com.huawei.ui.main.stories.template.health.contract.DataDetailActivityContract.DetailActivityView
    public void showNoDataView(boolean z) {
        int e;
        NoDataPageInfoBean noDataPageInfoBean;
        int e2;
        this.i.setVisibility(8);
        if (z) {
            if (this.g.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                ((RelativeLayout.LayoutParams) this.g.getLayoutParams()).addRule(3, R.id.health_detail_title_bar);
            }
            ryl operationData = this.p.d().getOperationData();
            if (operationData != null) {
                e2 = operationData.e();
            } else {
                e2 = this.p.b().e();
            }
            this.n = e2;
            noDataPageInfoBean = new NoDataPageInfoBean(this.k, e2, this.p.a().d(), this.p.a().c());
            noDataPageInfoBean.setHasDevice();
        } else {
            ryl operationData2 = this.p.e().getOperationData();
            if (operationData2 != null) {
                e = operationData2.e();
            } else {
                e = this.p.b().e();
            }
            this.n = e;
            noDataPageInfoBean = new NoDataPageInfoBean(this.k, e, this.p.a().e(), this.p.a().b());
        }
        LogUtil.a("HealthDataDetailActivity", "no data config ", noDataPageInfoBean.toString());
        this.j = HealthNoDeviceFragment.e(this.p.e(), noDataPageInfoBean);
        rzf.e(getSupportFragmentManager(), this.j, R.id.health_detail_no_data_container);
        this.h.setVisibility(8);
        this.g.setVisibility(0);
        this.c.setTitleBarBackgroundColor(getResources().getColor(R.color._2131298874_res_0x7f090a3a));
        b(false);
    }

    @Override // com.huawei.ui.main.stories.template.health.contract.DataDetailActivityContract.DetailActivityView
    public CustomTitleBar getCustomTitleBar() {
        return this.c;
    }

    @Override // com.huawei.ui.main.stories.template.health.contract.DataDetailActivityContract.DetailActivityView
    public HealthToolBar getHealthToolBar() {
        return this.f;
    }

    @Override // com.huawei.ui.main.stories.template.health.contract.DataDetailActivityContract.DetailActivityView
    public void setTitle(String str) {
        this.c.setTitleText(str);
    }

    @Override // com.huawei.ui.main.stories.template.health.HealthMvpActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        rxx.b().c();
        HealthToolBar healthToolBar = this.f;
        if (healthToolBar != null) {
            healthToolBar.removeCallbacks(this.m);
            this.f = null;
        }
        if (this.b != null) {
            this.b = null;
        }
        if (this.l != null) {
            this.l = null;
        }
        if (this.e != null) {
            this.e = null;
        }
        if (this.j != null) {
            this.j = null;
        }
        rzh.d();
        super.onDestroy();
    }

    public long c() {
        return this.o;
    }

    /* loaded from: classes7.dex */
    static class c implements ResourceParseHelper.JsonResult {
        private WeakReference<HealthDataDetailActivity> c;

        private c(HealthDataDetailActivity healthDataDetailActivity) {
            this.c = new WeakReference<>(healthDataDetailActivity);
        }

        @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.JsonResult
        public void onResult(ryk rykVar) {
            LogUtil.a("HealthDataDetailActivity", "requestConfig onResult");
            WeakReference<HealthDataDetailActivity> weakReference = this.c;
            if (weakReference == null) {
                LogUtil.b("HealthDataDetailActivity", "mWeakReference == null");
                return;
            }
            if (weakReference.get() == null) {
                LogUtil.b("HealthDataDetailActivity", "activity == null");
            }
            HealthDataDetailActivity.d.set(false);
        }

        @Override // com.huawei.ui.main.stories.template.ResourceParseHelper.JsonResult
        public void onFail() {
            LogUtil.a("HealthDataDetailActivity", "requestConfig onFail");
            WeakReference<HealthDataDetailActivity> weakReference = this.c;
            if (weakReference == null) {
                LogUtil.b("HealthDataDetailActivity", "mWeakReference == null");
                return;
            }
            if (weakReference.get() == null) {
                LogUtil.b("HealthDataDetailActivity", "activity == null");
            }
            HealthDataDetailActivity.d.set(false);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
