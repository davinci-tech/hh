package com.huawei.health.suggestion.ui.fitness.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.model.WorkoutListBean;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.sport.view.FitnessCardView;
import com.huawei.health.suggestion.ui.BaseStateActivity;
import com.huawei.health.suggestion.ui.callback.UiPagingCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.FragmentInstrumentation;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.adapter.LoadMoreRecyclerViewAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.arx;
import defpackage.asc;
import defpackage.ash;
import defpackage.ffy;
import defpackage.fnz;
import defpackage.fot;
import defpackage.fpg;
import defpackage.ggm;
import defpackage.ggs;
import defpackage.ghb;
import defpackage.gic;
import defpackage.koq;
import defpackage.mmp;
import defpackage.mod;
import defpackage.moe;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes8.dex */
public class RecommendListFragment extends Fragment implements View.OnClickListener {
    private int ab;
    private HealthRecycleView ac;
    private int ad;
    private Integer[] af;
    private HealthCheckBox ai;
    private Integer[] ak;
    private float al;
    private Integer[] am;
    private BaseStateActivity b;
    private HealthButton c;
    private ArrayList<String> d;
    private LoadMoreRecyclerViewAdapter e;
    private Context g;
    private View h;
    private LinearLayout j;
    private Integer[] k;
    private ImageView l;
    private int m;
    private View n;
    private Integer[] o;
    private boolean p;
    private boolean q;
    private HealthTextView r;
    private int s;
    private Integer[] u;
    private LinearLayoutManager v;
    private View w;
    private Integer x;
    private int z;
    private int y = -1;
    private int ag = 1;
    private List<Integer> ah = new ArrayList(10);
    private List<FitWorkout> ae = new ArrayList(10);
    private boolean t = false;
    private ArrayList<String> f = new ArrayList<>(10);
    private ArrayList<String> i = new ArrayList<>(10);
    private HashMap<Integer, SparseBooleanArray> aa = new HashMap<>();

