package com.huawei.health.suggestion.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ui.fitness.activity.fragment.TrainEventBaseFragment;
import com.huawei.hihealthservice.db.table.DBSessionCommon;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.nqx;
import defpackage.smy;
import defpackage.sqq;
import health.compact.a.Services;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class TrainEventActivity extends BaseStateActivity {
    private smy b;
    private nqx c;
    private Plan d;
    private String[] f;
    private int g;
    private HealthSubTabWidget h;
    private HealthViewPager i;
    private String j;
    private int k;
    private smy n;
    private smy o;
    private List<TrainEventBaseFragment> l = new ArrayList(10);

    /* renamed from: a, reason: collision with root package name */
    private PointF f3061a = new PointF(0.0f, 0.0f);
    private boolean e = true;

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public int getLoadingLayoutId() {
        return R.layout.sug_loading_layout;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    protected int setLoadingBackgroundColor() {
        return 0;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(16777216, 16777216);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        setContentView(R.layout.sug_activity_train_event2);
        this.k = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        HealthToolBar healthToolBar = (HealthToolBar) findViewById(R.id.tool_bar);
        healthToolBar.cHc_(View.inflate(this, R.layout.hw_toolbar_bottomview, null));
        healthToolBar.setIconTitle(2, getString(R.string._2130848444_res_0x7f022abc));
        if (sqq.d()) {
            healthToolBar.setIcon(2, R.drawable._2131431641_res_0x7f0b10d9);
        } else {
            healthToolBar.setIcon(2, R.drawable._2131430068_res_0x7f0b0ab4);
        }
        healthToolBar.cHf_(this);
        healthToolBar.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: com.huawei.health.suggestion.ui.TrainEventActivity.1
            @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
            public void onSingleTap(int i) {
                Intent intent = new Intent(TrainEventActivity.this.getApplicationContext(), (Class<?>) TrainReportActivity.class);
                intent.putExtra("plan", TrainEventActivity.this.d);
                TrainEventActivity.this.startActivity(intent);
            }
        });
        HealthViewPager healthViewPager = (HealthViewPager) findViewById(R.id.sug_train_event_vp);
        this.i = healthViewPager;
        BaseActivity.cancelLayoutById(healthViewPager);
        this.i.setIsScroll(false);
        this.h = (HealthSubTabWidget) findViewById(R.id.sug_event_pst);
        this.f = new String[]{getString(R.string._2130848406_res_0x7f022a96), getString(R.string._2130848407_res_0x7f022a97), getString(R.string._2130848408_res_0x7f022a98)};
        this.c = new nqx(this, this.i, this.h);
        this.o = this.h.c(this.f[0]);
        this.b = this.h.c(this.f[1]);
        this.n = this.h.c(this.f[2]);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
        initData();
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        this.l = new ArrayList(10);
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Suggestion_TrainEventActivity", "the intent is null");
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("planid");
        this.j = stringExtra;
        if (stringExtra == null) {
            LogUtil.h("Suggestion_TrainEventActivity", "the planid is null --initData");
            finish();
            return;
        }
        this.g = intent.getIntExtra("plantype", 1);
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_TrainEventActivity", "TrainEventActivity, initData : planApi is null.");
            return;
        }
        planApi.setPlanType(this.g);
        Plan plan = planApi.getjoinedPlanById(this.j);
        this.d = plan;
        if (plan != null) {
            a();
            CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            if (courseApi != null) {
                courseApi.getWorkoutRecords(this.j, new c(this));
                return;
            }
            return;
        }
        LogUtil.h("Suggestion_TrainEventActivity", "get null data of the plan's history record,destroy the view");
        finish();
    }

    static class c extends UiCallback<List<WorkoutRecord>> {
        WeakReference<TrainEventActivity> e;

        c(TrainEventActivity trainEventActivity) {
            this.e = new WeakReference<>(trainEventActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            TrainEventActivity trainEventActivity = this.e.get();
            if (trainEventActivity == null) {
                LogUtil.b("Suggestion_TrainEventActivity", "onFailure activity is null");
                return;
            }
            if (trainEventActivity.mTitleBar != null) {
                trainEventActivity.mTitleBar.setTitleText(StringUtils.c((Object) trainEventActivity.d.acquireName()));
            }
            trainEventActivity.b();
            trainEventActivity.showErrorLayout();
            LogUtil.h("Suggestion_TrainEventActivity", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<WorkoutRecord> list) {
            TrainEventActivity trainEventActivity = this.e.get();
            if (trainEventActivity == null) {
                LogUtil.b("Suggestion_TrainEventActivity", "onSuccess activity is null");
                return;
            }
            if (trainEventActivity.mTitleBar != null) {
                trainEventActivity.mTitleBar.setTitleText(StringUtils.c((Object) trainEventActivity.d.acquireName()));
            }
            trainEventActivity.b();
            trainEventActivity.finishLoading();
        }
    }

    private void a() {
        TrainEventBaseFragment trainEventBaseFragment = new TrainEventBaseFragment();
        TrainEventBaseFragment trainEventBaseFragment2 = new TrainEventBaseFragment();
        TrainEventBaseFragment trainEventBaseFragment3 = new TrainEventBaseFragment();
        b(trainEventBaseFragment, 2);
        b(trainEventBaseFragment2, 3);
        b(trainEventBaseFragment3, 4);
        this.l.add(trainEventBaseFragment);
        this.l.add(trainEventBaseFragment2);
        this.l.add(trainEventBaseFragment3);
    }

    private void b(TrainEventBaseFragment trainEventBaseFragment, int i) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("joined", this.d);
        bundle.putString("planid", this.j);
        bundle.putInt(DBSessionCommon.COLUMN_TIME_ZONE, i);
        bundle.putInt("plantype", this.g);
        trainEventBaseFragment.setArguments(bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.i.setOffscreenPageLimit(this.l.size());
        if (this.e) {
            this.e = false;
            this.c.c(this.o, this.l.get(0), true);
            this.c.c(this.b, this.l.get(1), false);
            this.c.c(this.n, this.l.get(2), false);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f3061a.x = motionEvent.getX();
            this.f3061a.y = motionEvent.getY();
        } else if (motionEvent.getAction() == 2 && Math.abs(motionEvent.getX() - this.f3061a.x) > this.k * 3) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
