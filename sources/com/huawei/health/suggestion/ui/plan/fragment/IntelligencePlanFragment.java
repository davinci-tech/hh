package com.huawei.health.suggestion.ui.plan.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserveLabels;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.knit.AdvancedSportFragment;
import com.huawei.health.marketing.utils.ColumnLayoutItemDecoration;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.suggestion.ui.BaseActivity;
import com.huawei.health.suggestion.ui.plan.adapter.IntPlanDetailAdapter;
import com.huawei.health.suggestion.ui.plan.fragment.IntelligencePlanFragment;
import com.huawei.health.suggestion.ui.plan.viewholder.OverseaAiDialogViewHolder;
import com.huawei.health.suggestion.ui.plan.viewmodel.IntPlanDetailDataViewModel;
import com.huawei.health.suggestion.ui.run.activity.PlanSettingsActivity;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.health.weight.WeightApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.trade.PayApi;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.swiperefreshlayout.HealthSwipeRefreshLayout;
import com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout;
import defpackage.arx;
import defpackage.ary;
import defpackage.ase;
import defpackage.ash;
import defpackage.fhu;
import defpackage.fuo;
import defpackage.fxf;
import defpackage.fxt;
import defpackage.fxu;
import defpackage.fyb;
import defpackage.fyo;
import defpackage.fyr;
import defpackage.fyw;
import defpackage.fyx;
import defpackage.gdw;
import defpackage.gge;
import defpackage.ggs;
import defpackage.ggu;
import defpackage.gib;
import defpackage.gsd;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.jeg;
import defpackage.koq;
import defpackage.kpn;
import defpackage.kpp;
import defpackage.moe;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class IntelligencePlanFragment extends AdvancedSportFragment implements PopViewList.PopViewClickListener {
    private static boolean b = false;

    /* renamed from: a, reason: collision with root package name */
    private ViewGroup f3267a;
    private fxf aa;
    private OnFitnessStatusChangeCallback ab;
    private IntPlanDetailDataViewModel ac;
    private IntPlanDetailAdapter ad;
    private long ae;
    private fxt ai;
    private HealthRecycleView aj;
    private LinearLayout am;
    private IntPlanDetailAdapter an;
    private a ap;
    private HealthTextView aq;
    private Observer c;
    private HealthSwipeRefreshLayout h;
    private UiCallback i;
    private IntPlan j;
    private RelativeLayout p;
    private View r;
    private RelativeLayout w;
    private HealthTextView y;
    private HealthRecycleView z;
    private final Context e = BaseApplication.e();
    private androidx.lifecycle.Observer<gdw> s = new androidx.lifecycle.Observer() { // from class: com.huawei.health.suggestion.ui.plan.fragment.IntelligencePlanFragment$$ExternalSyntheticLambda3
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            IntelligencePlanFragment.this.b((gdw) obj);
        }
    };
    private androidx.lifecycle.Observer<Boolean> ag = new androidx.lifecycle.Observer() { // from class: com.huawei.health.suggestion.ui.plan.fragment.IntelligencePlanFragment$$ExternalSyntheticLambda4
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            IntelligencePlanFragment.this.c(((Boolean) obj).booleanValue());
        }
    };
    private androidx.lifecycle.Observer<gdw> t = new androidx.lifecycle.Observer() { // from class: com.huawei.health.suggestion.ui.plan.fragment.IntelligencePlanFragment$$ExternalSyntheticLambda5
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            IntelligencePlanFragment.this.c((gdw) obj);
        }
    };
    private OnFitnessStatusChangeCallback u = new OnFitnessStatusChangeCallback() { // from class: fww
        @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
        public final void onUpdate() {
            IntelligencePlanFragment.this.i();
        }
    };
    private OnFitnessStatusChangeCallback af = new OnFitnessStatusChangeCallback() { // from class: fwy
        @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
        public final void onUpdate() {
            IntelligencePlanFragment.this.j();
        }
    };
    private OnFitnessStatusChangeCallback ah = new OnFitnessStatusChangeCallback() { // from class: fxb
        @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
        public final void onUpdate() {
            IntelligencePlanFragment.this.h();
        }
    };
    private OnFitnessStatusChangeCallback v = new OnFitnessStatusChangeCallback() { // from class: fwz
        @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
        public final void onUpdate() {
            IntelligencePlanFragment.this.f();
        }
    };
    private Handler q = new Handler(Looper.getMainLooper());
    private final Object as = new Object();
    private boolean n = false;
    private boolean k = false;
    private boolean f = true;
    private boolean l = false;
    private boolean al = false;
    private boolean ak = false;
    private boolean d = false;
    private boolean o = false;
    private boolean m = false;
    private fuo g = new fuo();
    private final Observer x = new Observer() { // from class: fwf
        @Override // com.huawei.haf.design.pattern.Observer
        public final void notify(String str, Object[] objArr) {
            IntelligencePlanFragment.this.e(str, objArr);
        }
    };

    public /* synthetic */ void i() {
        d("plan create");
    }

    public /* synthetic */ void j() {
        d("plan update");
    }

    public /* synthetic */ void h() {
        d("plan switch");
    }

    public /* synthetic */ void f() {
        d("PLAN_ADJUST");
    }

    public /* synthetic */ void e(final String str, Object[] objArr) {
        if ("WATCH_PLAN_REPORT_DIALOG".equals(str)) {
            IntPlanDetailAdapter intPlanDetailAdapter = this.ad;
            if (intPlanDetailAdapter == null) {
                LogUtil.h("Suggestion_IntelligencePlanFragment", "WATCH_PLAN_REPORT_DIALOG mPlanDetailAdapter == null");
                return;
            }
            View aGP_ = intPlanDetailAdapter.aGP_();
            if (aGP_ == null) {
                LogUtil.h("Suggestion_IntelligencePlanFragment", " view == null");
                return;
            } else {
                aGP_.setVisibility(8);
                return;
            }
        }
        if (ObserveLabels.STAY_FIT_PLAN_LOADING_FINISH.equals(str)) {
            ReleaseLogUtil.e("Suggestion_IntelligencePlanFragment", "mObserver loading label ", str, " mLoadingView ", this.r);
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.suggestion.ui.plan.fragment.IntelligencePlanFragment.2
                @Override // java.lang.Runnable
                public void run() {
                    if (IntelligencePlanFragment.this.r == null || IntelligencePlanFragment.this.r.getVisibility() == 8) {
                        return;
                    }
                    ReleaseLogUtil.e("Suggestion_IntelligencePlanFragment", "mObserver loading gone label ", str);
                    IntelligencePlanFragment.this.r.setVisibility(8);
                }
            });
            return;
        }
        if (ObserveLabels.UPDATE_STAY_FIT_PLAN.equals(str)) {
            ae();
            ary.a().e("PLAN_UPDATE");
            return;
        }
        if ("SPORT_ENTRANCE_IS_VISIBLE_TO_USER".equals(str)) {
            if (objArr == null || objArr.length == 0) {
                LogUtil.h("Suggestion_IntelligencePlanFragment", "mObserver objects ", objArr);
                return;
            }
            Object obj = objArr[0];
            if (!(obj instanceof Boolean)) {
                LogUtil.h("Suggestion_IntelligencePlanFragment", "mObserver object ", obj);
            } else if (((Boolean) obj).booleanValue()) {
                j("sportVisible");
            }
        }
    }

    public static BaseFragment a() {
        return new IntelligencePlanFragment();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("Suggestion_IntelligencePlanFragment", "onCreateView");
        View inflate = layoutInflater.inflate(R.layout.sug_fragment_intelligent_plan_detail, viewGroup, false);
        if (inflate instanceof ViewGroup) {
            this.f3267a = (ViewGroup) inflate;
        }
        BaseActivity.setViewSafeRegion(false, inflate);
        this.z = (HealthRecycleView) inflate.findViewById(R.id.plan_detail_recycle_view);
        this.p = (RelativeLayout) inflate.findViewById(R.id.network_error_layout);
        this.y = (HealthTextView) inflate.findViewById(R.id.no_net_work_tips);
        this.r = inflate.findViewById(R.id.loading_progress);
        this.w = (RelativeLayout) inflate.findViewById(R.id.oversea_dialog);
        aHR_(inflate);
        this.ad = new IntPlanDetailAdapter(getContext(), getChildFragmentManager(), this.z, this.g);
        ColumnLayoutItemDecoration m = m();
        this.z.addItemDecoration(m);
        this.z.setLayoutManager(new HealthLinearLayoutManager(getContext()));
        this.z.setAdapter(this.ad);
        this.z.setNestedScrollingEnabled(false);
        this.z.setItemAnimator(null);
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.plan_recommend_recycle_view);
        this.aj = healthRecycleView;
        healthRecycleView.addItemDecoration(m);
        this.an = new IntPlanDetailAdapter(getContext(), null, this.aj);
        if (!fyw.d()) {
            ah();
            ggs.a(getContext(), this.aj);
        } else {
            this.aj.setLayoutManager(new HealthLinearLayoutManager(getContext()));
        }
        this.aj.setAdapter(this.an);
        this.am = (LinearLayout) inflate.findViewById(R.id.sync_cloud_data_layout);
        this.aq = (HealthTextView) inflate.findViewById(R.id.sync_progress_percent);
        this.ab = new OnFitnessStatusChangeCallback() { // from class: fws
            @Override // com.huawei.basefitnessadvice.callback.OnFitnessStatusChangeCallback
            public final void onUpdate() {
                IntelligencePlanFragment.this.n();
            }
        };
        ai();
        v();
        if (LanguageUtil.bc(getContext())) {
            inflate.setRotationY(180.0f);
        }
        this.l = true;
        this.m = UnitUtil.h();
        this.ae = System.currentTimeMillis();
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi != null) {
            LogUtil.a("Suggestion_IntelligencePlanFragment", "DietDiaryRepository syncDietData ");
            weightApi.syncDietData();
        }
        return inflate;
    }

    public /* synthetic */ void n() {
        d("plan finish");
        fxf fxfVar = this.aa;
        if (fxfVar != null) {
            fxfVar.a(getContext());
        } else {
            LogUtil.b("Suggestion_IntelligencePlanFragment", "mPlanDetailInteract is  null");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ObserverManagerUtil.d(this.x, "WATCH_PLAN_REPORT_DIALOG");
        ObserverManagerUtil.d(this.x, "SPORT_ENTRANCE_IS_VISIBLE_TO_USER");
        ObserverManagerUtil.d(this.x, ObserveLabels.UPDATE_STAY_FIT_PLAN);
        ObserverManagerUtil.d(this.x, ObserveLabels.STAY_FIT_PLAN_LOADING_FINISH);
    }

    private void ah() {
        if (this.aj.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.aj.getLayoutParams();
            layoutParams.setMarginStart((int) getResources().getDimension(R.dimen._2131362009_res_0x7f0a00d9));
            layoutParams.setMarginEnd((int) getResources().getDimension(R.dimen._2131362007_res_0x7f0a00d7));
            this.aj.setLayoutParams(layoutParams);
        }
    }

    private void l() {
        HandlerExecutor.e(new Runnable() { // from class: fwj
            @Override // java.lang.Runnable
            public final void run() {
                IntelligencePlanFragment.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        HealthRecycleView healthRecycleView = this.aj;
        if (healthRecycleView == null || !(healthRecycleView.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.aj.getLayoutParams();
        layoutParams.setMarginStart((int) getResources().getDimension(R.dimen._2131363828_res_0x7f0a07f4));
        layoutParams.setMarginEnd((int) getResources().getDimension(R.dimen._2131363828_res_0x7f0a07f4));
        this.aj.setLayoutParams(layoutParams);
    }

    private ColumnLayoutItemDecoration m() {
        return new ColumnLayoutItemDecoration(0, getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8), 1, new int[]{0, getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532), 0, 0});
    }

    private void ai() {
        this.i = new UiCallback() { // from class: com.huawei.health.suggestion.ui.plan.fragment.IntelligencePlanFragment.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Suggestion_IntelligencePlanFragment", "finish plan fail, errorInfo:" + str);
                if (IntelligencePlanFragment.this.j != null) {
                    ash.a("ai_plan_finish", IntelligencePlanFragment.this.j.getPlanId());
                }
                IntelligencePlanFragment.this.r.setVisibility(8);
                FragmentActivity activity = IntelligencePlanFragment.this.getActivity();
                if (activity == null) {
                    LogUtil.h("Suggestion_IntelligencePlanFragment", "finish plan fail getActivity() == null");
                    return;
                }
                if (i == -404) {
                    IntelligencePlanFragment.this.b(activity, R.string._2130839508_res_0x7f0207d4);
                } else if (i == -6) {
                    IntelligencePlanFragment.this.b(activity, R.string.IDS_hw_show_sync_cloud_history_data);
                } else {
                    if (i != 200025) {
                        return;
                    }
                    IntelligencePlanFragment.this.b(activity, R.string._2130848738_res_0x7f022be2);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onSuccess(Object obj) {
                IntelligencePlanFragment.this.r.setVisibility(8);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, int i) {
        new NoTitleCustomAlertDialog.Builder(context).e(i).czC_(R.string._2130848357_res_0x7f022a65, new View.OnClickListener() { // from class: fwu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e().show();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.a("Suggestion_IntelligencePlanFragment", "onAttach");
        ab();
    }

    public void aHR_(View view) {
        HealthSwipeRefreshLayout healthSwipeRefreshLayout = (HealthSwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
        this.h = healthSwipeRefreshLayout;
        healthSwipeRefreshLayout.setPadding(0, 0, 0, 0);
        w();
    }

    private void w() {
        this.h.setRefreshPushText(getContext().getResources().getString(R.string._2130841851_res_0x7f0210fb));
        this.h.setCallback(new HwSwipeRefreshLayout.Callback() { // from class: com.huawei.health.suggestion.ui.plan.fragment.IntelligencePlanFragment.3
            @Override // com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout.Callback
            public boolean needToWait() {
                return true;
            }

            @Override // com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout.Callback
            public void onScrollUp() {
            }

            @Override // com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout.Callback
            public boolean isEnabled() {
                return ((IntelligencePlanFragment.this.ak ? IntelligencePlanFragment.this.z.canScrollVertically(-1) : IntelligencePlanFragment.this.aj.canScrollVertically(-1)) || LoginInit.getInstance(IntelligencePlanFragment.this.e).isBrowseMode()) ? false : true;
            }

            @Override // com.huawei.uikit.hwswiperefreshlayout.widget.HwSwipeRefreshLayout.Callback
            public void onRefreshStart() {
                LogUtil.a("Suggestion_IntelligencePlanFragment", "PullRefresh start");
                IntelligencePlanFragment.this.ae();
                boolean unused = IntelligencePlanFragment.b = true;
                IntelligencePlanFragment.this.d = false;
                IntelligencePlanFragment.this.d("pull refresh");
            }
        });
    }

    private void v() {
        IntPlanDetailDataViewModel intPlanDetailDataViewModel = (IntPlanDetailDataViewModel) new ViewModelProvider(this).get(IntPlanDetailDataViewModel.class);
        this.ac = intPlanDetailDataViewModel;
        intPlanDetailDataViewModel.e(this.s);
        this.ac.a(this.ag);
        this.ac.d(this.t);
        this.aa = new fxf(this.ac, this);
        ary.a().e(this.u, "PLAN_CREATE");
        ary.a().e(this.af, "PLAN_UPDATE");
        ary.a().e(this.ab, "PLAN_DELETE");
        ary.a().e(this.ah, "PLAN_SWITCH");
        ary.a().e(this.v, "PLAN_ADJUST");
    }

    private void t() {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(arx.b().getString(R.string._2130839004_res_0x7f0205dc), arx.b().getString(R.string._2130848656_res_0x7f022b90), arx.b().getString(R.string._2130848662_res_0x7f022b96), arx.b().getString(R.string._2130848364_res_0x7f022a6c)));
        if (this.j.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN && !EnvironmentInfo.k()) {
            arrayList.add(3, arx.b().getString(R.string._2130848494_res_0x7f022aee));
        }
        this.mPopViewController.c(this);
        this.mPopViewController.a(arrayList);
        this.mPopViewController.a(true);
        LogUtil.a("Suggestion_IntelligencePlanFragment", "initMenuPopView() mPopViewController.isEnable: ", Boolean.valueOf(this.mPopViewController.c()));
    }

    public void d(final String str) {
        LogUtil.a("Suggestion_IntelligencePlanFragment", "getCurrentPlanOnMain  reason = ", str);
        g(str);
        if (isResumed() || "plan switch".equals(str)) {
            synchronized (this.as) {
                if (!this.al) {
                    this.al = true;
                    HandlerExecutor.e(new Runnable() { // from class: fwo
                        @Override // java.lang.Runnable
                        public final void run() {
                            IntelligencePlanFragment.this.b(str);
                        }
                    });
                    this.l = false;
                } else {
                    LogUtil.a("Suggestion_IntelligencePlanFragment", "isRefreshing.");
                }
            }
        } else {
            LogUtil.a("Suggestion_IntelligencePlanFragment", "no resume.");
            this.l = true;
        }
        LogUtil.a("Suggestion_IntelligencePlanFragment", "getCurrentPlanOnMain  reason = ", str, " end.");
    }

    private void g(String str) {
        if ("pull refresh".equals(str) && Utils.o()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: fwv
                @Override // java.lang.Runnable
                public final void run() {
                    IntelligencePlanFragment.this.d();
                }
            });
        }
    }

    public /* synthetic */ void d() {
        if (gsd.a("RECOMMEND_DIET_TIME_MILLIS") && gsd.a("DIET_ANALYSIS_TIME_MILLIS")) {
            return;
        }
        ReleaseLogUtil.e("Suggestion_IntelligencePlanFragment", "refresh diet setting");
        String accountInfo = LoginInit.getInstance(this.e).getAccountInfo(1010);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.e("Suggestion_IntelligencePlanFragment", "code is empty");
        } else {
            kpp.c(new kpn(accountInfo), new ResponseCallback<Boolean>() { // from class: com.huawei.health.suggestion.ui.plan.fragment.IntelligencePlanFragment.5
                @Override // com.huawei.hwbasemgr.ResponseCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onResponse(int i, Boolean bool) {
                    ReleaseLogUtil.e("Suggestion_IntelligencePlanFragment", "refresh diet setting success:", bool);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void b(String str) {
        View view;
        synchronized (this.as) {
            this.al = false;
        }
        ReleaseLogUtil.e("Suggestion_IntelligencePlanFragment", "getCurrentPlan begin.", str);
        if (!this.ak && (view = this.r) != null && !b) {
            view.setVisibility(0);
        }
        if (LoginInit.getInstance(this.e).isBrowseMode()) {
            LogUtil.a("Suggestion_IntelligencePlanFragment", "getCurrentPlan in browser mode, try show plan list.");
            this.j = null;
            ad();
            this.ac.a();
            if (fyw.d()) {
                l();
            }
            this.ac.d(b);
            s();
            return;
        }
        c(str);
    }

    private void c(final String str) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: fwm
            @Override // java.lang.Runnable
            public final void run() {
                IntelligencePlanFragment.this.e(str);
            }
        });
    }

    public /* synthetic */ void e(String str) {
        if (this.j == null && CommonUtil.aa(getContext())) {
            this.ac.d(b);
            this.ak = false;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_IntelligencePlanFragment", "getCurrentIntPlan intPlanApi is null.");
        } else {
            planApi.b(new AnonymousClass4(str));
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.plan.fragment.IntelligencePlanFragment$4, reason: invalid class name */
    public class AnonymousClass4 extends UiCallback<IntPlan> {
        final /* synthetic */ String d;

        AnonymousClass4(String str) {
            this.d = str;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(final int i, String str) {
            LogUtil.b("Suggestion_IntelligencePlanFragment", "errorCode is " + i + ",errorInfo is " + str);
            HandlerExecutor.e(new Runnable() { // from class: fxc
                @Override // java.lang.Runnable
                public final void run() {
                    IntelligencePlanFragment.AnonymousClass4.this.b(i);
                }
            });
        }

        public /* synthetic */ void b(int i) {
            IntelligencePlanFragment.this.e(i);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final IntPlan intPlan) {
            final String str = this.d;
            HandlerExecutor.e(new Runnable() { // from class: fxa
                @Override // java.lang.Runnable
                public final void run() {
                    IntelligencePlanFragment.AnonymousClass4.this.e(intPlan, str);
                }
            });
        }

        public /* synthetic */ void e(IntPlan intPlan, String str) {
            IntelligencePlanFragment.this.b(intPlan, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi != null) {
            planApi.setNeedUpdateCurrentPlan();
        }
    }

    private void c(IntPlan intPlan) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (intPlan == null || planApi == null) {
            LogUtil.h("Suggestion_IntelligencePlanFragment", "donateFitnessFaCard, intPlan or intPlanApi is null");
            return;
        }
        int remindTime = planApi.getRemindTime();
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, moe.a(remindTime));
        calendar.set(12, moe.b(remindTime));
        int i = 0;
        calendar.set(13, 0);
        calendar.set(14, 0);
        int g = ase.g(intPlan);
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(g);
        IntWeekPlan weekInfoByOrder2 = intPlan.getWeekInfoByOrder(g + 1);
        if (weekInfoByOrder == null) {
            LogUtil.h("Suggestion_IntelligencePlanFragment", "donateFitnessFaCard, weekPlan is null");
            return;
        }
        int i2 = calendar.get(7);
        ArrayList arrayList = new ArrayList(7);
        while (true) {
            if (i >= 6) {
                break;
            }
            int i3 = i2 + i;
            calendar.set(7, i3);
            if (i3 > 7 && i3 % 7 == 2) {
                if (weekInfoByOrder2 == null) {
                    LogUtil.h("Suggestion_IntelligencePlanFragment", "donateFitnessFaCard, nextWeekPlan is null");
                    break;
                }
                weekInfoByOrder = weekInfoByOrder2;
            }
            IntDayPlan dayByOrder = weekInfoByOrder.getDayByOrder(gib.d(calendar.get(7)));
            if (dayByOrder == null) {
                LogUtil.h("Suggestion_IntelligencePlanFragment", "donateFitnessFaCard, dayPlan is null");
            } else if (dayByOrder.getInPlanActionCnt() <= 0) {
                LogUtil.h("Suggestion_IntelligencePlanFragment", "donateFitnessFaCard, planAction is null");
            } else {
                long timeInMillis = calendar.getTimeInMillis();
                if (System.currentTimeMillis() < timeInMillis) {
                    arrayList.addAll(fyr.a(timeInMillis));
                }
            }
            i++;
        }
        if (koq.c(arrayList)) {
            fyr.d(1, arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        this.j = null;
        this.aj.setVisibility(8);
        d(this.aj);
        aa();
        ad();
        this.ak = false;
        if (i == -404) {
            this.y.setText(R.string._2130841392_res_0x7f020f30);
            k();
            r();
        } else {
            this.y.setText(R.string._2130843631_res_0x7f0217ef);
            k();
            r();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(IntPlan intPlan, String str) {
        if (intPlan != null && IntPlan.PlanType.AI_FITNESS_PLAN.equals(intPlan.getPlanType()) && !ase.m(intPlan) && !fyw.t(this.j)) {
            PluginSuggestion.getInstance().startSendIntelligentPlan();
        }
        this.p.setVisibility(8);
        if (intPlan == null) {
            LogUtil.a("Suggestion_IntelligencePlanFragment", "getCurrentPlan no plan now");
            if (this.j != null) {
                this.j = null;
                this.ac.d(b);
            }
            s();
        } else {
            LogUtil.a("Suggestion_IntelligencePlanFragment", "getCurrentPlan is ", intPlan.getPlanId());
            if (this.j != null && !TextUtils.isEmpty(intPlan.getPlanId()) && !intPlan.getPlanId().equals(this.j.getPlanId())) {
                this.ac.d();
            }
            this.j = intPlan;
            h(str);
            c(intPlan);
            d(this.j);
        }
        k();
    }

    private void k() {
        z();
        if (b) {
            this.h.a();
            b = false;
        }
    }

    private void r() {
        this.p.setVisibility(0);
        this.p.setClickable(true);
        this.p.setOnClickListener(new View.OnClickListener() { // from class: fwk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligencePlanFragment.this.aHT_(view);
            }
        });
    }

    public /* synthetic */ void aHT_(View view) {
        if (!nsn.o()) {
            this.p.setVisibility(8);
            this.r.setVisibility(0);
            b("plan switch");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void aa() {
        if (this.ak) {
            return;
        }
        this.ac.e();
    }

    private void ad() {
        if (this.ak) {
            this.ac.b();
            this.ac.d();
            this.d = false;
        }
        this.mPopViewController.a(false);
        LogUtil.a("Suggestion_IntelligencePlanFragment", "removePlanDetail() mPopViewController.isEnable: ", Boolean.valueOf(this.mPopViewController.c()));
    }

    private void s() {
        String b2 = ash.b("ai_plan_sync_name");
        ReleaseLogUtil.e("Suggestion_IntelligencePlanFragment", "getCurrentPlan no plan now");
        if (b2 != null && !b2.equals("")) {
            LogUtil.a("Suggestion_IntelligencePlanFragment", "handleShowPlanList There are invalid calendar events: ", b2);
            i(b2);
        }
        ad();
        this.aj.setVisibility(0);
        b(this.aj);
        this.z.setVisibility(8);
        d(this.z);
        this.ak = false;
    }

    private void h(String str) {
        aa();
        this.ak = true;
        String b2 = ash.b("ai_plan_sync_name");
        String planId = this.j.getMetaInfo().getPlanId();
        String b3 = ash.b("ai_plan_sync");
        if (b3 != null && !planId.equals(b3)) {
            LogUtil.a("Suggestion_IntelligencePlanFragment", "handleShowPlanDetail There are invalid calendar events.");
            i(b2);
        }
        if (IntPlan.PlanType.AI_RUN_PLAN.equals(this.j.getPlanType())) {
            fxt fxtVar = new fxt(getContext(), this.j);
            this.ai = fxtVar;
            fxtVar.d();
        }
        t();
        m(str);
        this.aj.setVisibility(8);
        d(this.aj);
        this.z.setVisibility(0);
        b(this.z);
    }

    private void i(String str) {
        if (fyo.e(getContext())) {
            f(str);
        } else {
            LogUtil.a("Suggestion_IntelligencePlanFragment", "No calendar permission for deleting events");
        }
    }

    private void f(String str) {
        fyo.c(getContext(), str, (IntPlan.PlanType) null);
        ash.d("ai_plan_sync_name");
        ash.d("ai_plan_sync");
    }

    private void z() {
        HandlerExecutor.e(new Runnable() { // from class: fwp
            @Override // java.lang.Runnable
            public final void run() {
                IntelligencePlanFragment.this.g();
            }
        });
    }

    public /* synthetic */ void g() {
        if (ase.k(this.j)) {
            HandlerExecutor.d(new Runnable() { // from class: fwr
                @Override // java.lang.Runnable
                public final void run() {
                    IntelligencePlanFragment.this.b();
                }
            }, 5000L);
            return;
        }
        View view = this.r;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public /* synthetic */ void b() {
        View view = this.r;
        if (view == null || view.getVisibility() == 8) {
            return;
        }
        ReleaseLogUtil.e("Suggestion_IntelligencePlanFragment", "loadingGone delay gone");
        this.r.setVisibility(8);
    }

    private void m(String str) {
        z();
        this.ac.a(this.j);
        if (!fyw.a()) {
            if (getContext() == null) {
                LogUtil.h("Suggestion_IntelligencePlanFragment", "showPlanDetail context == null");
                return;
            }
            if (this.d && !this.aa.d(getContext())) {
                this.ac.b();
            }
            if (!this.d) {
                this.d = true;
                this.aa.b(getContext(), this.j);
                this.ae = System.currentTimeMillis();
                LogUtil.a("Suggestion_IntelligencePlanFragment", "showPlanDetail update plan");
            }
            if (fyo.e(getContext()) && ("PLAN_ADJUST".equals(str) || ("plan update".equals(str) && ase.k(this.j)))) {
                ReleaseLogUtil.e("Suggestion_IntelligencePlanFragment", "IntelligencePlanFragment has CalendarPermission");
                this.aa.a(this.j);
                this.aa.b(getContext());
                this.aa.c(getContext());
            }
        }
        ag();
    }

    private void ag() {
        if (!getUserVisibleHint()) {
            ReleaseLogUtil.e("Suggestion_IntelligencePlanFragment", "not in plan tab.");
            this.o = true;
            return;
        }
        Context context = getContext();
        if (context == null || this.j == null) {
            Object[] objArr = new Object[4];
            objArr[0] = "do not show dialog.";
            objArr[1] = Boolean.valueOf(context == null);
            objArr[2] = Boolean.valueOf(fyw.a());
            objArr[3] = Boolean.valueOf(this.j == null);
            LogUtil.b("Suggestion_IntelligencePlanFragment", objArr);
            return;
        }
        ac();
        if (fxu.c().d()) {
            return;
        }
        if (fyw.a()) {
            LogUtil.b("Suggestion_IntelligencePlanFragment", "isInSyncDataProgress.");
        } else {
            c(context);
        }
    }

    private void ac() {
        if (ase.a() && this.j.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN)) {
            if (fxu.c().d() || !fxu.c().e()) {
                LogUtil.h("Suggestion_IntelligencePlanFragment", "loadGuideView:", Boolean.valueOf(fxu.c().d()), " ", Boolean.valueOf(fxu.c().e()));
            } else {
                fxu.c().c(getContext(), new IBaseResponseCallback() { // from class: fwx
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        IntelligencePlanFragment.this.b(i, obj);
                    }
                });
            }
        }
    }

    public /* synthetic */ void b(int i, Object obj) {
        Context context = getContext();
        if (context == null || this.j == null) {
            ReleaseLogUtil.d("Suggestion_IntelligencePlanFragment", "loadGuideView ontext == null || mCurrentIntPlan == null");
        } else {
            LogUtil.a("Suggestion_IntelligencePlanFragment", "after showGuideView.showRemindDialog");
            c(context);
        }
    }

    private boolean d(Context context) {
        if (!fyw.x(this.j)) {
            return false;
        }
        ReleaseLogUtil.e("Suggestion_IntelligencePlanFragment", "planOverdue fnish plan");
        if (fyw.q(this.j)) {
            e(context, R.string._2130848609_res_0x7f022b61);
            return true;
        }
        e(context, R.string._2130848608_res_0x7f022b60);
        return true;
    }

    private void c(Context context) {
        if (ase.k(this.j)) {
            LogUtil.a("Suggestion_IntelligencePlanFragment", "showRemindDialog is h5 Plan");
            return;
        }
        if (d(context)) {
            return;
        }
        if (fyw.t(this.j)) {
            ReleaseLogUtil.d("Suggestion_IntelligencePlanFragment", "plan vip expired");
            return;
        }
        if (fyw.h(this.j) >= 3 && (this.j.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN) || this.j.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN))) {
            ReleaseLogUtil.e("Suggestion_IntelligencePlanFragment", "three week absent fnish plan");
            e(getContext(), R.string._2130848597_res_0x7f022b55);
        } else if (y()) {
            ReleaseLogUtil.e("Suggestion_IntelligencePlanFragment", "all exercise clockin fnish plan");
            e(context, R.string._2130848609_res_0x7f022b61);
        } else {
            this.aa.a(this.j);
            this.aa.e(getContext(), this.j);
        }
        this.o = false;
    }

    private boolean y() {
        return fyw.n(this.j) && ((this.j.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) && fyw.m(this.j)) || this.j.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN));
    }

    private void e(Context context, int i) {
        if (ash.b("ai_plan_finish").equals(this.j.getPlanId())) {
            LogUtil.a("Suggestion_IntelligencePlanFragment", "just remind in first.");
            return;
        }
        ggu.e(context, this.j, i, this.i);
        fyx.a(context.getString(i), this.j);
        ash.a("ai_plan_finish", this.j.getPlanId());
    }

    private void e(Context context, final IntPlan intPlan, final UiCallback uiCallback) {
        new NoTitleCustomAlertDialog.Builder(context).e(R.string._2130848367_res_0x7f022a6f).czC_(R.string._2130848357_res_0x7f022a65, new View.OnClickListener() { // from class: fwt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntelligencePlanFragment.this.aHS_(intPlan, uiCallback, view);
            }
        }).czz_(R.string._2130839509_res_0x7f0207d5, new View.OnClickListener() { // from class: fwq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e().show();
    }

    public /* synthetic */ void aHS_(IntPlan intPlan, UiCallback uiCallback, View view) {
        this.r.setVisibility(0);
        ggu.d(intPlan, 1);
        ggu.a(intPlan, true, uiCallback);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(gdw gdwVar) {
        if (gdwVar == null) {
            LogUtil.h("Suggestion_IntelligencePlanFragment", "notifyUpdateItemData itemData is null");
            return;
        }
        int a2 = gdwVar.a();
        if (a2 == 7) {
            if (nsn.ag(this.e)) {
                this.an.e(gdwVar);
                return;
            }
            return;
        }
        Context context = getContext();
        LogUtil.a("Suggestion_IntelligencePlanFragment", "notifyUpdateItemData viewType ", Integer.valueOf(a2), " context ", context);
        if (a2 == 6 && context != null) {
            OverseaAiDialogViewHolder overseaAiDialogViewHolder = new OverseaAiDialogViewHolder(LayoutInflater.from(context).inflate(R.layout.plan_oversea_dialog, (ViewGroup) null, false));
            overseaAiDialogViewHolder.a(gdwVar.b());
            this.w.addView(overseaAiDialogViewHolder.itemView);
            this.w.setVisibility(0);
            this.aj.smoothScrollToPosition(0);
            return;
        }
        if ((gdwVar.c() == 0 && a2 == 5) || a2 == 3) {
            this.an.e(gdwVar);
            return;
        }
        IntPlanDetailAdapter intPlanDetailAdapter = this.ad;
        if (intPlanDetailAdapter != null) {
            intPlanDetailAdapter.e(gdwVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(gdw gdwVar) {
        if (gdwVar == null) {
            LogUtil.h("Suggestion_IntelligencePlanFragment", "notifyDeleteItemData itemData is null");
            return;
        }
        if (gdwVar.a() == 6) {
            this.w.removeAllViews();
            this.w.setVisibility(8);
        } else {
            if ((gdwVar.c() == 0 && gdwVar.a() == 5) || gdwVar.a() == 3) {
                this.an.c(gdwVar);
                return;
            }
            IntPlanDetailAdapter intPlanDetailAdapter = this.ad;
            if (intPlanDetailAdapter != null) {
                intPlanDetailAdapter.c(gdwVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        if (LoginInit.getInstance(this.e).isBrowseMode()) {
            k();
        }
        if (z) {
            k();
            if (LoginInit.getInstance(this.e).isBrowseMode()) {
                this.p.setVisibility(0);
                this.p.setClickable(true);
                this.y.setText(R.string._2130842482_res_0x7f021372);
                this.p.setOnClickListener(new View.OnClickListener() { // from class: fwi
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        IntelligencePlanFragment.this.aHU_(view);
                    }
                });
                return;
            }
            this.y.setText(R.string._2130843631_res_0x7f0217ef);
            this.p.setVisibility(0);
        }
    }

    public /* synthetic */ void aHU_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            LoginInit.getInstance(this.e).browsingToLogin(new IBaseResponseCallback() { // from class: fwn
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    LogUtil.a("Suggestion_IntelligencePlanFragment", "browsingToLogin result = ", Integer.valueOf(i));
                }
            }, "");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public void a(String[] strArr) {
        LogUtil.a("Suggestion_IntelligencePlanFragment", "request CalendarPermission On Fragment");
        jeg.d().a(this, strArr, new PermissionsResultAction() { // from class: com.huawei.health.suggestion.ui.plan.fragment.IntelligencePlanFragment.8
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("Suggestion_IntelligencePlanFragment", "CalendarPermission Granted");
                IntelligencePlanFragment.this.aa.c(IntelligencePlanFragment.this.getContext());
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("Suggestion_IntelligencePlanFragment", "CalendarPermission Denied");
                if (IntelligencePlanFragment.this.shouldShowRequestPermissionRationale(str)) {
                    return;
                }
                nsn.e(IntelligencePlanFragment.this.getContext(), PermissionUtil.PermissionType.CALENDAR);
            }
        });
    }

    private boolean x() {
        String b2 = SharedPreferenceManager.b(getContext(), Integer.toString(20002), "map_tracking_sport_type");
        if (TextUtils.isEmpty(b2)) {
            return false;
        }
        int h = CommonUtil.h(b2);
        LogUtil.a("Suggestion_IntelligencePlanFragment", "sportType:", Integer.valueOf(h));
        return h == 2;
    }

    private void j(String str) {
        IntPlanDetailAdapter intPlanDetailAdapter = this.ad;
        if (intPlanDetailAdapter == null || !this.k) {
            LogUtil.h("Suggestion_IntelligencePlanFragment", "refreshH5AiPlan mPlanDetailAdapter ", intPlanDetailAdapter, " mIsVisibleToUser ", Boolean.valueOf(this.k));
        } else if (!ase.k(this.j)) {
            LogUtil.a("Suggestion_IntelligencePlanFragment", "refreshH5AiPlan not h5 plan return");
        } else {
            this.ad.c(str);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.n = true;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.m != UnitUtil.h()) {
            LogUtil.a("Suggestion_IntelligencePlanFragment", "switch unit:", Boolean.valueOf(this.m), Boolean.valueOf(UnitUtil.h()));
            this.m = UnitUtil.h();
            this.l = true;
        }
        if (this.k || !this.f || x()) {
            u();
        }
        LogUtil.a("Suggestion_IntelligencePlanFragment", "onResume getUserVisibleHint = ", Boolean.valueOf(this.k), " mFirstTimeVisible = ", Boolean.valueOf(this.f));
        ObserverManagerUtil.c("PLAN_ON_RESUME", new Object[0]);
        j("onResume");
        if (this.c == null) {
            Observer observer = new Observer() { // from class: com.huawei.health.suggestion.ui.plan.fragment.IntelligencePlanFragment.6
                @Override // com.huawei.haf.design.pattern.Observer
                public void notify(String str, Object... objArr) {
                    if (ObserveLabels.CALENDAR_VIEW_INITIALIZED.equals(str)) {
                        ObserverManagerUtil.c(ObserveLabels.CALENDAR_ACCESSIBILITY_CONTENT, null, null, nsf.h(R.string._2130850646_res_0x7f023356), null);
                    }
                }
            };
            this.c = observer;
            ObserverManagerUtil.d(observer, ObserveLabels.CALENDAR_VIEW_INITIALIZED);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        LogUtil.a("Suggestion_IntelligencePlanFragment", "setUserVisibleHint = ", Boolean.valueOf(z), " mIsLayoutInit = ", Boolean.valueOf(this.n), " mFirstTimeVisible = ", Boolean.valueOf(this.f));
        super.setUserVisibleHint(z);
        this.k = z;
        if (this.n && z && this.f) {
            this.f = false;
            u();
        }
        fuo fuoVar = this.g;
        if (fuoVar != null) {
            fuoVar.c(z);
        }
        if (z && this.o) {
            ag();
        }
        j("setUserVisibleHint");
        if (!z || this.g == null) {
            return;
        }
        e(this.j);
        gge.a(this.j, this.g.c());
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        ObserverManagerUtil.c(this.c);
        LogUtil.a("Suggestion_IntelligencePlanFragment", "onPause getUserVisibleHint = ", Boolean.valueOf(getUserVisibleHint()));
    }

    private void u() {
        if (!jdl.f(System.currentTimeMillis(), this.ae)) {
            LogUtil.a("Suggestion_IntelligencePlanFragment", "mPlanUpdateTime:", Long.valueOf(this.ae), " mIsNeedUpdate:", Boolean.valueOf(this.l), " mCheckDetail:", Boolean.valueOf(this.d));
            this.l = true;
            this.d = false;
        }
        if (this.l) {
            if (!this.f) {
                ae();
            }
            d("loadData");
        }
        if (this.j != null) {
            o();
        }
        this.f = false;
    }

    private void o() {
        int i = (this.j.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN || this.j.getPlanType() == IntPlan.PlanType.FIT_PLAN) ? 2 : 4;
        if (this.j.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
            i = 5;
        }
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.h("Suggestion_IntelligencePlanFragment", "payApi is null in resourceAuth.");
        } else {
            LogUtil.a("Suggestion_IntelligencePlanFragment", "resType:", Integer.valueOf(i), "PlanTempId:", this.j.getPlanTempId());
            payApi.resourceAuth(i, this.j.getPlanTempId(), new c());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.a("Suggestion_IntelligencePlanFragment", "onDestroyView.");
        IntPlanDetailDataViewModel intPlanDetailDataViewModel = this.ac;
        if (intPlanDetailDataViewModel != null) {
            intPlanDetailDataViewModel.c(this.s);
            this.ac.g(this.ag);
            this.ac.b(this.t);
        }
        ary.a().c(this.u, "PLAN_CREATE");
        ary.a().c(this.ah, "PLAN_SWITCH");
        ary.a().c(this.af, "PLAN_UPDATE");
        ary.a().c(this.ab, "PLAN_DELETE");
        ary.a().c(this.v, "PLAN_ADJUST");
        BroadcastManagerUtil.bFJ_(getContext(), this.ap);
        this.ak = false;
        this.al = false;
        this.l = false;
        this.f = true;
        ObserverManagerUtil.c(this.x);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
    public void setOnClick(int i) {
        LogUtil.a("Suggestion_IntelligencePlanFragment", "mPopViewController click ", Integer.valueOf(i));
        if (getContext() == null) {
            LogUtil.h("Suggestion_IntelligencePlanFragment", "Click PopView when context null", Integer.valueOf(i));
            return;
        }
        IntPlan intPlan = this.j;
        if (intPlan == null) {
            LogUtil.h("Suggestion_IntelligencePlanFragment", "Click PopView when plan null");
            return;
        }
        if (i == 0) {
            af();
            return;
        }
        if (i == 1) {
            q();
            return;
        }
        if (i == 2) {
            if (fyw.t(intPlan)) {
                JumpUtil.e(this.j);
                return;
            } else {
                startActivity(new Intent(getContext(), (Class<?>) PlanSettingsActivity.class));
                return;
            }
        }
        if (i != 3) {
            if (i == 4) {
                e(getContext(), this.j, this.i);
                return;
            } else {
                LogUtil.b("Suggestion_IntelligencePlanFragment", "Click plan pop view out of index ", Integer.valueOf(i));
                return;
            }
        }
        if (intPlan.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN && !EnvironmentInfo.k()) {
            this.ai.b();
        } else {
            e(getContext(), this.j, this.i);
        }
    }

    private void q() {
        if (fyw.t(this.j)) {
            JumpUtil.e(this.j);
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(this.j.getPlanType().getType()));
        hashMap.put("plan_name", this.j.getMetaInfo().getName());
        ixx.d().d(getContext(), AnalyticsValue.INT_PLAN_2030079.value(), hashMap, 0);
        if (this.j.getIntroduction() != null && this.j.getPlanType().equals(IntPlan.PlanType.FIT_PLAN)) {
            JumpUtil.c(getContext(), "", IntPlan.PlanType.FIT_PLAN.getType());
        } else {
            JumpUtil.b(getContext());
        }
    }

    private void af() {
        if (this.r != null && getContext() != null) {
            this.r.setVisibility(0);
            this.r.setBackground(ContextCompat.getDrawable(getContext(), R.color._2131299296_res_0x7f090be0));
        }
        fyb fybVar = new fyb(this.j, getContext());
        fybVar.a(new AnonymousClass10(fybVar));
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("plan_name", this.j.getMetaInfo().getName());
        ixx.d().d(getContext(), AnalyticsValue.INT_PLAN_2040213.value(), hashMap, 0);
    }

    /* renamed from: com.huawei.health.suggestion.ui.plan.fragment.IntelligencePlanFragment$10, reason: invalid class name */
    public class AnonymousClass10 extends UiCallback<HashMap<String, String>> {
        final /* synthetic */ fyb b;

        AnonymousClass10(fyb fybVar) {
            this.b = fybVar;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Suggestion_IntelligencePlanFragment", "share plan fail.");
            IntelligencePlanFragment.this.p();
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final HashMap<String, String> hashMap) {
            IntelligencePlanFragment.this.p();
            if (hashMap != null && !hashMap.isEmpty()) {
                Handler handler = IntelligencePlanFragment.this.q;
                final fyb fybVar = this.b;
                handler.post(new Runnable() { // from class: fxd
                    @Override // java.lang.Runnable
                    public final void run() {
                        fyb.this.d((HashMap<String, String>) hashMap);
                    }
                });
                return;
            }
            LogUtil.b("Suggestion_IntelligencePlanFragment", "share plan data empty.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        HandlerExecutor.e(new Runnable() { // from class: fwl
            @Override // java.lang.Runnable
            public final void run() {
                IntelligencePlanFragment.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        if (this.r == null || getContext() == null) {
            return;
        }
        this.r.setVisibility(8);
        this.r.setBackground(nsf.cKq_(R.color._2131296690_res_0x7f0901b2));
    }

    private void b(HealthRecycleView healthRecycleView) {
        if (healthRecycleView == null) {
            return;
        }
        healthRecycleView.setOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.plan.fragment.IntelligencePlanFragment.7
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                ObserverManagerUtil.c("PLAN_FRAGMENT_SCROLL", IntelligencePlanFragment.this.f3267a, IntelligencePlanFragment.this.h, recyclerView, Integer.valueOf(i2));
            }
        });
    }

    private void d(HealthRecycleView healthRecycleView) {
        if (healthRecycleView == null) {
            return;
        }
        healthRecycleView.setOnScrollChangeListener(null);
    }

    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("Suggestion_IntelligencePlanFragment", "SyncCloudDataReceiver onReceive intent is null or context is null");
                return;
            }
            String stringExtra = intent.getStringExtra("sync_cloud_data_status");
            double doubleExtra = intent.getDoubleExtra("sync_cloud_data_process", 0.0d);
            LogUtil.a("Suggestion_IntelligencePlanFragment", "SyncCloudDataReceiver onReceive to enter, action = ", intent.getAction(), " status:", stringExtra, " process:", Double.valueOf(doubleExtra));
            if ("start_sync_cloud_data".equals(stringExtra) || "ongoing_sync_cloud_data".equals(stringExtra)) {
                if (IntelligencePlanFragment.this.am != null) {
                    IntelligencePlanFragment.this.am.setVisibility(0);
                }
                if (IntelligencePlanFragment.this.aq != null) {
                    IntelligencePlanFragment.this.aq.setText(UnitUtil.e(doubleExtra, 2, 0));
                }
                ash.a("sync_cloud_data_status", Long.toString(System.currentTimeMillis()));
                return;
            }
            if ("sync_cloud_data_finish".equals(stringExtra) || "sync_cloud_data_fail".equals(stringExtra) || "sync_cloud_data_setting_flag".equals(stringExtra)) {
                if (IntelligencePlanFragment.this.am != null) {
                    IntelligencePlanFragment.this.am.setVisibility(8);
                }
                ash.d("sync_cloud_data_status");
                IntelligencePlanFragment.this.ae();
                IntelligencePlanFragment.this.d("sync cloud finish");
                return;
            }
            LogUtil.a("Suggestion_IntelligencePlanFragment", "SyncCloudDataReceiver onReceive is other status:", stringExtra);
        }
    }

    private void ab() {
        ash.d("sync_cloud_data_status");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("sync_cloud_data_action");
        this.ap = new a();
        BroadcastManagerUtil.bFz_(getContext(), this.ap, intentFilter);
    }

    private void d(IntPlan intPlan) {
        String b2 = SharedPreferenceManager.b(getContext(), Integer.toString(20002), "map_tracking_sport_type");
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        if (CommonUtil.h(b2) == 2) {
            e(intPlan);
        }
        gge.a(this.j, this.g.c());
    }

    private void e(final IntPlan intPlan) {
        if (intPlan == null || intPlan.getMetaInfo() == null) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: fwg
            @Override // java.lang.Runnable
            public final void run() {
                IntelligencePlanFragment.b(IntPlan.this);
            }
        });
    }

    public static /* synthetic */ void b(IntPlan intPlan) {
        ase.d(intPlan, "6", TextUtils.isEmpty(fhu.e().d()) ? 2 : 0, 1, 1);
        HashMap hashMap = new HashMap(5);
        hashMap.put("click", 1);
        hashMap.put("type", 4);
        hashMap.put("planName", intPlan.getMetaInfo().getName());
        if (ase.l(intPlan)) {
            hashMap.put("planType", 0);
        } else {
            hashMap.put("planType", 1);
        }
        hashMap.put("enter", "4");
        gge.e(AnalyticsValue.HEALTH_HOME_TRAINING_PROGRAM_PIC_2010036.value(), hashMap);
    }

    static class c implements IBaseResponseCallback {
        private WeakReference<IntelligencePlanFragment> e;

        private c(IntelligencePlanFragment intelligencePlanFragment) {
            this.e = new WeakReference<>(intelligencePlanFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i != 0) {
                LogUtil.h("Suggestion_IntelligencePlanFragment", "resourceAuth errorCode: ", Integer.valueOf(i), "objData: error ", obj);
                return;
            }
            if (!(obj instanceof Integer)) {
                LogUtil.h("Suggestion_IntelligencePlanFragment", "objData: error ", obj);
                return;
            }
            WeakReference<IntelligencePlanFragment> weakReference = this.e;
            if (weakReference == null) {
                LogUtil.b("Suggestion_IntelligencePlanFragment", "mWeakReference == null");
                return;
            }
            IntelligencePlanFragment intelligencePlanFragment = weakReference.get();
            if (intelligencePlanFragment == null) {
                LogUtil.b("Suggestion_IntelligencePlanFragment", "activity == null");
            }
            int intValue = ((Integer) obj).intValue();
            if (intelligencePlanFragment.j != null) {
                if (intelligencePlanFragment.j.getTransactionStatus() != intValue) {
                    intelligencePlanFragment.ae();
                    intelligencePlanFragment.d("getTransactionStatus() != authResult");
                    return;
                }
                return;
            }
            LogUtil.h("Suggestion_IntelligencePlanFragment", "resourceAuth mCurrentIntPlan is null");
        }
    }
}
