package com.huawei.health.suggestion.ui.fitness.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.WorkoutListBean;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessTopicActivity;
import com.huawei.health.suggestion.ui.fitness.adapter.FitnessTopicRecyAdapter;
import com.huawei.health.suggestion.ui.fitness.module.FitnessTopicDeleteModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.arx;
import defpackage.asc;
import defpackage.fot;
import defpackage.ggs;
import defpackage.koq;
import defpackage.mod;
import defpackage.nqx;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes7.dex */
public class FitnessTopicFragment extends BaseFragment implements View.OnClickListener {
    private HealthCheckBox b;
    private HealthButton c;
    private Bundle d;
    private CustomTitleBar e;
    private FitnessTopicRecyAdapter f;
    private LinearLayout g;
    private Intent i;
    private LinearLayout k;
    private View m;
    private int n;
    private HealthTextView o;
    private LinearLayout p;
    private RelativeLayout q;
    private HealthTextView s;
    private HealthRecycleView t;
    private int u;
    private String x;
    private int y;
    private boolean j = true;
    private List<Integer> r = new ArrayList(10);
    private boolean h = false;

    /* renamed from: a, reason: collision with root package name */
    private FitnessTopicDeleteModel f3122a = new FitnessTopicDeleteModel();
    private List<FitWorkout> w = new ArrayList(10);
    private Handler l = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessTopicFragment.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.b("Suggestion_FitnessTopicFragment", "handleMessage(), message == null");
                return;
            }
            super.handleMessage(message);
            if (message.what != 1) {
                return;
            }
            FitnessTopicFragment.this.h();
        }
    };

    static /* synthetic */ int l(FitnessTopicFragment fitnessTopicFragment) {
        int i = fitnessTopicFragment.n;
        fitnessTopicFragment.n = i + 1;
        return i;
    }

    public static FitnessTopicFragment b(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        FitnessTopicFragment fitnessTopicFragment = new FitnessTopicFragment();
        fitnessTopicFragment.setArguments(bundle);
        return fitnessTopicFragment;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.y = arguments.getInt("type");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sug_fragment_fit_topic, viewGroup, false);
        aAT_(inflate);
        return inflate;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_no_net_work) {
            LogUtil.a("Suggestion_FitnessTopicFragment", "view == mButtonNoNet");
            CommonUtil.q(getContext());
        } else if (view.getId() == R.id.reload_layout) {
            if (this.j) {
                this.j = false;
                c();
            }
        } else if (view.getId() == R.id.sug_tv_jonied_deleMode) {
            LogUtil.a("Suggestion_FitnessTopicFragment", "view == R.id.sug_tv_jonied_deleMode");
            a();
        } else if (view.getId() == R.id.sug_tv_jonied_all) {
            LogUtil.a("Suggestion_FitnessTopicFragment", "view == R.id.sug_tv_jonied_all");
            e();
        } else {
            LogUtil.a("Suggestion_FitnessTopicFragment", "invalid branch");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment
    public void initViewTahiti() {
        HealthRecycleView healthRecycleView = this.t;
        if (healthRecycleView == null || this.f == null) {
            return;
        }
        healthRecycleView.setAdapter(null);
        this.t.setLayoutManager(null);
        this.t.setAdapter(this.f);
        ggs.a(getContext(), this.t);
        this.f.notifyDataSetChanged();
    }

    public HealthRecycleView d() {
        return this.t;
    }

    private void a() {
        if (this.r.isEmpty()) {
            boolean z = !this.h;
            this.h = z;
            if (z) {
                this.b.setVisibility(0);
            } else {
                this.b.setVisibility(8);
            }
            this.f3122a.saveIssDeleteMode(this.h);
            this.f.d(this.f3122a, true);
            return;
        }
        m();
    }

    private void e() {
        if (this.b.isChecked()) {
            this.b.setText(R$string.IDS_contact_delete_uncheck_all);
            for (int i = 0; i < this.f.a().size(); i++) {
                if (!this.r.contains(Integer.valueOf(i))) {
                    this.r.add(Integer.valueOf(i));
                }
            }
        } else {
            this.b.setText(R$string.IDS_contact_delete_select_all);
            this.r.clear();
        }
        this.f3122a.saveSelects(this.r);
        this.f.d(this.f3122a, true);
    }

    private void m() {
        LogUtil.c("Suggestion_FitnessTopicFragment", "showDeleteDialog ", "enter");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(getContext());
        builder.e(getString(R.string._2130848456_res_0x7f022ac8)).czE_(getString(R.string._2130848357_res_0x7f022a65).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessTopicFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("Suggestion_FitnessTopicFragment", "it is positive");
                LogUtil.a("Suggestion_FitnessTopicFragment", "mSelectedList:", FitnessTopicFragment.this.r);
                List<FitWorkout> a2 = FitnessTopicFragment.this.f.a();
                FitnessTopicFragment.this.w.clear();
                Iterator it = FitnessTopicFragment.this.r.iterator();
                while (it.hasNext()) {
                    FitnessTopicFragment.this.w.add(a2.get(((Integer) it.next()).intValue()));
                }
                LogUtil.a("Suggestion_FitnessTopicFragment", "delete fitworkout size:", Integer.valueOf(FitnessTopicFragment.this.r.size()));
                CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
                if (courseApi != null) {
                    courseApi.deleteUserJoinedCourses(mod.j(FitnessTopicFragment.this.w), new UiCallback<String>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessTopicFragment.7.4
                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        public void onFailure(int i, String str) {
                            LogUtil.b("Suggestion_FitnessTopicFragment", "delete workout error ", str, "--errorcode:", Integer.valueOf(i));
                            Toast.makeText(arx.b(), str, 1).show();
                        }

                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        /* renamed from: e, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(String str) {
                            FitnessTopicFragment.this.i();
                            fot.a().e(FitnessTopicFragment.this.w);
                        }
                    });
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.h("Suggestion_FitnessTopicFragment", "showDeleteDialog : courseApi is null.");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        }).czA_(getString(com.huawei.health.servicesui.R$string.IDS_plugin_fitnessadvice_cancal), new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessTopicFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("Suggestion_FitnessTopicFragment", "it is negative");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.b.setChecked(false);
        this.b.setText(R$string.IDS_contact_delete_select_all);
        LogUtil.a("Suggestion_FitnessTopicFragment", "mDeleteModel.acquireSelects().size():", Integer.valueOf(this.f3122a.acquireSelects().size()));
        if (this.f3122a.acquireSelects().size() > 0) {
            this.b.postDelayed(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessTopicFragment.6
                @Override // java.lang.Runnable
                public void run() {
                    FitnessTopicFragment.this.b();
                }
            }, 20L);
        } else {
            this.b.setVisibility(8);
        }
        this.h = false;
        this.f3122a.saveIssDeleteMode(false);
        this.f.d(this.f3122a, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Collections.sort(this.r, new Comparator<Integer>() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessTopicFragment.10
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(Integer num, Integer num2) {
                return num2.intValue() - num.intValue();
            }
        });
        Iterator<Integer> it = this.r.iterator();
        while (it.hasNext()) {
            this.f.notifyItemRemoved(it.next().intValue());
        }
        this.r.clear();
        this.b.setVisibility(8);
        if ((this.f.a() == null || this.f.a().size() <= 0) && (this.b.getParent() instanceof View)) {
            ((View) this.b.getParent()).setVisibility(8);
            this.k.setVisibility(0);
            this.p.setVisibility(4);
        }
    }

    private void aAT_(View view) {
        this.m = view.findViewById(R.id.sug_loading_layout);
        this.t = (HealthRecycleView) view.findViewById(R.id.recyclerView_topic);
        this.p = (LinearLayout) view.findViewById(R.id.sug_layout_net_error);
        this.k = (LinearLayout) view.findViewById(R.id.sug_reco_workoutlist_nodata);
        this.c = (HealthButton) view.findViewById(R.id.btn_no_net_work);
        this.q = (RelativeLayout) view.findViewById(R.id.reload_layout);
        this.g = (LinearLayout) view.findViewById(R.id.sug_jonied_selectall);
        this.s = (HealthTextView) view.findViewById(R.id.sug_tv_jonied_deleMode);
        this.b = (HealthCheckBox) view.findViewById(R.id.sug_tv_jonied_all);
        this.o = (HealthTextView) view.findViewById(R.id.sug_fitnes_nodata);
        this.s.setOnClickListener(this);
        this.b.setOnClickListener(this);
        Intent intent = getActivity() == null ? null : getActivity().getIntent();
        this.i = intent;
        if (this.y != 8) {
            this.f = new FitnessTopicRecyAdapter(this.t);
        } else if (intent != null) {
            this.d = intent.getBundleExtra("bundlekey");
            this.f = new FitnessTopicRecyAdapter(this.t, this.y, this.d);
        }
        this.f.d(new FitnessTopicRecyAdapter.LoadMoreListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessTopicFragment.8
            @Override // com.huawei.health.suggestion.ui.fitness.adapter.FitnessTopicRecyAdapter.LoadMoreListener
            public void loadMore() {
                LogUtil.a("Suggestion_FitnessTopicFragment", "loadMore(), load more data");
                if (FitnessTopicFragment.this.u != -1) {
                    FitnessTopicFragment.this.f();
                }
            }
        });
        this.f.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessTopicFragment.9
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeRemoved(int i, int i2) {
                FitnessTopicFragment.this.f.a().remove(i);
            }
        });
        ggs.a(getContext(), this.t);
        this.t.setNestedScrollingEnabled(false);
        this.t.setAdapter(this.f);
        this.c.setOnClickListener(this);
        this.q.setOnClickListener(this);
        asc.e().b(new e(this));
    }

    static class e implements Runnable {
        private WeakReference<FitnessTopicFragment> d;

        e(FitnessTopicFragment fitnessTopicFragment) {
            this.d = new WeakReference<>(fitnessTopicFragment);
        }

        @Override // java.lang.Runnable
        public void run() {
            WeakReference<FitnessTopicFragment> weakReference = this.d;
            if (weakReference == null) {
                LogUtil.b("Suggestion_FitnessTopicFragment", "GetDataRunnable mWeakReference is null.");
                return;
            }
            FitnessTopicFragment fitnessTopicFragment = weakReference.get();
            if (fitnessTopicFragment == null) {
                LogUtil.b("Suggestion_FitnessTopicFragment", "GetDataRunnable fragment is null.");
            } else {
                fitnessTopicFragment.c();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("Suggestion_FitnessTopicFragment", "handleInitCustomTitleBar()");
        if (!(getActivity() instanceof FitnessTopicActivity)) {
            LogUtil.b("Suggestion_FitnessTopicFragment", "activity is null.or activity not FitnessTopicActivity.");
            return;
        }
        final FitnessTopicActivity fitnessTopicActivity = (FitnessTopicActivity) getActivity();
        CustomTitleBar a2 = fitnessTopicActivity.a();
        this.e = a2;
        a2.setDoubleClickEnable(true);
        this.e.setBackToTopListener(new CustomTitleBar.BackToTopListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessTopicFragment.12
            @Override // com.huawei.ui.commonui.titlebar.CustomTitleBar.BackToTopListener
            public void backToTop() {
                HealthRecycleView d2;
                LogUtil.a("Suggestion_FitnessTopicFragment", "double clicked, backToTop()");
                HealthViewPager e2 = fitnessTopicActivity.e();
                if (e2.getAdapter() instanceof nqx) {
                    nqx nqxVar = (nqx) e2.getAdapter();
                    if (!(nqxVar.getItem(e2.getCurrentItem()) instanceof FitnessTopicFragment) || (d2 = ((FitnessTopicFragment) nqxVar.getItem(e2.getCurrentItem())).d()) == null) {
                        return;
                    }
                    d2.smoothScrollToPosition(0);
                }
            }
        });
    }

    public void c() {
        if (this.i == null) {
            return;
        }
        n();
        if (this.u == -1) {
            a(false);
            d dVar = new d(this);
            CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            if (courseApi == null) {
                LogUtil.h("Suggestion_FitnessTopicFragment", "getData : courseApi is null.");
                return;
            } else {
                courseApi.getJoinedCourses(new WorkoutListBean(0, Integer.MAX_VALUE, (Integer[]) null, (Integer[]) null, (Integer[]) null, -1, (Integer[]) null, false), dVar);
                return;
            }
        }
        f();
    }

    static class d extends UiCallback<List<Workout>> {
        private WeakReference<FitnessTopicFragment> e;

        d(FitnessTopicFragment fitnessTopicFragment) {
            this.e = new WeakReference<>(fitnessTopicFragment);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Suggestion_FitnessTopicFragment", "GetDataUiCallback ", str, " == Failed--errorCode: ", Integer.valueOf(i));
            WeakReference<FitnessTopicFragment> weakReference = this.e;
            if (weakReference == null) {
                LogUtil.b("Suggestion_FitnessTopicFragment", "GetDataUiCallback onFailure mWeakReference is null.");
                return;
            }
            FitnessTopicFragment fitnessTopicFragment = weakReference.get();
            if (fitnessTopicFragment != null) {
                if (!fitnessTopicFragment.k()) {
                    fitnessTopicFragment.j = true;
                    fitnessTopicFragment.j();
                    return;
                } else {
                    LogUtil.h("Suggestion_FitnessTopicFragment", "GetDataUiCallback onFailure isActivityDestroyed");
                    return;
                }
            }
            LogUtil.b("Suggestion_FitnessTopicFragment", "GetDataUiCallback onFailure fitnessTopicFragment is null.");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<Workout> list) {
            LogUtil.a("Suggestion_FitnessTopicFragment", "GetDataUiCallback onSuccess!");
            WeakReference<FitnessTopicFragment> weakReference = this.e;
            if (weakReference == null) {
                LogUtil.b("Suggestion_FitnessTopicFragment", "GetDataUiCallback onSuccess mWeakReference is null.");
                return;
            }
            FitnessTopicFragment fitnessTopicFragment = weakReference.get();
            if (fitnessTopicFragment != null) {
                if (!fitnessTopicFragment.k()) {
                    fitnessTopicFragment.j = true;
                    fitnessTopicFragment.j();
                    if (koq.b(list)) {
                        fitnessTopicFragment.g.setVisibility(8);
                        fitnessTopicFragment.p.setVisibility(8);
                        fitnessTopicFragment.k.setVisibility(0);
                        return;
                    }
                    fitnessTopicFragment.g.setVisibility(0);
                    fitnessTopicFragment.p.setVisibility(8);
                    fitnessTopicFragment.k.setVisibility(8);
                    fitnessTopicFragment.f3122a.saveIssDeleteMode(fitnessTopicFragment.h);
                    fitnessTopicFragment.f3122a.saveSelects(fitnessTopicFragment.r);
                    fitnessTopicFragment.f.d(fitnessTopicFragment.f3122a, true, fot.a().c(mod.a(list)));
                    return;
                }
                LogUtil.h("Suggestion_FitnessTopicFragment", "GetDataUiCallback onSuccess isActivityDestroyed");
                return;
            }
            LogUtil.b("Suggestion_FitnessTopicFragment", "GetDataUiCallback onSuccess fitnessTopicFragment is null.");
        }
    }

    private void a(final boolean z) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessTopicFragment.11
                @Override // java.lang.Runnable
                public void run() {
                    FitnessTopicFragment.this.f.b(z);
                }
            });
        }
    }

    private void n() {
        this.u = this.i.getIntExtra("intent_key_topicid", -1);
        if (this.i.getStringExtra("intent_key_topicname") != null) {
            this.x = this.i.getStringExtra("intent_key_topicname");
        }
        LogUtil.a("Suggestion_FitnessTopicFragment", "mTopicId:", Integer.valueOf(this.u), "-mTopicName:", this.x);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessTopicFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    if (FitnessTopicFragment.this.u == -1) {
                        FitnessTopicFragment.this.o.setText(R.string._2130848454_res_0x7f022ac6);
                        FitnessTopicFragment.this.f.c(arx.b().getString(R.string._2130848460_res_0x7f022acc));
                    } else {
                        FitnessTopicFragment.this.f.c(FitnessTopicFragment.this.x);
                    }
                    FitnessTopicFragment.this.k.setVisibility(4);
                    FitnessTopicFragment.this.p.setVisibility(4);
                    FitnessTopicFragment.this.l();
                }
            });
        }
    }

    private void d(int i) {
        this.l.sendMessage(Message.obtain(this.l, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        d(1);
        switch (this.y) {
            case 0:
                g();
                break;
            case 1:
                g();
                break;
            case 2:
                a(5);
                break;
            case 3:
                a(10);
                break;
            case 4:
                a(15);
                break;
            case 5:
                a(20);
                break;
            case 6:
                a(25);
                break;
            case 7:
                a(3);
                break;
            case 8:
                g();
                break;
        }
    }

    private void g() {
        a aVar = new a(this);
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_FitnessTopicFragment", "getWorkoutsByTopicIdAndRefresh : courseApi is null.");
        } else {
            courseApi.getCoursesByTopicId(this.n, this.u, aVar);
        }
    }

    static class a extends UiCallback<List<Workout>> {
        private WeakReference<FitnessTopicFragment> e;

        a(FitnessTopicFragment fitnessTopicFragment) {
            this.e = new WeakReference<>(fitnessTopicFragment);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            WeakReference<FitnessTopicFragment> weakReference = this.e;
            if (weakReference == null) {
                LogUtil.b("Suggestion_FitnessTopicFragment", "GetWorkoutsAndRefreshUiCallback onFailure mWeakReference is null.");
                return;
            }
            FitnessTopicFragment fitnessTopicFragment = weakReference.get();
            if (fitnessTopicFragment != null) {
                if (!fitnessTopicFragment.k()) {
                    fitnessTopicFragment.j = true;
                    LogUtil.b("Suggestion_FitnessTopicFragment", "GetWorkoutsAndRefreshUiCallback ", str, "==Failed--errorcode:", Integer.valueOf(i));
                    fitnessTopicFragment.f.b(false);
                    fitnessTopicFragment.j();
                    if (fitnessTopicFragment.n == 0) {
                        fitnessTopicFragment.g.setVisibility(8);
                        fitnessTopicFragment.p.setVisibility(0);
                        fitnessTopicFragment.k.setVisibility(4);
                    }
                    LogUtil.b("Suggestion_FitnessTopicFragment", "getWorkoutsByTopicIdAndRefresh(), load data failed");
                    return;
                }
                LogUtil.h("Suggestion_FitnessTopicFragment", "GetWorkoutsAndRefreshUiCallback onFailure isActivityDestroyed");
                return;
            }
            LogUtil.b("Suggestion_FitnessTopicFragment", "GetWorkoutsAndRefreshUiCallback onFailure fitnessTopicFragment is null.");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<Workout> list) {
            if (koq.b(list)) {
                LogUtil.h("Suggestion_FitnessTopicFragment", "GetWorkoutsAndRefreshUiCallback onSuccess fitnessFitWorkouts is null");
                return;
            }
            WeakReference<FitnessTopicFragment> weakReference = this.e;
            if (weakReference == null) {
                LogUtil.b("Suggestion_FitnessTopicFragment", "GetWorkoutsAndRefreshUiCallback onSuccess mWeakReference is null.");
                return;
            }
            FitnessTopicFragment fitnessTopicFragment = weakReference.get();
            if (fitnessTopicFragment != null) {
                if (!fitnessTopicFragment.k()) {
                    fitnessTopicFragment.j = true;
                    FitnessTopicFragment.l(fitnessTopicFragment);
                    fitnessTopicFragment.j();
                    fitnessTopicFragment.p.setVisibility(8);
                    fitnessTopicFragment.g.setVisibility(8);
                    fitnessTopicFragment.k.setVisibility(8);
                    List<FitWorkout> a2 = fitnessTopicFragment.a(mod.a(list));
                    if (fitnessTopicFragment.y == 8) {
                        a2 = fitnessTopicFragment.c(a2);
                    }
                    fitnessTopicFragment.f.a(fitnessTopicFragment.f3122a, false, a2);
                    return;
                }
                LogUtil.h("Suggestion_FitnessTopicFragment", "GetWorkoutsAndRefreshUiCallback onSuccess isActivityDestroyed");
                return;
            }
            LogUtil.b("Suggestion_FitnessTopicFragment", "GetWorkoutsAndRefreshUiCallback onSuccess fitnessTopicFragment is null.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<FitWorkout> c(List<FitWorkout> list) {
        ArrayList arrayList = new ArrayList(2);
        if (koq.b(list)) {
            return arrayList;
        }
        for (FitWorkout fitWorkout : list) {
            if (fitWorkout != null) {
                if ("R001".equals(fitWorkout.acquireId())) {
                    arrayList.add(fitWorkout);
                } else if ("R002".equals(fitWorkout.acquireId())) {
                    arrayList.add(fitWorkout);
                }
            }
        }
        return arrayList;
    }

    private void a(int i) {
        b bVar = new b(this);
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_FitnessTopicFragment", "getWorkoutsByType : courseApi is null.");
        } else {
            courseApi.getCourseByType(this.u, i, this.n, bVar);
        }
    }

    static class b extends UiCallback<List<Workout>> {
        private WeakReference<FitnessTopicFragment> c;

        b(FitnessTopicFragment fitnessTopicFragment) {
            this.c = new WeakReference<>(fitnessTopicFragment);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            WeakReference<FitnessTopicFragment> weakReference = this.c;
            if (weakReference == null) {
                LogUtil.b("Suggestion_FitnessTopicFragment", "GetWorkoutsByTypeUiCallback onFailure mWeakReference is null.");
                return;
            }
            FitnessTopicFragment fitnessTopicFragment = weakReference.get();
            if (fitnessTopicFragment != null) {
                if (!fitnessTopicFragment.k()) {
                    fitnessTopicFragment.j = true;
                    LogUtil.b("Suggestion_FitnessTopicFragment", "GetWorkoutsByTypeUiCallback ", str, " ==Failed--errorCode: ", Integer.valueOf(i));
                    fitnessTopicFragment.f.b(false);
                    fitnessTopicFragment.j();
                    if (fitnessTopicFragment.n == 0) {
                        fitnessTopicFragment.g.setVisibility(8);
                        fitnessTopicFragment.p.setVisibility(0);
                        fitnessTopicFragment.k.setVisibility(4);
                    }
                    LogUtil.b("Suggestion_FitnessTopicFragment", "getWorkoutsByWorkoutType, load data failed");
                    return;
                }
                LogUtil.h("Suggestion_FitnessTopicFragment", "GetWorkoutsByTypeUiCallback onFailure isActivityDestroyed");
                return;
            }
            LogUtil.b("Suggestion_FitnessTopicFragment", "GetWorkoutsByTypeUiCallback onFailure fitnessTopicFragment is null.");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<Workout> list) {
            if (koq.b(list)) {
                LogUtil.h("Suggestion_FitnessTopicFragment", "GetWorkoutsByTypeUiCallback onSuccess fitnessFitWorkouts is null");
                return;
            }
            WeakReference<FitnessTopicFragment> weakReference = this.c;
            if (weakReference == null) {
                LogUtil.b("Suggestion_FitnessTopicFragment", "GetWorkoutsByTypeUiCallback onSuccess mWeakReference is null.");
                return;
            }
            FitnessTopicFragment fitnessTopicFragment = weakReference.get();
            if (fitnessTopicFragment != null) {
                if (!fitnessTopicFragment.k()) {
                    fitnessTopicFragment.j = true;
                    FitnessTopicFragment.l(fitnessTopicFragment);
                    fitnessTopicFragment.j();
                    fitnessTopicFragment.p.setVisibility(8);
                    fitnessTopicFragment.g.setVisibility(8);
                    fitnessTopicFragment.k.setVisibility(8);
                    fitnessTopicFragment.f.a(fitnessTopicFragment.f3122a, false, fitnessTopicFragment.a(mod.a(list)));
                    return;
                }
                LogUtil.h("Suggestion_FitnessTopicFragment", "GetWorkoutsByTypeUiCallback onSuccess isActivityDestroyed");
                return;
            }
            LogUtil.h("Suggestion_FitnessTopicFragment", "GetWorkoutsByTypeUiCallback onSuccess fitnessTopicFragment is null.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<FitWorkout> a(List<FitWorkout> list) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            return arrayList;
        }
        for (FitWorkout fitWorkout : list) {
            if (!arrayList.contains(fitWorkout)) {
                arrayList.add(fitWorkout);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k() {
        return getActivity() == null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessTopicFragment.4
                @Override // java.lang.Runnable
                public void run() {
                    FitnessTopicFragment.this.m.setVisibility(0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.fragment.FitnessTopicFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    FitnessTopicFragment.this.m.setVisibility(8);
                }
            });
        }
    }
}
