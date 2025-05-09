package com.huawei.health.suggestion.ui.fitness.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.common.SearchShowMode;
import com.huawei.health.suggestion.ui.BaseFitnessSearchActivity;
import com.huawei.health.suggestion.ui.fitness.adapter.FitnessActionTypeRecyAdapter;
import com.huawei.health.suggestion.ui.fitness.helper.LoadMoreInterface;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.arx;
import defpackage.foo;
import defpackage.koq;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes8.dex */
public class FitnessActionTypeActivity extends BaseFitnessSearchActivity implements LoadMoreInterface {

    /* renamed from: a, reason: collision with root package name */
    private boolean f3084a;
    private FitnessActionTypeRecyAdapter d;
    private String e;
    private HealthButton g;
    private HealthRecycleView h;
    private View i;
    private int c = 0;
    private final int f = 50;
    private int b = 1;
    private boolean j = false;

    @Override // com.huawei.health.suggestion.ui.BaseFitnessSearchActivity
    public void initNormalModeLayout() {
    }

    @Override // com.huawei.health.suggestion.ui.BaseFitnessSearchActivity
    public void initNormalViewController() {
    }

    static /* synthetic */ int d(FitnessActionTypeActivity fitnessActionTypeActivity, int i) {
        int i2 = fitnessActionTypeActivity.c + i;
        fitnessActionTypeActivity.c = i2;
        return i2;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FitnessActionTypeRecyAdapter fitnessActionTypeRecyAdapter = new FitnessActionTypeRecyAdapter(this.h, this);
        this.d = fitnessActionTypeRecyAdapter;
        fitnessActionTypeRecyAdapter.e(this);
        this.h.setAdapter(this.d);
        this.h.setLayoutManager(new LinearLayoutManager(arx.b()));
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        b();
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!CommonUtil.aa(getApplicationContext())) {
            this.i.setVisibility(0);
            this.j = true;
            this.g.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessActionTypeActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CommonUtil.q(FitnessActionTypeActivity.this);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            return;
        }
        this.i.setVisibility(8);
        if (!this.f3084a && this.c == 0 && this.j) {
            b();
            this.j = false;
        }
    }

    private void b() {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("FitnessActionTypeActivity", "getActionDatas : courseApi is null.");
        } else {
            courseApi.getCourseActionList(this.c, 50, 0, this.b, new UiCallback<List<AtomicAction>>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessActionTypeActivity.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("FitnessActionTypeActivity", "getActonDatas get data failure");
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(Handler handler, int i, String str) {
                    FitnessActionTypeActivity.this.f3084a = false;
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<AtomicAction> list) {
                    LogUtil.h("FitnessActionTypeActivity", "getActonDatas get data success");
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: ayZ_, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Handler handler, final List<AtomicAction> list) {
                    FitnessActionTypeActivity.this.f3084a = koq.c(list) && list.size() >= 50;
                    if (FitnessActionTypeActivity.this.f3084a) {
                        FitnessActionTypeActivity.d(FitnessActionTypeActivity.this, list.size());
                    }
                    FitnessActionTypeActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessActionTypeActivity.5.5
                        @Override // java.lang.Runnable
                        public void run() {
                            FitnessActionTypeActivity.this.d.b(list);
                        }
                    });
                }
            });
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseFitnessSearchActivity, com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        setContentView(R.layout.sug_activity_action_type);
        super.initLayout();
    }

    @Override // com.huawei.health.suggestion.ui.BaseFitnessSearchActivity, com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        super.initViewController();
        this.h = (HealthRecycleView) findViewById(R.id.recyclerview_action_type);
        this.i = findViewById(R.id.sug_action_no_data_set_network);
        this.g = (HealthButton) findViewById(R.id.btn_no_net_work);
        Intent intent = getIntent();
        if (intent != null) {
            this.e = intent.getStringExtra("body_title");
            this.mTitleBar.setTitleText(this.e);
            this.b = intent.getIntExtra("body_title_type", this.b);
            if (this.mFitSearchAllHelper instanceof foo) {
                ((foo) this.mFitSearchAllHelper).c(this.b);
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseFitnessSearchActivity
    public void initTitleBarSearchController() {
        if (this.mTitleBar == null) {
            LogUtil.h("FitnessActionTypeActivity", "initTitleBarSearchController TitleBar can not null");
        } else {
            this.mTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessActionTypeActivity.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (SearchShowMode.NORMAL.equals(FitnessActionTypeActivity.this.mShowModeStatus)) {
                        FitnessActionTypeActivity.this.switchToSearchMode();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.LoadMoreInterface
    public void loadMore() {
        if (this.f3084a) {
            b();
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseFitnessSearchActivity
    public void initSearchViewController() {
        super.initSearchViewController();
        this.mFitSearchAllHelper = new foo(this);
        setOnQueryTextListener(this.mFitSearchAllHelper);
        setLoadMoreListener(this.mFitSearchAllHelper);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
