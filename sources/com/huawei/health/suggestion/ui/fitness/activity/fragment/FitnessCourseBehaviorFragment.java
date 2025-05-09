package com.huawei.health.suggestion.ui.fitness.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.adapter.MyCourseAdapter;
import com.huawei.health.suggestion.ui.adapter.MySingleCourseAdapter;
import com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment;
import com.huawei.health.suggestion.ui.fitness.module.FitnessTopicDeleteModel;
import com.huawei.health.suggestion.ui.run.activity.CustomCourseActivity;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.WorkoutPackageInfo;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.scrollview.ScrollViewListener;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import defpackage.arx;
import defpackage.ary;
import defpackage.eil;
import defpackage.fff;
import defpackage.fot;
import defpackage.ggr;
import defpackage.ggs;
import defpackage.gnm;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mod;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes7.dex */
public class FitnessCourseBehaviorFragment extends BaseFragment implements View.OnClickListener {
    private static final int b = BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131362564_res_0x7f0a0304);
    private static final int c = BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131362832_res_0x7f0a0410);

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f3116a;
    private LinearLayout aa;
    private MySingleCourseAdapter ab;
    private HealthTextView ac;
    private HealthTextView ad;
    private HealthSubHeader ae;
    private HealthRecycleView ah;
    private RelativeLayout ai;
    private HealthCardView aj;
    private HealthTextView ak;
    private HealthSubHeader al;
    private RecyclerView.ItemDecoration am;
    private HealthRecycleView an;
    private int as;
    private AppCompatImageView d;
    private RelativeLayout i;
    private int j;
    private HealthScrollView k;
    private DeleteModeListener l;
    private HealthCardView m;
    private boolean p;
    private HealthToolBar t;
    private LinearLayout u;
    private MyCourseAdapter v;
    private LinearLayout w;
    private View x;
    private LinearLayout z;
    private int ag = 0;
    private boolean q = true;
    private List<Integer> af = new ArrayList(10);
    private boolean e = false;
    private FitnessTopicDeleteModel n = new FitnessTopicDeleteModel();
    private List<Object> ap = new ArrayList(10);
    private List<SleepAudioSeries> g = new ArrayList(10);
    private List<SleepAudioSeries> o = new ArrayList(10);
    private List<SleepAudioSeries> h = new ArrayList(10);
    private boolean s = true;
    private boolean r = false;
    private int f = 0;
    private OnFitnessStatusChangeCallback aq = new OnFitnessStatusChangeCallback() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.5
        @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
        public void onUpdate() {
            LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "mWorkoutStatusChangeCallback is refreshView");
            FitnessCourseBehaviorFragment.this.s = true;
        }
    };
    private MyCourseAdapter.OnItemClickListener y = new MyCourseAdapter.OnItemClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.12
        @Override // com.huawei.health.suggestion.ui.adapter.MyCourseAdapter.OnItemClickListener
        public void onItemClick() {
            FitnessCourseBehaviorFragment.this.af();
        }
    };
    private HealthToolBar.OnSingleTapListener ar = new HealthToolBar.OnSingleTapListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.15
        @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
        public void onSingleTap(int i2) {
            if (i2 != 1) {
                if (i2 != 2) {
                    return;
                }
                LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "click select all");
                FitnessCourseBehaviorFragment.this.k();
                return;
            }
            if (koq.c(FitnessCourseBehaviorFragment.this.af) || koq.c(FitnessCourseBehaviorFragment.this.o)) {
                FitnessCourseBehaviorFragment.this.al();
            }
        }
    };

    public interface DeleteModeListener {
        void deleteItem(int i);
    }

    private int a(int i2) {
        if (i2 != 1) {
            return i2 != 2 ? 1 : 3;
        }
        return 2;
    }

    private int e(int i2) {
        if (i2 != 5) {
            return i2 != 6 ? 0 : 1;
        }
        return 2;
    }

    static /* synthetic */ int i(FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment) {
        int i2 = fitnessCourseBehaviorFragment.f;
        fitnessCourseBehaviorFragment.f = i2 + 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void af() {
        List<Object> b2 = this.v.b();
        List<SleepAudioSeries> c2 = this.ab.c();
        if (koq.b(b2) && koq.b(c2)) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "selectAllStatus workouts and singleCourseWorkouts is empty");
            return;
        }
        int size = this.af.size();
        int size2 = this.o.size();
        if (koq.b(b2) && koq.c(c2)) {
            if (size2 == c2.size() && this.ag == 0) {
                k();
                return;
            } else {
                l();
                return;
            }
        }
        if (koq.c(b2) && koq.b(c2)) {
            if (size == b2.size() && this.ag == 0) {
                k();
                return;
            } else {
                l();
                return;
            }
        }
        if (size + size2 == b2.size() + c2.size() && this.ag == 0) {
            k();
        } else {
            l();
        }
    }

    private void l() {
        this.t.setIconTitleColor(2, getString(R.string._2130841399_res_0x7f020f37), R.color._2131299236_res_0x7f090ba4);
        this.t.setIcon(2, R.drawable._2131431675_res_0x7f0b10fb);
        this.ag = 0;
        DeleteModeListener deleteModeListener = this.l;
        if (deleteModeListener != null) {
            deleteModeListener.deleteItem(this.as);
        }
    }

    public static FitnessCourseBehaviorFragment a(int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", i2);
        bundle.putInt("category", i3);
        FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment = new FitnessCourseBehaviorFragment();
        fitnessCourseBehaviorFragment.setArguments(bundle);
        return fitnessCourseBehaviorFragment;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.as = arguments.getInt("type");
            this.j = arguments.getInt("category", 0);
            LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "onCreate.mType", Integer.valueOf(this.as), " mCourseCategory:", Integer.valueOf(this.j));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sug_fragment_course_behavior, viewGroup, false);
        aAH_(inflate);
        this.r = false;
        ary.a().e(this.aq, "WORKOUT_DELETE");
        ary.a().e(this.aq, "WORKOUT_FINISHED");
        ary.a().e(this.aq, "COLLECTION_ADD");
        ary.a().e(this.aq, "COLLECTION_DELETE");
        ary.a().e(this.aq, "CUSTOM_COURSE_UPDATE");
        return inflate;
    }

    public void aAH_(View view) {
        this.x = view.findViewById(R.id.sug_loading_layout);
        aAF_(view);
        this.i = (RelativeLayout) view.findViewById(R.id.course_behavior_container);
        aAG_(view);
        this.ah = (HealthRecycleView) view.findViewById(R.id.recyclerView_topic);
        this.w = (LinearLayout) view.findViewById(R.id.course_behavior_marketing_layout);
        this.z = (LinearLayout) view.findViewById(R.id.sug_layout_net_error);
        this.aa = (LinearLayout) view.findViewById(R.id.sug_reco_workoutlist_nodata);
        this.ad = (HealthTextView) view.findViewById(R.id.sug_fitnes_nodata1);
        this.ac = (HealthTextView) view.findViewById(R.id.sug_fitnes_nodata);
        this.f3116a = (HealthButton) view.findViewById(R.id.btn_no_net_work);
        this.ak = (HealthTextView) view.findViewById(R.id.tips_no_net_work);
        this.d = (AppCompatImageView) view.findViewById(R.id.btn_add_course);
        this.ai = (RelativeLayout) view.findViewById(R.id.reload_layout);
        aAE_(view);
        HealthCardView healthCardView = (HealthCardView) view.findViewById(R.id.course_behavior_cardview);
        this.m = healthCardView;
        BaseActivity.setViewSafeRegion(true, healthCardView);
        ad();
        z();
        this.v.c(this.y);
        ggs.b(getContext(), this.ah, true, true);
        this.ah.setHasFixedSize(true);
        this.an.setHasFixedSize(true);
        this.d.setOnClickListener(this);
        this.ah.setAdapter(this.v);
        this.an.setAdapter(this.ab);
        this.ah.setNestedScrollingEnabled(false);
        this.an.setNestedScrollingEnabled(false);
        this.ah.setLayoutManager(new j(getContext()));
        ag();
        if (this.as == 0) {
            eil.alQ_(getContext(), this.w, v());
        }
        this.f3116a.setOnClickListener(this);
        this.ad.setVisibility(8);
        this.ai.setOnClickListener(this);
        int i2 = this.as;
        if (i2 == 1) {
            this.ac.setText(R.string._2130848476_res_0x7f022adc);
        } else if (i2 == 0) {
            this.ac.setText(ggs.d(this.j) ? R.string._2130846709_res_0x7f0223f5 : R.string._2130848477_res_0x7f022add);
        } else {
            this.ac.setText(this.j == 258 ? R.string._2130848671_res_0x7f022b9f : R.string._2130848593_res_0x7f022b51);
        }
    }

    private void z() {
        MySingleCourseAdapter mySingleCourseAdapter = new MySingleCourseAdapter(this.an.getContext(), this.g, this.j);
        this.ab = mySingleCourseAdapter;
        mySingleCourseAdapter.e(new MySingleCourseAdapter.OnItemClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.11
            @Override // com.huawei.health.suggestion.ui.adapter.MySingleCourseAdapter.OnItemClickListener
            public void onItemCheckBoxClick(View view, int i2) {
                if (!koq.b(FitnessCourseBehaviorFragment.this.g, i2)) {
                    SleepAudioSeries sleepAudioSeries = (SleepAudioSeries) FitnessCourseBehaviorFragment.this.g.get(i2);
                    LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "onItemCheckBoxClick : name = ", sleepAudioSeries.getName());
                    if (view instanceof HealthCheckBox) {
                        boolean isChecked = ((HealthCheckBox) view).isChecked();
                        LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "onItemCheckBoxClick : checked = ", Boolean.valueOf(isChecked));
                        if (!isChecked) {
                            FitnessCourseBehaviorFragment.this.o.remove(sleepAudioSeries);
                            sleepAudioSeries.setIsSelected(0);
                        } else {
                            sleepAudioSeries.setIsSelected(1);
                            FitnessCourseBehaviorFragment.this.o.add(sleepAudioSeries);
                        }
                    }
                    FitnessCourseBehaviorFragment.this.af();
                    return;
                }
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "mCollectSingleCourseData position is OutOfBounds");
            }
        });
    }

    private void aAG_(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.layout_single_course);
        this.u = linearLayout;
        linearLayout.setVisibility(8);
        this.aj = (HealthCardView) view.findViewById(R.id.course_behavior_cardview_single_course);
        HealthSubHeader healthSubHeader = (HealthSubHeader) view.findViewById(R.id.sub_header_single_course_title);
        this.al = healthSubHeader;
        healthSubHeader.setRightDrawable(R.drawable._2131429719_res_0x7f0b0957);
        this.al.setMoreText("");
        this.al.setPaddingStartEnd(0.0f, 0.0f);
        this.al.setSubHeaderBackgroundColor(ContextCompat.getColor(BaseApplication.e(), R.color._2131299296_res_0x7f090be0));
        this.an = (HealthRecycleView) view.findViewById(R.id.recyclerView_single_course);
        HealthSubHeader healthSubHeader2 = (HealthSubHeader) view.findViewById(R.id.sub_header_series_course_title);
        this.ae = healthSubHeader2;
        healthSubHeader2.setRightDrawable(R.drawable._2131429719_res_0x7f0b0957);
        this.ae.setMoreText("");
        this.ae.setPaddingStartEnd(0.0f, 0.0f);
        ah();
        this.ae.setSubHeaderBackgroundColor(ContextCompat.getColor(BaseApplication.e(), R.color._2131299296_res_0x7f090be0));
    }

    private void ah() {
        this.al.setMoreViewClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FitnessCourseBehaviorFragment.this.aj.getVisibility() == 0) {
                    FitnessCourseBehaviorFragment.this.aj.setVisibility(8);
                    FitnessCourseBehaviorFragment.this.al.setRightDrawable(R.drawable._2131429713_res_0x7f0b0951);
                } else {
                    FitnessCourseBehaviorFragment.this.aj.setVisibility(0);
                    FitnessCourseBehaviorFragment.this.al.setRightDrawable(R.drawable._2131429719_res_0x7f0b0957);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ae.setMoreViewClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FitnessCourseBehaviorFragment.this.m.getVisibility() == 0) {
                    FitnessCourseBehaviorFragment.this.m.setVisibility(8);
                    FitnessCourseBehaviorFragment.this.ae.setRightDrawable(R.drawable._2131429713_res_0x7f0b0951);
                } else {
                    FitnessCourseBehaviorFragment.this.m.setVisibility(0);
                    FitnessCourseBehaviorFragment.this.ae.setRightDrawable(R.drawable._2131429719_res_0x7f0b0957);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void ag() {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), nsn.ag(BaseApplication.e()) ? 6 : 3) { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.20
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
        this.an.setLayoutManager(gridLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.16
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                super.getItemOffsets(rect, view, recyclerView, state);
                int spanCount = gridLayoutManager.getSpanCount();
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                if (childAdapterPosition < spanCount) {
                    rect.top = FitnessCourseBehaviorFragment.c;
                } else {
                    rect.top = 0;
                }
                rect.bottom = FitnessCourseBehaviorFragment.c;
                int i2 = ((spanCount - 1) * FitnessCourseBehaviorFragment.b) / spanCount;
                int i3 = (childAdapterPosition % spanCount) * (FitnessCourseBehaviorFragment.b - i2);
                rect.left = i3;
                rect.right = i2 - i3;
            }
        };
        this.am = itemDecoration;
        this.an.addItemDecoration(itemDecoration);
    }

    private void aAF_(View view) {
        HealthScrollView healthScrollView = (HealthScrollView) view.findViewById(R.id.course_behavior_scroll_view);
        this.k = healthScrollView;
        healthScrollView.setIsForceScrollListener(true);
        this.k.setScrollViewListener(new ScrollViewListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.18
            @Override // com.huawei.ui.commonui.scrollview.ScrollViewListener
            public void onScrollChanged(HealthScrollView healthScrollView2, int i2, int i3, int i4, int i5) {
                if (i3 == healthScrollView2.getChildAt(0).getMeasuredHeight() - healthScrollView2.getMeasuredHeight() && nsn.a(500) && FitnessCourseBehaviorFragment.this.v.c()) {
                    LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "BOTTOM SCROLL");
                    FitnessCourseBehaviorFragment.i(FitnessCourseBehaviorFragment.this);
                    FitnessCourseBehaviorFragment.this.b();
                }
            }
        });
    }

    private void ad() {
        if (nsn.m()) {
            ViewGroup.LayoutParams layoutParams = this.ah.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                ((LinearLayout.LayoutParams) layoutParams).bottomMargin = BaseApplication.e().getResources().getDimensionPixelSize(R.dimen._2131362886_res_0x7f0a0446);
            }
        }
        MyCourseAdapter myCourseAdapter = new MyCourseAdapter(this.ah);
        this.v = myCourseAdapter;
        myCourseAdapter.d(this.as);
        this.v.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.2
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeRemoved(int i2, int i3) {
                List<Object> b2 = FitnessCourseBehaviorFragment.this.v.b();
                if (!koq.b(b2)) {
                    b2.remove(i2);
                }
                if (FitnessCourseBehaviorFragment.this.l != null) {
                    FitnessCourseBehaviorFragment.this.l.deleteItem(FitnessCourseBehaviorFragment.this.as);
                    FitnessCourseBehaviorFragment.this.n.saveIssDeleteMode(false);
                }
            }
        });
    }

    private int v() {
        return this.j != 137 ? 4051 : 4052;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        if (this.as == 2 || ggs.d(this.j)) {
            this.d.setVisibility(8);
        } else {
            this.d.setVisibility(0);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment
    public void initViewTahiti() {
        HealthRecycleView healthRecycleView = this.ah;
        if (healthRecycleView != null && this.v != null) {
            healthRecycleView.setAdapter(null);
            this.ah.setLayoutManager(null);
            this.ah.setAdapter(this.v);
            ggs.b(getContext(), this.ah, true, true);
            this.v.notifyDataSetChanged();
        }
        HealthRecycleView healthRecycleView2 = this.an;
        if (healthRecycleView2 == null || this.ab == null) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "initViewTahiti, mSingleCourseRecyclerView is null or mMySingleCourseAdapter is null.");
            return;
        }
        healthRecycleView2.setLayoutManager(null);
        RecyclerView.ItemDecoration itemDecoration = this.am;
        if (itemDecoration != null) {
            this.an.removeItemDecoration(itemDecoration);
            this.am = null;
        }
        ag();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.s) {
            this.f = 0;
            b();
            this.s = false;
        }
        if (this.r && ggs.d(this.j)) {
            b();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.r = true;
    }

    public void b() {
        String str;
        aj();
        int i2 = this.j;
        if (i2 == 4) {
            str = "";
        } else {
            if (i2 == 5 || i2 == 6 || i2 == 7) {
                ac();
                return;
            }
            str = i2 != 257 ? i2 != 258 ? "FITNESS_COURSE" : "RUNNING_COURSE" : "WALKING_COURSE";
        }
        int i3 = this.as;
        if (i3 == 3) {
            this.v.d(3);
            x();
        } else if (i3 == 1) {
            this.v.d("collect");
            d(2, str);
        } else if (i3 == 2) {
            d(3, str);
        } else {
            this.v.d("downloaded");
            d(1, str);
        }
    }

    private void ac() {
        if (aa() == 1) {
            m();
            return;
        }
        final CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "getSleepRelaxCourse : courseApi is null.");
            return;
        }
        final int a2 = a(this.as);
        if (a2 == 3) {
            this.u.setVisibility(8);
        } else {
            this.u.setVisibility(0);
        }
        final int e2 = e(this.j);
        LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "getSleepOrRelaxCourseCourse : courseCategory = ", Integer.valueOf(e2), " opeType = ", Integer.valueOf(a2));
        ThreadPoolManager.d().execute(new Runnable() { // from class: fmv
            @Override // java.lang.Runnable
            public final void run() {
                FitnessCourseBehaviorFragment.this.a(a2, e2, courseApi);
            }
        });
    }

    public /* synthetic */ void a(int i2, int i3, CourseApi courseApi) {
        courseApi.getAudioBehaviorList(new fff.e().a(i2).b(2).c(i3).e(50).d(this.f + 1).a(), new b(this, i2));
    }

    static class b extends UiCallback<List<SleepAudioSeries>> {
        private WeakReference<FitnessCourseBehaviorFragment> b;
        private final int e;

        public b(FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment, int i) {
            this.b = new WeakReference<>(fitnessCourseBehaviorFragment);
            this.e = i;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Suggestion_FitnessCourseBehaviorFragment", "onFailure errorCode =", Integer.valueOf(i), " errorInfo =", str);
            FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment = this.b.get();
            if (fitnessCourseBehaviorFragment != null) {
                fitnessCourseBehaviorFragment.c(false);
                if (fitnessCourseBehaviorFragment.f == 0) {
                    fitnessCourseBehaviorFragment.o();
                    return;
                } else {
                    fitnessCourseBehaviorFragment.w();
                    return;
                }
            }
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "AudioBehaviorDataCalBack onFailure fragment == null");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<SleepAudioSeries> list) {
            LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "onSuccess data = ", list.toString(), " opeType = ", Integer.valueOf(this.e));
            FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment = this.b.get();
            if (fitnessCourseBehaviorFragment != null) {
                fitnessCourseBehaviorFragment.e(list, this.e);
                fitnessCourseBehaviorFragment.w();
            } else {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "AudioBehaviorDataCalBack onSuccess fragment == null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<SleepAudioSeries> list, int i2) {
        if (koq.b(list)) {
            if (this.f == 0) {
                o();
            }
            c(false);
        } else {
            if (i2 == 2 || i2 == 1) {
                d(list);
                ae();
                ai();
                a(false, false);
                return;
            }
            LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "opeType is not COLLECTION");
            a(list);
        }
    }

    private void ai() {
        if (koq.c(this.g)) {
            LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "collectSingleCourseData is collectSingleCourseData.size = ", Integer.valueOf(this.g.size()), " collectSingleCourseData = ", this.g.toString());
            this.al.setVisibility(0);
            this.u.setVisibility(0);
            this.ab.notifyDataSetChanged();
            return;
        }
        LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "collectSingleCourseData is empty");
        this.u.setVisibility(8);
        this.al.setVisibility(8);
    }

    private void ae() {
        if (koq.c(this.h)) {
            LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "collectSeriesCourseData is collectSeriesCourseData.size = ", Integer.valueOf(this.h.size()));
            this.ae.setVisibility(0);
            this.m.setVisibility(0);
            a(this.h);
            return;
        }
        LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "collectSeriesCourseData is empty");
        this.ae.setVisibility(8);
        this.m.setVisibility(8);
    }

    private void d(List<SleepAudioSeries> list) {
        if (koq.b(list)) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "getSingleAndSeriesCourseData data is empty");
            return;
        }
        this.e = true;
        if (koq.c(this.h)) {
            this.h.clear();
        }
        if (koq.c(this.g)) {
            this.g.clear();
        }
        LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "getSingleAndSeriesCourseData data = ", list.toString());
        for (int i2 = 0; i2 < list.size(); i2++) {
            SleepAudioSeries sleepAudioSeries = list.get(i2);
            if (sleepAudioSeries == null) {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "courseData is null");
            } else {
                int infoType = sleepAudioSeries.getInfoType();
                if (infoType == 0) {
                    this.g.add(sleepAudioSeries);
                } else if (infoType == 1) {
                    this.h.add(sleepAudioSeries);
                } else {
                    LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "type is not audio and series type = ", Integer.valueOf(infoType));
                }
            }
        }
        LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "getSingleAndSeriesCourseData : collectSingleCourseData = ", this.g, " collectSeriesCourseData = ", this.h);
    }

    private void a(List<SleepAudioSeries> list) {
        if (!isAdded() || koq.b(list)) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "dataCollectionSuccess activity or workouts is invalid.");
            return;
        }
        j();
        c(list.size() >= 50);
        this.q = true;
        this.e = true;
        i(new ArrayList(list));
    }

    private void x() {
        if (aa() == 1) {
            LogUtil.b("Suggestion_FitnessCourseBehaviorFragment", "getDataCustom Network isNetworkAvailable.");
            m();
            return;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.b("Suggestion_FitnessCourseBehaviorFragment", "getDataCustom courseApi is null.");
        } else {
            courseApi.getCoursesBySecondClassifyId(this.f * 50, 50, 0, 1, new e(this));
        }
    }

    static class e extends UiCallback<List<Workout>> {
        WeakReference<FitnessCourseBehaviorFragment> d;

        public e(FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment) {
            this.d = new WeakReference<>(fitnessCourseBehaviorFragment);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            WeakReference<FitnessCourseBehaviorFragment> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "CustomCoursesCallback onFailure mReference is null.");
                return;
            }
            FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment = weakReference.get();
            if (fitnessCourseBehaviorFragment != null) {
                LogUtil.b("Suggestion_FitnessCourseBehaviorFragment", "getDataCustom mType = ", Integer.valueOf(fitnessCourseBehaviorFragment.as), " failed. ", Integer.valueOf(i), ", ", str);
                fitnessCourseBehaviorFragment.c(false);
                if (fitnessCourseBehaviorFragment.f == 0) {
                    fitnessCourseBehaviorFragment.o();
                    return;
                } else {
                    fitnessCourseBehaviorFragment.w();
                    return;
                }
            }
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "CustomCoursesCallback onFailure fragment is null.");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<Workout> list) {
            WeakReference<FitnessCourseBehaviorFragment> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "CustomCoursesCallback onSuccess mReference is null.");
                return;
            }
            FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment = weakReference.get();
            if (fitnessCourseBehaviorFragment == null) {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "CustomCoursesCallback onSuccess fragment is null.");
                return;
            }
            List<FitWorkout> a2 = mod.a(list);
            if (koq.b(a2)) {
                fitnessCourseBehaviorFragment.c(false);
                if (fitnessCourseBehaviorFragment.f == 0) {
                    fitnessCourseBehaviorFragment.o();
                    return;
                }
                return;
            }
            Iterator<FitWorkout> it = a2.iterator();
            while (it.hasNext()) {
                it.next().setCourseDefineType(1);
            }
            fitnessCourseBehaviorFragment.c(mod.j(a2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (getActivity() == null || !isAdded()) {
            LogUtil.b("Suggestion_FitnessCourseBehaviorFragment", "dealWithGetDataFailed0rIsEmpty activity is null or is not added.");
        } else {
            HandlerExecutor.e(new Runnable() { // from class: fmw
                @Override // java.lang.Runnable
                public final void run() {
                    FitnessCourseBehaviorFragment.this.i();
                }
            });
        }
    }

    public /* synthetic */ void i() {
        am();
        w();
        this.q = true;
        this.z.setVisibility(8);
        a(true, false);
        y();
        this.e = false;
        if (this.l != null) {
            this.n.saveIssDeleteMode(this.p);
            this.l.deleteItem(this.as);
        } else {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "dealWithGetDataFailed0rIsEmpty mDeleteModeListener is null");
        }
        this.v.e(this.n, true, new ArrayList(0));
    }

    private void am() {
        int a2 = a(this.as);
        if ((a2 == 2 || a2 == 1) && ggs.d(this.j)) {
            this.g.clear();
            this.h.clear();
            MySingleCourseAdapter mySingleCourseAdapter = this.ab;
            if (mySingleCourseAdapter != null) {
                mySingleCourseAdapter.notifyDataSetChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        w();
        this.ak.setText(R.string._2130844161_res_0x7f021a01);
        this.f3116a.setVisibility(0);
        this.d.setVisibility(8);
        this.q = true;
        this.z.setVisibility(0);
        a(false, false);
        this.e = false;
        this.v.e(this.n, true, new ArrayList(new ArrayList(0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int aa() {
        return !CommonUtil.aa(BaseApplication.e()) ? 1 : 0;
    }

    private void d(int i2, String str) {
        if (aa() == 1) {
            m();
            return;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "getCourseData : courseApi is null.");
        } else {
            courseApi.getUserCourseList(this.f * 50, 50, i2, str, new a(this));
        }
    }

    static class a extends UiCallback<List<Workout>> {
        WeakReference<FitnessCourseBehaviorFragment> c;

        a(FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment) {
            this.c = new WeakReference<>(fitnessCourseBehaviorFragment);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            WeakReference<FitnessCourseBehaviorFragment> weakReference = this.c;
            if (weakReference == null) {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "CollectionCoursesCallback onFailure mReference is null.");
                return;
            }
            FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment = weakReference.get();
            if (fitnessCourseBehaviorFragment == null) {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "CollectionCoursesCallback onFailure fragment is null.");
                return;
            }
            LogUtil.b("Suggestion_FitnessCourseBehaviorFragment", str, "Failed,errorCode:", Integer.valueOf(i));
            fitnessCourseBehaviorFragment.c(false);
            if (fitnessCourseBehaviorFragment.f == 0) {
                fitnessCourseBehaviorFragment.o();
            } else {
                fitnessCourseBehaviorFragment.w();
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<Workout> list) {
            WeakReference<FitnessCourseBehaviorFragment> weakReference = this.c;
            if (weakReference == null) {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "CollectionCoursesCallback onSuccess mReference is null.");
                return;
            }
            FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment = weakReference.get();
            if (fitnessCourseBehaviorFragment != null) {
                if (fitnessCourseBehaviorFragment.aa() == 1) {
                    fitnessCourseBehaviorFragment.m();
                    return;
                } else {
                    if (koq.b(list)) {
                        if (fitnessCourseBehaviorFragment.f == 0) {
                            fitnessCourseBehaviorFragment.o();
                        }
                        fitnessCourseBehaviorFragment.c(false);
                        return;
                    }
                    fitnessCourseBehaviorFragment.c(list);
                    return;
                }
            }
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "CollectionCoursesCallback onSuccess fragment is null.");
        }
    }

    private void i(final List<Object> list) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "refreshDataWhenSuccess activity is null");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    FitnessCourseBehaviorFragment.this.w();
                    FitnessCourseBehaviorFragment.this.a(false, true);
                    FitnessCourseBehaviorFragment.this.z.setVisibility(8);
                    FitnessCourseBehaviorFragment.this.y();
                    if (FitnessCourseBehaviorFragment.this.f == 0) {
                        FitnessCourseBehaviorFragment.this.v.e(FitnessCourseBehaviorFragment.this.n, true, list);
                    } else {
                        FitnessCourseBehaviorFragment.this.v.d(FitnessCourseBehaviorFragment.this.n, true, list);
                    }
                    if (FitnessCourseBehaviorFragment.this.l != null) {
                        FitnessCourseBehaviorFragment.this.n.saveIssDeleteMode(FitnessCourseBehaviorFragment.this.p);
                        FitnessCourseBehaviorFragment.this.l.deleteItem(FitnessCourseBehaviorFragment.this.as);
                    } else {
                        LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "refreshDataWhenSuccess mDeleteModeListener is null");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<Workout> list) {
        if (!isAdded() || koq.b(list)) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "dataCollectionSuccess activity or workouts is invalid.");
            return;
        }
        j();
        int i2 = this.j;
        if (i2 != 258 && i2 != 257) {
            list = e(list);
        }
        c(list.size() >= 50);
        if (koq.b(list)) {
            if (this.f == 0) {
                o();
                return;
            }
            w();
            this.e = false;
            this.q = true;
            return;
        }
        this.q = true;
        this.e = true;
        i(new ArrayList(list));
    }

    private void j() {
        this.t.setIconVisible(2, 8);
        this.t.setIconVisible(1, 8);
        this.t.setVisibility(8);
        this.af.clear();
        this.p = false;
        y();
        this.n.saveIssDeleteMode(this.p);
        this.n.saveSelects(this.af);
    }

    private List<Workout> e(List<Workout> list) {
        ArrayList arrayList = new ArrayList(10);
        for (Workout workout : list) {
            if (workout != null) {
                if (this.j == 4) {
                    arrayList.add(workout);
                } else {
                    FitWorkout a2 = mod.a(workout);
                    if (a2 != null && a2.getWorkoutType() == 1 && (this.j == 0 || a2.getCategory() == this.j)) {
                        arrayList.add(workout);
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final boolean z) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.4
                @Override // java.lang.Runnable
                public void run() {
                    FitnessCourseBehaviorFragment.this.v.e(z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    FitnessCourseBehaviorFragment.this.x.setVisibility(8);
                }
            });
        }
    }

    private void aj() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.7
                @Override // java.lang.Runnable
                public void run() {
                    FitnessCourseBehaviorFragment.this.z.setVisibility(8);
                    FitnessCourseBehaviorFragment.this.x.setVisibility(0);
                    FitnessCourseBehaviorFragment.this.y();
                }
            });
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.btn_no_net_work) {
            CommonUtil.q(getContext());
        } else if (view.getId() == R.id.reload_layout) {
            if (this.q) {
                this.q = false;
                b();
            }
        } else if (view.getId() == R.id.btn_add_course) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                if (this.j == 258) {
                    d(activity);
                    HashMap hashMap = new HashMap(10);
                    hashMap.put("click", 1);
                    ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_SPORT_TAP_ALL_RUNNING_COURSE_1120018.value(), hashMap, 0);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                JumpUtil.b(arx.b(), this.j, 3, "FITNESS_COURSE");
            }
            HashMap hashMap2 = new HashMap(10);
            hashMap2.put("click", 1);
            hashMap2.put(BleConstants.SPORT_TYPE, 10001);
            ixx.d().d(BaseApplication.e(), AnalyticsValue.EVENT_CLICK_FITNESS_COURSE_MORE.value(), hashMap2, 0);
        } else {
            LogUtil.c("Suggestion_FitnessCourseBehaviorFragment", "else onClick ");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(FragmentActivity fragmentActivity) {
        if (this.as == 3) {
            gnm.aPB_(arx.b(), new Intent(fragmentActivity, (Class<?>) CustomCourseActivity.class));
        } else {
            JumpUtil.b(arx.b(), this.j, 3, "RUNNING_COURSE");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        int i2 = this.ag;
        if (i2 == 1) {
            this.ag = 0;
        } else if (i2 == 0) {
            this.ag = 1;
        } else {
            LogUtil.c("Suggestion_FitnessCourseBehaviorFragment", "else mSelectType ");
        }
        if (this.ag == 1) {
            this.t.setIconTitleColor(2, nsf.h(R$string.IDS_contact_delete_uncheck_all), R$color.textColorPrimary);
            this.t.setIcon(2, R.drawable._2131431674_res_0x7f0b10fa);
            for (int i3 = 0; i3 < this.v.b().size(); i3++) {
                if (!this.af.contains(Integer.valueOf(i3))) {
                    this.af.add(Integer.valueOf(i3));
                }
            }
        } else {
            this.t.setIconTitleColor(2, nsf.h(R$string.IDS_contact_delete_select_all), R$color.textColorPrimary);
            this.t.setIcon(2, R.drawable._2131431675_res_0x7f0b10fb);
            this.af.clear();
        }
        ab();
        this.n.saveSelects(this.af);
        DeleteModeListener deleteModeListener = this.l;
        if (deleteModeListener != null) {
            deleteModeListener.deleteItem(this.as);
            this.n.saveIssDeleteMode(this.p);
        }
        this.v.b(this.n, true);
    }

    private void ab() {
        List<SleepAudioSeries> c2 = this.ab.c();
        if (koq.b(c2)) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "handleSingleCourse singleAudioCourse is empty");
            return;
        }
        if (this.ag == 1) {
            g(c2);
        } else {
            b(c2);
        }
        this.ab.notifyDataSetChanged();
    }

    private void b(List<SleepAudioSeries> list) {
        if (koq.b(list)) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "clearSingleCourse singleAudioCourse is empty");
            return;
        }
        this.o.clear();
        for (SleepAudioSeries sleepAudioSeries : list) {
            if (sleepAudioSeries != null) {
                sleepAudioSeries.setIsSelected(0);
            }
        }
    }

    private void g(List<SleepAudioSeries> list) {
        if (koq.b(list)) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "selectAllSingleCourse singleAudioCourse is empty");
            return;
        }
        for (SleepAudioSeries sleepAudioSeries : list) {
            if (sleepAudioSeries != null && !this.o.contains(sleepAudioSeries)) {
                this.o.add(sleepAudioSeries);
                sleepAudioSeries.setIsSelected(1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void al() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(getContext());
        builder.e(getString(R.string._2130848456_res_0x7f022ac8)).czE_(getString(R.string._2130848357_res_0x7f022a65).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FitnessCourseBehaviorFragment.this.t();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czA_(getString(com.huawei.health.servicesui.R$string.IDS_plugin_fitnessadvice_cancal), new View.OnClickListener() { // from class: fna
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FitnessCourseBehaviorFragment.this.aAI_(view);
            }
        });
        builder.e().show();
    }

    public /* synthetic */ void aAI_(View view) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("page", Integer.valueOf(this.as));
        hashMap.put("type", 0);
        hashMap.put("from", Integer.valueOf(ggr.a(this.j)));
        ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_FITNESS_MY_COURSE_1130030.value(), hashMap, 0);
        LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "it is negative");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(Object obj) {
        if (obj instanceof Workout) {
            Workout workout = (Workout) obj;
            FitWorkout a2 = mod.a(workout);
            if (a2 != null) {
                if (a2.getCourseBelongInfoByType(0) != null) {
                    a2.setCourseBelongType(1);
                } else {
                    a2.setCourseBelongType(0);
                }
                this.ap.add(a2);
                return;
            }
            WorkoutPackageInfo e2 = mod.e(workout);
            if (e2 != null) {
                e2.setCourseBelongType(2);
                e2.saveId(e2.getWorkoutPackageId());
                this.ap.add(e2);
                return;
            }
            return;
        }
        if (obj instanceof SleepAudioSeries) {
            this.ap.add((SleepAudioSeries) obj);
        } else {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "object unknown type");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        r();
        List<Object> b2 = this.v.b();
        if (b2 == null) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "showDeleteDialog allWorkout is null");
            return;
        }
        this.ap.clear();
        for (Integer num : this.af) {
            if (koq.b(b2, num.intValue())) {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "allWorkout isOutOfBounds ");
            } else {
                b(b2.get(num.intValue()));
            }
        }
        int i2 = this.as;
        if (i2 == 3) {
            q();
        } else if (i2 == 1) {
            p();
        } else {
            s();
        }
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("page", Integer.valueOf(this.as));
        hashMap.put("type", 2);
        hashMap.put("from", Integer.valueOf(ggr.a(this.j)));
        ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_FITNESS_MY_COURSE_1130030.value(), hashMap, 0);
    }

    private void r() {
        LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", " deleteAudioSingleCourse = ", this.o);
        int i2 = 0;
        if (koq.b(this.o)) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "deleteAudioSingleCourse deleteCollectSleepCourses is empty");
            for (SleepAudioSeries sleepAudioSeries : this.ab.c()) {
                sleepAudioSeries.setIsShowCheckBox(0);
                sleepAudioSeries.setIsSelected(0);
            }
            this.ab.notifyDataSetChanged();
            return;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "deleteAudioSingleCourse : courseApi is null");
            return;
        }
        int i3 = this.as;
        if (i3 == 0) {
            i2 = this.o.size();
            b(courseApi, this.o);
        } else if (i3 == 1) {
            Iterator<SleepAudioSeries> it = this.o.iterator();
            while (it.hasNext()) {
                i2++;
                c(courseApi, it.next().getId());
            }
        } else {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "delete Audio Single mType= ", Integer.valueOf(i3));
        }
        if (i2 == this.o.size()) {
            this.o.clear();
        }
    }

    private void b(CourseApi courseApi, List<SleepAudioSeries> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<SleepAudioSeries> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(it.next().getId()));
        }
        courseApi.deletePlayRecord(arrayList, new i(arrayList, this));
    }

    static class i extends UiCallback<Boolean> {
        List<Integer> c;
        WeakReference<FitnessCourseBehaviorFragment> d;

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
        }

        public i(List<Integer> list, FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment) {
            this.c = list;
            this.d = new WeakReference<>(fitnessCourseBehaviorFragment);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Boolean bool) {
            WeakReference<FitnessCourseBehaviorFragment> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "DeleteUsedAudioSingleCourses onSuccess mReference is null.");
                return;
            }
            FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment = weakReference.get();
            if (fitnessCourseBehaviorFragment == null) {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "DeleteUsedAudioSingleCourses onSuccess fragment is null.");
                return;
            }
            Iterator<Integer> it = this.c.iterator();
            while (it.hasNext()) {
                fitnessCourseBehaviorFragment.c(it.next().intValue());
            }
        }
    }

    private void c(CourseApi courseApi, int i2) {
        courseApi.operateFavoriteAudio(i2, 2, new d(i2, this));
    }

    static class d extends UiCallback<Boolean> {

        /* renamed from: a, reason: collision with root package name */
        int f3118a;
        WeakReference<FitnessCourseBehaviorFragment> d;

        public d(int i, FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment) {
            this.f3118a = i;
            this.d = new WeakReference<>(fitnessCourseBehaviorFragment);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "DeleteCollectedAudioSingleCourses onFailure seriesId=", Integer.valueOf(this.f3118a));
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Boolean bool) {
            WeakReference<FitnessCourseBehaviorFragment> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "DeleteCollectedAudioSingleCourses onSuccess mReference is null.");
                return;
            }
            FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment = weakReference.get();
            if (fitnessCourseBehaviorFragment != null) {
                fitnessCourseBehaviorFragment.c(this.f3118a);
            } else {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "DeleteCollectedAudioSingleCourses onSuccess fragment is null.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2) {
        LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "doDeleteAudioSingleCourse seriesId=", Integer.valueOf(i2));
        List<SleepAudioSeries> c2 = this.ab.c();
        if (koq.b(c2)) {
            LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "doDeleteAudioSingleCourse audioSeriesList is empty");
            return;
        }
        Iterator<SleepAudioSeries> it = c2.iterator();
        while (it.hasNext()) {
            SleepAudioSeries next = it.next();
            if (next.getId() == i2) {
                it.remove();
            } else {
                next.setIsShowCheckBox(0);
                next.setIsSelected(0);
            }
        }
        this.p = false;
        this.n.saveIssDeleteMode(false);
        this.v.b(this.n, true);
        d(0);
        DeleteModeListener deleteModeListener = this.l;
        if (deleteModeListener != null) {
            deleteModeListener.deleteItem(this.as);
        }
        this.t.setVisibility(8);
        this.ab.notifyDataSetChanged();
        if (koq.b(c2)) {
            this.g.clear();
            this.al.setVisibility(8);
            this.aj.setVisibility(8);
            a(true, false);
        }
    }

    private void q() {
        if (koq.e((Object) this.ap, Workout.class)) {
            for (Workout workout : koq.e((List) this.ap, Workout.class)) {
                if (workout != null) {
                    final String acquireId = workout.acquireId();
                    if (TextUtils.isEmpty(acquireId)) {
                        LogUtil.b("Suggestion_FitnessCourseBehaviorFragment", "doDeleteCustom workoutId is invalid.");
                        return;
                    }
                    CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
                    if (courseApi == null) {
                        LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "deleteUserDefinedWorkout copy courseApi is null.");
                        return;
                    }
                    courseApi.deleteUserDefinedWorkout(acquireId, 1, new UiCallback<String>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.9
                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        public void onFailure(int i2, String str) {
                            LogUtil.b("Suggestion_FitnessCourseBehaviorFragment", "deleteUserDefinedWorkout errCode = ", Integer.valueOf(i2), ", errInfo = ", str);
                            nrh.d(BaseApplication.e(), str);
                        }

                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        /* renamed from: e, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(String str) {
                            LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "deleteUserDefinedWorkout onSuccess workoutId is ", acquireId);
                        }
                    });
                }
            }
            u();
        }
    }

    private void a(CourseApi courseApi, List<Workout> list) {
        courseApi.deleteUserJoinedCourses(list, new g(list, this));
    }

    static class g extends UiCallback<String> {
        List<Workout> b;
        WeakReference<FitnessCourseBehaviorFragment> e;

        public g(List<Workout> list, FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment) {
            this.b = list;
            this.e = new WeakReference<>(fitnessCourseBehaviorFragment);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Suggestion_FitnessCourseBehaviorFragment", "delete workout error ", str, "--errorcode:", Integer.valueOf(i));
            nrh.c(BaseApplication.e(), str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            WeakReference<FitnessCourseBehaviorFragment> weakReference = this.e;
            if (weakReference == null) {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "DeleteJoinedFitnessCourses onSuccess mReference is null.");
                return;
            }
            FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment = weakReference.get();
            if (fitnessCourseBehaviorFragment != null) {
                fitnessCourseBehaviorFragment.u();
                fot.a().e(mod.a(this.b));
            } else {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "DeleteJoinedFitnessCourses onSuccess fragment is null.");
            }
        }
    }

    private void c(CourseApi courseApi, List<SleepAudioSeries> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<SleepAudioSeries> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(it.next().getId()));
        }
        courseApi.deletePlayRecord(arrayList, new f(this));
    }

    static class f extends UiCallback<Boolean> {
        WeakReference<FitnessCourseBehaviorFragment> d;

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
        }

        public f(FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment) {
            this.d = new WeakReference<>(fitnessCourseBehaviorFragment);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Boolean bool) {
            WeakReference<FitnessCourseBehaviorFragment> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "DeleteJoinedSleepCourses onSuccess mReference is null.");
                return;
            }
            FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment = weakReference.get();
            if (fitnessCourseBehaviorFragment != null) {
                fitnessCourseBehaviorFragment.u();
            } else {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "DeleteJoinedSleepCourses onSuccess fragment is null.");
            }
        }
    }

    private void s() {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "deleteJoined : courseApi is null.");
            return;
        }
        if (koq.b(this.ap)) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "mToDelWorkouts is null.");
        } else if (koq.e((Object) this.ap, Workout.class)) {
            a(courseApi, koq.e((List) this.ap, Workout.class));
        } else if (koq.e((Object) this.ap, SleepAudioSeries.class)) {
            c(courseApi, koq.e((List) this.ap, SleepAudioSeries.class));
        }
    }

    private void e(CourseApi courseApi, List<Workout> list) {
        for (Workout workout : list) {
            if (workout != null) {
                String acquireId = workout.acquireId();
                if (workout instanceof WorkoutPackageInfo) {
                    WorkoutPackageInfo workoutPackageInfo = (WorkoutPackageInfo) workout;
                    LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "delete series course:", workoutPackageInfo.getWorkoutPackageId(), " ", Integer.valueOf(workout.getCourseBelongType()));
                    courseApi.uncollectCourse(workoutPackageInfo.getWorkoutPackageId(), workout.getCourseBelongType());
                } else {
                    LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "delete course:", acquireId);
                    courseApi.uncollectCourse(acquireId);
                }
                u();
            }
        }
    }

    private void d(CourseApi courseApi, List<SleepAudioSeries> list) {
        Iterator<SleepAudioSeries> it = list.iterator();
        while (it.hasNext()) {
            courseApi.operateFavoriteAudio(it.next().getId(), 2, new c(this));
        }
    }

    static class c extends UiCallback<Boolean> {
        WeakReference<FitnessCourseBehaviorFragment> d;

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
        }

        public c(FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment) {
            this.d = new WeakReference<>(fitnessCourseBehaviorFragment);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Boolean bool) {
            WeakReference<FitnessCourseBehaviorFragment> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "DeleteCollectedSleepCourse onSuccess mReference is null.");
                return;
            }
            FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment = weakReference.get();
            if (fitnessCourseBehaviorFragment != null) {
                fitnessCourseBehaviorFragment.u();
            } else {
                LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "DeleteCollectedSleepCourse onSuccess fragment is null.");
            }
        }
    }

    private void p() {
        if (this.ap == null) {
            LogUtil.b("Suggestion_FitnessCourseBehaviorFragment", "mToDelWorkouts is null");
            return;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "deleteCollect : courseApi is null.");
        } else if (koq.e((Object) this.ap, Workout.class)) {
            e(courseApi, koq.e((List) this.ap, Workout.class));
        } else if (koq.e((Object) this.ap, SleepAudioSeries.class)) {
            d(courseApi, koq.e((List) this.ap, SleepAudioSeries.class));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "mDeleteModel.acquireSelects().size():", Integer.valueOf(this.n.acquireSelects().size()));
        if (this.n.acquireSelects().size() > 0) {
            HandlerExecutor.d(new Runnable() { // from class: fnd
                @Override // java.lang.Runnable
                public final void run() {
                    FitnessCourseBehaviorFragment.this.n();
                }
            }, 20L);
        } else {
            this.t.setIconVisible(2, 8);
            this.t.setIconVisible(1, 8);
            y();
            this.t.setVisibility(8);
        }
        this.p = false;
        this.n.saveIssDeleteMode(false);
        this.v.b(this.n, true);
        d(0);
        ary.a().e("WORKOUT_DELETE");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        Collections.sort(this.af, new Comparator<Integer>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.8
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(Integer num, Integer num2) {
                return num2.intValue() - num.intValue();
            }
        });
        Iterator<Integer> it = this.af.iterator();
        while (it.hasNext()) {
            this.v.notifyItemRemoved(it.next().intValue());
        }
        this.af.clear();
        if (this.ag == 1) {
            k();
        }
        this.e = true;
        this.p = false;
        y();
        this.n.saveSelects(this.af);
        this.t.setVisibility(8);
        this.n.saveIssDeleteMode(false);
        if (this.v.b() != null && this.v.b().size() == 0) {
            if ((a(this.as) == 2 || a(this.as) == 1) && ggs.d(this.j)) {
                this.h.clear();
                this.ae.setVisibility(8);
                this.m.setVisibility(8);
            }
            a(true, false);
            this.z.setVisibility(8);
            this.e = false;
        }
        this.l.deleteItem(this.as);
        this.v.b(this.n, true);
        this.ah.requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, boolean z2) {
        int a2 = a(this.as);
        LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "isNoRecordOrContainerLayoutShow :", "opeType = ", Integer.valueOf(a2), " mCourseCategory = ", Integer.valueOf(this.j));
        if ((a2 != 2 && a2 != 1) || !ggs.d(this.j)) {
            LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "opeType is not collection is show = ", Boolean.valueOf(z));
            if (z) {
                this.aa.setVisibility(0);
            } else {
                this.aa.setVisibility(8);
            }
            if (z2) {
                this.i.setVisibility(0);
                return;
            } else {
                this.i.setVisibility(8);
                return;
            }
        }
        LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "isNoRecordLayoutShow :", "collectSingleCourseData.size() = ", Integer.valueOf(this.g.size()), "; collectSeriesCourseData.size() = ", Integer.valueOf(this.h.size()));
        if (koq.b(this.g) && koq.b(this.h)) {
            this.aa.setVisibility(0);
            this.i.setVisibility(8);
        } else {
            this.aa.setVisibility(8);
            this.i.setVisibility(0);
        }
        if (aa() == 1) {
            this.aa.setVisibility(8);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        fot.a().c();
        ary.a().c(this.aq, "WORKOUT_DELETE");
        ary.a().c(this.aq, "WORKOUT_FINISHED");
        ary.a().c(this.aq, "COLLECTION_ADD");
        ary.a().c(this.aq, "COLLECTION_DELETE");
        ary.a().c(this.aq, "CUSTOM_COURSE_UPDATE");
        this.aq = null;
        super.onDestroy();
    }

    private void aAE_(View view) {
        HealthToolBar healthToolBar = (HealthToolBar) view.findViewById(R.id.select_view);
        this.t = healthToolBar;
        healthToolBar.cHc_(View.inflate(getContext(), R.layout.hw_toolbar_bottomview, null));
        healthToolBar.setIcon(1, R.drawable._2131430279_res_0x7f0b0b87);
        healthToolBar.setIconTitle(1, nsf.h(R$string.IDS_music_management_delete));
        healthToolBar.setIcon(2, R.drawable._2131431675_res_0x7f0b10fb);
        healthToolBar.setIconTitle(2, nsf.h(R$string.IDS_contact_delete_select_all));
        healthToolBar.setOnSingleTapListener(this.ar);
        healthToolBar.setVisibility(8);
        healthToolBar.cHf_(getActivity());
    }

    public void e(boolean z) {
        LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "setDeleteModeListener ", Boolean.valueOf(z));
        d(z);
        this.p = z;
        if (this.as == 2 || ggs.d(this.j)) {
            this.d.setVisibility(8);
        } else {
            this.d.setVisibility(this.p ? 8 : 0);
        }
        this.t.setVisibility(this.p ? 0 : 8);
        if (this.p) {
            this.t.setIconEnabled(1, true);
            this.t.setIconEnabled(2, true);
            this.t.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessCourseBehaviorFragment.6
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    if (FitnessCourseBehaviorFragment.this.t.getHeight() > 0) {
                        FitnessCourseBehaviorFragment fitnessCourseBehaviorFragment = FitnessCourseBehaviorFragment.this;
                        fitnessCourseBehaviorFragment.d(fitnessCourseBehaviorFragment.t.getHeight());
                    }
                    FitnessCourseBehaviorFragment.this.t.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            });
        } else {
            d(0);
        }
        this.n.saveSelects(this.af);
        this.n.saveIssDeleteMode(this.p);
        this.v.b(this.n, true);
    }

    private void d(boolean z) {
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            SleepAudioSeries sleepAudioSeries = this.g.get(i2);
            if (sleepAudioSeries != null) {
                sleepAudioSeries.setIsShowCheckBox(z ? 1 : 0);
            }
        }
        this.ab.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i2) {
        if (this.t == null) {
            LogUtil.h("Suggestion_FitnessCourseBehaviorFragment", "setRecyclerMarginBottom, mHealthToolbar is null.");
        } else if (this.k.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.k.getLayoutParams();
            marginLayoutParams.bottomMargin = i2;
            this.k.setLayoutParams(marginLayoutParams);
            this.ah.requestLayout();
        }
    }

    public void b(DeleteModeListener deleteModeListener) {
        this.l = deleteModeListener;
    }

    public boolean g() {
        return this.e;
    }

    public boolean h() {
        int size = this.h.size() + this.g.size();
        LogUtil.a("Suggestion_FitnessCourseBehaviorFragment", "hasRecordForSleepOrDecompress courseDataSize = ", Integer.valueOf(size));
        return size > 0;
    }

    public int c() {
        return this.as;
    }

    public int e() {
        return this.o.size() + this.af.size();
    }

    public boolean f() {
        return this.p;
    }

    static class j extends HealthLinearLayoutManager {
        j(Context context) {
            super(context);
        }

        @Override // com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {
                LogUtil.b("Suggestion_FitnessCourseBehaviorFragment", LogAnonymous.b((Throwable) e));
            }
        }
    }
}
