package com.huawei.health.suggestion.ui.fitness.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.BaseActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessActionDetailActivity;
import com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy;
import com.huawei.health.suggestion.ui.view.ActionDetailContentView;
import com.huawei.health.suggestion.ui.view.ActionDetailView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.fnj;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class FitnessActionDetailActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private String f3080a;
    private BaseActionDetailPlayerStrategy b;
    private AtomicAction c;
    private fnj d;
    private ActionDetailView e;
    private String h;
    private String i;
    private HealthButton j;
    private CustomTitleBar m;
    private View o;
    private boolean g = false;
    private boolean l = false;
    private int n = 0;
    private Map<String, Object> f = new HashMap(4);

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("action_id");
            String stringExtra2 = intent.getStringExtra("action_version");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.f3080a = stringExtra;
            }
            if (!TextUtils.isEmpty(stringExtra2)) {
                this.h = stringExtra2;
            }
            this.i = intent.getStringExtra("courseAttr");
            String stringExtra3 = intent.getStringExtra(WebViewHelp.BI_KEY_PULL_FROM);
            String stringExtra4 = intent.getStringExtra("resourceId");
            String stringExtra5 = intent.getStringExtra("resourceName");
            String stringExtra6 = intent.getStringExtra("pullOrder");
            LogUtil.a("FitnessActionDetailActivity", "pullFrom:", stringExtra3, "resourceId:", stringExtra4, "resourceName:", stringExtra5, " mActionId:", this.f3080a, "pullOrder:", stringExtra6, "mCourseAttr:", this.i);
            this.f.put(WebViewHelp.BI_KEY_PULL_FROM, stringExtra3);
            this.f.put("resourceId", stringExtra4);
            this.f.put("resourceName", stringExtra5);
            this.f.put("pullOrder", stringExtra6);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        ActionDetailView actionDetailView = this.e;
        if (actionDetailView != null) {
            actionDetailView.e();
            getWindow().clearFlags(128);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ActionDetailView actionDetailView = this.e;
        if (actionDetailView != null) {
            actionDetailView.c();
            getWindow().addFlags(128);
        }
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            this.l = false;
            this.o.setVisibility(0);
            this.j.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessActionDetailActivity.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CommonUtil.q(FitnessActionDetailActivity.this);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            return;
        }
        this.o.setVisibility(8);
        if (!this.l) {
            c();
        } else if (f()) {
            c();
        }
    }

    private boolean f() {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            return false;
        }
        AtomicAction atomicAction = this.c;
        if (atomicAction != null && this.d != null) {
            return fnj.c(atomicAction.getExtendPropertyInt("aiFlag"), this.c.getExtendPropertyInt("aiMeasurement")) && this.d.b();
        }
        LogUtil.h("FitnessActionDetailActivity", "isReLoadData mActionInfo mActionModeBottomViewHelper == null ");
        return false;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ActionDetailView actionDetailView = this.e;
        if (actionDetailView != null) {
            actionDetailView.d();
            this.e = null;
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initLayout() {
        setContentView(R.layout.sug_activity_action_detail);
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initViewController() {
        View findViewById = findViewById(R.id.sug_action_no_data_set_network);
        this.o = findViewById;
        findViewById.setVisibility(8);
        this.j = (HealthButton) findViewById(R.id.btn_no_net_work);
        this.e = (ActionDetailView) findViewById(R.id.sug_action_detail_view);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.sug_action_title_bar);
        this.m = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131299296_res_0x7f090be0));
        this.m.setLeftButtonVisibility(0);
        this.m.setLeftButtonDrawable(ContextCompat.getDrawable(this, R$drawable.ic_health_navbar_close_black), nsf.h(R$string.accessibility_close));
        this.m.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: fkq
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                FitnessActionDetailActivity.this.a();
            }
        });
        b();
        d();
    }

    public /* synthetic */ void a() {
        this.n = this.m.getHeight();
        j();
    }

    private void d() {
        fnj fnjVar = new fnj(this, this.f3080a, this.f);
        this.d = fnjVar;
        fnjVar.aBq_(findViewById(R.id.sug_action_model_select));
        ActionDetailContentView.aLY_(findViewById(R.id.sug_action_model_select));
    }

    private void j() {
        if (this.e.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.e.getLayoutParams();
            if (nsn.ag(BaseApplication.getContext())) {
                layoutParams.topMargin = this.n / 2;
            } else {
                layoutParams.topMargin = 0;
            }
            this.e.setLayoutParams(layoutParams);
        }
    }

    private void c() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessActionDetailActivity.1
            @Override // java.lang.Runnable
            public void run() {
                CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
                if (courseApi != null) {
                    courseApi.getCourseActionInfo(FitnessActionDetailActivity.this.f3080a, FitnessActionDetailActivity.this.h, new UiCallback<AtomicAction>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessActionDetailActivity.1.4
                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        public void onFailure(int i, String str) {
                            LogUtil.h("FitnessActionDetailActivity", "getCourseActionInfo failure");
                            FitnessActionDetailActivity.this.l = false;
                            FitnessActionDetailActivity.this.e();
                        }

                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        /* renamed from: b, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(AtomicAction atomicAction) {
                            FitnessActionDetailActivity.this.l = true;
                            FitnessActionDetailActivity.this.a(atomicAction);
                        }
                    });
                } else {
                    LogUtil.h("FitnessActionDetailActivity", "getNetWorkData : courseApi is null.");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        c(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AtomicAction atomicAction) {
        if (atomicAction == null) {
            LogUtil.h("FitnessActionDetailActivity", "initViewData actionInfo can not null");
        } else {
            this.c = atomicAction;
            c(true);
        }
    }

    private void b() {
        this.e.getDownloadView().setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessActionDetailActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CommonUtil.aa(BaseApplication.getContext()) && !TextUtils.isEmpty(FitnessActionDetailActivity.this.f3080a) && !TextUtils.isEmpty(FitnessActionDetailActivity.this.h)) {
                    FitnessActionDetailActivity.this.g = true;
                    CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
                    if (courseApi != null) {
                        courseApi.getCourseActionInfo(FitnessActionDetailActivity.this.f3080a, FitnessActionDetailActivity.this.h, new UiCallback<AtomicAction>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessActionDetailActivity.5.3
                            @Override // com.huawei.basefitnessadvice.callback.UiCallback
                            public void onFailure(int i, String str) {
                                LogUtil.h("FitnessActionDetailActivity", "getCourseActionInfo Get Action Detail Info Fauile");
                                FitnessActionDetailActivity.this.e();
                            }

                            @Override // com.huawei.basefitnessadvice.callback.UiCallback
                            /* renamed from: b, reason: merged with bridge method [inline-methods] */
                            public void onSuccess(AtomicAction atomicAction) {
                                FitnessActionDetailActivity.this.a(atomicAction);
                            }
                        });
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    } else {
                        LogUtil.h("FitnessActionDetailActivity", "initListener, onClikc: courseApi is null.");
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                }
                LogUtil.h("FitnessActionDetailActivity", "OnClick No network or Param can not null");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.e.getCloseView().setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessActionDetailActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FitnessActionDetailActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.m.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessActionDetailActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FitnessActionDetailActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void c(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessActionDetailActivity.8
            @Override // java.lang.Runnable
            public void run() {
                if (FitnessActionDetailActivity.this.e != null) {
                    FitnessActionDetailActivity.this.e.setVisibility(0);
                    if (z) {
                        if (FitnessActionDetailActivity.this.c != null) {
                            FitnessActionDetailActivity.this.c.putExtendProperty("courseAttr", FitnessActionDetailActivity.this.i);
                        }
                        if (FitnessActionDetailActivity.this.b == null) {
                            FitnessActionDetailActivity.this.e.b(FitnessActionDetailActivity.this.c, 0, null);
                            FitnessActionDetailActivity fitnessActionDetailActivity = FitnessActionDetailActivity.this;
                            fitnessActionDetailActivity.b = fitnessActionDetailActivity.e.getVideoPlayerStrategy();
                        }
                        FitnessActionDetailActivity.this.d.c(FitnessActionDetailActivity.this.c);
                        FitnessActionDetailActivity.this.b.setIsDisplayedNoWifi(FitnessActionDetailActivity.this.g);
                        FitnessActionDetailActivity.this.b.initMediaPlayer();
                        return;
                    }
                    FitnessActionDetailActivity.this.e.refreshHeaderView(8);
                }
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        j();
        this.e.b();
        ActionDetailContentView.aLY_(findViewById(R.id.sug_action_model_select));
    }
}
