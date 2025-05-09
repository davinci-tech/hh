package com.huawei.health.suggestion.ui.run.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.basesport.helper.HeartRateConfigHelper;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder;
import com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseActionAdapter;
import com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseTouchHelperCallBack;
import com.huawei.health.suggestion.customizecourse.manager.adapter.interfaceadapter.ItemEventListener;
import com.huawei.health.suggestion.customizecourse.manager.adapter.interfaceadapter.OnStartDragListener;
import com.huawei.health.suggestion.customizecourse.manager.model.CourseConfig;
import com.huawei.health.suggestion.model.customcourse.TypeConfig;
import com.huawei.health.suggestion.ui.BaseStateActivity;
import com.huawei.health.suggestion.ui.run.activity.CustomCourseActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.pluginfitnessadvice.ChoreographedMultiActions;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.TargetConfig;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ash;
import defpackage.ffg;
import defpackage.ffl;
import defpackage.fhq;
import defpackage.fjc;
import defpackage.fjd;
import defpackage.fpq;
import defpackage.gge;
import defpackage.jdl;
import defpackage.jdx;
import defpackage.koq;
import defpackage.mmp;
import defpackage.mod;
import defpackage.nrh;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes4.dex */
public class CustomCourseActivity extends BaseStateActivity implements OnStartDragListener, ItemEventListener, EditDialogBuilder.OnDismissListener {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f3316a = new Object();
    private HealthButton b;
    private CustomCourseActionAdapter c;
    private CourseApi d;
    private HealthButton e;
    private CourseConfig f;
    private String g;
    private EditDialogBuilder h;
    private HealthRecycleView j;
    private String l;
    private HeartRateConfigHelper m;
    private FitWorkout n;
    private ItemTouchHelper p;
    private CustomViewDialog q;
    private CustomTitleBar r;
    private Map<String, ChoreographedSingleAction> s;
    private ffg t;
    private int i = 0;
    private boolean k = true;
    private Handler o = new a(Looper.getMainLooper(), this);

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        setContentView(R.layout.sug_activity_custom_course);
        this.r = (CustomTitleBar) findViewById(R.id.sug_title_bar);
        this.e = (HealthButton) findViewById(R.id.btn_new_combine);
        this.b = (HealthButton) findViewById(R.id.btn_add_action);
        this.mLoadingView = findViewById(R.id.sug_loading_layout);
        this.h = new EditDialogBuilder(this, this);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        t();
        final HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        hashMap.put("type", 1);
        hashMap.put("result", 0);
        this.e.setOnClickListener(new View.OnClickListener() { // from class: gbz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomCourseActivity.this.aJY_(hashMap, view);
            }
        });
        this.b.setOnClickListener(new View.OnClickListener() { // from class: gcb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomCourseActivity.this.aJZ_(hashMap, view);
            }
        });
        this.j = (HealthRecycleView) findViewById(R.id.sug_custom_course_recycle_view);
        this.j.setLayoutManager(new CustomCourseLinearLayoutManager(this));
        CustomCourseActionAdapter customCourseActionAdapter = new CustomCourseActionAdapter(this, this, this);
        this.c = customCourseActionAdapter;
        this.j.setAdapter(customCourseActionAdapter);
        this.j.a(false);
        this.j.d(false);
        if (this.j.getItemAnimator() instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) this.j.getItemAnimator()).setSupportsChangeAnimations(false);
        }
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new CustomCourseTouchHelperCallBack(this.c));
        this.p = itemTouchHelper;
        itemTouchHelper.attachToRecyclerView(this.j);
    }

    public /* synthetic */ void aJY_(Map map, View view) {
        if (view == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "mBtnNewCombine view is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            i();
            map.put("event", 1);
            gge.e("1130056", map);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void aJZ_(Map map, View view) {
        if (view == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "mBtnAddAction view is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            a();
            map.put("event", 2);
            gge.e("1130056", map);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void a() {
        if (this.n == null || this.c == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "addAction, mFitWorkout is null or mAdapter is null");
            return;
        }
        LogUtil.a("Suggestion_CustomCourseActivity", "addAction, mBtnAddAction is enter...");
        if (c(false) != 0) {
            LogUtil.h("Suggestion_CustomCourseActivity", "addAction, action num is max");
            nrh.d(BaseApplication.getContext(), getResources().getString(R.string._2130848638_res_0x7f022b7e));
            return;
        }
        ChoreographedSingleAction r = r();
        if (r == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "add action failed.");
            return;
        }
        ChoreographedMultiActions choreographedMultiActions = new ChoreographedMultiActions();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(r);
        choreographedMultiActions.setActionList(arrayList);
        choreographedMultiActions.setRepeatTimes(1);
        this.c.d(choreographedMultiActions);
        this.c.notifyDataSetChanged();
        v();
    }

    private void i() {
        if (this.f == null || this.n == null || this.c == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "addMulti, mCourseConfig is null or mFitWorkout is null or mAdapter is null");
            return;
        }
        LogUtil.a("Suggestion_CustomCourseActivity", "addMulti, mBtnNewCombine is enter");
        if (c(true) != 0) {
            LogUtil.h("Suggestion_CustomCourseActivity", "addMulti, action num is max");
            nrh.d(BaseApplication.getContext(), getResources().getString(R.string._2130848638_res_0x7f022b7e));
            return;
        }
        List<Attribute> defaultCombinationActions = this.f.getDefaultCombinationActions();
        ArrayList arrayList = new ArrayList();
        if (koq.c(defaultCombinationActions)) {
            Iterator<Attribute> it = defaultCombinationActions.iterator();
            while (it.hasNext()) {
                ChoreographedSingleAction d = d(it.next().getId());
                if (d != null) {
                    arrayList.add(d);
                }
            }
        }
        ChoreographedMultiActions choreographedMultiActions = new ChoreographedMultiActions();
        choreographedMultiActions.setActionList(arrayList);
        choreographedMultiActions.setRepeatTimes(defaultCombinationActions.size());
        if (koq.b(choreographedMultiActions.getActionList())) {
            LogUtil.h("Suggestion_CustomCourseActivity", "addMulti, actions.getActionList() is empty");
            return;
        }
        this.c.c(choreographedMultiActions);
        this.c.notifyDataSetChanged();
        v();
    }

    private void v() {
        this.o.post(new Runnable() { // from class: gca
            @Override // java.lang.Runnable
            public final void run() {
                CustomCourseActivity.this.b();
            }
        });
    }

    public /* synthetic */ void b() {
        CustomCourseActionAdapter customCourseActionAdapter;
        HealthRecycleView healthRecycleView = this.j;
        if (healthRecycleView == null || (customCourseActionAdapter = this.c) == null) {
            return;
        }
        healthRecycleView.scrollToPosition(customCourseActionAdapter.getItemCount() - 1);
    }

    private int c(boolean z) {
        int i;
        int i2;
        if (koq.b(this.c.b()) || this.f == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "mAdapter.getActions() is empty or mCourseConfig is or");
            return 0;
        }
        Iterator<fjd> it = this.c.b().iterator();
        long j = -1;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (it.hasNext()) {
            fjd next = it.next();
            if (next != null && next.i() != 5) {
                if (next.i() == 3) {
                    i5++;
                    j = next.f();
                    i6 = next.g();
                } else if (next.i() != 4) {
                    i3++;
                    i4 = next.f() == j ? i4 + i6 : i4 + 1;
                }
            }
        }
        if (z) {
            i = i3 + 2;
            i2 = i4 + 4;
            i5++;
        } else {
            i = i3 + 1;
            i2 = i4 + 1;
        }
        return (i > this.f.getMaxListActionNum() || i2 > this.f.getMaxActionNum() || i5 > 25) ? 1 : 0;
    }

    private void t() {
        this.r.setRightButtonVisibility(0);
        this.r.setRightButtonOnClickListener(new View.OnClickListener() { // from class: gci
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomCourseActivity.this.aJW_(view);
            }
        });
        this.r.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: gcf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomCourseActivity.this.aJX_(view);
            }
        });
    }

    public /* synthetic */ void aJW_(View view) {
        if (view == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "mTitleBar ok view is null.");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            u();
            p();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void aJX_(View view) {
        if (view == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "mTitleBar cancel view is null.");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (!f()) {
            this.k = false;
            finish();
        } else {
            w();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean f() {
        if (this.n == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "checkCourseEditData, mFitWorkout is null");
            return false;
        }
        CustomCourseActionAdapter customCourseActionAdapter = this.c;
        if (customCourseActionAdapter == null || koq.b(customCourseActionAdapter.b())) {
            this.k = false;
            return false;
        }
        FitWorkout fitWorkout = new FitWorkout();
        this.c.e(fitWorkout);
        if (!TextUtils.isEmpty(fitWorkout.acquireName()) && !fitWorkout.acquireName().equals(this.n.acquireName())) {
            return true;
        }
        if (!TextUtils.isEmpty(fitWorkout.acquireDescription()) && !fitWorkout.acquireDescription().equals(this.n.acquireDescription())) {
            return true;
        }
        List<ChoreographedMultiActions> c = this.c.c();
        if (koq.b(this.n.getCourseActions()) || c.size() != this.n.getCourseActions().size()) {
            return true;
        }
        int i = 0;
        for (ChoreographedMultiActions choreographedMultiActions : c) {
            ChoreographedMultiActions choreographedMultiActions2 = this.n.getCourseActions().get(i);
            if (choreographedMultiActions.getRepeatTimes() != choreographedMultiActions2.getRepeatTimes()) {
                return true;
            }
            if (!koq.b(choreographedMultiActions.getActionList())) {
                if (koq.b(choreographedMultiActions2.getActionList()) || choreographedMultiActions.getActionList().size() != choreographedMultiActions2.getActionList().size() || a(choreographedMultiActions.getActionList(), choreographedMultiActions2.getActionList())) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    private boolean a(List<ChoreographedSingleAction> list, List<ChoreographedSingleAction> list2) {
        int i = 0;
        for (ChoreographedSingleAction choreographedSingleAction : list) {
            ChoreographedSingleAction choreographedSingleAction2 = list2.get(i);
            if (choreographedSingleAction != null) {
                if (choreographedSingleAction2 == null) {
                    return true;
                }
                AtomicAction action = choreographedSingleAction.getAction();
                if (action != null) {
                    if (choreographedSingleAction2.getAction() != null && (TextUtils.isEmpty(action.getId()) || action.getId().equals(choreographedSingleAction2.getAction().getId()))) {
                        TargetConfig targetConfig = choreographedSingleAction.getTargetConfig();
                        if (targetConfig == null) {
                            continue;
                        } else if (choreographedSingleAction2.getTargetConfig() != null && ((TextUtils.isEmpty(targetConfig.getId()) || targetConfig.getId().equals(choreographedSingleAction2.getTargetConfig().getId())) && targetConfig.getValueH() == choreographedSingleAction2.getTargetConfig().getValueH() && targetConfig.getValueL() == choreographedSingleAction2.getTargetConfig().getValueL())) {
                            TargetConfig intensityConfig = choreographedSingleAction.getIntensityConfig();
                            if (intensityConfig == null) {
                                continue;
                            } else if (choreographedSingleAction2.getIntensityConfig() != null && ((TextUtils.isEmpty(intensityConfig.getId()) || intensityConfig.getId().equals(choreographedSingleAction2.getIntensityConfig().getId())) && intensityConfig.getValueH() == choreographedSingleAction2.getIntensityConfig().getValueH() && intensityConfig.getValueL() == choreographedSingleAction2.getIntensityConfig().getValueL())) {
                                i++;
                            }
                        }
                    }
                    return true;
                }
                continue;
            }
        }
        return false;
    }

    private void w() {
        View inflate = View.inflate(this, R.layout.delete_course_dialog, null);
        ((HealthTextView) inflate.findViewById(R.id.pop_tip)).setText(getString(R.string._2130848685_res_0x7f022bad));
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
        builder.czg_(inflate).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: gch
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomCourseActivity.this.aKa_(view);
            }
        }).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: gcg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomCourseActivity.this.aKb_(view);
            }
        });
        CustomViewDialog e2 = builder.e();
        this.q = e2;
        e2.show();
    }

    public /* synthetic */ void aKa_(View view) {
        this.k = false;
        e(this.q);
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void aKb_(View view) {
        e(this.q);
        u();
        p();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(CustomViewDialog customViewDialog) {
        if (customViewDialog != null) {
            customViewDialog.dismiss();
            LogUtil.a("Suggestion_CustomCourseActivity", "dismissDialog");
        }
    }

    private void x() {
        if (this.n == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "saveDraft, mFitWorkout is null");
            return;
        }
        if (!TextUtils.isEmpty(this.g) && this.g.equals(this.n.acquireName())) {
            this.n.saveName("");
            LogUtil.a("Suggestion_CustomCourseActivity", "saveDraft, course name clear");
        }
        this.n.saveModified(System.currentTimeMillis());
        Gson create = new GsonBuilder().serializeSpecialFloatingPointValues().create();
        synchronized (f3316a) {
            LogUtil.a("Suggestion_CustomCourseActivity", "saveDraft, draft json result:", Integer.valueOf(ash.a("coachRunCustomCourse", create.toJson(this.n).replaceAll("NaN", "0"))));
        }
    }

    private void p() {
        FitWorkout fitWorkout = this.n;
        if (fitWorkout == null || koq.b(fitWorkout.getCourseActions())) {
            nrh.d(BaseApplication.getContext(), getResources().getString(R.string._2130848637_res_0x7f022b7d));
            return;
        }
        if (TextUtils.isEmpty(this.n.acquireName().trim())) {
            nrh.d(BaseApplication.getContext(), getResources().getString(R.string._2130848748_res_0x7f022bec));
            return;
        }
        if (this.d == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "checkSaveState courseApi == null");
            return;
        }
        showLoading();
        if (this.i == 1) {
            this.d.modifyUserDefinedWorkout(this.n, new e(this));
        } else {
            this.d.addUserDefinedWorkout(this.n, new e(this));
        }
    }

    private void a(FitWorkout fitWorkout) {
        mmp mmpVar = new mmp(fitWorkout.acquireId());
        mmpVar.d("AllTraining");
        mmpVar.g(1);
        mod.b(this, fitWorkout, mmpVar);
        finish();
    }

    private void u() {
        CustomCourseActionAdapter customCourseActionAdapter;
        FitWorkout fitWorkout = this.n;
        if (fitWorkout == null || (customCourseActionAdapter = this.c) == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "stuffFitWorkout, mFitWorkout is null or mAdapter is null");
            return;
        }
        customCourseActionAdapter.e(fitWorkout);
        if (TextUtils.isEmpty(this.n.acquireName())) {
            this.n.saveName(g());
        }
        LogUtil.a("Suggestion_CustomCourseActivity", "courseName = ", this.n.acquireName(), ", courseDescription = ", this.n.acquireDescription());
        this.n.setCourseDefineType(1);
        this.n.setCourseActions(this.c.c());
    }

    private String g() {
        String str;
        try {
            str = new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMMdd")).format(new Date());
        } catch (IllegalArgumentException unused) {
            LogUtil.b("Suggestion_CustomCourseActivity", "getDefaultCourseName error");
            str = "";
        }
        return getResources().getString(R.string._2130848635_res_0x7f022b7b, getResources().getString(R.string.IDS_start_track_sport_type_run), str);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.i = intent.getIntExtra("CustomCourseState", 0);
            this.l = intent.getStringExtra("CustomCourseFitWorkoutId");
        }
        int i = this.i;
        this.k = i == 0;
        if (i == 1) {
            this.o.sendEmptyMessage(5);
        }
        this.f = fjc.c(this).e(0);
        this.d = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        l();
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        gge.e("1130054", hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        CourseConfig courseConfig = this.f;
        if (courseConfig == null || this.s == null) {
            LogUtil.b("Suggestion_CustomCourseActivity", "courseConfig or support action is null");
            return;
        }
        LogUtil.a("Suggestion_CustomCourseActivity", "course config:", courseConfig.toString());
        if (TextUtils.isEmpty(this.l)) {
            m();
            k();
            finishLoading();
        } else {
            CourseApi courseApi = this.d;
            if (courseApi == null) {
                LogUtil.h("Suggestion_CustomCourseActivity", "initFitWorkout mCourseApi == null");
                finishLoading();
            } else {
                courseApi.getCourseById(new ffl.d(this.l).d("2.0").d(1).b(), new b(this));
            }
        }
    }

    public static class e extends UiCallback<FitWorkout> {
        private final WeakReference<CustomCourseActivity> b;

        e(CustomCourseActivity customCourseActivity) {
            this.b = new WeakReference<>(customCourseActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Suggestion_CustomCourseActivity", "get custom course fail:", Integer.valueOf(i), str);
            CustomCourseActivity customCourseActivity = this.b.get();
            if (customCourseActivity != null) {
                if (i == 2200030) {
                    str = customCourseActivity.getString(R.string._2130848691_res_0x7f022bb3);
                }
                if (i == 2200027) {
                    str = customCourseActivity.getString(R.string._2130848638_res_0x7f022b7e);
                }
                if (i == 15030019) {
                    str = customCourseActivity.getString(R.string._2130848748_res_0x7f022bec);
                }
                customCourseActivity.o.sendMessage(Message.obtain(customCourseActivity.o, 0, str));
                customCourseActivity.finishLoading();
                HashMap hashMap = new HashMap(10);
                hashMap.put("click", 1);
                hashMap.put("event", Integer.valueOf(customCourseActivity.i));
                hashMap.put("result", 1);
                gge.e("1130057", hashMap);
                return;
            }
            ReleaseLogUtil.d("Suggestion_CustomCourseActivity", "CustomCourseUiCallback onFailure mTrainDetail is null");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(FitWorkout fitWorkout) {
            int i;
            int i2;
            CustomCourseActivity customCourseActivity = this.b.get();
            if (customCourseActivity == null) {
                ReleaseLogUtil.d("Suggestion_CustomCourseActivity", "CustomCourseUiCallback onSuccess mTrainDetail is null");
                return;
            }
            customCourseActivity.finishLoading();
            if (fitWorkout == null) {
                LogUtil.h("Suggestion_CustomCourseActivity", "get custom course success workout is null");
                return;
            }
            ash.d("coachRunCustomCourse");
            customCourseActivity.k = false;
            LogUtil.a("Suggestion_CustomCourseActivity", "get custom course success:", fitWorkout.acquireId());
            customCourseActivity.o.sendMessage(Message.obtain(customCourseActivity.o, 1, fitWorkout));
            HashMap hashMap = new HashMap(10);
            hashMap.put("click", 1);
            hashMap.put("type", 1);
            hashMap.put("workout_name", fpq.e(customCourseActivity));
            if (koq.c(fitWorkout.getCourseActions())) {
                i = 0;
                i2 = 0;
                for (ChoreographedMultiActions choreographedMultiActions : fitWorkout.getCourseActions()) {
                    if (koq.c(choreographedMultiActions.getActionList())) {
                        i += choreographedMultiActions.getActionList().size();
                        if (choreographedMultiActions.getActionList().size() > 1) {
                            i2++;
                        }
                    }
                }
            } else {
                i = 0;
                i2 = 0;
            }
            hashMap.put("actionNumber", Integer.valueOf(i));
            hashMap.put("groupNumber", Integer.valueOf(i2));
            gge.e("1130055", hashMap);
            HashMap hashMap2 = new HashMap(10);
            hashMap2.put("click", 1);
            hashMap2.put("event", Integer.valueOf(customCourseActivity.i));
            hashMap2.put("result", 0);
            gge.e("1130057", hashMap2);
        }
    }

    static class b extends UiCallback<Workout> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<CustomCourseActivity> f3317a;

        b(CustomCourseActivity customCourseActivity) {
            this.f3317a = new WeakReference<>(customCourseActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Suggestion_CustomCourseActivity", "get custom course fail:", Integer.valueOf(i), str);
            CustomCourseActivity customCourseActivity = this.f3317a.get();
            if (customCourseActivity != null) {
                customCourseActivity.k();
                customCourseActivity.finishLoading();
            } else {
                ReleaseLogUtil.d("Suggestion_CustomCourseActivity", "CustomCourseUiCallback onFailure mTrainDetail is null");
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Workout workout) {
            CustomCourseActivity customCourseActivity = this.f3317a.get();
            if (customCourseActivity == null) {
                ReleaseLogUtil.d("Suggestion_CustomCourseActivity", "CustomCourseUiCallback onSuccess mTrainDetail is null");
                return;
            }
            customCourseActivity.finishLoading();
            if (workout == null) {
                LogUtil.h("Suggestion_CustomCourseActivity", "CustomCourseDetailCallback onSuccess, workout is null");
                return;
            }
            LogUtil.a("Suggestion_CustomCourseActivity", "get custom course success:", workout.acquireId());
            customCourseActivity.n = mod.a(workout);
            customCourseActivity.o.sendEmptyMessage(3);
        }
    }

    private void m() {
        String b2 = ash.b("coachRunCustomCourse");
        if (!TextUtils.isEmpty(b2)) {
            LogUtil.a("Suggestion_CustomCourseActivity", "the course draft.");
            FitWorkout fitWorkout = (FitWorkout) new GsonBuilder().serializeSpecialFloatingPointValues().create().fromJson(CommonUtil.z(b2.replaceAll("NaN", "0")), FitWorkout.class);
            if (fitWorkout != null && jdl.e(fitWorkout.getModified(), System.currentTimeMillis()) < 2) {
                this.n = fitWorkout;
                LogUtil.a("Suggestion_CustomCourseActivity", "draft mFitWorkout.acquireId = ", fitWorkout.acquireId(), ", mFitWorkout.acquireName = ", this.n.acquireName());
                if (TextUtils.isEmpty(this.n.acquireName())) {
                    String g = g();
                    this.g = g;
                    this.n.saveName(g);
                    return;
                }
                return;
            }
            ash.d("coachRunCustomCourse");
        }
        LogUtil.a("Suggestion_CustomCourseActivity", "initFitWorkoutDefault");
        FitWorkout fitWorkout2 = new FitWorkout();
        this.n = fitWorkout2;
        fitWorkout2.setWorkoutType(2);
        this.n.saveVersion("2.0");
        this.n.saveId(this.f.getId());
        this.n.setType(4);
        this.n.setCourseDefineType(1);
        String g2 = g();
        this.g = g2;
        this.n.saveName(g2);
        this.n.setCourseActions(j());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        jdx.b(new Runnable() { // from class: gck
            @Override // java.lang.Runnable
            public final void run() {
                CustomCourseActivity.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        this.t = fhq.a();
        q();
    }

    private void q() {
        this.m = new HeartRateConfigHelper(258, new HeartRateConfigHelper.OnConfigHelperListener() { // from class: gce
            @Override // com.huawei.health.basesport.helper.HeartRateConfigHelper.OnConfigHelperListener
            public final void onInitComplete() {
                CustomCourseActivity.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        CustomCourseActionAdapter customCourseActionAdapter = this.c;
        if (customCourseActionAdapter == null) {
            return;
        }
        customCourseActionAdapter.a(this.m, this.t);
        CourseConfig courseConfig = this.f;
        if (courseConfig != null) {
            this.c.a(courseConfig.getMaxActionNum());
        }
        this.o.sendEmptyMessage(2);
    }

    private List<ChoreographedMultiActions> j() {
        ArrayList arrayList = new ArrayList();
        for (Attribute attribute : this.f.getDefaultCourseActions()) {
            ChoreographedMultiActions choreographedMultiActions = new ChoreographedMultiActions();
            choreographedMultiActions.setRepeatTimes(1);
            ChoreographedSingleAction choreographedSingleAction = this.s.get(attribute.getId());
            if (choreographedSingleAction != null) {
                ChoreographedSingleAction e2 = e(choreographedSingleAction);
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(e2);
                choreographedMultiActions.setActionList(arrayList2);
                arrayList.add(choreographedMultiActions);
            }
        }
        return arrayList;
    }

    private ChoreographedSingleAction e(ChoreographedSingleAction choreographedSingleAction) {
        Gson create = new GsonBuilder().create();
        return (ChoreographedSingleAction) create.fromJson(create.toJson(choreographedSingleAction), ChoreographedSingleAction.class);
    }

    private void d(ChoreographedSingleAction choreographedSingleAction) {
        EditDialogBuilder editDialogBuilder;
        Map<String, ChoreographedSingleAction> map;
        if (choreographedSingleAction == null || (editDialogBuilder = this.h) == null || (map = this.s) == null) {
            LogUtil.b("Suggestion_CustomCourseActivity", "edit dialog singleAction is null or builder is null or mSupportActionMap is null.");
        } else {
            editDialogBuilder.d(choreographedSingleAction, map);
        }
    }

    private void c(ChoreographedMultiActions choreographedMultiActions) {
        if (choreographedMultiActions == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "newRepeatTime, multiActions == null");
        } else {
            this.h.e(choreographedMultiActions, this.f.getMaxCombinationRepeats());
        }
    }

    private void d(TargetConfig targetConfig) {
        if (targetConfig == null || this.h == null) {
            LogUtil.b("Suggestion_CustomCourseActivity", "edit dialog builder is null or targetConfig is null");
            return;
        }
        int m = CommonUtil.m(BaseApplication.getContext(), targetConfig.getId());
        if (m == 0) {
            this.h.b(targetConfig);
            return;
        }
        if (m == 1) {
            this.h.e(targetConfig);
            return;
        }
        if (m == 3) {
            this.h.d(targetConfig);
            return;
        }
        if (m == 4 || m == 5) {
            this.h.c(targetConfig, this.c.a());
        } else if (m != 255) {
            LogUtil.h("Suggestion_CustomCourseActivity", "editActionTargetValues, sportTargetType = ", Integer.valueOf(m));
        }
    }

    private void c(TargetConfig targetConfig) {
        if (targetConfig == null || this.h == null) {
            LogUtil.b("Suggestion_CustomCourseActivity", "edit dialog builder is null or intensityConfig is null");
            return;
        }
        int m = CommonUtil.m(BaseApplication.getContext(), targetConfig.getId());
        if (m == 1) {
            this.h.a(targetConfig, this.c.a());
            return;
        }
        if (m != 4 && m != 8) {
            if (m == 13) {
                this.h.a(targetConfig, this.t);
                return;
            }
            if (m != 255) {
                switch (m) {
                    case 15:
                        this.h.a(targetConfig);
                        break;
                    case 16:
                        this.h.c(targetConfig);
                        break;
                    case 17:
                        break;
                    default:
                        LogUtil.h("Suggestion_CustomCourseActivity", "editActionIntensityValues, intensityType = ", Integer.valueOf(m));
                        break;
                }
                return;
            }
            return;
        }
        this.h.c(targetConfig, this.c.a(), this.c.d(), this.c.e());
    }

    private ChoreographedSingleAction r() {
        Map<String, ChoreographedSingleAction> map = this.s;
        if (map == null || map.size() == 0) {
            LogUtil.h("Suggestion_CustomCourseActivity", "mSupportActionMap is empty.");
            return null;
        }
        CourseConfig courseConfig = this.f;
        if (courseConfig != null) {
            ChoreographedSingleAction choreographedSingleAction = this.s.get(courseConfig.getDefaultNewAction().getId());
            if (choreographedSingleAction != null) {
                return e(choreographedSingleAction);
            }
        }
        return null;
    }

    private ChoreographedSingleAction d(String str) {
        ChoreographedSingleAction choreographedSingleAction;
        Map<String, ChoreographedSingleAction> map = this.s;
        if (map == null || map.size() == 0) {
            LogUtil.b("Suggestion_CustomCourseActivity", "mSupportActionMap is empty.");
            return null;
        }
        if (this.f == null || (choreographedSingleAction = this.s.get(str)) == null) {
            return null;
        }
        return e(choreographedSingleAction);
    }

    private void l() {
        CourseApi courseApi = this.d;
        if (courseApi == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "addUserDefineCourse courseApi == null");
        } else {
            courseApi.getCourseActionList(0, 50, 1, 1, new UiCallback<List<AtomicAction>>() { // from class: com.huawei.health.suggestion.ui.run.activity.CustomCourseActivity.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("Suggestion_CustomCourseActivity", "getSupportActionListFromCloud, get data failure");
                    CustomCourseActivity.this.n();
                    CustomCourseActivity.this.o();
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<AtomicAction> list) {
                    LogUtil.a("Suggestion_CustomCourseActivity", "getSupportActionListFromCloud, get data success");
                    if (koq.b(list)) {
                        CustomCourseActivity.this.n();
                    } else {
                        CustomCourseActivity.this.c(list);
                    }
                    CustomCourseActivity.this.o();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<AtomicAction> list) {
        this.s = new HashMap();
        for (AtomicAction atomicAction : list) {
            if (atomicAction != null) {
                ChoreographedSingleAction choreographedSingleAction = new ChoreographedSingleAction();
                choreographedSingleAction.setAction(atomicAction);
                a(choreographedSingleAction);
                this.s.put(atomicAction.getId(), choreographedSingleAction);
            }
        }
        if (this.s.size() == 0) {
            n();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(ChoreographedSingleAction choreographedSingleAction) {
        char c;
        if (choreographedSingleAction.getAction() == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "setDefaultTargetAndIntensity, singleAction.getAction() is null");
            return;
        }
        String id = choreographedSingleAction.getAction().getId();
        TargetConfig targetConfig = new TargetConfig();
        TargetConfig targetConfig2 = new TargetConfig();
        id.hashCode();
        switch (id.hashCode()) {
            case 77802175:
                if (id.equals("RD001")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 77802176:
                if (id.equals("RD002")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 77802177:
            default:
                c = 65535;
                break;
            case 77802178:
                if (id.equals("RD004")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 77802179:
                if (id.equals("RD005")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c == 1) {
                targetConfig.setId(Integer.toString(0));
                targetConfig.setValueType(TargetConfig.ValueTypes.LOW_VALUE.getValue());
                if (UnitUtil.h()) {
                    targetConfig.setValueL(UnitUtil.d(1.0d, 3) * 1000.0d);
                } else {
                    targetConfig.setValueL(1000.0d);
                }
                targetConfig2.setId(Integer.toString(255));
            } else if (c == 2) {
                targetConfig.setId(Integer.toString(1));
                targetConfig.setValueType(TargetConfig.ValueTypes.LOW_VALUE.getValue());
                targetConfig.setValueL(60.0d);
                targetConfig2.setId(Integer.toString(255));
            } else if (c != 3) {
                targetConfig.setId(Integer.toString(255));
                targetConfig2.setId(Integer.toString(255));
            }
            choreographedSingleAction.setTargetConfig(targetConfig);
            choreographedSingleAction.setIntensityConfig(targetConfig2);
        }
        targetConfig.setId(Integer.toString(1));
        targetConfig.setValueType(TargetConfig.ValueTypes.LOW_VALUE.getValue());
        targetConfig.setValueL(300.0d);
        targetConfig2.setId(Integer.toString(255));
        choreographedSingleAction.setTargetConfig(targetConfig);
        choreographedSingleAction.setIntensityConfig(targetConfig2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.s = new HashMap();
        ChoreographedSingleAction e2 = e("RD002", getResources().getString(R.string._2130842251_res_0x7f02128b), Integer.toString(0), Integer.toString(255));
        ChoreographedSingleAction e3 = e("RD003", getResources().getString(R.string._2130844981_res_0x7f021d35), Integer.toString(0), Integer.toString(255));
        ChoreographedSingleAction e4 = e("RD004", getResources().getString(R.string._2130844982_res_0x7f021d36), Integer.toString(1), Integer.toString(255));
        ChoreographedSingleAction e5 = e("RD005", getResources().getString(R.string._2130844983_res_0x7f021d37), Integer.toString(5), Integer.toString(255));
        ChoreographedSingleAction e6 = e("RD001", getResources().getString(R.string._2130841810_res_0x7f0210d2), Integer.toString(1), Integer.toString(255));
        this.s.put(e2.getAction().getId(), e2);
        this.s.put(e4.getAction().getId(), e4);
        this.s.put(e5.getAction().getId(), e5);
        this.s.put(e6.getAction().getId(), e6);
        this.s.put(e3.getAction().getId(), e3);
    }

    private ChoreographedSingleAction e(String str, String str2, String str3, String str4) {
        List<TargetConfig> defaultTargetConfigs = this.f.getDefaultTargetConfigs();
        ChoreographedSingleAction choreographedSingleAction = new ChoreographedSingleAction();
        choreographedSingleAction.setAction(d(str, str2));
        TargetConfig targetConfig = new TargetConfig();
        targetConfig.setId(str3);
        Iterator<TargetConfig> it = defaultTargetConfigs.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            TargetConfig next = it.next();
            if (next.getId().equals(targetConfig.getId())) {
                targetConfig.setValueType(next.getValueType());
                targetConfig.setValueL(next.getValueL());
                targetConfig.setValueH(next.getValueH());
                break;
            }
        }
        TypeConfig.setDefaultImperialData(targetConfig);
        choreographedSingleAction.setTargetConfig(targetConfig);
        List<TargetConfig> defaultIntensityConfigs = this.f.getDefaultIntensityConfigs();
        TargetConfig targetConfig2 = new TargetConfig();
        targetConfig2.setId(str4);
        Iterator<TargetConfig> it2 = defaultIntensityConfigs.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            TargetConfig next2 = it2.next();
            if (next2.getId().equals(targetConfig2.getId())) {
                targetConfig2.setValueType(next2.getValueType());
                targetConfig2.setValueL(next2.getValueL());
                targetConfig2.setValueH(next2.getValueH());
                break;
            }
        }
        TypeConfig.setDefaultImperialData(targetConfig2);
        choreographedSingleAction.setIntensityConfig(targetConfig2);
        return choreographedSingleAction;
    }

    private AtomicAction d(String str, String str2) {
        AtomicAction atomicAction = new AtomicAction();
        atomicAction.setOrchestrationType(1);
        atomicAction.setId(str);
        atomicAction.setName(str2);
        return atomicAction;
    }

    @Override // com.huawei.health.suggestion.customizecourse.manager.adapter.interfaceadapter.OnStartDragListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        this.p.startDrag(viewHolder);
    }

    @Override // com.huawei.health.suggestion.customizecourse.manager.adapter.interfaceadapter.ItemEventListener
    public void onComItemClick(int i, int i2) {
        CustomCourseActionAdapter customCourseActionAdapter = this.c;
        if (customCourseActionAdapter == null || koq.b(customCourseActionAdapter.b())) {
            LogUtil.h("Suggestion_CustomCourseActivity", "onComItemClick, mAdapter is null or mAdapter.getActions() is empty");
            return;
        }
        switch (i2) {
            case 0:
                b(i);
                break;
            case 1:
                d(i);
                break;
            case 2:
                c(i);
                break;
            case 3:
                c(this.c.b(i, i2));
                break;
            case 4:
                d(this.c.d(i, i2));
                break;
            case 5:
                a(this.c.a(i, i2));
                break;
            case 6:
                d(this.c.a(i, i2));
                break;
            case 7:
                b(this.c.a(i, i2));
                break;
            case 8:
                c(this.c.a(i, i2));
                break;
            case 9:
                s();
                break;
            case 10:
                this.c.d(i);
                break;
            default:
                LogUtil.h("Suggestion_CustomCourseActivity", "onComItemClick, position = ", Integer.valueOf(i), " type = ", Integer.valueOf(i2));
                break;
        }
    }

    private void s() {
        Handler handler = this.o;
        if (handler == null) {
            return;
        }
        handler.post(new Runnable() { // from class: gcl
            @Override // java.lang.Runnable
            public final void run() {
                CustomCourseActivity.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        CustomCourseActionAdapter customCourseActionAdapter = this.c;
        if (customCourseActionAdapter == null) {
            return;
        }
        customCourseActionAdapter.notifyDataSetChanged();
    }

    private void b(int i) {
        Iterator<fjd> it = this.c.b().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            fjd next = it.next();
            if (next != null) {
                if (i2 == i) {
                    next.d(!next.l());
                } else if (next.l()) {
                    next.d(false);
                }
            }
            i2++;
        }
        this.c.notifyDataSetChanged();
    }

    private void b(TargetConfig targetConfig) {
        if (targetConfig == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "editActionIntensityType, targetConfig is null");
        } else {
            this.h.d(targetConfig, this.f);
        }
    }

    private void a(TargetConfig targetConfig) {
        if (targetConfig == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "editActionTargetType, targetConfig is null");
        } else {
            this.h.e(targetConfig, this.f);
        }
    }

    private void c(int i) {
        if (koq.b(this.c.b(), i)) {
            LogUtil.h("Suggestion_CustomCourseActivity", "delMulti, mAdapter.getActions() is out of bound!");
            return;
        }
        long f = this.c.b().get(i).f();
        Iterator<fjd> it = this.c.b().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (f == it.next().f()) {
                it.remove();
                i2++;
            }
        }
        this.c.notifyItemRangeRemoved(i, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x009c, code lost:
    
        r9 = -1;
        r5 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(int r9) {
        /*
            r8 = this;
            com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseActionAdapter r0 = r8.c
            java.util.ArrayList r0 = r0.b()
            boolean r0 = defpackage.koq.b(r0)
            if (r0 == 0) goto L18
            java.lang.String r9 = "delActions, mAdapter.getActions() is empty"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            java.lang.String r0 = "Suggestion_CustomCourseActivity"
            com.huawei.hwlogsmodel.LogUtil.h(r0, r9)
            return
        L18:
            com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseActionAdapter r0 = r8.c
            java.util.ArrayList r0 = r0.b()
            java.lang.Object r0 = r0.get(r9)
            fjd r0 = (defpackage.fjd) r0
            int r1 = r0.i()
            if (r1 != 0) goto L39
            com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseActionAdapter r0 = r8.c
            java.util.ArrayList r0 = r0.b()
            r0.remove(r9)
            com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseActionAdapter r9 = r8.c
            r9.notifyDataSetChanged()
            return
        L39:
            com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseActionAdapter r1 = r8.c
            java.util.ArrayList r1 = r1.b()
            r1.remove(r9)
            long r0 = r0.f()
            r9 = 0
        L47:
            com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseActionAdapter r2 = r8.c
            java.util.ArrayList r2 = r2.b()
            int r2 = r2.size()
            r3 = 1
            r4 = -1
            if (r9 >= r2) goto L9c
            com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseActionAdapter r2 = r8.c
            java.util.ArrayList r2 = r2.b()
            java.lang.Object r2 = r2.get(r9)
            fjd r2 = (defpackage.fjd) r2
            long r5 = r2.f()
            int r5 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r5 != 0) goto L99
            int r2 = r2.i()
            r5 = 4
            if (r2 != r5) goto L99
            com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseActionAdapter r2 = r8.c
            java.util.ArrayList r2 = r2.b()
            int r5 = r9 + (-1)
            java.lang.Object r2 = r2.get(r5)
            fjd r2 = (defpackage.fjd) r2
            int r6 = r2.i()
            r7 = 3
            if (r6 != r7) goto L86
            goto L9e
        L86:
            int r5 = r2.i()
            if (r5 != r3) goto L91
            r9 = 2
            r2.e(r9)
            goto L9c
        L91:
            int r2 = r2.i()
            if (r2 != 0) goto L99
            r5 = r4
            goto L9e
        L99:
            int r9 = r9 + 1
            goto L47
        L9c:
            r9 = r4
            r5 = r9
        L9e:
            if (r5 == r4) goto La9
            com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseActionAdapter r0 = r8.c
            java.util.ArrayList r0 = r0.b()
            r0.remove(r5)
        La9:
            if (r9 == r4) goto Lb5
            com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseActionAdapter r0 = r8.c
            java.util.ArrayList r0 = r0.b()
            int r9 = r9 - r3
            r0.remove(r9)
        Lb5:
            com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseActionAdapter r9 = r8.c
            r9.notifyDataSetChanged()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.ui.run.activity.CustomCourseActivity.d(int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aJV_(Message message) {
        if (message.obj instanceof FitWorkout) {
            FitWorkout fitWorkout = (FitWorkout) message.obj;
            if (!TextUtils.isEmpty(fitWorkout.acquireId())) {
                LogUtil.a("Suggestion_CustomCourseActivity", "addUserDefineCourse success.", fitWorkout.acquireId());
                a(mod.a(fitWorkout));
            } else {
                LogUtil.h("Suggestion_CustomCourseActivity", "addUserDefineCourse failed");
                nrh.d(BaseApplication.getContext(), getResources().getString(R.string._2130851563_res_0x7f0236eb));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        FitWorkout fitWorkout = this.n;
        if (fitWorkout == null) {
            LogUtil.h("Suggestion_CustomCourseActivity", "courseDetailHandler, mFitWorkout is null");
            return;
        }
        if (this.i == 2 && !TextUtils.isEmpty(fitWorkout.acquireName())) {
            this.n.saveName(getResources().getString(R.string._2130848670_res_0x7f022b9e, this.n.acquireName()));
        }
        k();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (!f()) {
            this.k = false;
            super.onBackPressed();
        } else {
            w();
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.k) {
            u();
            x();
        }
        CustomCourseActionAdapter customCourseActionAdapter = this.c;
        if (customCourseActionAdapter != null) {
            customCourseActionAdapter.releaseVibrator();
        }
        super.onDestroy();
    }

    @Override // com.huawei.health.suggestion.customizecourse.manager.EditDialogBuilder.OnDismissListener
    public void onDismiss() {
        CustomCourseActionAdapter customCourseActionAdapter = this.c;
        if (customCourseActionAdapter != null) {
            customCourseActionAdapter.i();
        }
    }

    static class a extends BaseHandler<CustomCourseActivity> {
        a(Looper looper, CustomCourseActivity customCourseActivity) {
            super(looper, customCourseActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aKc_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(CustomCourseActivity customCourseActivity, Message message) {
            LogUtil.a("Suggestion_CustomCourseActivity", "handleMessageWhenReferenceNotNull, message.what = ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 0) {
                if (message.obj instanceof String) {
                    nrh.d(BaseApplication.getContext(), (String) message.obj);
                    return;
                }
                return;
            }
            if (i == 1) {
                customCourseActivity.aJV_(message);
                return;
            }
            if (i == 2) {
                if (customCourseActivity.n == null || !koq.c(customCourseActivity.n.getCourseActions())) {
                    return;
                }
                customCourseActivity.c.c(customCourseActivity.n);
                return;
            }
            if (i == 3) {
                customCourseActivity.h();
            } else if (i == 5) {
                customCourseActivity.r.setTitleText(customCourseActivity.getResources().getString(R.string._2130848666_res_0x7f022b9a));
            } else {
                LogUtil.h("Suggestion_CustomCourseActivity", "handleMessageWhenReferenceNotNull, message.what = ", Integer.valueOf(message.what));
            }
        }
    }

    static class CustomCourseLinearLayoutManager extends HealthLinearLayoutManager {
        public CustomCourseLinearLayoutManager(Context context) {
            super(context);
        }

        @Override // com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {
                LogUtil.b("Sug_CustomCourseLinearLayoutManager", LogAnonymous.b((Throwable) e));
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
