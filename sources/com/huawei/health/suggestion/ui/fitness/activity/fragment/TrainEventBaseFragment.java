package com.huawei.health.suggestion.ui.fitness.activity.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ui.BaseActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessResultActivity;
import com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter;
import com.huawei.health.suggestion.ui.fitness.helper.RecyclerHolder;
import com.huawei.health.suggestion.ui.fitness.module.SugChart;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealthservice.db.table.DBSessionCommon;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.ffy;
import defpackage.ffz;
import defpackage.fis;
import defpackage.fpn;
import defpackage.fqv;
import defpackage.gdp;
import defpackage.ggl;
import defpackage.koq;
import defpackage.moe;
import defpackage.nrf;
import defpackage.nrt;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class TrainEventBaseFragment extends Fragment implements Animation.AnimationListener, View.OnClickListener, SugChart.OnHelperGestureListener {

    /* renamed from: a, reason: collision with root package name */
    private String f3141a;
    private BaseRecyclerViewAdapter<Object> b;
    private LinearLayout c;
    private LinearLayout d;
    private int e;
    private Date f;
    private ImageView g;
    private boolean h = false;
    private Plan i;
    private ImageView j;
    private View k;
    private String l;
    private List<WorkoutRecord> m;
    private Date n;
    private int o;
    private List<WorkoutRecord> p;
    private List<WorkoutRecord> q;
    private String r;
    private ImageView s;
    private HealthTextView t;
    private int u;
    private HealthRecycleView v;
    private TranslateAnimation y;

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }

    @Override // com.huawei.health.suggestion.ui.fitness.module.SugChart.OnHelperGestureListener
    public void scrollY(boolean z) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.o = arguments.getInt("plantype");
            this.l = arguments.getString("planid");
            this.u = arguments.getInt(DBSessionCommon.COLUMN_TIME_ZONE);
            this.i = (Plan) arguments.getParcelable("joined");
        }
        View inflate = layoutInflater.inflate(R.layout.sug_trainevent_basefm, viewGroup, false);
        BaseActivity.cancelLayoutById(inflate);
        this.s = (ImageView) inflate.findViewById(R.id.sug_iv_ani);
        inflate.findViewById(R.id.sug_trainevent_time).setVisibility(0);
        BaseActivity.setViewSafeRegion(false, inflate.findViewById(R.id.sug_trainevent_time));
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.sug_train_rcv_events);
        this.v = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        View findViewById = inflate.findViewById(R.id.sug_loading_layout);
        this.k = findViewById;
        findViewById.setVisibility(8);
        d();
        this.t = (HealthTextView) inflate.findViewById(R.id.sug_tv_tevent_stime);
        this.g = (ImageView) inflate.findViewById(R.id.sug_event_last);
        this.j = (ImageView) inflate.findViewById(R.id.sug_event_next);
        e();
        this.g.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.j.setVisibility(4);
        c();
        try {
            if (this.i != null) {
                this.n = new SimpleDateFormat("yyyy-MM-dd").parse(this.i.acquireStartDate());
                this.f = new SimpleDateFormat("yyyy-MM-dd").parse(this.i.getEndDate());
            }
            Calendar calendar = Calendar.getInstance();
            a(calendar);
            b(calendar);
        } catch (ParseException e) {
            LogUtil.b("Suggestion_TraineventBaseFm", "onCreateView", LogAnonymous.b((Throwable) e));
        }
        this.v.setAdapter(this.b);
        d(Calendar.getInstance());
        b();
        return inflate;
    }

    private void b() {
        this.v.setOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.TrainEventBaseFragment.4
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (i2 != 0) {
                    LogUtil.c("Suggestion_TraineventBaseFm", "mIsUpDownScrolling");
                    TrainEventBaseFragment.this.h = true;
                }
                if (i != 0) {
                    LogUtil.c("Suggestion_TraineventBaseFm", "mIsLeftRightScrolling");
                    TrainEventBaseFragment.this.h = false;
                }
            }
        });
    }

    private void d() {
        this.b = new BaseRecyclerViewAdapter<Object>(new ArrayList(), R.layout.sug_train_event_chart2, R.layout.sug_item_rcvtrain_event) { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.TrainEventBaseFragment.1
            @Override // com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int i) {
                return i > 1 ? 1 : 0;
            }

            @Override // com.huawei.health.suggestion.ui.fitness.helper.BaseRecyclerViewAdapter
            public void convert(RecyclerHolder recyclerHolder, int i, Object obj) {
                ImageView imageView;
                if (recyclerHolder == null) {
                    return;
                }
                TrainEventBaseFragment.this.c = (LinearLayout) recyclerHolder.aCA_(R.id.event_chart);
                TrainEventBaseFragment.this.d = (LinearLayout) recyclerHolder.aCA_(R.id.event_bottom);
                BaseActivity.setViewSafeRegion(false, TrainEventBaseFragment.this.d, TrainEventBaseFragment.this.c);
                if (i == getItemCount() - 1 && (imageView = (ImageView) recyclerHolder.aCA_(R.id.sug_item_rcvtrain_underline)) != null) {
                    imageView.setVisibility(4);
                }
                if (i == 0) {
                    if (koq.e(obj, fqv.class)) {
                        TrainEventBaseFragment.this.c(recyclerHolder, (List<fqv>) obj);
                    }
                } else if (i == 1) {
                    if (koq.e(obj, fqv.class)) {
                        TrainEventBaseFragment.this.e(recyclerHolder, (List<fqv>) obj);
                    }
                } else if (i >= 2) {
                    TrainEventBaseFragment.this.a(recyclerHolder, i, obj);
                }
            }
        };
    }

    private void c() {
        if (getContext() == null || !nrt.a(getContext())) {
            return;
        }
        this.j.setBackground(nrf.cJH_(getContext().getResources().getDrawable(R$drawable.common_ui_arrow_right), getContext().getResources().getColor(R$color.healthTintColorPrimary)));
        this.g.setBackground(nrf.cJH_(getContext().getResources().getDrawable(R$drawable.common_ui_arrow_left), getContext().getResources().getColor(R$color.healthTintColorPrimary)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(RecyclerHolder recyclerHolder, List<fqv> list) {
        if (this.h) {
            LogUtil.a("Suggestion_TraineventBaseFm", "holderItemFirstExcel mIsUpDownScrolling");
            return;
        }
        recyclerHolder.e(R.id.sug_cb_tevent_b, 8).e(R.id.sug_sc_tevent_chart2, 8);
        recyclerHolder.aCA_(R.id.sug_cb_tevent_b_line).setVisibility(8);
        SugChart sugChart = (SugChart) recyclerHolder.aCA_(R.id.sug_sc_tevent_chart);
        sugChart.d(1);
        sugChart.b(30);
        sugChart.c(this);
        if (this.o == 0) {
            recyclerHolder.e(R.id.sug_cb_tevent_t, getString(R.string._2130848437_res_0x7f022ab5).toUpperCase(Locale.ENGLISH));
            sugChart.h(e(R.color._2131299175_res_0x7f090b67));
            sugChart.b(e(R.color._2131299169_res_0x7f090b61), e(R.color._2131299173_res_0x7f090b65));
            sugChart.i(e(R.color._2131299182_res_0x7f090b6e));
        } else {
            sugChart.h(e(R.color._2131299176_res_0x7f090b68));
            sugChart.b(e(R.color._2131299171_res_0x7f090b63), e(R.color._2131299178_res_0x7f090b6a));
            sugChart.i(e(R.color._2131299183_res_0x7f090b6f));
        }
        sugChart.a(e(R$color.textColorSecondary));
        sugChart.d(b(R.dimen._2131364966_res_0x7f0a0c66));
        sugChart.e(e(R$color.textColorTertiary));
        a(list, sugChart);
        sugChart.d(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(RecyclerHolder recyclerHolder, List<fqv> list) {
        if (this.h) {
            LogUtil.a("Suggestion_TraineventBaseFm", "holderItemMidExcel mIsUpDownScrolling");
            return;
        }
        recyclerHolder.e(R.id.sug_cb_tevent_b, getString(R.string._2130848399_res_0x7f022a8f).toUpperCase(Locale.ENGLISH)).e(R.id.sug_sc_tevent_chart, 8);
        SugChart sugChart = (SugChart) recyclerHolder.aCA_(R.id.sug_sc_tevent_chart2);
        sugChart.d(2);
        sugChart.b(30);
        sugChart.c(this);
        if (this.o == 0) {
            recyclerHolder.e(R.id.sug_cb_tevent_t, getString(R.string._2130848412_res_0x7f022a9c).toUpperCase(Locale.ENGLISH));
            sugChart.h(e(R.color._2131299168_res_0x7f090b60));
            sugChart.b(e(R.color._2131299170_res_0x7f090b62), e(R.color._2131299174_res_0x7f090b66));
            sugChart.i(e(R.color._2131299181_res_0x7f090b6d));
            sugChart.f(e(R.color._2131299181_res_0x7f090b6d));
        } else {
            recyclerHolder.e(R.id.sug_cb_tevent_t, getString(R.string._2130848395_res_0x7f022a8b).toUpperCase(Locale.ENGLISH));
            sugChart.h(e(R.color._2131299176_res_0x7f090b68));
            sugChart.b(e(R.color._2131299172_res_0x7f090b64), e(R.color._2131299179_res_0x7f090b6b));
            sugChart.i(e(R.color._2131299180_res_0x7f090b6c));
            sugChart.f(e(R.color._2131299180_res_0x7f090b6c));
        }
        sugChart.b(b(R.dimen._2131365013_res_0x7f0a0c95));
        sugChart.e(e(R$color.textColorTertiary));
        sugChart.c(b(R.dimen._2131365012_res_0x7f0a0c94));
        sugChart.a(e(R$color.textColorSecondary));
        sugChart.c(e(R$color.textColorTertiary));
        a(list, sugChart);
        sugChart.d(list);
    }

    private void a(List<fqv> list, SugChart sugChart) {
        if (LanguageUtil.bc(sugChart.getContext().getApplicationContext())) {
            Collections.reverse(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(RecyclerHolder recyclerHolder, int i, Object obj) {
        d(recyclerHolder, i);
        if (obj instanceof WorkoutRecord) {
            final WorkoutRecord workoutRecord = (WorkoutRecord) obj;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String[] strArr = {simpleDateFormat.format(Long.valueOf(workoutRecord.acquireExerciseTime())), SimpleDateFormat.getDateInstance(3).format(Long.valueOf(workoutRecord.acquireExerciseTime()))};
            a(recyclerHolder, workoutRecord, simpleDateFormat, strArr);
            recyclerHolder.e(R.id.sug_item_train_workout_date, strArr[1]).e(R.id.sug_item_train_workout_name, workoutRecord.acquireWorkoutName()).e(R.id.sug_item_train_workout_execute, strArr[0]);
            c(recyclerHolder, new Date(workoutRecord.acquireExerciseTime()));
            recyclerHolder.aCB_(R.id.sug_trainevent_event, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.TrainEventBaseFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (TrainEventBaseFragment.this.o == 0 || workoutRecord.isRunWorkout()) {
                        TrainEventBaseFragment.this.d(workoutRecord);
                    } else {
                        TrainEventBaseFragment.this.e(workoutRecord);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    private void a(RecyclerHolder recyclerHolder, WorkoutRecord workoutRecord, SimpleDateFormat simpleDateFormat, String[] strArr) {
        if (this.o == 0) {
            fpn.b(workoutRecord, simpleDateFormat, strArr);
            recyclerHolder.e(R.id.sug_item_train_workout_kcal, ffy.b(ffz.c(), (int) workoutRecord.acquireActualDistance(), moe.d(moe.g(workoutRecord.acquireActualDistance())))).e(R.id.sug_item_train_workout_duration, String.format(Locale.ENGLISH, "%02d:%02d:%02d", Integer.valueOf(workoutRecord.getDuration() / 3600), Integer.valueOf((workoutRecord.getDuration() % 3600) / 60), Integer.valueOf(workoutRecord.getDuration() % 60))).e(R.id.sug_item_train_workout_heartbeat, ffy.d(getActivity(), ffz.b(), fpn.b(workoutRecord.acquireActualDistance() == 0.0f ? 0.0d : workoutRecord.getDuration() / moe.g(workoutRecord.acquireActualDistance()))));
            return;
        }
        recyclerHolder.e(R.id.sug_item_train_workout_kcal, ffy.d(getActivity().getApplicationContext(), R.string._2130837534_res_0x7f02001e, moe.g(workoutRecord.getDuration()))).e(R.id.sug_item_train_workout_duration, ffy.b(R.plurals._2130903422_res_0x7f03017e, (int) workoutRecord.acquireActualCalorie(), moe.d(workoutRecord.acquireActualCalorie()))).e(R.id.sug_item_train_workout_heartbeat, UnitUtil.e(workoutRecord.acquireFinishRate(), 2, 0));
    }

    private void d(RecyclerHolder recyclerHolder, int i) {
        if (i == this.b.getItemCount() - 1) {
            recyclerHolder.e(R.id.sug_event_bottom_, 0);
        } else {
            recyclerHolder.e(R.id.sug_event_bottom_, 8);
        }
    }

    private void c(RecyclerHolder recyclerHolder, Date date) {
        if (ggl.a(date, new Date()) == 0) {
            recyclerHolder.e(R.id.sug_item_train_workout_date, 8);
            recyclerHolder.e(R.id.sug_item_train_workout_execute, 0);
        } else {
            recyclerHolder.e(R.id.sug_item_train_workout_date, 0);
            recyclerHolder.e(R.id.sug_item_train_workout_execute, 8);
        }
    }

    private void a(Calendar calendar) {
        int i = this.u;
        if (i == 2) {
            calendar.add(6, -6);
        } else if (i == 3) {
            calendar.add(6, -30);
        }
    }

    private void e() {
        if (LanguageUtil.bc(getContext())) {
            this.g.setRotation(180.0f);
            this.j.setRotation(180.0f);
        }
    }

    private void c(boolean z) {
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 0.0f, 2, z ? 1.0f : -1.0f, 2, 0.0f, 2, 0.0f);
        this.y = translateAnimation;
        translateAnimation.setAnimationListener(this);
        this.y.setDuration(400L);
        this.y.setInterpolator(new AccelerateDecelerateInterpolator());
        this.s.startAnimation(this.y);
    }

    public void b(boolean z) {
        this.h = false;
        if (d(z)) {
            return;
        }
        boolean a2 = a(z);
        Calendar calendar = Calendar.getInstance();
        int i = this.u;
        if (i == 2) {
            calendar.add(3, this.e);
            a(z, a2, calendar);
        } else if (i == 3) {
            calendar.add(6, this.e * 31);
            a(z, a2, calendar);
        }
    }

    private boolean a(boolean z) {
        if (z) {
            this.e++;
            return false;
        }
        this.e--;
        return true;
    }

    private boolean d(boolean z) {
        return this.u == 4 || (z && this.e >= 0);
    }

    private void a(boolean z, boolean z2, Calendar calendar) {
        if (ggl.a(calendar.getTime(), this.n) <= 0) {
            a(z2, calendar);
            b(calendar);
        } else {
            e(z);
        }
    }

    private void e(boolean z) {
        if (z) {
            this.e--;
        } else {
            this.e++;
        }
    }

    private void a(boolean z, Calendar calendar) {
        this.g.setVisibility(0);
        Bitmap awU_ = ffy.awU_(this.v);
        if (ffy.c(this.s, awU_)) {
            this.s.setImageBitmap(awU_);
            this.s.setVisibility(0);
            c(z);
        }
        this.k.setVisibility(0);
        d(calendar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(WorkoutRecord workoutRecord) {
        Intent intent = new Intent(getActivity(), (Class<?>) FitnessResultActivity.class);
        intent.putExtra("workout_record", workoutRecord);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(WorkoutRecord workoutRecord) {
        String acquireRunWorkoutTrajectoryId = TextUtils.isEmpty(workoutRecord.acquireTrajectoryId()) ? workoutRecord.acquireRunWorkoutTrajectoryId() : workoutRecord.acquireTrajectoryId();
        if (ffy.c(acquireRunWorkoutTrajectoryId)) {
            fis.d().a(acquireRunWorkoutTrajectoryId);
        }
    }

    public int e(int i) {
        return getResources().getColor(i);
    }

    public float b(int i) {
        return getResources().getDimension(i);
    }

    private void c(Calendar calendar) {
        LogUtil.c("Suggestion_TraineventBaseFm", "getLocalData");
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        List<WorkoutRecord> workoutRecords = courseApi != null ? courseApi.getWorkoutRecords(this.l, this.r, this.f3141a) : null;
        if (koq.c(workoutRecords)) {
            LogUtil.a("Suggestion_TraineventBaseFm", workoutRecords.toString());
            d(workoutRecords);
        } else {
            this.p = null;
            this.m = null;
            this.q = null;
            LogUtil.h("Suggestion_TraineventBaseFm", "getWorkoutRecords the data is null");
        }
        e(calendar);
    }

    private void d(List<WorkoutRecord> list) {
        if (this.o == 0) {
            gdp.b(list);
        }
        int i = this.u;
        if (i == 2) {
            this.p = list;
            LogUtil.c("Suggestion_TraineventBaseFm", "WeekRecordsData size：", Integer.valueOf(list.size()));
        } else if (i == 3) {
            this.m = list;
            LogUtil.c("Suggestion_TraineventBaseFm", "MonthRecordsData size：", Integer.valueOf(list.size()));
        } else {
            this.q = list;
            LogUtil.c("Suggestion_TraineventBaseFm", "AllRecordsData size：", Integer.valueOf(list.size()));
        }
    }

    private void e(Calendar calendar) {
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), getString(R.string._2130851567_res_0x7f0236ef)));
        ArrayList arrayList3 = new ArrayList(10);
        int i = this.u;
        if (i == 2) {
            e(this.p, arrayList, arrayList2, new c(7, calendar, simpleDateFormat), arrayList3);
        } else if (i == 3) {
            e(this.m, arrayList, arrayList2, new c(31, calendar, simpleDateFormat), arrayList3);
        } else {
            Plan plan = this.i;
            if (plan != null) {
                fpn.c(arrayList, arrayList2, plan);
                arrayList3.add(a(calendar, this.q, arrayList, 0));
                arrayList3.add(a(calendar, this.q, arrayList2, 1));
                fpn.e(this.q, arrayList3);
            }
        }
        this.b.refreshDataChange(arrayList3);
        this.k.setVisibility(8);
    }

    private void e(List<WorkoutRecord> list, List<fqv> list2, List<fqv> list3, c cVar, List<Object> list4) {
        for (int i = 0; i < cVar.b; i++) {
            fqv d = fpn.d(cVar.d, cVar.e, i, this.u);
            list2.add(d);
            Object clone = d.clone();
            if (clone instanceof fqv) {
                list3.add((fqv) clone);
            }
        }
        cVar.d.add(6, -cVar.b);
        list4.add(a(cVar.d, list, list2, 0));
        list4.add(a(cVar.d, list, list3, 1));
        fpn.e(list, list4);
    }

    private List a(Calendar calendar, List<WorkoutRecord> list, List<fqv> list2, int i) {
        if (list == null) {
            return list2;
        }
        if (i == 0) {
            if (this.o == 0) {
                int i2 = this.u;
                if (i2 == 2) {
                    return c(list2, list, calendar, 0);
                }
                if (i2 == 3) {
                    return c(list2, list, calendar, 0);
                }
                return c(list2, list, (Calendar) null, 0);
            }
            int i3 = this.u;
            if (i3 == 2) {
                return c(list2, list, calendar, 1);
            }
            if (i3 == 3) {
                return c(list2, list, calendar, 1);
            }
            return c(list2, list, (Calendar) null, 1);
        }
        if (this.o == 0) {
            int i4 = this.u;
            if (i4 == 2) {
                return c(list2, list, calendar, 2);
            }
            if (i4 == 3) {
                return c(list2, list, calendar, 2);
            }
            return c(list2, list, (Calendar) null, 2);
        }
        int i5 = this.u;
        if (i5 == 2) {
            return c(list2, list, calendar, 3);
        }
        if (i5 == 3) {
            return c(list2, list, calendar, 3);
        }
        return c(list2, list, (Calendar) null, 3);
    }

    private List<fqv> c(List<fqv> list, List<WorkoutRecord> list2, Calendar calendar, int i) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (koq.c(list2)) {
            boolean z = true;
            for (WorkoutRecord workoutRecord : list2) {
                if (workoutRecord != null) {
                    try {
                        int c2 = fpn.c(calendar, simpleDateFormat, workoutRecord, this.n);
                        if (workoutRecord.acquireFinishRate() < 100.0f) {
                            z = false;
                        }
                        fpn.c(list, new fpn.c(calendar, i, workoutRecord, c2), new DecimalFormat("##.##"), z);
                    } catch (ParseException unused) {
                        LogUtil.b("Suggestion_TraineventBaseFm", "DateFormat exception：", workoutRecord.acquireWorkoutDate());
                    }
                }
            }
        }
        return list;
    }

    protected void d(Calendar calendar) {
        b(calendar);
        java.text.DateFormat dateInstance = SimpleDateFormat.getDateInstance(3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (this.u == 4) {
            this.g.setVisibility(8);
            try {
                e(dateInstance, simpleDateFormat);
            } catch (ParseException unused) {
                LogUtil.h("Suggestion_TraineventBaseFm", "getRecordPlan parse calendar ParseException ");
            }
        } else {
            c(calendar, dateInstance, simpleDateFormat);
        }
        LogUtil.c("Suggestion_TraineventBaseFm", "acquire the date：", this.r, "--", this.f3141a);
        c(calendar);
    }

    private void c(Calendar calendar, java.text.DateFormat dateFormat, SimpleDateFormat simpleDateFormat) {
        a();
        this.f3141a = simpleDateFormat.format(Long.valueOf(calendar.getTimeInMillis()));
        String format = dateFormat.format(Long.valueOf(calendar.getTimeInMillis()));
        a(calendar);
        this.r = simpleDateFormat.format(Long.valueOf(calendar.getTimeInMillis()));
        String format2 = dateFormat.format(Long.valueOf(calendar.getTimeInMillis()));
        this.t.setText(ffy.d(BaseApplication.getContext(), R.string._2130845600_res_0x7f021fa0, format2, format));
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.t.setText(ffy.d(BaseApplication.getContext(), R.string._2130845600_res_0x7f021fa0, format, format2));
        }
    }

    private void a() {
        if (this.e < 0) {
            this.j.setVisibility(0);
        } else {
            this.j.setVisibility(4);
        }
    }

    private void b(Calendar calendar) {
        if (ggl.a(calendar.getTime(), this.n) >= 0) {
            this.g.setVisibility(4);
        }
    }

    private void e(java.text.DateFormat dateFormat, SimpleDateFormat simpleDateFormat) throws ParseException {
        Plan plan = this.i;
        if (plan == null || TextUtils.isEmpty(plan.acquireStartDate())) {
            return;
        }
        this.n = simpleDateFormat.parse(this.i.acquireStartDate());
        this.f = simpleDateFormat.parse(this.i.getEndDate());
        this.r = new SimpleDateFormat("yyyy-MM-dd").format(this.n);
        this.f3141a = new SimpleDateFormat("yyyy-MM-dd").format(this.f);
        this.t.setText(ffy.d(BaseApplication.getContext(), R.string._2130845600_res_0x7f021fa0, dateFormat.format(this.n), dateFormat.format(this.f)));
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.t.setText(ffy.d(BaseApplication.getContext(), R.string._2130845600_res_0x7f021fa0, dateFormat.format(this.f), dateFormat.format(this.n)));
        }
        if (this.i.getWeekCount() == 0) {
            LogUtil.b("Suggestion_TraineventBaseFm", "mJoinedPlan.getWeekCount()  planData error");
            this.i.saveWeekCount(ggl.a(this.n, this.f));
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
        this.s.setVisibility(0);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        this.s.setVisibility(4);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.c("Suggestion_TraineventBaseFm", "last and next click disable");
        if (view.getId() == R.id.sug_event_last) {
            b(false);
        } else {
            b(true);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.module.SugChart.OnHelperGestureListener
    public void scrollX(boolean z) {
        b(!z);
    }

    public static class c {
        private int b;
        private Calendar d;
        private SimpleDateFormat e;

        c(int i, Calendar calendar, SimpleDateFormat simpleDateFormat) {
            this.b = i;
            this.d = calendar;
            this.e = simpleDateFormat;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        FragmentInstrumentation.onPauseByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        FragmentInstrumentation.onHiddenChangedByFragment(this, z);
    }
}