    /* renamed from: a, reason: collision with root package name */
    private SparseBooleanArray f3129a = new SparseBooleanArray();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        p();
        this.g = getActivity();
        FragmentActivity activity = getActivity();
        if (activity instanceof BaseStateActivity) {
            this.b = (BaseStateActivity) activity;
        }
        View aAZ_ = aAZ_(layoutInflater, viewGroup);
        u();
        if (this.s != 1) {
            this.m = 0;
            this.ab = 0;
            asc.e().b(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    RecommendListFragment.this.t();
                }
            });
        } else {
            this.r.setText(R.string._2130848454_res_0x7f022ac6);
        }
        return aAZ_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        long j;
        String b = ash.b(o());
        if (StringUtils.i(b)) {
            String[] split = b.split("_");
            if (split.length > 1) {
                try {
                    j = Long.parseLong(split[1]);
                } catch (NumberFormatException unused) {
                    LogUtil.b("Suggestion_RecoListFm", "all fitness course number format exception");
                }
                if (j != 0 || System.currentTimeMillis() - j > 600000) {
                    this.t = true;
                    d();
                }
                final List c = ghb.c(b, FitWorkout[].class);
                BaseStateActivity baseStateActivity = this.b;
                if (baseStateActivity != null) {
                    baseStateActivity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.10
                        @Override // java.lang.Runnable
                        public void run() {
                            RecommendListFragment.this.c((List<FitWorkout>) c);
                        }
                    });
                    return;
                }
                return;
            }
        }
        j = 0;
        if (j != 0) {
        }
        this.t = true;
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String o() {
        return "allFitnessCourses" + LanguageUtil.e() + this.ad;
    }

    private void p() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.s = arguments.getInt("type");
            this.ad = arguments.getInt("index");
            this.p = arguments.getBoolean("isAddCourse", false);
            try {
                this.d = arguments.getStringArrayList("addCourseId");
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.b("Suggestion_RecoListFm", "get addCourseId list fail");
            }
            b();
        }
    }

    private View aAZ_(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.sug_trainevent_recofm, viewGroup, false);
        this.h = inflate.findViewById(R.id.sug_content);
        this.w = inflate.findViewById(R.id.no_record_layout);
        View findViewById = inflate.findViewById(R.id.sug_tv_jonied_deleMode);
        this.n = findViewById;
        findViewById.setOnClickListener(this);
        HealthCheckBox healthCheckBox = (HealthCheckBox) inflate.findViewById(R.id.sug_tv_jonied_all);
        this.ai = healthCheckBox;
        healthCheckBox.setOnClickListener(this);
        this.j = (LinearLayout) inflate.findViewById(R.id.plan_confirm_add_course);
        this.c = (HealthButton) inflate.findViewById(R.id.plan_confirm_add_course_btn);
        if (this.p) {
            this.j.setVisibility(0);
        }
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putStringArrayListExtra("courseIdList", RecommendListFragment.this.f);
                intent.putStringArrayListExtra("courseNameList", RecommendListFragment.this.i);
                RecommendListFragment.this.getActivity().setResult(-1, intent);
                RecommendListFragment.this.getActivity().finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ac = (HealthRecycleView) inflate.findViewById(R.id.sug_train_rcv_events);
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setSupportsChangeAnimations(false);
        this.ac.setItemAnimator(defaultItemAnimator);
        HealthLinearLayoutManager healthLinearLayoutManager = new HealthLinearLayoutManager(this.g, 1, false);
        this.v = healthLinearLayoutManager;
        ggs.b(this.g, this.ac, healthLinearLayoutManager);
        this.r = (HealthTextView) inflate.findViewById(R.id.sug_fitnes_nodata);
        this.l = (ImageView) inflate.findViewById(R.id.iv_sug_fitness_no_data);
        fpg.aCz_(e(), this.ai);
        this.al = fnz.e();
        r();
        this.ac.setAdapter(this.e);
        y();
        this.e.setOnMoreListener(new LoadMoreRecyclerViewAdapter.OnMoreListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.12
            @Override // com.huawei.ui.commonui.adapter.LoadMoreRecyclerViewAdapter.OnMoreListener
            public void onLoadingMore() {
                RecommendListFragment.this.q();
            }
        });
        return inflate;
    }

    private void r() {
        this.e = new LoadMoreRecyclerViewAdapter<FitWorkout>(new ArrayList(), this.ac, R.layout.sug_fitness_list_item) { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.14
            @Override // com.huawei.ui.commonui.adapter.LoadMoreRecyclerViewAdapter
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void convert(RecyclerHolder recyclerHolder, int i, FitWorkout fitWorkout) {
                LogUtil.a("Suggestion_RecoListFm", "convert, holder.itemView.getWidth() = ", Integer.valueOf(recyclerHolder.itemView.getWidth()));
                if (!Utils.o()) {
                    HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.tv_fe_name);
                    healthTextView.setMaxLines(1);
                    healthTextView.setSingleLine(true);
                    healthTextView.setEllipsize(TextUtils.TruncateAt.END);
                }
                SpannableString awT_ = ffy.awT_(BaseApplication.getContext(), "\\d+.\\d+|\\d+", ffy.b(R.plurals._2130903040_res_0x7f030000, fitWorkout.acquireUsers(), UnitUtil.e(fitWorkout.acquireUsers(), 1, 0)), R.style.sug_reco_train_num, R.style.sug_reco_train_desc);
                float b = moe.b(fitWorkout.acquireCalorie() * RecommendListFragment.this.al);
                recyclerHolder.c(R.id.tv_plan_peoples_num, awT_).b(R.id.tv_fe_name, StringUtils.c((Object) fitWorkout.acquireName())).b(R.id.tv_level, ggm.d(fitWorkout.acquireDifficulty())).b(R.id.tv_parameter_num, String.format(Locale.ROOT, RecommendListFragment.this.getString(R.string._2130837534_res_0x7f02001e), moe.k(fitWorkout.acquireDuration()))).b(R.id.tv_Kcal, ffy.b(R.plurals._2130903474_res_0x7f0301b2, (int) b, moe.i(b)));
                if (fitWorkout.isLongExplainVideoCourse()) {
                    RecommendListFragment.this.c(recyclerHolder);
                } else {
                    recyclerHolder.cwK_(R.id.tv_level).setVisibility(0);
                    recyclerHolder.cwK_(R.id.tv_Kcal).setVisibility(0);
                    RecommendListFragment recommendListFragment = RecommendListFragment.this;
                    recommendListFragment.a(recyclerHolder, recommendListFragment.getResources().getDimensionPixelSize(R.dimen._2131363575_res_0x7f0a06f7));
                }
                HealthCheckBox healthCheckBox = (HealthCheckBox) recyclerHolder.cwK_(R.id.plan_add_course_checkbox);
                healthCheckBox.setChecked(RecommendListFragment.this.f3129a.get(i, false));
                healthCheckBox.setClickable(false);
                RecommendListFragment.this.b(recyclerHolder, healthCheckBox, i, fitWorkout, getItemViewType(i));
                RecommendListFragment.this.c(recyclerHolder, fitWorkout, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(RecyclerHolder recyclerHolder) {
        if (recyclerHolder == null) {
            LogUtil.h("Suggestion_RecoListFm", "setLongExplainVideoCourse holder is null.");
            return;
        }
        recyclerHolder.cwK_(R.id.tv_level).setVisibility(8);
        recyclerHolder.cwK_(R.id.tv_Kcal).setVisibility(8);
        a(recyclerHolder, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(RecyclerHolder recyclerHolder, int i) {
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.tv_parameter_num);
        if (healthTextView.getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) healthTextView.getLayoutParams();
            layoutParams.setMarginStart(i);
            layoutParams.baselineToBaseline = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(RecyclerHolder recyclerHolder, final HealthCheckBox healthCheckBox, final int i, final FitWorkout fitWorkout, int i2) {
        FitnessCardView fitnessCardView = (FitnessCardView) recyclerHolder.cwK_(R.id.recycle_item);
        if (this.p) {
            healthCheckBox.setVisibility(0);
            fitnessCardView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecommendListFragment.this.a(i, fitWorkout, healthCheckBox);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        ImageView imageView = (ImageView) recyclerHolder.cwK_(R.id.sug_img_item_pic);
        String str = imageView.getTag() instanceof String ? (String) imageView.getTag() : null;
        if (TextUtils.isEmpty(str) || !str.equals(fitWorkout.acquirePicture())) {
            imageView.setTag(fitWorkout.acquirePicture());
            recyclerHolder.b(R.id.sug_img_item_pic, fitWorkout.acquirePicture(), nrf.d);
        }
        if (i2 != 1 || recyclerHolder.cwK_(R.id.sug_view_space) == null) {
            return;
        }
        if (i == 0) {
            recyclerHolder.cwK_(R.id.sug_view_space).setVisibility(0);
        } else if (nsn.ag(this.g) && i == 1) {
            recyclerHolder.cwK_(R.id.sug_view_space).setVisibility(0);
        } else {
            recyclerHolder.cwK_(R.id.sug_view_space).setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(RecyclerHolder recyclerHolder, FitWorkout fitWorkout, int i) {
        ViewGroup.LayoutParams layoutParams = recyclerHolder.itemView.findViewById(R.id.new_imageView).getLayoutParams();
        layoutParams.height = -2;
        a(recyclerHolder, fitWorkout);
        List<PriceTagBean> acquirePriceTagBeanList = fitWorkout.acquirePriceTagBeanList();
        if (fitWorkout.getCornerImgDisplay() == 1) {
            String b = mod.b(acquirePriceTagBeanList);
            if (!TextUtils.isEmpty(b)) {
                layoutParams.height = (int) getContext().getResources().getDimension(R.dimen._2131362959_res_0x7f0a048f);
                recyclerHolder.a(R.id.new_imageView, 0);
                nrf.a(b, (HealthImageView) recyclerHolder.itemView.findViewById(R.id.new_imageView), 0.0f, nrf.d, 0.0f, 0.0f);
            }
        }
        recyclerHolder.itemView.findViewById(R.id.new_imageView).setLayoutParams(layoutParams);
        if (Utils.j()) {
            recyclerHolder.a(R.id.tv_plan_peoples_num, 0);
        } else {
            recyclerHolder.a(R.id.tv_plan_peoples_num, 8);
        }
        if (nsn.s()) {
            recyclerHolder.a(R.id.tv_plan_peoples_num, 8);
        }
        if (this.s == 1) {
            d(recyclerHolder, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, FitWorkout fitWorkout, HealthCheckBox healthCheckBox) {
        if (healthCheckBox.isChecked()) {
            if (this.f.contains(fitWorkout.acquireId())) {
                this.f.remove(fitWorkout.acquireId());
                this.i.remove(fitWorkout.acquireName());
            }
            healthCheckBox.setChecked(false);
            this.f3129a.put(i, false);
            LogUtil.a("Suggestion_RecoListFm", "set workout ", fitWorkout.acquireId(), " pos ", Integer.valueOf(i), "  false");
            return;
        }
        if (!this.f.contains(fitWorkout.acquireId())) {
            ArrayList<String> arrayList = this.d;
            if (arrayList != null && arrayList.contains(fitWorkout.acquireId())) {
                nrh.b(getContext(), R.string._2130848701_res_0x7f022bbd);
                return;
            } else {
                this.f.add(fitWorkout.acquireId());
                this.i.add(fitWorkout.acquireName());
            }
        }
        healthCheckBox.setChecked(true);
        this.f3129a.put(i, true);
        LogUtil.a("Suggestion_RecoListFm", "set workout ", fitWorkout.acquireId(), " pos ", Integer.valueOf(i), "  true");
    }

    private void a(RecyclerHolder recyclerHolder, FitWorkout fitWorkout) {
        if (LanguageUtil.j(BaseApplication.getContext()) || LanguageUtil.p(BaseApplication.getContext())) {
            if (fitWorkout.acquireStage() == 0 && fitWorkout.acquireIsSupportDevice() == 0) {
                recyclerHolder.d(R.id.new_imageView, R.drawable.pic_corner_new_watchwear);
                recyclerHolder.a(R.id.new_imageView, 0);
                return;
            } else if (fitWorkout.acquireStage() == 0) {
                recyclerHolder.d(R.id.new_imageView, R.drawable._2131430906_res_0x7f0b0dfa);
                recyclerHolder.a(R.id.new_imageView, 0);
                return;
            } else if (fitWorkout.acquireIsSupportDevice() == 0) {
                recyclerHolder.d(R.id.new_imageView, R.drawable.pic_corner_watchwear);
                recyclerHolder.a(R.id.new_imageView, 0);
                return;
            } else {
                recyclerHolder.a(R.id.new_imageView, 8);
                return;
            }
        }
        recyclerHolder.a(R.id.new_imageView, 8);
    }

    private void y() {
        this.e.setOnItemClickListener(new LoadMoreRecyclerViewAdapter.OnItemClickListener<FitWorkout>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.11
            @Override // com.huawei.ui.commonui.adapter.LoadMoreRecyclerViewAdapter.OnItemClickListener
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onItemClicked(RecyclerHolder recyclerHolder, int i, FitWorkout fitWorkout) {
                if (!RecommendListFragment.this.q) {
                    mmp mmpVar = new mmp(fitWorkout.acquireId());
                    mmpVar.d("AllTraining");
                    mod.b(arx.b(), fitWorkout, mmpVar);
                    return;
                }
                recyclerHolder.cwK_(R.id.sug_rv_checkbox).performClick();
            }
        });
    }

    private void u() {
        this.e.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.15
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                super.onChanged();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int i, int i2) {
                super.onItemRangeChanged(i, i2);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int i, int i2, Object obj) {
                super.onItemRangeChanged(i, i2, obj);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeInserted(int i, int i2) {
                super.onItemRangeInserted(i, i2);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeRemoved(int i, int i2) {
                RecommendListFragment.this.e.remove(i);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeMoved(int i, int i2, int i3) {
                super.onItemRangeMoved(i, i2, i3);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a("Suggestion_RecoListFm", "onConfigurationChanged, FitnessCommonUtil.setRecyclerViewLayout");
        ggs.b(this.g, this.ac, this.v);
    }

    private void d(RecyclerHolder recyclerHolder, int i) {
        if (this.q) {
            fpg.a(this.ah, recyclerHolder, i);
        } else {
            recyclerHolder.a(R.id.sug_rv_checkbox, 8);
        }
        recyclerHolder.cwK_(R.id.sug_rv_checkbox).setTag(Integer.valueOf(i));
        recyclerHolder.cwP_(R.id.sug_rv_checkbox, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        LogUtil.a("Suggestion_RecoListFm", "up to loadMore currpage:", Integer.valueOf(this.m), "mPageFlag:", Integer.valueOf(this.ab), "--->dataSize: ", Integer.valueOf(this.e.size()));
        int i = this.m;
        if (i <= 0 || this.ab + 1 != i) {
            return;
        }
        this.ab = i;
        d();
    }

    public void d() {
        if (this.e == null) {
            return;
        }
        int i = this.s;
        if (i == 1) {
            m();
        } else if (i == 2) {
            s();
        } else {
            n();
        }
    }

    private void n() {
        l();
    }

    private void s() {
        LogUtil.a("Suggestion_RecoListFm", "Suggestion_RecoListFm", "----getRecommendWorks()--");
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_RecoListFm", "getRecommendWorks : courseApi is null.");
        } else {
            courseApi.getRecommendCourses("FITNESS_COURSE", new WorkoutListBean(this.m * 10, 10, this.y, this.am, this.ak, this.k, this.o, 0), new UiPagingCallback<List<Workout>>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.18
                @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
                public void onFailureFirst(int i, String str) {
                    LogUtil.h("Suggestion_RecoListFm", str, "==Failed--errorcode:", Integer.valueOf(i));
                    RecommendListFragment.this.k();
                }

                @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccessFirst(List<Workout> list) {
                    final List<FitWorkout> d = ggs.d(mod.a(list), "FITNESS_COURSE");
                    int e = gic.e((Object) ash.b("total"));
                    if (koq.c(d) && RecommendListFragment.this.s == 2 && e > 0) {
                        ArrayList<FitWorkout> b = fot.a().d(d).b(d);
                        RecommendListFragment.this.z = e;
                        d = ffy.d(b, RecommendListFragment.this.m * 10, 10, RecommendListFragment.this.z);
                    }
                    if (RecommendListFragment.this.b != null) {
                        RecommendListFragment.this.b.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.18.4
                            @Override // java.lang.Runnable
                            public void run() {
                                RecommendListFragment.this.c((List<FitWorkout>) d);
                            }
                        });
                    }
                }
            });
        }
    }

    private void l() {
        this.w.setVisibility(8);
        LogUtil.a("Suggestion_RecoListFm", "Suggestion_RecoListFm", "----getRecommendAllWorks()--");
        WorkoutListBean workoutListBean = new WorkoutListBean();
        e(workoutListBean);
        workoutListBean.setWorkoutType(new Integer[]{1});
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_RecoListFm", "getRecommendFilterWorks : courseApi is null.");
            return;
        }
        Integer primaryClassifyId = workoutListBean.getPrimaryClassifyId();
        this.x = primaryClassifyId;
        courseApi.d(workoutListBean, b(primaryClassifyId));
    }

    private UiPagingCallback<List<Workout>> b(final Integer num) {
        return new UiPagingCallback<List<Workout>>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccessFirst(List<Workout> list) {
                final List<FitWorkout> a2 = mod.a(list);
                int e = gic.e((Object) ash.b("total"));
                if (koq.c(a2) && RecommendListFragment.this.s == 2 && e > 0) {
                    ArrayList<FitWorkout> b = fot.a().d(a2).b(a2);
                    RecommendListFragment.this.z = e;
                    a2 = ffy.d(b, RecommendListFragment.this.m * 10, 10, RecommendListFragment.this.z);
                }
                if (RecommendListFragment.this.t) {
                    RecommendListFragment.this.t = false;
                    if (koq.c(a2)) {
                        ash.a(RecommendListFragment.this.o(), ghb.e(a2) + "_" + System.currentTimeMillis());
                    }
                }
                LogUtil.a("Suggestion_RecoListFm", "getCallback, value is ", num, ", mHistoryPrimaryIndex is ", RecommendListFragment.this.x);
                Integer num2 = num;
                if (num2 == null || num2.equals(RecommendListFragment.this.x)) {
                    if (RecommendListFragment.this.b != null) {
                        RecommendListFragment.this.b.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.4.4
                            @Override // java.lang.Runnable
                            public void run() {
                                RecommendListFragment.this.c((List<FitWorkout>) a2);
                            }
                        });
                        return;
                    }
                    return;
                }
                LogUtil.h("Suggestion_RecoListFm", "getCallback, value is not mHistoryPrimaryIndex");
            }

            @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
            public void onFailureFirst(int i, String str) {
                LogUtil.h("Suggestion_RecoListFm", str, "==Failed-- errorcode:", Integer.valueOf(i));
                RecommendListFragment.this.k();
            }
        };
    }

    private void e(WorkoutListBean workoutListBean) {
        workoutListBean.setPageStart(this.m * 10);
        workoutListBean.setPageSize(10);
        workoutListBean.setSupportWear(this.y);
        int i = this.ad;
        if (i != 0) {
            workoutListBean.setPrimaryClassifyId(Integer.valueOf(i));
        }
        Integer[] numArr = this.u;
        if (numArr != null && numArr.length > 0) {
            workoutListBean.setDifficulty(numArr);
        }
        Integer[] numArr2 = this.af;
        if (numArr2 != null && numArr2.length > 0) {
            workoutListBean.setSecondClassifyList(numArr2);
        }
        Integer[] numArr3 = this.ak;
        if (numArr3 != null && numArr3.length > 0) {
            workoutListBean.setTrainingPoints(numArr3);
        }
        Integer[] numArr4 = this.o;
        if (numArr4 != null && numArr4.length > 0) {
            workoutListBean.setEquipments(numArr4);
        }
        workoutListBean.setWorkoutRank(Integer.valueOf(this.ag));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<FitWorkout> list) {
        if (this.m == 0) {
            this.e.clear();
        }
        if (koq.c(list)) {
            LogUtil.a("Suggestion_RecoListFm", "getData Size: ", Integer.valueOf(list.size()));
            a(list);
        } else {
            this.e.noMoreLoad();
            this.ai.setEnabled(true);
        }
        w();
        h();
    }

    private void h() {
        BaseStateActivity baseStateActivity = this.b;
        if (baseStateActivity != null) {
            if (((Boolean) baseStateActivity.mLoadingView.getTag()).booleanValue()) {
                this.b.finishLoading();
            } else {
                this.b.mLoadingView.setTag(true);
            }
        }
    }

    private void m() {
        LogUtil.a("Suggestion_RecoListFm", "Suggestion_RecoListFm", "----getJoinedWorks()--");
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_RecoListFm", "getJoinedWorks : courseApi is null.");
        } else {
            courseApi.getJoinedCourses(new WorkoutListBean(0, Integer.MAX_VALUE, this.am, this.k, this.ak, this.y, this.o, false), new AnonymousClass5());
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment$5, reason: invalid class name */
    class AnonymousClass5 extends UiPagingCallback<List<Workout>> {
        AnonymousClass5() {
        }

        @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
        public void onFailureFirst(int i, String str) {
            LogUtil.h("Suggestion_RecoListFm", str, "==Failed--errorcode:", Integer.valueOf(i));
            RecommendListFragment.this.k();
        }

        @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccessFirst(List<Workout> list) {
            final List<FitWorkout> d = ggs.d(mod.a(list), "FITNESS_COURSE");
            asc.e().b(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.5.1
                @Override // java.lang.Runnable
                public void run() {
                    final List<FitWorkout> c = fot.a().c(fot.a().d(d).b(d));
                    if (RecommendListFragment.this.b != null) {
                        RecommendListFragment.this.b.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.5.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                RecommendListFragment.this.c((List<FitWorkout>) c);
                            }
                        });
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.m == 0) {
            this.e.clear();
        }
        h();
        if (this.e.size() == 0) {
            this.h.setVisibility(8);
            x();
        } else {
            this.e.loadError();
        }
    }

    private void x() {
        this.w.setVisibility(0);
        this.r.setText(R.string._2130840733_res_0x7f020c9d);
        if (LanguageUtil.bc(this.g)) {
            this.l.setImageDrawable(nrz.cKn_(this.g, R.drawable._2131430308_res_0x7f0b0ba4));
        } else {
            this.l.setImageResource(R.drawable._2131430308_res_0x7f0b0ba4);
        }
    }

    private void z() {
        this.w.setVisibility(0);
        this.r.setText(R.string._2130848453_res_0x7f022ac5);
        if (LanguageUtil.bc(this.g)) {
            this.l.setImageDrawable(nrz.cKn_(this.g, R.drawable._2131429871_res_0x7f0b09ef));
        } else {
            this.l.setImageResource(R.drawable._2131429871_res_0x7f0b09ef);
        }
    }

    private void a(List<FitWorkout> list) {
        this.m++;
        if (this.s == 1 || e(list)) {
            this.e.noMoreLoad(list);
            this.ai.setEnabled(true);
        } else {
            this.e.enableMoreLoad(list);
            this.ai.setEnabled(true);
        }
    }

    private boolean e(List<FitWorkout> list) {
        return this.s == 2 && list.size() >= this.z;
    }

    private void w() {
        if (this.e.size() <= 0) {
            this.h.setVisibility(8);
            z();
            this.ah.clear();
            this.q = false;
            return;
        }
        this.h.setVisibility(0);
        this.w.setVisibility(8);
        fpg.aCz_(e(), this.ai);
    }

    private void b() {
        if (this.p) {
            if (this.aa.get(Integer.valueOf(this.ad)) == null) {
                SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
                this.aa.put(Integer.valueOf(this.ad), sparseBooleanArray);
                this.f3129a = sparseBooleanArray;
                return;
            }
            this.f3129a = this.aa.get(Integer.valueOf(this.ad));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.sug_tv_jonied_deleMode) {
            i();
        } else if (R.id.sug_rv_checkbox == view.getId()) {
            aAY_(view);
        } else if (R.id.sug_tv_jonied_all == view.getId()) {
            j();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void j() {
        if (this.ai.isChecked()) {
            a();
        } else {
            this.ai.setText(R$string.IDS_contact_delete_select_all);
            this.ah.clear();
        }
        this.e.notifyDataSetChanged();
    }

    private void a() {
        this.ai.setText(R$string.IDS_contact_delete_uncheck_all);
        if (this.e.isCanLoadMore()) {
            fpg.c(this.ah, this.v.findLastVisibleItemPosition() + 1);
        } else {
            fpg.c(this.ah, this.e.size());
        }
    }

    private void aAY_(View view) {
        if (view.getTag() instanceof Integer) {
            Integer num = (Integer) view.getTag();
            if (view instanceof HealthCheckBox) {
                if (((HealthCheckBox) view).isChecked()) {
                    this.ah.add(num);
                } else if (this.ah.contains(num)) {
                    this.ah.remove(num);
                }
            }
        }
    }

    private void i() {
        if (this.ah.isEmpty()) {
            boolean z = !this.q;
            this.q = z;
            if (z) {
                this.ai.setVisibility(0);
            } else {
                this.ai.setVisibility(8);
            }
            this.e.notifyDataSetChanged();
            return;
        }
        v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.ai.setChecked(false);
        this.ai.setText(R$string.IDS_contact_delete_select_all);
        if (this.ah.size() > 0) {
            this.ai.postDelayed(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.2
                @Override // java.lang.Runnable
                public void run() {
                    RecommendListFragment.this.g();
                }
            }, 20L);
        } else {
            this.ai.setVisibility(8);
        }
        this.q = false;
        this.e.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        Collections.sort(this.ah, new Comparator<Integer>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.1
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(Integer num, Integer num2) {
                return num2.intValue() - num.intValue();
            }
        });
        Iterator<Integer> it = this.ah.iterator();
        while (it.hasNext()) {
            this.e.notifyItemRemoved(it.next().intValue());
        }
        this.ah.clear();
        this.ai.setVisibility(8);
        if (c()) {
            if (this.ai.getParent().getParent() instanceof View) {
                ((View) this.ai.getParent().getParent()).setVisibility(8);
            }
            this.h.setVisibility(8);
            z();
        }
    }

    private boolean c() {
        return this.e.size() == 0 && !this.e.isCanLoadMore();
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && ffy.c(this.e)) {
            fpg.aCz_(e(), this.ai);
        }
        FragmentInstrumentation.setUserVisibleHintByFragment(this, z);
    }

    private boolean e() {
        return this.s == 1 && ffy.c(this.e) && this.e.size() > 0;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.s == 1) {
            this.m = 0;
            this.ab = 0;
            this.ac.scrollToPosition(0);
            asc.e().b(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.8
                @Override // java.lang.Runnable
                public void run() {
                    RecommendListFragment.this.d();
                }
            });
        }
        FragmentInstrumentation.onResumeByFragment(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.s == 1) {
            fot.a().c();
        }
    }

    private void v() {
        LogUtil.c("Suggestion_RecoListFm", "showDeleteDialog ", "enter");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(getContext());
        builder.e(getString(R.string._2130848456_res_0x7f022ac8)).czE_(getString(R.string._2130848357_res_0x7f022a65).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("Suggestion_RecoListFm", "it is positive");
                RecommendListFragment.this.ae.clear();
                Iterator it = RecommendListFragment.this.ah.iterator();
                while (it.hasNext()) {
                    Object obj = RecommendListFragment.this.e.get(((Integer) it.next()).intValue());
                    if (obj instanceof FitWorkout) {
                        RecommendListFragment.this.ae.add((FitWorkout) obj);
                    }
                }
                LogUtil.a("Suggestion_RecoListFm", "delete fitworkout size:", Integer.valueOf(RecommendListFragment.this.ah.size()));
                CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
                if (courseApi != null) {
                    courseApi.deleteUserJoinedCourses(mod.j(RecommendListFragment.this.ae), new UiPagingCallback<String>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.7.1
                        @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
                        public void onFailureFirst(int i, String str) {
                            LogUtil.b("Suggestion_RecoListFm", "delete workout error ", str, "--errorcode:", Integer.valueOf(i));
                            Toast.makeText(RecommendListFragment.this.getContext(), str, 1).show();
                        }

                        @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
                        /* renamed from: b, reason: merged with bridge method [inline-methods] */
                        public void onSuccessFirst(String str) {
                            RecommendListFragment.this.f();
                            fot.a().e(RecommendListFragment.this.ae);
                        }
                    });
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.h("Suggestion_RecoListFm", "showDeleteDialog : courseApi is null.");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        }).czA_(getString(com.huawei.health.servicesui.R$string.IDS_plugin_fitnessadvice_cancal), new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.RecommendListFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("Suggestion_RecoListFm", "it is negative");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentInstrumentation.onViewCreatedByFragment(this, view, bundle);
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
